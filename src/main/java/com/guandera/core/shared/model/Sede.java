/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.talento.empleado.shared.model.Empleado;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class Sede extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long sedeid;

	@Index
	private String sedecodigo;

	private String sedenombre;

	private String sededireccion;

	private Long sedetelefono1;

	private Long sedetelefono2;

	private List<Key<Servicio>> servicioList;

	@Index
	@Load
	Ref<Compania> compania;

	private List<Key<Empleado>> empleadoList;

	public Sede() {
	}

	public Sede(Long sedeid) {
		this.sedeid = sedeid;
	}

	public Long getSedeid() {
		return sedeid;
	}

	public void setSedeid(Long sedeid) {
		this.sedeid = sedeid;
	}

	public String getSedecodigo() {
		return sedecodigo;
	}

	public void setSedecodigo(String sedecodigo) {
		this.sedecodigo = sedecodigo;
	}

	public String getSedenombre() {
		return sedenombre;
	}

	public void setSedenombre(String sedenombre) {
		this.sedenombre = sedenombre;
	}

	public String getSededireccion() {
		return sededireccion;
	}

	public void setSededireccion(String sededireccion) {
		this.sededireccion = sededireccion;
	}

	public Long getSedetelefono1() {
		return sedetelefono1;
	}

	public void setSedetelefono1(Long sedetelefono1) {
		this.sedetelefono1 = sedetelefono1;
	}

	public Long getSedetelefono2() {
		return sedetelefono2;
	}

	public void setSedetelefono2(Long sedetelefono2) {
		this.sedetelefono2 = sedetelefono2;
	}

	public List<Key<Servicio>> getServicioList() {
		return servicioList;
	}

	public void setServicioList(List<Key<Servicio>> servicioList) {
		this.servicioList = servicioList;
	}

	public Compania getCompania() {
		return compania.get();
	}

	public void setCompania(Compania compania) {
		this.compania = Ref.create(compania);
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
		hash += (sedeid != null ? sedeid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Sede)) {
			return false;
		}
		Sede other = (Sede) object;
		if ((this.sedeid == null && other.sedeid != null)
				|| (this.sedeid != null && !this.sedeid.equals(other.sedeid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Sede[ sedeid=" + sedeid + " ]";
	}

}
