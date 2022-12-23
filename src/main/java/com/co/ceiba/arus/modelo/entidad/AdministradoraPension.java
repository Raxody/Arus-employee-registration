package com.co.ceiba.arus.modelo.entidad;

import java.io.Serializable;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.co.ceiba.arus.modelo.excepcion.ExcepcionValorInvalido;

import static com.co.ceiba.arus.modelo.ValidadorArgumento.*;
import static com.co.ceiba.arus.modelo.ValidadorArgumentoException.*;

public class AdministradoraPension implements Serializable {

	private static final long serialVersionUID = 5L;
	private String codigo;
	private String tipoAdministradoraPension;
	private String fecha;

	private static final String PROTECCION = "Protección";
	private static final String PORVENIR = "Porvenir";
	private static final String COLPENSIONES = "Colpensiones";
	
	
	private static final String SE_DEBE_SELECCIONAR_EL_TIPO_DE_ADMINISTRADORA_DE_PENSION = "Se debe seleccionar el tipo de administradora de pension";
	private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ADMINISTRADORA_DE_PENSION = "Se dene ingresar la fecha de administradora de pensión"; 
	private static final String EL_FORMATO_DE_LA_FECHA_DE_LA_AMINISTRADORA_DE_PENSION_DEBE_SER_AAAA_MM_DD = "El formato de la fecha de la administradora de pensión debe ser AAAA-MM-DD";
	private static final String LA_FECHA_DE_LA_ADMINISTRADORA_DE_PENSION_DEBE_SER_MENOR_A_LA_ACTUAL = "La fecha de la administradora de pension debe ser menor a la actual";

	public AdministradoraPension(String tipoAdministradoraPension, String fecha) {

		validarObligatorioException(tipoAdministradoraPension,SE_DEBE_SELECCIONAR_EL_TIPO_DE_ADMINISTRADORA_DE_PENSION);
		validarObligatorioException(fecha,SE_DEBE_INGRESAR_LA_FECHA_DE_ADMINISTRADORA_DE_PENSION);
		
		validadorFechaPermitidaYFormateadaException(fecha,
				LA_FECHA_DE_LA_ADMINISTRADORA_DE_PENSION_DEBE_SER_MENOR_A_LA_ACTUAL,
				EL_FORMATO_DE_LA_FECHA_DE_LA_AMINISTRADORA_DE_PENSION_DEBE_SER_AAAA_MM_DD);

		this.tipoAdministradoraPension = tipoAdministradoraPension;
		this.fecha = fecha;

		switch (tipoAdministradoraPension) {

		case PROTECCION: {
			this.codigo = "AFP001";
			break;
		}
		case PORVENIR: {
			this.codigo = "AFP002";
			break;
		}
		case COLPENSIONES: {
			this.codigo = "AFP003";
			break;
		}
		default: {

			break;
		}
		}

	}


	public AdministradoraPension() {

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipoAdministradoraPension() {
		return tipoAdministradoraPension;
	}

	public void setTipoAdministradoraPension(String tipoAdministradoraPension) {
		this.tipoAdministradoraPension = tipoAdministradoraPension;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "AdministradoraPension [codigo=" + codigo + ", tipoAdministradoraPension=" + tipoAdministradoraPension
				+ ", fecha=" + fecha + "]";
	}

}
