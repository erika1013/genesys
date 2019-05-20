package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.guandera.core.shared.model.Ciudad;
import com.guandera.core.shared.model.Departamento;
import com.guandera.talento.client.service.ConfigurarTalentoService;
import com.guandera.talento.shared.model.*;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ConfigurarTalentoServiceImpl implements ConfigurarTalentoService, Serializable {

	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void crearDepartamento(Departamento departamento) {
		ofy().save().entity(departamento).now();

	}

	@Override
	public List<Departamento> consultarListaDepartamentos() {
		return ofy().load().type(Departamento.class).list();
	}

	@Override
	public List<Ciudad> consultarListaCiudades() {
		return ofy().load().type(Ciudad.class).list();
	}

	@Override
	public void crearCiudad(Ciudad ciudad, Integer departamentoCodigo) {

		if (!existeCiudad(ciudad.getCodigo())) {
			Departamento departamento = ofy().load().type(Departamento.class).filter("codigo", departamentoCodigo)
					.first().now();
			ciudad.setDepartamento(departamento);

			ofy().save().entity(ciudad).now();
		} else {

		}

	}

	private boolean existeCiudad(Integer codigo) {
		Ciudad ciudad = ofy().load().type(Ciudad.class).filter("codigo", codigo).first().now();

		if (ciudad != null) {
			System.out.println("Ciudad Exite: " + codigo);
			return true;
		} else {

			System.out.println("Ciudad Existe: " + codigo);

			return false;
		}
	}

}