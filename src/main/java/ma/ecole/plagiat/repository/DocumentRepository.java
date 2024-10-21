package ma.ecole.plagiat.repository;

import ma.ecole.plagiat.entities.Documente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface DocumentRepository extends MongoRepository<Documente, String> {
}
