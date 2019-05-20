/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.guandera.core.shared.model.Auditoria;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class ReferenciaTipo extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long referenciaTipoId;

	@Index
	private Integer codigo;

	private String nombre;

	public ReferenciaTipo() {
	}

	public ReferenciaTipo(Long referenciaTipoId) {
		this.referenciaTipoId = referenciaTipoId;
	}

	public Long getReferenciaTipoId() {
		return referenciaTipoId;
	}

	public void setReferenciaTipoId(Long referenciaTipoId) {
		this.referenciaTipoId = referenciaTipoId;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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
		hash += (referenciaTipoId != null ? referenciaTipoId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ReferenciaTipo)) {
			return false;
		}
		ReferenciaTipo other = (ReferenciaTipo) object;
		if ((this.referenciaTipoId == null && other.referenciaTipoId != null)
				|| (this.referenciaTipoId != null && !this.referenciaTipoId.equals(other.referenciaTipoId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.AspiranteReferenciaTipo[ referenciaTipoId=" + referenciaTipoId + " ]";
	}

}
