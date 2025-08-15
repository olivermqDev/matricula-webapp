package com.cibertec.matriculawebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.matriculawebapp.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
