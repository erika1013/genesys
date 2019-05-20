package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.talento.shared.model.Institucion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface InstitucionService {

	public void actualizar(Institucion institucion);

	public Institucion consultarPorId(Long institucionid);

	public List<Institucion> consultarTodos();

	public long contar();

	public void crear(Institucion institucion);

	public void eliminar(Institucion institucion);

	public Integer siguienteCodigoInstitucion();

}
