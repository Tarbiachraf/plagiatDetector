package ma.ecole.plagiat.exceptions;


import lombok.Getter;
import lombok.NonNull;
import ma.ecole.plagiat.dtos.ApiErrorDetails;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RootException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6378336966214073013L;

    private final HttpStatus httpStatus;
    private final List<ApiErrorDetails> errors = new ArrayList<>();

    public RootException(@NonNull final HttpStatus httpStatus) {
        super();
        this.httpStatus = httpStatus;
    }

    public RootException(@NonNull final HttpStatus httpStatus, final String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}