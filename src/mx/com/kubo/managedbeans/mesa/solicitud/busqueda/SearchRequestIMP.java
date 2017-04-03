package mx.com.kubo.managedbeans.mesa.solicitud.busqueda;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.tools.Utilities;

@ManagedBean(name = "searchRequest") @ViewScoped
public final class SearchRequestIMP extends SearchRequestAMO
implements SearchRequestIMO, Serializable 
{	
	private static final long serialVersionUID = -6471572633253608047L;

	@PostConstruct
	public void init()
	{					
		init_sesion_variables();
		
		coach_engine = new SearchEngineIMP();
		coach_engine.setService_search_request(service_search_request);
		coach_engine.setService_membership    (service_membership);
		coach_engine.setService_proyect_loan  (service_proyect_loan);
		coach_engine.setSesion(sesion);
		coach_engine.setSesion_search_request(sesion_search_request);
		coach_engine.setSummary_request(summary_request);
	}
	
	public final void listener_filtro_area(AjaxBehaviorEvent evento)
	{
		select_one_radio = (HtmlSelectOneRadio) evento.getComponent();		
		
		filtro_area_SELECTED = null;
		
		filtro_area_SELECTED = (Integer) select_one_radio.getValue();
						
		System.out.println("listener_filtro_area(): " + filtro_area_SELECTED);		
	}
	
	public final List<ClientViewFullName> completeinfoclient(String patron_busqueda)
	{
		
		System.out.println( "Buscando: '"+patron_busqueda+"'" );
		
		suggestions = service_search_request.getLista_por_filtro(patron_busqueda.trim(), filtro_area_SELECTED, radioTypeSearch);
							
		return suggestions;		
	}
	
	public void ejecutaBusqueda(){
		try
		{	
			if( radioTypeSearch == 8 ){  //NÚMERO DE CRÉDITO
			
				proyectLoan = service_proyect_loan.getProyectLoanListBySafiCreditID( search );
				
				if(proyectLoan != null){
				
					prospectus = proyectLoan.getPerson().getProspectus();
					
					prospectusPK = 	prospectus.getProspectusPK();
				
					prospectus_id = prospectus.getProspectusPK().getProspectus_id();
				}
				
			}else if( radioTypeSearch == 10 && Utilities.isNumeric( search ) ){
				
				if( search.length() == 18 ){
				
					//clabeaccountservice.loadClabeAccountListByProspectus(prospectus_id, company_id);
					
					ClabeAccount cl = clabeaccountservice.loadClabeAccountByClabeStr(search);
					
					if( cl != null ){
						search = cl.getClabepk().getProspectus_id()+"";
					}
					
				}
					
					MembershipPK mpk = new MembershipPK();
					
					mpk.setCompany_id(1);
					mpk.setProspectus_id( Integer.parseInt( search ) ); 
					
					member =  service_membership.getMembershipById(mpk);
					
					if( member != null ){
						
						search = member.getMembershipPK().getProspectus_id() +"";
						
						prospectus = member.getPerson().getProspectus();
						
						prospectusPK = 	prospectus.getProspectusPK();
					
						prospectus_id = prospectusPK.getProspectus_id();
						
					}
				
				
				
			} else {
				
					prospectus = null;
					proyectLoan = null;
					proyect_loan_ENABLED = false;
				
					if( radioTypeSearch == 9 && !Utilities.isNumeric( search ) ){
						
						member =  service_membership.getMembershipByEmail( search.trim() );
						
						if( member != null ){
							
							search = member.getMembershipPK().getProspectus_id() +"";
						}
					}
					
					if( Utilities.isNumeric(search) ){
						init_prospectus();
					}
					
					if(prospectus != null)
					{		
						proyectLoan = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id);
					}
					
			}
					
					
					if(prospectus != null)
					{		
						//proyectLoan = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id);
						
						if(proyectLoan != null)
						{	
							proyect_loan_ENABLED = true;
							
							init_acreditado_TOKEN();
												
							sesion_search_request.setSearchSummary(acreditado_TOKEN);
							
							faces = FacesContext.getCurrentInstance();
							
							faces .getViewRoot().getViewMap().remove("summaryRequest");
							
							faces .getViewRoot().getViewMap().remove("chartBackBean");
							
							faces .getViewRoot().getViewMap().remove("checkScoreProcessed");
							
							resolver  = faces.getApplication().getELResolver();	
							
							elContext = faces.getELContext();
							
							summary_request = (SummaryRequest)  resolver.getValue(elContext, null, "summaryRequest");
//							
							summary_request.init();					
							
							isPerson          = false;
							actividad_ENABLED = true;
							
							if(acreditado_TOKEN != null)
							{						
								type_log = "EVA";
								
								isValid              = true;
								actividad_ENABLED    = true;				        
										
								init_menu();										
							} 
							
						} else {
							
							procesar_area();			
						}						
					} else{
						
						isPerson = false ; type_log =  null;  actividad_ENABLED = false; acreditado_TOKEN = null; investor_ENABLED = false;
						isPerson = false;  isValid = false;  actividad_ENABLED = false; proyect_loan_ENABLED = false; investor_ENABLED =false;
						
					}
					
					System.out.println("\n\n\n\n\n");
					
					System.out.println ( isPerson+" - " +type_log+" - " +actividad_ENABLED+" - " +acreditado_TOKEN+" - " +investor_ENABLED);
					
					System.out.println ("isPerson"+ isPerson+" - " +"isValid" + isValid+" - " +"actividad_ENABLED"+    actividad_ENABLED+" - " +"proyect_loan_ENABLED"+ proyect_loan_ENABLED+" - " +"investor_ENABLED"+  investor_ENABLED);
					
					System.out.println("\n\n\n\n\n");
					
					if( prospectus != null ){
					
						addAccess( );
					
					}
					
					sesion_search_request.setPerson(isPerson);
					sesion_search_request.setTypeLog(type_log);
					sesion_search_request.setActividad_ENABLED(actividad_ENABLED);
					sesion_search_request.setSearchSummary(acreditado_TOKEN);
					sesion_search_request.setInvestor_ENABLED(investor_ENABLED);
					
					request = RequestContext.getCurrentInstance();
					
					request.addCallbackParam("isPerson", isPerson);
					request.addCallbackParam("isValid", isValid);					
					request.addCallbackParam("actividad_ENABLED",    actividad_ENABLED);
					request.addCallbackParam("proyect_loan_ENABLED", proyect_loan_ENABLED);
					request.addCallbackParam("investor_ENABLED",     investor_ENABLED);
					
					System.out.println(" Fin ");
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		System.out.println(" Terminó ");
	}
	
	public final void listener_buscar_solicitud(ActionEvent evento)
	{					
		ejecutaBusqueda();
	}
}
