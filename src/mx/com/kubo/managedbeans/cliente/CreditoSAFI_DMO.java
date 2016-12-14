package mx.com.kubo.managedbeans.cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import mx.com.kubo.model.TSafiPosicionInt;

public abstract class CreditoSAFI_DMO 
{
	protected TSafiPosicionInt credito_safi;
	
	private String 
		credito_amortizacion_id,     
		credito_cuota,
		credito_cuota_mas_comision,   
		credito_estatus,
		credito_fecha_inicio,                    
		credito_fecha_prox_ven,                  
		credito_fecha_ven,        
		credito_fecha_ultimo_pago,
		credito_id,                              
		credito_monto,                           			
		credito_no_amortizacion,                 
		credito_saldo_capital_atrasado,          
		credito_saldo_capital_vigent,            
		credito_saldo_capital_vigente,           
		credito_saldo_capital_vencido,           
		credito_saldo_capital_vencido_no_exigle, 
		credito_saldo_com_falta_pago,            			
		credito_saldo_interes_atrasado,          
		credito_saldo_interes_provisional,       
		credito_saldo_interes_no_contable,       
		credito_saldo_interes_vencido,           
		credito_saldo_interes_pro,               	
		credito_saldo_moratorio,                 
		credito_tasa_fija,                       
		credito_valor_cat;
	
	private SimpleDateFormat 
		formato_ddMMyyyy,
		formato_yyyyMMdd_hhmmssS;
	
	protected CreditoSAFI_DMO()
	{
		formato_ddMMyyyy          = new SimpleDateFormat("dd'-'MMM'-'yyyy", new Locale("ES"));
		formato_yyyyMMdd_hhmmssS  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");	
	}
	
	public String getCredito_amortizacion_id() {
		return credito_amortizacion_id;
	}

	public void setCredito_amortizacion_id(String credito_amortizacion_id) {
		this.credito_amortizacion_id = credito_amortizacion_id;
	}
	
	public Double getCredito_cuota() 
	{
		return credito_cuota == null ? 0 : Double.parseDouble(credito_cuota);
	}

	public void setCredito_cuota(String credito_cuota) {
		this.credito_cuota = credito_cuota;
	}

	public Double getCredito_cuota_mas_comision() 
	{
		return credito_cuota_mas_comision == null ? 0 : Double.parseDouble(credito_cuota_mas_comision);
	}

	public void setCredito_cuota_mas_comision(String credito_cuota_mas_comision) {
		this.credito_cuota_mas_comision = credito_cuota_mas_comision;
		
	}

	public String getEstatus() {
		return credito_estatus;
	}

	public void setCredito_estatus(String credito_estatus) {
		this.credito_estatus = credito_estatus;
	}

	public String getFecha_inicio() {
		return credito_fecha_inicio;
	}

	public void setCredito_fecha_inicio(String credito_fecha_inicio) {
		this.credito_fecha_inicio = credito_fecha_inicio;
	}

	public String getFecha_prox_ven() throws ParseException 
	{	
		if(credito_fecha_prox_ven == null || credito_fecha_prox_ven.equals("null") )
		{
			return "";	
		} else {
			return formato_ddMMyyyy.format(formato_yyyyMMdd_hhmmssS.parse(credito_fecha_prox_ven)).toUpperCase();
		}		
	}

	public void setCredito_fecha_prox_ven(String credito_fecha_prox_ven) {
		this.credito_fecha_prox_ven = credito_fecha_prox_ven;
	}

	public String getFecha_vencimiento() {
		return credito_fecha_ven;
	}

	public void setCredito_fecha_ven(String credito_fecha_ven) {
		this.credito_fecha_ven = credito_fecha_ven;
	}

	public String getCredito_fecha_ultimo_pago() {
		return credito_fecha_ultimo_pago;
	}

	public void setCredito_fecha_ultimo_pago(String credito_fecha_ultimo_pago) {
		this.credito_fecha_ultimo_pago = credito_fecha_ultimo_pago;
	}

	public String getCredito_id() {
		return credito_id;
	}

	public void setCredito_id(String credito_id) {
		this.credito_id = credito_id;
	}

	public Double getMonto() 
	{
		return credito_monto == null ? 0 : Double.parseDouble(credito_monto);
	}

	public void setCredito_monto(String credito_monto) {
		this.credito_monto = credito_monto;
	}

	public String getAmortizacion_numero() {
		return credito_no_amortizacion;
	}

	public void setCredito_no_amortizacion(String credito_no_amortizacion) {
		this.credito_no_amortizacion = credito_no_amortizacion;
	}

