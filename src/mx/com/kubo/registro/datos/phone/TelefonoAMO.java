package mx.com.kubo.registro.datos.phone;

import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;

public abstract class TelefonoAMO extends TelefonoDMO 
{
	protected void actualizaPhoneInfusion( String phonestr )
	{	
		try
		{			
			Integer contactId = prospectus.getInfusion_id();
			
			if( contactId != null )
			{			
				SystemParamPK system_param_PK_I = new SystemParamPK();
				
				system_param_PK_I.setCompany_id( 1 );
				system_param_PK_I.setSystem_param_id(88); // Bandera que índica si infusion esta habilitado
				
				 SystemParam system_param_I = systemParamService .loadSelectedSystemParam(system_param_PK_I);
				
				 if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
				 
					InfusionSoft infusion = new InfusionSoft();
					infusion.actualizaTelefonoContacto(contactId, phonestr);
						
				 }
			 
			}
		 
		}catch( Exception e ){
			
			e.printStackTrace();
			
		}
	
	}
}
