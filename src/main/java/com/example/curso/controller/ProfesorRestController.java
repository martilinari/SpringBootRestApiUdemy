package com.example.curso.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entity.Profesor;
import com.example.curso.mapper.Mapper;
import com.example.curso.model.MProfesor;
import com.example.curso.service.IProfesorService;

@RestController
@RequestMapping("/api")
public class ProfesorRestController {

	@Autowired
	@Qualifier("profesorService")
	private IProfesorService profesorService;

	@Autowired
	@Qualifier("mapper")
	private Mapper mapper;

	@GetMapping("/profesores")
	@ResponseStatus(HttpStatus.OK)
	public List<Profesor> getProfesores() {
		return profesorService.findAll();

	}

	@PostMapping("/find_profesor")
	public ResponseEntity<?> findProfesor(@RequestBody Profesor profesor) {
		Profesor profesorDb = profesorService.findProfesor(profesor);
		if (null != profesorDb) {
			return new ResponseEntity<>(profesorDb, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/sign_up")
	public ResponseEntity<Void> addProfesor(@RequestBody Profesor profesor) {
		if (profesorService.findProfesor(profesor) == null) {
			profesorService.save(profesor);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginProfesor(@RequestBody Profesor profesor) {
		Profesor profesorDb = profesorService.checkProfesorLogin(profesor);
		if (null != profesorDb) {
			List<Profesor> profesores = new ArrayList<Profesor>();
			List<MProfesor> mProfesores = new ArrayList<MProfesor>();
			profesores.add(profesorDb);
			mProfesores = mapper.convertirLista(profesores);
			return new ResponseEntity<>(mProfesores, HttpStatus.OK);

		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProfesor(@PathVariable(value = "id") Long id, @RequestBody Profesor profesor) {
		Profesor profesorDB = null;
		profesorDB = profesorService.findById(id);
		if (null != profesorDB) {
			profesorDB.setEmail(profesor.getEmail());
			profesorDB.setName(profesor.getName());
			profesorService.updateProfesor(profesorDB);
			return new ResponseEntity<>(profesorDB, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update_sql")
	public ResponseEntity<?> updateProfesorSql(@RequestBody Profesor profesor) {
		Profesor profesorDB = null;
		profesorDB = profesorService.findByIdSQL(profesor.getId());
		if (null != profesorDB) {
			profesorDB.setEmail(profesor.getEmail());
			profesorDB.setName(profesor.getName());
			profesorService.updateProfesor(profesorDB);
			return new ResponseEntity<>(profesorDB, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProfesor(@PathVariable(value = "id") Long id) {
		Profesor profesorDB = null;
		profesorDB = profesorService.findById(id);
		if (null != profesorDB) {
			profesorService.deleteProfesor(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteAllProfesor() {
		profesorService.deleteAllProfesor();
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@PostMapping("/deletePost")
	public ResponseEntity<Void> deleteProfesorPost(@RequestBody Profesor profesor) {
		if (null != profesorService.findProfesor(profesor)) {
			profesorService.deleteProfesor(profesor);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
