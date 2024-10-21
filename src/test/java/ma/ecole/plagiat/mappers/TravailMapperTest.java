package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.dtos.TravailDTO;
import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.reponses.TravailResponse;
import ma.ecole.plagiat.repository.StudentRepository;
import ma.ecole.plagiat.repository.SujetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TravailMapperTest {
    @InjectMocks
    private TravailMapper travailMapper;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SujetRepository sujetRepository;

    @Mock
    private StudentMapper studentMapper;  // Simuler le mapping des étudiants

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialiser les mocks
    }

    @Test
    void toResponse() {
        Student student = new Student("Achraf", "achraf@example.com", "D137697148", "Informatique", Collections.emptyList(), null);
        student.setId("1");
        Sujet sujet = new Sujet("Mathématiques", "Algèbre", "Introduction à l'algèbre", null, Collections.emptyList());
        sujet.setId("1");
        Travail travail = new Travail("1", "Contenu du travail", LocalDate.now(), student, sujet, 16.0, "Submitted");

        when(studentMapper.toResponse(student)).thenReturn(null);

        TravailResponse response = travailMapper.toResponse(travail);

        // Assert
        assertEquals(travail.getId(), response.id());
        assertEquals(travail.getContenu(), response.contenu());
        assertEquals(travail.getDateSoumission(), response.dateSoumission());
        assertEquals(16.0, response.grade());
        assertEquals("Submitted", response.status());
    }

    @Test
    void toEntity() {
        // Arrange
        Student student = new Student("Achraf", "achraf@example.com", "D137697148", "Informatique", Collections.emptyList(), null);
        student.setId("1");
        Sujet sujet = new Sujet("Mathématiques", "Algèbre", "Introduction à l'algèbre", null, Collections.emptyList());
        sujet.setId("1");
        // Simuler les appels aux repositories
        when(studentRepository.findById("1")).thenReturn(Optional.of(student));
        when(sujetRepository.findById("1")).thenReturn(Optional.of(sujet));

        TravailDTO travailDTO = new TravailDTO("Contenu du travail", LocalDate.now(), "1", "1", 16.0, "Submitted");

        // Act
        Travail travail = travailMapper.toEntity(travailDTO);

        // Assert
        assertEquals(travailDTO.contenu(), travail.getContenu());
        assertEquals(travailDTO.dateSoumission(), travail.getDateSoumission());
        assertEquals(student, travail.getStudent());
        assertEquals(sujet, travail.getSujet());
        assertEquals(16.0, travail.getGrade());
        assertEquals("Submitted", travail.getStatus());
    }
}