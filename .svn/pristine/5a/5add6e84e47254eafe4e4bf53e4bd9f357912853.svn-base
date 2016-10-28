package mx.com.kubo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ln_collection_company")
public class CollectionCompany 
implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CollectionCompanyPK pk;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	@Column
	private String phone1;
	
	@Column
	private String phone2;
	
	@Column
	private String logo;
	
	@Column
	private String mx_rfc;
	
	@Column
	private String email;
	
	@Column
	private String collector_name_1;
	
	@Column
	private String collector_name_2;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumns(value = {
	        @JoinColumn(name = "collection_company_id", referencedColumnName = "collection_company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",            referencedColumnName = "company_id",            insertable = false, updatable = false)
	    })
	private List<CollectionStackholder> lstStackholder;
	
	public CollectionCompanyPK getPk() {
		return pk;
	}
	
	public void setPk(CollectionCompanyPK pk) {
		this.pk = pk;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone1() {
		return phone1;
	}
	
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	
	public String getPhone2() {
		return phone2;
	}
	
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public List<CollectionStackholder> getLstStackholder() {
		return lstStackholder;
	}
	
	public void setLstStackholder(List<CollectionStackholder> lstStackholder) {
		this.lstStackholder = lstStackholder;
	}

	public String getMx_rfc() 
	{
		return mx_rfc;
	}

	public void setMx_rfc(String mx_rfc) 
	{
		this.mx_rfc = mx_rfc;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getCollector_name_1() 
	{
		return collector_name_1;
	}

	public void setCollector_name_1(String collector_name_1) 
	{
		this.collector_name_1 = collector_name_1;
	}

	public String getCollector_name_2() 
	{
		return collector_name_2;
	}

	public void setCollector_name_2(String collector_name_2) 
	{
		this.collector_name_2 = collector_name_2;
	}		
}
