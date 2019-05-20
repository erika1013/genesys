package com.guandera.core.client.service.admin;

import java.util.List;

import com.guandera.core.shared.model.CobroCalendario;
import com.guandera.core.shared.model.ControlMensual;
import com.guandera.core.shared.model.TipoServicio;
import com.guandera.core.shared.model.acceso.Usuario;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AdministracionService {

	boolean existePeriodo(String periodo);

	boolean existeRecibo(Integer nroRecibo);

	void anularRecibo(Integer nroRecibo, Usuario usuario);

	List<CobroCalendario> consultarCobroCalendario();

	void generarCobroEstudiante(Integer codigoEstudiante, CobroCalendario periodoCobro, Usuario usuario);

	CobroCalendario cargarPeriodo(Long calendarioid);

	boolean reciboAbonoCero(Integer nroRecibo);

	void generarCobroMesual(CobroCalendario periodoCobro, Usuario usuario);

	boolean existePeriodoRecibo(String periodo);

	void generarRecibosMesuales(CobroCalendario periodoCobro, Usuario usuario);

	List<TipoServicio> consultarServicios();

	TipoServicio cargarTipoServicio(Long tipoServicioid);

	void generarCobroEstudianteServicio(Integer codigoEstudiante, CobroCalendario periodoCobro,
			TipoServicio tipoServicio, Usuario usuario);

	void finalizarMes(CobroCalendario periodoCobro);

	void generarCobroMensualBloque(ControlMensual periodoGradoSede, Usuario usuario);

	void generarRecibosMensualesBloque(ControlMensual periodoGradoSede, Usuario usuario);

	void generarRecibosMensualesBloque07(ControlMensual periodoGradoSede, Usuario usuario);

}
