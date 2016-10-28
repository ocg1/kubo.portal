package mx.com.kubo.portal.efectivo;

public final class IndicadoresIMP extends IndicadoresAMO 
implements IndicadoresIMO 
{
	public void init() 
	{					
		init_cuota_control();
		init_cuota_cliente();
		
		init_liquidez_control();
		init_liquidez_cliente();
		
		monto_deudas_cliente += pagoMen;
		monto_deudas_control += pagoMenControl;
		
		init_indice_pago_deudas_acreditado();
		init_indice_pago_deudas_control();		
	}
}
