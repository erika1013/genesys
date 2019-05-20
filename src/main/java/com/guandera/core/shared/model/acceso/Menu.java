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
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long menuid;

	private String menunombre;
	
	private String menuicono;

	private List<Key<Submenu>> submenuList;

	private List<Key<Acceso>> accesoList;

	private Long creacionusuario;

	private Date creacionfecha;

	private Long modificacionusuario;

	private Date modificacionfecha;

	public Menu() {
	}

	public Menu(Long menuid) {
		this.menuid = menuid;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Menu)) {
			return false;
		}
		Menu other = (Menu) object;
		if ((this.menuid == null && other.menuid != null)
				|| (this.menuid != null && !this.menuid.equals(other.menuid))) {
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

	public Long getMenuid() {
		return menuid;
	}

	public String getMenunombre() {
		return menunombre;
	}

	public Date getModificacionfecha() {
		return modificacionfecha;
	}

	public Long getModificacionusuario() {
		return modificacionusuario;
	}

	public List<Key<Submenu>> getSubmenuList() {
		return submenuList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (menuid != null ? menuid.hashCode() : 0);
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

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	public void setMenunombre(String menunombre) {
		this.menunombre = menunombre;
	}

	public void setModificacionfecha(Date modificacionfecha) {
		this.modificacionfecha = modificacionfecha;
	}

	public void setModificacionusuario(Long modificacionusuario) {
		this.modificacionusuario = modificacionusuario;
	}

	public void setSubmenuList(List<Key<Submenu>> submenuList) {
		this.submenuList = submenuList;
	}

	@Override
	public String toString() {
		return "com.davivienda.sigpre.modelo.entity.Menu[ menuid=" + menuid + " ]";
	}

	public String getMenuicono() {
		return menuicono;
	}

	public void setMenuicono(String menuicono) {
		this.menuicono = menuicono;
	}
}
