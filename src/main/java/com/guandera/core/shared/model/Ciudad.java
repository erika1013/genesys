/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author FrediJavier
 */
@Entity
public class Ciudad extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long ciudadId;

	@Index
	private Integer codigo;

	private String nombre;

	@Index
	@Load
	Ref<Departamento> departamento;

	public Ciudad() {
	}

	public Ciudad(Long ciudadId) {
		this.ciudadId = ciudadId;
	}

	public Long getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(Long ciudadId) {
		this.ciudadId = ciudadId;
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

	public Departamento getDepartamento() {
		return departamento.get();
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = Ref.create(departamento);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (ciudadId != null ? ciudadId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Ciudad)) {
			return false;
		}
		Ciudad other = (Ciudad) object;
		if ((this.ciudadId == null && other.ciudadId != null)
				|| (this.ciudadId != null && !this.ciudadId.equals(other.ciudadId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.Ciudad[ ciudadId=" + ciudadId + " ]";
	}

}
