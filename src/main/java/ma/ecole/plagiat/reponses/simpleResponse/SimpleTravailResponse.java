package ma.ecole.plagiat.reponses.simpleResponse;

import java.io.Serializable;
import java.time.LocalDate;

public record SimpleTravailResponse(
        String id,
        String contenu,
        LocalDate dateSoumission,
        String studentId,                // Utilisation uniquement de l'ID de l'étudiant
        Double grade,
        String status
)  implements Serializable {}