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
public class Opcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long opcionid;

	private String opcionnombre;

	private String opcionvalor;

	private List<Key<Submenu>> submenuList;

	private Long creacionusuario;

	private Date creacionfecha;

	private Long modificacionusuario;

	private Date modificacionfecha;

	public Opcion() {
	}

	public Opcion(Long opcionid) {
		this.opcionid = opcionid;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Opcion)) {
			return false;
		}
		Opcion other = (Opcion) object;
		if ((this.opcionid == null && other.opcionid != null)
				|| (this.opcionid != null && !this.opcionid.equals(other.opcionid))) {
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

	public Date getModificacionfecha() {
		return modificacionfecha;
	}

	public Long getModificacionusuario() {
		return modificacionusuario;
	}

	public List<Key<Submenu>> getSubmenuList() {
		return submenuList;
	}

	public Long getOpcionid() {
		return opcionid;
	}

	public String getOpcionnombre() {
		return opcionnombre;
	}

	public String getOpcionvalor() {
		return opcionvalor;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (opcionid != null ? opcionid.hashCode() : 0);
		return hash;
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

	public void setSubmenuList(List<Key<Submenu>> submenuList) {
		this.submenuList = submenuList;
	}

	public void setOpcionid(Long opcionid) {
		this.opcionid = opcionid;
	}

	public void setOpcionnombre(String opcionnombre) {
		this.opcionnombre = opcionnombre;
	}

	public void setOpcionvalor(String opcionvalor) {
		this.opcionvalor = opcionvalor;
	}

	@Override
	public String toString() {
		return "com.davivienda.sigpre.modelo.entity.Opcion[ opcionid=" + opcionid + " ]";
	}

}
