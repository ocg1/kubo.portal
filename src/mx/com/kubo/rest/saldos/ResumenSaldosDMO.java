package mx.com.kubo.rest.saldos;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;

import mx.com.kubo.model.InfoCalifPorc;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectSaldosEdoCta;
import mx.com.kubo.model.SPProyectActive;
import mx.com.kubo.rest.ResumenSaldos;
import mx.com.kubo.services.ProyectLoanService;

import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse;
import safisrv.ws.InvKuboServicios.ConsultaInverRequest;
import safisrv.ws.InvKuboServicios.ConsultaInverResponse;

public abstract class ResumenSaldosDMO 
implements ResumenSaldosIMO
{	
	protected ProyectLoanService servicioProyecto;	
	//protected NaturalPersonService personaNaturalService;
	
	protected FacesContext faces;
	
	protected ExternalContext external;
	protected ELContext elContext;
	protected ELResolver resolver;		
	
	protected SessionBean sesion;
	
	protected NaturalPerson persona;
//	protected gnNaturalPersonPK key;
	
	protected ConsultaCuentasPorClienteRequest request;
	protected ConsultaCuentasPorClienteResponse replyA;
	protected ConsultaInverRequest request_investment;
	protected ConsultaInverResponse replyB;
	
	protected SPProyectActive activeResume;
	
	protected ProyectSaldosEdoCta saldosEdoCta;
	
	protected ResumenSaldos saldos;
	
	protected List<InfoCalifPorc> lstinfo;		
	
	protected  Locale locale;
	protected  NumberFormat dec;
	
	protected String safi_client_id;
	
	protected String selectedAccount;
	protected String selectedAccountAux;
	protected String cuentaAhoID;		
	protected String montoInversiones;
	protected String intARecibir;
	protected String saldoIntGlobal;
	protected String saldoIntPlazoFijo;
	protected String interesCobrado;
	protected String moraCobrado;
	
	protected String valArrGraphic1;
	protected String valArrGraphic2;
	protected String valArrGraphic4;
	
	protected String client;
	protected String interest_charged;
	protected String total_balance;
	protected String total_payments;
	protected String balances_cash;
	protected String balances_investment_process;
	protected String balances_active_investments;
	protected String in_process_amount;
	protected String broken_amount;
	protected String fully_paid_amount;
	protected String depositos = "0";
	protected String view_delinquentInv;
	protected String viewLabelDelinquent;
	protected String labelDelinquent;
	
	protected Double gat;
	
	protected int number_backward1_15;
	protected int number_backward16_30;
	protected int number_backward31_90;
	protected int number_backward91_120;
	protected int number_backward121_180;
	protected int number_under_warranty;
	protected int number_NoDelinquent;
	protected int number_notes_active;
	protected int notes_cash;
	protected int notes_investment_process;
	protected int notes_accrued_interest;	
	protected int investment_summary;	
	protected int in_process_number;
	protected int notes_active_investments;
	protected int notes_active_investments_cli;
	protected int active_number;
	protected int active_number_cli;
	protected int backward1_15_number;
	protected int backward1_15_number_cli;
	protected int backward16_30_number;	
	protected int backward16_30_number_cli;
	protected int backward31_90_number;	
	protected int backward31_90_number_cli;
	protected int expired91_120_number;
	protected int expired91_120_number_cli;
	protected int expired121_180_number;
	protected int expired121_180_number_cli;
	protected int under_warranty_number_cli;
	protected int under_warranty_number;
	
	protected String saldoVigente0;
	protected String saldoVigente1a15;
	protected String saldoVigente16a30;
	protected String saldoVigente31a90;
	protected String saldoVigente91a120;
	protected String saldoVigente120mas;
	protected String saldoAtrasado0;
	protected String saldoAtrasado1a15;
	protected String saldoAtrasado16a30;
	protected String saldoAtrasado31a90;
	protected String saldoAtrasado91a120;
	protected String saldoAtrasado120mas;
	protected String saldoCapVigTotal;
	protected String saldoCapAtrTotal;
	protected String saldoIntVigente;
	protected String saldoIntAtrasado;
	protected String saldoCapitalCtaOrden;
	protected String saldoInteresCtaOrden;
	protected String saldoTotalCtaOrden;
	
	protected String balances_accrued_interest;
	protected String active_amount;	
	protected String payments_received_amount;	
	protected String capital_amount;	
	protected String ordinary_interests_amount;	
	protected String interest_on_arrears_amount;
	
	protected ArrayList<String> numCuentas;
	protected ArrayList<String> infoCalEst;
	protected ArrayList<String> infoCalPorc;
	
	protected char status_delinquentinv	= 'C';
	
	protected int numInvActivas;
	protected int fully_paid_number;
	protected int payments_received_number;
	protected int capital_number;
	protected int ordinary_interests_number;
	protected int interest_on_arrears_number;
	
	protected ResumenSaldosDMO()
	{
		numCuentas   = new ArrayList<String>();
		infoCalEst   = new ArrayList<String>();
		infoCalPorc  = new ArrayList<String>();
				
		locale = new Locale("es","mx");
		
		dec = NumberFormat.getCurrencyInstance(locale);
	}
		
	public void setServicioProyecto(ProyectLoanService service)
	{
		servicioProyecto = service;
	}
	
	public void setPerson(NaturalPerson person)
	{
		persona = person;				
		
		safi_client_id = persona.getSafi_client_id();		
		
		client = persona.NombreCompletoNPM();
	}	
	
	public ResumenSaldos getResumenSaldos()
	{
		return saldos;
	}
}
