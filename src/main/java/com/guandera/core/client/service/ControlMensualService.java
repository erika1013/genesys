package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.CobroCalendario;
import com.guandera.core.shared.model.ControlMensual;
import com.guandera.core.shared.model.acceso.Usuario;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ControlMensualService {

	public void actualizar(ControlMensual controlMensual);

	public ControlMensual consultarPorId(Long controlMensualid);

	public List<ControlMensual> consultarTodos();

	public long contar();

	public void crear(ControlMensual controlMensual);

	public void eliminar(ControlMensual controlMensual);

	public List<CobroCalendario> consultarCalendarios();

	public CobroCalendario cargarPeriodo(Long calendarioid);

	public boolean existePeriodo(CobroCalendario periodoCobro);

	public void generarControlMesual(CobroCalendario periodoCobro, Usuario usuario);

	public void finalizarMes(ControlMensual itemSeleccionado);

	public void generarCobroMora(ControlMensual itemSeleccionado, Usuario usuario);

}
