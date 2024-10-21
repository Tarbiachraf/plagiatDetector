package ma.ecole.plagiat.mappers;

import com.sun.istack.Nullable;
import ma.ecole.plagiat.dtos.ProfDTO;
import ma.ecole.plagiat.dtos.SujetDTO;
import ma.ecole.plagiat.dtos.TravailDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.reponses.SujetResponse;
import ma.ecole.plagiat.reponses.simpleResponse.SimpleSujetResponse;
import ma.ecole.plagiat.repository.ProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class SujetMapper {

//    @Autowired
//    private ProfMapper profMapper;
    @Autowired
    private ProfRepository profRepository;


    // Mapper Entité -> Réponse (pour le client)
    public SujetResponse toResponse(Sujet sujet) {
        return new SujetResponse(
                sujet.getId(),
                sujet.getTitre(),
                sujet.getMatiere(),
                sujet.getDescription(),
                null,
                (sujet.getTravaux()  == null || sujet.getTravaux().isEmpty()) ?
                        null :
                sujet.getTravaux().stream()
                        .map(travail ->  SimpleTravailMapper.toSimpleResponse(travail))          // Travaux simplifiés
                        .collect(Collectors.toList())
        );
    }

    // Mapper Entité -> SimpleSujetResponse


    // Mapper DTO -> Entité (requête venant du client)
    public Sujet toEntity(SujetDTO sujetDTO) {
        Prof prof = profRepository.findAll().stream().findFirst().get();
        return new Sujet(
                sujetDTO.titre(),
                sujetDTO.matiere(),
                sujetDTO.description(),
                prof,  // Le prof peut être rempli plus tard
                List.of()   // Les travaux peuvent être remplis plus tard
        );
    }
}
