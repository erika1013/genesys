package com.guandera.proyecto.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;

@Entity
public class ProyectoEtapaCambio extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long cambioid;

	private Date fechaAnterior;
	private Date fechaActual;

	@Index
	@Load
	private Ref<ProyectoEtapa> etapaAnterior;

	@Index
	@Load
	private Ref<ProyectoEtapa> etapaActual;

	@Index
	@Load
	private Ref<Proyecto> proyecto;

	public ProyectoEtapaCambio() {
	}

	public ProyectoEtapaCambio(Long cambioid) {
		this.cambioid = cambioid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cambioid != null ? cambioid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProyectoEtapaCambio)) {
			return false;
		}
		ProyectoEtapaCambio other = (ProyectoEtapaCambio) object;
		if ((this.cambioid == null && other.cambioid != null)
				|| (this.cambioid != null && !this.cambioid.equals(other.cambioid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.ProyectoEtapaCambio[ cambioid=" + cambioid + " ]";
	}

	public Long getCambioid() {
		return cambioid;
	}

	public void setCambioid(Long cambioid) {
		this.cambioid = cambioid;
	}

	public Proyecto getProyecto() {
		return proyecto.get();
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = Ref.create(proyecto);
	}

	public ProyectoEtapa getEtapaAnterior() {
		return etapaAnterior.get();
	}

	public void setEtapaAnterior(ProyectoEtapa etapaAnterior) {
		this.etapaAnterior = Ref.create(etapaAnterior);
	}

	public ProyectoEtapa getEtapaActual() {
		return etapaActual.get();
	}

	public void setEtapaActual(ProyectoEtapa etapaActual) {
		this.etapaActual = Ref.create(etapaActual);
	}

	public Date getFechaAnterior() {
		return fechaAnterior;
	}

	public void setFechaAnterior(Date fechaAnterior) {
		this.fechaAnterior = fechaAnterior;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

}
