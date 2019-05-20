package com.guandera.proyecto.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.guandera.core.shared.model.Auditoria;

@Entity
public class RequerimientoTipo extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long tipoid;
	private String nombre;

	public RequerimientoTipo() {
	}

	public RequerimientoTipo(Long tipoid) {
		this.tipoid = tipoid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tipoid != null ? tipoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof RequerimientoTipo)) {
			return false;
		}
		RequerimientoTipo other = (RequerimientoTipo) object;
		if ((this.tipoid == null && other.tipoid != null)
				|| (this.tipoid != null && !this.tipoid.equals(other.tipoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.RequerimientoTipo[ tipoid=" + tipoid + " ]";
	}

	public Long getTipoid() {
		return tipoid;
	}

	public void setTipoid(Long tipoid) {
		this.tipoid = tipoid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
