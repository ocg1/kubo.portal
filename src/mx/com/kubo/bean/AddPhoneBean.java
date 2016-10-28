package mx.com.kubo.bean;

public class AddPhoneBean {
	
	private Integer phone_type_id;
	private String ladaPhone;
	private String numPhone;
	private String extension;
	private String comments;
		
	public Integer getPhone_type_id() {
		return phone_type_id;
	}
	public void setPhone_type_id(Integer phone_type_id) {
		this.phone_type_id = phone_type_id;
	}
	public String getLadaPhone() {
		return ladaPhone;
	}
	public void setLadaPhone(String ladaPhone) {
		this.ladaPhone = ladaPhone;
	}
	public String getNumPhone() {
		return numPhone;
	}
	public void setNumPhone(String numPhone) {
		this.numPhone = numPhone;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
