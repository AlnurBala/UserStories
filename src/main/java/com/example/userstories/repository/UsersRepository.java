package com.example.userstories.repository;

import com.example.userstories.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmailAddress(String emailAddress);

}
