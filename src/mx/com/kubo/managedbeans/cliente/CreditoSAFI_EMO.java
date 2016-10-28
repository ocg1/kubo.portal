package mx.com.kubo.managedbeans.cliente;

import com.soa.model.businessobject.TSafiPosicionInt;

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
		setCredito_fecha_ultimo_pago 			   (credito_safi.getFechaultimopago());
		setCredito_amortizacion_id                 (credito_safi.getAmortizacionid());
		setCredito_cuota						   (credito_safi.getCuota());
		setCredito_cuota_mas_comision              (credito_safi.getCuota_mas_com());
		setCredito_fecha_inicio                    (credito_safi.getFechainicio());
		setCredito_fecha_prox_ven                  (credito_safi.getFecprocven());
		setCredito_fecha_ven                       (credito_safi.getFechavencimien());
		setCredito_id                              (credito_safi.getCreditoid());
		setCredito_monto                           (credito_safi.getMontocredito());			
		setCredito_no_amortizacion                 (credito_safi.getNumamortizacion());
		setCredito_saldo_capital_atrasado          (credito_safi.getSaldocapatrasad());
		setCredito_saldo_capital_vigent            (credito_safi.getSaldocapvigent());
		setCredito_saldo_capital_vigente           (credito_safi.getSaldocapvigente());
		setCredito_saldo_capital_vencido           (credito_safi.getSaldocapvencido());
		setCredito_saldo_capital_vencido_no_exigle (credito_safi.getSaldcapvennoexi());
		setCredito_saldo_com_falta_pago            (credito_safi.getSaldcomfaltpago());			
		setCredito_saldo_interes_atrasado          (credito_safi.getSaldointeratras());
		setCredito_saldo_interes_provisional       (credito_safi.getSaldointerprovi());
		setCredito_saldo_interes_no_contable       (credito_safi.getSaldoIntNoConta());
		setCredito_saldo_interes_vencido           (credito_safi.getSaldointervenc());
		setCredito_saldo_interes_pro               (credito_safi.getSaldointerespro());	
		setCredito_saldo_moratorio                 (credito_safi.getSaldomoratorios());
		setCredito_tasa_fija                       (credito_safi.getTasafija());
		setCredito_valor_cat                       (credito_safi.getValorcat());
	}
}
