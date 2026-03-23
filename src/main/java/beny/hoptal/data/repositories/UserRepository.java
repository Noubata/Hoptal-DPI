package beny.hoptal.data.repositories;

import beny.hoptal.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNomUtilisateur(String username);
}
