package mx.com.kubo.bean;

import java.text.NumberFormat;
import java.util.Locale;

public class ItemInversion {

	private Integer proyect_loan_id;
	private String kubo_score_a;
	private String kubo_score_b;
	private Double ammount;
	private String motivo;
	private String status;
	private String ammountStr;
	private String tasa;
	private String dias_inversion;
	
	
	public  Locale locale = new Locale("es","mx");
	public  NumberFormat dec = NumberFormat.getCurrencyInstance( locale );
	
	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
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
	
	
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAmmountStr() {
		
		if( ammount != null ){
			
			ammountStr = dec.format( ammount );
			
		}
		System.out.println( "-- " + ammountStr );
		return ammountStr;
		
	}
	public void setAmmountStr(String ammountStr) {
		this.ammountStr = ammountStr;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	public String getDias_inversion() {
		return dias_inversion;
	}
	public void setDias_inversion(String dias_inversion) {
		this.dias_inversion = dias_inversion;
	}
	
	
}
