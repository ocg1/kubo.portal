package mx.com.kubo.listeners.registro;

import java.util.List;

import mx.com.kubo.bean.ActividadEconomicaDMO;
import mx.com.kubo.bean.BusinessBean;
import mx.com.kubo.bean.EmploymentBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;

public interface ListenerMiPrestamoIMO 
{
	void setSesion(SessionBean sesion);
	void setNatural_person(NaturalPerson natural_person);
	void setEmployment_bean(EmploymentBean employment_bean);
	void setBusiness_bean(BusinessBean business_bean);
	
	boolean update_employment();
	boolean update_employment_address();
	boolean update_business();
	boolean update_business_address();
	
	List<ActividadEconomicaDMO> getLista_employment_bean();
	List<ActividadEconomicaDMO>   getLista_business_bean();
}
