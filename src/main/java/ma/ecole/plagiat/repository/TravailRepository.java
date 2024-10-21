package ma.ecole.plagiat.repository;

import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.entities.Travail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TravailRepository extends MongoRepository<Travail, String> {

    List<Travail> findAllBySujetId(String id);

    List<Travail> findAllByStudentId(String id);

    List<Travail> findBySujetId(String id);

    Travail findBySujetAndStudent(Sujet sujet, Student student);
}
