package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.ProveedorPagoEstado;

public interface ProveedorPagoEstadoService {

	public void actualizar(ProveedorPagoEstado estado);

	public ProveedorPagoEstado consultarPorId(Long estadoid);

	public List<ProveedorPagoEstado> consultarTodos();

	public long contar();

	public void crear(ProveedorPagoEstado estado);

	public void eliminar(ProveedorPagoEstado estado);

	public Long siguienteRegistro();

}
