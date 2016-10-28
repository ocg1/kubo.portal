package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UISelectOne;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ListNeighborhoodBean;
import mx.com.kubo.change_control.ChangeControlEMO;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Residence;
import mx.com.kubo.model.ResidencePK;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.NeighborhoodService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ResidenceService;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;

public abstract class EditorViviendaDMO 
implements EditorViviendaIMO
{
	protected EmploymentService     service_vivienda;	
	protected AddressService        service_address;
	protected ResidenceService      service_residence;	
	protected NaturalPersonService  service_natural_person;
	protected NeighborhoodService   service_colonia;
	protected Change_controlService service_change_control;
	protected ProyectLoanService    service_proyect_loan;
		
	protected RequestContext request;
	
	protected HtmlInputText     ajax_inputText;
	protected HtmlInputTextarea ajax_text_area;
	protected UISelectOne       ajax_select_one;
	
	protected SessionBean sesion;
	
	protected ProyectLoan proyect_loan;
	
	protected NaturalPerson     natural_person;
	protected gnNaturalPersonPK natural_person_PK;	
	
	protected Address   address_ORIGINAL;
	protected Address   address;
	protected AddressPK address_PK;	
	
	protected NeighborhoodCat      colonia;
	protected NeighborhoodCatPK    colonia_PK;
	protected ListNeighborhoodBean colonia_JSON;
	
	protected Residence   tipo_vivienda;
	protected ResidencePK tipo_vivienda_PK;	
	
	protected TownCat  delegacion_municipio;
	protected StateCat estado;	
	
	protected ChangeBean change_control_bean;
	protected ChangeBean change_control_bitacora;
	protected Coincidencia coincidencia;
	protected AddressTokenIMO token;
	
	protected List<NeighborhoodCat> colonias_por_codigo_postal;
	protected List<Residence>       lista_tipo_vivienda;
	protected List<Change_control>  lista_de_cambios;
	protected List<Address> lista_address;	
	protected List<Coincidencia> lista_coincidencias;
	
	protected ArrayList<ListNeighborhoodBean> lista_colonias_JSON;
	
	protected Character area;
	
	protected String input_text_value;
	protected String input_text_id;
	protected String isEstados_ENABLED;
	
	protected String colonia_name;
	protected String colonia_Text;
	protected String codigo_postal;
	protected String delegacion_municipio_name;
	protected String estado_name;
	protected String map_ubication;	
	protected String vivienda_TOKEN;
	protected String vivienda_TOKEN_NEW;
	protected String vivienda_TOKEN_COMPARABLE;
	protected String motivo_del_cambio;
	protected String scriptColonia;
	protected String colonia_ENABLED;
	protected String otra_colonia_ENABLED;
	protected String neighborhood_name;
	protected String neighborhood_text;
	protected String neighborhood_zip_code;
	protected String neighborhood_latitude;
	protected String neighborhood_longitud;
	protected String alarma_NIVEL_1;
	protected String alarma_NIVEL_2;
	protected String antiguedad;
	protected String person_type_title;
	
	protected String[] afected_tables;
	protected String[] afected_fields;		
	
	protected Integer colonia_id;
	protected Integer tipo_vivienda_id;	 
	protected Integer address_type;
	protected Integer beneficiarie_id;
	protected Integer neighborhood_id;
	protected Integer length_residence;
	
	protected Long coincidencias_NIVEL_1;
	protected Long coincidencias_NIVEL_2;
	
	protected Character person_type;
	
	protected int townId;
	protected int stateId;
	protected int company_id;
	protected int prospectus_id;
	protected int emisor_id;
	protected int address_id;
	
	
	protected final int MEXICO  = 700;
	protected final int CASA    = 1;
	protected final int EMPRESA = 8;
	protected final int FISCAL  = 9;
	
	protected final int NIVEL_1 = 1;
	protected final int NIVEL_2 = 2;
	
	protected boolean is_remove_OK;
	protected boolean address_saved_OK;
	protected boolean guardar_edicion_OK;
	protected boolean map_ubication_CHANGED;
	protected boolean vivienda_CHANGED;
	protected boolean bitacora_ENABLED;
	protected boolean state_ENABLED;
	protected boolean address_ENABLED;	
	protected boolean auto_save_ENABLED;
	protected boolean fiscal_ENABLED;

	protected EditorViviendaDMO()
	{
		service_vivienda       = Utilities.findBean("employmentServiceImp");
		service_address        = Utilities.findBean("addressServiceImp");	
		service_residence      = Utilities.findBean("residenceServiceImp");
		service_natural_person = Utilities.findBean("naturalPersonServiceImp");		
		service_colonia        = Utilities.findBean("neighborhoodServiceImp");
		service_change_control = Utilities.findBean("change_controlServiceImp");
		service_proyect_loan   = Utilities.findBean("proyectLoanServiceImp");
		
		afected_tables = new String[]{"gn_address"};
		afected_fields = new String[]{"vivienda_TOKEN"};
		
		change_control_bean = new ChangeBean(ChangeControlEMO.EDICION_VIVIENDA);
		
		motivo_del_cambio = "";
		
		address_type = CASA;
		
		bitacora_ENABLED  = true;
		state_ENABLED     = true;
		address_ENABLED   = true;
		auto_save_ENABLED = false;
		vivienda_CHANGED  = false;	
		
		lista_tipo_vivienda = service_residence.getResidenceList();
		
		colonia_ENABLED = "block";
		otra_colonia_ENABLED = "none";
	}
	
	public final void setSesion(SessionBean sesion)
	{
		this.sesion = sesion;
		
		if(sesion != null)
		{
			area          = sesion.getArea();	
			company_id    = sesion.getCompany_id();			
			emisor_id     = sesion.getProspectus_id();
		}
	}
	
	public final void setPerson(NaturalPerson person)
	{
		if(person != null)
		{
			natural_person = person;
			
			company_id       = person.getNatPerPK().getCompany_id();
			prospectus_id    = person.getNatPerPK().getProspectus_id();	
			
			person_type      = person.getProspectus().getPerson_type();
			
			switch(person_type)
			{
				case 'F':
				case 'A':
					length_residence = person.getLength_of_residence();
					tipo_vivienda_id = person.getResidence_id();				
					
					if(tipo_vivienda_id != null)
					{
						tipo_vivienda_PK = new ResidencePK(tipo_vivienda_id, company_id);
						
						tipo_vivienda = service_residence.getResidenceById(tipo_vivienda_PK);
					
					}else{
					
						tipo_vivienda_PK = new ResidencePK(1, company_id);
						
						tipo_vivienda = service_residence.getResidenceById(tipo_vivienda_PK);
						
					}
					
					if(length_residence == null){
						
						length_residence = 1;
						
					}
										
					person_type_title = "Domicilio particular";
				break;
				
				case 'M':
					address_type = EMPRESA;
					
					person_type_title = "Domicilio de la Empresa";
				break;
			}
		} 
	}
	
	public final void setProspectus_id(int prospectus_id) 
	{
		this.prospectus_id = prospectus_id;
	}

	public String getAntiguedad() 
	{
		return antiguedad;
	}

	public final void setAddress_type(Integer address_type) 
	{
		this.address_type = address_type;
	}

	public final void setBeneficiarie_id(Integer beneficiarie_id) 
	{
		this.beneficiarie_id = beneficiarie_id;
	}
		
	public final void setVivienda_TOKEN_COMPARABLE(String vivienda_TOKEN)
	{
		vivienda_TOKEN_COMPARABLE = vivienda_TOKEN;
	}
	
	public final void setAuto_save_ENABLED(boolean enabled) 
	{
		auto_save_ENABLED = enabled;
	}
	
	public final void setBitacora_ENABLED(boolean enabled) 
	{
		bitacora_ENABLED = enabled;
	}		
	
	public final Long getCoincidencias_NIVEL_1() 
	{
		return coincidencias_NIVEL_1;
	}

	public final Long getCoincidencias_NIVEL_2() 
	{
		return coincidencias_NIVEL_2;
	}

	public final String getVivienda_TOKEN() 
	{
		return vivienda_TOKEN;
	}

	public final String getCodigo_postal() 
	{
		return codigo_postal;
	}
	
	public final String getMap_ubication() 
	{
		return map_ubication;
	}

	public final String getDelegacion_municipio()
	{
		return delegacion_municipio_name;
	}
	
	public final String getEstado()
	{
		return estado_name;
	}
	
	public final String getMotivo_del_cambio()
	{
		return motivo_del_cambio;
	}
	
	public final String getPerson_type_title() 
	{
		return person_type_title;
	}

	public final Integer getColonia_id()
	{
		return colonia_id;
	}
	
	public final String getColonia_name() 
	{
		return colonia_name;
	}

	public final String getColonia_Text(){
		return colonia_Text;
	}		
	
	public final String getAlarma_NIVEL_1() 
	{
		return alarma_NIVEL_1;
	}

	public final String getAlarma_NIVEL_2() 
	{
		return alarma_NIVEL_2;
	}

	public final Integer getTipo_vivienda_id() 
	{
		return tipo_vivienda_id;
	}

	public final Address getAddress() 
	{
		return address;
	}

	public final ChangeBean getChange_control_bitacora() 
	{
		return change_control_bitacora;
	}

	public final List<NeighborhoodCat> getColonias_por_codigo_postal()
	{
		return colonias_por_codigo_postal;
	}

	public final List<Residence> getLista_tipo_vivienda() 
	{
		return lista_tipo_vivienda;
	}

	public String getScriptColonia() 
	{
		return scriptColonia;
	}
	
	public final String getColonia_ENABLED() 
	{
		return colonia_ENABLED;
	}

	public final String getOtra_colonia_ENABLED() 
	{
		return otra_colonia_ENABLED;
	}

	public final List<Coincidencia> getLista_coincidencias() 
	{
		return lista_coincidencias;
	}

	public void setColonia_ENABLED(String colonia_ENABLED) 
	{
		this.colonia_ENABLED = colonia_ENABLED;
	}

	public void setOtra_colonia_ENABLED(String otra_colonia_ENABLED) 
	{
		this.otra_colonia_ENABLED = otra_colonia_ENABLED;
	}

	public final boolean isAddress_ENABLED() 
	{
		return address_ENABLED;
	}

	public final boolean isFiscal_ENABLED() 
	{
		return fiscal_ENABLED;
	}
	
}
