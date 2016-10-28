package mx.com.kubo.managedbeans.cliente;

import java.util.List;

import mx.com.kubo.bean.TAmortizacionBean;
import mx.com.kubo.model.CollectionRelationship;
import mx.com.kubo.model.RestructureBean;
import mx.com.kubo.referencia_pago_panel.PanelAMO;

public class CreditoEMO 
{	
	private RestructureBean        proyect;
	private CollectionRelationship collectionrelationship;
	private PanelAMO 				panel;
	
	protected String cat;
	protected String comisiones; 
	protected String comisionesGenerados;
	protected String contDesMonto; 
	protected String contDesTotal;
	protected String fecInicio; 
	protected String fecFin;
	protected String fecProxCuota; 
	protected String fecProxCuotaResumen;
	protected String interesesCobrados;
	protected String interesesGenerados;
	protected String ivaGenerado;
	protected String monto; 
	protected String montoProxCuota; 
	protected String numCredito; 
	protected String numCuotas;
	protected String plazo;
	protected String proyect_id; 
	protected String proyect_loan_id;
	protected String status; 
	protected String saldoLiquidar;	
	protected String saldoInsoluto; 
	protected String saldoCapVen;		
	protected String scriptGraphic;
	protected String tasa; 
	protected String tasaMensual; 
	protected String tasaMora; 
	protected String tasaMoraMensual;

	private boolean hasCollection;
	private boolean panel_enabled;
	private boolean saldo_liquidar_ENABLED;
	
	public boolean isPanel_enabled() {
		return panel_enabled;
	}

	public void setPanel_enabled(boolean panel_enabled) {
		this.panel_enabled = panel_enabled;
	}

	public final boolean isSaldo_liquidar_ENABLED() {
		return saldo_liquidar_ENABLED;
	}

	public final void setSaldo_liquidar_ENABLED(boolean saldo_liquidar_ENABLED) {
		this.saldo_liquidar_ENABLED = saldo_liquidar_ENABLED;
	}

	public RestructureBean getProyect() {
		return proyect;
	}

	public void setProyect(RestructureBean proyect) {
		this.proyect = proyect;
	}

	private List<TAmortizacionBean> tablaAmort;
	
	
	public String getNumCredito() {
		return numCredito;
	}
	
	public void setNumCredito(String numCredito) {
		this.numCredito = numCredito;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getFecProxCuota() {
		return fecProxCuota;
	}
	
	public void setFecProxCuota(String fecProxCuota) 
	{
		this.fecProxCuota = fecProxCuota;
	}
	
	public String getMontoProxCuota() {
		return montoProxCuota;
	}
	
	public void setMontoProxCuota(String montoProxCuota) {
		this.montoProxCuota = montoProxCuota;
	}
	
	public String getTasa() {
		return tasa;
	}
	
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	
	public String getCat() {
		return cat;
	}
	
	public void setCat(String cat) {
		this.cat = cat;
	}
	
	public String getSaldoLiquidar() {
		return saldoLiquidar;
	}
	
	public void setSaldoLiquidar(String saldoLiquidar) {
		this.saldoLiquidar = saldoLiquidar;
	}

	public List<TAmortizacionBean> getTablaAmort() {
		return tablaAmort;
	}
	
	public void setTablaAmort(List<TAmortizacionBean> tablaAmort) {
		this.tablaAmort = tablaAmort;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public String getNumCuotas() {
		return numCuotas;
	}

	public void setNumCuotas(String numCuotas) {
		this.numCuotas = numCuotas;
	}

	public String getFecInicio() {
		return fecInicio;
	}

	public void setFecInicio(String fecInicio) {
		this.fecInicio = fecInicio;
	}

	public String getFecFin() {
		return fecFin;
	}

	public void setFecFin(String fecFin) {
		this.fecFin = fecFin;
	}

	public String getContDesMonto() {
		return contDesMonto;
	}

	public void setContDesMonto(String contDesMonto) {
		this.contDesMonto = contDesMonto;
	}

	public String getContDesTotal() {
		return contDesTotal;
	}

	public void setContDesTotal(String contDesTotal) {
		this.contDesTotal = contDesTotal;
	}

	public String getProyect_id() {
		return proyect_id;
	}

	public void setProyect_id(String proyect_id) {
		this.proyect_id = proyect_id;
	}

	public String getProyect_loan_id() {
		return proyect_loan_id;
	}

	public void setProyect_loan_id(String proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}

	public String getTasaMora() {
		return tasaMora;
	}

	public void setTasaMora(String tasaMora) {
		this.tasaMora = tasaMora;
	}

	public String getComisiones() {
		return comisiones;
	}

	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}

	public String getFecProxCuotaResumen() {
		return fecProxCuotaResumen;
	}

	public void setFecProxCuotaResumen(String fecProxCuotaResumen) {
		this.fecProxCuotaResumen = fecProxCuotaResumen;
	}

	public String getInteresesCobrados() {
		return interesesCobrados;
	}

	public void setInteresesCobrados(String interesesCobrados) {
		this.interesesCobrados = interesesCobrados;
	}

	public String getSaldoInsoluto() {
		return saldoInsoluto;
	}

	public void setSaldoInsoluto(String saldoInsoluto) {
		this.saldoInsoluto = saldoInsoluto;
	}

	public String getInteresesGenerados() {
		return interesesGenerados;
	}

	public void setInteresesGenerados(String interesesGenerados) {
		this.interesesGenerados = interesesGenerados;
	}

	public String getIvaGenerado() {
		return ivaGenerado;
	}

	public void setIvaGenerado(String ivaGenerado) {
		this.ivaGenerado = ivaGenerado;
	}

	public String getComisionesGenerados() {
		return comisionesGenerados;
	}

	public void setComisionesGenerados(String comisionesGenerados) {
		this.comisionesGenerados = comisionesGenerados;
	}

	public String getSaldoCapVen() {
		return saldoCapVen;
	}

	public void setSaldoCapVen(String saldoCapVen) {
		this.saldoCapVen = saldoCapVen;
	}

	public String getScriptGraphic() {
		return scriptGraphic;
	}

	public void setScriptGraphic(String scriptGraphic) {
		this.scriptGraphic = scriptGraphic;
	}

	public String getTasaMensual() {
		return tasaMensual;
	}

	public void setTasaMensual(String tasaMensual) {
		this.tasaMensual = tasaMensual;
	}

	public String getTasaMoraMensual() {
		return tasaMoraMensual;
	}

	public void setTasaMoraMensual(String tasaMoraMensual) {
		this.tasaMoraMensual = tasaMoraMensual;
	}

	public CollectionRelationship getCollectionrelationship() {
		return collectionrelationship;
	}

	public void setCollectionrelationship(
			CollectionRelationship collectionrelationship) {
		this.collectionrelationship = collectionrelationship;
	}

	public final PanelAMO getPanel() 
	{
		return panel;
	}

	public final void setReferenciaPagoPanel(PanelAMO panel) 
	{
		this.panel = panel;
	}	

	public boolean isHasCollection() {
		return hasCollection;
	}

	public void setHasCollection(boolean hasCollection) {
		this.hasCollection = hasCollection;
	}
	
}
