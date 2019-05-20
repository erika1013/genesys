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

public interface ClienteService {

	public void actualizar(Cliente cliente);

	public Cliente consultarPorId(Long clienteid);

	public List<Cliente> consultarTodos();

	public long contar();

	public void crear(Cliente cliente);

	public void eliminar(Cliente cliente);

	public Long siguienteRegistro();

	public List<TipoIdentificacion> consultarTiposIdentificacion();

	// Cliente Contacto

	public void actualizarClienteContacto(ClienteContacto itemClienteContacto);

	public ClienteContacto consultarClienteContactoPorId(Long itemClienteContacto);

	public List<ClienteContacto> consultarClienteContactos(Long clienteid);

	public void crearClienteContacto(ClienteContacto itemClienteContacto);

	public void eliminarClienteContacto(ClienteContacto itemClienteContacto);

	public List<ClienteCargo> consultarCargos();

	public boolean existeClienteContacto(Integer numeroidentificacion);

	public List<ClienteCargo> consultarClienteCargos(Long clienteid);

	public void crearCargo(ClienteCargo cargo);

	public void actualizarCargo(ClienteCargo cargo);

	public void eliminarCargo(ClienteCargo cargo);

}
