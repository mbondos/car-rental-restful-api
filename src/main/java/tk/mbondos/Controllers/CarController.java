package tk.mbondos.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tk.mbondos.Entities.Car;
import tk.mbondos.Exceptions.CarNotFoundException;
import tk.mbondos.Repositories.CarRepository;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/cars")
public class CarController{

    private final CarRepository carRepository;


    @Autowired
    CarController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @RequestMapping(method = GET)
    Collection<Car> readCars() {
        return carRepository.findAll();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    Car add(@RequestBody Car car) {
        return carRepository.save(car);
     }

    @RequestMapping(method = GET, value = "/{carId}")
    Car readCar(@PathVariable Long carId){
        validateCar(carId);
        return carRepository.getOne(carId);
    }

    @RequestMapping(method = PUT, value = "/{carId}")
    Car update(@PathVariable Long carId, @RequestBody Car car) {
        Car update = carRepository.getOne(carId);
        validateCar(carId);
        update.setMake(car.getMake());
        update.setModel(car.getModel());
        update.setPrice(car.getPrice());
        update.setMileage(car.getMileage());
        update.setProductionYear(car.getProductionYear());

        return carRepository.save(update);
    }

    @RequestMapping(method = DELETE, value = "/{carId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long carId) {
        validateCar(carId);
        carRepository.delete(carRepository.getOne(carId));
    }

    private void validateCar(Long carId) {
        if (carRepository.getOne(carId) == null) {
            throw new CarNotFoundException(carId);
        }
    }


}
