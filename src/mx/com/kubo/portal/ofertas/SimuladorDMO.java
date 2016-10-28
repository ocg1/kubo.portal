package mx.com.kubo.portal.ofertas;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Frequency;
import mx.com.kubo.portal.simulador.CreditSimulatorIMO;
import mx.com.kubo.services.FrequencyService;
import mx.com.kubo.tools.Utilities;

public abstract class SimuladorDMO 
implements SimuladorIMO
{	
	protected FrequencyService service_frequency;
	
	protected RequestContext request;
	
	protected HtmlInputText input_text;
	protected HtmlSelectOneMenu select_one;
	
	protected SessionBean sesion;	
		
	protected CreditSimulatorIMO credit_simulator;
	
	protected List<Frequency> listFrequencyTmp;
	protected List<Frequency> listFrequency;
	
	protected StringBuilder sb;
	
	protected String ammount_value;	
	protected String term_frequency_TOKEN;
	
	protected Double ammount;
	protected Double payment;
	protected Double rate;
	protected Double cat;
	
	protected Integer purpose_id;
	protected Integer frequency_id;
	protected Integer term_id ;
	
	protected final int SEMANAL    = 1;
	protected final int CATORCENAL = 2;
	protected final int QUINCENAL  = 3;
	protected final int MENSUAL    = 4;
	
	protected SimuladorDMO()
	{
		service_frequency = Utilities.findBean("frequencyServiceImp");
		
		ammount_value = "50,000";
		ammount = 50000.00;
		
		  purpose_id = 0;
		frequency_id = 4;
		     term_id = 12;
	}
	
	public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		rate = sesion.getRate();
	}

	public List<Frequency> getListFrequency() 
	{
		return listFrequency;
	}
	
	public String getTerm_frequency_TOKEN()
	{
		sb = new StringBuilder();
		sb.append("A ").append(term_id);
		
		switch(frequency_id)
		{
			case SEMANAL:
				sb.append(" semanas");
			break;
			
			case CATORCENAL:
				sb.append(" catorcenas");
			break;
			
			case QUINCENAL:
				sb.append(" quincenas");
			break;
			
			case MENSUAL:
				sb.append(" meses");
			break;
		}
		
		term_frequency_TOKEN = sb.toString();
		
		return term_frequency_TOKEN;
	}

	public String getAmmount_value() 
	{
		return ammount_value;
	}
	
	public Double getRate()
	{
		return rate;
	}
	
	public Double getAmmount() 
	{
		return ammount;
	}
	
	public Double getPayment()
	{
		return payment;
	}
	
	public Double getCat()
	{
		return cat;
	}
		
	public Integer getPurpose_id()
	{
		return purpose_id;
	}

	public Integer getTerm_id() 
	{
		return term_id;
	}

	public Integer getFrequency_id() 
	{
		return frequency_id;
	}	
}
