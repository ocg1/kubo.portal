package mx.com.kubo.registro.datos.simulador;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.portal.simulador.CreditSimulatorIMO;
import mx.com.kubo.registro.ChangeControlDMO;

public abstract class SimuladorDMO extends ChangeControlDMO 
implements SimuladorIMO 
{
	protected RequestContext request;
	
	protected HtmlInputText input_text;
	protected HtmlSelectOneMenu select_one;
	
	protected CreditSimulatorIMO credit_simulator;
	
	protected Simulator simulator;
	protected SimulatorBean simulation;
	
	protected Locale locale;
	protected DecimalFormatSymbols symbols;
	protected DecimalFormat decimalFormat;
	
	private final String pattern;
	protected String ammount_value;	
	
	protected Double tasaTotal;
	protected Double comisionApertura;
	protected Double ammount;	
	
	protected Integer purpose_id;
	protected Integer frequency_id;
	protected Integer term_id;
	
	protected SimuladorDMO()
	{				
		locale = new Locale("es", "MX");

		symbols = new DecimalFormatSymbols(locale);
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');
		
		pattern = "###,###.##";
			    
	    decimalFormat = new DecimalFormat(pattern, symbols);
	    
		ammount_value = "50,000";
		ammount = 50000.00;
		
		comisionApertura = 5D;
		tasaTotal = 52.6D;
		
		  purpose_id = 1;
		frequency_id = 4;
		     term_id = 12;
	}
	
	public void setSimulator(Simulator simulator)
	{	    
	    this.simulator = simulator;
	     
		ammount       = simulator.getAmmount();
		ammount_value = decimalFormat.format(simulator.getAmmount());
		purpose_id    = simulator.getPurpose_id();
		frequency_id  = simulator.getFrequency_id();
	}

	public String getAmmount_value() 
	{
		return ammount_value;
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
