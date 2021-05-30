package com.prueba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.prueba.entity.Persona;
import com.prueba.repository.PersonaRepository;

@Service
@Transactional
public class PersonaService {
	@Autowired
	PersonaRepository personaRepository;

	public Optional<Persona> findByNombre(String nombre) {
		return personaRepository.findByNombre(nombre);
	}

	public boolean existsByDocumento(String documento) {
		return personaRepository.existsByDocumento(documento);
	}

	public List<Persona> obtenerTodos() {
		List<Persona> lista = personaRepository.findAll();
		return lista;
	}

	public Optional<Persona> obtenerPorDocumento(String documento) {
		return personaRepository.findById(documento);
	}

	public Optional<Persona> obtenerPorNombre(String nombre) {
		return personaRepository.findById(nombre);
	}

	public void guardar(Persona persona) {
		personaRepository.save(persona);
	}

	public void borrar(String documento) {
		personaRepository.deleteById(documento);
	}

}
