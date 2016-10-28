package mx.com.kubo.bean;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.change_control.ChangeControlEMO;
import mx.com.kubo.model.Change_control;

public class ChangeBean 
{
	private ChangeControlEMO change_control;
	
	private String nameTable;
	private String nameField;
	private String origValue;
	private String newValue;
	private String whyChangeData;
	
	private String ip;
	
	private int company_id;
	private int emisor_prospectus_id;
	private int acreditado_prospectus_id;	
	
	private boolean hasChange;
	
	private List<Change_control> lstChanges;
	
	private String bank_description;
	
	public ChangeBean(ChangeControlEMO change_control)
	{
		this.change_control = change_control;
		
		ip = getIpAddressClient();
		whyChangeData = "";
	}
	
	public ChangeBean()
	{
		super();
	}
	
	public boolean isValueChanged()
	{
		boolean changed_ENABLED = false;
		
		if(newValue != null && origValue != null)
		{
			changed_ENABLED = ! newValue.equalsIgnoreCase(origValue);
		}
		
		return changed_ENABLED;
	}
	
	private String getIpAddressClient()
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		
		String ipAddressClient  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
	    
		if(ipAddressClient == null)
		{
	    	ipAddressClient = httpServletRequest.getRemoteAddr();
		}
		
		return ipAddressClient;
	}

	public final void setIp(String ip) 
	{
		this.ip = ip;
	}

	public void setCompany_id(int company_id) 
	{
		this.company_id = company_id;
	}

	public void setEmisor_prospectus_id(int prospectus_id) 
	{
		emisor_prospectus_id = prospectus_id;
	}

	public void setAcreditado_prospectus_id(int prospectus_id) 
	{
		acreditado_prospectus_id = prospectus_id;
	}	
	
	public void setChange_control(ChangeControlEMO change_control) 
	{
		this.change_control = change_control;
	}

	public ChangeControlEMO getChange_control() 
	{
		return change_control;
	}

	public String getNameTable() 
	{
		return nameTable;
	}

	public void setNameTable(String nameTable) 
	{
		this.nameTable = nameTable;
	}

	public String getNameField() 
	{
		return nameField;
	}

	public void setNameField(String nameField) 
	{
		this.nameField = nameField;
	}

	public String getOrigValue() 
	{
		return origValue;
	}

	public void setOrigValue(String origValue) 
	{
		this.origValue = origValue;
	}

	public String getNewValue() 
	{
		return newValue;
	}

	public void setNewValue(String newValue) 
	{
		this.newValue = newValue;
	}

	public String getWhyChangeData() 
	{
		return whyChangeData;
	}

	public void setWhyChangeData(String whyChangeData) 
	{
		this.whyChangeData = whyChangeData;
	}
	
	public boolean isHasChange() 
	{
		return hasChange;
	}

	public void setHasChange(boolean hasChange) 
	{
		this.hasChange = hasChange;
	}

	public List<Change_control> getLstChanges() 
	{
		return lstChanges;
	}

	public void setLstChanges(List<Change_control> lstChanges) 
	{
		this.lstChanges = lstChanges;
	}

	public String getBank_description() 
	{
		return bank_description;
	}

	public void setBank_description(String bank_description) 
	{
		this.bank_description = bank_description;
	}
	
	public final String getIp() 
	{
		return ip;
	}
	
	public int getCompany_id() 
	{
		return company_id;
	}

	public int getEmisor_prospectus_id() 
	{
		return emisor_prospectus_id;
	}
	
	public int getAcreditado_prospectus_id() 
	{
		return acreditado_prospectus_id;
	}
}
