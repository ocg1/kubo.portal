package mx.com.kubo.bean;

import mx.com.kubo.model.Business;
import mx.com.kubo.model.Employment;

public abstract class ActividadEconomicaDMO 
{
	protected Employment employment;
	protected Business business;
	
	protected AddressBean addressbean;
	
	protected PhoneBean phonebeanFixed;
	protected PhoneBean phonebeanCel;
	
	protected String check_inH1;
	protected String check_inM1;
	protected String check_outH1;
	protected String check_outM1;
	
	protected String yearOrYears;
	protected String monthOrMonths;
	
	protected String same_address;
	
	protected boolean same_address_ENABLED;		
	
	public AddressBean getAddressbean() 
	{
		return addressbean;
	}

	public PhoneBean getPhonebeanFixed() 
	{
		return phonebeanFixed;
	}
	
	public PhoneBean getPhonebeanCel() 
	{
		return phonebeanCel;
	}
	
	public String getYearOrYears() 
	{
		return yearOrYears;
	}
	
	public String getMonthOrMonths() 
	{
		return monthOrMonths;
	}

	public void setAddressbean(AddressBean addressbean) 
	{
		this.addressbean = addressbean;
	}
	
	public void setPhonebeanFixed(PhoneBean phonebeanFixed) 
	{
		this.phonebeanFixed = phonebeanFixed;
	}
	
	public void setPhonebeanCel(PhoneBean phonebeanCel) 
	{
		this.phonebeanCel = phonebeanCel;
	}
	
	public void setYearOrYears(String yearOrYears) 
	{
		this.yearOrYears = yearOrYears;
	}
	
	public void setMonthOrMonths(String monthOrMonths) 
	{
		this.monthOrMonths = monthOrMonths;
	}
	
	public void setSame_address(boolean same_address__ENABLED) 
	{
		if(same_address__ENABLED)
		{
			same_address = "N";
			
		} else {
			
			same_address = "S";
		}
	}
	
	public boolean getSame_address_ENABLED()
	{		
		same_address_ENABLED = false;
		
		if(same_address != null && same_address.equals("N"))
		{
			same_address_ENABLED = true;						 
		}
		
		return same_address_ENABLED;
	}
	
	public String getSame_address() {
		return same_address;
	}
	
	public void setSame_address(String same_address) {
		this.same_address = same_address;
	}
}
