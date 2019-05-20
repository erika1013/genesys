/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.shared.model;

import java.io.Serializable;
import java.util.Date;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class FormacionEstado extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long formacionEstadoId;

	@Index
	private Integer codigo;

	private String nombre;

	public FormacionEstado() {
	}

	public FormacionEstado(Long formacionEstadoId) {
		this.formacionEstadoId = formacionEstadoId;
	}

	public Long getFormacionEstadoId() {
		return formacionEstadoId;
	}

	public void setFormacionEstadoId(Long formacionEstadoId) {
		this.formacionEstadoId = formacionEstadoId;
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
		hash += (formacionEstadoId != null ? formacionEstadoId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof FormacionEstado)) {
			return false;
		}
		FormacionEstado other = (FormacionEstado) object;
		if ((this.formacionEstadoId == null && other.formacionEstadoId != null)
				|| (this.formacionEstadoId != null && !this.formacionEstadoId.equals(other.formacionEstadoId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.FormacionEstado[ formacionEstadoId=" + formacionEstadoId + " ]";
	}

}
