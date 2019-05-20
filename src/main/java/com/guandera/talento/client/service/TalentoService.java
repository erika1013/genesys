package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.talento.shared.model.ReferenciaTipo;
import com.guandera.core.shared.model.Ciudad;
import com.guandera.core.shared.model.CompaniaCargo;
import com.guandera.core.shared.model.Departamento;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoIdentificacion;
import com.guandera.talento.aspirante.shared.model.AspiranteEstado;
import com.guandera.talento.empleado.shared.model.EmpleadoEstado;
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
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface TalentoService {

	public List<Ciudad> listaCiudad();

	public List<EstadoCivil> listaEstadoCivil();

	public List<AspiranteEstado> listaAspiranteEstado();

	public List<NivelEducativo> listaNivelEducativo();

	public List<CompetenciaTipo> listaCompetenciaTipo();

	public List<FormacionEstado> listaFormacionEstado();

	public List<ProgramaAcademico> listaProgramaAcademico();

	public List<Institucion> listaInstitucion();

	public List<Competencia> listaCompetencia();

	public List<ReferenciaTipo> listaReferenciaTipo();

	public List<Idioma> listaIdioma();

	public List<Departamento> listaDepartamento();

	public List<Ciudad> listaCiudadPorDepartamentoId(Long departamentoId);

	public List<EmpleadoEstado> listaEmpleadoEstado();

	public List<Sede> consultarSedes();

	public List<TipoIdentificacion> consultarTiposIdentificacion();

	public List<CompaniaCargo> consultarCargos();

}
