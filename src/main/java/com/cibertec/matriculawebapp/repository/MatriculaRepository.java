package com.cibertec.matriculawebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.matriculawebapp.model.Matricula;
import com.cibertec.matriculawebapp.model.Curso;
import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    /**
     * Consulta JPQL para encontrar todos los cursos en los que un estudiante específico está matriculado.
     * "SELECT m.curso": Selecciona solo el objeto Curso de la matrícula.
     * "FROM Matricula m": Opera sobre la entidad Matricula, a la que llamamos 'm'.
     * "WHERE m.estudiante.id = :idEstudiante": Filtra por el ID del estudiante contenido en la matrícula.
     * ":idEstudiante" es un parámetro que pasaremos al método.
     */
    @Query("SELECT m.curso FROM Matricula m WHERE m.estudiante.id = :idEstudiante")
    List<Curso> findCursosByEstudianteId(@Param("idEstudiante") Long idEstudiante);
}