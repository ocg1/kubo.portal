package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.model.PademobilePayment;
import mx.com.kubo.model.PademobilePaymentPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.PademobilePaymentService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.SHA1;


@ManagedBean
@ViewScoped
public class PagoPademovil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{systemParamServiceImp}")
	private SystemParamService systemparamservice;
	
	@ManagedProperty("#{pademobilePaymentServiceImp}")
	private PademobilePaymentService pademobilepaymentservice;
	
	private String codetran;
	private String sign;
	private String status;
	private String message;
	private String prospectus;
	
	private String value;
	private String key;
	
	private SessionBean sesion;
	
	private SystemParam systemparam;
	
	private boolean valid;
	
	@PostConstruct
	public void init(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		sesion = (SessionBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "sessionBean");
		
		codetran 	= (String) facesContext.getExternalContext().getRequestParameterMap().get("codtran");
		sign 		= (String) facesContext.getExternalContext().getRequestParameterMap().get("sign");
		status		= (String) facesContext.getExternalContext().getRequestParameterMap().get("status");
		message		= (String) facesContext.getExternalContext().getRequestParameterMap().get("message");
		prospectus	= (String) facesContext.getExternalContext().getRequestParameterMap().get("prospectus_id");
		
		String proyect_loan_id	= (String) facesContext.getExternalContext().getRequestParameterMap().get("proyect_loan_id");
		
		String proyect_id	= (String) facesContext.getExternalContext().getRequestParameterMap().get("proyect_id");
		
		String safi_credit_id	= (String) facesContext.getExternalContext().getRequestParameterMap().get("safi_credit_id");
		
		String ammount = (String) facesContext.getExternalContext().getRequestParameterMap().get("monto");
		
		PademobilePayment payment = new PademobilePayment();
		
		payment.setAccounted("N");
		payment.setAmmount(Double.parseDouble(ammount));
		payment.setCod_tran(codetran);
		payment.setMessage(message);
		payment.setPayment_date(new Date());
		payment.setProspectus_id(Integer.parseInt(prospectus));
		payment.setProyect_id(Integer.parseInt(proyect_id));
		payment.setProyect_loan_id(Integer.parseInt(proyect_loan_id));
		payment.setSafi_credit_id(safi_credit_id);
		payment.setStatus(status);
		
		PademobilePaymentPK pk = new PademobilePaymentPK();
		pk.setCompany_id(sesion.getCompany_id());
		payment.setPk(pk);
		
		if(pademobilepaymentservice.savePademobilePayment(payment)){
			
			System.out.println("Pago Guardado Satisfactoriamente!!!");
			
		}
		
	    try{
			
	    	FacesContext context = FacesContext.getCurrentInstance();
	    	HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
	    	//String requestURL = request.getRequestURL().toString();
	    	 
	    	String query = request.getQueryString();
	    	//System.out.println(requestURL+"?"+query);
	    	 
	    	 
	    	SystemParamPK syspk = new SystemParamPK();
			
			syspk.setCompany_id(1);
	    	
	    	syspk.setSystem_param_id(37); //valor para que nos traiga la llave;
	 		
	 		systemparam = systemparamservice.loadSelectedSystemParam(syspk);
	 		
	 		key = "";
	 		
	 		if(systemparam != null){
	 			key = systemparam.getValue();
	 		}
	    	
	 		//query = query.replace("%C3%B3", "ó");
	 		//query = query.replace("%C3%A9", "é");
	 		
	 		value = query;
	 		
	 		String firma = SHA1.hmacSha1(value, key);
	 		
	 		System.out.println("value: "+value);
	 		
	 		System.out.println("sign: "+sign);
	    	
	    	System.out.println("firma: "+firma);
	 		
	    	
	    	
	    	/********************************************************************************/
	    	
	    	
	    	String var[] = {"sign"}; 
	 		
	 		value = query;
	 		String value01 =  value;
	 		for(int y=0;y<var.length;y++){
	 			
	 			int pos = value01.indexOf(var[y]);
	 			
	 			if( pos != (-1) ){
	 			
			 		String valueTem = value01.substring(0,pos);
			 		
			 		int i = 0;
			 		
			 		for(i = pos ; i < value01.length(); i++ ){
			 			
			 			if (value01.charAt(i) == '&'){
			 		
			 				break;
			 				
			 			}
			 			
			 		}
			 		
			 		i++;
			 		
			 		
			 		valueTem += value01.substring(i);
			 		
			 		value01 = valueTem;
			 		
	 			}
		 		
	 		}
	 		value = value01;
	 		System.out.println("value: "+value);
	 		
	    	firma = SHA1.hmacSha1(value, key);
	    	 
	    	System.out.println("sign: "+sign);
	    	
	    	System.out.println("firma: "+firma);
	    	
	    	/********************************************************************************/
	    	
//	 		String []var2 = {"sign","prospectus_id"}; 
//	 		
//	 		value = query;
//	 		value01 =  value;
//	 		for(int y=0;y<var2.length;y++){
//	 			
//	 			int pos = value01.indexOf(var2[y]);
//	 			
//	 			if( pos != (-1) ){
//	 			
//			 		String valueTem = value01.substring(0,pos);
//			 		
//			 		int i = 0;
//			 		
//			 		for(i = pos ; i < value01.length(); i++ ){
//			 			
//			 			if (value01.charAt(i) == '&'){
//			 		
//			 				break;
//			 				
//			 			}
//			 			
//			 		}
//			 		
//			 		i++;
//			 		
//			 		valueTem += value01.substring(i);
//			 		
//			 		value01 = valueTem;
//			 		
//	 			}
//		 		
//	 		}
//	 		
//	 		value = value01;
//	 		
//	 		System.out.println("value: "+value);
//	 		
//	    	firma = SHA1.hmacSha1(value, key);
	    	 
	    	System.out.println("sign: "+sign);
	    	
	    	System.out.println("firma: "+firma);
			
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		
		
		// "prospectus="+prospectus+"&codtran"+codetran+"tipo_pago";
		
		//prospectus_id=18&codtran=f8d75560a699ccb3ace2d38f22aaec94&tipo_pago=pademobile&sign=8c11b0aae5dfaa44c432ab97931e14db7ded7228&status=true&message=La operación se ha realizado con éxito
	}
	
	public String getCodetran() {
		return codetran;
	}
	public void setCodetran(String codetran) {
		this.codetran = codetran;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getProspectus() {
		return prospectus;
	}

	public void setProspectus(String prospectus) {
		this.prospectus = prospectus;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public SystemParam getSystemparam() {
		return systemparam;
	}

	public void setSystemparam(SystemParam systemparam) {
		this.systemparam = systemparam;
	}

	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public PademobilePaymentService getPademobilepaymentservice() {
		return pademobilepaymentservice;
	}

	public void setPademobilepaymentservice(
			PademobilePaymentService pademobilepaymentservice) {
		this.pademobilepaymentservice = pademobilepaymentservice;
	}
	
	
	
}