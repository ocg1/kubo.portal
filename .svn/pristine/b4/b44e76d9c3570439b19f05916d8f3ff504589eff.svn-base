
package mx.com.kubo.services.mesa.solicitud.estatus;

import java.text.SimpleDateFormat;

import mx.com.kubo.model.ProyectLoan;

public final class EditorEstatusIMP extends EditorEstatusPMO
implements EditorEstatusIMO
{					
	public final void init_proyect_loan(ProyectLoan proyect_loan)
	{
		actualProyect = proyect_loan;
		
		company_id      = proyect_loan.getProyectloanPk().getCompany_id();
		prospectus_id   = proyect_loan.getProyectloanPk().getProspectus_id();		
		proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
		proyect_id      = proyect_loan.getProyectloanPk().getProyect_id();
		
		acreditado_prospectus_id  = prospectus_id;	
	}
	
	public final boolean guardar_cambio_de_estatus() 
	{				
		formatter_date = new SimpleDateFormat("dd/MM/yyyy");
		fecha_pre_autorizacion     = null;
		fecha_pospuesta            = null;
		motive_catalog_description = "";
		
		asignar_change_control();
								
		cambiar_estatus_solicitud();
						
		if(cambiar_estatus_OK)
		{
			procesar_estatus_solicitud();
			actualizar_proyect_loan();			
		}
		
		return cambiar_estatus_OK;
	}
}
