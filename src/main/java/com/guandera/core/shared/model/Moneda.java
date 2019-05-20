/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class Moneda extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long monedaid;

	private String nombre;
	
	private String codigo;
	
	

	public Moneda() {
	}

	public Moneda(Long monedaid) {
		this.monedaid = monedaid;
	}

	public Long getMonedaid() {
		return monedaid;
	}

	public void setMonedaid(Long monedaid) {
		this.monedaid = monedaid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (monedaid != null ? monedaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Moneda)) {
			return false;
		}
		Moneda other = (Moneda) object;
		if ((this.monedaid == null && other.monedaid != null)
				|| (this.monedaid != null && !this.monedaid.equals(other.monedaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Moneda[ monedaid=" + monedaid + " ]";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
