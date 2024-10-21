package ma.ecole.plagiat.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class PlagiatNotFoundException extends RootException{
    @Serial
    private static final long serialVersionUID = 1L;

    public PlagiatNotFoundException(String profId) {
        super(HttpStatus.NOT_FOUND, String.format("PlagiatDetection avec ID %s introuvable.", profId));
    }
}
