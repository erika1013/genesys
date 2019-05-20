/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
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
public class Descuento extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long descuentoid;

	private String nombre;

	private Double descuentovalor;

	@Index
	@Load
	Ref<Sede> sede;

	@Index
	@Load
	Ref<TipoServicio> tipoServicio;

	public Descuento() {
	}

	public Descuento(Long descuentoid) {
		this.descuentoid = descuentoid;
	}

	public Long getDescuentoid() {
		return descuentoid;
	}

	public void setDescuentoid(Long descuentoid) {
		this.descuentoid = descuentoid;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio.get();
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = Ref.create(tipoServicio);
	}

	public Double getDescuentovalor() {
		return descuentovalor;
	}

	public void setDescuentovalor(Double descuentovalor) {
		this.descuentovalor = descuentovalor;
	}

	public Sede getSede() {
		return sede.get();
	}

	public void setSede(Sede sede) {
		this.sede = Ref.create(sede);
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
		hash += (descuentoid != null ? descuentoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Descuento)) {
			return false;
		}
		Descuento other = (Descuento) object;
		if ((this.descuentoid == null && other.descuentoid != null)
				|| (this.descuentoid != null && !this.descuentoid.equals(other.descuentoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Descuento[ descuentoid=" + descuentoid + " ]";
	}

}
