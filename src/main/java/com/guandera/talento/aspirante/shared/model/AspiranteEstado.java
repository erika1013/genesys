/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.aspirante.shared.model;

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
public class AspiranteEstado extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long estadoId;

	@Index
	private Integer codigo;

	private String nombre;

	public AspiranteEstado() {
	}

	public AspiranteEstado(Long estadoId) {
		this.estadoId = estadoId;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
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
		hash += (estadoId != null ? estadoId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AspiranteEstado)) {
			return false;
		}
		AspiranteEstado other = (AspiranteEstado) object;
		if ((this.estadoId == null && other.estadoId != null)
				|| (this.estadoId != null && !this.estadoId.equals(other.estadoId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.AspiranteEstado[ estadoId=" + estadoId + " ]";
	}

}
