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

@Entity
public class Requerimiento extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long requerimientoid;

	@Index
	private Integer requerimientoNumero;
	
	@Index
	private Integer numero;
	
	
	private Integer prioridad;

	private String nombre;
	private String descripcion;
	private Date estadoFecha;

	@Index
	@Load
	Ref<RequerimientoTipo> tipo;
	@Index
	@Load
	Ref<Proyecto> proyecto;

	@Index
	@Load
	Ref<RequerimientoEstado> estado;

	public Requerimiento() {
	}

	public Requerimiento(Long requerimientoid) {
		this.requerimientoid = requerimientoid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (requerimientoid != null ? requerimientoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Requerimiento)) {
			return false;
		}
		Requerimiento other = (Requerimiento) object;
		if ((this.requerimientoid == null && other.requerimientoid != null)
				|| (this.requerimientoid != null && !this.requerimientoid.equals(other.requerimientoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.Requerimiento[ requerimientoid=" + requerimientoid + " ]";
	}

	public Long getRequerimientoid() {
		return requerimientoid;
	}

	public void setRequerimientoid(Long requerimientoid) {
		this.requerimientoid = requerimientoid;
	}

	public RequerimientoTipo getTipo() {
		return tipo.get();
	}

	public void setTipo(RequerimientoTipo tipo) {
		this.tipo = Ref.create(tipo);
	}

	public Proyecto getProyecto() {
		return proyecto.get();
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = Ref.create(proyecto);
	}

	public RequerimientoEstado getEstado() {
		return estado.get();
	}

	public void setEstado(RequerimientoEstado estado) {
		this.estado = Ref.create(estado);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Date getEstadoFecha() {
		return estadoFecha;
	}

	public void setEstadoFecha(Date estadoFecha) {
		this.estadoFecha = estadoFecha;
	}

	public Integer getRequerimientoNumero() {
		return requerimientoNumero;
	}

	public void setRequerimientoNumero(Integer requerimientoNumero) {
		this.requerimientoNumero = requerimientoNumero;
	}



}
