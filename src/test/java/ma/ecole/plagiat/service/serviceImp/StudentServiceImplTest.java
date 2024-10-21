package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.dtos.StudentDTO;
import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.reponses.StudentResponse;
import ma.ecole.plagiat.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;
    @Test
    void createStudent() {
        StudentDTO studentDTO = new StudentDTO("achraf","achraf@gmail.com", "D137697148","5ème année");
        StudentResponse studentResponse = studentService.createStudent(studentDTO);
        studentResponse.toString();
        Assertions.assertNotNull(studentResponse);
    }
}