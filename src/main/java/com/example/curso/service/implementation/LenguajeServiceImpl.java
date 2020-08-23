package com.example.curso.service.implementation;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso.dao.ILenguajeDao;
import com.example.curso.entity.Lenguaje;
import com.example.curso.service.ILenguajeService;

@Service("lenguajeService")
public class LenguajeServiceImpl implements ILenguajeService {

	@Autowired
	private ILenguajeDao lenguajeDao;

	@Override
	@Transactional(readOnly = true)
	public List<Lenguaje> findAll() {
		return (List<Lenguaje>) lenguajeDao.findAll();
	}

	@Override
	@Transactional
	public void saveLenguaje(Lenguaje lenguaje) {
		lenguajeDao.save(lenguaje);
	}

	@Override
	public Lenguaje findLenguajeById(Long id) {
		return lenguajeDao.findByIdSQL(id);
	}

}
