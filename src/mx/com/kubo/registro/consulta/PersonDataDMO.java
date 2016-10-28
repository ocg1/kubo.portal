package mx.com.kubo.registro.consulta;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.model.CreditHistoryPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.CreditHistoryService;

public abstract class PersonDataDMO
implements PersonDataIMO
{	
	protected AccessService service_access;
	protected CreditHistoryService service_credit_history;
	
	protected RequestContext request;		
	
	protected CreditHistory   credit_history;
	protected CreditHistoryPK credit_history_PK;
	
	protected SessionBean sesion;
	
	protected Prospectus prospectus;
	protected NaturalPerson person;
	protected Membership membership;
	
	protected Address address;
	protected Phone phone;
	protected Access access;
	
	protected String messageInit;
	protected String addressStr;
	protected String birthday;
	protected String legendCard;
	protected String legendMortgage;
	protected String legendCar;
	
	protected Integer prospectus_id;
	protected Integer company_id;
	
	protected Character area;
	
	protected int i;
	protected int edad;
	protected final int SCREEN_PERSON_DATA = 65;
	
	protected boolean displayAction;		
	protected boolean flag;
	
	public void setService_access(AccessService service) 
	{
		service_access = service;
	}

	public void setService_credit_history(CreditHistoryService service) 
	{
		service_credit_history = service;
	}

	public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		area           = sesion.getArea();
		prospectus_id  = sesion.getProspectus_id();
		company_id     = sesion.getCompany_id();
	}

	public void setMembership(Membership membership) 
	{
		this.membership = membership;
		
		person = membership.getPerson();
		
		prospectus = person.getProspectus();
	}
	
	public void setCredit_history(CreditHistory credithistory) 
	{
		credit_history = credithistory;
	}

	public void setAddress(Address address) 
	{
		this.address = address;
	}

	public void setPhone(Phone phone) 
	{
		this.phone = phone;
	}

	public NaturalPerson getPerson() 
	{
		return person;
	}

	public Phone getPhone() 
	{
		return phone;
	}

	public String getBirthday() 
	{
		return birthday;
	}

	public String getAddressStr() 
	{
		return addressStr;
	}
	
	public String getMessageInit() 
	{
		return messageInit;
	}

	public String getLegendCard() 
	{
		return legendCard;
	}

	public String getLegendMortgage() 
	{
		return legendMortgage;
	}

	public String getLegendCar() 
	{
		return legendCar;
	}

	public int getEdad() 
	{
		return edad;
	}

	public boolean isDisplayAction() 
	{
		return displayAction;
	}
}
