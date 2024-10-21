package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.reponses.simpleResponse.SimpleSujetResponse;

public class SimpleSujetMapper {
    public static SimpleSujetResponse toSimpleResponse(Sujet sujet) {
        return new SimpleSujetResponse(
                sujet.getId(),
                sujet.getTitre(),
                sujet.getMatiere(),
                sujet.getDescription()
        );
    }
}
