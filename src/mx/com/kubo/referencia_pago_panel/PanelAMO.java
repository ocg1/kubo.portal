package mx.com.kubo.referencia_pago_panel;

import mx.com.kubo.referencia_pago.Nota;
import mx.com.kubo.referencia_pago.Paso;
import mx.com.kubo.referencia_pago.ReferenciaPago;

public abstract class PanelAMO extends PanelDMO
{	
	protected void asignar(ReferenciaPago referencia)
	{
		switch (referencia) 
		{
			case BANORTE:
				entity = lista_referencias_tmp.get(referencia.getOrden_ASC());
				
				if(cuota != null)
				{
					pago = Double.parseDouble(cuota) + entity.getComision();
					
				} else {
					
					pago = 0D;
				}
				
				banorte.setUrl_logo(entity.getUrl_logo());
				banorte.setNo_empresa(entity.getMx_numero_empresa());
				banorte.setNo_referencia(entity.getReferencia());	
				banorte.setPago(currency.format(pago));
				
				lista_referencias.add(banorte);
			break;
			
			case BANAMEX:
				entity = lista_referencias_tmp.get(referencia.getOrden_ASC());
				
				if(cuota != null)
				{
					pago = Double.parseDouble(cuota) + entity.getComision();
					
				} else {
					
					pago = 0D;
				}
				
				banamex.setUrl_logo(entity.getUrl_logo());
				banamex.setSucursal(entity.getMx_numero_empresa());
				banamex.setNo_cuenta(entity.getNo_cuenta());		
				banamex.setNo_referencia(entity.getReferencia());
				banamex.setPago(currency.format(pago));
				
				lista_referencias.add(banamex);				
			break;
			
			case TELECOMM:
				entity = lista_referencias_tmp.get(referencia.getOrden_ASC());
				
				if(cuota != null)
				{
					pago = Double.parseDouble(cuota) + entity.getComision();
					
				} else {
					
					pago = 0D;
				}
				
				telecomm.setUrl_logo(entity.getUrl_logo());
				telecomm.setSucursal(entity.getMx_numero_empresa());
				telecomm.setNo_cuenta(entity.getNo_cuenta());		
				telecomm.setNo_referencia(entity.getReferencia());
				telecomm.setPago(currency.format(pago));
				
				lista_referencias.add(telecomm);
			break;
			
			case SEVEN_ELEVEN:
				entity = lista_referencias_tmp.get(referencia.getOrden_ASC());
				
				if(cuota != null)
				{
					pago = Double.parseDouble(cuota) + entity.getComision();
					
				} else {
					
					pago = 0D;
				}
				
				seven_eleven.setUrl_logo(entity.getUrl_logo());
				seven_eleven.setPago_servicio(entity.getMx_numero_empresa());		
				seven_eleven.setNo_referencia(entity.getReferencia());	
				seven_eleven.setPago(currency.format(pago));
				
				asignarNota();
				seven_eleven.setNota(nota);
				
				lista_referencias.add(seven_eleven);
			break;
			
			case BANORTE_SPEI:
				entity = lista_referencias_tmp.get(referencia.getOrden_ASC());
				
				if(cuota != null)
				{
					pago = Double.parseDouble(cuota) + entity.getComision();
					
				} else {
					
					pago = 0D;
				}
				
				banorte_SPEI.setUrl_logo(entity.getUrl_logo());
				banorte_SPEI.setCLABE(entity.getClabe_account());		
				banorte_SPEI.setNo_referencia(entity.getReferencia());
				banorte_SPEI.setCliente(acreditado);	
				banorte_SPEI.setPago(currency.format(pago));
				
				lista_referencias_SPEI.add(banorte_SPEI);
			break;
	
			default: break;
		}
	}
		
	protected void asignarNota()
	{
		paso_1 = new Paso("Paso 1", "Buscar en el sistema \"Pago de Servicio\"");
		paso_2 = new Paso("Paso 2", "Ingresar número de convenio ó empresa: 1787");
		paso_3 = new Paso("Paso 3", "Verificar el nombre de la empresa: KU BO FINANCIERO");
		paso_4 = new Paso("Paso 4", "Ingresar monto");
		
		nota   = new Nota("7-eleven cobrará $8 pesos adicionalmente por recibir el pago");
		
		nota.add(paso_1);
		nota.add(paso_2);
		nota.add(paso_3);
		nota.add(paso_4);
	}
}
