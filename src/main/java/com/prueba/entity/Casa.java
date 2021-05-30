package com.prueba.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "casa")
public class Casa implements Serializable {

	private static final long serialVersionUID = -3330617886592640607L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "K_IDCASA", nullable = false)
	private Long id;
	@Column(name="N_DIRECCION",nullable=false)
	private String direccion;
	@Column(name="O_TELEFONO",nullable=false)
	private Long telefono;
	@JsonIgnore
	@OneToMany(mappedBy="documento",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Column(name="K_DOCUMENTO",nullable=false)
	Set<Persona> persona;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Long getTelefono() {
		return telefono;
	}
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
	public Set<Persona> getPersona() {
		return persona;
	}
	public void setPersona(Set<Persona> persona) {
		this.persona = persona;
	}


}
