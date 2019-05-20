package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Convenio;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.Servicio;
import com.guandera.core.shared.model.TipoServicio;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ServicioService {

	public void actualizar(Servicio servicio);

	public Servicio consultarPorId(Long servicioid);

	public List<Servicio> consultarTodos();

	public long contar();

	public void crear(Servicio servicio);

	public void eliminar(Servicio servicio);

	public Long siguienteRegistro();

	public List<Compania> consultarCompanias();

}
