package mx.com.kubo.managedbeans.registro.consulta;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.registro.verificacion.PersonaBloqueadaAMO;

@ManagedBean (name = "creditHistoryController") @ViewScoped
public final class CreditHistoryController extends CreditHistoryControllerAMO
implements CreditHistoryControllerIMO, Serializable
{		
	private static final long serialVersionUID = -1823457936020120056L;

	@PostConstruct
	public void init()
	{						
		faces     = FacesContext.getCurrentInstance();
		external  = faces.getExternalContext();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		area           = sesion.getArea();
		prospectus_id  = sesion.getProspectus_id();
		company_id     = sesion.getCompany_id();
		
		membership_PK = new MembershipPK(); 
		membership_PK.setCompany_id(company_id);
		membership_PK.setProspectus_id(prospectus_id);
		
		membership = service_membership.getMembershipById(membership_PK);
		
		sessionUsed = (HttpSession) external.getSession(false);
		
		sessionId = sessionUsed.getId();							
			
		init_pre_aprobacion();
		init_credit_history();
		init_historial_crediticio();
		init_promotor_id();
		init_person_data();	
	}	

	public void muestraTarjetaDepAndSave ()
	{		
		if(credithistory.getDepcc_is_principal() != null)
		{
			if(credithistory.getDepcc_is_principal() == 0)
			{
				hidden2 = "none";
				credithistory.setDepcc_company(null);
				credithistory.setDepcc_limit(null);
				
			} else {
				
				hidden2 = "block";
			}
		}
		
		saveCreditHistory();
	}
			
	public String callWebService()
	{
		int result=(int) (Math.random()* 2+1);
		System.out.println("Web Service DICE = "+result);
		
		if(result ==1)
		{
			return "ofertaInicial";
			
		} else {
			
			return "infoResult";
		}		
	}
		
/*	
	public final void init_autorizar_consulta(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
				
		select_radio = (HtmlSelectOneRadio) evento.getComponent();
		
		Integer consulta_ENABLED = Integer.parseInt(select_radio.getValue().toString());
		
		if(consulta_ENABLED == 0)
		{		
			init_change_control("", "", "", "S");
		}
		
		if(consulta_ENABLED > 0)
		{		
			init_change_control("", "", "", "N");
		}
		
		request.addCallbackParam("consulta_ENABLED", consulta_ENABLED);
	}
*/
	
	public final void listener_have_credit(AjaxBehaviorEvent evento)
	{
		have_credit = null;
		
		ajax_input_radio = (HtmlSelectOneRadio) evento.getComponent();
		
		have_credit = (Integer) ajax_input_radio.getValue();
		
		credithistory.setHave_credit(have_credit);
		
		saveCreditHistory();
		
		System.out.println("CreditHistoryController.listener_have_credit(): " + have_credit);
	}
	
	public final void listener_first_recent_credit(AjaxBehaviorEvent evento)
	{
		first_recent_credit = null;
		
		ajax_input_radio = (HtmlSelectOneRadio) evento.getComponent();
		
		first_recent_credit = (Integer) ajax_input_radio.getValue();
		
		credithistory.setFirst_recent_credit(first_recent_credit);
		
		saveCreditHistory();
		
		System.out.println("CreditHistoryController.listener_first_recent_credit(): " + first_recent_credit);
	}
	
	public final void listener_asignar_nota_del_coach(AjaxBehaviorEvent evento)
	{
		ajax_inputText = (HtmlInputText) evento.getComponent();
		
		nota_del_coach = (String) ajax_inputText.getValue();
		
		asignar_nota_coach();
		
		System.out.println("CreditHistoryController.listener_asignar_nota_del_coach(): " + nota_del_coach);
	}
		
	public final void listener_guardar_nota_del_coach()
	{
		is_nota_OK = service_notas.addNote(nota_coach);
		
		if(is_nota_OK)
		{
			System.out.println("CreditHistoryController.listener_guardar_nota_del_coach(): OK");	
			
		} else {
			
			System.out.println("CreditHistoryController.listener_guardar_nota_del_coach(): ERROR");
		}		
	}
	
	public void getPersonDataCreditHistory(){
		
		SystemParamPK spkH = new SystemParamPK();
		
		spkH.setCompany_id(1);
		spkH.setSystem_param_id( 90 );
		
		SystemParam param = systemParamService.loadSelectedSystemParam( spkH );
		
		if( param != null && param.getValue().equals("S") ) {
		
			init();
			sesion.setNombre( membership.getPerson().getFirst_name() + " " + membership.getPerson().getFather_last_name() );
			data.getPersonData();
			
			
		}else{
			
			request = RequestContext.getCurrentInstance();	
			request.addCallbackParam("isActive", false);
			
		}
		
	}

}
