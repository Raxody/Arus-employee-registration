package com.co.ceiba.arus.modelo.entidad;

import java.io.Serializable;
import static com.co.ceiba.arus.modelo.ValidadorArgumento.*;
import static com.co.ceiba.arus.modelo.ValidadorArgumentoException.*;

public class AdministradoraSalud implements Serializable {

	private static final long serialVersionUID = 4L;
	private String codigo;
	private String tipoAdministradoraSalud;
	private String fecha;

	private static final String SANITAS_EPS = "Sanitas EPS";
	private static final String SURA_EPS = "Sura EPS";
	private static final String COOMEVA = "Coomeva";
	private static final String MEDIMAS = "Medimas";	
	private static final String SE_DEBE_SELECCIONAR_EL_TIPO_DE_ADMINISTRADORA_DE_SALUD = "Se debe seleccionar el tipo de administradora de salud";
	private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ADMINISTRADORA_DE_SALUD = "Se debe ingresar la fecha de administradora de salud"; 
	private static final String EL_FORMATO_DE_LA_FECHA_DE_LA_AMINISTRADORA_DE_SALUD_DEBE_SER_AAAA_MM_DD = "El formato de la fecha de la administradora de salud debe ser AAAA-MM-DD";
	private static final String LA_FECHA_DE_LA_ADMINISTRADORA_DE_SALUD_DEBE_SER_MENOR_A_LA_ACTUAL = "La fecha de la administradora de salud debe ser menor a la actual";

	public AdministradoraSalud(String tipoAdministradoraSalud, String fecha) {

		validarObligatorioException(tipoAdministradoraSalud,SE_DEBE_SELECCIONAR_EL_TIPO_DE_ADMINISTRADORA_DE_SALUD);
		validarObligatorioException(fecha,SE_DEBE_INGRESAR_LA_FECHA_DE_ADMINISTRADORA_DE_SALUD);		
		validadorFechaPermitidaYFormateadaException(fecha,
				LA_FECHA_DE_LA_ADMINISTRADORA_DE_SALUD_DEBE_SER_MENOR_A_LA_ACTUAL,
				EL_FORMATO_DE_LA_FECHA_DE_LA_AMINISTRADORA_DE_SALUD_DEBE_SER_AAAA_MM_DD);
		
		this.tipoAdministradoraSalud = tipoAdministradoraSalud;
		this.fecha = fecha;
		switch (tipoAdministradoraSalud) {

		case SANITAS_EPS: {
			this.codigo = "EPS001";
			break;
		}
		case SURA_EPS: {
			this.codigo = "EPS002";
			break;
		}
		case COOMEVA: {
			this.codigo = "EPS003";
			break;
		}
		case MEDIMAS: {
			this.codigo = "EPS004";
			break;
		}
		default: {

			break;
		}
		}

	}
	
	public AdministradoraSalud() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipoAdministradoraSalud() {
		return tipoAdministradoraSalud;
	}

	public void setTipoAdministradoraSalud(String tipoAdministradoraSalud) {
		this.tipoAdministradoraSalud = tipoAdministradoraSalud;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "AdministradoraSalud [codigo=" + codigo + ", tipoAdministradoraSalud=" + tipoAdministradoraSalud
				+ ", fecha=" + fecha + "]";
	}

}
