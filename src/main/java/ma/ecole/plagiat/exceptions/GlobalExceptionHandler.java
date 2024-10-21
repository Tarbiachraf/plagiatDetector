package ma.ecole.plagiat.exceptions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ecole.plagiat.dtos.ApiErrorDetails;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {


    @ExceptionHandler(RootException.class)
    public ResponseEntity<ProblemDetail> rootException(final RootException ex, WebRequest request) {
        log.info(ex.getMessage(), ex);

        final ProblemDetail problemDetail = this.createProblemDetail(
                ex,                                      // Exception levée
                ex.getHttpStatus(),                      // Statut HTTP associé à l'exception
                ex.getMessage(),                         // Détail par défaut
                null,
                null,
                request                                  // La requête Web
        );

        return ResponseEntity.status(ex.getHttpStatus()).body(problemDetail);
    }


}
