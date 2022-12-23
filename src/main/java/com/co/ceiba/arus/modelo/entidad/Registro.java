package com.co.ceiba.arus.modelo.entidad;

import static com.co.ceiba.arus.modelo.ValidadorArgumento.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class Registro {

	private Persona persona;
	private AdministradoraPension administradoraPension;
	private AdministradoraSalud administradoraSalud;
	private Documento documento;
	private String mensaje;
	
	private static final String SE_DEBE_SELECCIONAR_EL_TIPO_DE_ADMINISTRADORA_DE_PENSION = "Se debe seleccionar el tipo de administradora de pension";
	private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ADMINISTRADORA_DE_PENSION = "Se dene ingresar la fecha de administradora de pensión";
	
	
	private static final String EL_FORMATO_DE_LA_FECHA_DE_LA_AMINISTRADORA_DE_PENSION_DEBE_SER_AAAA_MM_DD = "El formato de la fecha de la administradora de pensión debe ser AAAA-MM-DD";
	private static final String LA_FECHA_DE_LA_ADMINISTRADORA_DE_PENSION_DEBE_SER_MENOR_A_LA_ACTUAL = "La fecha de la administradora de pension debe ser menor a la actual";
	private static final String VACIO = "";

	
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

	private static final String SOLO_SE_ACEPTAN_NUMEROS_EN_EL_REGISTRO_CIVIL = "Solo se aceptan números en el registro civil";
	private static final String EL_REGISTRO_CIVIL_DEBE_SER_POSITIVO = "El registro civil debe ser positivo";

	private static final String SOLO_SE_ACEPTAN_NUMEROS_EN_LA_TARJETA_DE_IDENTIDAD = "Solo se aceptan números en la tarjeta de identidad";
	private static final String LA_TARJETA_DE_IDENTIDAD_DEBE_SER_POSITIVA = "La tarjeta de identidad debe ser positiva";
	
	private static final String SELECCIONA_EL_TIPO_DEL_DOCUMENTO = "Selecciona el tipo del documento";
	
	
	
	private static final String SE_DEBE_INGRESAR_EL_PRIMER_NOMBRE = "Se debe ingresar el primer nombre";
	private static final String SOLO_SE_ACEPTAN_LETRAS_EN_EL_PRIMER_NOMBRE = "Solo se aceptan letras en el primer nombre";

	private static final String SE_DEBE_INGRESAR_EL_SEGUNDO_NOMBRE = "Se debe ingresar el segundo nombre";
	private static final String SOLO_SE_ACEPTAN_LETRAS_EN_EL_SEGUNDO_NOMBRE = "Solo se aceptan letras en el segundo nombre";

	private static final String SE_DEBE_INGRESAR_EL_PRIMER_APELLIDO = "Se debe ingresar el primer apellido";
	private static final String SOLO_SE_ACEPTAN_LETRAS_EN_EL_PRIMER_APELLIDO = "Solo se aceptan letras en el primer apellido";

	private static final String SE_DEBE_INGRESAR_EL_SEGUNDO_APELLIDO = "Se debe ingresar el segundo apellido";
	private static final String SOLO_SE_ACEPTAN_LETRAS_EN_EL_SEGUNDO_APELLIDO = "Solo se aceptan letras en el segundo apellido";
	
	
	private static final String SE_DEBE_SELECCIONAR_EL_TIPO_DE_ADMINISTRADORA_DE_SALUD = "Se debe seleccionar el tipo de administradora de salud";
	private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ADMINISTRADORA_DE_SALUD = "Se debe ingresar la fecha de administradora de salud"; 
	private static final String EL_FORMATO_DE_LA_FECHA_DE_LA_AMINISTRADORA_DE_SALUD_DEBE_SER_AAAA_MM_DD = "El formato de la fecha de la administradora de salud debe ser AAAA-MM-DD";
	private static final String LA_FECHA_DE_LA_ADMINISTRADORA_DE_SALUD_DEBE_SER_MENOR_A_LA_ACTUAL = "La fecha de la administradora de salud debe ser menor a la actual";
	

	
	
	public Registro(Persona persona, AdministradoraPension administradoraPension,
			AdministradoraSalud administradoraSalud) {
		this.persona = persona;
		this.administradoraPension = administradoraPension;
		this.administradoraSalud = administradoraSalud;
	}

	public Registro(AdministradoraPension administradoraPension, AdministradoraSalud administradoraSalud,
			Documento documento) {
		this.mensaje = "";
		this.administradoraPension = administradoraPension;
		this.administradoraSalud = administradoraSalud;
		this.documento = documento;
	}

	public Registro() {
		
	}
	
	public String validacionesAdministradoraPension(String tipoAdministradoraPension, String fecha) {
		if(!validarObligatorio(tipoAdministradoraPension,SE_DEBE_SELECCIONAR_EL_TIPO_DE_ADMINISTRADORA_DE_PENSION).equals(VACIO)) {
			return validarObligatorio(tipoAdministradoraPension,SE_DEBE_SELECCIONAR_EL_TIPO_DE_ADMINISTRADORA_DE_PENSION);
			
		}else if(!validarObligatorio(fecha,SE_DEBE_INGRESAR_LA_FECHA_DE_ADMINISTRADORA_DE_PENSION).equals(VACIO)) {
			return validarObligatorio(fecha,SE_DEBE_INGRESAR_LA_FECHA_DE_ADMINISTRADORA_DE_PENSION);			
		}else if (!validadorFechaPermitidaYFormateada(fecha,
				LA_FECHA_DE_LA_ADMINISTRADORA_DE_PENSION_DEBE_SER_MENOR_A_LA_ACTUAL,
				EL_FORMATO_DE_LA_FECHA_DE_LA_AMINISTRADORA_DE_PENSION_DEBE_SER_AAAA_MM_DD).equals(VACIO)) {
			return validadorFechaPermitidaYFormateada(fecha,
					LA_FECHA_DE_LA_ADMINISTRADORA_DE_PENSION_DEBE_SER_MENOR_A_LA_ACTUAL,
					EL_FORMATO_DE_LA_FECHA_DE_LA_AMINISTRADORA_DE_PENSION_DEBE_SER_AAAA_MM_DD);
		}
		return VACIO;
	}
	
	
	public String validacionesAdministradoraSalud(String tipoAdministradoraSalud, String fecha) {
		if(!validarObligatorio(tipoAdministradoraSalud,SE_DEBE_SELECCIONAR_EL_TIPO_DE_ADMINISTRADORA_DE_SALUD).equals(VACIO)) {
			return validarObligatorio(tipoAdministradoraSalud,SE_DEBE_SELECCIONAR_EL_TIPO_DE_ADMINISTRADORA_DE_SALUD);
			
		}else if(!validarObligatorio(fecha,SE_DEBE_INGRESAR_LA_FECHA_DE_ADMINISTRADORA_DE_SALUD).equals(VACIO)) {
			return validarObligatorio(fecha,SE_DEBE_INGRESAR_LA_FECHA_DE_ADMINISTRADORA_DE_SALUD);			
		}else if (!validadorFechaPermitidaYFormateada(fecha,
				LA_FECHA_DE_LA_ADMINISTRADORA_DE_SALUD_DEBE_SER_MENOR_A_LA_ACTUAL,
				EL_FORMATO_DE_LA_FECHA_DE_LA_AMINISTRADORA_DE_SALUD_DEBE_SER_AAAA_MM_DD).equals(VACIO)) {
			return validadorFechaPermitidaYFormateada(fecha,
					LA_FECHA_DE_LA_ADMINISTRADORA_DE_SALUD_DEBE_SER_MENOR_A_LA_ACTUAL,
					EL_FORMATO_DE_LA_FECHA_DE_LA_AMINISTRADORA_DE_SALUD_DEBE_SER_AAAA_MM_DD);
		}
		return VACIO;
	}
	
	
	
	
	public String validacionesDocumento(String tipoDocumento, String numeroDocumento) {

		if (!validarObligatorio(tipoDocumento, SE_DEBE_INGRESAR_EL_TIPO_DE_DOCUMENTO).equals(VACIO)) {
			return validarObligatorio(tipoDocumento, SE_DEBE_INGRESAR_EL_TIPO_DE_DOCUMENTO);
		} else if (!validarObligatorio(numeroDocumento, SE_DEBE_INGRESAR_EL_NUMERO_DE_DOCUMENTO).equals(VACIO)) {
			return validarObligatorio(numeroDocumento, SE_DEBE_INGRESAR_EL_NUMERO_DE_DOCUMENTO);
		}

		switch (tipoDocumento) {
		case CEDULA_DE_CIUDADANIA: {
			if (!validacionesNumericasPositivas(numeroDocumento, SOLO_SE_ACEPTAN_NUMEROS_EN_LA_CEDULA_DE_CIUDADANIA,
					LA_CEDULA_DE_CIUDADANIA_DEBE_SER_POSITIVA).equals(VACIO)) {
				return validacionesNumericasPositivas(numeroDocumento,
						SOLO_SE_ACEPTAN_NUMEROS_EN_LA_CEDULA_DE_CIUDADANIA, LA_CEDULA_DE_CIUDADANIA_DEBE_SER_POSITIVA);
			} else if (!validarLongitud(numeroDocumento, LONGITUD_MINIMA_CEDULA_DE_CIUDADANIA,
					String.format(LA_CEDULA_DE_CIUDADANIA_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A,
							LONGITUD_MINIMA_CEDULA_DE_CIUDADANIA)).equals(VACIO)) {
				return validarLongitud(numeroDocumento, LONGITUD_MINIMA_CEDULA_DE_CIUDADANIA,
						String.format(LA_CEDULA_DE_CIUDADANIA_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A,
								LONGITUD_MINIMA_CEDULA_DE_CIUDADANIA));
			}
			break;
		}
		case CEDULA_DE_EXTRANJERIA: {
			if (!validarSiSoloContieneNumerosYLetras(numeroDocumento,
					SOLO_SE_ACEPTAN_NUMEROS_Y_LETRAS_EN_LA_CEDULA_DE_EXTRANJERIA).equals(VACIO)) {
				return validarSiSoloContieneNumerosYLetras(numeroDocumento,
						SOLO_SE_ACEPTAN_NUMEROS_Y_LETRAS_EN_LA_CEDULA_DE_EXTRANJERIA);
			} else if (!validarSiContieneAlmenosUnNumeroYUnaLetra(numeroDocumento,
					LA_CEDULA_DE_EXTRANJERIA_DEBE_TENER_MINIMO_UN_NUMERO_Y_UNA_LETRA).equals(VACIO)) {
				return validarSiContieneAlmenosUnNumeroYUnaLetra(numeroDocumento,
						LA_CEDULA_DE_EXTRANJERIA_DEBE_TENER_MINIMO_UN_NUMERO_Y_UNA_LETRA);
			} else if (!validarLongitud(numeroDocumento, LONGITUD_MINIMA_CEDULA_DE_EXTRANJERIA,
					String.format(LA_CEDULA_DE_EXTRANJERIA_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A,
							LONGITUD_MINIMA_CEDULA_DE_EXTRANJERIA)).equals(VACIO)) {
				return validarLongitud(numeroDocumento, LONGITUD_MINIMA_CEDULA_DE_EXTRANJERIA,
						String.format(LA_CEDULA_DE_EXTRANJERIA_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A,
								LONGITUD_MINIMA_CEDULA_DE_EXTRANJERIA));
			}
			break;
		}
		case REGISTRO_CIVIL: {
			if (!validacionesNumericasPositivas(numeroDocumento, SOLO_SE_ACEPTAN_NUMEROS_EN_EL_REGISTRO_CIVIL,
					EL_REGISTRO_CIVIL_DEBE_SER_POSITIVO).equals(VACIO)) {
				return validacionesNumericasPositivas(numeroDocumento, SOLO_SE_ACEPTAN_NUMEROS_EN_EL_REGISTRO_CIVIL,
						EL_REGISTRO_CIVIL_DEBE_SER_POSITIVO);
			}

			break;
		}
		case TARJETA_DE_IDENTIDAD: {
			if (!validacionesNumericasPositivas(numeroDocumento, SOLO_SE_ACEPTAN_NUMEROS_EN_LA_TARJETA_DE_IDENTIDAD,
					LA_TARJETA_DE_IDENTIDAD_DEBE_SER_POSITIVA).equals(VACIO)) {
				return validacionesNumericasPositivas(numeroDocumento,
						SOLO_SE_ACEPTAN_NUMEROS_EN_LA_TARJETA_DE_IDENTIDAD, LA_TARJETA_DE_IDENTIDAD_DEBE_SER_POSITIVA);
			}
			break;
		}
		case VACIO: {
			return SELECCIONA_EL_TIPO_DEL_DOCUMENTO;
		}
		}
		return VACIO;
	}



	private String validacionesNumericasPositivas(String numeroDocumento, String soloSeAceptanNumerosDelDocumento,
			String elnumeroDelDocumentoDebeSerPositivo) {
		if (!validarSoloNumeros(numeroDocumento, soloSeAceptanNumerosDelDocumento).equals(VACIO)) {
			return validarSoloNumeros(numeroDocumento, soloSeAceptanNumerosDelDocumento);
		} else if (!validarPositivo(Long.parseLong(numeroDocumento), elnumeroDelDocumentoDebeSerPositivo).equals(VACIO)) {
			return validarPositivo(Long.parseLong(numeroDocumento), elnumeroDelDocumentoDebeSerPositivo);
		}
		return VACIO;

	}
	
	
	public String validacionesPersona(String parteNombre, int posicionParteNombre) {

		if (!validacionesNombresObligatorioYSoloLetras(parteNombre, SE_DEBE_INGRESAR_EL_PRIMER_NOMBRE,
				SOLO_SE_ACEPTAN_LETRAS_EN_EL_PRIMER_NOMBRE).equals(VACIO) && posicionParteNombre == 1) {
			return validacionesNombresObligatorioYSoloLetras(parteNombre, SE_DEBE_INGRESAR_EL_PRIMER_NOMBRE,
					SOLO_SE_ACEPTAN_LETRAS_EN_EL_PRIMER_NOMBRE);
		} else if (!validacionesNombresObligatorioYSoloLetras(parteNombre, SE_DEBE_INGRESAR_EL_SEGUNDO_NOMBRE,
				SOLO_SE_ACEPTAN_LETRAS_EN_EL_SEGUNDO_NOMBRE).equals(VACIO) && posicionParteNombre == 2) {
			return validacionesNombresObligatorioYSoloLetras(parteNombre, SE_DEBE_INGRESAR_EL_SEGUNDO_NOMBRE,
					SOLO_SE_ACEPTAN_LETRAS_EN_EL_SEGUNDO_NOMBRE);
		} else if (!validacionesNombresObligatorioYSoloLetras(parteNombre, SE_DEBE_INGRESAR_EL_PRIMER_APELLIDO,
				SOLO_SE_ACEPTAN_LETRAS_EN_EL_PRIMER_APELLIDO).equals(VACIO) && posicionParteNombre == 3) {
			return validacionesNombresObligatorioYSoloLetras(parteNombre, SE_DEBE_INGRESAR_EL_PRIMER_APELLIDO,
					SOLO_SE_ACEPTAN_LETRAS_EN_EL_PRIMER_APELLIDO);
		} else if (!validacionesNombresObligatorioYSoloLetras(parteNombre, SE_DEBE_INGRESAR_EL_SEGUNDO_APELLIDO,
				SOLO_SE_ACEPTAN_LETRAS_EN_EL_SEGUNDO_APELLIDO).equals(VACIO) && posicionParteNombre == 4) {
			return validacionesNombresObligatorioYSoloLetras(parteNombre, SE_DEBE_INGRESAR_EL_SEGUNDO_APELLIDO,
					SOLO_SE_ACEPTAN_LETRAS_EN_EL_SEGUNDO_APELLIDO);
		}
		
		return VACIO;

	}



	private String validacionesNombresObligatorioYSoloLetras(String parteDelNombre, String oligatorioParteDelNombre,
			String soloLetrasParteDelNombre) {
		if (!validarObligatorio(parteDelNombre, oligatorioParteDelNombre).equals(VACIO)) {
			return validarObligatorio(parteDelNombre, oligatorioParteDelNombre);
		} else if (!validarSiSoloContieneLetras(parteDelNombre, soloLetrasParteDelNombre).equals(VACIO)) {
			return validarSiSoloContieneLetras(parteDelNombre, soloLetrasParteDelNombre);
		}
		return VACIO;

	}
	
	public void validacionesDrools(Registro registro) {
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		builder.add(ResourceFactory.newClassPathResource("rules/reglascomponentedos.drl"), ResourceType.DRL);
		if (builder.hasErrors()) {
			for (KnowledgeBuilderError error : builder.getErrors()) {
				System.out.println(error);
			}
			throw new IllegalArgumentException("no se encontró");  
		}		
		KnowledgeBase base = KnowledgeBaseFactory.newKnowledgeBase();
		base.addKnowledgePackages(builder.getKnowledgePackages());
		StatefulKnowledgeSession session = base.newStatefulKnowledgeSession();
		session.insert(registro);
		session.fireAllRules();
		session.dispose();
	}
	
	public boolean validadorFechaCriterios(String fechaIngresada, String fechaPermitida)  {
        try{
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaIngresadaFormateada = date.parse(fechaIngresada);
            Date fechaPermitidaFormateada = date.parse(fechaPermitida);
            
            System.out.println(fechaIngresadaFormateada);
            System.out.println(fechaPermitidaFormateada);

            if(fechaIngresadaFormateada.after(fechaPermitidaFormateada)){
                return true;                
            }
            
        }catch (ParseException a){
            return false;
        }
        return false;
    
    }
	
	
	
	@Override
	public String toString() {		
		return "{\r\n"
		+ "	\"numeroDocumento\" :" + persona.getDocumento().getNumeroDocumento() + ",\r\n"
		+ "	\"tipoDocumento\" : \"" + persona.getDocumento().getTipoDocumento() + "\",\r\n"
		+ "	\"primerNombre\" : \"" + persona.getPrimerNombre() + "\",\r\n"
		+ "	\"segundoNombre\" : \""+ persona.getSegundoNombre() + "\",\r\n"
		+ "	\"primerApellido\" : \"" + persona.getPrimerApellido() + "\",\r\n"
		+ "	\"segundoApellido\" : \"" + persona.getSegundoApellido() + "\",\r\n"
		+ "	\"codigoAdministradoraSalud\" : \"" + administradoraSalud.getCodigo() + "\",\r\n"
		+ "	\"tipoAdministradoraSalud\" : \"" + administradoraSalud.getTipoAdministradoraSalud() + "\",\r\n"
		+ "	\"fechaAdministradoraSalud\" : \"" + administradoraSalud.getFecha() + "\",\r\n"
		+ "	\"codigoAdministradoraPension\" : \"" + administradoraPension.getCodigo() + "\",\r\n"
		+ "	\"tipoAdministradoraPension\" : \"" + administradoraPension.getTipoAdministradoraPension() + "\",\r\n"
		+ "	\"fechaAdministradoraPension\" : \"" + administradoraPension.getFecha() + "\"\r\n"
		+ "}";
		
	}
	
	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
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


	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
