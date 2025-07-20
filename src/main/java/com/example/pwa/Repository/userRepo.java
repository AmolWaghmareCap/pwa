package com.example.pwa.Repository;

import com.example.pwa.Entity.User;
import com.example.pwa.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
