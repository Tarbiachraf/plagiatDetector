package ma.ecole.plagiat.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TravailNotFoundException extends RootException {
    private static final long serialVersionUID = 1L;

    public TravailNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Travail non trouvé");
    }

    public TravailNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}