package com.example.utilisateur.Service;

import com.example.utilisateur.Entity.PasswordResetToken;
import com.example.utilisateur.Entity.Utilisateur;
import com.example.utilisateur.Repository.PasswordResetTokenRepository;
import com.example.utilisateur.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public Utilisateur createUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> seConnecter(String identifiant, String mdp) {
        return utilisateurRepository.findByIdentifiantAndMdp(identifiant, mdp);
    }

    public void seDeconnecter(Utilisateur utilisateur) {
        // implementation
    }

    public void modifierMotDePasse(Utilisateur utilisateur, String newMdp) {
        utilisateur.setMdp(newMdp);
        utilisateurRepository.save(utilisateur);
    }

    public void resetPassword(String email, String token, String newPassword) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur != null) {
            PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
            if (passwordResetToken != null && passwordResetToken.getUtilisateur() != null && passwordResetToken.getUtilisateur().equals(utilisateur)) {
                utilisateur.setMdp(newPassword);
                utilisateurRepository.save(utilisateur);
                passwordResetToken.setUtilisateur(null);
                passwordResetTokenRepository.save(passwordResetToken);
            }
        }
    }
}