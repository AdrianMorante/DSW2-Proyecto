package Proyecto_DSW2.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import Proyecto_DSW2.model.Auth;
import Proyecto_DSW2.serviceImpl.UserDetailImplement;
import Proyecto_DSW2.util.Token;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		Auth authCredenciales = new Auth();
		
		try {
			authCredenciales = new ObjectMapper().readValue(request.getReader(), Auth.class);
		} catch (Exception e) {
		}
		
		UsernamePasswordAuthenticationToken userPAT = new UsernamePasswordAuthenticationToken(
				authCredenciales.getEmail(),
				authCredenciales.getPassword(),
				Collections.emptyList()
				);
		
		return getAuthenticationManager().authenticate(userPAT);
	}
	
	 @Override
	    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
	        String email = authResult.getName();
	        String token = Token.crearToken(email, email);
	        
	        response.setHeader("Authorization", "Bearer " + token);
	        
	        Map<String, Object> respuesta = new HashMap<>();
	        respuesta.put("mensaje", "Ha recibido el token correctamente");
	        respuesta.put("status", 200);

	        response.setContentType("application/json"); 
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(new ObjectMapper().writeValueAsString(respuesta));
	    }

	    @Override
	    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
	        Map<String, Object> respuesta = new HashMap<>();
	        respuesta.put("mensaje", "El correo o contraseña son inválidos");
	        respuesta.put("status", 401);

	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(new ObjectMapper().writeValueAsString(respuesta));
	    }

}
