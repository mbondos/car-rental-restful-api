package tk.mbondos.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tk.mbondos.Entities.Rental;
import tk.mbondos.Exceptions.CarRentedException;
import tk.mbondos.Exceptions.NegativeDateDeltaException;
import tk.mbondos.Exceptions.RentalNotFoundException;
import tk.mbondos.Repositories.CarRepository;
import tk.mbondos.Repositories.CustomerRepository;
import tk.mbondos.Repositories.RentalRepository;

import java.time.LocalDate;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;


    @Autowired
    public RentalController(RentalRepository rentalRepository, CustomerRepository customerRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    @RequestMapping(method = GET)
    Collection<Rental> readRentals() {
        return rentalRepository.findAll();
    }

    @RequestMapping(method = GET, value = "/{rentalId}")
    Rental readRental(@PathVariable Long rentalId){
        validateRental(rentalId);
        return rentalRepository.getOne(rentalId);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    Rental add(@RequestBody Rental rental) {
        validateDate(rental);
        validateDateRange(rental);
        carRepository.save(rental.getCar());
        customerRepository.save(rental.getCustomer());
        return rentalRepository.save(rental);
    }

    @RequestMapping(method = PUT, value = "/{rentalId}")
    Rental update(@PathVariable Long rentalId, @RequestBody Rental rental) {
        Rental update = rentalRepository.getOne(rentalId);
        validateRental(rentalId);
        update.setPickupDate(rental.getPickupDate());
        update.setReturnDate(rental.getReturnDate());
        update.setCar(rental.getCar());
        update.setCustomer(rental.getCustomer());

        return rentalRepository.save(update);
    }

    @RequestMapping(method = DELETE, value = "/{rentalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long rentalId) {
        validateRental(rentalId);
        rentalRepository.delete(rentalRepository.getOne(rentalId));
    }

    //Check if pickupDate is before returnDate.
    private void validateDate(Rental rental) {
        LocalDate pickupDate = rental.getPickupDate();
        LocalDate returnDate = rental.getReturnDate();
        if(pickupDate.isAfter(returnDate))
            throw new NegativeDateDeltaException();
    }

    //Check if rental dates collide.
    private void validateDateRange(Rental rental) {
        Collection<Rental> rentalCollection = rentalRepository.findByCarId(rental.getCar().getId());

        for(Rental r : rentalCollection) {
            if((rental.getPickupDate().isAfter(r.getPickupDate()) &&
                    rental.getPickupDate().isBefore(r.getReturnDate())) ||
                    (rental.getReturnDate().isAfter(r.getPickupDate()) &&
                    rental.getReturnDate().isBefore(r.getReturnDate())) ||
                    (r.getPickupDate().isAfter(rental.getPickupDate()) &&
                     r.getPickupDate().isBefore(rental.getReturnDate())))
                        throw new CarRentedException(r.getId());
        }
    }

    private void validateRental(Long rentalId) {
        if(customerRepository.getOne(rentalId) == null) {
            throw new RentalNotFoundException(rentalId);
        }
    }
}
