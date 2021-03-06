package mx.com.kubo.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.kubo.model.catalogos.IdentificationType;

@Entity @Table(name = "gn_natural_person")
public class NaturalPerson 
implements Serializable 
{
	private static final long serialVersionUID = -3114637423053826772L;

	@EmbeddedId @AttributeOverride(name = "prospectus_id", column = @Column(name = "prospectus_id"))
	private gnNaturalPersonPK natPerPK;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false)
	}) private Prospectus prospectus;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "economic_activity_id", referencedColumnName = "economic_activity_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",           referencedColumnName = "company_id",           insertable = false, updatable = false)
	}) private Economic_Activity economic_activity;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "society_type_id", referencedColumnName = "society_type_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",      referencedColumnName = "company_id",      insertable = false, updatable = false)
	}) private SocietyType society_type;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "identification_type_id", referencedColumnName = "identification_type_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",             referencedColumnName = "company_id",      insertable = false, updatable = false)
	}) private IdentificationType identification_type;
	
	@Column private String first_name = "";      //	varchar(45)
	@Column private String middle_name = "";     //	varchar(45)
	@Column private String father_last_name = "";//	varchar(45)
	@Column private String mother_last_name = "";//	varchar(45)
	@Column private String short_name;           //	varchar(90)	
	@Column private String mx_rfc;               //	varchar(13)
	@Column private String mx_curp;              //	varchar(18)
	@Column private String mx_company_rfc;              
	@Column private String about_me;             //	varchar(2000)
	@Column private String mx_ife_cveelector;    //	varchar(2000)
	@Column private String mx_ife_numvertical;   //	varchar(2000)
	@Column private String mx_ife_seccion;	
	@Column private String mx_ine_cic;	
	@Column private String	safi_client_id;      	
	@Column private String study_area;
	@Column private String legal_name;
		
	@Column private Date date_of_birth;
	
	@Column private Double rate;//	varchar(2000)
		
	@Column private Integer local_phone_id;//	"int(10) unsigned"
	@Column private Integer mobile_phone_id;//	"int(10) unsigned"	
	@Column private Integer occupation_id;//	"int(10) unsigned"
	@Column private Integer state_id;         //	"smallint(5) unsigned"
	@Column private Integer gender_id;        //	"tinyint(3) unsigned"
	@Column private Integer study_level_id;   //	"tinyint(3) unsigned"
	@Column private Integer marital_status_id;//	"tinyint(3) unsigned"	
	
	@Column private Integer residence_id;         //	"tinyint(3) unsigned"	
	@Column private Integer legal_status_id;      //	"tinyint(3) unsigned"	
	@Column private Integer dependants_number_id; //	"tinyint(3) unsigned"
	@Column private Integer economic_activity_id; //	"tinyint(3) unsigned"
	@Column private Integer economic_dependent_id;//	"tinyint(3) unsigned"
	@Column private Integer identification_type_id = 0;
	
	@Column private Integer mx_ife_numemision;
	@Column private Integer citizenship;        //	"tinyint(3) unsigned"
	@Column private String 	 birth_place;
	
	
	@Column private Integer dependants_number;  //	"tinyint(3) unsigned"
	@Column private Integer length_of_residence;//	"tinyint(3) unsigned"
	
	@Column private Integer has_computer_home;//	"tinyint(3) unsigned"
	@Column private Integer has_internet_home;//	"tinyint(3) unsigned"
	@Column private Integer has_computer_employment;//	"tinyint(3) unsigned"
	@Column private Integer has_internet_employment;//	"tinyint(3) unsigned"
	@Column private Integer has_computer_business;//	"tinyint(3) unsigned"
	@Column private Integer has_internet_business;//	"tinyint(3) unsigned"
	@Column private Integer has_printer;//	"tinyint(3) unsigned"

	@Column private Integer country_id=700;//	"tinyint(3) unsigned"	
	@Column private Integer society_type_id;
	@Column private Integer sector_id=32;
	
	@Column private Character 	is_name_public;	
	@Column private Character 	employee_type; 
	@Column private Character 	is_age_valid;  
	@Column private Character 	is_coverage_zone;
	@Column private Integer 	country_of_residence;

	public NaturalPerson()
	{
		super();
	}
	
	public gnNaturalPersonPK getNatPerPK() 
	{
		return natPerPK;
	}
	
	public void setNatPerPK(gnNaturalPersonPK natPerPK) 
	{
		this.natPerPK = natPerPK;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		
		if( first_name != null ){
			first_name = first_name.trim(); 
		}
		
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		
		if( middle_name != null ){
			middle_name = middle_name.trim(); 
		}
		
		this.middle_name = middle_name;
	}
	public String getFather_last_name() {
		return father_last_name;
	}
	public void setFather_last_name(String father_last_name) {
		
		if( father_last_name != null ){
			father_last_name = father_last_name.trim(); 
		}
		
		this.father_last_name = father_last_name;
	}
	public String getMother_last_name() {
		return mother_last_name;
	}
	public void setMother_last_name(String mother_last_name) {
		
		if( mother_last_name != null ){
			mother_last_name = mother_last_name.trim(); 
		}
		
		this.mother_last_name = mother_last_name;
	}
	public Character getIs_name_public() {
		return is_name_public;
	}
	public void setIs_name_public(Character is_name_public) {
		this.is_name_public = is_name_public;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public Integer getGender_id() {
		return gender_id;
	}
	public void setGender_id(Integer gender_id) {
		this.gender_id = gender_id;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Integer getLocal_phone_id() {
		return local_phone_id;
	}
	public void setLocal_phone_id(Integer local_phone_id) {
		this.local_phone_id = local_phone_id;
	}
	public Integer getMobile_phone_id() {
		return mobile_phone_id;
	}
	public void setMobile_phone_id(Integer mobile_phone_id) {
		this.mobile_phone_id = mobile_phone_id;
	}
	public Integer getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(Integer citizenship) {
		this.citizenship = citizenship;
	}
	public Integer getState_id() {
		return state_id;
	}
	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}
	public String getMx_rfc() {
		return mx_rfc;
	}
	public void setMx_rfc(String mx_rfc) {
		this.mx_rfc = mx_rfc;
	}
	public String getMx_curp() {
		return mx_curp;
	}
	public void setMx_curp(String mx_curp) {
		this.mx_curp = mx_curp;
	}
	
	public String getMx_company_rfc() {
		return mx_company_rfc;
	}

	public void setMx_company_rfc(String mx_company_rfc) {
		this.mx_company_rfc = mx_company_rfc;
	}

	public Integer getStudy_level_id() {
		return study_level_id;
	}
	public void setStudy_level_id(Integer study_level_id) {
		this.study_level_id = study_level_id;
	}
	public Integer getMarital_status_id() {
		return marital_status_id;
	}
	public void setMarital_status_id(Integer marital_status_id) {
		this.marital_status_id = marital_status_id;
	}
	public Character getEmployee_type() {
		return employee_type;
	}
	public void setEmployee_type(Character employee_type) {
		this.employee_type = employee_type;
	}
		
	public final Character getIs_age_valid() 
	{
		return is_age_valid;
	}

	public final void setIs_age_valid(Character is_age_valid) 
	{
		this.is_age_valid = is_age_valid;
	}
	
	public final Character getIs_coverage_zone() 
	{
		return is_coverage_zone;
	}

	public final void setIs_coverage_zone(Character is_coverage_zone) 
	{
		this.is_coverage_zone = is_coverage_zone;
	}

	public Integer getOccupation_id() {
		return occupation_id;
	}
	public void setOccupation_id(Integer occupation_id) {
		this.occupation_id = occupation_id;
	}
	public Integer getResidence_id() {
		return residence_id;
	}
	public void setResidence_id(Integer residence_id) {
		this.residence_id = residence_id;
	}
	public Integer getLength_of_residence() {
		return length_of_residence;
	}
	public void setLength_of_residence(Integer length_of_residence) {
		this.length_of_residence = length_of_residence;
	}
	public Integer getDependants_number() {
		return dependants_number;
	}
	public void setDependants_number(Integer dependants_number) {
		this.dependants_number = dependants_number;
	}
	
	public Integer getDependants_number_id() {
		return dependants_number_id;
	}

	public void setDependants_number_id(Integer dependants_number_id) {
		this.dependants_number_id = dependants_number_id;
	}

	public Integer getLegal_status_id() {
		return legal_status_id;
	}
	public void setLegal_status_id(Integer legal_status_id) {
		this.legal_status_id = legal_status_id;
	}
	public Integer getHas_printer() {
		return has_printer;
	}
	public void setHas_printer(Integer has_printer) {
		this.has_printer = has_printer;
	}
	
	public Integer getEconomic_activity_id() 
	{
		return economic_activity_id;
	}
	
	public void setEconomic_activity_id(Integer economic_activity_id) 
	{
		this.economic_activity_id = economic_activity_id;
	}
	
	public Integer getEconomic_dependent_id() 
	{
		return economic_dependent_id;
	}
	
	public void setEconomic_dependent_id(Integer economic_dependent_id) 
	{
		this.economic_dependent_id = economic_dependent_id;
	}
	
	public Integer getIdentification_type_id() 
	{
		return identification_type_id;
	}

	public void setIdentification_type_id(Integer identification_type_id) 
	{
		this.identification_type_id = identification_type_id;
	}

	public Integer getCountry_id() 
	{
		return country_id;
	}
	
	public void setCountry_id(Integer country_id)
	{
		this.country_id = country_id;
	}
	
	public String getAbout_me() {
		return about_me;
	}
	public void setAbout_me(String about_me) {
		this.about_me = about_me;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getHas_computer_home() {
		return has_computer_home;
	}

	public void setHas_computer_home(Integer has_computer_home) {
		this.has_computer_home = has_computer_home;
	}

	public Integer getHas_internet_home() {
		return has_internet_home;
	}

	public void setHas_internet_home(Integer has_internet_home) {
		this.has_internet_home = has_internet_home;
	}

	public Integer getHas_computer_employment() {
		return has_computer_employment;
	}

	public void setHas_computer_employment(Integer has_computer_employment) {
		this.has_computer_employment = has_computer_employment;
	}

	public Integer getHas_internet_employment() {
		return has_internet_employment;
	}

	public void setHas_internet_employment(Integer has_internet_employment) {
		this.has_internet_employment = has_internet_employment;
	}

	public Integer getHas_computer_business() {
		return has_computer_business;
	}

	public void setHas_computer_business(Integer has_computer_business) {
		this.has_computer_business = has_computer_business;
	}

	public Integer getHas_internet_business() {
		return has_internet_business;
	}

	public void setHas_internet_business(Integer has_internet_business) {
		this.has_internet_business = has_internet_business;
	}

	public String getMx_ife_cveelector() {
		return mx_ife_cveelector;
	}

	public void setMx_ife_cveelector(String mx_ife_cveelector) {
		this.mx_ife_cveelector = mx_ife_cveelector;
	}

	public String getMx_ife_numvertical() {
		return mx_ife_numvertical;
	}

	public void setMx_ife_numvertical(String mx_ife_numvertical) {
		this.mx_ife_numvertical = mx_ife_numvertical;
	}

	public Integer getMx_ife_numemision() {
		return mx_ife_numemision;
	}

	public void setMx_ife_numemision(Integer mx_ife_numemision) {
		this.mx_ife_numemision = mx_ife_numemision;
	}

	public Integer getSociety_type_id() {
		return society_type_id;
	}

	public void setSociety_type_id(Integer society_type_id) {
		this.society_type_id = society_type_id;
	}

	public Integer getSector_id() {
		return sector_id;
	}

	public void setSector_id(Integer sector_id) {
		this.sector_id = sector_id;
	}

	public Prospectus getProspectus() {
		return prospectus;
	}

	public void setProspectus(Prospectus prospectus) {
		this.prospectus = prospectus;
	}
	
	public final Economic_Activity getEconomic_activity() {
		return economic_activity;
	}

	public final void setEconomic_activity(Economic_Activity economic_activity) {
		this.economic_activity = economic_activity;
	}

	public SocietyType getSociety_type() {
		return society_type;
	}

	public void setSociety_type(SocietyType society_type) {
		this.society_type = society_type;
	}
	
	public void setIdentification_type(IdentificationType identification_type)
	{
		this.identification_type = identification_type;
	}
	
	public IdentificationType getIdentification_type()
	{
		return identification_type;
	}

	public String getSafi_client_id() {
		return safi_client_id;
	}

	public void setSafi_client_id(String safi_client_id) 
	{
		this.safi_client_id = safi_client_id;
	}

	public String getMx_ife_seccion() 
	{
		return mx_ife_seccion;
	}

	public void setMx_ife_seccion(String mx_ife_seccion) 
	{
		this.mx_ife_seccion = mx_ife_seccion;
	}
	
	public String getMx_ine_cic() 
	{
		return mx_ine_cic;
	}

	public void setMx_ine_cic(String mx_ine_cic) 
	{
		this.mx_ine_cic = mx_ine_cic;
	}

	public String NombreCompletoNPM()
	{
		String nombre = "";
		
		if(first_name != null && first_name.trim().length() > 0)
		{
			nombre += first_name;
		}
		
		if(middle_name != null && middle_name.trim().length() > 0)
		{
			nombre += " "+middle_name;
		}
		
		if(father_last_name != null && father_last_name.trim().length() > 0)
		{
			nombre += " " + father_last_name;
		}
		
		if(mother_last_name != null && mother_last_name.trim().length() > 0)
		{
			nombre += " " + mother_last_name;
		}
		
		return nombre;
	}
	
	public String NombreCompletoPMN()
	{
		String nombre ="";
				
		if(father_last_name != null && father_last_name.trim().length() > 0)
		{
			nombre += " " + father_last_name;
		}
		
		if(mother_last_name != null && mother_last_name.trim().length() > 0)
		{
			nombre += " " + mother_last_name;
		}
		
		if(first_name != null && first_name.trim().length() > 0)
		{
			nombre += first_name;
		}
		
		if(middle_name != null && middle_name.trim().length() > 0)
		{
			nombre += " " + middle_name;
		}
		
		return nombre;
	}
	
	public String getStudy_area() 
	{
		return study_area;
	}


	public void setStudy_area(String study_area) 
	{
		this.study_area = study_area;
	}

	public String getLegal_name() 
	{
		return legal_name;
	}

	public void setLegal_name(String legal_name) 
	{
		this.legal_name = legal_name;
	}

	public String getValueof(String field)
	{
		if(field.equals("first_name"))
		{
			return this.first_name;
			
		 }else if(field.equals("middle_name")){
			return this.middle_name;
		} else return null;
	}
	
	public Integer getEdad()
	{
		try 
		{
			if(this.date_of_birth != null)
			{
				Calendar birth = new GregorianCalendar();
				Calendar today = new GregorianCalendar();
				int age=0;
				int factor=0;
				//Date birthDate=new SimpleDateFormat("dd-MM-yyyy").parse(datetext);
				Date birthDate= this.date_of_birth;
				Date currentDate=new Date(); //current date
				birth.setTime(birthDate);
				today.setTime(currentDate);
				if (today.get(Calendar.MONTH) <= birth.get(Calendar.MONTH)) 
				{
					if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) 
					{
						if (today.get(Calendar.DATE) > birth.get(Calendar.DATE)) 
						{
							factor = -1; //Aun no celebra su cumpleaños
						}
						
					} else {
						factor = -1; //Aun no celebra su cumpleaños
					}
				}
				age=(today.get(Calendar.YEAR)-birth.get(Calendar.YEAR))+factor;
				return age;
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getBirthDayStr()
	{		
		if(date_of_birth != null)
		{
			SimpleDateFormat frmt =  new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es","ES"));
			
			return frmt.format(date_of_birth);
		} else {
			
			return null;
		}
		
	}

	public Integer getCountry_of_residence() {
		return country_of_residence;
	}

	public void setCountry_of_residence(Integer country_of_residence) {
		this.country_of_residence = country_of_residence;
	}

	public String getBirth_place() {
		return birth_place;
	}

	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

}


