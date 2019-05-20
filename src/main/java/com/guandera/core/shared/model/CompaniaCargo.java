/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.guandera.talento.empleado.shared.model.Empleado;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class CompaniaCargo extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long companiacargoid;

	private String companiacargonombre;

	private List<Key<Empleado>> empleadoList;

	public CompaniaCargo() {
	}

	public CompaniaCargo(Long companiacargoid) {
		this.companiacargoid = companiacargoid;
	}

	public Long getCompaniacargoid() {
		return companiacargoid;
	}

	public void setCompaniacargoid(Long companiacargoid) {
		this.companiacargoid = companiacargoid;
	}

	public String getCompaniacargonombre() {
		return companiacargonombre;
	}

	public void setCompaniacargonombre(String companiacargonombre) {
		this.companiacargonombre = companiacargonombre;
	}

	public List<Key<Empleado>> getEmpleadoList() {
		return empleadoList;
	}

	public void setEmpleadoList(List<Key<Empleado>> empleadoList) {
		this.empleadoList = empleadoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (companiacargoid != null ? companiacargoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CompaniaCargo)) {
			return false;
		}
		CompaniaCargo other = (CompaniaCargo) object;
		if ((this.companiacargoid == null && other.companiacargoid != null)
				|| (this.companiacargoid != null && !this.companiacargoid.equals(other.companiacargoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.CompaniaCargo[ companiacargoid=" + companiacargoid + " ]";
	}

}
