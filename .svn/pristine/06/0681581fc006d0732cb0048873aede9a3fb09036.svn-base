package mx.com.kubo.bean;

import java.io.Serializable;
import java.util.Locale;

public class CreditoCaracteristicas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int 	crdId;
	private String 	categoria;
	private Integer grado;
	private Double 	tasa;
	private Integer plazo;
	private String 	califBuro;
	private Double 	montoTotal;
	private String 	titCredito;
	private String 	proposito;
	private Double 	porcFondeo;
	private Double 	montoRestante;
	private Integer tiempoRestante;
	private String claseNumero;
	private Double widthPorc;
	private String 	sMontoTotal;
	private String 	sMontoRestante;
	
	public static Locale locale = new Locale("es","mx");
	public static java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
	public static java.text.NumberFormat num = java.text.NumberFormat.getNumberInstance(locale);
	
	public CreditoCaracteristicas(){
	
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getGrado() {
		return grado;
	}
	public void setGrado(Integer grado) {
		this.grado = grado;
	}
	public Double getTasa() {
		return tasa;
	}
	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}
	public Integer getPlazo() {
		return plazo;
	}
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}
	public String getCalifBuro() {
		return califBuro;
	}
	public void setCalifBuro(String califBuro) {
		this.califBuro = califBuro;
	}
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getTitCredito() {
		return titCredito;
	}
	public void setTitCredito(String titCredito) {
		this.titCredito = titCredito;
	}
	public String getProposito() {
		return proposito;
	}
	public void setProposito(String proposito) {
		this.proposito = proposito;
	}
	public Double getPorcFondeo() {
		return Math.rint((100*montoRestante)/montoTotal*100)/100;
	}
	public void setPorcFondeo(Double porcFondeo) {
		this.porcFondeo = Math.rint((100*montoRestante)/montoTotal*100)/100;
	}
	public Double getMontoRestante() {
		return montoRestante;
	}
	public void setMontoRestante(Double montoRestante) {
		this.montoRestante = montoRestante;
	}
	public Integer getTiempoRestante() {
		return tiempoRestante;
	}
	public void setTiempoRestante(Integer tiempoRestante) {
		this.tiempoRestante = tiempoRestante;
	}
	public void setClaseNumero(String claseNumero){
		this.claseNumero = claseNumero;
	}
	public String getClaseNumero(){
		return "gradeNumber"+getCategoria();
	}

	public Double getWidthPorc() {
		return Math.rint((Double)(getPorcFondeo() * 0.75303030303031313131)*100)/100;
	}

	public void setWidthPorc(Double widthPorc) {
		this.widthPorc = widthPorc;
	}

	public String getsMontoTotal() {
		return dec.format(montoTotal);
	}

	public void setsMontoTotal(String sMontoTotal) {
		this.sMontoTotal = sMontoTotal;
	}

	public String getsMontoRestante() {
		return dec.format(montoRestante);
	}

	public void setsMontoRestante(String sMontoRestante) {
		this.sMontoRestante = sMontoRestante;
	}

	public int getCrdId() {
		return crdId;
	}

	public void setCrdId(int crdId) {
		this.crdId = crdId;
	}
	
}
