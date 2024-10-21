package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.dtos.ProfDTO;
import ma.ecole.plagiat.dtos.StudentDTO;
import ma.ecole.plagiat.dtos.SujetDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.reponses.ProfResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class ProfMapper {


    @Autowired
    private StudentMapper studentMapper;

    // Mapper Entité -> Réponse (pour le client)
    public ProfResponse toResponse(Prof prof) {
        return new ProfResponse(
                prof.getId(),
                prof.getName(),
                prof.getEmail(),
                prof.getDepartment(),
                Optional.ofNullable(prof.getSujets()).orElse(Collections.emptyList())
                        .stream()
                        .map(sujetMapper -> SimpleSujetMapper.toSimpleResponse(sujetMapper))            // Sujets simplifiés
                        .collect(Collectors.toList()),
                Optional.ofNullable(prof.getStudents())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(studentMapper::toResponse)
                        .collect(Collectors.toList())
        );
    }

    // Mapper DTO -> Entité (requête venant du client)
    public Prof toEntity(ProfDTO profDTO) {
        return new Prof(
                profDTO.name(),
                profDTO.email(),
                profDTO.department(),
                null,  // Les sujets peuvent être remplis plus tard
                null  // Les étudiants peuvent être remplis plus tard
        );
    }
}

