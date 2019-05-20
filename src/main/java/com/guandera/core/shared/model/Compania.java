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
public class Compania extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long companiaid;

	private String companianombre;

	private Integer companianit;

	private Short companiadv;

	private String companiapais;

	private String companiaciudad;

	private String email;

	private String companiawebsite;

	private List<Key<Sede>> sedeList;

	private List<Key<Proveedor>> proveedorList;

	public Compania() {
	}

	public Compania(Long companiaid) {
		this.companiaid = companiaid;
	}

	public Long getCompaniaid() {
		return companiaid;
	}

	public void setCompaniaid(Long companiaid) {
		this.companiaid = companiaid;
	}

	public String getCompanianombre() {
		return companianombre;
	}

	public void setCompanianombre(String companianombre) {
		this.companianombre = companianombre;
	}

	public Integer getCompanianit() {
		return companianit;
	}

	public void setCompanianit(Integer companianit) {
		this.companianit = companianit;
	}

	public Short getCompaniadv() {
		return companiadv;
	}

	public void setCompaniadv(Short companiadv) {
		this.companiadv = companiadv;
	}

	public String getCompaniapais() {
		return companiapais;
	}

	public void setCompaniapais(String companiapais) {
		this.companiapais = companiapais;
	}

	public String getCompaniaciudad() {
		return companiaciudad;
	}

	public void setCompaniaciudad(String companiaciudad) {
		this.companiaciudad = companiaciudad;
	}

	public String getCompaniawebsite() {
		return companiawebsite;
	}

	public void setCompaniawebsite(String companiawebsite) {
		this.companiawebsite = companiawebsite;
	}

	public List<Key<Sede>> getSedeList() {
		return sedeList;
	}

	public void setSedeList(List<Key<Sede>> sedeList) {
		this.sedeList = sedeList;
	}

	public List<Key<Proveedor>> getProveedorList() {
		return proveedorList;
	}

	public void setProveedorList(List<Key<Proveedor>> proveedorList) {
		this.proveedorList = proveedorList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (companiaid != null ? companiaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Compania)) {
			return false;
		}
		Compania other = (Compania) object;
		if ((this.companiaid == null && other.companiaid != null)
				|| (this.companiaid != null && !this.companiaid.equals(other.companiaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Compania[ companiaid=" + companiaid + " ]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
