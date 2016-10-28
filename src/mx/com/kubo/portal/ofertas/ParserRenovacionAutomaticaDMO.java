package mx.com.kubo.portal.ofertas;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.html.HtmlInputText;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.portal.simulador.CreditSimulatorIMO;

public abstract class ParserRenovacionAutomaticaDMO 
implements ParserRenovacionAutomaticaIMO
{		
	protected RequestContext request;
	
	protected HtmlInputText input_text;
	
	protected SessionBean sesion;
	
	protected Scoring score;
	
	protected CreditSimulatorIMO credit_simulator;
	
	protected StringBuilder sb;
	protected Pattern pattern;
	protected Matcher  matcher;
	
	protected NumberFormat format;
	
	protected OfertaBean oferta;
	protected RenovacionBean renovacion;
		
	protected List<OfertaBean> tabla_oferta_N;			
	protected List<RenovacionBean> renovaciones;
	
	protected String r_data;
	protected String oferta_TOKEN;
	protected String renovacion_TOKEN;
	protected String renovaciones_TOKEN;
	protected String ofertas_TOKEN;	
	protected String ofert_data;
	
	protected String ofert_TOKEN;
	protected String ofert_rate_TOKEN;
	protected String ofert_ammount_TOKEN;	
	protected String ofert_ammount;
	protected String ofert_frequency;
	protected String ofert_term;	
	protected String ofert_payment;
	protected String term_frequency_TOKEN;
		
	protected final String PRESTAMO_APROBADO;
	protected final String MONTO_APROBADO;
	protected final String MEJOR_TASA;
	protected final String HASTA;
	protected final String PLAZO;
	
	protected String       r_data_split [];	
	protected String oferta_TOKEN_split [];
	protected String   ofert_data_split [];		
	
	protected Double actual_rate;	
	protected Double rate;
	protected Double opening_commission;
	protected Double ammount;
	protected Double payment;
	protected Double cat;
	
	protected Integer frequency_id;
	protected Integer term_id;
		
	protected int beginIndex;
	protected int endIndex;
	
	protected final int SAFI_CREDIT_ID = 0;
	protected final int ACTUAL_AMMOUNT = 1;
	protected final int ACTUAL_PAYMENT = 2;
	protected final int ACTUAL_TERM    = 3;
	protected final int ACTUAL_FREQUENCY = 4;	
	protected final int MAX_AMMOUNT = 5;
	protected final int MAX_PAYMENT = 6;
	protected final int OFERT_FREQUENCY = 1;
	protected final int OFERT_TERM = 0;		
	protected final int SEMANAL = 1;
	protected final int CATORCENAL = 2;
	protected final int QUINCENAL = 3;
	protected final int MENSUAL = 4;
	protected final int MAX_OFERT_NUMBER_PER_RENOVATION = 3;
	
	protected boolean same_rate_ENABLED;
	
	protected ParserRenovacionAutomaticaDMO()
	{						
		PRESTAMO_APROBADO = "Tu nuevo préstamo ya está aprobado";
		MEJOR_TASA = " con una mejor tasa:";
		MONTO_APROBADO = "Solo tienes que escoger tu monto aprobado de ";
		HASTA = " hasta ";
		PLAZO = " y elegir tu plazo";
		
		format = NumberFormat.getNumberInstance(new Locale("es", "MX"));
	}
	
	public void setProyect_loan(ProyectLoan proyect_loan) 
	{						
		if(proyect_loan != null)
		{		
			r_data = proyect_loan.getR_data();			
			score  = proyect_loan.getScoring();
			actual_rate = proyect_loan.getRate();
			
			if(score != null)
			{
				rate = score.getRate();
				opening_commission = score.getOpening_commission();
				
				sesion.setOpeningCommission(opening_commission);				
				sesion.setRate(rate);
				
				same_rate_ENABLED = actual_rate.toString().equals(rate.toString());
			}
		}
	}
	
	public void setSesion(SessionBean sesion)
	{
		this.sesion = sesion;
	}
	
/*	
	public void setSimulator(Simulator simulator) 
	{
		this.simulator = simulator;
	}
*/	
	
	public boolean isSame_rate_ENABLED()
	{
		return same_rate_ENABLED;
	}
	
	public String getOfert_TOKEN() 
	{
		return ofert_TOKEN;
	}

	public String getOfert_ammount_TOKEN() 
	{
		return ofert_ammount_TOKEN;
	}
	
	public String getOfert_rate_TOKEN() 
	{
		return ofert_rate_TOKEN;
	}
	
	public String getTerm_frequency_TOKEN() 
	{		
		return term_frequency_TOKEN;
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
	
	public Double getRate() 
	{
		return rate;
	}
	
	public List<RenovacionBean> getRenovaciones()
	{
		return renovaciones;
	}
}
