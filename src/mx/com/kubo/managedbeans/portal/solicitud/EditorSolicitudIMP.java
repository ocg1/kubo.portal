package mx.com.kubo.managedbeans.portal.solicitud;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.portal.AccessIMP;
import mx.com.kubo.portal.reader.ParameterReaderIMP;

@ManagedBean(name = "editor_solicitud") @ViewScoped
public final class EditorSolicitudIMP extends EditorSolicitudDMO 
implements Serializable
{
	private static final long serialVersionUID = -6131542276265727457L;

	@PostConstruct
	public final void init()
	{
		System.out.println( "" );
		System.out.println( "" );
		
		System.out.println( "*******************************" );
		System.out.println( "*******************************" );
		System.out.println( "***EditorSolicitudIMP.init()***" );
		System.out.println( "*******************************" );
		System.out.println( "*******************************" );
		
		System.out.println( "" );
		System.out.println( "" );
		
		
		faces = FacesContext.getCurrentInstance();
		
		context = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();	
		
		init_sesion();
		
		session_sumary  = (SearchSummaySession) resolver.getValue(context, null, "searchSummaySession");
		session_sumary.setSearchSummary(searchSummary);
		
		summary_request = (SummaryRequest) resolver.getValue(context, null, "summaryRequest");
		summary_request.init();
	}
		
	protected void init_sesion() 
	{
		reader = new ParameterReaderIMP();
		reader.setFaces(faces);
		reader.init_sesion();
				  
		access_from = reader.getAccess_from();
	        	
		if(access_from != null)
		{			 
			         sesion = reader.getSesion();
			     page_title = reader.getPage_title();	
			proyect_loan_id = reader.getProyect_loan_id();		
		          credit_id = reader.getCredit_id();
         
			auditor = new AccessIMP();
			auditor.setService_access(service_access);
			auditor.setSesion(sesion);
			auditor.setScreen_id(SCREEN_EDITAR_SOLICITUD);
			auditor.setAccess_from(access_from);
			
			          company_id = sesion.getCompany_id();
			       prospectus_id = sesion.getCoachProspectus_id();
			prospectus_id_viewed = sesion.getProspectus_id();	 
		}				
		
		if(proyect_loan_id != null)
		{         																	          
	        proyect_loan = service_proyect_loan.getProyectLoanByProyectLoanID(proyect_loan_id, prospectus_id_viewed, company_id);	        
	        
		} else if (credit_id != null) {
			
			proyect_loan = service_proyect_loan.getProyectLoanListBySafiCreditID(credit_id);
			
			proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
		}
		
		if(proyect_loan != null)
		{		
	        proyect_id = proyect_loan.getProyectloanPk().getProyect_id();
	        
	        searchSummary = proyect_loan_id + "::" + proyect_id + "::" + prospectus_id_viewed + "::" + company_id;
		}
	}
	
}
