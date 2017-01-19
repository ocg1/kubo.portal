package mx.com.kubo.portal.ofertas;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.Frequency;
import mx.com.kubo.model.SimulatorBean;
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
	
	protected int prospectus_id;
	protected int company_id;
	
	protected Double tasaTotal;
	protected Double comisionApertura;
	
	protected SimulatorBean simulation;
		
	protected CreditSimulatorIMO credit_simulator;
	
	protected List<Frequency> listFrequencyTmp;
	protected List<Frequency> listFrequency;
	
	protected StringBuilder sb;
	
	protected String ammount_value;	
	protected String term_frequency_TOKEN;
	
	protected Double ammount;	
	protected Double cat;	
	
	protected Double max_ammount;
	protected Double max_payment;
	
	protected Integer purpose_id;
	protected Integer frequency_id;
	protected Integer term_id ;
	
	protected boolean max_ammount_ENABLED;
	protected boolean max_payment_ENABLED;
	
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
	
	public void setRenovacion(RenovacionBean renovacion)
	{		
		try
		{
			max_ammount = Double.parseDouble(renovacion.getMax_ammount());
			max_payment = Double.parseDouble(renovacion.getMax_payment());
			
		} catch (Exception e) {	
			
			e.printStackTrace();
		}
	}		

	public boolean isMax_payment_ENABLED() 
	{
		return max_payment_ENABLED || max_ammount_ENABLED;
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
	
	public SimulatorBean getSimulation()
	{
		return simulation;
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

	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public void setTasaTotal(Double tasaTotal) {
		this.tasaTotal = tasaTotal;
	}

	public void setComisionApertura(Double comisionApertura) {
		this.comisionApertura = comisionApertura;
	}		
}
