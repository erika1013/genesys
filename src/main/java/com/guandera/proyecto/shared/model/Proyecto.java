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
import com.guandera.core.shared.model.CentroCostos;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.Compania;

@Entity
public class Proyecto extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long proyectoid;
	
	@Index
	private Integer proyectoNumero;
	
	@Index
	@Load
	Ref<ProyectoTipo> tipo;

	@Index
	@Load
	Ref<Compania> compania;

	@Index
	@Load
	Ref<CentroCostos> centroCostos;

	@Index
	@Load
	Ref<Cliente> cliente;

	@Index
	@Load
	Ref<ProyectoEtapa> etapa;

	private String nombre;
	private String objetivo;
	private String alcance;
	private String avance;
	private Date fechaInicial;
	private Date fechaFinal;
	private Date fechaActualizacion;
	private Date fechaEtapa;

	public Proyecto() {
	}

	public Proyecto(Long proyectoid) {
		this.proyectoid = proyectoid;
	}

	public Long getclientecontactoid() {
		return proyectoid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (proyectoid != null ? proyectoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Proyecto)) {
			return false;
		}
		Proyecto other = (Proyecto) object;
		if ((this.proyectoid == null && other.proyectoid != null)
				|| (this.proyectoid != null && !this.proyectoid.equals(other.proyectoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.Proyecto[ proyectoid=" + proyectoid + " ]";
	}

	public Long getProyectoid() {
		return proyectoid;
	}

	public void setProyectoid(Long proyectoid) {
		this.proyectoid = proyectoid;
	}

	public ProyectoTipo getTipo() {
		return tipo.get();
	}

	public void setProyectoTipo(ProyectoTipo tipo) {
		this.tipo = Ref.create(tipo);
	}

	public Compania getCompania() {
		return compania.get();
	}

	public void setCompania(Compania compania) {
		this.compania = Ref.create(compania);
	}

	public CentroCostos getCentroCostos() {
		return centroCostos.get();
	}

	public void setCentroCostos(CentroCostos centroCostos) {
		this.centroCostos = Ref.create(centroCostos);
	}

	public Cliente getCliente() {
		return cliente.get();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = Ref.create(cliente);
	}

	public ProyectoEtapa getEtapa() {
		return etapa.get();
	}

	public void setEtapa(ProyectoEtapa etapa) {
		this.etapa = Ref.create(etapa);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	public String getAvance() {
		return avance;
	}

	public void setAvance(String avance) {
		this.avance = avance;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
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

	public Date getFechaEtapa() {
		return fechaEtapa;
	}

	public void setFechaEtapa(Date fechaEtapa) {
		this.fechaEtapa = fechaEtapa;
	}

	public Integer getProyectoNumero() {
		return proyectoNumero;
	}

	public void setProyectoNumero(Integer proyectoNumero) {
		this.proyectoNumero = proyectoNumero;
	}



}
