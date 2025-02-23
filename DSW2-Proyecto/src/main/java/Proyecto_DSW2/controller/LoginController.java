package Proyecto_DSW2.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto_DSW2.model.Usuario;
import Proyecto_DSW2.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
public class LoginController {
	@Autowired
    private UserServiceImpl userService;

    @PostMapping("/registro")
    public ResponseEntity<Map<String, Object>> registrarUsuario(@RequestBody Usuario usuario) {
        return userService.registrarUsuario(usuario);
    }
}
