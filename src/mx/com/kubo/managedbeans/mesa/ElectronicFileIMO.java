package mx.com.kubo.managedbeans.mesa;

import java.util.List;

import javax.faces.event.ActionEvent;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.mesa.solicitud.PldNotification;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;

public interface ElectronicFileIMO 
{
	void search_expedient(ActionEvent event);
	void loadDocumentation();
	
	List<ClientViewFullName> completeinfoclient(String strSearch);
	List<PldNotification> getLista_pld_notification();
	
	ProyectLoan getProyectLoan();
	
	String getTitleStr();
	String getContMsgStr();
	
	boolean isInclude_disp();
	boolean isCreden_fm2_dip();
	boolean isMsg_disp();
	boolean isDispListSIC();
	boolean isCapacidad_pago_disp();
	boolean isDoc_address_disp();
	boolean isDoc_contracts_disp();
}
