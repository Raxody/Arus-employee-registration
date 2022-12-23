package com.co.ceiba.arus.modelo.entidad;

import static com.co.ceiba.arus.modelo.ValidadorArgumentoException.*;

import java.io.Serializable;

public class Persona implements Serializable {

	private static final long serialVersionUID = 2L;

	private static final String SE_DEBE_INGRESAR_EL_PRIMER_NOMBRE = "Se debe ingresar el primer nombre";
	private static final String SOLO_SE_ACEPTAN_LETRAS_EN_EL_PRIMER_NOMBRE = "Solo se aceptan letras en el primer nombre";

	private static final String SE_DEBE_INGRESAR_EL_SEGUNDO_NOMBRE = "Se debe ingresar el segundo nombre";
	private static final String SOLO_SE_ACEPTAN_LETRAS_EN_EL_SEGUNDO_NOMBRE = "Solo se aceptan letras en el segundo nombre";

	private static final String SE_DEBE_INGRESAR_EL_PRIMER_APELLIDO = "Se debe ingresar el primer apellido";
	private static final String SOLO_SE_ACEPTAN_LETRAS_EN_EL_PRIMER_APELLIDO = "Solo se aceptan letras en el primer apellido";

	private static final String SE_DEBE_INGRESAR_EL_SEGUNDO_APELLIDO = "Se debe ingresar el segundo apellido";
	private static final String SOLO_SE_ACEPTAN_LETRAS_EN_EL_SEGUNDO_APELLIDO = "Solo se aceptan letras en el segundo apellido";
	private String primerNombre, segundoNombre, primerApellido, segundoApellido;
	private Documento documento;	

	public Persona(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Documento documento) {
        validacionesNombresObligatorioYSoloLetrasException(primerNombre, SE_DEBE_INGRESAR_EL_PRIMER_NOMBRE, SOLO_SE_ACEPTAN_LETRAS_EN_EL_PRIMER_NOMBRE);
        validacionesNombresObligatorioYSoloLetrasException(segundoNombre, SE_DEBE_INGRESAR_EL_SEGUNDO_NOMBRE, SOLO_SE_ACEPTAN_LETRAS_EN_EL_SEGUNDO_NOMBRE);
        validacionesNombresObligatorioYSoloLetrasException(primerApellido, SE_DEBE_INGRESAR_EL_PRIMER_APELLIDO, SOLO_SE_ACEPTAN_LETRAS_EN_EL_PRIMER_APELLIDO);
        validacionesNombresObligatorioYSoloLetrasException(segundoApellido, SE_DEBE_INGRESAR_EL_SEGUNDO_APELLIDO, SOLO_SE_ACEPTAN_LETRAS_EN_EL_SEGUNDO_APELLIDO);

        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.documento = documento;
    }

    private void validacionesNombresObligatorioYSoloLetrasException(String parteDelNombre, String oligatorioParteDelNombre
            , String soloLetrasParteDelNombre) {
        validarObligatorioException(parteDelNombre, oligatorioParteDelNombre);
        validarSiSoloContieneLetrasException(parteDelNombre, soloLetrasParteDelNombre);
    }

	
	public Persona() {

	}
	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "Persona [primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", primerApellido="
				+ primerApellido + ", segundoApellido=" + segundoApellido ;
	}

}
