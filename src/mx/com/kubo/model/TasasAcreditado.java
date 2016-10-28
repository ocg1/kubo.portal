package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="pr_mx_tasas_acreditado")
public class TasasAcreditado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TasasAcreditadoPK pk;
	@Column
	private String  kubo_score_a;//	varchar(1)
	@Column
	private String  kubo_score_b;//	varchar(2)
	@Column
	private Double  rate;//	"decimal(12
	@Column
	private Double  rate_investor;//	"decimal(12
	@Column
	private Double opening_commission;
	@Column
	private String kubo_rate;
	@Column
	private Double liquidity;
	
	public TasasAcreditadoPK getPk() {
		return pk;
	}
	public void setPk(TasasAcreditadoPK pk) {
		this.pk = pk;
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
	public void setKubo_score_b(String kubo_score_b) {
		this.kubo_score_b = kubo_score_b;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getRate_investor() {
		return rate_investor;
	}
	public void setRate_investor(Double rate_investor) {
		this.rate_investor = rate_investor;
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
	public Double getLiquidity() {
		return liquidity;
	}
	public void setLiquidity(Double liquidity) {
		this.liquidity = liquidity;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
