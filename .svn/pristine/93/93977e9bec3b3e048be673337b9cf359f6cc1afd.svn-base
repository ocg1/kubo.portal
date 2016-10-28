package mx.com.kubo.registro.datos.birthdate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.registro.datos.DatosPersonalesDMO;
import mx.com.kubo.services.SystemParamService;

public abstract class FechaNacimientoDMO extends DatosPersonalesDMO
implements FechaNacimientoIMO
{	
	protected SystemParamService service_system_param;	
	
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;			
	
	protected SimpleDateFormat frm;
	
	protected Calendar fecha;
	protected Calendar clone_fecha_inicial;
	protected Calendar fecha_inicial;
	protected Calendar fecha_final;
	
	protected Date birthdate;
	protected Date date;
	
	protected StringBuilder sb;
		
	protected String day;
	protected String month;
	protected String year;	
	protected String dd;
	protected String msg;
	protected String msgTutor;
	protected String mes_TOKEN;
	protected String birthday_TOKEN;	
	protected String birthdate_TOKEN;
	protected String value_ORIGINAL;
		
	protected ArrayList<String> lista_dias;
	protected ArrayList<String> lista_meses;
	protected ArrayList<String> lista_years;
	
	protected final String catalogo_meses_nombres[];

	protected Integer year_edad_MAX;
	protected Integer year_edad_MIN;
	protected Integer mes_INDEX;
	
	protected int elapsed_time;
	protected int elapsed_dias;
	protected int elapsed_meses;
	protected int elapsed_years;
	protected int elapsed_horas;
	protected int elapsed_mins;
	protected int elapsed_secs;
	
	protected int EDAD_MIN_ACREDITADO;
	protected int EDAD_MIN_INVERSIONISTA;
	protected int EDAD_MIN_IS_AGE_VALID;
	protected int EDAD_MAX_IS_AGE_VALID;
	protected int EDAD_MAX_ACREDITADO;
	protected int EDAD_MAX_INVERSIONISTA;
	
	protected boolean renderMsgTutor;
	protected boolean renderMsg;	
	protected boolean rango_edad_OK;
	protected boolean rango_age_OK;
	protected boolean edad_min_OK;
	protected boolean edad_max_OK;
	protected boolean fecha_OK;
	
	protected final String MSG_EDAD_MIN_ACREDITADO;
	protected final String MSG_EDAD_MAX_ACREDITADO;
	protected final String MSG_EDAD_MIN_INVERSIONISTA;
	protected final String MSG_EDAD_MAX_INVERSIONISTA;
	protected final String MSG_EDAD_18_INVERSIONISTA;
	
	protected final int PARAM_EDAD_MIN_ACREDITADO    = 15;
	protected final int PARAM_EDAD_MIN_INVERSIONISTA = 16;
	protected final int PARAM_EDAD_MIN_IS_AGE_VALID  = 67;
	protected final int PARAM_EDAD_MAX_IS_AGE_VALID  = 68;
	protected final int PARAM_EDAD_MAX_ACREDITADO    = 17;	
	protected final int PARAM_EDAD_MAX_INVERSIONISTA = 18;
		
	protected FechaNacimientoDMO()
	{
		frm = new SimpleDateFormat("dd/MM/yyyy");
				
		msg            = "";
		msgTutor       = "";
		mes_TOKEN      = "";
		birthday_TOKEN = "";
		
		day   = "0";
		month = "0";
		year  = "0";
		
		MSG_EDAD_MIN_INVERSIONISTA = "Fecha de usuario incorrecta, usuario menor de edad";
		MSG_EDAD_MAX_INVERSIONISTA = "Fecha de usuario incorrecta, usuario supera el límite de edad.";
		MSG_EDAD_18_INVERSIONISTA  = "Debido a que aún no cuentas con la mayoría de edad,es necesario que tu tutor también se registre con nosotros.";
		MSG_EDAD_MIN_ACREDITADO    = "Debes ser mayor de edad para solicitarnos crédito";
		MSG_EDAD_MAX_ACREDITADO    = "Superas el límite de edad permitida para solicitarnos crédito";
				
		catalogo_meses_nombres = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	}
	
	public void setService_system_param(SystemParamService service)
	{
		service_system_param = service;
	}

	public final boolean isRenderMsg() 
	{
		return renderMsg;
	}
	
	public final boolean isRenderMsgTutor() 
	{
		return renderMsgTutor;
	}
	
	public final boolean isRango_age_OK() 
	{
		return rango_age_OK;
	}

	public final boolean isEdad_min_OK() 
	{
		return edad_min_OK;
	}

	public final boolean isEdad_max_OK() 
	{
		return edad_max_OK;
	}
	
	public final int getEDAD_MAX_IS_AGE_VALID() 
	{
		return EDAD_MAX_IS_AGE_VALID;
	}

	public final String getMsgTutor() 
	{
		return msgTutor;
	}
	
	public final String getMsg() 
	{
		return msg;
	}

	public String getDay() 
	{
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public final ArrayList<String> getLista_dias() 
	{
		return lista_dias;
	}

	public final ArrayList<String> getLista_meses() 
	{
		return lista_meses;
	}

	public final ArrayList<String> getLista_years() 
	{
		return lista_years;
	}

}
