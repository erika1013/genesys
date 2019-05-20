package com.guandera.core.client.view.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.admin.CargarBancoService;
import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.server.service.admin.CargarBancoServiceImpl;
import com.guandera.core.shared.model.BancoPago;
import com.guandera.core.shared.model.BancoPagoEstado;

@ManagedBean(name = "cargarBancoMB")
@SessionScoped
public class CargarBancoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private CargarBancoService cargarBancoServicio;

	private String textoCSV;

	private Integer numeroCargados;
	private Integer numeroExistentes;

	public String getTextoCSV() {
		return textoCSV;
	}

	public void setTextoCSV(String textoCSV) {
		this.textoCSV = textoCSV;
	}

	public CargarBancoManagedBean() {
		cargarBancoServicio = new CargarBancoServiceImpl();

	}

	public String cargarPagosBanco() {

		numeroCargados = 0;
		numeroExistentes = 0;

		// try {

		StringTokenizer tokens = new StringTokenizer(textoCSV, "\n");
		while (tokens.hasMoreTokens()) {

			String linea = tokens.nextToken();
			// String[] campos = linea.split(";");

			BancoPago pago = new BancoPago();

			Long estadoid = (long) 1;

			BancoPagoEstado estado = cargarBancoServicio.cargarBancoPagoEstado(estadoid);

			pago.setTipo(Integer.valueOf((linea.substring(0, 4)).trim()));
			// System.out.println(" val1");
			pago.setFechaProceso(Integer.valueOf((linea.substring(4, 12)).trim()));
			// System.out.println(" val2");
			pago.setNumeroCuenta(Long.valueOf((linea.substring(12, 28)).trim()));
			// System.out.println(" val3");
			pago.setFechaSistema(Integer.valueOf((linea.substring(28, 36)).trim()));
			// System.out.println(" val4");
			pago.setHoraSistema(Integer.valueOf((linea.substring(36, 42)).trim()));
			// System.out.println(" val5");
			pago.setTerminal(Integer.valueOf((linea.substring(42, 48)).trim()));
			// System.out.println(" val6");
			pago.setTalon(Integer.valueOf((linea.substring(48, 56)).trim()));
			// System.out.println(" val7");

			pago.setValorChequeSigno(linea.substring(56, 57));
			// System.out.println(" val8");
			pago.setValorCheque(Double.valueOf((linea.substring(57, 73)).trim()));
			// System.out.println(" val9");

			// pago.setValorCheque(Double.valueOf( (linea.substring(57,
			// 74)).trim()));

			pago.setValorTotalSigno((linea.substring(75, 76)));
			pago.setValorTotal(Double.valueOf((linea.substring(76, 92)).trim()));
			// System.out.println(" val10");
			// pago.setValorTotal(Double.valueOf( (linea.substring(76,
			// 93)).trim()));

			pago.setValorBlancoSigno((linea.substring(94, 95)));
			pago.setValorBlanco(Double.valueOf((linea.substring(95, 111)).trim()));
			// pago.setValorBlanco(Double.valueOf( (linea.substring(95,
			// 112)).trim()));
			// System.out.println(" val11");
			pago.setOficinaRecaudo(Integer.valueOf((linea.substring(112, 117)).trim()));
			pago.setMotivo(Integer.valueOf((linea.substring(117, 121)).trim()));
			pago.setReferencia1(Long.valueOf((linea.substring(121, 137)).trim()));
			pago.setReferencia2(Long.valueOf((linea.substring(137, 153)).trim()));
			// System.out.println(" val12");
			pago.setSaldoSigno((linea.substring(153, 154)));
			pago.setSaldo(Double.valueOf((linea.substring(154, 170)).trim()));
			// System.out.println(" val13");
			// pago.setSaldo(Double.valueOf( (linea.substring(154,
			// 171)).trim()));

			pago.setIndicadorExento(Integer.valueOf((linea.substring(172, 174)).trim()));
			// System.out.println(" val13.1");
			pago.setValorExento(Double.valueOf((linea.substring(174, 193)).trim()));
			// System.out.println(" val13.2");
			pago.setCanal(Integer.valueOf((linea.substring(193, 195)).trim()));
			// System.out.println(" val13.3");
			pago.setCausalDevolucion(Integer.valueOf((linea.substring(195, 199)).trim()));
			// System.out.println(" val13.4");
			// pago.setJornada(Integer.valueOf(
			// (linea.substring(199)).trim()));
			pago.setJornada(0);
			// System.out.println(" val14");

			// SimpleDateFormat formatter = new
			// SimpleDateFormat("yyyy-MM-dd");
			// Date fechaNacimiento = formatter.parse(campos[6]);
			// estudiante.setFechanacimiento(fechaNacimiento);

			pago.setFechaCarge(new Date());
			pago.setEstado(estado);
			pago.setUsuarioCreacion(usuario());
			pago.setFechaCreacion(new Date());
			pago.setUsuarioModificacion(usuario());
			pago.setFechaModificacion(new Date());

			if (!cargarBancoServicio.exitePago(pago)) {

				cargarBancoServicio.cargarPagoBanco(pago);
				numeroCargados += 1;

			} else {
				numeroExistentes += 1;
			}

		}

		return respuesta();
		// } catch (Exception e) {
		// System.out.println("Error: " + e.getStackTrace());
		// System.out.println("Error: " + e.getMessage());
		// System.out.println("Error: " + e.toString());
		// mensajeError("PersistenceErrorOccured");
		// return null;
		// }

	}

	public String respuesta() {

		textoCSV = "";
		return "CargarBancoOK";
	}

	public Integer getNumeroCargados() {
		return numeroCargados;
	}

	public void setNumeroCargados(Integer numeroCargados) {
		this.numeroCargados = numeroCargados;
	}

	public Integer getNumeroExistentes() {
		return numeroExistentes;
	}

	public void setNumeroExistentes(Integer numeroExistentes) {
		this.numeroExistentes = numeroExistentes;
	}

}
