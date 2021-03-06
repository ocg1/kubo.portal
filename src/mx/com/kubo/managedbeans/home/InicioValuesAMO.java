package mx.com.kubo.managedbeans.home;

import java.util.Date;


import mx.com.kubo.managedbeans.preregistro.PreregistroIMO;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.QrAccess;
import mx.com.kubo.tools.Utilities;

public abstract class InicioValuesAMO extends InicioValuesDMO
{
	protected void init_sesion() 
	{
		
		if(sesion.getArea() != null && sesion.getArea() == 'I')
		{				
			area = "I";
			
		} else {
			
			preregistro  = (PreregistroIMO) resolver.getValue(context, null, "addNaturalPerson");
			
			area = "L";
			preregistro.setArea('L');				
		}
		
	}
	
	protected void init_external_params() 
	{
		
		qrParam        = (String) external.getRequestParameterMap().get("origen");			
		
		partner        = (String) external.getRequestParameterMap().get("partner");			
		partner_id     = (String) external.getRequestParameterMap().get("partner_id");			
								
		sm_first_name  = (String) external.getRequestParameterMap().get("sm_first_name");			
		sm_email       = (String) external.getRequestParameterMap().get("sm_email");	
		promotorID     = (String) external.getRequestParameterMap().get("promotor");
		
		referred       = (String) external.getRequestParameterMap().get("inf_field_FirstName");
		
		utm_medium	   = (String) external.getRequestParameterMap().get("utm_medium");
		utm_origen     = (String) external.getRequestParameterMap().get("utm_origen");			
		utm_campaign   = (String) external.getRequestParameterMap().get("utm_campaign");
		utm_partner_id = (String) external.getRequestParameterMap().get("utm_source");
		
		user_agent     = (String) external.getRequestParameterMap().get("user_agent");
		
		if( utm_partner_id != null && (utm_partner_id.equalsIgnoreCase("INVITEDBY") || utm_partner_id.equalsIgnoreCase("REC") )  ){
			initReferred();
		}
		
	}
	
	protected void init_partner() 
	{
		if(partner == null && partner_id != null)
		{
			partner = partner_id;
		}
		
		if( utm_partner_id != null &&  utm_partner_id.trim().length() > 0 && !utm_partner_id.equalsIgnoreCase("INVITEDBY") && !utm_partner_id.equalsIgnoreCase("REC") )
		{
			partner = utm_partner_id;
		}
		
		if(sesion != null )
		{			
			if(partner != null)
			{				
				if(partner.equals("10000034"))
				{
					renderLogoPartner = true;
					sesion.setPartner("ALIANZA BARARED");
					
				} else if( partner.length() > 0) {
					
					sesion.setPartner(partner);
					
				}					
			}
			
			if(sm_email != null || sm_first_name != null )
			{
				sesion.setPartner(null);
				sesion.setSm_email(sm_email);
				sesion.setSm_first_name(sm_first_name);
			}
			
			if( sesion.getPartner() != null && sesion.getPartner().equals("ALIANZA BARARED"))
			{
				renderLogoPartner = true;
			}				
		}
		
		if( user_agent != null && user_agent.trim().length() >0 && !user_agent.trim().equalsIgnoreCase("null") )
		{
			//System.out.println( "******* InicioValuesAMO INSERTANDO VALOR *******" );
			//System.out.println( "******* USER AGENT: "+ user_agent +" *******" );
			//System.out.println( "***************************************************" );
			sesion.setUser_agent( user_agent );
		}
		
		//System.out.println( "\n\n******* Partner: "+sesion.getPartner()+" *******\n\n" );
		
	}
	
	protected void init_medium(){
		
		if( (utm_medium != null &&  utm_medium.trim().length() > 0) ) {
			
			sesion.setMedium( utm_medium );
		
		}
		
	}
	
	protected void init_origin_campaign() 
	{
		if( (utm_origen != null &&  utm_origen.trim().length() > 0) ) {
			qrParam = utm_origen;
		
		} else if( utm_campaign != null &&  utm_campaign.trim().length() > 0 ) {
			
			qrParam = utm_campaign;				
		}
		
		if( utm_partner_id != null && (utm_partner_id.equalsIgnoreCase("INVITEDBY") || utm_partner_id.equalsIgnoreCase("REC") ) ){
			qrParam = utm_partner_id + " : "+utm_medium;
		}
		
		if(qrParam != null){
			sesion.setOrigen_campaign(qrParam);
			sesion.setUrl_value(url_value);
			insertQrParam();
		}
	}

	protected void initReferred(){
		
		if( referred != null && !referred.trim().isEmpty() ){
			
			//referred = Utilities.decodeBase64( referred );
			
			referred = Utilities.encodeBase64( referred );
			
			Membership membership = membershipService.getMembershipByEmail(referred);
			
			if( membership != null ){
				
				sesion.setReferred_client(membership.getMembershipPK().getProspectus_id()+"");
				sesion.setReferred_not_client("");
				
			}else{
				
				sesion.setReferred_client("");
				sesion.setReferred_not_client(referred);
				
			}
		}
		
	}
	
	private void insertQrParam() {
		qr = new QrAccess();
		
		qr.setQr_param(qrParam);
		qr.setQr_date(new Date());
		
		service_access.saveQrAccess(qr);
	}
}
