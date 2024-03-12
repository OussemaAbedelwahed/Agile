package com.example.utilisateur.Repository;

import com.example.utilisateur.Entity.PasswordResetToken;
import com.example.utilisateur.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
