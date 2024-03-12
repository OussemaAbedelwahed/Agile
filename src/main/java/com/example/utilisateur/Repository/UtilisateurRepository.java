package com.example.utilisateur.Repository;

import com.example.utilisateur.Entity.Utilisateur;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
    @Query("SELECT u FROM Utilisateur u WHERE u.identifiant = :identifiant AND u.mdp = :mdp")
    Optional<Utilisateur> findByIdentifiantAndMdp(@Param("identifiant") String identifiant, @Param("mdp") String mdp);

}