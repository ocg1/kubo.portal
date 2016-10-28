package mx.com.kubo.mesa.solicitud.perfil;

import java.util.List;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.EconActivityBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ViewEconomicInfo;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.ViewClientInfoService;
import mx.com.kubo.tools.Utilities;

public abstract class ActividadEconomicaDMO 
implements ActividadEconomicaIMO
{	
	protected     EmploymentService service_employment;
	protected ViewClientInfoService service_view_client_info;	
	protected       BusinessService service_business;		
	protected Change_controlService service_change_control;
		
	protected EconActivityBean actividad_economica;
	protected ViewEconomicInfo bmx;
	protected Business business;
	
	protected Employment employment;	
	
	//protected Address address_empleo;
	//protected Address addressB;
	
	protected SessionBean sesion;
	
	protected NaturalPerson person;
	
	protected ChangeBean       change_control;
	protected Change_control   changeCtrl;
	protected Change_controlPK changeCtrlPK;		
	
	protected List <Business> 	 lista_business;	
	protected List <Employment>  lista_employment;
	
	protected List <EconActivityBean> lista_actividad_economica;
	protected List<Change_control>    lista_change_control;
	protected List<ViewEconomicInfo>  suggestions;
	
	protected Object tipo_de_actividad;
	
/*	
	protected String auxTenure;
	protected String auxYears;
	protected String employAddressStr;
	protected String businessAddressStr;
*/	
	protected String latlongMap;
	protected String ipAddressClient;
	protected String motivo_del_cambio;
	protected String bmx_activity_id;	
	protected String bmx_LABEL;
	protected String actividad_LABEL;
	
	protected String [] afected_tables;
	protected String [] afected_field;
	
/*	
	protected Integer years_since_when;
	protected Integer months_since_when;
*/	
	protected Integer bmx_inegi_id;
	protected Integer bmx_sector_id;
	
	protected int prospectus_id;
	protected int mesa_prospectus_id;
	protected int company_id;
	protected int index;	
	
	protected boolean nameVisible;
	protected boolean female;
	protected boolean hasEconActivity;
	protected boolean update_OK;
	
	protected ActividadEconomicaDMO()
	{
		afected_field  = new String[]{"bmx_econ_activity_id"};
		afected_tables = new String[]{"gn_business", "gn_employment"};
		
		service_employment       = Utilities.findBean("employmentServiceImp");	
		service_view_client_info = Utilities.findBean("viewClientInfoServiceImp");				
		service_business         = Utilities.findBean("businessServiceImp");				
		service_change_control   = Utilities.findBean("change_controlServiceImp");
	}
	
	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		mesa_prospectus_id = sesion.getProspectus_id();
		ipAddressClient    = sesion.getIP_address_client();
	}

	public final void setPerson(NaturalPerson person) 
	{
		this.person = person;
		
		prospectus_id = person.getNatPerPK().getProspectus_id();
		   company_id = person.getNatPerPK().getCompany_id();
		   
		if(person != null && person.getGender_id() != null && person.getGender_id() == 2)
		{			
			female = true;
				
		} else {
				
			female = false;
		}
	}
	
	public final void setNameVisible(boolean bandera) 
	{
		nameVisible = bandera;
	}		

	public void setLista_business(List<Business> lista_business) 
	{
		this.lista_business = lista_business;
	}

	public void setLista_employment(List<Employment> lista_employment) 
	{
		this.lista_employment = lista_employment;
	}

	public final List<EconActivityBean> getLista_actividad_economica() 
	{
		return lista_actividad_economica;
	}

	public final ChangeBean getChange_control() 
	{
		return change_control;
	}
	
/*	
	public String getAuxTenure() 
	{
		return auxTenure;
	}
	
	public String getAuxYears() 
	{
		return auxYears;
	}
	
	public String getBusinessAddressStr() 
	{
		return businessAddressStr;
	}
	
	public String getEmployAddressStr() 
	{
		return employAddressStr;
	}
*/	
}
