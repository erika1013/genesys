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
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class Submenu implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long submenuid;

	@Index
	private Short submenuorden;

	@Index
	@Load
	Ref<Opcion> opcion;

	@Index
	@Load
	Ref<Menu> menu;

	private Long creacionusuario;

	private Date creacionfecha;

	private Long modificacionusuario;

	private Date modificacionfecha;

	public Submenu() {
	}

	public Submenu(Long submenuid) {
		this.submenuid = submenuid;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Submenu)) {
			return false;
		}
		Submenu other = (Submenu) object;
		if ((this.submenuid == null && other.submenuid != null)
				|| (this.submenuid != null && !this.submenuid.equals(other.submenuid))) {
			return false;
		}
		return true;
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

	public Long getSubmenuid() {
		return submenuid;
	}

	public Short getSubmenuorden() {
		return submenuorden;
	}

	public Opcion getOpcion() {
		return opcion.get();
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (submenuid != null ? submenuid.hashCode() : 0);
		return hash;
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

	public void setSubmenuid(Long submenuid) {
		this.submenuid = submenuid;
	}

	public void setSubmenuorden(Short submenuorden) {
		this.submenuorden = submenuorden;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = Ref.create(opcion);
	}

	@Override
	public String toString() {
		return "com.davivienda.sigpre.modelo.entity.Submenu[ submenuid=" + submenuid + " ]";
	}
}
