package mx.com.kubo.managedbeans.mesa.solicitud.adicional;

import static mx.com.kubo.managedbeans.mesa.solicitud.adicional.TipoCreditoAdicional.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.ProyectLoan;

import org.primefaces.context.RequestContext;

@SuppressWarnings("serial")
@ManagedBean(name = "additionalCredit") 
@SessionScoped
public class AdditionalCreditIMP extends AdditionalCreditAMO
implements AdditionalCreditIMO, Serializable 
{			
	public final void init(ProyectLoan proyect_loan_ACTUAL)
	{				
		init_valores_default();
		init_sesion_login();
		
		setPermissions(sesion.getRole_id());
		
		proyect_loan = proyect_loan_ACTUAL;
		
		prospectus_id  = proyect_loan.getProyectloanPk().getProspectus_id();
		company_id     = proyect_loan.getProyectloanPk().getCompany_id();			
		bur_sol_num    = proyect_loan.getMx_solicitud_buro();	
		safi_credit_id = proyect_loan.getSafi_credit_id();
		
		if(safi_credit_id != null && safi_credit_id.length() > 0)
		{
			proyectloan_safi_credit_id = Integer.parseInt(proyect_loan.getSafi_credit_id());
			
		} else {
			
			proyectloan_safi_credit_id = -1;
		}
		
		asignar_flag_no_publish();
		asignar_lista_pagos();		
		asignar_SGB_risk_get_resume();									
		asignar_documentacion();		
	}
	
	public final void cargar_solicitud_de_credito(ActionEvent evento_AJAX)
	{
		proyect_loan_SEARCH_TOKEN = (String) evento_AJAX.getComponent().getAttributes().get("proyectAtrr").toString();
		
		request   = RequestContext.getCurrentInstance();		
		faces     = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		cal1 = Calendar.getInstance();			
		cal1.setTime(new Date());									
		
		control_table = (MenuControlTableBean) resolver.getValue(elContext, null, "menuControlTableBean");
		
		evento_AJAX.getComponent().getAttributes().put("section", "controlTable/searchRequest::12::menu1");
		
		control_table.cambiaPagina(evento_AJAX);
		
		summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
					
		summarysesion.setPerson(false);			
		summarysesion.setSearchSummary(proyect_loan_SEARCH_TOKEN);			
		summarysesion.setTypeLog("EVA");
		
		summary = (SummaryRequest) resolver.getValue(elContext, null, "summaryRequest");
					
		cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		
		milis1 = cal1.getTimeInMillis();
		milis2 = cal2.getTimeInMillis();
		
		diff = milis2 - milis1;
		
		System.out.println( "tardó: "+diff+" milisegundos");
		
		cal1.setTime(new Date());
		
		summary.mapeoDatos(summarysesion.getSearchSummary());	
		
		cal2.setTime(new Date());
		
		milis1 = cal1.getTimeInMillis();
		milis2 = cal2.getTimeInMillis();		
		
		diff = milis2 - milis1;						
		
		System.out.println( " tardó: " + diff + " milisegundos en cargar la sabana");
							
		request.addPartialUpdateTarget("form_Prin");
		request.addPartialUpdateTarget("actualPage");
		request.addPartialUpdateTarget("pnlCancel");
	}
	
	public final void renovar_solicitud_de_credito()
	{		
		System.out.printf("\nAdditionalCreditIMP.renovar_solicitud_de_credito(): \n");
		
		try
		{		
			reasignador_service.setSesionBean(sesion);
			reasignador_service.init(proyect_loan);
			reasignador_service.renovar_solicitud_de_credito(RENOVACION);
			
			proyect_loan = proyectloanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
			
			proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
			proyect_id      = proyect_loan.getProyectloanPk().getProyect_id();
			
			ver_nueva_solicitud_ENABLED  = true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			setRenderDocuments(false);
			setRenderError(true);
			setRenderSuccess(false);			
		}
	}
	
	public final void generaSolicitud()
	{						
		if(getAdditionalType() == 1)
		{			
			System.out.println("NUEVA_CONSULTA_ENABLED - selectedItems:  " + getSelectedItems());
			
			tipo_credito_adicional = NUEVA_CONSULTA_ENABLED;						
			
			generar_solicitud();
			
		} else if(getAdditionalType() == 2 ) {
			
			System.out.printf("\nAdditionalCreditIMP.generaSolicitud(): NUEVA_CONSULTA_DISABLED - selectedItems:  \n" + getSelectedItems());
			
			tipo_credito_adicional = NUEVA_CONSULTA_DISABLED;						
			
			generar_solicitud();			
		}			
	}	
	
	private void generar_solicitud()
	{										
		try
		{		
			reasignador_service.setSesionBean(sesion);
			reasignador_service.setProyect_loan_reasignable(proyect_loan);
			reasignador_service.crear_nuevo_proyecto(tipo_credito_adicional, getLoanType());
							
			copiar_archivos();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			setRenderDocuments(false);
			setRenderError(true);
			setRenderSuccess(false);			
		}		
	}
}
