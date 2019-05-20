/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.empleado.shared.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.talento.nomina.shared.model.NominaNovedad;
import com.guandera.talento.nomina.shared.model.NominaPago;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class EmpleadoContrato extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long empleadocontratoid;

	private Double contratosalario;

	private Long numeroContrato;

	private Date empleadocontratofechainicial;

	private Date empleadocontratofechafinal;

	@Index
	@Load
	Ref<Empleado> empleado;

	@Index
	@Load
	private Ref<EmpleadoContratoEstado> empleadoContratoEstado;

	@Index
	@Load
	Ref<TipoContrato> tipoContrato;

	@Index
	@Load
	private Ref<NominaNovedad> nominaNovedad;

	public EmpleadoContrato() {
	}

	public EmpleadoContrato(Long empleadocontratoid) {
		this.empleadocontratoid = empleadocontratoid;
	}

	public Long getEmpleadocontratoid() {
		return empleadocontratoid;
	}

	public void setEmpleadocontratoid(Long empleadocontratoid) {
		this.empleadocontratoid = empleadocontratoid;
	}

	public Double getContratosalario() {
		return contratosalario;
	}

	public void setContratosalario(Double contratosalario) {
		this.contratosalario = contratosalario;
	}

	public Date getEmpleadocontratofechainicial() {
		return empleadocontratofechainicial;
	}

	public void setEmpleadocontratofechainicial(Date empleadocontratofechainicial) {
		this.empleadocontratofechainicial = empleadocontratofechainicial;
	}

	public Date getEmpleadocontratofechafinal() {
		return empleadocontratofechafinal;
	}

	public void setEmpleadocontratofechafinal(Date empleadocontratofechafinal) {
		this.empleadocontratofechafinal = empleadocontratofechafinal;
	}


	public Empleado getEmpleado() {
		return empleado.get();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = Ref.create(empleado);
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato.get();
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = Ref.create(tipoContrato);
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (empleadocontratoid != null ? empleadocontratoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleadoContrato)) {
			return false;
		}
		EmpleadoContrato other = (EmpleadoContrato) object;
		if ((this.empleadocontratoid == null && other.empleadocontratoid != null)
				|| (this.empleadocontratoid != null && !this.empleadocontratoid.equals(other.empleadocontratoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.EmpleadoContrato[ empleadocontratoid=" + empleadocontratoid + " ]";
	}

	public Long getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(Long numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public EmpleadoContratoEstado getEmpleadoContratoEstado() {
		return empleadoContratoEstado.get();
	}

	public void setEmpleadoContratoEstado(EmpleadoContratoEstado empleadoContratoEstado) {
		this.empleadoContratoEstado = Ref.create(empleadoContratoEstado);
	}

	public NominaNovedad getNominaNovedad() {
		return nominaNovedad.get();
	}

	public void setNominaNovedad(NominaNovedad nominaNovedad) {
		this.nominaNovedad = Ref.create(nominaNovedad);
	}

}
