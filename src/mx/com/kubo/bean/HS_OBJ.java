package mx.com.kubo.bean;

public class HS_OBJ {

	private Long v_id ;
	private String firstname_value;
	private String second_name;
	private String last_name;
	private String last_name2;
	private String area;
	private String mobil_value ;
	private String email_value ;
	private String url_value;
	private String url_medium;
	private String url_campaign;
	private Integer registration_reason_id;
	private Integer prospectus_id;
	

	public Integer getRegistration_reason_id() {
		return registration_reason_id;
	}

	public void setRegistration_reason_id(Integer registration_reason_id) {
		this.registration_reason_id = registration_reason_id;
	}

	public Integer getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public Long getV_id() {
		return v_id;
	}

	public void setV_id(Long v_id) {
		this.v_id = v_id;
	}

	public String getFirstname_value() {
		return firstname_value;
	}

	public void setFirstname_value(String firstname_value) {
		this.firstname_value = firstname_value;
	}

	public String getMobil_value() {
		return mobil_value;
	}

	public void setMobil_value(String mobil_value) {
		this.mobil_value = mobil_value;
	}

	public String getEmail_value() {
		return email_value;
	}

	public void setEmail_value(String email_value) {
		this.email_value = email_value;
	}

	public String getUrl_value() {
		return url_value;
	}

	public void setUrl_value(String url_value) {
		this.url_value = url_value;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLast_name2() {
		return last_name2;
	}

	public void setLast_name2(String last_name2) {
		this.last_name2 = last_name2;
	}

	public String getUrl_medium() {
		return url_medium;
	}

	public void setUrl_medium(String url_medium) {
		this.url_medium = url_medium;
	}

	public String getUrl_campaign() {
		return url_campaign;
	}

	public void setUrl_campaign(String url_campaign) {
		this.url_campaign = url_campaign;
	}
	
}
