package com.example.utilisateur.Controller;

import com.example.utilisateur.Entity.Utilisateur;
import com.example.utilisateur.Service.UtilisateurService;
import com.example.utilisateur.DTO.PasswordResetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/creerUtilisateur")
    public ResponseEntity<Utilisateur> creerUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdUtilisateur = utilisateurService.createUser(utilisateur);
        return new ResponseEntity<>(createdUtilisateur, HttpStatus.CREATED);
    }

    @PostMapping("/seConnecter")
    public ResponseEntity<Utilisateur> seConnecter(@RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> authenticatedUtilisateur = utilisateurService.seConnecter(utilisateur.getIdentifiant(), utilisateur.getMdp());
        if (authenticatedUtilisateur.isPresent()) {
            return new ResponseEntity<>(authenticatedUtilisateur.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/seDeconnecter")
    public ResponseEntity<Void> seDeconnecter(@RequestParam("identifiant") String identifiant, @RequestParam("mdp") String mdp) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant(identifiant);
        utilisateur.setMdp(mdp);
        utilisateurService.seDeconnecter(utilisateur);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/modifierMotDePasse")
    public ResponseEntity<Utilisateur> modifierMotDePasse(@RequestBody Map<String, String> requestBody) {
        String identifiant = requestBody.get("identifiant");
        String mdp = requestBody.get("mdp");
        String newPassword = requestBody.get("newPassword");

        // Retrieve the existing user from the database
        Utilisateur existingUtilisateur = utilisateurService.seConnecter(identifiant, mdp).orElse(null);

        // Check if the existing user is found
        if (existingUtilisateur != null) {
            // Modify the password of the existing user
            utilisateurService.modifierMotDePasse(existingUtilisateur, newPassword);
            return new ResponseEntity<>(existingUtilisateur, HttpStatus.OK);
        } else {
            // Return error response if the user is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/resetPassword")
    public ResponseEntity<Void> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        utilisateurService.resetPassword(passwordResetRequest.getEmail(), passwordResetRequest.getToken(), passwordResetRequest.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}