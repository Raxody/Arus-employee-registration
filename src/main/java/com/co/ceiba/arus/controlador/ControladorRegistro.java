package com.co.ceiba.arus.controlador;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonObject;

import org.apache.commons.io.IOUtils;

import com.co.ceiba.arus.modelo.entidad.*;
import com.co.ceiba.arus.servicio.ServicioRegistro;

@ManagedBean(name = "registroPersona")
@SessionScoped

public class ControladorRegistro implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String VACIO = "";

	@EJB // Acá inyecto el servicio
	private ServicioRegistro servicioRegistro;

	private Persona persona;

	private Documento documento;

	private AdministradoraPension administradoraPension;

	private AdministradoraSalud administradoraSalud;

	private Registro registro;
	
	private List<String> administradorasPension;

	private List<String> documentos;

	private List<String> administradorasSalud;

	@PostConstruct
	public void init() {
		documentos = servicioRegistro.valoresDisponiblestipoDeDocumento();
		administradorasSalud = servicioRegistro.valoresDisponiblesAdministradoDeSalud();
		administradorasPension = servicioRegistro.valoresDisponiblesAdministradoraDePension();
		persona = new Persona();
		documento = new Documento();
		administradoraPension = new AdministradoraPension();
		administradoraSalud = new AdministradoraSalud();
		registro = new Registro();

		
	}
	
	





	public void crear() throws IOException {
		Registro validadorRegistroDrools = new Registro(new AdministradoraPension(administradoraPension.getTipoAdministradoraPension(),
				administradoraPension.getFecha()), new AdministradoraSalud(administradoraSalud.getTipoAdministradoraSalud(),
						administradoraSalud.getFecha()),new Documento(documento.getTipoDocumento(),documento.getNumeroDocumento()));
		registro.validacionesDrools(validadorRegistroDrools);
		
		if(!validadorRegistroDrools.getMensaje().equals(VACIO)) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, validadorRegistroDrools.getMensaje() , null); 
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}else {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro ha sido exitoso" , null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			servicioRegistro.envioRegistroPersonaServicioExterno(persona,documento,administradoraPension,administradoraSalud);
			
		}
		//servicioRegistro.registrarPersona(administradoraSalud);	
		/*
		 * servicioRegistro.registrarPersona(persona,
		 * documento,administradoraPension,administradoraSalud);
		 * 
		 * ExternalContext context =
		 * FacesContext.getCurrentInstance().getExternalContext();
		 * context.redirect("NewFile.jsf");
		 */

	}
	
	public static void main(String[] args) {
		
	}

	private void validaciones(FacesContext context, UIComponent toValidate, Object value, int parteNombre) {
		if (!registro.validacionesPersona((String) value, parteNombre).equals(VACIO)) {
			((UIInput) toValidate).setValid(false);
			context.addMessage(toValidate.getClientId(context),
					new FacesMessage(registro.validacionesPersona((String) value, parteNombre)));
		}
	}

	public void validarPrimerNombre(FacesContext context, UIComponent toValidate, Object value) {
		this.validaciones(context, toValidate, value, 1);
	}

	public void validarSegundoNombre(FacesContext context, UIComponent toValidate, Object value) {
		this.validaciones(context, toValidate, value, 2);
	}

	public void validarPrimerApellido(FacesContext context, UIComponent toValidate, Object value) {
		this.validaciones(context, toValidate, value, 3);
	}

	public void validarSegundoApellido(FacesContext context, UIComponent toValidate, Object value) {
		this.validaciones(context, toValidate, value, 4);
	}

	public void validarDocumento(FacesContext context, UIComponent toValidate, Object value) {
		UIInput uiInputConfirmPassword = (UIInput) toValidate.getAttributes().get("identificacion");
		String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();
		if (!registro.validacionesDocumento((String) value, confirmPassword).equals(VACIO)) {
			((UIInput) toValidate).setValid(false);
			context.addMessage(toValidate.getClientId(context),
					new FacesMessage(registro.validacionesDocumento((String) value, confirmPassword)));
		}
	}

	
	

	public void validarAdministradoraPension(FacesContext context, UIComponent toValidate, Object value) {
		UIInput uiInputFecha = (UIInput) toValidate.getAttributes().get("pensionObtenida");
		String fecha = uiInputFecha.getSubmittedValue().toString();	
		if (!registro.validacionesAdministradoraPension((String)value,fecha).equals(VACIO)) {
			((UIInput) toValidate).setValid(false);
			context.addMessage(toValidate.getClientId(context),
					new FacesMessage(registro.validacionesAdministradoraPension((String)value,fecha)));
		}
	}
	
	public void validarAdministradoraSalud(FacesContext context, UIComponent toValidate, Object value) {
		UIInput uiInputFecha = (UIInput) toValidate.getAttributes().get("saludObtenida");
		String fecha = uiInputFecha.getSubmittedValue().toString();	
		if (!registro.validacionesAdministradoraSalud((String)value,fecha).equals(VACIO)) {
			((UIInput) toValidate).setValid(false);
			context.addMessage(toValidate.getClientId(context),
					new FacesMessage(registro.validacionesAdministradoraSalud((String)value,fecha)));
		}
	}
	

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<String> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<String> documentos) {
		this.documentos = documentos;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public List<String> getAdministradorasSalud() {
		return administradorasSalud;
	}

	public void setAdministradorasSalud(List<String> administradorasSalud) {
		this.administradorasSalud = administradorasSalud;
	}

	public AdministradoraPension getAdministradoraPension() {
		return administradoraPension;
	}

	public void setAdministradoraPension(AdministradoraPension administradoraPension) {
		this.administradoraPension = administradoraPension;
	}

	public AdministradoraSalud getAdministradoraSalud() {
		return administradoraSalud;
	}

	public void setAdministradoraSalud(AdministradoraSalud administradoraSalud) {
		this.administradoraSalud = administradoraSalud;
	}

	public List<String> getAdministradorasPension() {
		return administradorasPension;
	}

	public void setAdministradorasPension(List<String> administradorasPension) {
		this.administradorasPension = administradorasPension;
	}

	public ServicioRegistro getServicioRegistro() {
		return servicioRegistro;
	}

	public void setServicioRegistro(ServicioRegistro servicioRegistro) {
		this.servicioRegistro = servicioRegistro;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	

	
}
