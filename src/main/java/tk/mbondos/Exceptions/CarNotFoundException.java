package tk.mbondos.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long carId) {
        super("Car with id: " + carId + " does not exist.");
    }


}
