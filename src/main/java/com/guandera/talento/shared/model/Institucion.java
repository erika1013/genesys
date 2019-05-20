/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.core.shared.model.Ciudad;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class Institucion extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long institucionId;

	@Index
	private Integer codigo;

	private String nombre;

	@Index
	@Load
	Ref<Ciudad> ciudad;

	public Institucion() {
	}

	public Institucion(Long institucionId) {
		this.institucionId = institucionId;
	}

	public Long getInstitucionId() {
		return institucionId;
	}

	public void setInstitucionId(Long institucionId) {
		this.institucionId = institucionId;
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

	public Ciudad getCiudad() {
		return ciudad.get();
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = Ref.create(ciudad);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (institucionId != null ? institucionId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Institucion)) {
			return false;
		}
		Institucion other = (Institucion) object;
		if ((this.institucionId == null && other.institucionId != null)
				|| (this.institucionId != null && !this.institucionId.equals(other.institucionId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.Institucion[ institucionId=" + institucionId + " ]";
	}

}
