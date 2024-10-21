package ma.ecole.plagiat.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record StudentDTO(

        String name,
        String email,
        String studentNumber,
        String classe
) {}
