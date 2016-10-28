package mx.com.kubo.managedbeans.mesa.solicitud.estatus;

public abstract class CasosPospuestosPMO extends CasosPospuestosAMO
{
	protected final void init_calendario()
	{			
		System.out.printf("\nCasosPospuestosAMO.init_calendario()");
		
		switch(estatus)
		{
			case POSPUESTO:
				fecha_ORIGINAL_pospuesta = fecha_ORIGINAL;				
			break;
			
			case PRE_AUTORIZADO:
				fecha_ORIGINAL_pre_autorizacion = fecha_ORIGINAL;				
			break;
			
			default: break;
		}

		init_fecha_POSPUESTO();
		init_fecha_PRE_AUTORIZACION();
		
		init_list_days();
		init_list_of_months();
		init_list_of_years();	
	}
}
