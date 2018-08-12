package tk.mbondos.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class CustomerRentingException extends RuntimeException {
    public CustomerRentingException(Long rentalId) {
        super("Can not delete customer with rentals. Rental id: " + rentalId);
    }
}
