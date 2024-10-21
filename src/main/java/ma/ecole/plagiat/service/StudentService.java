package ma.ecole.plagiat.service;

import ma.ecole.plagiat.dtos.StudentDTO;
import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.reponses.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentDTO student);
    StudentResponse getStudentById(String id);
    List<StudentResponse> getAllStudents();
    StudentResponse updateStudent(String id, StudentDTO studentDetails);
    void deleteStudentById(String id);
}
