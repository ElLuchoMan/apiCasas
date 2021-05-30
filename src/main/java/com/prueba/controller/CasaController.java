package com.prueba.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.DTO.Mensaje;
import com.prueba.entity.Casa;
import com.prueba.entity.Persona;
import com.prueba.service.CasaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/casas")
@CrossOrigin(origins = "*")
public class CasaController {

	@Autowired
	CasaService casaService;

	@GetMapping("/")
	@ApiOperation(value = "Método que trae la lista de todas las casas ingresadas")
	public ResponseEntity<List<Casa>> getLista() {
		List<Casa> lista = casaService.obtenerTodos();
		return new ResponseEntity<List<Casa>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Método que trae una casa por ID")
	public ResponseEntity<Casa> getOne(@PathVariable Long id) {
		if (!casaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe una casa con ese id"), HttpStatus.NOT_FOUND);
		Casa casa = casaService.findById(id).get();
		return new ResponseEntity<Casa>(casa, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Método que trae una casa por direccion")
	public List<Casa> encontrarDireccion(@RequestParam("direccion") String direccion) {
		return casaService.encontrarDireccion(direccion);
	}

	@PostMapping("/nueva")
	@ApiOperation(value = "Método que permite registar una Casa")
	public ResponseEntity<?> create(@RequestBody Casa casa) {
		if (StringUtils.isBlank(casa.getDireccion()))
			return new ResponseEntity(new Mensaje("La dirección es obligatoria"), HttpStatus.BAD_REQUEST);
		if (casaService.existsByDireccion(casa.getDireccion()))
			return new ResponseEntity(new Mensaje("Esa dirección ya está registrada"), HttpStatus.BAD_REQUEST);
		if (casaService.existsByTelefono(casa.getTelefono()))
			return new ResponseEntity(new Mensaje("Ese telefono ya está registrado"), HttpStatus.BAD_REQUEST);
		casaService.save(casa);
		return new ResponseEntity(new Mensaje("Casa guardada"), HttpStatus.CREATED);
	}

	@PutMapping("/actualizar/{id}")
	@ApiOperation(value = "Método que permite actualizar una casa mediante su ID")
	public ResponseEntity<?> update(@RequestBody Casa casa, @PathVariable("id") Long id) {
		if (StringUtils.isBlank(casa.getDireccion()))
			return new ResponseEntity(new Mensaje("La dirección es obligatoria"), HttpStatus.BAD_REQUEST);
		if (!casaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe esa casa"), HttpStatus.NOT_FOUND);
		Casa casaUpdate = casaService.findById(id).get();
		casaUpdate.setDireccion(casa.getDireccion());
		casaUpdate.setTelefono(casa.getTelefono());
		casaUpdate.setId(casa.getId());
		return new ResponseEntity(new Mensaje("Casa actualizada"), HttpStatus.CREATED);
	}

	@DeleteMapping("/borrar/{id}")
	@ApiOperation(value = "Método que permite eliminar una casa")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!casaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe esa casa"), HttpStatus.NOT_FOUND);
		casaService.borrar(id);
		return new ResponseEntity(new Mensaje("Casa eliminada"), HttpStatus.OK);
	}
}
