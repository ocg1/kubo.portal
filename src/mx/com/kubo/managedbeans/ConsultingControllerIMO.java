package mx.com.kubo.managedbeans;

import java.util.List;

import mx.com.kubo.model.ClientView;
//import mx.com.kubo.model.Membership;

public interface ConsultingControllerIMO 
{
	void init();
	void selectionAction();
	void cargaClientes();
	//void createConsultingLst( Membership memberSel_tmp );	
	void createConsulting(  );	
	
	
	List<ClientView> completeinfoclient(String name);
}
