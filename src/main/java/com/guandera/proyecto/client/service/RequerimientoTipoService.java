package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.proyecto.shared.model.RequerimientoTipo;

public interface RequerimientoTipoService {

	public void actualizar(RequerimientoTipo itemRequerimientoTipo);

	public RequerimientoTipo consultarPorId(Long tipoPersonaid);

	public List<RequerimientoTipo> consultarTodos();

	public long contar();

	public void crear(RequerimientoTipo itemRequerimientoTipo);

	public void eliminar(RequerimientoTipo itemRequerimientoTipo);

	public Long siguienteRegistro();

}
