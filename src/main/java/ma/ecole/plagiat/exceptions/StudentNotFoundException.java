package ma.ecole.plagiat.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StudentNotFoundException extends RootException {
    private static final long serialVersionUID = 1L;

    public StudentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Étudiant non trouvé");
    }

    public StudentNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}