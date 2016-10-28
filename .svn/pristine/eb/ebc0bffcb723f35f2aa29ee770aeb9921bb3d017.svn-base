package mx.com.kubo.notificaciones.creditos;


import mx.com.kubo.services.impl.BitacoraServiceCalling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.soa.model.businessobject.Vtbur_infocredcte_c;
import com.soa.model.businessobject.Vtbur_infocredcte_m;
import com.soa.model.businessobject.Vtbur_infocredcte_vig;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;

public abstract class CreditosVigentesDMO 
{
	protected String creditos_vigentes_INFO;
	
	protected WsSgbRiskServiceLocator  locator;
	protected WsSgbRisk                webService;
	
	@Autowired @Qualifier("bitacoraServiceCalling")	
	protected BitacoraServiceCalling   bitacora;
	
	protected Vtbur_infocredcte_vig [] creditosVigentes;
	protected Vtbur_infocredcte_c   [] cerradosAnterior;
	protected Vtbur_infocredcte_m   [] cerradosPosterior;

//TODO Desacoplado
//	protected MailSenderIMP notificacion;
	
	private int company_id, prospectus_id;
	
	protected CreditosVigentesDMO()
	{
		company_id    = -1;
		prospectus_id = -1;
		creditos_vigentes_INFO = "";
	}
	
	public void setCompany_id(int company_id) 
	{
		bitacora.setCompany_id(company_id);
	}
	
	public void setProspectus_id(int prospectus_id) 
	{
		bitacora.setProspectus_id(prospectus_id);
	}
//TODO Refactorizar
/*
	public void setNotificacion(MailSenderIMP notificacion)
	{
		this.notificacion = notificacion;
	}
*/
	
	public int getCompany_id()
	{		
		return company_id;
	}

	public int getProspectus_id()
	{		
		return prospectus_id;
	}
}
