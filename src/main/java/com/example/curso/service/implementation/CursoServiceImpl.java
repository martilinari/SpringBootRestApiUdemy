package com.example.curso.service.implementation;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso.dao.ICursoDao;
import com.example.curso.entity.Curso;
import com.example.curso.service.ICursoService;

@Service("cursoService")
public class CursoServiceImpl implements ICursoService {

	@Autowired
	private ICursoDao cursoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		return (List<Curso>) cursoDao.findAll();
	}

	@Override
	public void saveCurso(Curso curso) {
		cursoDao.save(curso);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Curso> getCursosProfesor(Long id) {
		return (List<Curso>) cursoDao.findByProfesorId(id);
	}

}
