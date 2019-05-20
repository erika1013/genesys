/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;

@Entity
public class Competencia extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long competenciaId;

	@Index
	@Load
	private Integer codigo;

	private String nombre;

	@Index
	@Load
	Ref<CompetenciaTipo> tipo;

	public Competencia() {
	}

	public Competencia(Long competenciaId) {
		this.competenciaId = competenciaId;
	}

	public Long getCompetenciaId() {
		return competenciaId;
	}

	public void setCompetenciaId(Long competenciaId) {
		this.competenciaId = competenciaId;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CompetenciaTipo getTipo() {
		return tipo.get();
	}

	public void setTipo(CompetenciaTipo tipo) {
		this.tipo = Ref.create(tipo);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (competenciaId != null ? competenciaId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Competencia)) {
			return false;
		}
		Competencia other = (Competencia) object;
		if ((this.competenciaId == null && other.competenciaId != null)
				|| (this.competenciaId != null && !this.competenciaId.equals(other.competenciaId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.Competencia[ competenciaId=" + competenciaId + " ]";
	}

}
