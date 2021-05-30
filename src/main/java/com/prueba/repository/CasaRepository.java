package com.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.entity.Casa;
import com.prueba.entity.Persona;

import java.util.List;
import java.util.Optional;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Long> {
	Optional<Casa> findById(String id);

	@Query("FROM Casa e WHERE e.direccion=:direccion")
	public List<Casa> encontrarDireccion(@Param("direccion") String direccion);

	boolean existsById(Long id);

	boolean existsByDireccion(String direccion);

	boolean findByDireccion(String direccion);

	public Casa save(Casa casa);

	boolean existsByTelefono(Long telefono);

}
