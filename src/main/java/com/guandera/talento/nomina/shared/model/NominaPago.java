/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.nomina.shared.model;

import java.io.Serializable;
import java.util.List;

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
public class NominaPago extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long pagonominaid;

	private String ano;
	private String mes;
	
	@Index
	private String periodo;

	@Index
	private Integer numeroNomina;
	
	private Integer diasLaborados;

	private Double valorNetoPago;

	private Double totalDevengado;

	private Double totalDeducciones;

	@Index
	@Load
	Ref<EmpleadoContrato> empleadoContrato;

	public NominaPago() {
	}

	public NominaPago(Long pagonominaid) {
		this.pagonominaid = pagonominaid;
	}

	public Long getPagonominaid() {
		return pagonominaid;
	}

	public void setPagonominaid(Long pagonominaid) {
		this.pagonominaid = pagonominaid;
	}



	public EmpleadoContrato getEmpleadoContrato() {
		return empleadoContrato.get();
	}

	public void setEmpleadoContrato(EmpleadoContrato empleadoContrato) {
		this.empleadoContrato = Ref.create(empleadoContrato);
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pagonominaid != null ? pagonominaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof NominaPago)) {
			return false;
		}
		NominaPago other = (NominaPago) object;
		if ((this.pagonominaid == null && other.pagonominaid != null)
				|| (this.pagonominaid != null && !this.pagonominaid.equals(other.pagonominaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.NominaPago[ pagonominaid=" + pagonominaid + " ]";
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public Integer getNumeroNomina() {
		return numeroNomina;
	}

	public void setNumeroNomina(Integer numeroNomina) {
		this.numeroNomina = numeroNomina;
	}

	public Double getValorNetoPago() {
		return valorNetoPago;
	}

	public void setValorNetoPago(Double valorNetoPago) {
		this.valorNetoPago = valorNetoPago;
	}

	public Double getTotalDevengado() {
		return totalDevengado;
	}

	public void setTotalDevengado(Double totalDevengado) {
		this.totalDevengado = totalDevengado;
	}

	public Double getTotalDeducciones() {
		return totalDeducciones;
	}

	public void setTotalDeducciones(Double totalDeducciones) {
		this.totalDeducciones = totalDeducciones;
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

	public Integer getDiasLaborados() {
		return diasLaborados;
	}

	public void setDiasLaborados(Integer diasLaborados) {
		this.diasLaborados = diasLaborados;
	}

}
