package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.reponses.simpleResponse.SimpleTravailResponse;

public class SimpleTravailMapper {
    public static SimpleTravailResponse toSimpleResponse(Travail travail) {
        return new SimpleTravailResponse(
                travail.getId(),
                travail.getContenu(),
                travail.getDateSoumission(),
                travail.getStudent().getId(),    // Utilisation de l'ID de l'étudiant
                travail.getGrade(),
                travail.getStatus()
        );
    }
}
