package mx.com.kubo.bean;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.Phone;

public final class PhoneReview extends PhoneReviewDMO
implements Serializable
{	
	private static final long serialVersionUID = -7625310413879503259L;
	
	private List<Phone> lstPhoneUpdate;
	private List<PersonAndPhones> lstPersonPhone;
	private List<ReferencesReview> lstReferences;	
	
	public List<ReferencesReview> getLstReferences() 
	{
		return lstReferences;
	}
	
	public void setLstReferences(List<ReferencesReview> lstReferences) 
	{
		this.lstReferences = lstReferences;
	}

	public List<PersonAndPhones> getLstPersonPhone() 
	{
		return lstPersonPhone;
	}

	public void setLstPersonPhone(List<PersonAndPhones> lstPersonPhone) 
	{
		this.lstPersonPhone = lstPersonPhone;
	}	
	
	public List<Phone> getLstPhoneUpdate() 
	{
		return lstPhoneUpdate;
	}

	public void setLstPhoneUpdate(List<Phone> lstPhoneUpdate) 
	{
		this.lstPhoneUpdate = lstPhoneUpdate;
	}	
}
