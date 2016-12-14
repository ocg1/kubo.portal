package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_prospectus_comment")
public class PospectusComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PospectusCommentPK pk;
	@Column
	private Integer prospectus_id_to;
	@Column
	private String comment;
	@Column
	private Date comment_date;
	@Column
	private Integer proyect_loan_id;
	
	public PospectusCommentPK getPk() {
		return pk;
	}
	public void setPk(PospectusCommentPK pk) {
		this.pk = pk;
	}
	public Integer getProspectus_id_to() {
		return prospectus_id_to;
	}
	public void setProspectus_id_to(Integer prospectus_id_to) {
		this.prospectus_id_to = prospectus_id_to;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	} 
	
}
