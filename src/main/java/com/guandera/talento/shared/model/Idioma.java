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
public class Idioma extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long idiomaId;

	@Index
	private Integer codigo;

	private String nombre;

	public Idioma() {
	}

	public Idioma(Long idiomaId) {
		this.idiomaId = idiomaId;
	}

	public Long getIdiomaId() {
		return idiomaId;
	}

	public void setIdiomaId(Long idiomaId) {
		this.idiomaId = idiomaId;
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
		hash += (idiomaId != null ? idiomaId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Idioma)) {
			return false;
		}
		Idioma other = (Idioma) object;
		if ((this.idiomaId == null && other.idiomaId != null)
				|| (this.idiomaId != null && !this.idiomaId.equals(other.idiomaId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.Idioma[ idiomaId=" + idiomaId + " ]";
	}

}
