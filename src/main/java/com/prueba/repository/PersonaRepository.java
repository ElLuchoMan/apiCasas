package com.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.entity.Persona;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {
	Optional<Persona> findByNombre(String nombre);

	boolean existsByDocumento(String documento);
}