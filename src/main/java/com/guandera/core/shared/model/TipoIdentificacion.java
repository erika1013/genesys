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

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class TipoIdentificacion extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long tipoidentificacionid;

	private Integer tipoidentificacionnumero;

	private String tipoidentificacionalfa;

	private String tipoidentificacionnombre;

	private List<Key<Proveedor>> proveedorList;

	private List<Key<Persona>> personaList;

	public TipoIdentificacion() {
	}

	public TipoIdentificacion(Long tipoidentificacionid) {
		this.tipoidentificacionid = tipoidentificacionid;
	}

	public Long getTipoidentificacionid() {
		return tipoidentificacionid;
	}

	public void setTipoidentificacionid(Long tipoidentificacionid) {
		this.tipoidentificacionid = tipoidentificacionid;
	}

	public Integer getTipoidentificacionnumero() {
		return tipoidentificacionnumero;
	}

	public void setTipoidentificacionnumero(Integer tipoidentificacionnumero) {
		this.tipoidentificacionnumero = tipoidentificacionnumero;
	}

	public String getTipoidentificacionalfa() {
		return tipoidentificacionalfa;
	}

	public void setTipoidentificacionalfa(String tipoidentificacionalfa) {
		this.tipoidentificacionalfa = tipoidentificacionalfa;
	}

	public String getTipoidentificacionnombre() {
		return tipoidentificacionnombre;
	}

	public void setTipoidentificacionnombre(String tipoidentificacionnombre) {
		this.tipoidentificacionnombre = tipoidentificacionnombre;
	}

	public List<Key<Proveedor>> getProveedorList() {
		return proveedorList;
	}

	public void setProveedorList(List<Key<Proveedor>> proveedorList) {
		this.proveedorList = proveedorList;
	}

	public List<Key<Persona>> getPersonaList() {
		return personaList;
	}

	public void setPersonaList(List<Key<Persona>> personaList) {
		this.personaList = personaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tipoidentificacionid != null ? tipoidentificacionid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoIdentificacion)) {
			return false;
		}
		TipoIdentificacion other = (TipoIdentificacion) object;
		if ((this.tipoidentificacionid == null && other.tipoidentificacionid != null)
				|| (this.tipoidentificacionid != null
						&& !this.tipoidentificacionid.equals(other.tipoidentificacionid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.TipoIdentificacion[ tipoidentificacionid=" + tipoidentificacionid + " ]";
	}

}
