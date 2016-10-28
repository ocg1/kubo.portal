package mx.com.kubo.mesa.solicitud.perfil;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.services.ExpensesService;
import mx.com.kubo.services.IncomeService;
import mx.com.kubo.services.LoanNegotiationService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.tools.Utilities;

public abstract class IndicePagoDeudasDMO 
{
	protected ProyectLoanService     service_proyect_loan;
	protected LoanNegotiationService service_loan_negotiation;
	protected ExpensesService        service_egresos;
	protected IncomeService          service_ingresos;
	
	protected FacesContext        faces;
	protected ELContext           context;
	protected ELResolver          resolver;			
	protected SearchSummaySession session_sumary;
	protected ProyectLoanPK       proyect_loan_PK;
	protected ProyectLoan         proyect_loan_actual;
	protected LoanNegotiation     loan_negotiation;
	
	protected List<Expenses> lista_egresos;
	protected List<Income>   lista_ingresos;
	
	protected NumberFormat formatter; 
	private DecimalFormatSymbols symbols;
	
	protected String cadenaProyecto;
	protected String clase_por_rango_indice;
	
	protected String indice_pago_deudas_acreditado;
	protected String indice_pago_deudas_mesa_control;
	
	protected Double monto_deudas_acreditado;	
	protected Double monto_deudas_mesa_control;
	protected Double ingreso_neto_acreditado;	
	protected Double ingreso_neto_mesa_control;
	
	protected Double monto_tanda;
	protected Double monto_creditos;
	protected Double cuota_acreditado;
	protected Double cuota_mesa_control;
	
	protected Double monto_negocio;
	protected Double monto_sueldo;
	
	protected int proyect_loan_id;
	protected int proyect_id;
	protected int prospectus_id;
	protected int company_id;
	
	protected final int TANDA       = 6;
	protected final int CREDITOS    = 7;
	protected final int NEGOCIO     = 2;
	protected final int SUELDO_NETO = 6;
	
	protected boolean bandera;
	
	protected IndicePagoDeudasDMO()
	{
		symbols = new DecimalFormatSymbols(new Locale("es", "mx"));
		
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');
		
		formatter = new DecimalFormat("#0.00", symbols);
		
		service_proyect_loan     = Utilities.findBean("proyectLoanServiceImp");
		service_loan_negotiation = Utilities.findBean("loanNegotiationServiceImp");
		service_egresos          = Utilities.findBean("expensesServiceImp");
		service_ingresos         = Utilities.findBean("incomeServiceImp");
	}
	
	public void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}
		
	public void setService_loan_negotiation(LoanNegotiationService service) 
	{
		service_loan_negotiation = service;
	}

	public void setService_egresos(ExpensesService service) 
	{
		service_egresos = service;
	}
	
	public void setService_ingresos(IncomeService service) 
	{
		service_ingresos = service;
	}
	
	public Double getIngreso_neto_acreditado() 
	{
		return ingreso_neto_acreditado;
	}		

	public Double getIngreso_neto_mesa_control() 
	{
		return ingreso_neto_mesa_control;
	}

	public Double getMonto_deudas_acreditado() 
	{
		return monto_deudas_acreditado;
	}	

	public Double getMonto_deudas_mesa_control() 
	{
		return monto_deudas_mesa_control;
	}

	public String getIndice_pago_deudas_acreditado() 
	{
		return indice_pago_deudas_acreditado;
	}
		
	public String getIndice_pago_deudas_mesa_control() 
	{
		return indice_pago_deudas_mesa_control;
	}

	public String getClase_por_rango_indice() 
	{
		return clase_por_rango_indice;
	}	
}
