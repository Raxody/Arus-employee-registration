package com.co.ceiba.arus.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.co.ceiba.arus.modelo.excepcion.*;

public class ValidadorArgumentoException {
	
	
	 public static void validarObligatorioException(Object valor, String mensaje) {
	        if (valor == null || valor.equals("")) {
	            throw new ExcepcionValorObligatorio(mensaje);
	        }
	    }

	    public static void validarLongitudException(String valor, int longitud, String mensaje) {
	        if (valor.length() > longitud) {
	            throw new ExcepcionLongitudValor(mensaje);
	        }
	    }

	    public static void validarPositivoException(long valor, String mensaje) {
	        if (valor <= 0) {
	            throw new ExcepcionValorInvalido(mensaje);
	        }
	    }

	    public static void validarSoloNumerosException(String valor, String mensaje) {
	        try {
	            Long.parseLong(valor);
	        } catch (NumberFormatException numberFormatException) {
	            throw new ExcepcionValorInvalido(mensaje);
	        }
	    }

	    public static void validarSiSoloContieneNumerosYLetrasException(String valor, String mensaje) {
	        for (int x = 0; x < valor.length(); x++) {
	            char c = valor.charAt(x);
	            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ' || (c >= '0' && c <= '9'))) {
	                throw new ExcepcionValorInvalido(mensaje);
	            }
	        }
	    }

	    public static void validarSiSoloContieneLetrasException(String valor, String mensaje) {
	        for (int x = 0; x < valor.length(); x++) {
	            char c = valor.charAt(x);
	            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
	                throw new ExcepcionValorInvalido(mensaje);
	            }
	        }
	    }

	    public static void validarSiContieneAlmenosUnNumeroYUnaLetraException(String valor, String mensaje) {
	        int contieneLetras = 0, contieneNumeros = 0;
	        for (int x = 0; x < valor.length(); x++) {
	            char c = valor.charAt(x);
	            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
	                contieneLetras++;
	            } else if (c >= '0' && c <= '9') {
	                contieneNumeros++;
	            }
	        }
	        if (contieneLetras == 0 || contieneNumeros == 0) {
	            throw new ExcepcionValorInvalido(mensaje);
	        }
	    }
	    
		public static void validadorFechaPermitidaYFormateadaException(String fecha, String mensajeError1,String mensajeError2)  {
	        try{
	            Date fechaDiaActual = new Date(System.currentTimeMillis());
	            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	            Date fechaIngresada = date.parse(fecha);

	            if(fechaIngresada.after(fechaDiaActual)){
	            	throw new ExcepcionValorInvalido(mensajeError1);                
	            }
	            
	        }catch (ParseException a){
	        	throw new ExcepcionValorInvalido(mensajeError2);
	        }
	    
	    }

}
