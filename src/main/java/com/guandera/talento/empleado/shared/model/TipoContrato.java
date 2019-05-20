/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.empleado.shared.model;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.guandera.core.shared.model.Auditoria;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class TipoContrato extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long tipocontratoid;

	private String tipocontratonombre;

	private List<Key<EmpleadoContrato>> empleadoContratoList;

	public TipoContrato() {
	}

	public TipoContrato(Long tipocontratoid) {
		this.tipocontratoid = tipocontratoid;
	}

	public Long getTipocontratoid() {
		return tipocontratoid;
	}

	public void setTipocontratoid(Long tipocontratoid) {
		this.tipocontratoid = tipocontratoid;
	}

	public String getTipocontratonombre() {
		return tipocontratonombre;
	}

	public void setTipocontratonombre(String tipocontratonombre) {
		this.tipocontratonombre = tipocontratonombre;
	}

	public List<Key<EmpleadoContrato>> getEmpleadoContratoList() {
		return empleadoContratoList;
	}

	public void setEmpleadoContratoList(List<Key<EmpleadoContrato>> empleadoContratoList) {
		this.empleadoContratoList = empleadoContratoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tipocontratoid != null ? tipocontratoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoContrato)) {
			return false;
		}
		TipoContrato other = (TipoContrato) object;
		if ((this.tipocontratoid == null && other.tipocontratoid != null)
				|| (this.tipocontratoid != null && !this.tipocontratoid.equals(other.tipocontratoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.TipoContrato[ tipocontratoid=" + tipocontratoid + " ]";
	}

}
