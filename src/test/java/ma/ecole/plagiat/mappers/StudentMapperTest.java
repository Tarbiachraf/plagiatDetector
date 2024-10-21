package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.dtos.ProfDTO;
import ma.ecole.plagiat.dtos.StudentDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.reponses.ProfResponse;
import ma.ecole.plagiat.reponses.StudentResponse;
import ma.ecole.plagiat.repository.ProfRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentMapperTest {

    @InjectMocks
    private StudentMapper studentMapper;
    @Mock
    private ProfRepository profRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void toResponse() {
        Student student = Student.builder()
                .id("1")
                .name("achraf")
                .email("achraf@gmail.com")
                .classe("Info")
                .studentNumber("D137697148")
                .prof(null)
                .plagiatDetectionList(Collections.emptyList())
                .travaux(Collections.emptyList())
                .build();
        StudentResponse studentResponse = studentMapper.toResponse(student);
        assertEquals(student.getId(), studentResponse.id());
        assertEquals(student.getName(), studentResponse.name());
        assertEquals(student.getStudentNumber(), studentResponse.studentNumber());
        assertEquals(student.getEmail(), studentResponse.email());
        assertTrue(studentResponse.travaux().isEmpty());

    }

    @Test
    void toEntity() {
        Prof profMock = new Prof("Professeur X", "prof@example.com", "Informatique", Collections.emptyList(), Collections.emptyList());
        profMock.setId("1");
        when(profRepository.findAll()).thenReturn(List.of(profMock));  // Mocker la méthode findAll

        StudentDTO studentDTO = StudentDTO.builder()
                .name("achraf")
                .studentNumber("D137697148")
                .email("achraf@gmail.com")
                .classe("Info")
                .build();
        Student student = studentMapper.toEntity(studentDTO);
        assertEquals("achraf",student.getName());
        assertEquals("achraf@gmail.com", student.getEmail());
        assertEquals("D137697148", student.getStudentNumber());
        assertEquals("Info", student.getClasse());
        assertTrue(student.getTravaux().isEmpty());
        assertNotNull(student.getProf());

    }
}