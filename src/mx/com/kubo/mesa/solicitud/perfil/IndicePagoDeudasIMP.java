package mx.com.kubo.mesa.solicitud.perfil;

import javax.faces.context.FacesContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.ProyectLoanPK;

public class IndicePagoDeudasIMP extends IndicePagoDeudasDMO
{	
	public void init()
	{			
		if(asignar_proyect_loan_actual())
		{		
			asignar_lista_egresos_ingresos();
			
			asignar_cuota_acreditado();
			asignar_cuota_mesa_control();
			
			asignar_ingresos_acreditado();
			asignar_ingresos_mesa_control();
			
			asignar_monto_deudas_acreditado();
			asignar_monto_deudas_mesa_control() ;
					
			asignar_indice_pago_deudas_acreditado();
			asignar_indice_pago_deudas_mesa_control();
		}
	}
	
	private boolean asignar_proyect_loan_actual() 
	{		
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		        
        session_sumary = (SearchSummaySession) resolver.getValue(context, null, "searchSummaySession");
        cadenaProyecto = session_sumary.getSearchSummary();
        
		if(cadenaProyecto.split("::").length > 2)
		{						
			proyect_loan_id = Integer.parseInt(cadenaProyecto.split("::")[0]);
			proyect_id      = Integer.parseInt(cadenaProyecto.split("::")[1]);
			prospectus_id   = Integer.parseInt(cadenaProyecto.split("::")[2]);
			company_id      = Integer.parseInt(cadenaProyecto.split("::")[3]);						
			
			proyect_loan_PK = new ProyectLoanPK(proyect_loan_id, proyect_id, prospectus_id, company_id);
			
			proyect_loan_actual = service_proyect_loan.findProyect(proyect_loan_PK);
			loan_negotiation    = service_loan_negotiation.loadMaxLoanNegotiation(prospectus_id, company_id, proyect_loan_id, proyect_id);
			
			bandera = true;
		} else {
			
			bandera = false;
		}
		
		return bandera;
	}
	
	private void asignar_lista_egresos_ingresos() 
	{
		prospectus_id = proyect_loan_actual.getProyectloanPk().getProspectus_id();
		company_id    = proyect_loan_actual.getProyectloanPk().getCompany_id();
		
		lista_egresos  = service_egresos.getListExpensesByProspect(prospectus_id, company_id);
		lista_ingresos = service_ingresos.getListIncomeByProspect(prospectus_id, company_id);
	}

	private void asignar_cuota_acreditado() 
	{
		cuota_acreditado = proyect_loan_actual.getPayment();
		
		switch (proyect_loan_actual.getFrequency_id())
		{
			case 1://semanal
				cuota_acreditado *= 4;
			break;
			
			case 2://catorcenal
				cuota_acreditado *= 2;
			break;
			
			case 3: //quincenal
				cuota_acreditado *= 2;
			break;
			
			case 4://mensual
			break;
		}
	}
	
	private void asignar_cuota_mesa_control() 
	{
		int frecuencia_pago;
		
		if(loan_negotiation != null && loan_negotiation.getPayment() != null)
		{
			cuota_mesa_control = loan_negotiation.getPayment();	
			frecuencia_pago    = loan_negotiation.getFrequency_id();
			
		} else {			
			cuota_mesa_control = proyect_loan_actual.getPayment();
			frecuencia_pago    = proyect_loan_actual.getFrequency_id();
		}
		
		switch (frecuencia_pago)
		{
			case 1://semanal
				cuota_mesa_control *= 4;
			break;
			
			case 2://catorcenal
				cuota_mesa_control *= 2;
			break;
			
			case 3: //quincenal
				cuota_mesa_control *= 2;
			break;
			
			case 4://mensual
			break;
		}
	}
		
	private void asignar_ingresos_acreditado() 
	{
		monto_negocio = 0D;
		monto_sueldo  = 0D;
		
		for(Income ingreso: lista_ingresos)
		{
			if(ingreso.getIncome_type_id() == NEGOCIO)
			{
				monto_negocio = ingreso.getAmmount();
			}
			
			if(ingreso.getIncome_type_id() == SUELDO_NETO)
			{
				monto_sueldo = ingreso.getAmmount();
			}
		}
		
		ingreso_neto_acreditado = monto_negocio + monto_sueldo;
	}
	
	private void asignar_ingresos_mesa_control()
	{
		monto_negocio = 0D;
		monto_sueldo  = 0D;
		
		for(Income ingreso: lista_ingresos)
		{
			if(ingreso.getIncome_type_id() == NEGOCIO)
			{
				monto_negocio = ingreso.getAmmount_modified() != null ? ingreso.getAmmount_modified() : ingreso.getAmmount();
			}
			
			if(ingreso.getIncome_type_id() == SUELDO_NETO)
			{
				monto_sueldo = ingreso.getAmmount_modified() != null ? ingreso.getAmmount_modified() : ingreso.getAmmount();
			}
		}
		
		ingreso_neto_mesa_control = monto_negocio + monto_sueldo;
	}

 	private void asignar_monto_deudas_acreditado() 
	{
		monto_tanda    = 0D;
		monto_creditos = 0D;
				
		for(Expenses egreso: lista_egresos)
		{
			if(egreso.getExpense_type_id() == TANDA)
			{
				monto_tanda = egreso.getAmmount();
			}
			
			if(egreso.getExpense_type_id() == CREDITOS)
			{
				monto_creditos = egreso.getAmmount();
			}
		}
		
		monto_deudas_acreditado = monto_tanda + monto_creditos + cuota_acreditado;		
	}
	
	private void asignar_monto_deudas_mesa_control() 
	{		
		monto_tanda    = 0D;
		monto_creditos = 0D;
				
		for(Expenses egreso: lista_egresos)
		{
			if(egreso.getExpense_type_id() == TANDA)
			{
				monto_tanda = egreso.getAmmount_modified() != null ? egreso.getAmmount_modified() : egreso.getAmmount();
			}
			
			if(egreso.getExpense_type_id() == CREDITOS)
			{
				monto_creditos = egreso.getAmmount_modified() != null ? egreso.getAmmount_modified() : egreso.getAmmount();
			}
		}
		
		monto_deudas_mesa_control = monto_tanda + monto_creditos + cuota_mesa_control;		
	}
	
	protected final void asignar_indice_pago_deudas_acreditado()
	{		
		if(ingreso_neto_acreditado > 0)
		{
			Double indice = (monto_deudas_acreditado / ingreso_neto_acreditado) * 100;
			
			indice_pago_deudas_acreditado = formatter.format(indice) + " %";
			
		} else {
			
			indice_pago_deudas_acreditado = "No aplica";
		}
	}
	
	protected final void asignar_indice_pago_deudas_mesa_control()
	{
		if(ingreso_neto_mesa_control > 0)
		{
			Double indice = (monto_deudas_mesa_control / ingreso_neto_mesa_control) * 100;
			
			indice_pago_deudas_mesa_control = formatter.format(indice) + " %";
			
			asignar_codigo_color(indice);		
			
		} else {
			
			indice_pago_deudas_mesa_control = "No aplica";
			clase_por_rango_indice          = "mayor_30_porciento";
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
