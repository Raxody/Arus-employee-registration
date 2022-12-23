package com.co.ceiba.arus.servicio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;

import org.apache.commons.io.IOUtils;

import com.co.ceiba.arus.modelo.entidad.*;

@Stateless

public class ServicioRegistro {

	private static final String SANITAS_EPS = "Sanitas EPS";
	private static final String SURA_EPS = "Sura EPS";
	private static final String COOMEVA = "Coomeva";
	private static final String MEDIMAS = "Medimas";

	private static final String CEDULA_DE_CIUDADANIA = "Cédula de ciudadania";
	private static final String CEDULA_DE_EXTRANJERIA = "Cédula de extranjeria";
	private static final String REGISTRO_CIVIL = "Registro civil";
	private static final String TARJETA_DE_IDENTIDAD = "Tarjeta de Identidad";
	
	private static final String PROTECCION = "Protección";
	private static final String PORVENIR = "Porvenir";
	private static final String COLPENSIONES= "Colpensiones";
	
	private static final String URL_SERVICIO_EXTERNO = "http://localhost:8085/registro/registrar";
	
	private Registro registroPersona;

	private void registrarPersona(Persona persona, Documento documento, AdministradoraPension administradoraPension,
			AdministradoraSalud administradoraSalud) {
		registroPersona = new Registro(
				new Persona(persona.getPrimerNombre(), persona.getSegundoNombre(), persona.getPrimerApellido(),
						persona.getSegundoApellido(),
						new Documento(documento.getTipoDocumento(), documento.getNumeroDocumento())),
				new AdministradoraPension(administradoraPension.getTipoAdministradoraPension(),
						administradoraPension.getFecha()),
				new AdministradoraSalud(administradoraSalud.getTipoAdministradoraSalud(),administradoraSalud.getFecha()));

	}	
	
	public void envioRegistroPersonaServicioExterno(Persona persona, Documento documento, AdministradoraPension administradoraPension,
			AdministradoraSalud administradoraSalud) {
		
		registrarPersona(persona,documento,administradoraPension,administradoraSalud);
		String json = registroPersona.toString();
		
		try {
			URL url = new URL(URL_SERVICIO_EXTERNO);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");

			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.close();
			
			InputStream in = new BufferedInputStream(conn.getInputStream());
			String result = IOUtils.toString(in, "UTF-8");
			System.out.println(result);
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	

	public List<String> valoresDisponiblestipoDeDocumento() {
		return Arrays.asList(CEDULA_DE_CIUDADANIA, CEDULA_DE_EXTRANJERIA, REGISTRO_CIVIL, TARJETA_DE_IDENTIDAD);
	}

	public List<String> valoresDisponiblesAdministradoDeSalud() {
		return Arrays.asList(SANITAS_EPS, SURA_EPS, COOMEVA, MEDIMAS);
	}
	
	public List<String> valoresDisponiblesAdministradoraDePension() {
		return Arrays.asList(PROTECCION,PORVENIR,COLPENSIONES);
	}

}
