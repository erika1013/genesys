package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Convenio;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoServicio;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ConvenioService {

	public void actualizar(Convenio convenio);

	public Convenio consultarPorId(Long convenioid);

	public List<Convenio> consultarTodos();

	public long contar();

	public void crear(Convenio convenio);

	public void eliminar(Convenio convenio);

	public Long siguienteRegistro();

	public List<Sede> consultarSedes();

	public List<TipoServicio> consultarTiposConvenio();

}
