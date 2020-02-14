package com.uniovi.entities;


public class Professor {

	private Long id;
	private String dni;
	private String nombre;
	private String apellido;
	private String categoria;

	public Professor() {
	}

	public Professor(Long id, String dni, String nombre, String apellido, String categoria) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", categoria=" + categoria + "]";
	}

}
