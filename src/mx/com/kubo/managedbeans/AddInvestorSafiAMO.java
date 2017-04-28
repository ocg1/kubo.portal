package mx.com.kubo.managedbeans;

import java.util.ArrayList;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.webServices.WsSgbRiskServiceLocator;

import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.bean.jackson.AplicationPublicationInvestorDataDTO;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Country;
import mx.com.kubo.model.CountryPK;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
import mx.com.kubo.registro.publicacion.DocumentsReviewIMP;

public abstract class AddInvestorSafiAMO extends AddInvestorSafiDMO 
{
	protected void init_investor() 
	{
		investor_PK = new InvestorPK();						
		investor_PK.setCompany_id(sesion.getCompany_id());
		investor_PK.setProspectus_id(sesion.getProspectus_id());
		
		investor = investorservice.getInvestorById(investor_PK);
		
		if( investor != null && investor.getStatus_id()!=null && investor.getStatus_id()!=0)
		{
			successFull = true;
			
			countList = savingaccountservice.getListAccountByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
			
			if(countList!=null&&countList.size()>0)
			{
				for(SavingAccount saving_account : countList)
				{
					if ( saving_account.getStatus() == 1 )
					{						
						flagaccount = true;
						successFull = false;						
					}					
				}				
			}			
		}
	}
	
	protected void init_menu() 
	{
		if(!successFull)
		{			
			menuIncomplete = accessService.loadMenu(sesion.getProspectus_id(),sesion.getCompany_id(),sesion.getArea());
			
			if(menuIncomplete != null && menuIncomplete.size() > 0)
			{			
				boolean flag = false;
				
				recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");				
				
				listRequiredMenu = new ArrayList<MenuRegBean>();
				
				for(AccessCollector access : menuIncomplete)
				{				
					if(access!=null&&access.getPercentage()!=null&&access.getPercentage()<100 && access.getScreen_id()!=18)
					{						
						menureq = new MenuRegBean();
						menureq.setTargetItem(access.getName());
						menureq.setScreenid(access.getScreen_id());
						menureq.setIdItem("menu"+access.getMenu_order());
						menureq.setNameItem(recurso.getString(access.getResource_name()));
						
						listRequiredMenu.add(menureq);
						flag = true;
					}
					
					else if(access.getPercentage()==null && access.getScreen_id()!=6)
					{
						menureq = new MenuRegBean();
						menureq.setTargetItem(access.getName());
						menureq.setScreenid(access.getScreen_id());
						menureq.setIdItem("menu"+access.getMenu_order());
						menureq.setNameItem(recurso.getString(access.getResource_name()));
						flag = true;
						listRequiredMenu.add(menureq);
					}
				}
				
				if(flag && !flagaccount)
				{					
					setErrorDisp(true);
					
					return;
				}
			}			
		}
	}
	
	protected void hubspot_update_person() 
	{
		try
		{				
			system_param_PK_I = new SystemParamPK();
			system_param_PK_I.setCompany_id( 1 );
			system_param_PK_I.setSystem_param_id(IS_INFUSION_ENABLED);
			
			system_param_I = systemparamservice.loadSelectedSystemParam(system_param_PK_I);
			
			boolean is_infusion_ENABLED = system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") && membership.getPerson().getProspectus().getInfusion_id() != null;
			
			if(is_infusion_ENABLED)
			{			 
				InfusionSoft infusion = new InfusionSoft();
					
				infusion.addTAgToContact( membership.getPerson().getProspectus().getInfusion_id() , 551 ); // tag Llena Registro								
			}			 			
			
			boolean consulta_prospector_rechazado = membership.getPerson() != null && membership.getPerson().getProspectus() != null && membership.getPerson().getProspectus().getHs_vid() != null;
			
			if(consulta_prospector_rechazado)
			{						
				system_param_PK_I = new SystemParamPK();					
				system_param_PK_I.setCompany_id( 1 );
				system_param_PK_I.setSystem_param_id(IS_HUBSPOT_ENABLED);
					
				system_param_I = systemparamservice.loadSelectedSystemParam(system_param_PK_I);
				
				boolean is_hubspot_ENABLED = system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S");
				
				if(is_hubspot_ENABLED)
				{						
					hs =  new HubSpotController();
						
					properties = new StringBuilder(  "{ \"property\" : \"estatus_inversionista\" , \"value\" : \"solicitud_finalizada\"}" ) ;
						
						//String properties = "estatus_inversionista=finalizo_solicitud&hbuhu&";
						
						hs.updateProspectus(membership.getPerson().getProspectus().getHs_vid(), properties);
						
						
						hs.sendEvent( "000002052984", membership.getEmail() );
						
						//hs.createField( properties);
						
					 }
				}
			 		 
		} catch( Exception e ) {
			
			e.printStackTrace();
		}
	}
	
