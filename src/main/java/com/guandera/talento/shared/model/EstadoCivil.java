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
public class EstadoCivil extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long estadoCivilId;

	@Index
	private Integer codigo;

	private String nombre;

	public EstadoCivil() {
	}

	public EstadoCivil(Long estadoCivilId) {
		this.estadoCivilId = estadoCivilId;
	}

	public Long getEstadoCivilId() {
		return estadoCivilId;
	}

	public void setEstadoCivilId(Long estadoCivilId) {
		this.estadoCivilId = estadoCivilId;
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
		hash += (estadoCivilId != null ? estadoCivilId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EstadoCivil)) {
			return false;
		}
		EstadoCivil other = (EstadoCivil) object;
		if ((this.estadoCivilId == null && other.estadoCivilId != null)
				|| (this.estadoCivilId != null && !this.estadoCivilId.equals(other.estadoCivilId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.EstadoCivil[ estadoCivilId=" + estadoCivilId + " ]";
	}

}
