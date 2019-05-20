package com.guandera.core.shared.model.acceso;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S
 */
@Entity
public class Acceso implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long accesoid;

	@Index
	@Load
	Ref<Rol> rol;

	@Index
	@Load
	Ref<Menu> menu;

	private Long creacionusuario;

	private Date creacionfecha;

	private Long modificacionusuario;

	private Date modificacionfecha;

	public Acceso() {
	}

	public Acceso(Long accesoid) {
		this.accesoid = accesoid;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Acceso)) {
			return false;
		}
		Acceso other = (Acceso) object;
		if ((this.accesoid == null && other.accesoid != null)
				|| (this.accesoid != null && !this.accesoid.equals(other.accesoid))) {
			return false;
		}
		return true;
	}

	public Long getAccesoid() {
		return accesoid;
	}

	public Date getCreacionfecha() {
		return creacionfecha;
	}

	public Long getCreacionusuario() {
		return creacionusuario;
	}

	public Menu getMenu() {
		return menu.get();
	}

	public Date getModificacionfecha() {
		return modificacionfecha;
	}

	public Long getModificacionusuario() {
		return modificacionusuario;
	}

	public Rol getRol() {
		return rol.get();
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accesoid != null ? accesoid.hashCode() : 0);
		return hash;
	}

	public void setAccesoid(Long accesoid) {
		this.accesoid = accesoid;
	}

	public void setCreacionfecha(Date creacionfecha) {
		this.creacionfecha = creacionfecha;
	}

	public void setCreacionusuario(Long creacionusuario) {
		this.creacionusuario = creacionusuario;
	}

	public void setMenu(Menu menu) {
		this.menu = Ref.create(menu);
	}

	public void setModificacionfecha(Date modificacionfecha) {
		this.modificacionfecha = modificacionfecha;
	}

	public void setModificacionusuario(Long modificacionusuario) {
		this.modificacionusuario = modificacionusuario;
	}

	public void setRol(Rol rol) {
		this.rol = Ref.create(rol);
	}

	@Override
	public String toString() {
		return "com.davivienda.sigpre.modelo.entity.Acceso[ accesoid=" + accesoid + " ]";
	}

}
