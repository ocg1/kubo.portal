package mx.com.kubo.bean;

import java.util.Date;

import mx.com.kubo.model.Files;

public class FileForScreenBean {

	private int file_id;
	private int prospectus_id;
	private int company_id;
	private int file_type_id;
	private int proyect_loan_id;
	private String name_category;
	private int file_category_id;
	private String uploaded_text;
	private String abreviation;
	private Date upload_date;
	private Date approval_date;
	private String location;	
	private String location_thump;
	private String approved;
	private Integer responsible;	
	private String metadata;
	
	public FileForScreenBean( Files file ){
		
		this.file_id = file.getFilesPk().getFile_id();
		this.prospectus_id  = file.getFilesPk().getProspectus_id();
		this.company_id = file.getFilesPk().getCompany_id();
		this.file_type_id = file.getFilesPk().getFile_type_id();
		this.proyect_loan_id = file.getFilesPk().getProyect_loan_id();
		this.name_category  = file.getFileType().getName();
		this.file_category_id = file.getFileType().getFile_category_id();
		this.uploaded_text = file.getFileType().getUploaded_text();
		this.abreviation = file.getFileType().getAbreviation();
		this.upload_date = file.getUpload_date();
		this.approval_date = file.getApproval_date();
		this.location = file.getLocation();	
		this.approved = file.getApproved();
		this.responsible = file.getResponsible();	
		this.metadata = file.getMetadata();
		
	}
	
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getFile_type_id() {
		return file_type_id;
	}
	public void setFile_type_id(int file_type_id) {
		this.file_type_id = file_type_id;
	}
	public int getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(int proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
	public String getName_category() {
		return name_category;
	}
	public void setName_category(String name_category) {
		this.name_category = name_category;
	}
	public int getFile_category_id() {
		return file_category_id;
	}
	public void setFile_category_id(int file_category_id) {
		this.file_category_id = file_category_id;
	}
	public String getUploaded_text() {
		return uploaded_text;
	}
	public void setUploaded_text(String uploaded_text) {
		this.uploaded_text = uploaded_text;
	}
	public String getAbreviation() {
		return abreviation;
	}
	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public Date getApproval_date() {
		return approval_date;
	}
	public void setApproval_date(Date approval_date) {
		this.approval_date = approval_date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public Integer getResponsible() {
		return responsible;
	}
	public void setResponsible(Integer responsible) {
		this.responsible = responsible;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public String getLocation_thump() {
		return location_thump;
	}

	public void setLocation_thump(String location_thump) {
		this.location_thump = location_thump;
	}
	
}
