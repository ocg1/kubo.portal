package mx.com.kubo.mesa.solicitud.perfil;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.tools.Utilities;

public abstract class EditorIdentificationDMO 
implements EditorIdentificationIMO 
{
	protected  NaturalPersonService service_natural_person;
	protected Change_controlService service_change_control;
	
	protected RequestContext request;
	
	protected HtmlSelectOneMenu select_menu;
	protected HtmlInputText input_text;
	
	protected SessionBean sesion;
	protected NaturalPerson person;
	
	protected ChangeBean changeDataIFE;
	protected ChangeBean ifeChangeTem;
	
	protected List <ChangeBean>     lstChangeIFE;	
	protected List <Change_control> lstChange_ife;
	
	protected String input_text_id;
	protected String input_text_value;
	protected String label;
	protected String whyChangeData;
	
	protected final String label_INE = "Número CIC";
	protected final String label_PASAPORTE = "Pasaporte";
	protected final String label_CARTILLA  = "Cartilla Militar";
	protected final String label_LICENCIA  = "Licencia de Manejo";
	protected final String label_CEDULA    = "Cédula Profesional";
	
	protected Integer identification_type_id;
	
	protected boolean label_ENABLED;
	protected boolean editor_ife_ENABLED;
	
	protected final int IFE = 1;
	protected final int INE = 2;
	protected final int PASAPORTE = 3;
	protected final int CARTILLA  = 4;
	protected final int LICENCIA  = 5;
	protected final int CEDULA    = 6;
	
	protected EditorIdentificationDMO()
	{
		service_natural_person = Utilities.findBean("naturalPersonServiceImp");
		service_change_control = Utilities.findBean("change_controlServiceImp");
	}		

	public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
	}

	public void setPerson(NaturalPerson person) 
	{
		this.person = person;
	}	

	public NaturalPerson getPerson() 
	{
		return person;
	}		

	public Integer getIdentification_type_id() 
	{
		return identification_type_id;
	}

	public String getLabel() 
	{
		return label;
	}

	public boolean isLabel_ENABLED() 
	{
		return label_ENABLED;
	}		
	
  	public final boolean isEditor_ife_ENABLED()
	{
		return editor_ife_ENABLED;
	}
  	
  	public ChangeBean getChangeDataIFE() 
  	{
		return changeDataIFE;
	}
  	
 	public List<ChangeBean> getLstChangeIFE() 
 	{
		return lstChangeIFE;
	}
}
