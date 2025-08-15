package com.cibertec.matriculawebapp.controller;


import com.cibertec.matriculawebapp.model.Matricula;
import com.cibertec.matriculawebapp.repository.CursoRepository;
import com.cibertec.matriculawebapp.repository.EstudianteRepository;
import com.cibertec.matriculawebapp.repository.MatriculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDate;

@Controller
public class MatriculaController {

    @Autowired private MatriculaRepository matriculaRepository;
    @Autowired private EstudianteRepository estudianteRepository;
    @Autowired private CursoRepository cursoRepository;

    // Muestra el formulario para realizar una nueva matrícula
    @GetMapping("/matriculas/nueva")
    public String mostrarFormularioDeMatricula(Model model) {
        // Obtenemos todos los estudiantes y cursos para los dropdowns
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        model.addAttribute("matricula", new Matricula());
        return "formulario-matricula";
    }

    // Procesa el guardado de la nueva matrícula
    @PostMapping("/matriculas")
    public String guardarMatricula(@ModelAttribute("matricula") Matricula matricula) {
        matricula.setFechaMatricula(LocalDate.now());
        matriculaRepository.save(matricula);
        // Redirigimos a los detalles del estudiante para ver la nueva matrícula
        return "redirect:/estudiantes/detalle/" + matricula.getEstudiante().getId();
    }
}