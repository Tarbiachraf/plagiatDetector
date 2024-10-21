package ma.ecole.plagiat.reponses;

import ma.ecole.plagiat.reponses.simpleResponse.SimpleTravailResponse;

import java.io.Serializable;
import java.util.List;

public record SujetResponse(
        String id,
        String titre,
        String matiere,
        String description,
        ProfResponse prof,
        List<SimpleTravailResponse> travaux
)  implements Serializable {}
