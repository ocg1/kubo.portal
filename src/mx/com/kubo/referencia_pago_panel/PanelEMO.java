package mx.com.kubo.referencia_pago_panel;

import static mx.com.kubo.referencia_pago.ReferenciaPago.*;

public final class PanelEMO extends PanelAMO
{
	public final void setListaRefenciasPago()
	{					
		asignar(BANORTE);											
		//asignar(BANAMEX);					
		asignar(TELECOMM);
		asignar(SEVEN_ELEVEN);						
	}	
	
	public final void setListaReferenciasPago_SPEI()
	{
		asignar(BANORTE_SPEI);					
	}
}
