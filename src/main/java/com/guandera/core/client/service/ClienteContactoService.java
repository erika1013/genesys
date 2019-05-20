package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteCargo;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.TipoIdentificacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ClienteContactoService {

	public void actualizar(ClienteContacto itemClienteContacto);

	public ClienteContacto consultarPorId(Long itemClienteContacto);

	public List<ClienteContacto> consultarTodos();

	public long contar();

	public void crear(ClienteContacto itemClienteContacto);

	public void eliminar(ClienteContacto itemClienteContacto);

	public Long siguienteRegistro();

	public List<TipoIdentificacion> consultarTiposIdentificacion();

	public List<ClienteCargo> consultarCargos();

	public List<Cliente> consultarClientes();

	public boolean existeClienteContacto(Integer numeroidentificacion);

}
