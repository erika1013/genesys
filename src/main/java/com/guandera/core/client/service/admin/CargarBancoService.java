package com.guandera.core.client.service.admin;

import com.guandera.core.shared.model.BancoPago;
import com.guandera.core.shared.model.BancoPagoEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CargarBancoService {

	BancoPagoEstado cargarBancoPagoEstado(Long estadoid);

	void cargarPagoBanco(BancoPago pago);

	boolean exitePago(BancoPago pago);

	void aplicarPagoBanco();

}
