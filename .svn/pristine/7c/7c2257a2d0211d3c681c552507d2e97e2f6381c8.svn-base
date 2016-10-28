package mx.com.kubo.registro.beneficiarios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.html.HtmlSelectOneRadio;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.Benefi_ciaries;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Beneficiaries;
import mx.com.kubo.model.BeneficiariesPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.EditorViviendaIMO;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BeneficiariesService;

public abstract class BeneficiarioDMO 
implements BeneficiarioIMO
{
	protected AddressService       service_address;
	protected BeneficiariesService service_beneficiario;
	
	protected RequestContext request;
	protected HtmlSelectOneRadio select_radio;
	
	protected SessionBean sesion;
	protected NaturalPerson person;
	
	protected Benefi_ciaries beneficiario_bean;
	protected Beneficiaries   beneficiarie;
	protected BeneficiariesPK beneficiario_PK;	
	
	protected Address   address_ORIGINAL;
	protected Address   address;
	protected AddressPK address_PK;	
	
	protected EditorViviendaIMO editor_vivienda;
	
	protected Date fecha_nacimiento;
	protected Calendar fecha;
	
	protected SimpleDateFormat date_format;
	
	protected String same_address;			
	protected String first_name;
	protected String middle_name;
	protected String mother_last_name;
	protected String father_last_name;
	protected String parentesco;
	
	protected String [] monthStr;
	
	protected ArrayList<String> days;
	protected ArrayList<String> months;
	protected ArrayList<String> years;	
	
	protected Integer genero;
	protected Integer beneficiarie_id;
	
	protected Double  porcentaje;
	
	protected int address_id;
	protected int beneficiario_id;
	protected int tipo_cuenta_id;
	protected int prospectus_id;
	protected int company_id;
	
	protected final int CASA = 1;
	protected final int BENEFICIARIO = 6;
	
	protected boolean update_OK;
	protected boolean save_OK;
	protected boolean datebirth_ENABLED;
	
	protected BeneficiarioDMO()
	{		
		days   = new ArrayList<String>();
		months = new ArrayList<String>();
		years  = new ArrayList<String>();
		
		date_format = new SimpleDateFormat("dd/MM/yyyy");
		
		monthStr = new String[]
		{ "Enero",   "Febrero",   "Marzo",  "Abril", "Mayo",
		  "Junio",   "Julio",     "Agosto", "Septiembre", 
		  "Octubre", "Noviembre", "Diciembre"
		 };	
	}
	
	public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
	}

	public void setService_address(AddressService service) 
	{
		service_address = service;
	}
	
	public void setService_beneficiario(BeneficiariesService service) 
	{
		service_beneficiario = service;
	}	
	
	public final void setPerson(NaturalPerson person) 
	{
		this.person = person;
		
		company_id    = person.getNatPerPK().getCompany_id();
		prospectus_id = person.getNatPerPK().getProspectus_id();
	}
	
	public ArrayList<String> getDays() 
	{
		return days;
	}

	public ArrayList<String> getMonths() 
	{
		return months;
	}

	public ArrayList<String> getYears() 
	{
		return years;
	}
}
