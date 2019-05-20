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
public class Asignacion extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long asignacionid;
	
	@Index
	private Integer asignacionNumero;
	

	private Date fechaAsignacion;
	private Date fechaEstado;

	@Index
	@Load

	Ref<Tarea> tarea;

	@Index
	@Load
	Ref<AsignacionEstado> estado;

	@Index
	@Load

	Ref<Usuario> usuario;

	public Asignacion() {
	}

	public Asignacion(Long asignacionid) {
		this.asignacionid = asignacionid;
	}

	public Long getclientecontactoid() {
		return asignacionid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (asignacionid != null ? asignacionid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Asignacion)) {
			return false;
		}
		Asignacion other = (Asignacion) object;
		if ((this.asignacionid == null && other.asignacionid != null)
				|| (this.asignacionid != null && !this.asignacionid.equals(other.asignacionid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.Asignacion[ asignacionid=" + asignacionid + " ]";
	}

	public Long getAsignacionid() {
		return asignacionid;
	}

	public void setAsignacionid(Long asignacionid) {
		this.asignacionid = asignacionid;
	}

	public Tarea getTarea() {
		return tarea.get();
	}

	public void setTarea(Tarea tarea) {
		this.tarea = Ref.create(tarea);
	}

	public AsignacionEstado getEstado() {
		return estado.get();
	}

	public void setEstado(AsignacionEstado estado) {
		this.estado = Ref.create(estado);
	}

	public Usuario getUsuario() {
		return usuario.get();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = Ref.create(usuario);
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public Date getFechaEstado() {
		return fechaEstado;
	}

	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public Integer getAsignacionNumero() {
		return asignacionNumero;
	}

	public void setAsignacionNumero(Integer asignacionNumero) {
		this.asignacionNumero = asignacionNumero;
	}

}
