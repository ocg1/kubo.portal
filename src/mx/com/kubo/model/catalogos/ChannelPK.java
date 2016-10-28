package mx.com.kubo.model.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChannelPK 
implements Serializable
{
	private static final long serialVersionUID = 1090403137093114646L;

	@Column private String channel_id;
	
	@Column private int company_id;
	
	public int getCompany_id() 
	{
		return company_id;
	}
	
	public String getChannel_id() 
	{
		return channel_id;
	}
	
	public void setChannel_id(String channel_id) 
	{
		this.channel_id = channel_id;
	}

	public void setCompany_id(int company_id) 
	{
		this.company_id = company_id;
	}
}
