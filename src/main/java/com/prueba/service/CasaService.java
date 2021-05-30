package com.prueba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.entity.Casa;
import com.prueba.entity.Persona;
import com.prueba.repository.CasaRepository;

@Service
@Transactional
public class CasaService {
	@Autowired
	CasaRepository casaRepository;

	public Optional<Casa> findById(Long id) {
		return casaRepository.findById(id);
	}

	public boolean existsById(Long id) {
		return casaRepository.existsById(id);
	}

	public List<Casa> obtenerTodos() {
		List<Casa> lista = casaRepository.findAll();
		return lista;
	}

	public boolean existsByDireccion(String direccion) {
		return casaRepository.existsByDireccion(direccion);
	}

	public boolean existsByTelefono(Long telefono) {
		return casaRepository.existsByTelefono(telefono);
	}

	public Optional<Casa> findByDireccion(String direccion) {
		return casaRepository.findById(direccion);
	}
	public List<Casa> encontrarDireccion(String direccion) {
		return casaRepository.encontrarDireccion(direccion);
	}
	@Transactional
	public Casa save(Casa casa) {
		return casaRepository.save(casa);
	}

	public void borrar(Long id) {
		casaRepository.deleteById(id);
	}

}
