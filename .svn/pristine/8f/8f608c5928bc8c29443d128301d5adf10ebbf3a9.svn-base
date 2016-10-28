package mx.com.kubo.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

@Entity
@Table(name="view_investment_in_proyect")
public class ViewInvestmetInProyect implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ViewInvestmetInProyect_PK proyectloanfundingPk;
	
	
	@Column
	private Double amount;
	@Column(name="PorcentajeFondeo")
	private Double porcentajeFondeo;
	@Column(name="DisponibleFondeo")
	private Double disponibleFondeo;
	@Column(name="DiasPorTrans")
	private Integer diasPoTrans;
	@Column(name="Estatus")
	private String estatus;
	
//	
//	@ManyToOne(fetch=FetchType.EAGER)  
//	@JoinColumns(value = {
//	        @JoinColumn(name = "investor", referencedColumnName = "safi_client_id", insertable = false, updatable = false)
//	    })
//	
//	private NaturalPerson investorPerson;
//
//	@ManyToOne  
//	@JoinColumnsOrFormulas({
//			@JoinColumnOrFormula(formula=@JoinFormula(value="CAST(investor AS INTEGER)", referencedColumnName="safi_client_id")) })
//	@Fetch(FetchMode.JOIN)
//	private NaturalPerson investorPerson;
	
	public ViewInvestmetInProyect_PK getProyectloanfundingPk() {
		return proyectloanfundingPk;
	}

	public void setProyectloanfundingPk(
			ViewInvestmetInProyect_PK proyectloanfundingPk) {
		this.proyectloanfundingPk = proyectloanfundingPk;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPorcentajeFondeo() {
		return porcentajeFondeo;
	}

	public void setPorcentajeFondeo(Double porcentajeFondeo) {
		this.porcentajeFondeo = porcentajeFondeo;
	}

	public Double getDisponibleFondeo() {
		return disponibleFondeo;
	}

	public void setDisponibleFondeo(Double disponibleFondeo) {
		this.disponibleFondeo = disponibleFondeo;
	}

	public Integer getDiasPoTrans() {
		return diasPoTrans;
	}

	public void setDiasPoTrans(Integer diasPoTrans) {
		this.diasPoTrans = diasPoTrans;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

//	public NaturalPerson getInvestorPerson() {
//		return investorPerson;
//	}
//
//	public void setInvestorPerson(NaturalPerson investorPerson) {
//		this.investorPerson = investorPerson;
//	}
	
	
}
