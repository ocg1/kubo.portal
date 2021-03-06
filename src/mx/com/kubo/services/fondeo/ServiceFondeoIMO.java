package mx.com.kubo.services.fondeo;

import java.util.List;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.ProyectLoan;

public interface ServiceFondeoIMO 
{
	void init();
	
	void setSesion(SessionBean sesion);
	
	void setAcreditado(ProyectLoan proyect_loan);
	
	void setLista_errores(List<String> lista_errores);
	
	void setProspectus_id(int prospectus_id);
	void setCompany_id   (int company_id);;
	
	List<String> getLista_errores() ;
	
	String getSAFI_client_id();
	String getSAFI_account_id();
	
	Integer getSAFI_prospectus_id();
	
	boolean isAlta_prospecto_OK();
	boolean isSolicitud_credito_OK();
	boolean isAlta_cliente_OK();	
	boolean isAlta_cliente_ENABLED();
	boolean isCreacion_credito_OK();
	boolean isCreacion_seguro_OK();
	boolean isCuenta_OK();
	
}
