package mx.com.kubo.mesa.solicitud.adicional;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.solicitud.adicional.TipoCreditoAdicional;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;

public interface ReasignadorIMO 
{
	void setMax_payment_ENABLED(boolean max_payment_ENABLED);
	
	void init(ProyectLoan proyect_loan_ACTUAL);
	
	void setProyect_loan_reasignable(ProyectLoan actual);
	void setProyect_loan_NEW( ProyectLoan actualProyect);
	
	void renovar_solicitud_de_credito(TipoCreditoAdicional tipo);
	
	void crear_nuevo_proyecto(TipoCreditoAdicional tipo_credito_adicional, int loan_type);
	
	void init_renovacion_aprobacion_automatica(String loan_type);
	
	void crear_lista_documentos(boolean validacion_de_vigencia);
	
	void copiar_documentos();
	
	boolean copiar_archivos(DocumentationDMO documento);
	
	boolean callSGB(Proyect proyect, ProyectLoan proyect_loan);
	
	boolean isNew_proyect_OK();
	
	void setSesionBean(SessionBean sesion);
	
}
