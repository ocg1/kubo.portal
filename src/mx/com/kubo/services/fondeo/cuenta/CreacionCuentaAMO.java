package mx.com.kubo.services.fondeo.cuenta;

import java.util.Date;

import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;
import safisrv.ws.CuentasServicios.AltaCuentaRequest;

public abstract class CreacionCuentaAMO extends CreacionCuentaDMO 
{
	protected final void init_saving_account() 
	{				
		lista_saving_account_ACTIVA = service_saving_account.getListAccountActiveByProspect(prospectus_id, company_id);
		
		if(lista_saving_account_ACTIVA != null && lista_saving_account_ACTIVA.size() > 0)
		{
			saving_account = lista_saving_account_ACTIVA.get(0);
			
		} else {
			
			lista_saving_account = service_saving_account.getListAccountByProspect(prospectus_id, company_id);
			
			if(lista_saving_account == null || lista_saving_account.size() == 0)
			{			
				saving_account_PK = new SavingAccountPK();
				saving_account    = new SavingAccount();
				
				saving_account_PK.setCompany_id(company_id);
				saving_account_PK.setProspectus_id(prospectus_id);

				saving_account.setDescription("Cuenta para pago de préstamo");
				saving_account.setSaving_accountPk(saving_account_PK);
				
				service_saving_account.addSavingAccount(saving_account, prospectus_id, company_id);
				
			} else {
				
				int ultimo = lista_saving_account.size() - 1;
				
				saving_account = lista_saving_account.get(ultimo);
			}
		}
		
		SAFI_account_id = saving_account.getSafi_account_id();								
	}
	
	protected void init_values()
	{
		if((area + "").equals("I"))
		{			
			tipoCuentaID = "1";
			
			if(electronic_statement != null && electronic_statement == 1)
			{
				edoCuenta = "I";
				
			} else {
				
				edoCuenta = "D";
			}
			
			init_mx_clabe();			 
			 
		} else {
			
			tipoCuentaID = "1";
			edoCuenta    = "D";
			clabe        = "072180002608080808";
		}
		
		fechaReg = date_format.format(new Date());
	}
	
	private void init_mx_clabe() 
	{
		lista_bancos = service_clabe_account.loadClabeAccountListByProspectus(prospectus_id, company_id);
		 
		 if(lista_bancos != null && lista_bancos.size() > 0)
		 {
			 if(lista_bancos.get(0).getMx_clabe() != null && lista_bancos.get(0).getMx_clabe().trim().length() > 0)
			 {
				 clabe = lista_bancos.get(0).getMx_clabe();
				 
			 } else {

/*					 
				 lista_errores.add("El cliente no cuenta con cuenta Clabe");
				 clabe = null;
*/					 
				 clabe = "";
			 }
			 
		} else {

/*				
			 lista_errores.add("El cliente no cuenta con cuenta Clabe");
			 clabe = null;
*/				 
			 clabe = "";
		 }	
	}

	protected void init_request() 
	{			
		request = new AltaCuentaRequest();
				
		request.setClabe(clabe);
		request.setClienteID(SAFI_client_id);
		request.setEdoCta(edoCuenta);///pendiente captura si el estado de cuenta se envia a domicilio o por internet
		request.setEsPrincipal("S");
		request.setEtiqueta("Cuenta para inversión");
		request.setFechaReg(fechaReg);
		request.setInstitucionID("24");
		request.setMonedaID("1");
		request.setSucursalID("1");
		request.setTipoCuentaID(tipoCuentaID);
	}
	
	protected void init_cuenta() 
	{		
		response = service_SAFI.createCuentaSAFI(request, prospectus_id, company_id);
		
		if(response != null)
		{
			codigo_respuesta  = response.getCodigoRespuesta();
			mensaje_respuesta = response.getMensajeRespuesta();
		}		
	}
	
	protected void init_response() 
	{	
		if(response != null)
		{
			if(codigo_respuesta.equals("0"))
			{					
				SAFI_account_id = response.getCuentaAhoID();
				
				saving_account.setSafi_account_id(SAFI_account_id);
				
				service_saving_account.updateSavingAccount(saving_account);
				
				System.out.println("SAFICreacionCuentaAMO.init_cuenta_response(): " + SAFI_account_id);
				
				cuenta_OK = true;
				
			} else {
				
				lista_errores.add(mensaje_respuesta);
				cuenta_OK = false;
			}	
		}
	}
}
