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
public class CentroCostos extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long centroCostosid;

	@Index
	private String codigo;

	private String nombre;

	@Index
	@Load
	Ref<Compania> compania;

	public CentroCostos() {
	}

	public CentroCostos(Long centroCostosid) {
		this.centroCostosid = centroCostosid;
	}

	public Long getCentroCostosid() {
		return centroCostosid;
	}

	public void setCentroCostosid(Long centroCostosid) {
		this.centroCostosid = centroCostosid;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Compania getCompania() {
		return compania.get();
	}

	public void setCompania(Compania compania) {
		this.compania = Ref.create(compania);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (centroCostosid != null ? centroCostosid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CentroCostos)) {
			return false;
		}
		CentroCostos other = (CentroCostos) object;
		if ((this.centroCostosid == null && other.centroCostosid != null)
				|| (this.centroCostosid != null && !this.centroCostosid.equals(other.centroCostosid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.CentroCostos[ centroCostosid=" + centroCostosid + " ]";
	}

}
