package ma.ecole.plagiat.repository;

import ma.ecole.plagiat.entities.Sujet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SujetRepository extends MongoRepository<Sujet, String> {

}
