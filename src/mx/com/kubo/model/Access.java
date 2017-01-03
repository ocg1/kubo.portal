package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="pr_access")
public class Access 
implements Serializable 
{
	private static final long serialVersionUID = 6229038881127317724L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer access_id;//	"int(10) unsigned"
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date access_datetime;
	
	@Column private Integer screen_id;//	"int(10) unsigned"	
	@Column private Integer company_id;//	"tinyint(3) unsigned"	
	@Column private Integer prospectus_id;//"int(10) unsigned"	
	@Column private Integer prospectus_id_viewed;//"int(10) unsigned"
		
	@Column private Integer percentage=0;//"int(10) unsigned"	
	@Column private Integer horizontal_size;
	@Column private Integer vertical_size;
	@Column private Integer prospectus_id_coach;
	
	@Column private String web_browser;
	@Column private String op_system;
	@Column private String web_browser_version;
	@Column private String access_from;
	@Column private String user_agent;
	@Column private String device_info;
	@Column private String ipaddress;
	@Column private String version_description;
	@Column private String url_access;
	
	public String getAccess_from() {
		return access_from;
	}

	public void setAccess_from(String access_from) {
		this.access_from = access_from;
	}

	public Integer getAccess_id() {
		return access_id;
	}
	
	public void setAccess_id(Integer access_id) {
		this.access_id = access_id;
	}
	
	public Integer getScreen_id() {
		return screen_id;
	}
	
	public void setScreen_id(Integer screen_id) {
		this.screen_id = screen_id;
	}
	
	public Integer getCompany_id() {
		return company_id;
	}
	
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	
	public Date getAccess_datetime() {
		return access_datetime;
	}
	
	public void setAccess_datetime(Date access_datetime) {
		this.access_datetime = access_datetime;
	}
	
	public Integer getPercentage() {
		return percentage;
	}
	
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
	
	public String getWeb_browser() {
		return web_browser;
	}
	
	public void setWeb_browser(String web_browser) {
		this.web_browser = web_browser;
	}
	
	public String getOp_system() {
		return op_system;
	}
	
	public void setOp_system(String op_system) {
		this.op_system = op_system;
	}
	
	public String getWeb_browser_version() {
		return web_browser_version;
	}
	
	public void setWeb_browser_version(String web_browser_version) {
		this.web_browser_version = web_browser_version;
	}
	
	public Integer getProspectus_id_viewed() {
		return prospectus_id_viewed;
	}
	
	public void setProspectus_id_viewed(Integer prospectus_id_viewed) 
	{
		this.prospectus_id_viewed = prospectus_id_viewed;
	}
	
	public final void setHorizontal_size(Integer horizontal_size) 
	{
		this.horizontal_size = horizontal_size;
	}
	
	public final void setVertical_size(Integer vertical_size) 
	{
		this.vertical_size = vertical_size;
	}
	
	public final void setIpaddress(String ipaddress) 
	{
		this.ipaddress = ipaddress;
	}
	
	public final Integer getHorizontal_size() 
	{
		return horizontal_size;
	}
	
	public final Integer getVertical_size() 
	{
		return vertical_size;
	}
	
	public final String getIpaddress() 
	{
		return ipaddress;
	}

	public Integer getProspectus_id_coach() {
		return prospectus_id_coach;
	}

	public void setProspectus_id_coach(Integer prospectus_id_coach) {
		this.prospectus_id_coach = prospectus_id_coach;
	}

	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getVersion_description() {
		return version_description;
	}

	public void setVersion_description(String version_description) {
		this.version_description = version_description;
	}

	public String getUrl_access() {
		return url_access;
	}

	public void setUrl_access(String url_access) {
		this.url_access = url_access;
	}
	
	
}

