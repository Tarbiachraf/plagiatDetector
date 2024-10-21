package ma.ecole.plagiat.reponses;

import ma.ecole.plagiat.reponses.simpleResponse.SimpleTravailResponse;

import java.io.Serializable;
import java.util.List;

public record StudentResponse(
        String id,
        String name,
        String email,
        String studentNumber,
        String classe,
        List<SimpleTravailResponse> travaux
)  implements Serializable {}
