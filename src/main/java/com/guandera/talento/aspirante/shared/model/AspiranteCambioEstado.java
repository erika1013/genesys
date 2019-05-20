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

/**
 * 
 * @author FrediJavier
 */
@Entity
public class AspiranteCambioEstado extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long cambioestadoid;

	private Date fechaCambio;

	@Index
	@Load
	Ref<Aspirante> aspirante;

	@Index
	@Load
	Ref<AspiranteEstado> estadoAnterior;

	@Index
	@Load
	Ref<AspiranteEstado> estadoActual;

	public AspiranteCambioEstado() {
	}

	public AspiranteCambioEstado(Long cambioestadoid) {
		this.cambioestadoid = cambioestadoid;
	}

	public Long getCambioestadoid() {
		return cambioestadoid;
	}

	public void setCambioestadoid(Long cambioestadoid) {
		this.cambioestadoid = cambioestadoid;
	}

	public Date getFechaCambio() {
		return fechaCambio;
	}

	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public Aspirante getAspirante() {
		return aspirante.get();
	}

	public void setAspirante(Aspirante aspirante) {
		this.aspirante = Ref.create(aspirante);
	}

	public AspiranteEstado getEstadoAnterior() {
		return estadoAnterior.get();
	}

	public void setEstadoAnterior(AspiranteEstado estadoAnterior) {
		this.estadoAnterior = Ref.create(estadoAnterior);
	}

	public AspiranteEstado getEstadoActual() {
		return estadoActual.get();
	}

	public void setEstadoActual(AspiranteEstado estadoActual) {
		this.estadoActual = Ref.create(estadoActual);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cambioestadoid != null ? cambioestadoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AspiranteCambioEstado)) {
			return false;
		}
		AspiranteCambioEstado other = (AspiranteCambioEstado) object;
		if ((this.cambioestadoid == null && other.cambioestadoid != null)
				|| (this.cambioestadoid != null && !this.cambioestadoid.equals(other.cambioestadoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.AspiranteCambioEstado[ cambioestadoid=" + cambioestadoid + " ]";
	}

}
