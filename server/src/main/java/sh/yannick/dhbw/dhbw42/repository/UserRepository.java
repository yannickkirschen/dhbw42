package sh.yannick.dhbw.dhbw42.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sh.yannick.dhbw.dhbw42.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByDisplayName(String name);
}
