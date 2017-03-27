package mx.com.kubo.registro.datos.phone;

import org.apache.log4j.Logger;

import mx.com.kubo.model.Phone;
import mx.com.kubo.registro.ChangeControlDMO;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

public abstract class TelefonoDMO extends ChangeControlDMO
implements TelefonoIMO 
{
	protected Logger log = Logger.getLogger(getClass());
	
	protected PhoneService phoneService;
	protected SystemParamService systemParamService;
	
	protected Phone thisPhoneFixed;			
	protected Phone thisPhoneCell;
	
	protected String phoneFixedPropectus;
	protected String phoneCellProspectus;
	protected String ladaCelProspectus;
	protected String ladaFixedProspectus;	
	
	protected Integer infusion_id;
	
	protected int phone_type_id;
	
	protected final int IS_INFUSION_ENABLED = 88;
	protected final int PARTICULAR_CELULAR = 6;
	protected final int PARTICULAR_FIJO = 5;
	
	protected boolean hasPhoneCell;
	protected boolean hasPhoneFixedProspectus;
	
	protected TelefonoDMO()
	{
		      phoneService = Utilities.findBean("phoneServiceImp");
		systemParamService = Utilities.findBean("systemParamServiceImp");
	}
	
	public void setInfusion_id(Integer infusion_id)
	{
		this.infusion_id = infusion_id;
	}
	
	public String getPhoneCellProspectus() 
	{
		return phoneCellProspectus;
	}
	
	public String getPhoneFixedPropectus() 
	{
		return phoneFixedPropectus;
	}

	public Phone getThisPhoneFixed() {
		return thisPhoneFixed;
	}
	
	public Phone getThisPhoneCell() {
		return thisPhoneCell;
	}

}
