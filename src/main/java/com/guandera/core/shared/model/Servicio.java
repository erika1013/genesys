/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class Servicio extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long servicioid;

	@Index
	private Long codigo;

	@Index
	private String nombre;

	private Double factorIva;

	@Index
	@Load
	Ref<Compania> compania;

	public Servicio() {
	}

	public Servicio(Long servicioid) {
		this.servicioid = servicioid;
	}

	public Long getServicioid() {
		return servicioid;
	}

	public void setServicioid(Long servicioid) {
		this.servicioid = servicioid;
	}

	public Compania getCompania() {
		return compania.get();
	}

	public void setCompania(Compania compania) {
		this.compania = Ref.create(compania);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getFactorIva() {
		return factorIva;
	}

	public void setFactorIva(Double factorIva) {
		this.factorIva = factorIva;
	}

}
