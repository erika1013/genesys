package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.ClienteCargo;

public interface ClienteCargoService {

	public void actualizar(ClienteCargo itemClienteCargo);

	public ClienteCargo consultarPorId(Long tipoPersonaid);

	public List<ClienteCargo> consultarTodos();

	public long contar();

	public void crear(ClienteCargo itemClienteCargo);

	public void eliminar(ClienteCargo itemClienteCargo);

	public Long siguienteRegistro();

}
