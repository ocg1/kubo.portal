package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="findBmxActivity",
				query="SELECT a FROM BmxEconActivityCat a  WHERE a.description LIKE :description"
	),
	@NamedQuery(name="findBmxActivityById",
				query="SELECT a FROM BmxEconActivityCat a  WHERE a.bmxEconActivityCatPK.bmx_econ_activity_id =  :bmx_econ_activity_id  and a.bmxEconActivityCatPK.company_id = :company_id "
	),
}) 
@Entity
@Table(name="gn_bmx_econ_activity_cat")
public class BmxEconActivityCat implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private BmxEconActivityCatPK bmxEconActivityCatPK;
	@Column
	private String description;
	@Column
	private int inegi_activity_id;
	@Column
	private int mx_bc_number;
	@Column
	private int mx_cnbv_number;
	@Column
	private char gov_activity;
	@Column
	private char risk;
	@Column
	private char status;
	
	public BmxEconActivityCat(){
		
	}

	public BmxEconActivityCatPK getBmxEconActivityCatPK() {
		return bmxEconActivityCatPK;
	}

	public void setBmxEconActivityCatPK(BmxEconActivityCatPK bmxEconActivityCatPK) {
		this.bmxEconActivityCatPK = bmxEconActivityCatPK;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInegi_activity_id() {
		return inegi_activity_id;
	}

	public void setInegi_activity_id(int inegi_activity_id) {
		this.inegi_activity_id = inegi_activity_id;
	}

	public int getMx_bc_number() {
		return mx_bc_number;
	}

	public void setMx_bc_number(int mx_bc_number) {
		this.mx_bc_number = mx_bc_number;
	}

	public int getMx_cnbv_number() {
		return mx_cnbv_number;
	}

	public void setMx_cnbv_number(int mx_cnbv_number) {
		this.mx_cnbv_number = mx_cnbv_number;
	}

	public char getGov_activity() {
		return gov_activity;
	}

	public void setGov_activity(char gov_activity) {
		this.gov_activity = gov_activity;
	}

	public char getRisk() {
		return risk;
	}

	public void setRisk(char risk) {
		this.risk = risk;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
}
