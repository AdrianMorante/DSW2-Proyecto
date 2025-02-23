package Proyecto_DSW2.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Proyecto_DSW2.model.Alumnos;
import Proyecto_DSW2.repository.AlumnosRepository;
import Proyecto_DSW2.service.AlumnoService;

@Service
public class AlumnosServiceImpl implements AlumnoService {

    @Autowired
    private AlumnosRepository dao;
    private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public ResponseEntity<Map<String, Object>> listarAlumnos() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Alumnos> alumnos = dao.findAll();

        if (!alumnos.isEmpty()) {
            respuesta.put("mensaje", "Lista de alumnos");
            respuesta.put("alumnos", alumnos);
            respuesta.put("status", HttpStatus.OK.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("status", HttpStatus.NOT_FOUND.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarAlumnoPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Alumnos> alumno = dao.findById(id);

        if (alumno.isPresent()) {
            respuesta.put("alumno", alumno.get());
            respuesta.put("mensaje", "Búsqueda correcta");
            respuesta.put("status", HttpStatus.OK.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.ok().body(respuesta);
        } else {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarAlumno(Alumnos alumno) {
        Map<String, Object> respuesta = new HashMap<>();

        String nombreSimbolos = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";

        if (!alumno.getNombre().matches(nombreSimbolos)) {
            respuesta.put("mensaje", "El nombre solo puede contener letras y espacios");
            respuesta.put("status", HttpStatus.BAD_REQUEST.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        if (!alumno.getApellido().matches(nombreSimbolos)) {
            respuesta.put("mensaje", "El apellido solo puede contener letras y espacios");
            respuesta.put("status", HttpStatus.BAD_REQUEST.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        if (alumno.getEdad() < 15 || alumno.getEdad() > 100) {
            respuesta.put("mensaje", "La edad debe estar entre 15 y 100 años");
            respuesta.put("status", HttpStatus.BAD_REQUEST.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        dao.save(alumno);
        respuesta.put("alumno", alumno);
        respuesta.put("mensaje", "Se registró correctamente al alumno");
        respuesta.put("status", HttpStatus.CREATED.value());
        respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarAlumno(Alumnos al, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Alumnos> alumnoExiste = dao.findById(id);

        if (alumnoExiste.isPresent()) {
            Alumnos alumno = alumnoExiste.get();
            String nombreSimbolos = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";

            if (!al.getNombre().matches(nombreSimbolos)) {
                respuesta.put("mensaje", "El nombre solo puede contener letras y espacios");
                respuesta.put("status", HttpStatus.BAD_REQUEST.value());
                respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }

            if (!al.getApellido().matches(nombreSimbolos)) {
                respuesta.put("mensaje", "El apellido solo puede contener letras y espacios");
                respuesta.put("status", HttpStatus.BAD_REQUEST.value());
                respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }

            if (al.getEdad() < 15 || al.getEdad() > 100) {
                respuesta.put("mensaje", "La edad debe estar entre 15 y 100 años");
                respuesta.put("status", HttpStatus.BAD_REQUEST.value());
                respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }

            alumno.setNombre(al.getNombre());
            alumno.setApellido(al.getApellido());
            alumno.setEdad(al.getEdad());
            dao.save(alumno);

            respuesta.put("alumno", alumno);
            respuesta.put("mensaje", "Datos del alumno modificados");
            respuesta.put("status", HttpStatus.CREATED.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } else {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarAlumno(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Alumnos> alumnoExiste = dao.findById(id);

        if (alumnoExiste.isPresent()) {
            dao.deleteById(id);
            respuesta.put("mensaje", "Eliminado correctamente");
            respuesta.put("status", HttpStatus.OK.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND.value());
            respuesta.put("fecha", LocalDateTime.now().format(formatoFecha));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

}
