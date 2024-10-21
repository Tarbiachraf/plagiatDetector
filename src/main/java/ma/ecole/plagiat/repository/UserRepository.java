package ma.ecole.plagiat.repository;

import ma.ecole.plagiat.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String s);
}
