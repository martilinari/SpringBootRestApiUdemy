package com.example.curso.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entity.Lenguaje;
import com.example.curso.entity.Profesor;
import com.example.curso.model.ProfesorLenguaje;
import com.example.curso.service.ILenguajeService;
import com.example.curso.service.IProfesorService;

@RestController
@RequestMapping("/api")
public class ProfesorLenguajeRestController {

	@Autowired
	@Qualifier("lenguajeService")
	private ILenguajeService lenguajeService;

	@Autowired
	@Qualifier("profesorService")
	private IProfesorService profesorService;

	@PostMapping("/lenguajes_profesor")
	public ResponseEntity<?> listLenguajesProfesor(@RequestBody Profesor profesor) {
		Profesor profesorDb = profesorService.findById(profesor.getId());
		if (null != profesorDb) {
			Collection<Lenguaje> listLenguaje = profesorDb.getLenguajes();
			if (null != listLenguaje && listLenguaje.size() != 0) {
				return new ResponseEntity<>(listLenguaje, HttpStatus.OK);
			}

		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/save_lenguaje_profesor")
	public ResponseEntity<?> agregarLenguaje(@RequestBody ProfesorLenguaje profesorLenguaje) {
		Profesor profesorDb = profesorService.findById(profesorLenguaje.getProfesor().getId());
		if (null != profesorDb) {
			Lenguaje lenguajeDb = lenguajeService.findLenguajeById(profesorLenguaje.getLenguaje().getId());
			if (null != profesorDb) {
				profesorDb.addLenguaje(lenguajeDb);
				profesorService.save(profesorDb);
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
