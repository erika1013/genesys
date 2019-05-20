
package com.guandera.proyecto.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.guandera.core.shared.model.Auditoria;

@Entity
public class TareaEstado extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long estado;
	private String nombre;

	public TareaEstado() {
	}

	public TareaEstado(Long estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (estado != null ? estado.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TareaEstado)) {
			return false;
		}
		TareaEstado other = (TareaEstado) object;
		if ((this.estado == null && other.estado != null)
				|| (this.estado != null && !this.estado.equals(other.estado))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.TareaEstado[ estado=" + estado + " ]";
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
