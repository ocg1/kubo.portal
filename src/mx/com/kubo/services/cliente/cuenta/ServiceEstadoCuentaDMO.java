package mx.com.kubo.services.cliente.cuenta;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedProperty;
import javax.xml.rpc.ServiceException;

import mx.com.kubo.bean.TAmortizacionBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.cliente.CreditoEMO;
import mx.com.kubo.managedbeans.cliente.CreditoSAFI_EMO;
import mx.com.kubo.model.CollectionRelationship;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.RestructureBean;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.referencia_pago_panel.PanelAMO;
import mx.com.kubo.services.CollectionRelationshipService;
import mx.com.kubo.services.EstadoCuentaDatosService;
import mx.com.kubo.services.EstadoCuentaService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

import mx.com.kubo.model.TSafiCreditosMovs;
import mx.com.kubo.model.TSafiPagosCuota;
import mx.com.kubo.model.TSafiPosicionInt;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;

public abstract class ServiceEstadoCuentaDMO 
implements ServiceEstadoCuentaIMO
{	
	protected ProyectLoanService service_proyect_loan;
	
	protected CollectionRelationshipService service_collection_relationship;
	
	protected ServiceReferenciaPagoIMP service_referencia_pago;
	
	protected SystemParamService service_system_param;
	
	protected EstadoCuentaDatosService estadocuentadatosservice;
	
	protected EstadoCuentaService estadocuentaservice;
	
	protected SessionBean sesion;
	protected ProyectLoan proyectloan;	
	protected ProyectLoan proyect_loan;
	
	private SystemParam    systemparam;
	private SystemParamPK  systemParam_PK;
	
	protected WsSgbRiskServiceLocator locator_SGB;
	protected WsSgbRisk  web_service_SGB;
	
	protected List<TSafiPosicionInt>  posicion_SAFI;
	protected List<TSafiPagosCuota>   pagos_SAFI;
	protected List<TSafiCreditosMovs> creditos_SAFI;
	
	protected PanelAMO referencia_pago;
	
	protected CreditoEMO      credito;
	protected CreditoSAFI_EMO credito_safi;
	protected RestructureBean restructure;
	protected CollectionRelationship relationship;	
	protected TAmortizacionBean amortizacion;
	
	protected List<TAmortizacionBean> lista_amortizacion;
	protected List<ProyectLoan>       lista_proyect_loan;
	protected List<CreditoEMO>        lista_creditos;
	
	protected Hashtable<String,Double[]> comisiones;
		
	protected Calendar calTemp;
	
	protected Date fechaCorte;
	protected Date fechaInicio;
	protected Date fechaFin;
	protected Date d1, d2;
	protected Date fecha_ultimo_pago;
	
	protected SimpleDateFormat fm1;
	protected SimpleDateFormat formatStr;
	protected SimpleDateFormat formato_ddMMyyyy;
	protected NumberFormat dec, num;
	private Locale locale;
	
	protected StringBuilder sb;
	
	protected String capitalDes = "";
	protected String interesDes = "";
	protected String ivaDes     = "";	
	protected String fecha_inicio;
	protected String fecha_vencimiento;
	protected String saldoLiquidarStr;
	protected String reportePago;
	protected String colores;
	protected String scriptGraphic;
	protected String nombre;
	protected Integer numCliente;
	protected String cuota;
	protected String panel_monto_detalle; 	
	protected String estatus;
	protected String credito_id;
	protected String plazo;
	protected String amortizacion_id;
	
	protected final String TABLE;
	protected final String TITLE;
	protected final String TITLE_UNDER;
	protected final String TD_FIN;
	protected final String VALUE;
	protected final String TR_FIN;
	protected final String TABLE_FIN;		
	
	protected Double saldo_capital_vencido; 
	protected Double saldo_capital_vencido_no_exigle;			
	protected Double saldoLiquidar = 0D;	
	protected Double capital_vigente_tmp; 
	protected Double interes_vigente_tmp;
	protected Double interes;
	protected Double comision;
	protected Double tmpCapVen;
	protected Double capital_vencido;
	protected Double iva;
	protected Double cuota_pago;
	protected Double  catdl;		
	protected Double capital_atrasado;
	protected Double capital_atrasado_CREDITO_MORA;	
	protected Double saldo_insoluto;
	protected Double iva_generado;
	protected Double intereses_generados;	
	
	private Double capitales;
	private Double capital_pagado;
	private Double intereses;
	private Double tasa_mora;	
	
	protected Long dias_transcurridos;
	protected Long ld1, ld2;
		
	protected Integer contador_creditos_liquidados;
	protected Integer contador_creditos_vencidos;
	protected Integer contador_creditos_mora;
	protected Integer contador_creditos_vigentes;
	protected Integer creditos_totales;
	protected Integer prospectus_id;
	protected Integer company_id;
	protected Integer list_stackholder_size;
	
	private Integer factorMora = 0;
	
	protected Character area;
	
	protected double catlg;
	
	protected int diasT = -1;
	protected int frequency_id;
	
	protected boolean ver_creditos_pagados_ENABLED;;
	protected boolean credito_vencido;
	protected boolean credito_mora;
	protected boolean credito_LIQUIDADO_EN_FECHA;
	protected boolean credito_VENCIDO;
	protected boolean cuotas_vencidas_ENABLED;
	protected boolean status;
	
	protected final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
	
	protected ServiceEstadoCuentaDMO()
	{
		locator_SGB = new WsSgbRiskServiceLocator();
		
		try 
		{
			web_service_SGB = locator_SGB.getWsSgbRisk();
			
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		
		locale = new Locale("es","mx");
		
		fm1              = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		formatStr        = new SimpleDateFormat("EEE dd'-'MMM'-'yyyy", new Locale("ES"));
		formato_ddMMyyyy = new SimpleDateFormat("dd'-'MMM'-'yyyy",     new Locale("ES"));
		
		dec = NumberFormat.getCurrencyInstance(locale);
		num = NumberFormat.getNumberInstance(locale);
		
		TABLE       = "<table style = 'border-collapse: collapse;'>";
		TITLE       = "<tr><td>";
		TITLE_UNDER = "<tr><td style = 'border-bottom: solid 2px #000000;'>";
		TD_FIN      = "</td>";
		VALUE       = "<td style = 'text-align: right;'>";
		TR_FIN      = "</td></tr>";
		TABLE_FIN   = "</table>";
		
		capital_vigente_tmp = 0D;
		interes_vigente_tmp = 0D;	
		tmpCapVen           = 0D;
		
		contador_creditos_vigentes   = 0;
		contador_creditos_liquidados = 0;
		contador_creditos_mora       = 0;
		creditos_totales             = 0;
		contador_creditos_vencidos   = 0;
		capital_vencido              = 0.0;
		
		credito_vencido  = false;
		credito_mora     = false;
		
		service_proyect_loan            = Utilities.findBean("proyectLoanServiceImp") ;
		service_collection_relationship = Utilities.findBean("collectionRelationshipServiceImp") ;		
		//service_referencia_pago         = new ServiceReferenciaPagoIMP() ;		
		service_system_param            = Utilities.findBean("systemParamServiceImp") ;
		estadocuentaservice 			= Utilities.findBean("estadoCuentaServiceImp");
		estadocuentadatosservice		= Utilities.findBean("estadoCuentaDatosServiceImp") ;
			
	}
		
	public final void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}

	public final void setService_collection_relationship(CollectionRelationshipService service) 
	{
		service_collection_relationship = service;
	}

	public final void setService_system_param(SystemParamService service) 
	{
		service_system_param = service;
	}

	public final void setService_referencia_pago(ServiceReferenciaPagoIMP service)
	{
		service_referencia_pago = service;
	}
	
	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		area          = sesion.getArea();
		
		lista_proyect_loan = service_proyect_loan.getProyectLoanListByProspectus(prospectus_id, company_id);
		
		init_IVA();
		init_factor_mora();
	}
	
	public final void setVer_creditos_pagados_ENABLED(boolean bandera) 
	{
		ver_creditos_pagados_ENABLED = bandera;
	}

	private void init_IVA()
	{
		systemParam_PK = new SystemParamPK();
		
		systemParam_PK.setCompany_id(company_id);
		systemParam_PK.setSystem_param_id(14); // valor del iva  
		
		systemparam = service_system_param.loadSelectedSystemParam(systemParam_PK);
		
		iva = Double.parseDouble(systemparam.getValue() == null ? "0.16" : systemparam.getValue());		
	}
	
	protected void init_factor_mora()
	{			
		systemParam_PK = new SystemParamPK();		
		systemParam_PK.setCompany_id(company_id);
		
		systemParam_PK.setSystem_param_id(48); 
		
		systemparam = service_system_param.loadSelectedSystemParam(systemParam_PK);
		
		if(systemparam != null)
		{			
			factorMora = Integer.parseInt( systemparam.getValue());			
		}
	}

	public final void setProspectus_id(Integer prospectus_id) 
	{
		this.prospectus_id = prospectus_id;
	}

	public final void setPosicion_SAFI(List<TSafiPosicionInt> posicion)
	{
		posicion_SAFI = posicion;
		
		numCliente = posicion.get(0).getPk().getClienteId();
		nombre     = posicion.get(0).getNombreCompleto();
	}
		
	public final void setPagos_SAFI(List<TSafiPagosCuota> pagos) 
	{
		pagos_SAFI = pagos;
	}

	public final void setFecha_de_corte(Date fecha) 
	{
		fechaCorte = fecha;
	}
	
	public final void setFecha_inicio(Date fecha) 
	{
		fechaInicio = fecha;		
	}
	
	public final void setFecha_final(Date fecha) 
	{
		fechaFin = fecha;		
	}
	
	public final void setComisiones(Hashtable<String, Double[]> comisiones) 
	{
		this.comisiones = comisiones;
	}
	
	protected final boolean isCredito_LIQUIDADO() {
		
		return credito_safi.getEstatus() != null && (credito_safi.getEstatus().equals("P") || credito_safi.getEstatus().equals("RP"));
	}
	
	protected final boolean isCredito_VIGENTE() 
	{ 
		return credito_safi.getEstatus() != null && (credito_safi.getEstatus().equals("V") || credito_safi.getEstatus().equals("RV") );
	}
	
	public final Integer getContador_creditos_vigentes()
	{
		return contador_creditos_vigentes;
	}
	
	public final Integer getContador_creditos_liquidados() 
	{
		return contador_creditos_liquidados;
	}

	public final Integer getContador_creditos_vencidos() 
	{
		return contador_creditos_vencidos;
	}

	public final Integer getContador_creditos_mora() 
	{
		return contador_creditos_mora;
	}

	public final Integer getCreditos_totales() 
	{
		return creditos_totales;
	}

	protected final Double getTasa_mora() 
	{
		tasa_mora  = factorMora * Double.parseDouble(credito_safi.getTasa_fija());
		
		return tasa_mora;
	}
	
	protected ProyectLoan getProyectLoanByCreditId(List<ProyectLoan> lstPyL, String credit_id )
	{		
		ProyectLoan res = null;
				
		for(ProyectLoan pyl : lstPyL)
		{			
			if(pyl.getSafi_credit_id() != null && Integer.parseInt(pyl.getSafi_credit_id()) == Integer.parseInt((credit_id)) )
			{
				res = pyl;
				break;
			}			
		}
		
		return res;		
	}	
	
	protected final Double getCapitales() 
	{
		capitales = credito_safi.getSaldo_capital_vigent()
				  + credito_safi.getSaldo_capital_atrasado()
				  + capital_vencido;
		
		return capitales;
	}
	
	protected Double getIntereses() 
	{
		intereses = credito_safi.getSaldo_moratorio()
				  + credito_safi.getSaldo_interes_provisional()
				  + credito_safi.getSaldo_interes_atrasado()				  
				  + credito_safi.getSaldo_interes_vencido()
				  + credito_safi.getSaldo_interes_no_contable();
		
		return intereses;
	}
	
	protected final Double getCapital_pagado() 
	{
		capital_pagado = credito_safi.getMonto() - getCapitales();
		
		return capital_pagado;
	}
}
