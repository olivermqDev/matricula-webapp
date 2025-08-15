package com.cibertec.matriculawebapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data // <-- Genera Getters, Setters, toString, equals, hashCode
@NoArgsConstructor // <-- Genera un constructor sin argumentos
@AllArgsConstructor // <-- Genera un constructor con todos los argumentos
@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "estudiante")
    private List<Matricula> matriculas;
}