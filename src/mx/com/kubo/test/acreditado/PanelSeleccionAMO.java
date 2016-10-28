package mx.com.kubo.test.acreditado;

import java.util.Calendar;

import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMP;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;

@SuppressWarnings("serial")
public abstract class PanelSeleccionAMO extends PanelSeleccionDMO
{
	protected void asignar_default_selected(int index) 
	{
		acreditado_selected = lista_acreditados.get(index);
		
		prospectus_id = acreditado_selected.getMembershipPK().getProspectus_id();
		company_id    = acreditado_selected.getMembershipPK().getCompany_id();
		
		lista_proyectos = service_proyect.loadProyectListByProspect(acreditado_selected.getPerson().getProspectus());
		
		ultimo_registro   = lista_proyectos.size() - 1;		
		proyecto_selected = lista_proyectos.get(ultimo_registro);
				
		lista_proyect_loan = service_proyect_loan.getProyectLoanListByProspectus(prospectus_id, company_id);
		
		ultimo_registro       = lista_proyect_loan.size() - 1;
		proyect_loan_selected = lista_proyect_loan.get(ultimo_registro);
		
		status_id_selected = proyect_loan_selected.getStatus_id().toString();
		lista_motivos = service_motivos.getMotiveStatusListByStatus(proyect_loan_selected.getStatus_id());
		
		asignar_service_casos_pospuestos(proyect_loan_selected);
		
		descripcion_motivo = DEFAULT_DESCRIPCION;
	}

	protected void asignar_service_casos_pospuestos(ProyectLoan selected) 
	{
		if(selected.getPosposed_date() != null)
		{
			Calendar today       = Calendar.getInstance();
			Calendar fecha_leida = Calendar.getInstance();
			
			fecha_leida.setTime(selected.getPosposed_date());

			System.out.println("setFechaPospuestaValida: " + fecha_leida.after(today));	
			
			estatus_ORIGINAL = EstatusProyectLoan.getInstance(selected.getStatusProyect().getStatusPK().getStatus_id());
			
			caso_pospuesto = new CasosPospuestosIMP(estatus_ORIGINAL, selected.getPosposed_date());
		}				
	}
}
