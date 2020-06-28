package com.example.curso.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.curso.entity.Curso;

public interface ICursoDao extends JpaRepository<Curso, Long> {

	public List<Curso> findByProfesorId(Long id);

}
