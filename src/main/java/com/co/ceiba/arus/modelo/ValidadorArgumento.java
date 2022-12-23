package com.co.ceiba.arus.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class ValidadorArgumento {

	private static final String VACIO = "";

	private ValidadorArgumento() {
	}

	public static String validarObligatorio(Object valor, String mensaje) {
		if (valor == null || valor.equals(VACIO)) {
			return mensaje;
		}
		return VACIO;
	}

	public static String validarLongitud(String valor, int longitud, String mensaje) { // 123456 < 1234
		if (valor.length() > longitud) {
			return mensaje;
		}
		return VACIO;
	}

	public static String validarPositivo(long valor, String mensaje) {
		if (valor <= 0) {
			return mensaje;
		}
		return VACIO;
	}

	public static String validarSoloNumeros(String valor, String mensaje) {
		try {
			Long.parseLong(valor);
		} catch (NumberFormatException numberFormatException) {
			return mensaje;
		}
		return VACIO;
	}

	public static String validarSiSoloContieneNumerosYLetras(String valor, String mensaje) {
		for (int x = 0; x < valor.length(); x++) {
			char c = valor.charAt(x);
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ' || (c >= '0' && c <= '9'))) {
				return mensaje;
			}
		}
		return VACIO;
	}

	public static String validarSiSoloContieneLetras(String valor, String mensaje) {
		for (int x = 0; x < valor.length(); x++) {
			char c = valor.charAt(x);
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
				return mensaje;
			}
		}
		return VACIO;
	}

	public static String validarSiContieneAlmenosUnNumeroYUnaLetra(String valor, String mensaje) {
		int contieneLetras = 0;
		int contieneNumeros = 0;
		for (int x = 0; x < valor.length(); x++) {
			char c = valor.charAt(x);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				contieneLetras++;
			} else if (c >= '0' && c <= '9') {
				contieneNumeros++;
			}
		}
		if (contieneLetras == 0 || contieneNumeros == 0) {
			return mensaje;
		}
		return VACIO;
	}

	public static String validadorFechaPermitidaYFormateada(String fecha, String mensajeError1,String mensajeError2)  {
        try{
            Date fechaDiaActual = new Date(System.currentTimeMillis());
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaIngresada = date.parse(fecha);

            if(fechaIngresada.after(fechaDiaActual)){
                return mensajeError1;                
            }
            
        }catch (ParseException a){
            return mensajeError2;
        }
        return VACIO;
    
    }
	

}
