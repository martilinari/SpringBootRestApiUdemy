package com.example.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entity.Lenguaje;
import com.example.curso.service.ILenguajeService;

@RestController
@RequestMapping("/api")
public class LenguajeRestController {

	@Autowired
	@Qualifier("lenguajeService")
	private ILenguajeService lenguajeService;

	@GetMapping("/lenguajes")
	public ResponseEntity<?> listaLenguajes() {
		List<Lenguaje> listaLenguajes = lenguajeService.findAll();
		if (null != listaLenguajes && listaLenguajes.size() != 0) {
			return new ResponseEntity<>(listaLenguajes, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

}
