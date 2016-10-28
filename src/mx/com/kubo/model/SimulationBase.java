package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="in_simulation_base")
public class SimulationBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	@Id
	private int 		sim_base_id; //` INT(10) NOT NULL,
	@Column
	private Double  	ammount; //` DECIMAL(12,2) NULL,
	@Column
	private Integer 	term; //` INT(10) NULL,
	@Column
	private String  	kubo_score_a; //` VARCHAR(1) NULL,
	@Column
	private String  	kubo_score_b; //` VARCHAR(1) NULL,
	@Column
	private String 		gender_id; //` INT(1) NULL,
	@Column
	private String 		purpose; //` VARCHAR(60) NULL,
	@Column
	private Double 		rate; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		payment; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes; //` DECIMAL(12,10) NULL,
	@Column
	private Integer 	no_pago; //` INT(1) NULL,
	@Column
	private Double 		capital_payment; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_payment; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_1; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_1; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_2; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_2; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_3; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_3; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_4; //` DECIMAL(12,10) NULL,
	@Column
	private Double		interes_fee_4; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_5; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_5; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_6; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_6; // DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_7; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_7; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_8; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_8; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_9; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_9; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_10; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_10; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_11; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_11; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_12; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_12; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_13; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_13; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_14; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_14; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_15; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_15; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_16; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_16; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_17; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_17; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		capital_fee_18; //` DECIMAL(12,10) NULL,
	@Column
	private Double 		interes_fee_18; //` DECIMAL(12,10) NULL,
	
	
	public int getSim_base_id() {
		return sim_base_id;
	}
	public void setSim_base_id(int sim_base_id) {
		this.sim_base_id = sim_base_id;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
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
	public String getGender_id() {
		return gender_id;
	}
	public void setGender_id(String gender_id) {
		this.gender_id = gender_id;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Double getInteres() {
		return interes;
	}
	public void setInteres(Double interes) {
		this.interes = interes;
	}
	public Integer getNo_pago() {
		return no_pago;
	}
	public void setNo_pago(Integer no_pago) {
		this.no_pago = no_pago;
	}
	public Double getCapital_payment() {
		return capital_payment;
	}
	public void setCapital_payment(Double capital_payment) {
		this.capital_payment = capital_payment;
	}
	public Double getInteres_payment() {
		return interes_payment;
	}
	public void setInteres_payment(Double interes_payment) {
		this.interes_payment = interes_payment;
	}
	public Double getCapital_fee_1() {
		return capital_fee_1;
	}
	public void setCapital_fee_1(Double capital_fee_1) {
		this.capital_fee_1 = capital_fee_1;
	}
	public Double getInteres_fee_1() {
		return interes_fee_1;
	}
	public void setInteres_fee_1(Double interes_fee_1) {
		this.interes_fee_1 = interes_fee_1;
	}
	public Double getCapital_fee_2() {
		return capital_fee_2;
	}
	public void setCapital_fee_2(Double capital_fee_2) {
		this.capital_fee_2 = capital_fee_2;
	}
	public Double getInteres_fee_2() {
		return interes_fee_2;
	}
	public void setInteres_fee_2(Double interes_fee_2) {
		this.interes_fee_2 = interes_fee_2;
	}
	public Double getCapital_fee_3() {
		return capital_fee_3;
	}
	public void setCapital_fee_3(Double capital_fee_3) {
		this.capital_fee_3 = capital_fee_3;
	}
	public Double getInteres_fee_3() {
		return interes_fee_3;
	}
	public void setInteres_fee_3(Double interes_fee_3) {
		this.interes_fee_3 = interes_fee_3;
	}
	public Double getCapital_fee_4() {
		return capital_fee_4;
	}
	public void setCapital_fee_4(Double capital_fee_4) {
		this.capital_fee_4 = capital_fee_4;
	}
	public Double getInteres_fee_4() {
		return interes_fee_4;
	}
	public void setInteres_fee_4(Double interes_fee_4) {
		this.interes_fee_4 = interes_fee_4;
	}
	public Double getCapital_fee_5() {
		return capital_fee_5;
	}
	public void setCapital_fee_5(Double capital_fee_5) {
		this.capital_fee_5 = capital_fee_5;
	}
	public Double getInteres_fee_5() {
		return interes_fee_5;
	}
	public void setInteres_fee_5(Double interes_fee_5) {
		this.interes_fee_5 = interes_fee_5;
	}
	public Double getCapital_fee_6() {
		return capital_fee_6;
	}
	public void setCapital_fee_6(Double capital_fee_6) {
		this.capital_fee_6 = capital_fee_6;
	}
	public Double getInteres_fee_6() {
		return interes_fee_6;
	}
	public void setInteres_fee_6(Double interes_fee_6) {
		this.interes_fee_6 = interes_fee_6;
	}
	public Double getCapital_fee_7() {
		return capital_fee_7;
	}
	public void setCapital_fee_7(Double capital_fee_7) {
		this.capital_fee_7 = capital_fee_7;
	}
	public Double getInteres_fee_7() {
		return interes_fee_7;
	}
	public void setInteres_fee_7(Double interes_fee_7) {
		this.interes_fee_7 = interes_fee_7;
	}
	public Double getCapital_fee_8() {
		return capital_fee_8;
	}
	public void setCapital_fee_8(Double capital_fee_8) {
		this.capital_fee_8 = capital_fee_8;
	}
	public Double getInteres_fee_8() {
		return interes_fee_8;
	}
	public void setInteres_fee_8(Double interes_fee_8) {
		this.interes_fee_8 = interes_fee_8;
	}
	public Double getCapital_fee_9() {
		return capital_fee_9;
	}
	public void setCapital_fee_9(Double capital_fee_9) {
		this.capital_fee_9 = capital_fee_9;
	}
	public Double getInteres_fee_9() {
		return interes_fee_9;
	}
	public void setInteres_fee_9(Double interes_fee_9) {
		this.interes_fee_9 = interes_fee_9;
	}
	public Double getCapital_fee_10() {
		return capital_fee_10;
	}
	public void setCapital_fee_10(Double capital_fee_10) {
		this.capital_fee_10 = capital_fee_10;
	}
	public Double getInteres_fee_10() {
		return interes_fee_10;
	}
	public void setInteres_fee_10(Double interes_fee_10) {
		this.interes_fee_10 = interes_fee_10;
	}
	public Double getCapital_fee_11() {
		return capital_fee_11;
	}
	public void setCapital_fee_11(Double capital_fee_11) {
		this.capital_fee_11 = capital_fee_11;
	}
	public Double getInteres_fee_11() {
		return interes_fee_11;
	}
	public void setInteres_fee_11(Double interes_fee_11) {
		this.interes_fee_11 = interes_fee_11;
	}
	public Double getCapital_fee_12() {
		return capital_fee_12;
	}
	public void setCapital_fee_12(Double capital_fee_12) {
		this.capital_fee_12 = capital_fee_12;
	}
	public Double getInteres_fee_12() {
		return interes_fee_12;
	}
	public void setInteres_fee_12(Double interes_fee_12) {
		this.interes_fee_12 = interes_fee_12;
	}
	public Double getCapital_fee_13() {
		return capital_fee_13;
	}
	public void setCapital_fee_13(Double capital_fee_13) {
		this.capital_fee_13 = capital_fee_13;
	}
	public Double getInteres_fee_13() {
		return interes_fee_13;
	}
	public void setInteres_fee_13(Double interes_fee_13) {
		this.interes_fee_13 = interes_fee_13;
	}
	public Double getCapital_fee_14() {
		return capital_fee_14;
	}
	public void setCapital_fee_14(Double capital_fee_14) {
		this.capital_fee_14 = capital_fee_14;
	}
	public Double getInteres_fee_14() {
		return interes_fee_14;
	}
	public void setInteres_fee_14(Double interes_fee_14) {
		this.interes_fee_14 = interes_fee_14;
	}
	public Double getCapital_fee_15() {
		return capital_fee_15;
	}
	public void setCapital_fee_15(Double capital_fee_15) {
		this.capital_fee_15 = capital_fee_15;
	}
	public Double getInteres_fee_15() {
		return interes_fee_15;
	}
	public void setInteres_fee_15(Double interes_fee_15) {
		this.interes_fee_15 = interes_fee_15;
	}
	public Double getCapital_fee_16() {
		return capital_fee_16;
	}
	public void setCapital_fee_16(Double capital_fee_16) {
		this.capital_fee_16 = capital_fee_16;
	}
	public Double getInteres_fee_16() {
		return interes_fee_16;
	}
	public void setInteres_fee_16(Double interes_fee_16) {
		this.interes_fee_16 = interes_fee_16;
	}
	public Double getCapital_fee_17() {
		return capital_fee_17;
	}
	public void setCapital_fee_17(Double capital_fee_17) {
		this.capital_fee_17 = capital_fee_17;
	}
	public Double getInteres_fee_17() {
		return interes_fee_17;
	}
	public void setInteres_fee_17(Double interes_fee_17) {
		this.interes_fee_17 = interes_fee_17;
	}
	public Double getCapital_fee_18() {
		return capital_fee_18;
	}
	public void setCapital_fee_18(Double capital_fee_18) {
		this.capital_fee_18 = capital_fee_18;
	}
	public Double getInteres_fee_18() {
		return interes_fee_18;
	}
	public void setInteres_fee_18(Double interes_fee_18) {
		this.interes_fee_18 = interes_fee_18;
	}

	
}
