package Proyecto_DSW2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Proyecto_DSW2.model.Alumnos;

@Repository
public interface AlumnosRepository extends JpaRepository<Alumnos, Long>{
}
