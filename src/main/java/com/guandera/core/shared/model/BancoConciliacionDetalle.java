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

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class BancoConciliacionDetalle extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long conciliacionDetalleid;

	@Index
	private Integer numeroRecibo;

	private Double valorAplicado;

	@Index
	private Integer fechaAplicacion;

	@Index
	@Load
	Ref<BancoConciliacion> bancoConciliacion;

	private String observacion;

	public BancoConciliacionDetalle() {
	}

	public BancoConciliacionDetalle(Long conciliacionDetalleid) {
		this.conciliacionDetalleid = conciliacionDetalleid;
	}

	public Long getConciliacionDetalleid() {
		return conciliacionDetalleid;
	}

	public void setConciliacionDetalleid(Long conciliacionDetalleid) {
		this.conciliacionDetalleid = conciliacionDetalleid;
	}

	public BancoConciliacion getBancoConciliacion() {
		return bancoConciliacion.get();
	}

	public void setBancoConciliacion(BancoConciliacion bancoConciliacion) {
		this.bancoConciliacion = Ref.create(bancoConciliacion);
	}

	public Integer getNumeroRecibo() {
		return numeroRecibo;
	}

	public void setNumeroRecibo(Integer numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	public Double getValorAplicado() {
		return valorAplicado;
	}

	public void setValorAplicado(Double valorAplicado) {
		this.valorAplicado = valorAplicado;
	}

	public Integer getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Integer fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (conciliacionDetalleid != null ? conciliacionDetalleid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof BancoConciliacionDetalle)) {
			return false;
		}
		BancoConciliacionDetalle other = (BancoConciliacionDetalle) object;
		if ((this.conciliacionDetalleid == null && other.conciliacionDetalleid != null)
				|| (this.conciliacionDetalleid != null
						&& !this.conciliacionDetalleid.equals(other.conciliacionDetalleid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.ConciliacionDetalle[ conciliacionDetalleid=" + conciliacionDetalleid
				+ " ]";
	}

}
