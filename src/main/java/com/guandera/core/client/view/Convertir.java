package com.guandera.core.client.view;

import java.util.regex.Pattern;

public class Convertir {

	private final String[] unidades = { "", "UN", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE" };
	private final String[] decenas = { "DIEZ", "ONCE", "DOCE", "TRECE", "CATORCE", "QUINCE", "DIECIS�IS", "DIECISIETE",
			"DIECIOCHO", "DIECINUEVE", "VEINTE", "TREINTA", "CUARENTA", "CINCUENTA", "SESENTA", "SETENTA", "OCHENTA",
			"NOVENTA" };
	private final String[] centenas = { "", "CIENTO ", "DOSCIENTOS ", "TRECIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ",
			"SEISCIENTOS ", "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS" };

	public Convertir() {
	}

	public String Convertir(String numero, String codigoMoneda) {
		String literal = "";
		// String decimal;
		numero = numero.replace(".", ",");
		if (numero.indexOf(",") == -1) {
			numero = numero + ",00";
		}
		if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
			String Num[] = numero.split(",");

			if (Integer.parseInt(Num[0]) == 0) {
				literal = "CERO";
			} else if (Integer.parseInt(Num[0]) > 999999) {
				literal = getMillones(Num[0]);
			} else if (Integer.parseInt(Num[0]) > 999) {
				literal = getMiles(Num[0]);
			} else if (Integer.parseInt(Num[0]) > 99) {
				literal = getCentenas(Num[0]);
			} else if (Integer.parseInt(Num[0]) > 9) {
				literal = getDecenas(Num[0]);
			} else {
				literal = getUnidades(Num[0]);
			}

			//return (literal + " PESOS");
			return (literal + " " + codigoMoneda);

		} else {
			return literal = null;
		}
	}

	public String getUnidades(String numero) {// 1 - 9

		String num = numero.substring(numero.length() - 1);
		return unidades[Integer.parseInt(num)];
	}

	public String getDecenas(String num) {// 99
		int n = Integer.parseInt(num);
		if (n < 10) {
			return getUnidades(num);
		} else if (n > 19) {
			String u = getUnidades(num);
			if (u.equals("")) {
				return decenas[Integer.parseInt(num.substring(0, 1)) + 8];
			} else {
				return decenas[Integer.parseInt(num.substring(0, 1)) + 8] + " Y " + u;
			}
		} else {// numeros entre 11 y 19
			return decenas[n - 10];
		}
	}

	public String getCentenas(String num) {// 999 o 099
		if (Integer.parseInt(num) > 99) {
			if (Integer.parseInt(num) == 100) {
				return "CIEN ";
			} else {
				return centenas[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
			}
		} else {
			return getDecenas(Integer.parseInt(num) + "");
		}
	}

	public String getMiles(String numero) {// 999 999

		String c = numero.substring(numero.length() - 3);

		String m = numero.substring(0, numero.length() - 3);
		String n = "";
		if (Integer.parseInt(m) > 0) {
			n = getCentenas(m);
			return n + "MIL " + getCentenas(c);
		} else {
			return "" + getCentenas(c);
		}

	}

	public String getMillones(String numero) { // 000 000 000

		String miles = numero.substring(numero.length() - 6);

		String millon = numero.substring(0, numero.length() - 6);
		String n = "";
		//if (millon.length() > 1) {
		if(Integer.parseInt(millon) > 1){
			n = getCentenas(millon) + " MILLONES ";
		} else {
			n = getUnidades(millon) + " MILLON ";
		}
		return n + getMiles(miles);
	}
}
