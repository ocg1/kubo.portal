package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQuery(
		name  = "queryAddFile",
		query = "select MAX(f.filesPk.file_id) from Files f where f.filesPk.prospectus_id=? and f.filesPk.company_id=?"
)

@Entity @Table(name = "gn_file")
public class Files 
implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FilesPK filesPk;
	
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "file_type_id", referencedColumnName = "file_type_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",   referencedColumnName = "company_id",   insertable = false, updatable = false)
	    })	
	private FileType fileType;
	
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "reca_id", referencedColumnName = "reca_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private Reca reca;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date upload_date;
	
	@Column
	private Date validity_date;
	
	@Column private Date approval_date;
	
	@Column private String location;	
	@Column private String approved;
	@Column private Integer responsible;	
	@Column private String metadata;
			
	@Column private Integer reca_id;

	public FilesPK getFilesPk() 
	{
		return filesPk;
	}

	public String getLocation() {
		return location;
	}

	public Date getUpload_date() {
		return upload_date;
	}

	public String getApproved() {
		return approved;
	}

	public Date getApproval_date() {
		return approval_date;
	}

	public Integer getResponsible() {
		return responsible;
	}

	public void setFilesPk(FilesPK filesPk) {
		this.filesPk = filesPk;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public void setApproval_date(Date approval_date) {
		this.approval_date = approval_date;
	}

	public void setResponsible(Integer responsible) {
		this.responsible = responsible;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public Integer getReca_id() {
		return reca_id;
	}

	public void setReca_id(Integer reca_id) {
		this.reca_id = reca_id;
	}

	public Reca getReca() {
		return reca;
	}

	public void setReca(Reca reca) {
		this.reca = reca;
	}

	public Date getValidity_date() {
		return validity_date;
	}

	public void setValidity_date(Date validity_date) {
		this.validity_date = validity_date;
	}
}
