package mx.com.kubo.rest.tienda.accounts;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.SaldoInversionista;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SaldoInversionistaService;
import mx.com.kubo.tools.Utilities;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse;
import safisrv.ws.CuentasServicios.SAFIServicios;
import safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator;

public abstract class CuentasClienteDMO implements CuentasClienteIMO
{
	protected SAFIServiciosServiceLocator locatorAccount ;
	protected SAFIServicios servCuentasCliente;
	
	protected        ProyectLoanService proyectLoanService;
	protected SaldoInversionistaService saldoinversionistaservice;
	
	protected ConsultaCuentasPorClienteRequest request;
	protected ConsultaCuentasPorClienteResponse response;
	
	protected SaldoInversionista saldoObj;
	protected InvestorsAccounts account;
	
	protected List<InvestorsAccounts> listInvAccounts;
	
	protected List<String> SAFI_cuenta;
	
	protected String[] cuentas;
	
	protected String accountList;
	
	protected Double saldoTotal	= 0D;
	
	protected Integer prospectus_id;
	
	protected boolean response_OK;
	
	protected CuentasClienteDMO()
	{
		try
		{
			saldoTotal = 0D;
			
			listInvAccounts	= new ArrayList<InvestorsAccounts>();
			
			saldoinversionistaservice = Utilities.findBean("saldoInversionistaServiceImp");
			proyectLoanService = Utilities.findBean("proyectLoanServiceImp");
			
			locatorAccount = new SAFIServiciosServiceLocator();
			servCuentasCliente = locatorAccount.getSAFIServiciosSoap11();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
    public void setSesion(SessionBean sesion)
    {    	    	
    	prospectus_id = sesion.getProspectus_id();
    }
    
	public Double getSaldoTotal() 
	{
		return saldoTotal;
	}
	
	public List<InvestorsAccounts> getListInvAccounts()
	{
		return listInvAccounts;
	}
}
