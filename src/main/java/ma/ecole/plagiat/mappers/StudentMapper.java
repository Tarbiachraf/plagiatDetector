package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.dtos.StudentDTO;
import ma.ecole.plagiat.dtos.TravailDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.reponses.StudentResponse;
import ma.ecole.plagiat.repository.ProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class StudentMapper {
//


    @Autowired
    private ProfRepository profRepository;

    // Mapper Entité -> Réponse (pour le client)
    public StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getStudentNumber(),
                student.getClasse(),
                (student.getTravaux() == null || student.getTravaux().isEmpty()) ?
                        Collections.emptyList() : student.getTravaux().stream()
                        .map(travail ->  SimpleTravailMapper.toSimpleResponse(travail))         // Travaux simplifiés
                        .collect(Collectors.toList())
        );
    }

    // Mapper DTO -> Entité (requête venant du client)
    public Student toEntity(StudentDTO studentDTO) {
        Prof prof = profRepository.findAll().stream().findFirst().get();

        Student student = new Student(
                studentDTO.name(),
                studentDTO.email(),
                studentDTO.studentNumber(),
                studentDTO.classe(),
                Collections.emptyList(),
                prof

        );
        return student;
    }
}
