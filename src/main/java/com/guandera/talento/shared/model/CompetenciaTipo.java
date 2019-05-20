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
public class CompetenciaTipo extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long tipoId;

	@Index
	private Integer codigo;

	private String nombre;

	public CompetenciaTipo() {
	}

	public CompetenciaTipo(Long tipoId) {
		this.tipoId = tipoId;
	}

	public Long getTipoId() {
		return tipoId;
	}

	public void setTipoId(Long tipoId) {
		this.tipoId = tipoId;
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
		hash += (tipoId != null ? tipoId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CompetenciaTipo)) {
			return false;
		}
		CompetenciaTipo other = (CompetenciaTipo) object;
		if ((this.tipoId == null && other.tipoId != null)
				|| (this.tipoId != null && !this.tipoId.equals(other.tipoId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.CompetenciaTipo[ tipoId=" + tipoId + " ]";
	}

}
