package mx.com.kubo.portal.ofertas;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.html.HtmlInputText;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.portal.simulador.CreditSimulatorIMO;

public abstract class ParserRenovacionAutomaticaDMO 
implements ParserRenovacionAutomaticaIMO
{		
	protected RequestContext request;
	
	protected HtmlInputText input_text;
	
	protected SessionBean sesion;	
	
	protected CreditSimulatorIMO credit_simulator;
	
	protected SimulatorBean simulation;
	
	protected StringBuilder sb;
	protected Pattern pattern;
	protected Matcher  matcher;
	
	protected NumberFormat format;
	
	protected OfertaBean oferta;
	protected RenovacionBean renovacion;
		
	protected List<OfertaBean> tabla_oferta_N;			
	protected List<RenovacionBean> renovaciones;
	
	protected HashMap<String, Integer> diccionario; 
	
	protected String r_data;
	protected String oferta_TOKEN;
	protected String renovacion_TOKEN;
	protected String renovaciones_TOKEN;
	protected String ofertas_TOKEN;	
	protected String ofert_data;

	protected String ofert_ammount_TOKEN;	
	protected String ofert_ammount;
	protected String ofert_frequency;
	protected String ofert_term;	
	protected String ofert_payment;
	protected String term_frequency_TOKEN;
	protected String loan_type_id;
		
	protected final String MONTO_APROBADO;
	protected final String HASTA;
	protected final String PLAZO;
	protected final String REN_AUTO;
	protected final String RDC_AUTO;
	protected final String REN;
	protected final String RDC;
	protected final String OFERTA;
	
	protected String       r_data_split [];	
	protected String oferta_TOKEN_split [];
	protected String   ofert_data_split [];		
	
	protected Double ammount;
	protected Double payment;
	
	protected Integer frequency_id;
	protected Integer term_id;
	protected Integer min_ammount_from_offer;
	protected Integer max_ammount_from_offer;
		
	protected int beginIndex;
	protected int endIndex;
	protected int ofert_count;
	
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
	
	protected boolean ofert_ENABLED;
	protected boolean only_one_offert_ENABLED;
	
	protected ParserRenovacionAutomaticaDMO()
	{						
		MONTO_APROBADO = "Solo tienes que escoger tu monto aprobado de ";
		HASTA = " hasta ";
		PLAZO = " y elegir tu plazo";
		REN_AUTO = "REN_AUTO";
		RDC_AUTO = "RDC_AUTO";
		OFERTA   = "OFERTA_";
		REN = "REN";
		RDC = "RDC";
		  
		only_one_offert_ENABLED = true;
		
		format = NumberFormat.getNumberInstance(new Locale("es", "MX"));
	}
	
	public void setScore(Scoring score)
	{
		r_data = score.getR_data();
	}
		
	public void setSesion(SessionBean sesion)
	{
		this.sesion = sesion;
	}		
	
	public String getLoan_type_id() 
	{
		return loan_type_id;
	}

	public String getOfert_ammount_TOKEN() 
	{
		return ofert_ammount_TOKEN;
	}
	
	public String getTerm_frequency_TOKEN() 
	{		
		return term_frequency_TOKEN;
	}
	
	public SimulatorBean getSimulation()
	{
		return simulation;
	}
	
	public RenovacionBean getRenovacion(String SAFI_credit_id)
	{
		Integer indice = diccionario.get(SAFI_credit_id);
		
		renovacion = renovaciones.get(indice);
		
		return renovacion;
	}
	
	public List<RenovacionBean> getRenovaciones()
	{
		return renovaciones;
	}
}
