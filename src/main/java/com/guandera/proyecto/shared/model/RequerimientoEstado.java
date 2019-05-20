
package com.guandera.proyecto.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.guandera.core.shared.model.Auditoria;

@Entity
public class RequerimientoEstado extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long estadoid;
	private String nombre;
	private String descripcion;

	public RequerimientoEstado() {
	}

	public RequerimientoEstado(Long estadoid) {
		this.estadoid = estadoid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (estadoid != null ? estadoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof RequerimientoEstado)) {
			return false;
		}
		RequerimientoEstado other = (RequerimientoEstado) object;
		if ((this.estadoid == null && other.estadoid != null)
				|| (this.estadoid != null && !this.estadoid.equals(other.estadoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.RequerimientoEstado[ estadoid=" + estadoid + " ]";
	}

	public Long getEstadoid() {
		return estadoid;
	}

	public void setEstadoid(Long estadoid) {
		this.estadoid = estadoid;
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
