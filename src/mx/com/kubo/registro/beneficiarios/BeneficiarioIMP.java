package mx.com.kubo.registro.beneficiarios;

import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.Benefi_ciaries;

public final class BeneficiarioIMP extends BeneficiarioAMO
implements BeneficiarioIMO
{
	public final void init()
	{
		init_fecha_nacimiento_INPUT();
	}
	
	public final void init_same_addres(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_radio = (HtmlSelectOneRadio) event.getComponent();
		
		same_address = select_radio.getValue().toString();		
		
		beneficiarie_id = (Integer) event.getComponent().getAttributes().get("beneficiarie_id");
		
		request.addCallbackParam("beneficiarie_id", beneficiarie_id);
		request.addCallbackParam("same_address", same_address);
	}
	
	public final void persist(Benefi_ciaries bean) 
	{		
		request = RequestContext.getCurrentInstance();
		
		beneficiario_bean = bean;
		
		init_beneficiario_bean();
		
		if(same_address != null && same_address.equals("S"))
		{	
			if( bean.getAddressbean() != null ){
				address = bean.getAddressbean().getAddress();
			}
			
			asignar_mismo_domicilio();
		}
		
		update_beneficiarie();
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("save_OK", save_OK);
	}
	
	public void init_date_of_birth(Benefi_ciaries bean) 
	{
		beneficiario_bean = bean;
		
		init_date_of_birth();
		
		if(datebirth_ENABLED)
		{
			persist(beneficiario_bean);
		}
	}
}
