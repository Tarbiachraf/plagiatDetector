package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.reponses.simpleResponse.SimpleTravailResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTravailMapperTest {

    @Test
    void toSimpleResponse() {
        // Arrange: Créer un objet Student et Travail
        Student student = new Student("Achraf", "achraf@example.com", "D137697148", "Informatique", Collections.emptyList(), null);
        student.setId("1");
        Travail travail = new Travail("1", "Contenu du travail", LocalDate.now(), student, null, 16.0, "Submitted");

        // Act: Mapper l'objet Travail en SimpleTravailResponse
        SimpleTravailResponse response = SimpleTravailMapper.toSimpleResponse(travail);

        // Assert: Vérifier que les champs sont correctement mappés
        assertEquals(travail.getId(), response.id());
        assertEquals(travail.getContenu(), response.contenu());
        assertEquals(travail.getDateSoumission(), response.dateSoumission());
        assertEquals(travail.getStudent().getId(), response.studentId());  // Vérifier que l'ID de l'étudiant est mappé
        assertEquals(travail.getGrade(), response.grade());
        assertEquals(travail.getStatus(), response.status());
    }
}