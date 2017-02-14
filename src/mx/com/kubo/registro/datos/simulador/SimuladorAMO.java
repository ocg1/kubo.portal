package mx.com.kubo.registro.datos.simulador;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.portal.simulador.CreditSimulatorIMP;

public abstract class SimuladorAMO extends SimuladorDMO 
{
	public final void init_simulator()
	{				
		request = RequestContext.getCurrentInstance();
		
		credit_simulator = new CreditSimulatorIMP(term_id, purpose_id);
		
		//credit_simulator.setSesion(sesion);
		
		credit_simulator.setProspectus_id( prospectus_id );
		credit_simulator.setCompany_id( company_id );		
		credit_simulator.setTasaTotal( tasaTotal );
		credit_simulator.setComisionApertura( comisionApertura );		
		credit_simulator.setAmmount(ammount);
		credit_simulator.setFrequency_id(frequency_id);
		
		credit_simulator.init_cuota_by_formula();
		
		simulation = credit_simulator.getSimulation();				
	}
	
	protected void init_change_control(String field, String original_value, String new_value) 
	{			
		change_control_ENABLED = ! new_value.equalsIgnoreCase(original_value);
		
		if(change_control_ENABLED)
		{	
			change_control_PK = new Change_controlPK();
			change_control    = new Change_control();		
			
			change_control_PK.setProspectus_id(prospectus_id);
			change_control_PK.setCompany_id(company_id);
			
			change_control.setChange_controlPK(change_control_PK);		
			change_control.setChange_prospectus_id(change_prospectus_id);
			
			change_control.setAfected_table("ln_simulation");	
			change_control.setField(field);
			
			change_control.setOriginal_value(original_value);
			change_control.setNew_value(new_value);
			
			change_control.setComments(comments);
			change_control.setIp(IP_address_client);
			change_control.setFocus_date(focus_date);

			change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);
		}
	}
}
