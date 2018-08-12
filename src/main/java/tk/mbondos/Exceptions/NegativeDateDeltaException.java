package tk.mbondos.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class NegativeDateDeltaException extends RuntimeException {
    public NegativeDateDeltaException() {
        super("ReturnDate must be after pickupDate");
    }


}
