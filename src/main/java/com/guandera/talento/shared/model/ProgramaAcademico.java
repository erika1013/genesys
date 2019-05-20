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

/**
 * 
 * @author FrediJavier
 */
@Entity
public class ProgramaAcademico extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long programaAcademicoId;

	@Index
	private Integer codigo;

	private String nombre;

	@Index
	@Load
	Ref<NivelEducativo> nivelEducativo;

	public ProgramaAcademico() {
	}

	public ProgramaAcademico(Long programaAcademicoId) {
		this.programaAcademicoId = programaAcademicoId;
	}

	public Long getProgramaAcademicoId() {
		return programaAcademicoId;
	}

	public void setProgramaAcademicoId(Long programaAcademicoId) {
		this.programaAcademicoId = programaAcademicoId;
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

	public NivelEducativo getNivelEducativo() {
		return nivelEducativo.get();
	}

	public void setNivelEducativo(NivelEducativo nivelEducativo) {
		this.nivelEducativo = Ref.create(nivelEducativo);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (programaAcademicoId != null ? programaAcademicoId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProgramaAcademico)) {
			return false;
		}
		ProgramaAcademico other = (ProgramaAcademico) object;
		if ((this.programaAcademicoId == null && other.programaAcademicoId != null)
				|| (this.programaAcademicoId != null && !this.programaAcademicoId.equals(other.programaAcademicoId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.ProgramaAcademico[ programaAcademicoId=" + programaAcademicoId + " ]";
	}

}
