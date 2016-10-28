package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;


@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = SPProyectActive.class,
			name = "getResumenProyectActive",    
			query = "call SP_ACTIVE_PROYECT(:cuentaAho_temp)",    
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class SPProyectActive implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private int sin_mora;
	@Column
	private int sin_mora_cli;
	@Column
	private double montoSinMoraVig;
	@Column
	private double montoSinMoraAtr;
	@Column
	private int atraso1_15;
	@Column
	private int atraso1_15_cli;
	@Column
	private double montoAtraso1_15Vig;
	@Column
	private double montoAtraso1_15Atr;
	@Column
	private int atraso16_30;
	@Column
	private int atraso16_30_cli;
	@Column
	private double montoAtraso16_30Vig;
	@Column
	private double montoAtraso16_30Atr;
	@Column
	private int atraso31_90;
	@Column
	private int atraso31_90_cli;
	@Column
	private double montoAtraso31_90Vig;
	@Column
	private double montoAtraso31_90Atr;
	@Column
	private int atraso91_120;
	@Column
	private int atraso91_120_cli;
	@Column
	private double montoAtraso91_120Vi;
	@Column
	private double montoAtraso91_120At;
	@Column
	private int atraso120_Mas;
	@Column
	private int atraso120_MasCli;
	@Column
	private double montoAtraso120_MasV;
	@Column
	private double montoAtraso120_MasA;
	@Column
	private double saldoIntVig;
	@Column
	private double saldoIntAtr;
	@Column
	private int NumClientesAplGa;
	@Column
	private int NumInversionesAplGa;
	@Column
	private double MontoCtaOrden;
	@Column
	private double SaldoIntCtaOrden;
	@Column
	private int total_creditos_activos;
	@Column
	private int total_creditos_actXcliente;
	@Column
	private double totalVigente;
	@Column
	private double totalExigible;
	
	public int getSin_mora() {
		return sin_mora;
	}
	public void setSin_mora(int sin_mora) {
		this.sin_mora = sin_mora;
	}
	public int getAtraso1_15() {
		return atraso1_15;
	}
	public void setAtraso1_15(int atraso1_15) {
		this.atraso1_15 = atraso1_15;
	}
	public int getAtraso16_30() {
		return atraso16_30;
	}
	public void setAtraso16_30(int atraso16_30) {
		this.atraso16_30 = atraso16_30;
	}
	public int getAtraso31_90() {
		return atraso31_90;
	}
	public void setAtraso31_90(int atraso31_90) {
		this.atraso31_90 = atraso31_90;
	}
	public int getAtraso91_120() {
		return atraso91_120;
	}
	public void setAtraso91_120(int atraso91_120) {
		this.atraso91_120 = atraso91_120;
	}
	public int getAtraso120_Mas() {
		return atraso120_Mas;
	}
	public void setAtraso120_Mas(int atraso120_Mas) {
		this.atraso120_Mas = atraso120_Mas;
	}
	public int getTotal_creditos_activos() {
		return total_creditos_activos;
	}
	public void setTotal_creditos_activos(int total_creditos_activos) {
		this.total_creditos_activos = total_creditos_activos;
	}
	public int getSin_mora_cli() {
		return sin_mora_cli;
	}
	public void setSin_mora_cli(int sin_mora_cli) {
		this.sin_mora_cli = sin_mora_cli;
	}
	public double getMontoSinMoraVig() {
		return montoSinMoraVig;
	}
	public void setMontoSinMoraVig(double montoSinMoraVig) {
		this.montoSinMoraVig = montoSinMoraVig;
	}
	public double getMontoSinMoraAtr() {
		return montoSinMoraAtr;
	}
	public void setMontoSinMoraAtr(double montoSinMoraAtr) {
		 this.montoSinMoraAtr = montoSinMoraAtr;
	}
	public int getAtraso1_15_cli() {
		return atraso1_15_cli;
	}
	public void setAtraso1_15_cli(int atraso1_15_cli) {
		this.atraso1_15_cli = atraso1_15_cli;
	}
	public double getMontoAtraso1_15Vig() {
		return montoAtraso1_15Vig;
	}
	public void setMontoAtraso1_15Vig(double montoAtraso1_15Vig) {
		this.montoAtraso1_15Vig = montoAtraso1_15Vig;
	}
	public double getMontoAtraso1_15Atr() {
		return montoAtraso1_15Atr;
	}
	public void setMontoAtraso1_15Atr(double montoAtraso1_15Atr) {
		this.montoAtraso1_15Atr = montoAtraso1_15Atr;
	}
	public int getAtraso16_30_cli() {
		return atraso16_30_cli;
	}
	public void setAtraso16_30_cli(int atraso16_30_cli) {
		this.atraso16_30_cli = atraso16_30_cli;
	}
	public double getMontoAtraso16_30Vig() {
		return montoAtraso16_30Vig;
	}
	public void setMontoAtraso16_30Vig(double montoAtraso16_30Vig) {
		this.montoAtraso16_30Vig = montoAtraso16_30Vig;
	}
	public double getMontoAtraso16_30Atr() {
		return montoAtraso16_30Atr;
	}
	public void setMontoAtraso16_30Atr(double montoAtraso16_30Atr) {
		this.montoAtraso16_30Atr = montoAtraso16_30Atr;
	}
	public int getAtraso31_90_cli() {
		return atraso31_90_cli;
	}
	public void setAtraso31_90_cli(int atraso31_90_cli) {
		this.atraso31_90_cli = atraso31_90_cli;
	}
	public double getMontoAtraso31_90Vig() {
		return montoAtraso31_90Vig;
	}
	public void setMontoAtraso31_90Vig(double montoAtraso31_90Vig) {
		this.montoAtraso31_90Vig = montoAtraso31_90Vig;
	}
	public double getMontoAtraso31_90Atr() {
		return montoAtraso31_90Atr;
	}
	public void setMontoAtraso31_90Atr(double montoAtraso31_90Atr) {
		this.montoAtraso31_90Atr = montoAtraso31_90Atr;
	}
	public int getAtraso91_120_cli() {
		return atraso91_120_cli;
	}
	public void setAtraso91_120_cli(int atraso91_120_cli) {
		this.atraso91_120_cli = atraso91_120_cli;
	}
	public double getMontoAtraso91_120Vi() {
		return montoAtraso91_120Vi;
	}
	public void setMontoAtraso91_120Vi(double montoAtraso91_120Vi) {
		this.montoAtraso91_120Vi = montoAtraso91_120Vi;
	}
	public double getMontoAtraso91_120At() {
		return montoAtraso91_120At;
	}
	public void setMontoAtraso91_120At(double montoAtraso91_120At) {
		this.montoAtraso91_120At = montoAtraso91_120At;
	}
	public int getAtraso120_MasCli() {
		return atraso120_MasCli;
	}
	public void setAtraso120_MasCli(int atraso120_MasCli) {
		this.atraso120_MasCli = atraso120_MasCli;
	}
	public double getMontoAtraso120_MasV() {
		return montoAtraso120_MasV;
	}
	public void setMontoAtraso120_MasV(double montoAtraso120_MasV) {
		this.montoAtraso120_MasV = montoAtraso120_MasV;
	}
	public double getMontoAtraso120_MasA() {
		return montoAtraso120_MasA;
	}
	public void setMontoAtraso120_MasA(double montoAtraso120_MasA) {
		this.montoAtraso120_MasA = montoAtraso120_MasA;
	}
	public double getSaldoIntVig() {
		return saldoIntVig;
	}
	public void setSaldoIntVig(double saldoIntVig) {
		this.saldoIntVig = saldoIntVig;
	}
	public double getSaldoIntAtr() {
		return saldoIntAtr;
	}
	public void setSaldoIntAtr(double saldoIntAtr) {
		this.saldoIntAtr = saldoIntAtr;
	}
	public int getTotal_creditos_actXcliente() {
		return total_creditos_actXcliente;
	}
	public void setTotal_creditos_actXcliente(int total_creditos_actXcliente) {
		this.total_creditos_actXcliente = total_creditos_actXcliente;
	}
	public double getTotalVigente() {
		return totalVigente;
	}
	public void setTotalVigente(double totalVigente) {
		this.totalVigente = totalVigente;
	}
	public double getTotalExigible() {
		return totalExigible;
	}
	public void setTotalExigible(double totalExigible) {
		this.totalExigible = totalExigible;
	}
	public int getNumClientesAplGa() {
		return NumClientesAplGa;
	}
	public void setNumClientesAplGa(int numClientesAplGa) {
		this.NumClientesAplGa = numClientesAplGa;
	}
	public int getNumInversionesAplGa() {
		return NumInversionesAplGa;
	}
	public void setNumInversionesAplGa(int numInversionesAplGa) {
		this.NumInversionesAplGa = numInversionesAplGa;
	}
	public double getMontoCtaOrden() {
		return MontoCtaOrden;
	}
	public void setMontoCtaOrden(double montoCtaOrden) {
		this.MontoCtaOrden = montoCtaOrden;
	}
	public double getSaldoIntCtaOrden() {
		return SaldoIntCtaOrden;
	}
	public void setSaldoIntCtaOrden(double saldoIntCtaOrden) {
		this.SaldoIntCtaOrden = saldoIntCtaOrden;
	}
	
}
