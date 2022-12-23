package com.co.ceiba.arus.modelo.entidad;

import static com.co.ceiba.arus.modelo.ValidadorArgumento.*;
import static com.co.ceiba.arus.modelo.ValidadorArgumentoException.*;

import java.io.Serializable;

public class Documento implements Serializable {

	private static final long serialVersionUID = 3L;

	private static final String CEDULA_DE_CIUDADANIA = "Cédula de ciudadania";
	private static final String CEDULA_DE_EXTRANJERIA = "Cédula de extranjeria";
	private static final String REGISTRO_CIVIL = "Registro civil";
	private static final String TARJETA_DE_IDENTIDAD = "Tarjeta de Identidad";

	private static final String SE_DEBE_INGRESAR_EL_TIPO_DE_DOCUMENTO = "Se debe ingresar el tipo de documento";
	private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_DOCUMENTO = "Se debe ingresar el número de documento";

	private static final String SOLO_SE_ACEPTAN_NUMEROS_EN_LA_CEDULA_DE_CIUDADANIA = "Solo se aceptan números en la cédula de ciudadanía";
	private static final int LONGITUD_MINIMA_CEDULA_DE_CIUDADANIA = 10;
	private static final String LA_CEDULA_DE_CIUDADANIA_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A = "La cédula de ciudadanía debe tener una longitud menor o igual a %s";
	private static final String LA_CEDULA_DE_CIUDADANIA_DEBE_SER_POSITIVA = "La cédula de ciudadania debe ser positiva";

	private static final String SOLO_SE_ACEPTAN_NUMEROS_Y_LETRAS_EN_LA_CEDULA_DE_EXTRANJERIA = "Solo se aceptan números y letras en la cédula de extranjería";
	private static final String LA_CEDULA_DE_EXTRANJERIA_DEBE_TENER_MINIMO_UN_NUMERO_Y_UNA_LETRA = "La cédula de extranjería debe tener mínimo un número y una letra";
	private static final int LONGITUD_MINIMA_CEDULA_DE_EXTRANJERIA = 14;
	private static final String LA_CEDULA_DE_EXTRANJERIA_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A = "La cédula de extranjería debe tener una longitud menor o igual a %s";
	private static final String LA_CEDULA_DE_EXTRANJERIA_DEBE_SER_POSITIVA = "La cédula de extranjería debe ser positiva";

	private static final String SOLO_SE_ACEPTAN_NUMEROS_EN_EL_REGISTRO_CIVIL = "Solo se aceptan números en el registro civil";
	private static final String EL_REGISTRO_CIVIL_DEBE_SER_POSITIVO = "El registro civil debe ser positivo";

	private static final String SOLO_SE_ACEPTAN_NUMEROS_EN_LA_TARJETA_DE_IDENTIDAD = "Solo se aceptan números en la tarjeta de identidad";
	private static final String LA_TARJETA_DE_IDENTIDAD_DEBE_SER_POSITIVA = "La tarjeta de identidad debe ser positiva";
	
	private static final String SELECCIONA_EL_TIPO_DEL_DOCUMENTO = "Selecciona el tipo del documento";
	

	private String numeroDocumento;
	private String tipoDocumento;

    public Documento(String tipoDocumento,String numeroDocumento) {

        validarObligatorioException(tipoDocumento, SE_DEBE_INGRESAR_EL_TIPO_DE_DOCUMENTO);
        validarObligatorioException(numeroDocumento, SE_DEBE_INGRESAR_EL_NUMERO_DE_DOCUMENTO);
        this.tipoDocumento = tipoDocumento;
        switch (tipoDocumento){
            case CEDULA_DE_CIUDADANIA:{
                validarLongitudException(numeroDocumento,LONGITUD_MINIMA_CEDULA_DE_CIUDADANIA,String.format(LA_CEDULA_DE_CIUDADANIA_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A,LONGITUD_MINIMA_CEDULA_DE_CIUDADANIA));
                validacionesNumericasPositivasException(numeroDocumento, SOLO_SE_ACEPTAN_NUMEROS_EN_LA_CEDULA_DE_CIUDADANIA, LA_CEDULA_DE_CIUDADANIA_DEBE_SER_POSITIVA);
                break;
            }
            case CEDULA_DE_EXTRANJERIA:{
                validarSiSoloContieneNumerosYLetrasException(numeroDocumento,SOLO_SE_ACEPTAN_NUMEROS_Y_LETRAS_EN_LA_CEDULA_DE_EXTRANJERIA);
                validarSiContieneAlmenosUnNumeroYUnaLetra(numeroDocumento,LA_CEDULA_DE_EXTRANJERIA_DEBE_TENER_MINIMO_UN_NUMERO_Y_UNA_LETRA);
                validarLongitud(numeroDocumento,LONGITUD_MINIMA_CEDULA_DE_EXTRANJERIA,String.format(LA_CEDULA_DE_EXTRANJERIA_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A,LONGITUD_MINIMA_CEDULA_DE_EXTRANJERIA));
                this.numeroDocumento = numeroDocumento;
                break;
            }
            case REGISTRO_CIVIL:{
                validacionesNumericasPositivasException(numeroDocumento, SOLO_SE_ACEPTAN_NUMEROS_EN_EL_REGISTRO_CIVIL, EL_REGISTRO_CIVIL_DEBE_SER_POSITIVO);
                break;
            }
            case TARJETA_DE_IDENTIDAD:{
                validacionesNumericasPositivasException(numeroDocumento, SOLO_SE_ACEPTAN_NUMEROS_EN_LA_TARJETA_DE_IDENTIDAD, LA_TARJETA_DE_IDENTIDAD_DEBE_SER_POSITIVA);
                break;
            }
            default:{
            	
            	break;
            }
        }
    }

    private void validacionesNumericasPositivasException(String numeroDocumento, String soloSeAceptanNumerosDelDocumento, String elnumeroDelDocumentoDebeSerPositivo) {
        validarSoloNumerosException(numeroDocumento, soloSeAceptanNumerosDelDocumento);
        validarPositivoException(Long.parseLong(numeroDocumento), elnumeroDelDocumentoDebeSerPositivo);
        this.numeroDocumento = numeroDocumento;
    }
	
	
	public Documento() {

	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Override
	public String toString() {
		return "Documento [numeroDocumento=" + numeroDocumento + ", tipoDocumento=" + tipoDocumento + "]";
	}

}