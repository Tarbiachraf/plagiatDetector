package ma.ecole.plagiat.controller;

import ma.ecole.plagiat.dtos.StudentDTO;
import ma.ecole.plagiat.reponses.StudentResponse;
import ma.ecole.plagiat.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable String id) {
        StudentResponse studentResponse = studentService.getStudentById(id);
        return ResponseEntity.ok(studentResponse);  // Exception StudentNotFoundException gérée par le service
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentResponse createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO) {
        StudentResponse updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updatedStudent);  // Exception StudentNotFoundException gérée par le service
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudentById(id);  // Exception StudentNotFoundException gérée par le service
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

