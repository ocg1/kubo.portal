package mx.com.kubo.registro.verificacion;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Stackholder_relationship;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.StackholderRelService;

public interface ProspectoDuplicadoIMO 
{
	void setService_membership    (MembershipService     service);
	void setService_natural_person(NaturalPersonService  service);
	void setService_access        (AccessService         service);
	void setService_accionistas   (StackholderRelService service);
	
	void init_natural_person(SessionBean sesion);
	void init_natural_person(int prospectus_id , int company_id , SessionBean sesion);
	
	void registrar_access();
	
	void valida_persona_relacionada_y_empleado();
	boolean isFlagEmployee();
	boolean isFlagRelation();
	boolean verificar_duplicidad_prospecto();
	
	Stackholder_relationship  getStackholder_selection();
	
	String getLista_prospectos_duplicados();
}
