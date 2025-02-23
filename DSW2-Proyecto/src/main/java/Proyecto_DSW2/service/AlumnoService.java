package Proyecto_DSW2.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import Proyecto_DSW2.model.Alumnos;

public interface AlumnoService {
	public ResponseEntity<Map<String, Object>> listarAlumnos();

	public ResponseEntity<Map<String, Object>> listarAlumnoPorId(Long id);

	public ResponseEntity<Map<String, Object>> agregarAlumno(Alumnos alumno);

	public ResponseEntity<Map<String, Object>> editarAlumno(Alumnos alumno, Long id);

	public ResponseEntity<Map<String, Object>> eliminarAlumno(Long id);

}
