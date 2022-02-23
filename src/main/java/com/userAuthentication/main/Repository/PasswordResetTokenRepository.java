package com.userAuthentication.main.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userAuthentication.main.model.PasswordResetToken;



public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}
