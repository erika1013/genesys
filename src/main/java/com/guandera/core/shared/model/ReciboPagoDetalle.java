/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;

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
public class ReciboPagoDetalle extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long recibodetalleid;

	@Index
	private Integer periodo;

	@Index
	private Integer ordenAplicacion;

	@Index
	@Load
	Ref<ReciboPago> reciboPago;

	@Index
	@Load
	Ref<Cobro> cobro;

	private Double cobrovalor;

	public ReciboPagoDetalle() {
	}

	public ReciboPagoDetalle(Long recibodetalleid) {
		this.recibodetalleid = recibodetalleid;
	}

	public Long getRecibodetalleid() {
		return recibodetalleid;
	}

	public void setRecibodetalleid(Long recibodetalleid) {
		this.recibodetalleid = recibodetalleid;
	}

	public ReciboPago getReciboPago() {
		return reciboPago.get();
	}

	public void setReciboPago(ReciboPago reciboPago) {
		this.reciboPago = Ref.create(reciboPago);
	}

	public Cobro getCobro() {
		return cobro.get();
	}

	public void setCobro(Cobro cobro) {
		this.cobro = Ref.create(cobro);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (recibodetalleid != null ? recibodetalleid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ReciboPagoDetalle)) {
			return false;
		}
		ReciboPagoDetalle other = (ReciboPagoDetalle) object;
		if ((this.recibodetalleid == null && other.recibodetalleid != null)
				|| (this.recibodetalleid != null && !this.recibodetalleid.equals(other.recibodetalleid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.ReciboPagoDetalle[ recibodetalleid=" + recibodetalleid + " ]";
	}

	public Double getCobrovalor() {
		return cobrovalor;
	}

	public void setCobrovalor(Double cobrovalor) {
		this.cobrovalor = cobrovalor;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getOrdenAplicacion() {
		return ordenAplicacion;
	}

	public void setOrdenAplicacion(Integer ordenAplicacion) {
		this.ordenAplicacion = ordenAplicacion;
	}

}
