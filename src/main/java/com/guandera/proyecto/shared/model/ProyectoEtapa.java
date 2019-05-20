/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.proyecto.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.guandera.core.shared.model.Auditoria;

@Entity
public class ProyectoEtapa extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long etapaid;

	private String nombre;
	private String descripcion;

	public ProyectoEtapa() {
	}

	public ProyectoEtapa(Long etapaid) {
		this.etapaid = etapaid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (etapaid != null ? etapaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProyectoEtapa)) {
			return false;
		}
		ProyectoEtapa other = (ProyectoEtapa) object;
		if ((this.etapaid == null && other.etapaid != null)
				|| (this.etapaid != null && !this.etapaid.equals(other.etapaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.ProyectoEtapa[ etapaid=" + etapaid + " ]";
	}

	public Long getEtapaid() {
		return etapaid;
	}

	public void setEtapaid(Long etapaid) {
		this.etapaid = etapaid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
