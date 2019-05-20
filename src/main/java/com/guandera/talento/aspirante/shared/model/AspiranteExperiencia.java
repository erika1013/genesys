/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.aspirante.shared.model;

import java.io.Serializable;
import java.util.Date;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.core.shared.model.Ciudad;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class AspiranteExperiencia extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long experienciaid;

	private String empresa;

	private String descripcion;

	private Date fechaIncial;

	private Date fechaFinal;

	private String cargo;

	private String jefe;

	private Long telefono;

	private Integer extension;

	@Index
	@Load
	Ref<Aspirante> aspirante;

	@Index
	@Load
	Ref<Ciudad> ciudad;

	public AspiranteExperiencia() {
	}

	public AspiranteExperiencia(Long experienciaid) {
		this.experienciaid = experienciaid;
	}

	public Long getExperienciaid() {
		return experienciaid;
	}

	public void setExperienciaid(Long experienciaid) {
		this.experienciaid = experienciaid;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaIncial() {
		return fechaIncial;
	}

	public void setFechaIncial(Date fechaIncial) {
		this.fechaIncial = fechaIncial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
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

	public Aspirante getAspirante() {
		return aspirante.get();
	}

	public void setAspirante(Aspirante aspirante) {
		this.aspirante = Ref.create(aspirante);
	}

	public Ciudad getCiudad() {
		return ciudad.get();
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = Ref.create(ciudad);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (experienciaid != null ? experienciaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AspiranteExperiencia)) {
			return false;
		}
		AspiranteExperiencia other = (AspiranteExperiencia) object;
		if ((this.experienciaid == null && other.experienciaid != null)
				|| (this.experienciaid != null && !this.experienciaid.equals(other.experienciaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.AspiranteExperiencia[ experienciaid=" + experienciaid + " ]";
	}

}
