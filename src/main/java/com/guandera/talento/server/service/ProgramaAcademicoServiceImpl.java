package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.client.service.ProgramaAcademicoService;
import com.guandera.talento.shared.model.ProgramaAcademico;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ProgramaAcademicoServiceImpl implements ProgramaAcademicoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ProgramaAcademico programaAcademico) {
		ofy().save().entity(programaAcademico).now();
	}

	@Override
	public ProgramaAcademico consultarPorId(Long id) {
		Key<ProgramaAcademico> k = Key.create(ProgramaAcademico.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ProgramaAcademico> consultarTodos() {
		return ofy().load().type(ProgramaAcademico.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ProgramaAcademico.class).count();
		return cont;

	}

	@Override
	public void crear(ProgramaAcademico programaAcademico) {
		ofy().save().entity(programaAcademico).now();
	}

	@Override
	public void eliminar(ProgramaAcademico programaAcademico) {
		ofy().delete().entity(programaAcademico).now();

	}

}