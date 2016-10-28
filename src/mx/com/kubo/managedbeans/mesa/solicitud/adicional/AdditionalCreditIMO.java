package mx.com.kubo.managedbeans.mesa.solicitud.adicional;

import javax.faces.event.ActionEvent;

import mx.com.kubo.model.ProyectLoan;

public interface AdditionalCreditIMO 
{
	void init(ProyectLoan proyect_loan_ACTUAL);
	
	void cargar_solicitud_de_credito(ActionEvent evento_AJAX);
	
	void renovar_solicitud_de_credito();
	void generaSolicitud();
}
