package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.proyecto.shared.model.TareaEstado;

public interface TareaEstadoService {

	public void actualizar(TareaEstado itemTareaEstado);

	public TareaEstado consultarPorId(Long itemTareaEstado);

	public List<TareaEstado> consultarTodos();

	public long contar();

	public void crear(TareaEstado itemTareaEstado);

	public void eliminar(TareaEstado itemTareaEstado);

	public Long siguienteRegistro();

}
