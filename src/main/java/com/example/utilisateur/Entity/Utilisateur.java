package com.example.utilisateur.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;


@Entity
@AllArgsConstructor


public class Utilisateur {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String identifiant;
        private String mdp;
        @Email(message = "Invalid email format")

        private String email;

    // getters and setters

        public Long getId() {
                return id;
        }

        public String getIdentifiant() {
                return identifiant;
        }

        public String getMdp() {
                return mdp;
        }

        public String getEmail() {
                return email;
        }

        public Utilisateur() {
        }

        public void setId(Long id) {
                this.id = id;
        }

        public void setIdentifiant(String identifiant) {
                this.identifiant = identifiant;
        }



        public void setEmail(String email) {
                this.email = email;


        }

        public void setMdp(String plainTextPassword) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                this.mdp = encoder.encode(plainTextPassword);
        }

}
