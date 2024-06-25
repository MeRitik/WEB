package com.ritik.scm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ritik.scm.entities.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Custom methods

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
