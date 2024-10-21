package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.dtos.StudentDTO;
import ma.ecole.plagiat.dtos.TravailDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.reponses.TravailResponse;
import ma.ecole.plagiat.reponses.simpleResponse.SimpleTravailResponse;
import ma.ecole.plagiat.repository.StudentRepository;
import ma.ecole.plagiat.repository.SujetRepository;
import ma.ecole.plagiat.repository.TravailRepository;
import ma.ecole.plagiat.service.StudentService;
import ma.ecole.plagiat.service.SujetService;
import ma.ecole.plagiat.service.TravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TravailMapper {

    @Autowired
    private StudentMapper studentMapper;


    @Autowired
    private SujetRepository sujetRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Mapper Entité -> Réponse (pour le client)
    public TravailResponse toResponse(Travail travail) {
        return new TravailResponse(
                travail.getId(),
                travail.getContenu(),
                travail.getDateSoumission(),
                studentMapper.toResponse(travail.getStudent()),       // Étudiant complet
                SimpleSujetMapper.toSimpleResponse(travail.getSujet()),     // Sujet simplifié
                travail.getGrade(),
                travail.getStatus()
        );
    }

    // Mapper Entité -> SimpleTravailResponse


    // Mapper DTO -> Entité (requête venant du client)
    public Travail toEntity(TravailDTO travailDTO) {
        return new Travail(
                travailDTO.contenu(),
                travailDTO.dateSoumission(),
                studentRepository.findById(travailDTO.studentId()).get(),
                sujetRepository.findById(travailDTO.sujetId()).get(),
                //new Sujet("67085b7ff6331273ac396974","Introduction to AI","Artificial Intelligence","This subject covers the basics of AI.",new Prof()),
                travailDTO.grade(),
                travailDTO.status()
        );
    }
}
