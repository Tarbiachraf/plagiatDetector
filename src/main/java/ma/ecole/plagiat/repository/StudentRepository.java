package ma.ecole.plagiat.repository;

import ma.ecole.plagiat.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {
}
