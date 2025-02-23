package Proyecto_DSW2.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Proyecto_DSW2.model.Usuario;
import Proyecto_DSW2.repository.UsuarioRepository;

@Service
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository dao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = dao.findOneByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario con dicho email: "+email+ " no existe."));
		
		return new UserDetailImplement(usuario);
	}
	
	
	public ResponseEntity<Map<String, Object>> registrarUsuario(Usuario usuario) {
	    Map<String, Object> respuesta = new HashMap<>();
	    if (dao.findOneByEmail(usuario.getEmail()).isPresent()) {
	        respuesta.put("mensaje", "Este correo ya existe");
	        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
	        respuesta.put("fecha", new Date());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	    }
	    if (!usuario.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
	        respuesta.put("mensaje", "El nombre solo debe contener letras");
	        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
	        respuesta.put("fecha", new Date());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	    }
	    if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
	        respuesta.put("mensaje", "La contraseña es obligatoria");
	        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
	        respuesta.put("fecha", new Date());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	    }
	    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
	    dao.save(usuario);

	    respuesta.put("mensaje", "Usuario registrado exitosamente");
	    respuesta.put("status", HttpStatus.CREATED.value());
	    respuesta.put("fecha", new Date());
	    return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

}
