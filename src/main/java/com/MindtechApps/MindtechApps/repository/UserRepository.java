package com.MindtechApps.MindtechApps.repository;

import com.MindtechApps.MindtechApps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(Long id);

    Optional<User> getUserByEmail(String email);
}
