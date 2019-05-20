
package com.guandera.proyecto.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.guandera.core.shared.model.Auditoria;

@Entity
public class Tiempo extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long tiempoid;

	private String codigo;
	private String nombre;
	private String codigo1;
	private Double numero1;

	public Tiempo() {
	}

	public Tiempo(Long tiempoid) {
		this.tiempoid = tiempoid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tiempoid != null ? tiempoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Tiempo)) {
			return false;
		}
		Tiempo other = (Tiempo) object;
		if ((this.tiempoid == null && other.tiempoid != null)
				|| (this.tiempoid != null && !this.tiempoid.equals(other.tiempoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.Tiempo[ tiempoid=" + tiempoid + " ]";
	}

	public Long getTiempoid() {
		return tiempoid;
	}

	public void setTiempoid(Long tiempoid) {
		this.tiempoid = tiempoid;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo1() {
		return codigo1;
	}

	public void setCodigo1(String codigo1) {
		this.codigo1 = codigo1;
	}

	public Double getNumero1() {
		return numero1;
	}

	public void setNumero1(Double numero1) {
		this.numero1 = numero1;
	}

}
