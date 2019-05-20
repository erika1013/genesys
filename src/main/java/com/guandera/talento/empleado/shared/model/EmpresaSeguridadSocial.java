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
public class EmpresaSeguridadSocial extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long empresaseguridadsocialid;

	private String empresaseguridadsocialnombre;

	private String contacto;
	private String telefono;
	private String email;
	private String direccion;
	private String web;
	private String online;

	private List<Key<SeguridadSocial>> seguridadSocialList;

	public EmpresaSeguridadSocial() {
	}

	public EmpresaSeguridadSocial(Long empresaseguridadsocialid) {
		this.empresaseguridadsocialid = empresaseguridadsocialid;
	}

	public Long getEmpresaseguridadsocialid() {
		return empresaseguridadsocialid;
	}

	public void setEmpresaseguridadsocialid(Long empresaseguridadsocialid) {
		this.empresaseguridadsocialid = empresaseguridadsocialid;
	}

	public String getEmpresaseguridadsocialnombre() {
		return empresaseguridadsocialnombre;
	}

	public void setEmpresaseguridadsocialnombre(String empresaseguridadsocialnombre) {
		this.empresaseguridadsocialnombre = empresaseguridadsocialnombre;
	}

	public List<Key<SeguridadSocial>> getSeguridadSocialList() {
		return seguridadSocialList;
	}

	public void setSeguridadSocialList(List<Key<SeguridadSocial>> seguridadSocialList) {
		this.seguridadSocialList = seguridadSocialList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (empresaseguridadsocialid != null ? empresaseguridadsocialid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpresaSeguridadSocial)) {
			return false;
		}
		EmpresaSeguridadSocial other = (EmpresaSeguridadSocial) object;
		if ((this.empresaseguridadsocialid == null && other.empresaseguridadsocialid != null)
				|| (this.empresaseguridadsocialid != null
						&& !this.empresaseguridadsocialid.equals(other.empresaseguridadsocialid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.EmpresaSeguridadSocial[ empresaseguridadsocialid="
				+ empresaseguridadsocialid + " ]";
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

}
