package com.prueba.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
	@Column(name = "K_DOCUMENTO", nullable = false)
	private String documento;
	@Column(name = "N_NOMBRE", nullable = false)
	private String nombre;
	@Column(name = "O_EDAD", nullable = false)
	private String edad;
	@ManyToOne
	@JoinColumn(name = "K_IDCASA", nullable = false)
	private Casa casa;

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa, Long id) {
		this.casa=casa;
		
	}

}