	protected void registraAcceso( SessionBean sesion, int screen, int porcent)
	{	
		access = new Access();		
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id( sesion.getProspectus_id() );
		access.setScreen_id( screen );
		access.setPercentage( porcent );
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUrl_access( sesion.getUrl_access() );
		access.setUser_agent(sesion.getUser_agent());
		access.setDevice_info(sesion.getDevice_info());
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access,true);		
	}
	
	protected void creaProspect_INV_SGB()
	{
		System.out.println("+++++    LLAMANDO AL SGB    +++++");

		try 
		{
			locator = new WsSgbRiskServiceLocator();
			service = locator.getWsSgbRisk();// yyyymmdd. 19860131
			
			String clabe = "";
			
			String banco = "";
			
			String _JSON_str = "";
			
			ap_INV = new AplicationPublicationInvestorDataDTO();
			
			accountList  = clabeaccountservice.loadClabeAccountListByProspectus(prospectus_id, membership.getMembershipPK().getCompany_id());
			
			if( accountList != null && accountList.size() > 0  )
			{			
				banco = accountList.get(0).getBank_description();
				clabe = accountList.get(0).getMx_clabe();				
			}
			
			CountryPK cpk = new CountryPK(membership.getPerson().getCountry_id(),membership.getMembershipPK().getCompany_id() );
			
			Country c = countryService.getCountryById( cpk );
			
			StateCatPK stPK = null;
			
			StateCat st = null;
			
			if( membership.getPerson().getState_id() != null )
			{			
				stPK = new  StateCatPK(membership.getPerson().getState_id() ,membership.getMembershipPK().getCompany_id() );
				st =  service_estado.getStateById(stPK);			
			}
		
			if( c != null)
			{
				ap_INV .setCountryOfBirthName( c.getName() );
			}
			
			ap_INV .setDateOfBirth( membership.getPerson().getDate_of_birth() );
			ap_INV .setFatherLastName(membership.getPerson().getFather_last_name());
			ap_INV .setFirstName(membership.getPerson().getFirst_name());
			ap_INV .setGenderId(membership.getPerson().getGender_id());
			ap_INV .setMail(membership.getEmail());
			ap_INV .setMiddleName(membership.getPerson().getMiddle_name());
			ap_INV .setMotherLastName(membership.getPerson().getMother_last_name());
			ap_INV .setMxBankDescription(banco);
			ap_INV .setMxClabe(clabe);
			ap_INV .setMxCurp(membership.getPerson().getMx_curp());
			ap_INV .setMxRfc(membership.getPerson().getMx_rfc());
			ap_INV .setProspectusId(membership.getMembershipPK().getProspectus_id());
			ap_INV .setReason(membership.getRegistration_reason().getName());
			
			if(st != null)
			{
				ap_INV .setStateOfBirthName( st.getName() );
			}
			
			ap_INV .setStatusId(1);
			
			ObjectMapper mapper = new ObjectMapper();
			
							//Object to JSON in String
			try 
			{				
				String jsonInString = mapper.writeValueAsString(ap_INV);
				_JSON_str = jsonInString;
				
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
				
			}

			System.out.println( "******" );
			System.out.println( "***SGB*REQUEST**" );
			System.out.println( _JSON_str );
			System.out.println( "******" );
			System.out.println( "******" );
			
			service.aplicationPublicationInvestor(_JSON_str);
			
		} catch(Exception e) {
			
			e.printStackTrace();			
		}		
	}
	
	protected void init_documents_review() 
	{
		documents = new DocumentsReviewIMP();	
		documents.setPerson(person);
		documents.init();
	}
	
	protected void notificar()
	{					
		notificacion_OK = false;
		
		try 
		{
			notificador = new NotificadorIMP();
			notificador.setEmisor(membership);
			notificador.notificar(Evento.SOLICITUD_INVERSIONISTA_EXITOSA);
			
		} catch (NotificacionException e) {			
			e.printStackTrace();
		}
		
		try 
		{
			notificador = new NotificadorIMP();
			notificador.setEmisor(membership);
			notificador.notificar(Evento.SOLICITUD_INVERSIONISTA_FINALIZADA);
			
			notificacion_OK = true;
			
		} catch (NotificacionException e) {			
			
			e.printStackTrace();
		}
	}
}
