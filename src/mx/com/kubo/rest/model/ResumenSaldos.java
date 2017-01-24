package mx.com.kubo.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResumenSaldos 
{
	private String saldo_disponible;
	private String depositos_mes;
	private String intereses_cobrados;
	private String saldo_total;
	
	private String globalEfectivoDisponible;  
	private String globalInversionesProceso;  
	private String notes_investment_process;
	private String impulsoInversionesActivas; 
	private String number_notes_active;
	private String impulsoInteresesCobrar;  
	private String fijoInversionesActivas;    
	private String fijoInteresesDevengados;   
	private String cuentasSaldoTotal;    
	
	private String globalIntereses;
    private String impulsoCapital;
    private String impulsoInteresesRecibidos;
    private String impulsoInteresesMoratorios;
    private String fijoIntereses;
    private String pagosRecibidos;
    
    private String enProcesoVigente;
    private String enProcesoAtrasado;
    private String enProcesoClientes;
    private String activasVigente;
    private String activasAtrasado;
    private String activasClientes;
    private String sixteenDaysVigente;
    private String sixteenDaysAtrasado;
    private String sixteenDaysClientes;
    private String thirtyoneDaysVigente;
    private String thirtyoneDaysAtrasado;
    private String thirtyoneDaysClientes;
    private String ninetyoneDaysVigente;
    private String ninetyoneDaysAtrasado;
    private String ninetyoneDaysClientes;
    private String morethan120DaysVigente;
    private String morethan120DaysAtrasado;
    private String morethan120DaysClientes;
    private String inversionesActivasVigente;
    private String inversionesActivasAtrasado;
    private String inversionesActivasClientes;
    private String interesesPorCobrarVigente;
    private String interesesPorCobrarAtrasado;
    private String impulsoSaldoTotal;
    private String pagadasTotalmente;
    private String pagadasTotalmenteClientes;
    private String creditosEnVenta;
    private String creditosEnVentaClientes;
    private String garantiasAplicadasCapital;
    private String garantiasAplicadasInteres;
    private String garantiasSaldoTotal;
    private String garantiasSaldoTotalClientes;
    
	public String getEnProcesoVigente() {
		return enProcesoVigente;
	}

	public void setEnProcesoVigente(String enProcesoVigente) {
		this.enProcesoVigente = enProcesoVigente;
	}

	public String getEnProcesoAtrasado() {
		return enProcesoAtrasado;
	}

	public void setEnProcesoAtrasado(String enProcesoAtrasado) {
		this.enProcesoAtrasado = enProcesoAtrasado;
	}

	public String getEnProcesoClientes() {
		return enProcesoClientes;
	}

	public void setEnProcesoClientes(String enProcesoClientes) {
		this.enProcesoClientes = enProcesoClientes;
	}

	public String getActivasVigente() {
		return activasVigente;
	}

	public void setActivasVigente(String activasVigente) {
		this.activasVigente = activasVigente;
	}

	public String getActivasAtrasado() {
		return activasAtrasado;
	}

	public void setActivasAtrasado(String activasAtrasado) {
		this.activasAtrasado = activasAtrasado;
	}

	public String getActivasClientes() {
		return activasClientes;
	}

	public void setActivasClientes(String activasClientes) {
		this.activasClientes = activasClientes;
	}

	public String getSixteenDaysVigente() {
		return sixteenDaysVigente;
	}

	public void setSixteenDaysVigente(String sixteenDaysVigente) {
		this.sixteenDaysVigente = sixteenDaysVigente;
	}

	public String getSixteenDaysAtrasado() {
		return sixteenDaysAtrasado;
	}

	public void setSixteenDaysAtrasado(String sixteenDaysAtrasado) {
		this.sixteenDaysAtrasado = sixteenDaysAtrasado;
	}

	public String getSixteenDaysClientes() {
		return sixteenDaysClientes;
	}

	public void setSixteenDaysClientes(String sixteenDaysClientes) {
		this.sixteenDaysClientes = sixteenDaysClientes;
	}

	public String getThirtyoneDaysVigente() {
		return thirtyoneDaysVigente;
	}

	public void setThirtyoneDaysVigente(String thirtyoneDaysVigente) {
		this.thirtyoneDaysVigente = thirtyoneDaysVigente;
	}

	public String getThirtyoneDaysAtrasado() {
		return thirtyoneDaysAtrasado;
	}

	public void setThirtyoneDaysAtrasado(String thirtyoneDaysAtrasado) {
		this.thirtyoneDaysAtrasado = thirtyoneDaysAtrasado;
	}

	public String getThirtyoneDaysClientes() {
		return thirtyoneDaysClientes;
	}

	public void setThirtyoneDaysClientes(String thirtyoneDaysClientes) {
		this.thirtyoneDaysClientes = thirtyoneDaysClientes;
	}

	public String getNinetyoneDaysVigente() {
		return ninetyoneDaysVigente;
	}

	public void setNinetyoneDaysVigente(String ninetyoneDaysVigente) {
		this.ninetyoneDaysVigente = ninetyoneDaysVigente;
	}

	public String getNinetyoneDaysAtrasado() {
		return ninetyoneDaysAtrasado;
	}

	public void setNinetyoneDaysAtrasado(String ninetyoneDaysAtrasado) {
		this.ninetyoneDaysAtrasado = ninetyoneDaysAtrasado;
	}

	public String getNinetyoneDaysClientes() {
		return ninetyoneDaysClientes;
	}

	public void setNinetyoneDaysClientes(String ninetyoneDaysClientes) {
		this.ninetyoneDaysClientes = ninetyoneDaysClientes;
	}

	public String getMorethan120DaysVigente() {
		return morethan120DaysVigente;
	}

	public void setMorethan120DaysVigente(String morethan120DaysVigente) {
		this.morethan120DaysVigente = morethan120DaysVigente;
	}

	public String getMorethan120DaysAtrasado() {
		return morethan120DaysAtrasado;
	}

	public void setMorethan120DaysAtrasado(String morethan120DaysAtrasado) {
		this.morethan120DaysAtrasado = morethan120DaysAtrasado;
	}

	public String getMorethan120DaysClientes() {
		return morethan120DaysClientes;
	}

	public void setMorethan120DaysClientes(String morethan120DaysClientes) {
		this.morethan120DaysClientes = morethan120DaysClientes;
	}

	public String getInversionesActivasVigente() {
		return inversionesActivasVigente;
	}

	public void setInversionesActivasVigente(String inversionesActivasVigente) {
		this.inversionesActivasVigente = inversionesActivasVigente;
	}

	public String getInversionesActivasAtrasado() {
		return inversionesActivasAtrasado;
	}

	public void setInversionesActivasAtrasado(String inversionesActivasAtrasado) {
		this.inversionesActivasAtrasado = inversionesActivasAtrasado;
	}

	public String getInversionesActivasClientes() {
		return inversionesActivasClientes;
	}

	public void setInversionesActivasClientes(String inversionesActivasClientes) {
		this.inversionesActivasClientes = inversionesActivasClientes;
	}

	public String getInteresesPorCobrarVigente() {
		return interesesPorCobrarVigente;
	}

	public void setInteresesPorCobrarVigente(String interesesPorCobrarVigente) {
		this.interesesPorCobrarVigente = interesesPorCobrarVigente;
	}

	public String getInteresesPorCobrarAtrasado() {
		return interesesPorCobrarAtrasado;
	}

	public void setInteresesPorCobrarAtrasado(String interesesPorCobrarAtrasado) {
		this.interesesPorCobrarAtrasado = interesesPorCobrarAtrasado;
	}

	public String getImpulsoSaldoTotal() {
		return impulsoSaldoTotal;
	}

	public void setImpulsoSaldoTotal(String impulsoSaldoTotal) {
		this.impulsoSaldoTotal = impulsoSaldoTotal;
	}

	public String getPagadasTotalmente() {
		return pagadasTotalmente;
	}

	public void setPagadasTotalmente(String pagadasTotalmente) {
		this.pagadasTotalmente = pagadasTotalmente;
	}

	public String getPagadasTotalmenteClientes() {
		return pagadasTotalmenteClientes;
	}

	public void setPagadasTotalmenteClientes(String pagadasTotalmenteClientes) {
		this.pagadasTotalmenteClientes = pagadasTotalmenteClientes;
	}

	public String getCreditosEnVenta() {
		return creditosEnVenta;
	}

	public void setCreditosEnVenta(String creditosEnVenta) {
		this.creditosEnVenta = creditosEnVenta;
	}

	public String getCreditosEnVentaClientes() {
		return creditosEnVentaClientes;
	}

	public void setCreditosEnVentaClientes(String creditosEnVentaClientes) {
		this.creditosEnVentaClientes = creditosEnVentaClientes;
	}

	public String getGarantiasAplicadasCapital() {
		return garantiasAplicadasCapital;
	}

	public void setGarantiasAplicadasCapital(String garantiasAplicadasCapital) {
		this.garantiasAplicadasCapital = garantiasAplicadasCapital;
	}

	public String getGarantiasAplicadasInteres() {
		return garantiasAplicadasInteres;
	}

	public void setGarantiasAplicadasInteres(String garantiasAplicadasInteres) {
		this.garantiasAplicadasInteres = garantiasAplicadasInteres;
	}

	public String getGarantiasSaldoTotal() {
		return garantiasSaldoTotal;
	}

	public void setGarantiasSaldoTotal(String garantiasSaldoTotal) {
		this.garantiasSaldoTotal = garantiasSaldoTotal;
	}

	public String getGarantiasSaldoTotalClientes() {
		return garantiasSaldoTotalClientes;
	}

	public void setGarantiasSaldoTotalClientes(String garantiasSaldoTotalClientes) {
		this.garantiasSaldoTotalClientes = garantiasSaldoTotalClientes;
	}

	public String getGlobalIntereses() {
		return globalIntereses;
	}

	public void setGlobalIntereses(String globalIntereses) {
		this.globalIntereses = globalIntereses;
	}

	public String getImpulsoCapital() {
		return impulsoCapital;
	}

	public void setImpulsoCapital(String impulsoCapital) {
		this.impulsoCapital = impulsoCapital;
	}

	public String getImpulsoInteresesRecibidos() {
		return impulsoInteresesRecibidos;
	}

	public void setImpulsoInteresesRecibidos(String impulsoInteresesRecibidos) {
		this.impulsoInteresesRecibidos = impulsoInteresesRecibidos;
	}

	public String getImpulsoInteresesMoratorios() {
		return impulsoInteresesMoratorios;
	}

	public void setImpulsoInteresesMoratorios(String impulsoInteresesMoratorios) {
		this.impulsoInteresesMoratorios = impulsoInteresesMoratorios;
	}

	public String getFijoIntereses() {
		return fijoIntereses;
	}

	public void setFijoIntereses(String fijoIntereses) {
		this.fijoIntereses = fijoIntereses;
	}

	public String getPagosRecibidos() {
		return pagosRecibidos;
	}

	public void setPagosRecibidos(String pagosRecibidos) {
		this.pagosRecibidos = pagosRecibidos;
	}

	public String getSaldo_disponible() {
		return saldo_disponible;
	}
	
	public void setSaldo_disponible(String saldo_disponible) {
		this.saldo_disponible = saldo_disponible;
	}
	
	public String getDepositos_mes() {
		return depositos_mes;
	}
	
	public void setDepositos_mes(String depositos_mes) {
		this.depositos_mes = depositos_mes;
	}
	
	public String getIntereses_cobrados() {
		return intereses_cobrados;
	}
	
	public void setIntereses_cobrados(String intereses_cobrados) {
		this.intereses_cobrados = intereses_cobrados;
	}
	
	public String getSaldo_total() {
		return saldo_total;
	}
	
	public void setSaldo_total(String saldo_total) {
		this.saldo_total = saldo_total;
	}

	public String getGlobalEfectivoDisponible() {
		return globalEfectivoDisponible;
	}

	public void setGlobalEfectivoDisponible(String globalEfectivoDisponible) {
		this.globalEfectivoDisponible = globalEfectivoDisponible;
	}

	public String getGlobalInversionesProceso() {
		return globalInversionesProceso;
	}

	public void setGlobalInversionesProceso(String globalInversionesProceso) {
		this.globalInversionesProceso = globalInversionesProceso;
	}		

	public String getNotes_investment_process() {
		return notes_investment_process;
	}

	public void setNotes_investment_process(String notes_investment_process) {
		this.notes_investment_process = notes_investment_process;
	}

	public String getImpulsoInversionesActivas() {
		return impulsoInversionesActivas;
	}

	public void setImpulsoInversionesActivas(String impulsoInversionesActivas) {
		this.impulsoInversionesActivas = impulsoInversionesActivas;
	}

	public String getNumber_notes_active() {
		return number_notes_active;
	}

	public void setNumber_notes_active(String number_notes_active) {
		this.number_notes_active = number_notes_active;
	}

	public String getImpulsoInteresesCobrar() {
		return impulsoInteresesCobrar;
	}

	public void setImpulsoInteresesCobrar(String impulsoInteresesCobrar) {
		this.impulsoInteresesCobrar = impulsoInteresesCobrar;
	}

	public String getFijoInversionesActivas() {
		return fijoInversionesActivas;
	}

	public void setFijoInversionesActivas(String fijoInversionesActivas) {
		this.fijoInversionesActivas = fijoInversionesActivas;
	}

	public String getFijoInteresesDevengados() {
		return fijoInteresesDevengados;
	}

	public void setFijoInteresesDevengados(String fijoInteresesDevengados) {
		this.fijoInteresesDevengados = fijoInteresesDevengados;
	}

	public String getCuentasSaldoTotal() {
		return cuentasSaldoTotal;
	}

	public void setCuentasSaldoTotal(String cuentasSaldoTotal) {
		this.cuentasSaldoTotal = cuentasSaldoTotal;
	}
}
