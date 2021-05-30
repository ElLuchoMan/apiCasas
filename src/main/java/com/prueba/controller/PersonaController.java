package com.prueba.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.prueba.DTO.Mensaje;
import com.prueba.entity.Casa;
import com.prueba.entity.Persona;
import com.prueba.service.CasaService;
import com.prueba.service.PersonaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "*")
public class PersonaController {

	@Autowired
	PersonaService personaService;
	@GetMapping("/")
	@ApiOperation(value="Método que trae la lista de las personas registradas")
	public ResponseEntity<List<Persona>> getLista() {
		List<Persona> lista = personaService.obtenerTodos();
		return new ResponseEntity<List<Persona>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{documento}")
	@ApiOperation(value="Método que trae una persona mediante su documento")
	public ResponseEntity<Persona> getOne(@PathVariable String documento) {
		if (!personaService.existsByDocumento(documento))
			return new ResponseEntity(new Mensaje("No existe una persona con ese documento"), HttpStatus.NOT_FOUND);
		Persona persona = personaService.obtenerPorDocumento(documento).get();
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}

	@PostMapping("/nuevo")
	@ApiOperation(value="Método que permite registrar a una persona")
	public ResponseEntity<?> create(@RequestBody Persona persona, Casa casa) {
		if (StringUtils.isBlank(persona.getDocumento()))
			return new ResponseEntity(new Mensaje("El documento es obligatorio"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(persona.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (personaService.existsByDocumento(persona.getNombre()))
			return new ResponseEntity(new Mensaje("Ese documento ya está registrado"), HttpStatus.BAD_REQUEST);
		personaService.guardar(persona);
		return new ResponseEntity(new Mensaje("Persona guardada"), HttpStatus.CREATED);
	}

	@PutMapping("/actualizar/{documento}")
	@ApiOperation(value="Método que permite actualizar una persona mediante su documento")
	public ResponseEntity<?> update(@RequestBody Persona persona, @PathVariable("documento") String documento) {
		if (!personaService.existsByDocumento(documento))
			return new ResponseEntity(new Mensaje("No existe esa persona"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(persona.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Persona personaUpdate = personaService.obtenerPorDocumento(documento).get();
		personaUpdate.setNombre(persona.getNombre());
		personaUpdate.setEdad(persona.getEdad());
		personaUpdate.setEdad(persona.getEdad());
		personaService.guardar(personaUpdate);
		return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.CREATED);
	}

	@DeleteMapping("/borrar/{documento}")
	@ApiOperation(value="Método que permite eliminar una persona mediante su documento")
	public ResponseEntity<?> delete(@PathVariable String documento) {
		if (!personaService.existsByDocumento(documento))
			return new ResponseEntity(new Mensaje("No existe una persona con ese documento"), HttpStatus.NOT_FOUND);
		personaService.borrar(documento);
		return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
	}
}
