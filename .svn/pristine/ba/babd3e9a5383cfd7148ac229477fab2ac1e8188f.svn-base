package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity @Table(name = "ln_membership")
public class Membership 
implements Serializable
{
	private static final long serialVersionUID = -921855710962244210L;

	@EmbeddedId
	@AttributeOverride(name = "prospectus_id", column = @Column(name  ="prospectus_id"))
	private MembershipPK membershipPK;
	
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "security_question_id", referencedColumnName = "security_question_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	}) private SecurityQuestion securityQuestion;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false)
	}) private NaturalPerson person;
	
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "fromwhere_id", referencedColumnName = "fromwhere_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",   referencedColumnName = "company_id",   insertable = false, updatable = false)
	}) private FromWhereCat fromWhereCat;
	
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "who_answered_id", referencedColumnName = "who_answered_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",      referencedColumnName = "company_id",      insertable = false, updatable = false)
	}) private WhoAnsweredCat whoAnswerCat;
	
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "registration_reason_id", referencedColumnName = "registration_reason_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",             referencedColumnName = "company_id",             insertable = false, updatable = false)
	}) private RegistrationReason registration_reason;
	
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "promotor_id", referencedColumnName = "promotor_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id",   insertable = false, updatable = false)
	}) private Promotor promotor;
	

	@Column private Date last_login_attempt;
	@Column private Date last_FB_login_attempt;
	@Column private Date creation_date;	
	@Column private Date activation_date;
	@Column private Date date_canceled;
	@Column private Date file_creation_date;
			
	@Column private String activation_code;	
	@Column private String accept_subscribe;
	@Column private String other_registration_reason;			
	@Column private String fromwhere_other;
	@Column private String who_answered_other;
	@Column private String priceshoes_number;	
	@Column private String who_recommends;
	@Column private String origin;	
	@Column private String url_origin;
	@Column private String email_verified;
	@Column private String nickname;	
	@Column private String email;	
	@Column private String password;			
	@Column private String is_client_pass;
	@Column private String answer;	
	@Column private String contract;
	@Column private String is_canceled = "N";	
	@Column private String is_duplicated;
	@Column private String is_blocked;
	@Column private String is_pld_blocked;
	@Column private String is_stackholder;	
	
	@Column private Integer is_active;		
	@Column private Integer who_answered_id;
	@Column private Integer promotor_id;
	@Column private Integer fromwhere_id;
	@Column private Integer registration_reason_id;
	@Column private Integer notification_preference_id = 2; 
	@Column private Integer security_question_id;	
	@Column private Integer failed_login_attempts    = 0;
	@Column private Integer failed_token_attempts    = 0;	
	@Column private Integer failed_question_attempts = 0;
	@Column private String 	 fb_id;
	@Column private String 	 utm_medium;
	

	
	
	public MembershipPK getMembershipPK() {
		return membershipPK;
	}
	
	public void setMembershipPK(MembershipPK membershipPK) {
		this.membershipPK = membershipPK;
	}
	
	public SecurityQuestion getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(SecurityQuestion securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public NaturalPerson getPerson() {
		return person;
	}
	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
	public FromWhereCat getFromWhereCat() {
		return fromWhereCat;
	}
	public void setFromWhereCat(FromWhereCat fromWhereCat) {
		this.fromWhereCat = fromWhereCat;
	}
	public WhoAnsweredCat getWhoAnswerCat() {
		return whoAnswerCat;
	}
	public void setWhoAnswerCat(WhoAnsweredCat whoAnswerCat) {
		this.whoAnswerCat = whoAnswerCat;
	}
	public RegistrationReason getRegistration_reason() {
		return registration_reason;
	}
	public void setRegistration_reason(RegistrationReason registration_reason) {
		this.registration_reason = registration_reason;
	}
	public Promotor getPromotor() {
		return promotor;
	}
	public void setPromotor(Promotor promotor) {
		this.promotor = promotor;
	}
	public Date getLast_login_attempt() {
		return last_login_attempt;
	}
	public void setLast_login_attempt(Date last_login_attempt) {
		this.last_login_attempt = last_login_attempt;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public Date getActivation_date() {
		return activation_date;
	}
	public void setActivation_date(Date activation_date) {
		this.activation_date = activation_date;
	}
	public Date getDate_canceled() {
		return date_canceled;
	}
	public void setDate_canceled(Date date_canceled) {
		this.date_canceled = date_canceled;
	}
	public Date getFile_creation_date() {
		return file_creation_date;
	}
	public void setFile_creation_date(Date file_creation_date) {
		this.file_creation_date = file_creation_date;
	}
	public String getActivation_code() {
		return activation_code;
	}
	public void setActivation_code(String activation_code) {
		this.activation_code = activation_code;
	}
	public String getAccept_subscribe() {
		return accept_subscribe;
	}
	public void setAccept_subscribe(String accept_subscribe) {
		this.accept_subscribe = accept_subscribe;
	}
	public String getOther_registration_reason() {
		return other_registration_reason;
	}
	public void setOther_registration_reason(String other_registration_reason) {
		this.other_registration_reason = other_registration_reason;
	}
	public String getFromwhere_other() {
		return fromwhere_other;
	}
	public void setFromwhere_other(String fromwhere_other) {
		this.fromwhere_other = fromwhere_other;
	}
	public String getWho_answered_other() {
		return who_answered_other;
	}
	public void setWho_answered_other(String who_answered_other) {
		this.who_answered_other = who_answered_other;
	}
	public String getPriceshoes_number() {
		return priceshoes_number;
	}
	public void setPriceshoes_number(String priceshoes_number) {
		this.priceshoes_number = priceshoes_number;
	}
	public String getWho_recommends() {
		return who_recommends;
	}
	public void setWho_recommends(String who_recommends) {
		this.who_recommends = who_recommends;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getUrl_origin() {
		return url_origin;
	}
	public void setUrl_origin(String url_origin) {
		this.url_origin = url_origin;
	}
	public String getEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(String email_verified) {
		this.email_verified = email_verified;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getIs_canceled() {
		return is_canceled;
	}
	public void setIs_canceled(String is_canceled) {
		this.is_canceled = is_canceled;
	}
	public String getIs_duplicated() {
		return is_duplicated;
	}
	public void setIs_duplicated(String is_duplicated) {
		this.is_duplicated = is_duplicated;
	}
	public String getIs_blocked() {
		return is_blocked;
	}
	public void setIs_blocked(String is_blocked) {
		this.is_blocked = is_blocked;
	}
	
	public String getIs_pld_blocked() {
		return is_pld_blocked;
	}

	public void setIs_pld_blocked(String is_pld_blocked) {
		this.is_pld_blocked = is_pld_blocked;
	}

	public String getIs_stackholder() {
		return is_stackholder;
	}
	public void setIs_stackholder(String is_stackholder) {
		this.is_stackholder = is_stackholder;
	}
	public Integer getIs_active() {
		return is_active;
	}
	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}
	public Integer getWho_answered_id() {
		return who_answered_id;
	}
	public void setWho_answered_id(Integer who_answered_id) {
		this.who_answered_id = who_answered_id;
	}
	public Integer getPromotor_id() {
		return promotor_id;
	}
	public void setPromotor_id(Integer promotor_id) {
		this.promotor_id = promotor_id;
	}
	public Integer getFromwhere_id() {
		return fromwhere_id;
	}
	public void setFromwhere_id(Integer fromwhere_id) {
		this.fromwhere_id = fromwhere_id;
	}
	
	public Integer getRegistration_reason_id() 
	{
		return registration_reason_id;
	}
	
	public void setRegistration_reason_id(Integer registration_reason_id) {
		this.registration_reason_id = registration_reason_id;
	}
	
	public Integer getNotification_preference_id() {
		return notification_preference_id;
	}

	public void setNotification_preference_id(Integer notification_preference_id) {
		this.notification_preference_id = notification_preference_id;
	}

	public Integer getSecurity_question_id() {
		return security_question_id;
	}
	public void setSecurity_question_id(Integer security_question_id) {
		this.security_question_id = security_question_id;
	}
	public Integer getFailed_login_attempts() {
		return failed_login_attempts;
	}
	public void setFailed_login_attempts(Integer failed_login_attempts) {
		this.failed_login_attempts = failed_login_attempts;
	}
	public Integer getFailed_token_attempts() {
		return failed_token_attempts;
	}
	public void setFailed_token_attempts(Integer failed_token_attempts) {
		this.failed_token_attempts = failed_token_attempts;
	}
	public Integer getFailed_question_attempts() {
		return failed_question_attempts;
	}
	public void setFailed_question_attempts(Integer failed_question_attempts) {
		this.failed_question_attempts = failed_question_attempts;
	}

	public String getIs_client_pass() {
		return is_client_pass;
	}

	public void setIs_client_pass(String is_client_pass) {
		this.is_client_pass = is_client_pass;
	}

	public String getFb_id() {
		return fb_id;
	}

	public void setFb_id(String fb_id) {
		this.fb_id = fb_id;
	}

	public Date getLast_FB_login_attempt() {
		return last_FB_login_attempt;
	}

	public void setLast_FB_login_attempt(Date last_FB_login_attempt) {
		this.last_FB_login_attempt = last_FB_login_attempt;
	}

	public String getUtm_medium() {
		return utm_medium;
	}

	public void setUtm_medium(String utm_medium) {
		this.utm_medium = utm_medium;
	}
	
	

}
