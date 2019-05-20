package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.CentroCostos;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Factura;
import com.guandera.core.shared.model.FacturaDetalle;
import com.guandera.core.shared.model.FacturaEstado;
import com.guandera.core.shared.model.Sede;

public interface FacturaService {

	public void actualizar(Factura factura);

	public Factura consultarPorId(Long facturaid);

	public List<Factura> consultarTodos();

	public long contar();

	public void crear(Factura factura);

	public void eliminar(Factura factura);

	public Long siguienteRegistro();

	public List<FacturaEstado> consultarEstados();

	public List<Cliente> consultarCliente();

	public void eliminarFacturaDetalle(FacturaDetalle itemFacturaDetalle);

	public void actualizarFacturaDetalle(FacturaDetalle itemFacturaDetalle);

	public void crearFacturaDetalle(FacturaDetalle itemFacturaDetalle);

	public List<FacturaDetalle> consultarFacturaDetalle(Long facturaid);

	public List<Compania> consultarCompanias();

	public List<Sede> consultarSedes(Long companiaid);

	public List<ClienteContacto> consultarContactosPorCliente(Long clienteid);

	public List<CentroCostos> consultarCentrosCostosPorCompania(Long companiaid);

}
