package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.InvestorParam;
import mx.com.kubo.model.InvestorParamPK;
import mx.com.kubo.model.ProfileInv;


@ManagedBean
@ViewScoped
public class Ajustes extends AjustesPMO implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	@PostConstruct
	public void init(){
		
		FacesContext faces = FacesContext.getCurrentInstance();
		
		ELResolver resolver = faces.getApplication().getELResolver();
		ELContext context  = faces.getELContext();
		
		sesion          	= (SessionBean)       resolver.getValue(context, null, "sessionBean");
		
		investment_list     = (InvestmentList)       resolver.getValue(context, null, "investmentList");	
		
		paramCat = paramcatsevice.getInvestmentParamCat();
		
		initScript();
		
		inicializaSaldos();
		
		registraAccess();
		
		puedeInvertirFG();
		
	}

	
	public void updateParam(ActionEvent e)
    {
    	
    	Map<String , String > map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    	
    	HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		
    	ipAddressClient  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
    	
    	String sum_max_E5 			= map.get("param_id_"+ID_SUMA_MONTO_MAXIMO_E5_INV);
    	String sum_max_FG 			= map.get("param_id_"+ID_SUMA_MONTO_MAXIMO_FG_INV);
    	
    	InvestorParam inP = new InvestorParam();
    	InvestorParam inPT = new InvestorParam();
    	InvestorParamPK inPK = new InvestorParamPK();
    	
    	
    		if(  ht != null ){
    			inPT = ht.get(ID_SUMA_MONTO_MAXIMO_E5_INV);
    		}
    		
    		if( sum_max_E5 != null &&( ht == null || inPT == null || ( inPT != null && inPT.getValue_str() != null && !inPT.getValue_str().equals(sum_max_E5) ) ) ){
    		
		    	inPK.setCompany_id(sesion.getCompany_id());
		    	inPK.setProspectus_id(sesion.getProspectus_id());
		    	inPK.setInvestment_param_id(ID_SUMA_MONTO_MAXIMO_E5_INV);
		    	
		    	inP.setPk(inPK);
		    	inP.setLast_asign_date(new Date());
		    	inP.setValue_str(sum_max_E5);
		    	
		    	investor_param_service.updateInvestorParam(inP);
		    	
		    	saveChangeData( inPT.getValue_str(), sum_max_E5, "El usuario modificó el límite máximo en E5");

		    	inPT.setValue_str(sum_max_E5);
		    	
	    	
    		}
    	
    	
    	inP = new InvestorParam();
    	inPK = new InvestorParamPK();
    	inPT = null;
    	
    	
    	
    		if(  ht != null ){
    			inPT = ht.get(ID_SUMA_MONTO_MAXIMO_FG_INV);
    		}
    		
    		if( sum_max_FG != null &&( ht == null || inPT == null || ( inPT != null && inPT.getValue_str() != null && !inPT.getValue_str().equals(sum_max_FG) ) ) ){
    		
		    	inPK.setCompany_id(sesion.getCompany_id());
		    	inPK.setProspectus_id(sesion.getProspectus_id());
		    	inPK.setInvestment_param_id(ID_SUMA_MONTO_MAXIMO_FG_INV);
		    	
		    	inP.setPk(inPK);
		    	inP.setLast_asign_date(new Date());
		    	inP.setValue_str(sum_max_FG);
		    	
		    	investor_param_service.updateInvestorParam(inP);
	    	
		    	saveChangeData( inPT.getValue_str(), sum_max_FG, "El usuario modificó el límite máximo en F y G");
		    	
		    	inPT.setValue_str(sum_max_FG);
		    	
    		}
    	
    	
    	
    }
	
	private void puedeInvertirFG(){
	
		ProfileInv profile =  profile_inv_service.getProfileInvByProspectus( sesion.getProspectus_id(), sesion.getCompany_id () );
		
		puedeInvertire_en_F_G = false;
		
		if(profile != null){
		
			if( profile.getProfile_categor_user_id() != null && profile.getProfile_categor_user_id() == 3 ){
				
				puedeInvertire_en_F_G = true;
				
			}else if( profile.getProfile_categor_user_id() == null && profile.getProfile_category_id() == 3 ){
				puedeInvertire_en_F_G = true;
			}
			
		}
		
	}
	
	protected boolean saveChangeData( String origValue, String newValue, String comment)
	{
		
		String table= "in_investor_param";
		String field= "value_str";
		
		Change_control changeCtrl   = new Change_control();
		Change_controlPK changeCtrlPK = new Change_controlPK();		
		
		changeCtrlPK.setProspectus_id(sesion.getProspectus_id());
		changeCtrlPK.setCompany_id(sesion.getCompany_id());		
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
		changeCtrl.setIp(ipAddressClient);
		changeCtrl.setAfected_table(table);				
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);
		changeCtrl.setField(field);
		changeCtrl.setComments(comment);
		
		if(service_change_control.addChangeControl(changeCtrl, sesion.getProspectus_id(), sesion.getCompany_id()))
		{
			return true;	
		} else {
			return false;
		}
	}
	
	private void registraAccess(){
		
		Access access = new Access();
		
		
		access.setScreen_id(79);
			
		access.setUrl_access( sesion.getUrl_access() );
		access.setPercentage(0);
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUser_agent      (sesion.getUser_agent());
		access.setDevice_info    (sesion.getDevice_info());
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		
		boolean isValid = true; 
		
		if( sesion.getCompany_id() != null )
		{
			access.setCompany_id(sesion.getCompany_id());
			
		}else{
		
			isValid = false;
			
		}
		
		if( sesion.getProspectus_id() != null )
		{
			access.setProspectus_id( sesion.getProspectus_id() );
		
		}else{
			
			isValid = false;
		}
		
		if(isValid)
		{
			accessService.add(access, true);
		}
		
	}
	
	
	
}
