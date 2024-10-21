package ma.ecole.plagiat.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SujetNotFoundException extends RootException {
    private static final long serialVersionUID = 1L;

    public SujetNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Sujet non trouvé");
    }

    public SujetNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}