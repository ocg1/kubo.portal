package mx.com.kubo.bean;

import java.io.Serializable;

import mx.com.kubo.model.Phone;

public abstract class PhoneReviewDMO 
implements Serializable
{
	private static final long serialVersionUID = 1828035986759545660L;
	
	private Phone phone;
	
	private String ladaPhone;
	private String numPhone;
	private String phone_label;
	
	private int index;
		
	private boolean hasPerson;
	private boolean hasReferences;
	private boolean panel_lada_ENABLED;
	private boolean panel_extension_ENABLED;
	
	public Phone getPhone() 
	{
		return phone;
	}
	
	public void setPhone(Phone phone) 
	{
		this.phone = phone;
	}
		
	public boolean isHasPerson() 
	{
		return hasPerson;
	}

	public void setHasPerson(boolean hasPerson) 
	{
		this.hasPerson = hasPerson;
	}

	public String getNumPhone() {
		return numPhone;
	}

	public void setNumPhone(String numPhone) {
		this.numPhone = numPhone;
	}

	public String getLadaPhone() {
		return ladaPhone;
	}

	public void setLadaPhone(String ladaPhone) {
		this.ladaPhone = ladaPhone;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isHasReferences() {
		return hasReferences;
	}

	public void setHasReferences(boolean hasReferences) {
		this.hasReferences = hasReferences;
	}

	public String getPhone_label() {
		return phone_label;
	}

	public void setPhone_label(String phone_label) {
		this.phone_label = phone_label;
	}

	public boolean isPanel_lada_ENABLED() {
		return panel_lada_ENABLED;
	}

	public void setPanel_lada_ENABLED(boolean panel_lada_ENABLED) {
		this.panel_lada_ENABLED = panel_lada_ENABLED;
	}

	public boolean isPanel_extension_ENABLED() {
		return panel_extension_ENABLED;
	}

	public void setPanel_extension_ENABLED(boolean panel_extension_ENABLED) {
		this.panel_extension_ENABLED = panel_extension_ENABLED;
	}
}
