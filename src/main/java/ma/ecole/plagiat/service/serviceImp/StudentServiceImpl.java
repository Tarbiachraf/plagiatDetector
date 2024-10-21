package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.dtos.StudentDTO;
import ma.ecole.plagiat.dtos.TravailDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.exceptions.ProfNotFoundException;
import ma.ecole.plagiat.exceptions.StudentNotFoundException;
import ma.ecole.plagiat.mappers.ProfMapper;
import ma.ecole.plagiat.mappers.StudentMapper;
import ma.ecole.plagiat.mappers.TravailMapper;
import ma.ecole.plagiat.reponses.StudentResponse;
import ma.ecole.plagiat.repository.ProfRepository;
import ma.ecole.plagiat.repository.StudentRepository;
import ma.ecole.plagiat.repository.TravailRepository;
import ma.ecole.plagiat.service.ProfService;
import ma.ecole.plagiat.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ProfService profService;

    @Autowired
    private ProfRepository profRepository;

    @Override
    public StudentResponse getStudentById(String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return studentMapper.toResponse(student);
    }



    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(studentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public StudentResponse createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        Prof prof = profRepository.findAll().stream().findFirst().orElseThrow(()->new ProfNotFoundException());
        if (prof.getStudents() == null) {
            prof.setStudents(new ArrayList<>());
        }

        Student savedStudent = studentRepository.save(student);
        prof.getStudents().add(savedStudent);
        Prof savedProf = profRepository.save(prof);

        return studentMapper.toResponse(savedStudent);
    }

    @Override
    public StudentResponse updateStudent(String id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setName(studentDTO.name());
        student.setEmail(studentDTO.email());
        student.setStudentNumber(studentDTO.studentNumber());
        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toResponse(updatedStudent);
    }

    @Override
    public void deleteStudentById(String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
    }
}
