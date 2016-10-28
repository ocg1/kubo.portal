package mx.com.kubo.portal.ofertas;

import java.io.Serializable;

public final class OfertaBean implements Serializable
{	
	private static final long serialVersionUID = -6319860143544740569L;
	
	private String ammount;
	private String payment;
			
	public String getAmmount() 
	{
		return ammount;
	}
	
	public void setAmmount(String ammount) 
	{
		this.ammount = ammount;
	}
	
	public String getPayment() 
	{
		return payment;
	}
	
	public void setPayment(String payment)
	{
		this.payment = payment;
	}
}
