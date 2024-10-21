package ma.ecole.plagiat.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ProfNotFoundException extends RootException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProfNotFoundException() {
        super(HttpStatus.NOT_FOUND, String.format("Professeur introuvable."));
    }
}
