package mx.com.kubo.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginMembership 
{    
	private String area;
    private String prospectus_id;
    private String full_name;
    private String last_login_attempt;
    
    private boolean password_ENABLED;
    
    public LoginMembership(){}

    public String getProspectus_id() {
        return prospectus_id;
    }

    public void setProspectus_id(String prospectus_id) {
        this.prospectus_id = prospectus_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

	public String getLast_login_attempt() {
		return last_login_attempt;
	}

	public void setLast_login_attempt(String last_login_attempt) {
		this.last_login_attempt = last_login_attempt;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean isPassword_ENABLED() {
		return password_ENABLED;
	}

	public void setPassword_ENABLED(boolean password_ENABLED) {
		this.password_ENABLED = password_ENABLED;
	}		
}
