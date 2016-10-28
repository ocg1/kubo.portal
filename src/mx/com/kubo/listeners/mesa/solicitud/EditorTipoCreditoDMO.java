package mx.com.kubo.listeners.mesa.solicitud;

import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.LoanType;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.tools.Utilities;

public abstract class EditorTipoCreditoDMO 
implements EditorTipoCreditoIMO
{
	protected ProyectLoanService    service_proyect_loan;	
	protected Change_controlService service_change_control;
	
	protected HtmlSelectOneMenu select_one_menu;
	
	protected Change_control   changeCtrl;
	protected Change_controlPK changeCtrlPK;
	protected ChangeBean change_control_bean;	
	
	protected List<Change_control>  bitacora_change_control;
	protected List<LoanType> lista_loan_type;
	
	protected ProyectLoan proyect_loan;
	
	protected String loan_type_id;
	protected String tipo_de_credito;
	protected String ip_address;
	
	protected String[] tables;
	protected String[] fields;
	
	protected int company_id;
	protected int prospectus_id;
	protected int emisor_prospectus_id;
	
	protected boolean change_control_OK;
	protected boolean update_OK;
	
	protected EditorTipoCreditoDMO()
	{
		service_change_control = Utilities.findBean("change_controlServiceImp");
		service_proyect_loan   = Utilities.findBean("proyectLoanServiceImp");
		
		lista_loan_type = service_proyect_loan.getLista_loan_type();
		
		tables = new String[]{"ln_proyect_loan"};
		fields = new String[]{"loan_type"};
	}
	
	public final void setSesion(SessionBean sesion) 
	{
		emisor_prospectus_id = sesion.getProspectus_id();
		ip_address = sesion.getIP_address_client();
	}
	
	public final void setProyect_loan(ProyectLoan proyect_loan)
	{
		this.proyect_loan = proyect_loan;
		
		if(proyect_loan != null)
		{
			company_id    = proyect_loan.getPerson().getNatPerPK().getCompany_id();
			prospectus_id = proyect_loan.getPerson().getNatPerPK().getProspectus_id();	
			
			loan_type_id    = proyect_loan.getLoantype().getPk().getLoan_type_id();
			tipo_de_credito = proyect_loan.getLoantype().getLoan_type_desc();
			
			init_change_control();
		}
	}
	
	protected void init_change_control() 
	{		
		change_control_bean = new ChangeBean();
		change_control_bean.setOrigValue(loan_type_id);
		change_control_bean.setNameTable("ln_proyect_loan");
		change_control_bean.setNameField("loan_type");
			
		bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
			
		change_control_bean.setHasChange (bitacora_change_control != null && bitacora_change_control.size() > 0 ? true : false);
		change_control_bean.setLstChanges(bitacora_change_control != null && bitacora_change_control.size() > 0 ? bitacora_change_control : null);
	}
	
	public final String getLoan_type_id() 
	{
		return loan_type_id;
	}
	
	public final String getTipo_de_credito() 
	{
		return tipo_de_credito;
	}

	public final List<LoanType> getLista_loan_type() 
	{
		return lista_loan_type;
	}

	public final ChangeBean getChange_control_bean() 
	{
		return change_control_bean;
	}
}
