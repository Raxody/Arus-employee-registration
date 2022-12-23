package com.co.ceiba.arus.modelo.entidad;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class prueba {

	private String nombre1;
	private String nombre2;

	public prueba(String nombre1, String nombre2) {
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
	}

	public prueba(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public static void main(String[] args) throws IOException {
		prueba.enviador();

	}

	public static void enviador() throws IOException {
		/*
		 * ExternalContext context =
		 * FacesContext.getCurrentInstance().getExternalContext();
		 * context.redirect("http://localhost:8083/registro/factura/anulada");
		 */
		
		Registro registro = new Registro(new Persona("juan", "Ananes", "Lopez", "Avila",
				new Documento("Cédula de ciudadania", "1234567")),new AdministradoraPension("Protección", "2020-12-12"),
				new AdministradoraSalud("Sanitas EPS", "2019-12-5"));

		String query_url = "http://localhost:8083/registro/factura/facturar";
		String json = registro.toString();
		try {
			URL url = new URL(query_url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");

			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.close();

// read the response
			InputStream in = new BufferedInputStream(conn.getInputStream());
			String result = IOUtils.toString(in, "UTF-8");
			System.out.println(result);
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
