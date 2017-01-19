package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pr_prospectus_extra")
public class ProspectusExtra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int extra_id;//` INT(10) NOT NULL AUTO_INCREMENT,
	@Column
	private Integer prospectus_id;//` INT(10) UNSIGNED NULL,
	@Column
	private String value1_ps;
	
	public int getExtra_id() {
		return extra_id;
	}
	public void setExtra_id(int extra_id) {
		this.extra_id = extra_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getValue1_ps() {
		return value1_ps;
	}
	public void setValue1_ps(String value1_ps) {
		this.value1_ps = value1_ps;
	}
	
	
	
}
