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
public class Tarea extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long tareaid;
	
	@Index
	private Integer tareaNumero;
	

	@Index
	@Load
	Ref<Requerimiento> requerimiento;

	@Index
	@Load
	Ref<TareaEstado> estado;

	@Index
	@Load
	Ref<Tiempo> tiempo;

	private Double prioridad;
	private String detalle;
	private Integer tiempoEstimado;
	private Date fechaInicio;
	private Date fechaFinal;
	private Date fechaActualizacion;

	public Tarea() {
	}

	public Tarea(Long tareaid) {
		this.tareaid = tareaid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tareaid != null ? tareaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Tarea)) {
			return false;
		}
		Tarea other = (Tarea) object;
		if ((this.tareaid == null && other.tareaid != null)
				|| (this.tareaid != null && !this.tareaid.equals(other.tareaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.Tarea[ tareaid=" + tareaid + " ]";
	}

	public Requerimiento getRequerimiento() {
		return requerimiento.get();
	}

	public void setRequerimiento(Requerimiento requerimiento) {
		this.requerimiento = Ref.create(requerimiento);
	}

	public Long getTareaid() {
		return tareaid;
	}

	public void setTareaid(Long tareaid) {
		this.tareaid = tareaid;
	}

	public Tiempo getTiempo() {
		return tiempo.get();
	}

	public void setTiempo(Tiempo tiempo) {
		this.tiempo = Ref.create(tiempo);
	}

	public TareaEstado getEstado() {
		return estado.get();
	}

	public void setEstado(TareaEstado estado) {
		this.estado = Ref.create(estado);
	}

	public Double getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Double prioridad) {
		this.prioridad = prioridad;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Integer getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(Integer tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Integer getTareaNumero() {
		return tareaNumero;
	}

	public void setTareaNumero(Integer tareaNumero) {
		this.tareaNumero = tareaNumero;
	}



}
