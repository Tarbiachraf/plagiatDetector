package ma.ecole.plagiat.reponses.simpleResponse;

import java.io.Serializable;

public record SimpleSujetResponse(
        String id,
        String titre,
        String matiere,
        String description
)  implements Serializable {}