package mx.com.kubo.mesa.solicitud.resumen;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.PurposeService;
import mx.com.kubo.tools.Utilities;

public abstract class ChangeControlDMO implements ChangeControlIMO
{
	protected RequestContext request;
	
	protected        PurposeService service_purpose;
	protected    ProyectLoanService service_proyect_loan;	
	protected        ProyectService service_proyect;		
	protected Change_controlService service_change_control;
	
	protected HtmlSelectOneMenu select_one_menu;
	protected HtmlInputText input_text;
	
	protected ProyectLoan proyect_loan;
	
	protected Change_control   change_control;
	protected Change_controlPK change_control_PK;
	protected ChangeBean change_control_bean;	
	
	protected List<Change_control>  bitacora_change_control;
	
	protected String[] tables;
	protected String[] fields;
	
	protected String ip_address;
	protected String original_value;
	protected String afected_table;
	protected String afected_table_type;
	protected String field;
	
	protected Integer field_type_id;
	
	protected int company_id;
	protected int prospectus_id;
	protected int emisor_prospectus_id;
	
	protected boolean change_control_OK;
	protected boolean update_OK;	
	
	protected ChangeControlDMO()
	{
		service_change_control = Utilities.findBean("change_controlServiceImp");
	}
	
	public final void setSesion(SessionBean sesion) 
	{
		emisor_prospectus_id = sesion.getProspectus_id();
		
		ip_address = sesion.getIP_address_client();
	}
	
	public void setProyect_loan(ProyectLoan proyect_loan)
	{
		this.proyect_loan = proyect_loan;
		
		if(proyect_loan != null)
		{
			company_id    = proyect_loan.getProyectloanPk().getCompany_id();
			prospectus_id = proyect_loan.getProyectloanPk().getProspectus_id();	
		}
	}
	
	public boolean isUpdate_OK()
	{
		return update_OK;
	}
	
	public ProyectLoan getProyect_loan()
	{
		return proyect_loan;
	}
	
	public final ChangeBean getChange_control_bean() 
	{
		return change_control_bean;
	}
}