	public Double getSaldo_capital_atrasado() 
	{
		return credito_saldo_capital_atrasado == null ? 0 : Double.parseDouble(credito_saldo_capital_atrasado);
	}

	public void setCredito_saldo_capital_atrasado(String saldo) 
	{
		credito_saldo_capital_atrasado = saldo;
	}

	public Double getSaldo_capital_vigent() 
	{
		return credito_saldo_capital_vigent == null ? 0 : Double.parseDouble(credito_saldo_capital_vigent);
	}

	public void setCredito_saldo_capital_vigent(String saldo) 
	{
		credito_saldo_capital_vigent = saldo;
	}

	public Double getSaldo_capital_vigente() 
	{
		if(credito_saldo_capital_vigente  == null)
		{
			return (double) 0;
		} else if(credito_saldo_capital_vigente.toUpperCase().equals("NULL")){
			return (double) 0;
		} else {
			return Double.parseDouble(credito_saldo_capital_vigente);
		}		
	}

	public void setCredito_saldo_capital_vigente(String saldo) 
	{
		credito_saldo_capital_vigente = saldo;
	}

	public Double getSaldo_capital_vencido() 
	{
		return credito_saldo_capital_vencido == null ? 0 : Double.parseDouble(credito_saldo_capital_vencido);
	}

	public void setCredito_saldo_capital_vencido(String saldo) 
	{
		credito_saldo_capital_vencido = saldo;
	}

	public Double getCredito_saldo_capital_vencido_no_exigle() 
	{
		return credito_saldo_capital_vencido_no_exigle == null ? 0 : Double.parseDouble(credito_saldo_capital_vencido_no_exigle);		
	}

	public void setCredito_saldo_capital_vencido_no_exigle(String saldo) 
	{
		credito_saldo_capital_vencido_no_exigle = saldo;
	}

	public final Double getSaldo_com_falta_pago() 
	{		
		return credito_saldo_com_falta_pago == null ? 0 : Double.parseDouble(credito_saldo_com_falta_pago);
	}

	public void setCredito_saldo_com_falta_pago(String credito_saldo_com_falta_pago) {
		this.credito_saldo_com_falta_pago = credito_saldo_com_falta_pago;
	}

	public Double getSaldo_interes_atrasado() 
	{		
		return credito_saldo_interes_atrasado == null ? 0 : Double.parseDouble(credito_saldo_interes_atrasado);
	}

	public void setCredito_saldo_interes_atrasado(String saldo) 
	{
		this.credito_saldo_interes_atrasado = saldo;
	}

	public Double getSaldo_interes_provisional() 
	{
		return credito_saldo_interes_provisional == null ? 0 : Double.parseDouble(credito_saldo_interes_provisional);
	}

	public void setCredito_saldo_interes_provisional(String saldo) 
	{
		credito_saldo_interes_provisional = saldo;
	}

	public Double getSaldo_interes_no_contable() 
	{
		return credito_saldo_interes_no_contable == null ? 0 : Double.parseDouble(credito_saldo_interes_no_contable);
	}

	public void setCredito_saldo_interes_no_contable(String saldo) 
	{
		this.credito_saldo_interes_no_contable = saldo;
	}

	public Double getSaldo_interes_vencido() 
	{
		return credito_saldo_interes_vencido == null ? 0 : Double.parseDouble(credito_saldo_interes_vencido);
	}

	public void setCredito_saldo_interes_vencido(String saldo) 
	{
		this.credito_saldo_interes_vencido = saldo;
	}

	public Double getSaldo_interes_pro() 
	{
		return (credito_saldo_interes_pro == null || (credito_saldo_interes_pro.equals("null")) ) ? 0 : Double.parseDouble(credito_saldo_interes_pro);
	}

	public void setCredito_saldo_interes_pro(String saldo) 
	{
		credito_saldo_interes_pro = saldo;
	}

	public final Double getSaldo_moratorio() 
	{
		return credito_saldo_moratorio == null ? 0 : Double.parseDouble(credito_saldo_moratorio);
	}

	public void setCredito_saldo_moratorio(String saldo) 
	{
		credito_saldo_moratorio = saldo;
	}

	public String getTasa_fija() 
	{
		return credito_tasa_fija;
	}

	public void setCredito_tasa_fija(String credito_tasa_fija) {
		this.credito_tasa_fija = credito_tasa_fija;
	}

	public String getValor_cat() {
		return credito_valor_cat;
	}

	public void setCredito_valor_cat(String credito_valor_cat) {
		this.credito_valor_cat = credito_valor_cat;
	}  
}
