package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.proyecto.shared.model.RequerimientoEstado;

public interface RequerimientoEstadoService {

	public void actualizar(RequerimientoEstado itemRequerimientoEstado);

	public RequerimientoEstado consultarPorId(Long tipoPersonaid);

	public List<RequerimientoEstado> consultarTodos();

	public long contar();

	public void crear(RequerimientoEstado itemRequerimientoEstado);

	public void eliminar(RequerimientoEstado itemRequerimientoEstado);

	public Long siguienteRegistro();

}
