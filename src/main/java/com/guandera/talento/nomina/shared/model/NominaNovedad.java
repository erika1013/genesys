/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.nomina.shared.model;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.talento.empleado.shared.model.EmpleadoContrato;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class NominaNovedad extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long nominanovedadid;

	@Index
	@Load
	private Ref<EmpleadoContrato> empleadoContrato;

	@Index
	private Integer nominanovedadcodigo;

	private String nominanovedadnombre;
	private String ano;
	private String mes;
	@Index
	private String periodo;
	private boolean estado;

	private Long tipo;
	private Long nominanovedadvalor;

	private String nominanovedadobservacion;

	private List<Key<NominaPagoDetalle>> detalleContratoList;

	public NominaNovedad() {
	}

	public NominaNovedad(Long nominanovedadid) {
		this.nominanovedadid = nominanovedadid;
	}

	public Integer getNominanovedadcodigo() {
		return nominanovedadcodigo;
	}

	public void setNominanovedadcodigo(Integer nominanovedadcodigo) {
		this.nominanovedadcodigo = nominanovedadcodigo;
	}

	public Long getNominanovedadid() {
		return nominanovedadid;
	}

	public void setNominanovedadid(Long nominanovedadid) {
		this.nominanovedadid = nominanovedadid;
	}

	public String getNominanovedadnombre() {
		return nominanovedadnombre;
	}

	public void setNominanovedadnombre(String nominanovedadnombre) {
		this.nominanovedadnombre = nominanovedadnombre;
	}

	public Long getNominanovedadvalor() {
		return nominanovedadvalor;
	}

	public void setNominanovedadvalor(Long nominanovedadvalor) {
		this.nominanovedadvalor = nominanovedadvalor;
	}

	public List<Key<NominaPagoDetalle>> getDetalleContratoList() {
		return detalleContratoList;
	}

	public void setDetalleContratoList(List<Key<NominaPagoDetalle>> detalleContratoList) {
		this.detalleContratoList = detalleContratoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (nominanovedadid != null ? nominanovedadid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof NominaNovedad)) {
			return false;
		}
		NominaNovedad other = (NominaNovedad) object;
		if ((this.nominanovedadid == null && other.nominanovedadid != null)
				|| (this.nominanovedadid != null && !this.nominanovedadid.equals(other.nominanovedadid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.NominaNovedad[ nominanovedad=" + nominanovedadid + " ]";
	}

	public String getNominanovedadobservacion() {
		return nominanovedadobservacion;
	}

	public void setNominanovedadobservacion(String nominanovedadobservacion) {
		this.nominanovedadobservacion = nominanovedadobservacion;
	}

	public EmpleadoContrato getEmpleadoContrato() {
		return empleadoContrato.get();
	}

	public void setEmpleadoContrato(EmpleadoContrato empleadoContrato) {
		this.empleadoContrato = Ref.create(empleadoContrato);
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
