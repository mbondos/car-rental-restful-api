package tk.mbondos.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tk.mbondos.Entities.Customer;
import tk.mbondos.Entities.Rental;
import tk.mbondos.Exceptions.CustomerNotFoundException;
import tk.mbondos.Exceptions.CustomerRentingException;
import tk.mbondos.Repositories.CustomerRepository;
import tk.mbondos.Repositories.RentalRepository;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, RentalRepository rentalRepository) {
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }

    @RequestMapping(method = GET)
    Collection<Customer> readCustomers() {
        return customerRepository.findAll();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    Customer add(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @RequestMapping(method = GET, value = "/{customerId}")
    Customer readCustomer(@PathVariable Long customerId) {
        validateCustomer(customerId);
        return customerRepository.getOne(customerId);
    }

    @RequestMapping(method = PUT, value = "/{customerId}")
    Customer update(@PathVariable Long customerId, @RequestBody Customer customer) {
        Customer update = customerRepository.getOne(customerId);
        validateCustomer(customerId);
        update.setFirstName(customer.getFirstName());
        update.setLastName(customer.getLastName());
        update.setAddress(customer.getAddress());

        return customerRepository.save(update);
    }



    @RequestMapping(method = DELETE, value = "/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long customerId) {
        Rental rental = rentalRepository.findAllByCustomerId(customerId);
        validateCustomer(customerId);
        if(rental != null)
            throw new CustomerRentingException(rental.getId());
        else
            customerRepository.delete(customerRepository.getOne(customerId));
    }

    private void validateCustomer(Long customerId) {
        if(customerRepository.getOne(customerId) == null) {
            throw new CustomerNotFoundException(customerId);
        }
    }



}
