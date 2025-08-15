package com.cibertec.matriculawebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.matriculawebapp.model.Estudiante;

// Al extender JpaRepository<Estudiante, Long>, obtenemos todo el CRUD para la entidad Estudiante.
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}