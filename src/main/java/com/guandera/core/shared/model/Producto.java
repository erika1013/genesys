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
public class Producto extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long productoid;

	private String nombre;

	private Double productovalor;

	@Index
	@Load
	Ref<Sede> sede;

	public Producto() {
	}

	public Producto(Long productoid) {
		this.productoid = productoid;
	}

	public Long getProductoid() {
		return productoid;
	}

	public void setProductoid(Long productoid) {
		this.productoid = productoid;
	}

	public Double getProductovalor() {
		return productovalor;
	}

	public void setProductovalor(Double productovalor) {
		this.productovalor = productovalor;
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
		hash += (productoid != null ? productoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Producto)) {
			return false;
		}
		Producto other = (Producto) object;
		if ((this.productoid == null && other.productoid != null)
				|| (this.productoid != null && !this.productoid.equals(other.productoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Producto[ productoid=" + productoid + " ]";
	}

}
