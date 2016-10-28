package mx.com.kubo.mesa.solicitud.perfil;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.EconActivityBean;
import mx.com.kubo.model.ViewEconomicInfo;

public final class ActividadEconomicaIMP extends ActividadEconomicaAMO
implements ActividadEconomicaIMO
{	
	public final void init()
	{		
		change_control = new ChangeBean();
		
		lista_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id,company_id,new String[]{"gn_business","gn_employment"},new String[]{"bmx_econ_activity_id"});
		
		change_control.setHasChange (lista_change_control!=null && lista_change_control.size()>0?true:false);
		change_control.setLstChanges(lista_change_control!=null && lista_change_control.size()>0?lista_change_control:null);
							
		init_employment();
		init_business();		
	}	

	public final List<ViewEconomicInfo> listener_autocomplete(String query)
	{
		suggestions = new ArrayList<ViewEconomicInfo>();
		
		suggestions = service_view_client_info.getListEconomicByDescription(query);
		
		return suggestions;
	}
	
	public final void save(EconActivityBean econActBean)
	{		
		actividad_economica = econActBean;
		
		bmx = econActBean.getBmxActivity();
		
		bmx_activity_id = bmx.getBmx_econ_activity_id();
		bmx_inegi_id    = bmx.getInegi_econ_activity_id();
		bmx_sector_id   = bmx.getEcon_sector_id();
		bmx_LABEL       = bmx.getDescription();
		
		index             = econActBean.getIndex();
		motivo_del_cambio = econActBean.getWhyChangeData();
		tipo_de_actividad = econActBean.getTypeEconActivity();
		actividad_LABEL   = econActBean.getActivityDesc();
		
		if(bmx != null && motivo_del_cambio != null)
		{
			guardar_business();
			guardar_employment();	
			init_employment();
			init_business();
		}
	}	
}
