package mx.com.kubo.services.fondeo.cliente;

import mx.com.kubo.model.Income;

public abstract class AltaConocimientoClienteAMO extends AltaConocimientoClienteDMO 
{	
	protected void init_values() 
	{
		principal_fuente_ingresos = "";
		
		if((area + "").equals("L"))
		{
			resInc = 0D;
			
			lstIng = service_ingresos.getListIncomeByProspect(proyect_loan.getPerson().getNatPerPK().getProspectus_id(), proyect_loan.getPerson().getNatPerPK().getCompany_id());
			
			if(lstIng != null && lstIng.size() > 0)
			{						
				for(Income income : lstIng)
				{
					if(income.getAmmount() != null)
					{
						resInc =+ income.getAmmount();
					}
				}
			}
				
			if(resInc <= 20000)
			{							
				pld.setAprox_monthly_income("Ing1");
					
			} else if(resInc > 20000 && resInc <= 50000) {
					
					pld.setAprox_monthly_income("Ing2");
					
			} else if(resInc > 50000&&resInc <= 100000) {
					
				pld.setAprox_monthly_income("Ing3");
					
			} else if(resInc > 100000) {
					
				pld.setAprox_monthly_income("Ing4");
					
			}
		}
						
		listB = service_business.getListBusinessByProspect(prospectus_id, company_id);
		
		if(listB!=null && listB.size()>0)
		{
			bmx = service_business.findBmxActivityById(listB.get(0).getBmx_econ_activity_id(), listB.get(0).getBusinessPK().getCompany_id());
			
			if(bmx != null)
			{
				principal_fuente_ingresos = "Comerciante del giro "+bmx.getDescription();
			}
		}

		listE = service_employment.getListEmployByProspect(prospectus_id, company_id);
		
		if(listE != null && listE.size() > 0)
		{
			principal_fuente_ingresos = "Empleado del giro ";
		}
				
		if(principal_fuente_ingresos.length() > 99 )
		{
			principal_fuente_ingresos = principal_fuente_ingresos.substring(0,98);
		}
		
		pld.setPrincipal_income(principal_fuente_ingresos);
		
		service_PLD.updatePrevencionLD(pld);
	}
	
	protected void init_alta_conocimiento_cliente() 
	{
		response = service_SAFI.createPLDSAFI(pld, SAFI_client_id, prospectus_id, company_id);
		
		if(!response.getCodigoRespuesta().equals("0"))
		{
			mensaje_respuesta = response.getMensajeRespuesta().trim();
			
			int indexsp = mensaje_respuesta.indexOf("CONOCIMIENTOCTEALT");
			int indexdk = mensaje_respuesta.indexOf("Duplicate entry");
			
			if(indexsp > 0 && indexdk > 0)
			{
				lista_errores.add("Este cliente ya tiene Conocimiento de Cliente.");
				
			} else {
				
				lista_errores.add(mensaje_respuesta);
			}
		}
	}
}
