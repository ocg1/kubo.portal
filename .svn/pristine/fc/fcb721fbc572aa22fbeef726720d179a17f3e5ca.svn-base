package mx.com.kubo.mesa.solicitud.investor;

import static mx.com.kubo.notificaciones.notificables.Evento.ERROR_DESARROLLO;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.ApplicationParams;
import mx.com.kubo.managedbeans.CreaCreditoService;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.notificaciones.notificables.Evento;

public class ActivadorIMP extends ActivadorAMO
implements ActivadorIMO
{
	public void creaClienteInvSafi(ActionEvent e)
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion      = (SessionBean)        resolver.getValue(elContext, null, "sessionBean");	
		creacredito = (CreaCreditoService) resolver.getValue(elContext, null, "creaCreditoService");
		
		String prospectus=(String) e.getComponent().getAttributes().get("prospectusid").toString();	
		// String company=(String) e.getComponent().getAttributes().get("companyid").toString();
		
		company_id = sesion.getCompany_id();
		prospectus_id_inv = Integer.parseInt(prospectus);
				
		init_proyect_loan();
		
		lista_errores = creacredito.getLstErrors();
		
		creacredito.setParam_values_OK(true);
		creacredito.setLista_errores(lista_errores);
		creacredito.setProyect_loan(proyect_loan);
		
		alta_prospecto_OK = creacredito.callWSSafiAltaPros();		
		lista_errores     = creacredito.getLstErrors();
		
		if(alta_prospecto_OK)
		{		
			creacredito.setLista_errores(lista_errores);
			creacredito.setSolicitud_credito_OK(true);
			
			SAFI_client_id = creacredito.creaClienteSAFI();
			lista_errores  = creacredito.getLstErrors();
			
			if(proyect_loan.getPerson().getCitizenship() == EXTRANJERO)
			{
				callSPSafiAltaIFE(prospectus_id_inv, company_id, proyect_loan);
				
			} else {
			
				if(SAFI_client_id != null)
				{
					if(acreditado_IFE != null)
					{
						 callSPSafiAltaIFE(prospectus_id_inv, company_id, proyect_loan);
						 
					} else {
						
						lista_errores.add("El cliente no cuenta con la clave de elector.");						
					}
				 	
				} else {
					
					lista_errores.add("No existe el id del cliente en Safi.");					
				}
			}
			
			
			if(SAFI_client_id != null && SAFI_client_id.length() > 0)
			{
				creacredito.setLista_errores(lista_errores);
				
				cuenta_OK     = creacredito.creaCuentaSafi();
				lista_errores = creacredito.getLstErrors();
				
				if(cuenta_OK)
				{
					pld = service_PLD.getPrevencionLDByProspectus(prospectus_id_inv, company_id);
					
					if(pld != null)
					{
						saving = service_saving_account.getSavingAccountByProspectus(prospectus_id_inv, company_id);
						
						if(saving != null && saving.getSafi_account_id() != null && !saving.getSafi_account_id().equals("") && !saving.getSafi_account_id().equals(" ") )
						{							
							creacredito.setLista_errores(lista_errores);
							
							creacredito.creaPLDCuentaSAFI();
							
							lista_errores = creacredito.getLstErrors();
						}
						
						if(SAFI_client_id != null && !SAFI_client_id.equals("") && !SAFI_client_id.equals(" ")  )
						{			
							creacredito.setLista_errores(lista_errores);
							
							creacredito.createPLDSAFI();
							
							lista_errores = creacredito.getLstErrors();
							
							investor_PK = new InvestorPK();
							
							investor_PK.setCompany_id(company_id);
							investor_PK.setProspectus_id(prospectus_id_inv);
							
							investor = service_investor.getInvestorById(investor_PK);
							investor.setStatus_id(2);
							
							service_investor.updateInvestor(investor);
							
							notificacion( Evento.ACTIVACION_INVERSIONISTA , "" ,membership, investor.getMember());	
							
							
							//****************
							
							ApplicationParams applicationParam = (ApplicationParams) FacesContext.getCurrentInstance()
									.getApplication().getELResolver()
									.getValue(elContext, null, "applicationParams");
							
							applicationParam.getHt_member_investor().put(SAFI_client_id, membership);
							
							//*****************
							
						}
					}
					
					 //msg = "Tus datos como inversionista han sido creados satisfactoriamente";
					 
				} else {
					
					notificacion( ERROR_DESARROLLO, "fallo alta de cuenta Safi!!",membership, membership );
				}
				
			} else {
				
				notificacion( ERROR_DESARROLLO, "fallo alta de clienteSafi !!",membership, membership );
			}
			
		} else {
			
			notificacion(ERROR_DESARROLLO, "fallo alta de prospecto Inversionista!!", membership, membership);
		}
		
		request = RequestContext.getCurrentInstance();
		
		strRes = "";
		
		lista_errores = creacredito.getLstErrors();
		
		if(lista_errores != null && lista_errores.size() > 0)
		{			
			request.addCallbackParam("isError", true);
			
			strRes = "";
			
			for (String target : lista_errores)
			{
				 if(!target.equals("remove"))
				 {
					 strRes += target + "::";
				 }
			}

		} else {
			
			request.addCallbackParam("isError", false);
			
			strRes = "El Inversionista se dió de alta satisfactoriamente.";			
		}
		
		request.addCallbackParam("respuestaStr", strRes);
		
		request.addPartialUpdateTarget("creditos");			
	}
}
