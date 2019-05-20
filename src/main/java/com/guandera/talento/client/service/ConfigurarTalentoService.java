package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.core.shared.model.Ciudad;
import com.guandera.core.shared.model.Departamento;
import com.guandera.talento.shared.model.*;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ConfigurarTalentoService {

	public void crearDepartamento(Departamento departamento);

	public void crearCiudad(Ciudad ciudad, Integer departamentoCodigo);

	public List<Departamento> consultarListaDepartamentos();

	public List<Ciudad> consultarListaCiudades();

}
