package com.cibertec.matriculawebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.matriculawebapp.model.Curso;
import com.cibertec.matriculawebapp.model.Estudiante;
import com.cibertec.matriculawebapp.repository.EstudianteRepository;
import com.cibertec.matriculawebapp.repository.MatriculaRepository;

@Controller // Marca esta clase como un controlador web de Spring.
public class EstudianteController {

    @Autowired // Inyección de dependencias: Spring nos da una instancia del repositorio.
    private EstudianteRepository estudianteRepository;
    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/estudiantes"; 
    }
    // --- READ: Muestra la lista de todos los estudiantes ---
    @GetMapping("/estudiantes")
    public String listarEstudiantes(Model model) {
        // Model es el objeto que usamos para pasar datos del controlador a la vista.
        model.addAttribute("listaEstudiantes", estudianteRepository.findAll());
        return "listado-estudiantes"; // Devuelve el nombre del archivo HTML a renderizar.
    }

    // --- READ DETAILS: Muestra los detalles de un estudiante y sus cursos matriculados ---
    @GetMapping("/estudiantes/detalle/{id}")
    public String verDetalleEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id de estudiante inválido:" + id));

        // Usamos nuestra consulta personalizada para obtener los cursos
        List<Curso> cursosMatriculados = matriculaRepository.findCursosByEstudianteId(id);
    
        model.addAttribute("estudiante", estudiante);
        model.addAttribute("cursos", cursosMatriculados);
    
        return "detalle-estudiante"; // Necesitaremos crear esta nueva vista
    }


    // --- CREATE (Paso 1): Muestra el formulario para un nuevo estudiante ---
    @GetMapping("/estudiantes/nuevo")
    public String mostrarFormularioDeNuevoEstudiante(Model model) {
        Estudiante estudiante = new Estudiante();
        model.addAttribute("estudiante", estudiante);
        return "formulario-estudiante";
    }

    // --- CREATE (Paso 2): Procesa y guarda el estudiante del formulario ---
    @PostMapping("/estudiantes")
    public String guardarEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {
        // @ModelAttribute vincula los datos del formulario al objeto Estudiante.
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes"; // Redirige al usuario de vuelta a la lista.
    }

    // --- UPDATE (Paso 1): Muestra el formulario para editar un estudiante existente ---
    @GetMapping("/estudiantes/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        // @PathVariable obtiene el 'id' de la URL.
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id de estudiante inválido:" + id));
        model.addAttribute("estudiante", estudiante);
        return "formulario-estudiante"; // Reutilizamos el mismo formulario.
    }
    
    // --- UPDATE (Paso 2): Procesa y guarda los cambios del estudiante editado ---
    // NOTA: Este método se fusiona con guardarEstudiante. El save() de JPA es inteligente:
    // si el objeto tiene un ID, hace un UPDATE; si no lo tiene, hace un INSERT.

    // --- DELETE: Elimina un estudiante ---
    @GetMapping("/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteRepository.deleteById(id);
        return "redirect:/estudiantes";
    }
}