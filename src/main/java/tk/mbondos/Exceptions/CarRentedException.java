package tk.mbondos.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class CarRentedException extends RuntimeException {
    public CarRentedException(Long rentalId) {
        super("Car rented. Rental id: " + rentalId);
    }


}
