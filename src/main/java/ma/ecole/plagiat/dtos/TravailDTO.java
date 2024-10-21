package ma.ecole.plagiat.dtos;

import java.time.LocalDate;


public record TravailDTO(
        String contenu,
        LocalDate dateSoumission,
        String studentId,
        String sujetId,
        Double grade,
        String status
) {

}
