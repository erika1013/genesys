package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Acuerdo;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Servicio;
import com.guandera.core.shared.model.Cliente;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AcuerdoService {

	public void actualizar(Acuerdo acuerdo);

	public Acuerdo consultarPorId(Long acuerdoid);

	public List<Acuerdo> consultarTodos();

	public long contar();

	public void crear(Acuerdo acuerdo);

	public void eliminar(Acuerdo acuerdo);

	public Long siguienteRegistro();

	public List<Cliente> consultarClientes();

	public List<Compania> consultarCompanias();

	public List<Servicio> consultarServicios(Long companiaid);

}
