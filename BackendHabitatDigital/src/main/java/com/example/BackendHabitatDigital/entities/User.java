package com.example.BackendHabitatDigital.entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    public String name;
    public String email;
    private String password;
    private int contactNumber;
}
