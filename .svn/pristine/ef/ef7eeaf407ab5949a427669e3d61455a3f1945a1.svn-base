package mx.com.kubo.model;

import java.util.Locale;

public class RestructureBean {

	private ProyectLoan proyect;
	private String fechaInicio;
	private String FechaFin;
	private Double saldoLiquidar;
	
	private  Locale locale = new Locale("es","mx");
	private  java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
	
	public ProyectLoan getProyect() {
		return proyect;
	}
	public void setProyect(ProyectLoan proyect) {
		this.proyect = proyect;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return FechaFin;
	}
	public void setFechaFin(String fechaFin) {
		FechaFin = fechaFin;
	}
	public Double getSaldoLiquidar() {
		return saldoLiquidar;
	}
	public void setSaldoLiquidar(Double saldoLiquidar) {
		this.saldoLiquidar = saldoLiquidar;
	}
	public String getSaldoLiquidarStr() {
		if(saldoLiquidar != null){
			return dec.format(saldoLiquidar);
		}else
			return "";
	}
	
}
