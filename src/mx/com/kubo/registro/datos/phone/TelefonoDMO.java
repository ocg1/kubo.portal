package mx.com.kubo.registro.datos.phone;

import org.apache.log4j.Logger;

import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.SystemParamService;

public abstract class TelefonoDMO 
implements TelefonoIMO 
{
	protected Logger log = Logger.getLogger(getClass());
	
	protected PhoneService phoneService;
	protected SystemParamService systemParamService;
	
	protected Phone thisPhoneFixed;
	
	protected Prospectus prospectus;
	
	protected Phone thisPhoneCell;
	
	protected String phoneCellProspectus;
	protected String ladaCelProspectus;
	protected String ladaFixedProspectus;
	protected String phoneFixedPropectus;
	
	protected Character area;
	
	protected int prospectus_id;
	protected int company_id;
	
	protected boolean hasPhoneCell;
	protected boolean hasPhoneFixedProspectus;
	
	public void setProspectus(Prospectus prospectus)
	{
		this.prospectus = prospectus;
		
		prospectus_id = prospectus.getProspectusPK().getProspectus_id();
		   company_id = prospectus.getProspectusPK().getCompany_id();
	}
	
	public void setHasPhoneCell(boolean hasPhoneCell) {
		this.hasPhoneCell = hasPhoneCell;
	}
	
	public boolean isHasPhoneCell() {
		return hasPhoneCell;
	}
	
	public String getPhoneCellProspectus() {
		return phoneCellProspectus;
	}
	
	public String getLadaCelProspectus() {
		return ladaCelProspectus;
	}
	
	public Phone getThisPhoneCell() {
		return thisPhoneCell;
	}
}
