package ma.ecole.plagiat.repository;

import ma.ecole.plagiat.entities.PlagiatDetection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlagiatDetectionRepository extends MongoRepository<PlagiatDetection, String> {

}
