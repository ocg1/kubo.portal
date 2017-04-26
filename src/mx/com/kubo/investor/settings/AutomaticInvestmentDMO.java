package mx.com.kubo.investor.settings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.FilterStore;
import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.InvestmentFilter;
import mx.com.kubo.model.InvestmentFilterPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.rest.tienda.SAFIInvestmentIMO;
import mx.com.kubo.rest.tienda.accounts.CuentasClienteIMO;
import mx.com.kubo.services.AutomaticInvestmentService;
import mx.com.kubo.services.InvestmentFilterService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PurposeService;
import mx.com.kubo.tools.Utilities;

public abstract class AutomaticInvestmentDMO implements AutomaticInvestmentIMO
{
	protected  RequestContext request;
	protected ExternalContext external;
	
	protected HtmlInputText input;
	protected HtmlSelectOneMenu select;
	
	
	protected Map<String , String > map;
	
	protected             PurposeService purposeservice;	
	protected       NaturalPersonService naturalPersonService;
	protected    InvestmentFilterService investmentFilterServiceImp;
	protected AutomaticInvestmentService service_automatic_investment;
	
	protected SessionBean sesion;
	protected NaturalPerson person;
	protected gnNaturalPersonPK person_PK;
	protected InvestmentFilter filterInvestment;
	protected InvestmentFilterPK fpk;
	protected AutomaticInvestment automatic_investment;
	
	protected FilterStore filter;
	protected CuentasClienteIMO cuenta;
	protected SAFIInvestmentIMO inversion;	
	
	protected List<Purpose> purposelst;
	protected List<ItemLoanList> proyectList;
	protected List<ItemLoanList> proyectListForInvesInd;
	protected List<InvestorsAccounts> listInvAccounts;
	protected List<AutomaticInvestment> automatic_investment_list;
	
	protected SimpleDateFormat frm;
	protected StringBuilder sb;
	
	protected Date finisehd_date;
	
	private Calendar calendar;
	
	protected String previousType = "0";
	protected String flagRisk = "0";
	protected String destiny_str;
	protected String risk_str;
	protected String lastFilter;
	protected String labelOnFilteringByAmmount = "";
	protected String scriptStatus;
	protected String tagAccount;
	protected String cuentaActual;
	protected String genderStr;
	protected String strQuery;
	protected String safi_client_id;
	protected String account;
	protected String ultimoFiltro;
	protected String label = "";
	protected String frequency_label;
	protected String frequency = "D";
	protected String is_active = "1";
	
	protected final String DIARIA  = "Diaria";
	protected final String SEMANAL = "Semanal";
	
	protected Double saldoTotal;
	protected Double saldoActual;
	protected Double ammoutToInv;
	protected Double ammountFoundedInv;
	
	protected int typeSearch;
	protected int company_id;
	protected int prospectus_id;
	
	protected boolean flagNumberProy = true;
	protected boolean flagInversionFG;
	protected boolean hold_selected;
	
	protected AutomaticInvestmentDMO()
	{
    				purposeservice   = Utilities.findBean("purposeServiceImp");
		      naturalPersonService   = Utilities.findBean("naturalPersonServiceImp");
		investmentFilterServiceImp   = Utilities.findBean("investmentFilterServiceImp");
		service_automatic_investment = Utilities.findBean("automaticInvestmentServiceImp");
		
		proyectList = new ArrayList<ItemLoanList>();
		
		frm = new SimpleDateFormat("yyyy-MM-dd");
		
    	calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	calendar.add(Calendar.YEAR, 100);
    	
    	finisehd_date = calendar.getTime();
    	
    	frequency_label = DIARIA;
	}

	public void setSesion(SessionBean sesion)
	{
		this.sesion = sesion;
		
		if(sesion != null)
		{
	    	   company_id = sesion.getCompany_id();
	       	prospectus_id = sesion.getProspectus_id();
	   		
	       	person_PK = new gnNaturalPersonPK(prospectus_id, company_id);
	       	
	       	person = naturalPersonService.getNaturalPersonById(person_PK);
	       	
	       	safi_client_id = person.getSafi_client_id();
		}
	}
	
	public void setFlagNumberProy(boolean flag) 
	{
		flagNumberProy = flag;
	}
	
	public String getFlagRisk() 
	{
		return flagRisk;
	}

	public void setFlagRisk(String flagRisk) 
	{
		this.flagRisk = flagRisk;
	}
	
	public boolean isFlagNumberProy() 
	{
		return flagNumberProy;
	}
	
	public boolean isFlagInversionFG() 
	{
		return flagInversionFG;
	}
	
	public String getDestiny_str() 
	{
		return destiny_str;
	}
	
	public String getRisk_str() 
	{
		return risk_str;
	}
	
	public int getTypeSearch() 
	{
		return typeSearch;
	}

	public void setTypeSearch(int typeSearch) 
	{
		this.typeSearch = typeSearch;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getFrequency()
	{
		return frequency;
	}
	
	public String getFrequency_label()
	{
		return frequency_label;
	}	
	
	public String getPreviousType() 
	{
		return previousType;
	}

	public void setPreviousType(String previousType) 
	{
		this.previousType = previousType;
	}
	
	public String getLabelOnFilteringByAmmount() 
	{
		return labelOnFilteringByAmmount;
	}

	public void setLabelOnFilteringByAmmount(String labelOnFilteringByAmmount) 
	{
		this.labelOnFilteringByAmmount = labelOnFilteringByAmmount;
	}
	
	public String getScriptStatus() 
	{
		return scriptStatus;
	}
	
	public List<AutomaticInvestment> getAutomatic_investment_list()
	{
		return automatic_investment_list;
	}
	
	public List<Purpose> getPurposelst()
	{
		return purposelst;
	}
}
