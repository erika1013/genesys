package com.guandera.core.shared.model.acceso;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long rolid;
	private String rolnombre;
	private String roldescripcion;

	private List<Key<Usuario>> usuarioList;

	private List<Key<Acceso>> accesoList;

	private Long creacionusuario;
	private Date creacionfecha;
	private Long modificacionusuario;
	private Date modificacionfecha;

	public Rol() {
	}

	public Rol(Long rolid) {
		this.rolid = rolid;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Rol)) {
			return false;
		}
		Rol other = (Rol) object;
		if ((this.rolid == null && other.rolid != null) || (this.rolid != null && !this.rolid.equals(other.rolid))) {
			return false;
		}
		return true;
	}

	public List<Key<Acceso>> getAccesoList() {
		return accesoList;
	}

	public Date getCreacionfecha() {
		return creacionfecha;
	}

	public Long getCreacionusuario() {
		return creacionusuario;
	}

	public Date getModificacionfecha() {
		return modificacionfecha;
	}

	public Long getModificacionusuario() {
		return modificacionusuario;
	}

	public Long getRolid() {
		return rolid;
	}

	public String getRolnombre() {
		return rolnombre;
	}

	public List<Key<Usuario>> getUsuarioList() {
		return usuarioList;
	}

	public String getRoldescripcion() {
		return roldescripcion;
	}

	public void setRoldescripcion(String roldescripcion) {
		this.roldescripcion = roldescripcion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (rolid != null ? rolid.hashCode() : 0);
		return hash;
	}

	public void setAccesoList(List<Key<Acceso>> accesoList) {
		this.accesoList = accesoList;
	}

	public void setCreacionfecha(Date creacionfecha) {
		this.creacionfecha = creacionfecha;
	}

	public void setCreacionusuario(Long creacionusuario) {
		this.creacionusuario = creacionusuario;
	}

	public void setModificacionfecha(Date modificacionfecha) {
		this.modificacionfecha = modificacionfecha;
	}

	public void setModificacionusuario(Long modificacionusuario) {
		this.modificacionusuario = modificacionusuario;
	}

	public void setRolid(Long rolid) {
		this.rolid = rolid;
	}

	public void setRolnombre(String rolnombre) {
		this.rolnombre = rolnombre;
	}

	public void setUsuarioList(List<Key<Usuario>> usuarioList) {
		this.usuarioList = usuarioList;
	}

	@Override
	public String toString() {
		return "com.davivienda.sigpre.modelo.entity.Rolusuario[ rolid=" + rolid + " ]";
	}
}
