package mscreative.example.dlvr.repositories;

import mscreative.example.dlvr.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail (String email);
    boolean existsByEmail(String email);
}
