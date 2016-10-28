package mx.com.kubo.services.impl;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMO;
import mx.com.kubo.services.Change_controlService;

import org.springframework.stereotype.Service;

@Service (value = "change_controlServiceImp")
public class Change_controlServiceImp extends ServiceChangeControlDMO
implements Change_controlService
{	
	public final boolean registrar_change_control(ChangeBean change_control_bean, CasosPospuestosIMO pospuesto, int company_id, int emisor_prospectus_id, int acreditado_prospectus_id) 
	{				
		String fecha_ORIGINAL = "null";
		
		if(pospuesto.getFecha_ORIGINAL() != null)
		{
			fecha_ORIGINAL = formatter_date.format(pospuesto.getFecha_ORIGINAL());
		} 

		change_control_bean.setOrigValue(fecha_ORIGINAL);
		change_control_bean.setNewValue(formatter_date.format(pospuesto.getFecha_NEW()));
		
		change_control_bean.setCompany_id              (company_id);
		change_control_bean.setAcreditado_prospectus_id(acreditado_prospectus_id);
		change_control_bean.setEmisor_prospectus_id    (emisor_prospectus_id);
		
		change_control_bean.setWhyChangeData("Fecha pospuesta al " + formatter_date.format(pospuesto.getFecha_NEW()));
		
		if(change_control_bean.isValueChanged())
		{		
			return addChangeControl(change_control_bean);
			
		} else {
			
			return false;
		}
	}
		
	public final boolean registrar_change_control(ChangeBean change_control_bean, String estatus_ORIGINAL, String estatus_NEW, String comentario, int company_id, int emisor_prospectus_id, int acreditado_prospectus_id) 
	{
		change_control_bean.setNewValue (estatus_NEW);
		change_control_bean.setOrigValue(estatus_ORIGINAL);		
		change_control_bean.setCompany_id(company_id);
		change_control_bean.setAcreditado_prospectus_id(acreditado_prospectus_id);
		change_control_bean.setEmisor_prospectus_id    (emisor_prospectus_id);
		
		change_control_bean.setWhyChangeData(comentario);
		
		if(change_control_bean.isValueChanged())
		{			
			return addChangeControl(change_control_bean);
			
		} else {
			
			return false;
		}
	}
	
	public final boolean registrar_change_control(ChangeBean change_control_bean, int company_id, int emisor_prospectus_id, int acreditado_prospectus_id) 
	{		
		change_control_bean.setCompany_id              (company_id);
		change_control_bean.setAcreditado_prospectus_id(acreditado_prospectus_id);
		change_control_bean.setEmisor_prospectus_id    (emisor_prospectus_id);
		
		if(change_control_bean.isValueChanged())
		{			
			return addChangeControl(change_control_bean);
			
		} else {
			
			return false;
		}
	}
}
