package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.shared.model.Ciudad;
import com.guandera.core.shared.model.CompaniaCargo;
import com.guandera.core.shared.model.Departamento;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoIdentificacion;
import com.guandera.talento.aspirante.shared.model.AspiranteEstado;
import com.guandera.talento.client.service.TalentoService;
import com.guandera.talento.empleado.shared.model.EmpleadoEstado;
import com.guandera.talento.shared.model.ReferenciaTipo;
import com.guandera.talento.shared.model.Competencia;
import com.guandera.talento.shared.model.CompetenciaTipo;
import com.guandera.talento.shared.model.EstadoCivil;
import com.guandera.talento.shared.model.FormacionEstado;
import com.guandera.talento.shared.model.Idioma;
import com.guandera.talento.shared.model.Institucion;
import com.guandera.talento.shared.model.NivelEducativo;
import com.guandera.talento.shared.model.ProgramaAcademico;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class TalentoServiceImpl implements TalentoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public List<Ciudad> listaCiudad() {
		return ofy().load().type(Ciudad.class).order("codigo").list();
	}

	@Override
	public List<EstadoCivil> listaEstadoCivil() {
		return ofy().load().type(EstadoCivil.class).order("codigo").list();
	}

	@Override
	public List<AspiranteEstado> listaAspiranteEstado() {
		return ofy().load().type(AspiranteEstado.class).order("codigo").list();
	}

	@Override
	public List<NivelEducativo> listaNivelEducativo() {
		return ofy().load().type(NivelEducativo.class).order("codigo").list();
	}

	@Override
	public List<CompetenciaTipo> listaCompetenciaTipo() {
		return ofy().load().type(CompetenciaTipo.class).order("codigo").list();
	}

	@Override
	public List<FormacionEstado> listaFormacionEstado() {
		return ofy().load().type(FormacionEstado.class).order("codigo").list();
	}

	@Override
	public List<ProgramaAcademico> listaProgramaAcademico() {
		return ofy().load().type(ProgramaAcademico.class).order("codigo").list();
	}

	@Override
	public List<Institucion> listaInstitucion() {
		return ofy().load().type(Institucion.class).order("codigo").list();
	}

	@Override
	public List<Competencia> listaCompetencia() {
		return ofy().load().type(Competencia.class).order("codigo").list();
	}

	@Override
	public List<ReferenciaTipo> listaReferenciaTipo() {
		return ofy().load().type(ReferenciaTipo.class).order("codigo").list();
	}

	@Override
	public List<Idioma> listaIdioma() {
		return ofy().load().type(Idioma.class).order("codigo").list();
	}

	@Override
	public List<Departamento> listaDepartamento() {
		return ofy().load().type(Departamento.class).order("codigo").list();
	}

	@Override
	public List<Ciudad> listaCiudadPorDepartamentoId(Long departamentoId) {

		Key<Departamento> k = Key.create(Departamento.class, departamentoId);
		Departamento departamento = ofy().load().key(k).now();

		return ofy().load().type(Ciudad.class).filter("departamento", departamento).order("codigo").list();
	}

	@Override
	public List<EmpleadoEstado> listaEmpleadoEstado() {
		return ofy().load().type(EmpleadoEstado.class).order("codigo").list();
	}

	@Override
	public List<Sede> consultarSedes() {
		return ofy().load().type(Sede.class).list();
	}

	@Override
	public List<TipoIdentificacion> consultarTiposIdentificacion() {
		return ofy().load().type(TipoIdentificacion.class).list();
	}

	@Override
	public List<CompaniaCargo> consultarCargos() {
		return ofy().load().type(CompaniaCargo.class).list();
	}

}