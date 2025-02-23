package Proyecto_DSW2.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto_DSW2.model.Alumnos;
import Proyecto_DSW2.serviceImpl.AlumnosServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnosController {

	@Autowired
	private AlumnosServiceImpl service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listar(){
		return service.listarAlumnos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listaPorID(@PathVariable Long id){
		return service.listarAlumnoPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@Valid @RequestBody Alumnos alumno){
		return service.agregarAlumno(alumno);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editar(@RequestBody Alumnos alumno, @PathVariable Long id){
		return service.editarAlumno(alumno, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
		return service.eliminarAlumno(id);
	}

}

