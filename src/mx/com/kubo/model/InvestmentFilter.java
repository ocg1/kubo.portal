package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="in_user_filter")
public class InvestmentFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private InvestmentFilterPK pk;
	@Column
	private Date filter_date_used;
	@Column
	private String filter;
	
	public InvestmentFilterPK getPk() {
		return pk;
	}
	public void setPk(InvestmentFilterPK pk) {
		this.pk = pk;
	}
	public Date getFilter_date_used() {
		return filter_date_used;
	}
	public void setFilter_date_used(Date filter_date_used) {
		this.filter_date_used = filter_date_used;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
}
