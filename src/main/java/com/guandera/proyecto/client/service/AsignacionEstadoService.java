package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.proyecto.shared.model.AsignacionEstado;

public interface AsignacionEstadoService {

	public void actualizar(AsignacionEstado itemAsignacionEstado);

	public AsignacionEstado consultarPorId(Long tipoPersonaid);

	public List<AsignacionEstado> consultarTodos();

	public long contar();

	public void crear(AsignacionEstado itemAsignacionEstado);

	public void eliminar(AsignacionEstado itemAsignacionEstado);

	public Long siguienteRegistro();

}
