package mx.com.kubo.portal.ofertas;

import java.util.List;

public final class RenovacionBean 
{
	protected String safi_credit_id;
	protected String actual_ammount;	
	protected String actual_payment;
	protected String actual_frequency;
	protected String actual_term;
	protected String max_ammount;
	protected String max_payment;
	
	private String ofert_TOKEN;
	private String ofert_rate_TOKEN;
	private String ofert_ammount_TOKEN;
	private String term_frequency_1_TOKEN;
	private String term_frequency_2_TOKEN;
	private String term_frequency_3_TOKEN;
	
	private List<OfertaBean> tabla_oferta_1;
	private List<OfertaBean> tabla_oferta_2;
	private List<OfertaBean> tabla_oferta_3;

	public void setOfert_TOKEN(String ofert_TOKEN) {
		this.ofert_TOKEN = ofert_TOKEN;
	}
	
	public void setOfert_rate_TOKEN(String ofert_rate_TOKEN) {
		this.ofert_rate_TOKEN = ofert_rate_TOKEN;
	}

	public void setOfert_ammount_TOKEN(String ofert_ammount_TOKEN) {
		this.ofert_ammount_TOKEN = ofert_ammount_TOKEN;
	}

	public void setTerm_frequency_1_TOKEN(String term_frequency_1_TOKEN) {
		this.term_frequency_1_TOKEN = term_frequency_1_TOKEN;
	}

	public void setTerm_frequency_2_TOKEN(String term_frequency_2_TOKEN) {
		this.term_frequency_2_TOKEN = term_frequency_2_TOKEN;
	}

	public void setTerm_frequency_3_TOKEN(String term_frequency_3_TOKEN) {
		this.term_frequency_3_TOKEN = term_frequency_3_TOKEN;
	}

	public void setTabla_oferta_1(List<OfertaBean> tabla_oferta_1) {
		this.tabla_oferta_1 = tabla_oferta_1;
	}

	public void setTabla_oferta_2(List<OfertaBean> tabla_oferta_2) {
		this.tabla_oferta_2 = tabla_oferta_2;
	}

	public void setTabla_oferta_3(List<OfertaBean> tabla_oferta_3) {
		this.tabla_oferta_3 = tabla_oferta_3;
	}
	
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}

	public void setActual_ammount(String actual_ammount) {
		this.actual_ammount = actual_ammount;
	}
	
	public void setActual_payment(String actual_payment) {
		this.actual_payment = actual_payment;
	}
	
	public void setActual_frequency(String actual_frequency) {
		this.actual_frequency = actual_frequency;
	}
	
	public void setMax_ammount(String max_ammount) {
		this.max_ammount = max_ammount;
	}

	public void setMax_payment(String max_payment) {
		this.max_payment = max_payment;
	}

	public void setActual_term(String actual_term) {
		this.actual_term = actual_term;
	}

	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	
	public String getActual_ammount() {
		return actual_ammount;
	}
	
	public String getActual_payment() {
		return actual_payment;
	}

	public String getActual_frequency() {
		return actual_frequency;
	}

	public String getActual_term() {
		return actual_term;
	}

	public String getMax_ammount() {
		return max_ammount;
	}

	public String getMax_payment() {
		return max_payment;
	}

	public String getOfert_TOKEN() {
		return ofert_TOKEN;
	}

	public String getOfert_rate_TOKEN() {
		return ofert_rate_TOKEN;
	}
	
	public String getOfert_ammount_TOKEN() {
		return ofert_ammount_TOKEN;
	}

	public String getTerm_frequency_1_TOKEN() 
	{
		return term_frequency_1_TOKEN;
	}
	
	public String getTerm_frequency_2_TOKEN() 
	{
		return term_frequency_2_TOKEN;
	}
	
	public String getTerm_frequency_3_TOKEN() 
	{
		return term_frequency_3_TOKEN;
	}
	
	public List<OfertaBean> getTabla_oferta_1() 
	{
		return tabla_oferta_1;
	}		
	
	public List<OfertaBean> getTabla_oferta_2() 
	{
		return tabla_oferta_2;
	}	
	
	public List<OfertaBean> getTabla_oferta_3() 
	{
		return tabla_oferta_3;
	}	
}
