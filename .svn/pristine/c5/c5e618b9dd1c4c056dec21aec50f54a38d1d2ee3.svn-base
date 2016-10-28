package mx.com.kubo.portal.efectivo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.ProyectLoan;

public abstract class IndicadoresDMO 
implements IndicadoresIMO
{	
	protected ProyectLoan proyect_loan;
	protected ExpensesBean ammountConsolidate;
	protected LoanNegotiation negotiation;
	
	private DecimalFormatSymbols symbols;
	protected NumberFormat formatter; 
	
	protected String clase_por_rango_indice;
	protected String indice_pago_deudas_cliente;
	protected String indice_pago_deudas_control;
	
	protected Double excedente;
	protected Double excedenteControl=0D;
	
	protected Double liquidezReq;
	protected Double liquidezCli;
	protected Double liquidezCliControl=0D;
	
	protected Double totalIncome;
	protected Double totalExpenses;
	protected Double totalIncomeControl=0D;
	protected Double totalExpensesControl=0D;
	
	protected Double pagoMen;	
	protected Double pagoMenControl;
	protected Double payment;	
	
	protected Double ingreso_neto_cliente;
	protected Double ingreso_neto_control;
	protected Double monto_deudas_cliente;
	protected Double monto_deudas_control;
	
	protected int frequency_id;
	
	protected final int SEMANAL    = 1;
	protected final int CATORCENAL = 2;
	protected final int QUINCENAL  = 3;
	protected final int MENSUAL    = 4;
	
	protected boolean dispOKCl;
	protected boolean dispWarnCl;
	protected boolean dispOKControl;
	protected boolean dispWarnControl;
	
	protected IndicadoresDMO()
	{
		symbols = new DecimalFormatSymbols(new Locale("es", "mx"));
		
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');
		
		formatter = new DecimalFormat("#0.00", symbols);
	}
	
	public void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		pagoMen     = proyect_loan.getPayment();
		liquidezReq = proyect_loan.getLiquidity() == null ? 2.5D : proyect_loan.getLiquidity();
	}

	public void setNegotiation(LoanNegotiation negotiation) 
	{
		this.negotiation = negotiation;
	}

	public void setAmmountConsolidate(ExpensesBean ammountConsolidate) 
	{
		this.ammountConsolidate = ammountConsolidate;
	}
	
	public void setIngreso_neto_cliente(Double ingreso_neto_cliente) 
	{
		this.ingreso_neto_cliente = ingreso_neto_cliente;
	}

	public void setIngreso_neto_control(Double ingreso_neto_control) 
	{
		this.ingreso_neto_control = ingreso_neto_control;
	}

	public void setMonto_deudas_cliente(Double monto_deudas_cliente) 
	{
		this.monto_deudas_cliente = monto_deudas_cliente;
	}

	public void setMonto_deudas_control(Double monto_deudas_control) 
	{
		this.monto_deudas_control = monto_deudas_control;
	}

	public void setTotalIncome(Double totalIncome) 
	{
		this.totalIncome = totalIncome;
	}

	public void setTotalExpenses(Double totalExpenses) 
	{
		this.totalExpenses = totalExpenses;
	}

	public void setTotalIncomeControl(Double totalIncomeControl) 
	{
		this.totalIncomeControl = totalIncomeControl;
	}
	
	public void setTotalExpensesControl(Double totalExpensesControl) 
	{
		this.totalExpensesControl = totalExpensesControl;
	}

	public ExpensesBean getAmmountConsolidate() 
	{
		return ammountConsolidate;
	}
	
	public String getClase_por_rango_indice() 
	{
		return clase_por_rango_indice;
	}

	public String getIndice_pago_deudas_cliente() 
	{
		return indice_pago_deudas_cliente;
	}

	public String getIndice_pago_deudas_control() 
	{
		return indice_pago_deudas_control;
	}

	public Double getIngreso_neto_cliente() 
	{
		return ingreso_neto_cliente;
	}

	public Double getIngreso_neto_control() 
	{
		return ingreso_neto_control;
	}

	public Double getMonto_deudas_cliente() 
	{
		return monto_deudas_cliente;
	}

	public Double getMonto_deudas_control() 
	{
		return monto_deudas_control;
	}

	public Double getPagoMen() 
	{
		return pagoMen;
	}
	
	public Double getPagoMenControl() 
	{
		return pagoMenControl;
	}
	
	public Double getExcedente() 
	{
		return excedente;
	}
	
	public Double getExcedenteControl() 
	{
		return excedenteControl;
	}

	public Double getLiquidezReq() 
	{
		return liquidezReq;
	}
	
	public Double getLiquidezCli() 
	{
		return liquidezCli;
	}
	
	public Double getLiquidezCliControl() 
	{
		return liquidezCliControl;
	}
	
	public boolean isDispOKCl() 
	{
		return dispOKCl;
	}

	public boolean isDispWarnCl() 
	{
		return dispWarnCl;
	}

	public boolean isDispOKControl() 
	{
		return dispOKControl;
	}

	public boolean isDispWarnControl() 
	{
		return dispWarnControl;
	}
}
