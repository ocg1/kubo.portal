package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity @Table(name = "gn_scoring_result")
public class Scoring implements Serializable 
{
	private static final long serialVersionUID = -3558943580777445433L;

	@Id
	@Column
	private int scoring_result_id;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false),
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false)
	})	private NaturalPerson person;
	
	@Column private String bc_score;
	@Column private String mx_folio;
	@Column private String mx_solicitud_buro;
	@Column private String  kubo_score_a;
	@Column private String  kubo_score_b;
	@Column private String  cci_score;
	@Column private String kubo_rate;
	@Column private String risk_level;
	@Column private String is_prospector = "N";	
	@Column private String efl_test = "0";	
	@Column private String r_data;
	@Column private String is_consulting_for_renovation = "N";	
	
	@Column private Date result_datetime;	
	@Column private Date bc_score_date;

	@Column private Double  rate;	
	@Column private Double  rate_investor;	
	@Column private Double opening_commission;
	@Column private Double liquidity;

	@Column private Integer company_id;
	@Column private Integer prospectus_id;
	@Column private Integer status = 0;	
	@Column private Integer risk_processed = 0;	
	@Column private Integer screen_viewed = 0;
	
	public int getScoring_result_id() {
		return scoring_result_id;
	}
	public void setScoring_result_id(int scoring_result_id) {
		this.scoring_result_id = scoring_result_id;
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
	public Date getResult_datetime() {
		return result_datetime;
	}
	public void setResult_datetime(Date result_datetime) {
		this.result_datetime = result_datetime;
	}
	public String getKubo_score_a() {
		return kubo_score_a;
	}
	public void setKubo_score_a(String kubo_score_a) {
		this.kubo_score_a = kubo_score_a;
	}
	public String getKubo_score_b() {
		return kubo_score_b;
	}
	public String getR_data() {
		return r_data;
	}
	public void setR_data(String r_data) {
		this.r_data = r_data;
	}
	public void setKubo_score_b(String kubo_score_b) {
		this.kubo_score_b = kubo_score_b;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Date getBc_score_date() {
		return bc_score_date;
	}
	public void setBc_score_date(Date bc_score_date) {
		this.bc_score_date = bc_score_date;
	}
	public String getBc_score() {
		return bc_score;
	}
	public void setBc_score(String bc_score) {
		this.bc_score = bc_score;
	}
	public String getMx_folio() {
		return mx_folio;
	}
	public void setMx_folio(String mx_folio) {
		this.mx_folio = mx_folio;
	}
	public String getMx_solicitud_buro() {
		return mx_solicitud_buro;
	}
	public void setMx_solicitud_buro(String mx_solicitud_buro) {
		this.mx_solicitud_buro = mx_solicitud_buro;
	}
	public Double getRate_investor() {
		return rate_investor;
	}
	public void setRate_investor(Double rate_investor) {
		this.rate_investor = rate_investor;
	}

	public String getRate_investorIF_Exist(){
		Double evaluate = this.getRate_investor();
		if(evaluate == null){
			return "";
		}
		else{
			return this.getRate_investor()+"%";
		}
	}
	public String getCci_score() {
		return cci_score;
	}
	public void setCci_score(String cci_score) {
		this.cci_score = cci_score;
	}
	public Double getOpening_commission() {
		return opening_commission;
	}
	public void setOpening_commission(Double opening_commission) {
		this.opening_commission = opening_commission;
	}
	public String getKubo_rate() {
		return kubo_rate;
	}
	public void setKubo_rate(String kubo_rate) {
		this.kubo_rate = kubo_rate;
	}
	public String getRisk_level() {
		return risk_level;
	}
	public void setRisk_level(String risk_level) {
		this.risk_level = risk_level;
	}
	public Double getLiquidity() {
		return liquidity;
	}
	public void setLiquidity(Double liquidity) {
		this.liquidity = liquidity;
	}
	public NaturalPerson getPerson() {
		return person;
	}
	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRisk_processed() {
		return risk_processed;
	}
	public void setRisk_processed(Integer risk_processed) {
		this.risk_processed = risk_processed;
	}
	public Integer getScreen_viewed() {
		return screen_viewed;
	}
	public void setScreen_viewed(Integer screen_viewed) {
		this.screen_viewed = screen_viewed;
	}
	public String getIs_prospector() {
		return is_prospector;
	}
	public void setIs_prospector(String is_prospector) {
		this.is_prospector = is_prospector;
	}
	public String getEfl_test() {
		return efl_test;
	}
	public void setEfl_test(String efl_test) {
		this.efl_test = efl_test;
	}
	public String getIs_consulting_for_renovation() {
		return is_consulting_for_renovation;
	}
	public void setIs_consulting_for_renovation(String is_consulting_for_renovation) {
		this.is_consulting_for_renovation = is_consulting_for_renovation;
	}
}
