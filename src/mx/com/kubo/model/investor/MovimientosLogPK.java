package mx.com.kubo.model.investor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MovimientosLogPK 
implements Serializable
{
	private static final long serialVersionUID = 7653096674595181142L;

	@Column private String safi_account_id;
	
	@Column private Integer company_id;
	@Column private Integer prospectus_id;
	
	@Column(name = "cancellation_motive_id")
	private Integer motive_id;		
	
	@Column(name = "mov_notification_id") 
	private Integer log_id;

	public final Integer getCompany_id() {
		return company_id;
	}

	public final Integer getProspectus_id() {
		return prospectus_id;
	}

	public final Integer getMotive_id() {
		return motive_id;
	}

	public final String getSafi_account_id() {
		return safi_account_id;
	}
	
	public final Integer getLog_id() 
	{
		return log_id;
	}

	public final void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}

	public final void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public final void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public final void setMotive_id(Integer motive_id) {
		this.motive_id = motive_id;
	}

	public final void setSafi_account_id(String safi_account_id) {
		this.safi_account_id = safi_account_id;
	}
}
