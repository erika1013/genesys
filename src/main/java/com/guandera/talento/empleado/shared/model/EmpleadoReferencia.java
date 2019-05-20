/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.empleado.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.core.shared.model.Ciudad;
import com.guandera.talento.shared.model.ReferenciaTipo;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class EmpleadoReferencia extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long referenciaid;

	private String nombres;

	private String apellidos;

	private String profesion;

	private String cargo;

	private Long celular;

	private Long telefono;

	private Integer extension;

	@Index
	@Load
	Ref<Ciudad> ciudad;

	@Index
	@Load
	Ref<Empleado> empleado;

	@Index
	@Load
	Ref<ReferenciaTipo> tipo;

	public EmpleadoReferencia() {
	}

	public EmpleadoReferencia(Long referenciaid) {
		this.referenciaid = referenciaid;
	}

	public Long getReferenciaid() {
		return referenciaid;
	}

	public void setReferenciaid(Long referenciaid) {
		this.referenciaid = referenciaid;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public Integer getExtension() {
		return extension;
	}

	public void setExtension(Integer extension) {
		this.extension = extension;
	}

	public Ciudad getCiudad() {
		return ciudad.get();
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = Ref.create(ciudad);
	}

	public Empleado getEmpleado() {
		return empleado.get();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = Ref.create(empleado);
	}

	public ReferenciaTipo getTipo() {
		return tipo.get();
	}

	public void setTipo(ReferenciaTipo tipo) {
		this.tipo = Ref.create(tipo);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (referenciaid != null ? referenciaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleadoReferencia)) {
			return false;
		}
		EmpleadoReferencia other = (EmpleadoReferencia) object;
		if ((this.referenciaid == null && other.referenciaid != null)
				|| (this.referenciaid != null && !this.referenciaid.equals(other.referenciaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.EmpleadoReferencia[ referenciaid=" + referenciaid + " ]";
	}

}
