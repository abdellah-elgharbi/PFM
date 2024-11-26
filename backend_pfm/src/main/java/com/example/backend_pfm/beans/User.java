package com.example.backend_pfm.beans;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nom;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String hashedPassword;
    @NotNull
    private String numero;
}
