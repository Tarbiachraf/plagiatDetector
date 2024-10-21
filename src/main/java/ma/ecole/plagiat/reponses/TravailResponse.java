package ma.ecole.plagiat.reponses;

import ma.ecole.plagiat.reponses.simpleResponse.SimpleSujetResponse;

import java.io.Serializable;
import java.time.LocalDate;

public record TravailResponse(
        String id,
        String contenu,
        LocalDate dateSoumission,
        StudentResponse student,
        SimpleSujetResponse sujet,
        Double grade,
        String status
)  implements Serializable {}
