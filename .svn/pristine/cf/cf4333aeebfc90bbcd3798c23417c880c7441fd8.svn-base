package mx.com.kubo.portal.efectivo;

public abstract class IndicadoresAMO extends IndicadoresDMO 
{
	protected void init_cuota_control() 
	{
		if(negotiation != null)
		{
			frequency_id = negotiation.getFrequency_id();
			payment      = negotiation.getPayment();
			
		} else {
			
			frequency_id = proyect_loan.getFrequency_id();
			payment      = proyect_loan.getPayment();
		}
		
		pagoMenControl = payment;
			
		switch (frequency_id)
		{
			case SEMANAL:
				pagoMenControl = pagoMenControl * 4;
			break;
			
			case CATORCENAL:
				pagoMenControl = pagoMenControl * 2;
			break;
			
			case QUINCENAL:
				pagoMenControl = pagoMenControl * 2;
			break;
			
			case MENSUAL: break;
		}
	}
	
	protected void init_cuota_cliente() 
	{
		frequency_id = proyect_loan.getFrequency_id();
		payment      = proyect_loan.getPayment();
		
		switch (frequency_id)
		{
			case SEMANAL:
				pagoMen = pagoMen * 4;
			break;
			
			case CATORCENAL:
				pagoMen = pagoMen * 2;
			break;
			
			case QUINCENAL:
				pagoMen = pagoMen * 2;
			break;
			
			case MENSUAL: break;
		}
	}
	
	protected void init_liquidez_control() 
	{
		excedenteControl = totalIncomeControl - totalExpensesControl;
		
		if(ammountConsolidate.getAmmount() != null && ammountConsolidate.getAmmount() > 0)
		{
			ammountConsolidate.setExcedentConsolidate(excedenteControl + ammountConsolidate.getAmmount());
			
			liquidezCliControl = ammountConsolidate.getExcedentConsolidate() / pagoMenControl;
			
		} else {
			
			ammountConsolidate.setExcedentConsolidate(excedenteControl + ammountConsolidate.getAmmount());
			
			liquidezCliControl = excedenteControl / pagoMenControl;
		}
		
		liquidezCliControl = (double) Math.round((liquidezCliControl) * 100) / 100;
		
		if(liquidezReq != null && liquidezReq < liquidezCliControl)
		{
			  dispOKControl = true;
			dispWarnControl = false;
			
		} else {
			
			  dispOKControl = false;
			dispWarnControl = true;
		}	
	}
	
	protected void init_liquidez_cliente()
	{
		excedente = totalIncome - totalExpenses;	
		
		liquidezCli = excedente / pagoMen;
		
		liquidezCli = (double) Math.round((liquidezCli) * 100) / 100;
		
		if(liquidezReq != null && liquidezReq < liquidezCli)
		{
			dispOKCl   = true;
			dispWarnCl = false;
			
		} else {
			
			dispOKCl   = false;
			dispWarnCl = true;
		}		
	}
	
	protected void init_indice_pago_deudas_acreditado()
	{		
		if(ingreso_neto_cliente > 0)
		{
			Double indice = (monto_deudas_cliente / ingreso_neto_cliente) * 100;
			
			indice_pago_deudas_cliente = formatter.format(indice) + " %";
			
		} else {
			
			indice_pago_deudas_cliente = "No aplica";
		}
	}
	
	protected void init_indice_pago_deudas_control()
	{
		if(ingreso_neto_control > 0)
		{
			Double indice = (monto_deudas_control / ingreso_neto_control) * 100;
			
			indice_pago_deudas_control = formatter.format(indice) + " %";
			
			asignar_codigo_color(indice);		
			
		} else {
			
			indice_pago_deudas_control = "No aplica";
			
			clase_por_rango_indice = "mayor_30_porciento";
		}
	}
	
	private void asignar_codigo_color(Double indice) 
	{
		if(indice < 25)
		{
			clase_por_rango_indice = "menor_25_porciento";
		}
		
		if(indice >= 25 && indice < 30)
		{
			clase_por_rango_indice = "mayor_25_menor_30_porciento";
		}
		
		if(indice >=  30)
		{
			clase_por_rango_indice = "mayor_30_porciento";
		}		
	}
}
