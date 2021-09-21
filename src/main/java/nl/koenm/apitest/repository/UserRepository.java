package nl.koenm.apitest.repository;

import nl.koenm.apitest.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByUsername(String user_name);
    boolean existsByUsernameIgnoreCase(String name);

}
