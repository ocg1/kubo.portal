package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "in_group_member")
public class GroupMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	  private GroupMemberPK pk;
	@Column
	  private String is_creator; // ` VARCHAR(1) NULL,
	@Column
	  private Date added_date; //` DATETIME NULL,
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "investor_group_id", referencedColumnName = "investor_group_id", insertable = false, updatable = false)
	}) private InvestorGroup investorgroup;
	
	
	public GroupMemberPK getPk() {
		return pk;
	}
	public void setPk(GroupMemberPK pk) {
		this.pk = pk;
	}
	public String getIs_creator() {
		return is_creator;
	}
	public void setIs_creator(String is_creator) {
		this.is_creator = is_creator;
	}
	public Date getAdded_date() {
		return added_date;
	}
	public void setAdded_date(Date added_date) {
		this.added_date = added_date;
	}
	public InvestorGroup getInvestorgroup() {
		return investorgroup;
	}
	public void setInvestorgroup(InvestorGroup investorgroup) {
		this.investorgroup = investorgroup;
	}
	
}
