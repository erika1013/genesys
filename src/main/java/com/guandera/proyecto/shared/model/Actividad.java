/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.proyecto.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.core.shared.model.acceso.Usuario;

@Entity
public class Actividad extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long actividadid;

	@Index
	private Integer actividadNumero;

	@Index
	@Load
	private

	Ref<ActividadTipo> tipo;

	@Index
	@Load
	private

	Ref<Asignacion> asignacion;

	@Index
	@Load
	private Ref<Tiempo> tiempo;

	private String descripcion;
	private Date fecha;
	private Date actividadFechaCreacion;
	private Date actividadFechaModificacion;
	private Double tiempoempleado;

	private Integer year;
	private Integer mes;

	@Index
	@Load
	private Integer periodo;

	@Index
	@Load
	private

	Ref<Usuario> usuario;

	public Actividad() {
	}

	public Actividad(Long actividadid) {
		this.actividadid = actividadid;
	}

	public Long getclientecontactoid() {
		return actividadid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (actividadid != null ? actividadid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Actividad)) {
			return false;
		}
		Actividad other = (Actividad) object;
		if ((this.actividadid == null && other.actividadid != null)
				|| (this.actividadid != null && !this.actividadid.equals(other.actividadid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.Actividad[ actividadid=" + actividadid + " ]";
	}

	public Long getActividadid() {
		return actividadid;
	}

	public void setActividadid(Long actividadid) {
		this.actividadid = actividadid;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getActividadFechaCreacion() {
		return actividadFechaCreacion;
	}

	public void setActividadFechaCreacion(Date actividadFechaCreacion) {
		this.actividadFechaCreacion = actividadFechaCreacion;
	}

	public Date getActividadFechaModificacion() {
		return actividadFechaModificacion;
	}

	public void setActividadFechaModificacion(Date actividadFechaModificacion) {
		this.actividadFechaModificacion = actividadFechaModificacion;
	}

	public Double getTiempoempleado() {
		return tiempoempleado;
	}

	public void setTiempoempleado(Double tiempoempleado) {
		this.tiempoempleado = tiempoempleado;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public ActividadTipo getTipo() {
		return tipo.get();
	}

	public void setTipo(ActividadTipo tipo) {
		this.tipo = Ref.create(tipo);
	}

	public Asignacion getAsignacion() {
		return asignacion.get();
	}

	public void setAsignacion(Asignacion asignacion) {
		this.asignacion = Ref.create(asignacion);
	}

	public Tiempo getTiempo() {
		return tiempo.get();
	}

	public void setTiempo(Tiempo tiempo) {
		this.tiempo = Ref.create(tiempo);
	}

	public Usuario getUsuario() {
		return usuario.get();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = Ref.create(usuario);
	}

	public Integer getActividadNumero() {
		return actividadNumero;
	}

	public void setActividadNumero(Integer actividadNumero) {
		this.actividadNumero = actividadNumero;
	}

}
