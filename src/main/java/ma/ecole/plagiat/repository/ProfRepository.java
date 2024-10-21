package ma.ecole.plagiat.repository;

import ma.ecole.plagiat.entities.Prof;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfRepository extends MongoRepository<Prof, String> {
    Optional<Prof> findByEmail(String email);
}
