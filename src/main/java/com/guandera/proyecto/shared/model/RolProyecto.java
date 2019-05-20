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
public class RolProyecto extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long rolid;

	private String nombre;

	public RolProyecto() {
	}

	public RolProyecto(Long rolid) {
		this.rolid = rolid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (rolid != null ? rolid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof RolProyecto)) {
			return false;
		}
		RolProyecto other = (RolProyecto) object;
		if ((this.rolid == null && other.rolid != null) || (this.rolid != null && !this.rolid.equals(other.rolid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.RolProyecto[ rolid=" + rolid + " ]";
	}

	public Long getRolid() {
		return rolid;
	}

	public void setRolid(Long rolid) {
		this.rolid = rolid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
