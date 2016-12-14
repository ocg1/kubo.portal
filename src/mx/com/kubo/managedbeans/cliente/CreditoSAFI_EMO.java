package mx.com.kubo.managedbeans.cliente;

import mx.com.kubo.model.TSafiPosicionInt;

public final class CreditoSAFI_EMO extends CreditoSAFI_DMO
{
	public CreditoSAFI_EMO(TSafiPosicionInt credito)
	{
		credito_safi = credito;
		init();
	}
	
	private void init()
	{
		setCredito_estatus           			   (credito_safi.getEstatus());
		setCredito_fecha_ultimo_pago 			   (credito_safi.getFechaUltimoPago()+"");
		setCredito_amortizacion_id                 (credito_safi.getNumAmortizacion()+"");
		setCredito_cuota						   (credito_safi.getCuota()+"");
		setCredito_cuota_mas_comision              (credito_safi.getCuota_mas_com()+"");
		setCredito_fecha_inicio                    (credito_safi.getFechaInicio()+"");
		setCredito_fecha_prox_ven                  (credito_safi.getFecProcVen()+"");
		setCredito_fecha_ven                       (credito_safi.getFechaVencimien()+"");
		setCredito_id                              (credito_safi.getPk().getCreditoId()+"");
		setCredito_monto                           (credito_safi.getMontoCredito()+"");			
		setCredito_no_amortizacion                 (credito_safi.getNumAmortizacion()+"");
		setCredito_saldo_capital_atrasado          (credito_safi.getSaldoCapAtrasad()+"");
		setCredito_saldo_capital_vigent            (credito_safi.getSaldoCapVigent()+"");
		setCredito_saldo_capital_vigente           (credito_safi.getSaldoCapVigente()+"");
		setCredito_saldo_capital_vencido           (credito_safi.getSaldoCapVencido()+"");
		setCredito_saldo_capital_vencido_no_exigle (credito_safi.getSaldoCapVenNoExi()+"");
		setCredito_saldo_com_falta_pago            (credito_safi.getSaldoComFaltaPago()+"");			
		setCredito_saldo_interes_atrasado          (credito_safi.getSaldoInterAtras()+"");
		setCredito_saldo_interes_provisional       (credito_safi.getSaldoInterProvi()+"");
		setCredito_saldo_interes_no_contable       (credito_safi.getSaldoIntNoConta()+"");
		setCredito_saldo_interes_vencido           (credito_safi.getSaldoInterVenc()+"");
		setCredito_saldo_interes_pro               (credito_safi.getSaldoInteresPro()+"");	
		setCredito_saldo_moratorio                 (credito_safi.getSaldoMoratorios()+"");
		setCredito_tasa_fija                       (credito_safi.getTasaFija()+"");
		setCredito_valor_cat                       (credito_safi.getValorCat()+"");
	}
}
