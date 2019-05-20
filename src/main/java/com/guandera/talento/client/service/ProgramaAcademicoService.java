package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.talento.shared.model.ProgramaAcademico;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ProgramaAcademicoService {

	public void actualizar(ProgramaAcademico programaAcademico);

	public ProgramaAcademico consultarPorId(Long programaAcademicoid);

	public List<ProgramaAcademico> consultarTodos();

	public long contar();

	public void crear(ProgramaAcademico programaAcademico);

	public void eliminar(ProgramaAcademico programaAcademico);

}
