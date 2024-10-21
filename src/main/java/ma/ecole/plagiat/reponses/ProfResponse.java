package ma.ecole.plagiat.reponses;

import ma.ecole.plagiat.reponses.simpleResponse.SimpleSujetResponse;

import java.io.Serializable;
import java.util.List;

public record ProfResponse(
        String id,
        String name,
        String email,
        String department,
        List<SimpleSujetResponse> sujets,  // Sujets publiés par le professeur
        List<StudentResponse> students
) implements Serializable {}
