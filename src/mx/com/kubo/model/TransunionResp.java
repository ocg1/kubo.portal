package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ln_transunion")
public class TransunionResp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@EmbeddedId
	private TransunionRespPK pk;
	
	@Column
	private Integer transunion_bc_score; 	// 	int(10) unsigned
	@Column
	private String  transunion_mod1; 		//	varchar(10)
	@Column
	private String  transunion_mod2; 		//	varchar(10)
	@Column
	private String  mx_rechazomop; 			//	varchar(1)
	@Column
	private String  mx_rechazoobs; 			//	varchar(1)
	@Column
	private String  mx_rechazomop24; 		//	varchar(1)
	@Column
	private String  mx_rechazofpd; 			//	varchar(1)
	@Column
	private String  mx_motivo_ajuste; 		//	varchar(100)
	@Column
	private String  mx_motivo_ajuste_2; 		//	varchar(100)
	@Column
	private Date 	 creation_date; 			//	datetime
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "mx_motivo_ajuste", referencedColumnName = "mx_motivo_ajuste", insertable = false, updatable = false)
	})
	private TransunionAjuste1 ajuste1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "mx_motivo_ajuste_2", referencedColumnName = "mx_motivo_ajuste_2", insertable = false, updatable = false)
	})
	private TransunionAjuste2 ajuste2;
	
	
	public Integer getTransunion_bc_score() {
		return transunion_bc_score;
	}
	public void setTransunion_bc_score(Integer transunion_bc_score) {
		this.transunion_bc_score = transunion_bc_score;
	}
	public String getTransunion_mod1() {
		return transunion_mod1;
	}
	public void setTransunion_mod1(String transunion_mod1) {
		this.transunion_mod1 = transunion_mod1;
	}
	public String getTransunion_mod2() {
		return transunion_mod2;
	}
	public void setTransunion_mod2(String transunion_mod2) {
		this.transunion_mod2 = transunion_mod2;
	}
	public String getMx_rechazomop() {
		return mx_rechazomop;
	}
	public void setMx_rechazomop(String mx_rechazomop) {
		this.mx_rechazomop = mx_rechazomop;
	}
	public String getMx_rechazoobs() {
		return mx_rechazoobs;
	}
	public void setMx_rechazoobs(String mx_rechazoobs) {
		this.mx_rechazoobs = mx_rechazoobs;
	}
	public String getMx_rechazomop24() {
		return mx_rechazomop24;
	}
	public void setMx_rechazomop24(String mx_rechazomop24) {
		this.mx_rechazomop24 = mx_rechazomop24;
	}
	public String getMx_rechazofpd() {
		return mx_rechazofpd;
	}
	public void setMx_rechazofpd(String mx_rechazofpd) {
		this.mx_rechazofpd = mx_rechazofpd;
	}
	public String getMx_motivo_ajuste() {
		return mx_motivo_ajuste;
	}
	public void setMx_motivo_ajuste(String mx_motivo_ajuste) {
		this.mx_motivo_ajuste = mx_motivo_ajuste;
	}
	public String getMx_motivo_ajuste_2() {
		return mx_motivo_ajuste_2;
	}
	public void setMx_motivo_ajuste_2(String mx_motivo_ajuste_2) {
		this.mx_motivo_ajuste_2 = mx_motivo_ajuste_2;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public TransunionRespPK getPk() {
		return pk;
	}
	public void setPk(TransunionRespPK pk) {
		this.pk = pk;
	}
	public TransunionAjuste1 getAjuste1() {
		return ajuste1;
	}
	public void setAjuste1(TransunionAjuste1 ajuste1) {
		this.ajuste1 = ajuste1;
	}
	public TransunionAjuste2 getAjuste2() {
		return ajuste2;
	}
	public void setAjuste2(TransunionAjuste2 ajuste2) {
		this.ajuste2 = ajuste2;
	}

	
}
