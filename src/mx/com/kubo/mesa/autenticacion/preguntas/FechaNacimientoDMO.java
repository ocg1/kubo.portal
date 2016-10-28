package mx.com.kubo.mesa.autenticacion.preguntas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import mx.com.kubo.mesa.autenticacion.AutenticacionDMO;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

public abstract class FechaNacimientoDMO extends AutenticacionDMO
{	
	protected SystemParamService service_system_param;
	
	protected NaturalPerson natural_person;
	
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;	
	
	protected SimpleDateFormat date_format;
	
	protected Calendar fecha;
	
	protected Date birthdate_ORIGINAL;
	protected Date birthdate_NEW;
	protected Date date;
	
	protected StringBuilder sb;
	
	protected String area;
	protected String day;
	protected String month;
	protected String year;	

	protected String mes_TOKEN;
	protected String birthdate_TOKEN;	
		
	protected ArrayList<String> lista_dias;
	protected ArrayList<String> lista_meses;
	protected ArrayList<String> lista_years;
	
	protected final String catalogo_meses_nombres[];

	protected Integer year_edad_MAX;
	protected Integer year_edad_MIN;
	
	protected int EDAD_MIN_ACREDITADO;
	protected int EDAD_MIN_INVERSIONISTA;
	protected int EDAD_MIN_IS_AGE_VALID;
	protected int EDAD_MAX_IS_AGE_VALID;
	protected int EDAD_MAX_ACREDITADO;
	protected int EDAD_MAX_INVERSIONISTA;
	
	protected boolean fecha_OK;
	protected boolean fecha_ENABLED;		
	
	protected final int PARAM_EDAD_MIN_ACREDITADO    = 15;
	protected final int PARAM_EDAD_MIN_INVERSIONISTA = 16;
	protected final int PARAM_EDAD_MIN_IS_AGE_VALID  = 67;
	protected final int PARAM_EDAD_MAX_IS_AGE_VALID  = 68;
	protected final int PARAM_EDAD_MAX_ACREDITADO    = 17;	
	protected final int PARAM_EDAD_MAX_INVERSIONISTA = 18;
		
	protected FechaNacimientoDMO()
	{
		date_format = new SimpleDateFormat("dd/MM/yyyy");
				
		mes_TOKEN       = "";
		birthdate_TOKEN = "";
		
		day   = "0";
		month = "0";
		year  = "0";
			
		catalogo_meses_nombres = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		
		service_system_param = Utilities.findBean("systemParamServiceImp");		
	}

	public final void setNatural_person(NaturalPerson natural_person) 
	{
		this.natural_person = natural_person;
		
		company_id = natural_person.getNatPerPK().getCompany_id();		
		area       = natural_person.getProspectus().getArea().toString();
		
		birthdate_ORIGINAL = natural_person.getDate_of_birth();
		
		init();
	}
	
	private void init() 
	{
		init_system_param();
		init_lista_dias_meses();
		init_lista_years();	
	}
	
	protected abstract void init_system_param();
	protected abstract void init_lista_dias_meses();
	protected abstract void init_lista_years();
	
	public final boolean isValue_ENABLED() 
	{
		return fecha_ENABLED;
	}

	public final String getValue_ORIGINAL() 
	{
		return date_format.format(birthdate_ORIGINAL);
	}

	public final String getValue_NEW() 
	{
		return birthdate_TOKEN;
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
