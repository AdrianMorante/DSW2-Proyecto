package Proyecto_DSW2.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String email;
	private String password;
	
}