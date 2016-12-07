package mx.com.kubo.rest;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.services.ProyectLoanService;

public abstract class ResumenSaldosDMO 
{
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService servicioProyecto;
	
	protected NaturalPerson persona;
	
	protected  Locale locale;
	protected  NumberFormat dec;
	
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
	
	protected Double gat;
	
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
	
	protected ArrayList<String> infoCalEst;
	protected ArrayList<String> infoCalPorc;
	
	protected int numInvActivas;
	protected int fully_paid_number;
	protected int payments_received_number;
	protected int capital_number;
	protected int ordinary_interests_number;
	protected int interest_on_arrears_number;
		
	public void setServicioProyecto(ProyectLoanService service)
	{
		servicioProyecto = service;
	}
}
