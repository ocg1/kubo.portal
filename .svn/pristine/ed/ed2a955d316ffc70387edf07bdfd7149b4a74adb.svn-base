package mx.com.kubo.managedbeans.investor;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.registro.verificacion.CierreDelDiaIMO;
import mx.com.kubo.registro.verificacion.UsuariosFirmadosIMO;
import mx.com.kubo.services.EventTokenAccessService;
import mx.com.kubo.services.SystemParamService;

public abstract class NavigationInvestDMO 
{	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService service_system_param;
	
	@ManagedProperty("#{eventTokenAccessServiceImp}")
	protected EventTokenAccessService event_token_access_service;
	
	protected     CierreDelDiaIMO ejecutor;
	protected UsuariosFirmadosIMO gestor;
	
	protected List<ItemLoanList> proyectlist_to_inv = null;
	
	protected String account_to_inv;	
	protected String safi_client_id;	
	protected String paginaActual = "templates/myInvestments.xhtml";
	protected String paginaActualIntInv ="";	
	protected String paginaActualInt = "";
	
	protected Integer investor_id;
	
	protected int menuInveSel = 1;
	protected int MODIFICAR_DATOS_PERSONALES = 1;
	protected int EJECUCION_CIERRE_DEL_DIA   = 89;
	
	protected boolean displayInfo = true;
	protected boolean displayList = false;
	protected boolean displaySolicitud = false;			
	protected boolean resumenDisp=true;
	protected boolean listProyectDisp=false;
	protected boolean summaryDisp=false;
	protected boolean basicDataDisp=false;
	protected boolean logsDisp=false;
	protected boolean proyectListInvestDisp=false;
	protected boolean cashWithdrawalDisp =false;
	protected boolean flagTokenPersonalData=false;
	protected boolean flagFistTimeLIst = false;	
	protected boolean flagDispEdoCuenta=false;
	protected boolean cierre_del_dia_ENABLED;

	public String getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}
	

	public boolean isDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(boolean displayInfo) {
		this.displayInfo = displayInfo;
	}

	public boolean isDisplayList() {
		return displayList;
	}

	public void setDisplayList(boolean displayList) {
		this.displayList = displayList;
	}

	public boolean isDisplaySolicitud() {
		return displaySolicitud;
	}

	public void setDisplaySolicitud(boolean displaySolicitud) {
		this.displaySolicitud = displaySolicitud;
	}
	
	
	public boolean isResumenDisp() {
		return resumenDisp;
	}

	public boolean isListProyectDisp() {
		return listProyectDisp;
	}

	public boolean isSummaryDisp() {
		return summaryDisp;
	}

	public boolean isBasicDataDisp() {
		return basicDataDisp;
	}

	public void setResumenDisp(boolean resumenDisp) {
		this.resumenDisp = resumenDisp;
	}

	public void setListProyectDisp(boolean listProyectDisp) {
		this.listProyectDisp = listProyectDisp;
	}

	public void setSummaryDisp(boolean summaryDisp) {
		this.summaryDisp = summaryDisp;
	}

	public void setBasicDataDisp(boolean basicDataDisp) {
		this.basicDataDisp = basicDataDisp;
	}
	public boolean isLogsDisp() {
		return logsDisp;
	}
	public void setLogsDisp(boolean logsDisp) {
		this.logsDisp = logsDisp;
	}


	public String getPaginaActualInt() {
		return paginaActualInt;
	}

	public void setPaginaActualInt(String paginaActualInt) {
		this.paginaActualInt = paginaActualInt;
	}

	public boolean isProyectListInvestDisp() {
		return proyectListInvestDisp;
	}

	public void setProyectListInvestDisp(boolean proyectListInvestDisp) {
		this.proyectListInvestDisp = proyectListInvestDisp;
	}

	public String getPaginaActualIntInv() {
		return paginaActualIntInv;
	}

	public void setPaginaActualIntInv(String paginaActualIntInv) {
		this.paginaActualIntInv = paginaActualIntInv;
	}

	public boolean isFlagDispEdoCuenta() {
		return flagDispEdoCuenta;
	}

	public void setFlagDispEdoCuenta(boolean flagDispEdoCuenta) {
		this.flagDispEdoCuenta = flagDispEdoCuenta;
	}

	public boolean isCashWithdrawalDisp() {
		return cashWithdrawalDisp;
	}

	public void setCashWithdrawalDisp(boolean cashWithdrawalDisp) {
		this.cashWithdrawalDisp = cashWithdrawalDisp;
	}

	public List<ItemLoanList> getProyectlist_to_inv() {
		return proyectlist_to_inv;
	}

	public void setProyectlist_to_inv(List<ItemLoanList> proyectlist_to_inv) {
		this.proyectlist_to_inv = proyectlist_to_inv;
	}

	public String getAccount_to_inv() {
		return account_to_inv;
	}

	public void setAccount_to_inv(String account_to_inv) {
		this.account_to_inv = account_to_inv;
	}

	public Integer getInvestor_id() {
		return investor_id;
	}

	public void setInvestor_id(Integer investor_id) {
		this.investor_id = investor_id;
	}

	public String getSafi_client_id() {
		return safi_client_id;
	}

	public void setSafi_client_id(String safi_client_id) {
		this.safi_client_id = safi_client_id;
	}

	public int getMenuInveSel() {
		return menuInveSel;
	}

	public void setMenuInveSel(int menuInveSel) {
		this.menuInveSel = menuInveSel;
	}

	public void setService_system_param(SystemParamService service)
	{
		service_system_param = service;
	}

	public void setEvent_token_access_service(EventTokenAccessService service) 
	{
		event_token_access_service = service;
	}

	public boolean isFlagTokenPersonalData() {
		return flagTokenPersonalData;
	}

	public void setFlagTokenPersonalData(boolean flagTokenPersonalData) {
		this.flagTokenPersonalData = flagTokenPersonalData;
	}

}
