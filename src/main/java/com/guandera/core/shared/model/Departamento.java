/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class Departamento extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long departamentoId;

	@Index
	private Integer codigo;

	private String nombre;

	public Departamento() {
	}

	public Departamento(Long departamentoId) {
		this.departamentoId = departamentoId;
	}

	public Long getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(Long departamentoId) {
		this.departamentoId = departamentoId;
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
		hash += (departamentoId != null ? departamentoId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Departamento)) {
			return false;
		}
		Departamento other = (Departamento) object;
		if ((this.departamentoId == null && other.departamentoId != null)
				|| (this.departamentoId != null && !this.departamentoId.equals(other.departamentoId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.Departamento[ departamentoId=" + departamentoId + " ]";
	}

}
