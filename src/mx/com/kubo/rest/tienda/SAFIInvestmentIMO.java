package mx.com.kubo.rest.tienda;

import java.util.Hashtable;
import java.util.List;

import mx.com.kubo.bean.FilterStore;
import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ViewForTiendaExec;
import safisrv.ws.InvKuboServicios.SAFIServicios;

public interface SAFIInvestmentIMO 
{
	boolean isFlagNotRule();
	boolean isFlagMin_E5_E4();
	boolean puedeInvertirEn_F_G( int prospectus_id, int company_id , String safi_account );

	String getScriptStatus();
	Double getPorcMaxSaldoProyF1_G1();

	List<ItemLoanList> getProyectList();

	FilterStore getFilter();

	Hashtable<String, List<ProyectFunding>> gethTFunding();

	Double initMontoDisponibleEn_E5();
	Double getMontoInvertido_F_G();
	//Double getSaldoTotal();
	Double getMontoMaximoPorProyecto_F_G();

	void setSaldoTotal(Double saldoTotal);
	
	void setListInvAccounts(List<InvestorsAccounts> listInvAccounts);
	
	void init();
	//void inicializaSaldos(List<String> sAFI_cuenta);
	void cargaListaTienda( String query, int prospectusInvestor_id, int companyInvestor_id, String flagRisk,String safi_investor_client_id, String safi_account_id );
	void createProyectListView(List<ViewForTiendaExec> temporalProyectListView, int intValue, int intValue2, String cuentaActual);
	void setProyectList(List<ItemLoanList> proyectList);

	List<ItemLoanList> calculaInvestmentBite(int intValue, int intValue2, Double monto_a_invertir, List<ItemLoanList> proyectList);
	//List<InvestorsAccounts> getListInvAccounts();

	SAFIServicios getServicioInvKuboSafi();
	
	void setMontoInvertido_F_G_temp(Double d);	
	void setMonto_a_invertir(Double d);
	void setMonto_a_invertir_temp(Double monto_a_invertir_temp);
	void sethTFunding(Hashtable<String, List<ProyectFunding>> generaHashFunding);
	
	Double getMaxPorcPryG();
	Double getMontoMinF1_G1_G();
	Double getMontoInvertido_F_G_temp();
	Double getMaximoInvBySaldoG();
	Double getMaximoInvBySaldoPryE5();
	Double getMaximoInvBySaldoPryE4();
	Double getPorcent_suma_F1_G1_G();
	Double getLimiteMaximoInversion_F_G();
	Double getMontoMinG();
	Double getMontoMinE4E5G();
	Double getMonto_a_invertir_temp();
	Double getMonto_a_invertir();
	Double getMontoSaldoG();
	Double getMontoDisponibleEn_E5();
	Double getPorcMaxSUMSaldoProyE5();
	Double getInvestmentBiteVAL();
	Double getPorcMaxSaldoG();
	Double getPorcMaxSaldoPryE5G();
	Double getPorcMaxSaldoPryE4G();
}
