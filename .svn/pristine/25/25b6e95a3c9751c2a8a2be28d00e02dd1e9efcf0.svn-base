package mx.com.kubo.managedbeans;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.Amortization;
import mx.com.kubo.model.ComparisonMod;
import mx.com.kubo.model.ComparisonModPK;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.Simulation_Cache;
import mx.com.kubo.model.Simulation_Cache_PK;
import mx.com.kubo.repositories.ServiceCallingDao;
import mx.com.kubo.services.ComparisonService;
import mx.com.kubo.services.SimulationCacheService;
import mx.com.kubo.services.SimulatorService;
import safisrv.ws.CreditosServicios.SAFIServicios;
import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse;




@ManagedBean
@SessionScoped
public class Comparison implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@ManagedProperty("#{serviceCallingDaoImp}")
	private ServiceCallingDao servicecallingRepository;
	
	@ManagedProperty("#{simulationCacheServiceImp}")
	private SimulationCacheService simulationCacheService;
	
	@ManagedProperty("#{simulatorServiceImp}")
	protected SimulatorService simulatorService;
	
	@ManagedProperty("#{comparisonServiceImp}")
	private ComparisonService comparisonService;

	private Double 	monto = 15000d;
	private Integer plazo = 8;
	private Integer frequency = 1;
	private Double cuota = 0d;
	
	private Integer frequencyAnual;
	private String frequencyStr;
	private int numCuota=0;
	private Double tasaPeriodoPorc=0d;
	private Double tasaTotal=52.6d;
	private Double iva=0.16d;
	private Double ivaInteres=0d;
	private Double tasaPeriodo=0d;
	private Double totalPagar=0d;
	private Double montoCuota=0d;
	private int diasFreq = 0;
	
	private Double interes=0d;
	private String cadenaResultado;
	private String nameInst;
	public  Locale locale = new Locale("es","mx");
	public  java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
	
	private String montoStr;
	private String cuotaStr;
	
	private String montoInStr;
	private String cuotaInStr;
	
	private String montoCuotaStr;
	private String totalPagarStr;
	private String totalPagarOtroStr;
	private String diferenciaStr;
	private String freqStr;
	private Double cat;
	private String catStr;
	private Double montoComisionApert;
	private boolean flagSaveSimulationCache = false;
	private SessionBean sesion ;
	private Double comisionApertura=5D;
	
	@PostConstruct
	public void init(){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		sesion = (SessionBean) FacesContext.getCurrentInstance().getApplication()
	        .getELResolver().getValue(elContext, null, "sessionBean");
	}
	
	
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Integer getPlazo() {
		return plazo;
	}
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public Double getCuota() {
		return cuota;
	}
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
	public Integer getFrequencyAnual() {
		return frequencyAnual;
	}
	public void setFrequencyAnual(Integer frequencyAnual) {
		this.frequencyAnual = frequencyAnual;
	}
	public String getFrequencyStr() {
		return frequencyStr;
	}
	public void setFrequencyStr(String frequencyStr) {
		this.frequencyStr = frequencyStr;
	}
	public int getNumCuota() {
		return numCuota;
	}
	public void setNumCuota(int numCuota) {
		this.numCuota = numCuota;
	}
	public Double getTasaPeriodoPorc() {
		return tasaPeriodoPorc;
	}
	public void setTasaPeriodoPorc(Double tasaPeriodoPorc) {
		this.tasaPeriodoPorc = tasaPeriodoPorc;
	}
	public Double getTasaTotal() {
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion 
		    = (SessionBean) FacesContext.getCurrentInstance().getApplication()
		        .getELResolver().getValue(elContext, null, "sessionBean");
		if(sesion.getRate()!=null)
			tasaTotal = sesion.getRate();
		
		return tasaTotal;
	}
	public void setTasaTotal(Double tasaTotal) {
		this.tasaTotal = tasaTotal;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public Double getIvaInteres() {
		return ivaInteres;
	}
	public void setIvaInteres(Double ivaInteres) {
		this.ivaInteres = ivaInteres;
	}
	public Double getTasaPeriodo() {
		return tasaPeriodo;
	}
	public void setTasaPeriodo(Double tasaPeriodo) {
		this.tasaPeriodo = tasaPeriodo;
	}
	public Double getTotalPagar() {
		return totalPagar;
	}
	public void setTotalPagar(Double totalPagar) {
		this.totalPagar = totalPagar;
	}
	public Double getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}
	public Double getInteres() {
		return interes;
	}
	public void setInteres(Double interes) {
		this.interes = interes;
	}
	public String getCadenaResultado() {
		return cadenaResultado;
	}
	public void setCadenaResultado(String cadenaResultado) {
		this.cadenaResultado = cadenaResultado;
	}
	
	
	public void myComparison(){
//		System.out.println("Creando comparison." );
//		System.out.println(", cuota: "+getCuotaInStr());
//		System.out.println(", monto: "+getMontoInStr());
//		System.out.println(", freq: "+getFrequency());
//		System.out.println(", plazo: "+getPlazo());
		
		String s =getCuotaInStr();
		s = s.replaceAll(",","");
		s = s.replaceAll("$","");
		
		System.out.println("s Cuota: "+ s);
		
		setCuota(Double.parseDouble(s));
		
		s = getMontoInStr();
		s = s.replaceAll(",","");
		s = s.replaceAll("$","");
		
		System.out.println("s Monto: "+ s);
		
		setMonto(Double.parseDouble(s));
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			
		}
		
		simulaCred();
		String cadena="";
		cadena +=    dec.format(getMonto())+"::"  //0
					+ dec.format(getCuota())+"::" // 1 competencia
					+ dec.format(getMontoCuota())+"::" // 2 kubo
					+ getFrequencyStr()+"::" // 3
					+ getPlazo()+"::" // 4
					+ dec.format(getTotalPagarOtro())+"::" // 5 competencia
					+ dec.format(getTotalPagar())+"::"// 6 kubo
					 ;
		if(getNameInst()!=null&& getNameInst().length()>0)
			cadena += getNameInst()+"::";// 7 nombre competencia
		else
			cadena += "Otra institución::";// 7 nombre competencia
		
		Double d = getDiferencia();
		
		cadena += dec.format(d)+"::";// 8 diferencia
		
		cadena += getCatStr()+"::";// 9 cat
		
		SimpleDateFormat formatStr = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
		
		String strDate = (formatStr.format(new Date()));

		cadena += strDate+"::";// 10 fecha
		
		cadena += getTasaTotal()+"::";// 11 tasa Kubo
		
		setCadenaResultado(cadena);
		setNameInst("");
	}
	
	public void simulaCred(){
		setNumCuota(generaNumCuotas());
		setTasaPeriodo(generaTasaPeriodo());
		//setMontoCuota(generaMontoCuota2());
		getCuotaByFormula(true);
		if(flagSaveSimulationCache){
			saveSimulatorCache();
		}
		
		//Guarda las comparaciones para usuarios conectados y no conectados.
		if(isConnected()){
			ComparisonModPK comparisonPK = new ComparisonModPK(0, sesion.getProspectus_id(), sesion.getCompany_id());
						
			ComparisonMod comparisonMod = new ComparisonMod();
			comparisonMod.setComparisonPK(comparisonPK);
			comparisonMod.setFrequency_id(getFrequency());
			comparisonMod.setInstitution_name_other(getNameInst());
			comparisonMod.setTotal_payment_kubo(getTotalPagar());
			comparisonMod.setTotal_payment_other(getTotalPagarOtro());
			comparisonMod.setAmmount(getMonto());
			comparisonMod.setPayment(getCuota());
			comparisonMod.setNum_payments(getPlazo());
			comparisonMod.setRate(getTasaTotal());
			comparisonMod.setMx_cat(getCat());
			comparisonMod.setCreation_date(new Date());
			
			if(comparisonMod != null){
				comparisonService.add(comparisonMod);
			}
			
		}else{
			ComparisonModPK comparisonPK = new ComparisonModPK(0, 0, 1);
						
			ComparisonMod comparisonMod = new ComparisonMod();
			comparisonMod.setComparisonPK(comparisonPK);
			comparisonMod.setFrequency_id(getFrequency());
			comparisonMod.setInstitution_name_other(getNameInst());
			comparisonMod.setTotal_payment_kubo(getTotalPagar());
			comparisonMod.setTotal_payment_other(getTotalPagarOtro());
			comparisonMod.setAmmount(getMonto());
			comparisonMod.setPayment(getCuota());
			comparisonMod.setNum_payments(getPlazo());
			comparisonMod.setRate(getTasaTotal());
			comparisonMod.setMx_cat(getCat());
			comparisonMod.setCreation_date(new Date());
			
			if(comparisonMod != null){
				comparisonService.add(comparisonMod);
			}
		}
		

	}
	
	private Integer generaNumCuotas(){
		int freq = getFrequency();
		//int num = 0;
		int num = getPlazo();
		switch (freq){
			case 1://Semanal
				//num= (int)Double.parseDouble(Math.rint((getPlazo()*52)/12)+"");
				setFrequencyAnual(52);
				setFrequencyStr("semanales");
				setDiasFreq(7);
				setFreqStr("S");
				break;
			case 2: //Catorcenal
				//num= (int)Double.parseDouble(Math.rint((getPlazo()*26)/12)+"");
				setFrequencyAnual(26);
				setFrequencyStr("catorcenales");
				setDiasFreq(14);
				setFreqStr("C");
				break;
			case 3: //Quincenal
				num= getPlazo()*2;
				setFrequencyAnual(24);
				setFrequencyStr("quincenales");
				setDiasFreq(15);
				setFreqStr("Q");
				break;
			case 4: //Mensual
				num= getPlazo();
				setFrequencyAnual(12);
				setFrequencyStr("mensuales");
				setDiasFreq(30);
				setFreqStr("M");
				break;
		}
		return num;
	}
	
	private Double getDiferencia(){
		return getTotalPagarOtro() - getTotalPagar();
	}
	
	private Double generaTasaPeriodo(){
		double ta = getTasaTotal();
		double n = Double.parseDouble(getFrequencyAnual()+"");
		//return (double)Math.round((ta/n)*100)/100;
		double tp =(double)Math.round((((ta/100)/n)*(1+getIva()))*10000)/10000;
		setTasaPeriodoPorc((double)Math.round((tp*100)*100)/100);
		return tp;
	}	
	private Double generaMontoCuota2(){
		//double ta = getTasaTotal();
		/*Double intper = getTasaPeriodo();
		Double num = (Math.pow((1+intper), getNumCuota()))*intper;
		Double den = (Math.pow((1+intper), getNumCuota()))-1;
		Double montoAPagar = getMonto()*(num/den);
		setMontoCuota((double)Math.round((montoAPagar*getNumCuota())*100)/100);
		setInteres(generaInteres());
		return (double)Math.round(montoAPagar*100)/100;*/
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion 
		    = (SessionBean) FacesContext.getCurrentInstance().getApplication()
		        .getELResolver().getValue(elContext, null, "sessionBean");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		//SimpleDateFormat formatStr = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
		
		String thisFechaInicio = format.format(new Date());
		//setFechaInicio(formatStr.format(new Date()));
		
		Simulation_Cache simulationCache = null;
		if(getFrequency() == 1 || getFrequency() == 2)
			simulationCache = simulationCacheService.getMaxByAmmountRateTermFrequency(getMonto(), getTasaPeriodo(), getPlazo(), getFrequency(), 1);
			
		if(simulationCache == null){
		SimuladorCuotaCreditoRequest simulador = new SimuladorCuotaCreditoRequest();
		
		simulador.setFechaInicio(thisFechaInicio);
		simulador.setFrecuencia(getFreqStr());
		simulador.setMontoSolici(getMonto()+"");
		simulador.setPlazo(getNumCuota()+"");
		simulador.setTasaAnualizada(getTasaTotal()+"");
		simulador.setAjustarFecVen("N");
		
		montoComisionApert = (getMonto()*getComisionApertura())/100;
		simulador.setComisionApertura(montoComisionApert +"");
		simulador.setFormaCobroComAp("D");
		
		ServiceCalling srvCall = new ServiceCalling();
		if(sesion!=null&&sesion.getProspectus_id()!=null&&sesion.getProspectus_id()!=0){
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(sesion.getCompany_id());
			srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.simuladorCuotaCredito");
			srvCall.setProspectus_id(sesion.getProspectus_id());
			srvCall.setStatus(1);
			servicecallingRepository.saveServiceCall(srvCall);
		}
		try{
			SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
			SAFIServicios service = locator.getSAFIServiciosSoap11();
			SimuladorCuotaCreditoResponse response =  service.simuladorCuotaCredito(simulador);
		
				if(response.getCodigoRespuesta().equals("0")){
					if(sesion!=null&&sesion.getProspectus_id()!=null&&sesion.getProspectus_id()!=0){
						srvCall = new ServiceCalling();
			
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id(sesion.getCompany_id());
						srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.simuladorCuotaCredito: "+response.getMensajeRespuesta());
						srvCall.setProspectus_id(sesion.getProspectus_id());
						srvCall.setStatus(2);
						
						servicecallingRepository.saveServiceCall(srvCall);
					}
					
					/*setCat(Double.parseDouble(response.getCat().replace(",", "")));
					setCatStr(response.getCat());*/
					setMontoCuota(Double.parseDouble(response.getMontoCuota().replace(",", "")));
					setNumCuota(Integer.parseInt(response.getNumeroCuotas().replace(",", "")));
					setTotalPagar(Double.parseDouble(response.getTotalPagar().replace(",", "")));
					setInteres(generaInteres());
					
					setCat(Double.parseDouble(response.getCat().replace(",", "")));
					System.out.println("Cat Antes:  -->"+getCat());
					setCat((double) Math.ceil(getCat()*10)/10)	;
					System.out.println("Cat Después:  -->"+getCat());
					
					//Guarda solo si la frecuencia es Semanal o Catorcenal
					if(getFrequency() == 1 || getFrequency() == 2){
						flagSaveSimulationCache = true;
						
					}else{
						flagSaveSimulationCache = false;
						
					}

					
				}else{
					if(sesion!=null&&sesion.getProspectus_id()!=null&&sesion.getProspectus_id()!=0){
						srvCall = new ServiceCalling();
						
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id(sesion.getCompany_id());
						srvCall.setException(response.getMensajeRespuesta());
						srvCall.setProspectus_id(sesion.getProspectus_id());
						srvCall.setStatus(3);
						servicecallingRepository.saveServiceCall(srvCall);
					}
					flagSaveSimulationCache = false;
				}
				
				return Double.parseDouble(response.getMontoCuota().replace(",", ""));
		}catch(Exception e){
			e.printStackTrace();
			flagSaveSimulationCache = false;
			return 0D;
		}
		}else{
			
			
			setMontoCuota(simulationCache.getPayment());
			//setMontoCuota(Double.parseDouble(response.getMontoCuota().replace(",", "")));
			setNumCuota(simulationCache.getNum_payments());
			//setNumCuota(Integer.parseInt(response.getNumeroCuotas().replace(",", "")));
			setTotalPagar(simulationCache.getTotal_payment());
			//setTotalPagar(Double.parseDouble(response.getTotalPagar().replace(",", "")));
			
			setInteres(generaInteres());
			
			setCat(Math.ceil(simulationCache.getMx_cat()*10)/10);
			//setCat(Double.parseDouble(response.getCat().replace(",", "")));
			//System.out.println("Cat Antes:  -->"+getCat());
			//setCat((double) Math.ceil(getCat()*10)/10)	;
			//System.out.println("Cat Después:  -->"+getCat());
			
			simulationCache.setConsultation_last(new Date());
			simulationCache.setConsultation_count(simulationCache.getConsultation_count() == null?1:simulationCache.getConsultation_count()+1);
			simulationCacheService.update(simulationCache);
			
			flagSaveSimulationCache = false;
			return simulationCache.getPayment();
		}
		
	}
	private Double generaInteres(){
		Double i = ((getMontoCuota()*getNumCuota())-getMonto());
		double interes = i;/*/(1+getIva());
		double iva = (double)Math.round((i - interes)*100)/100;
		setIvaInteres(iva);*/
		return (double)Math.round(interes*100)/100;
	}
	private Double generaTotal(){
		return (double)Math.round((getMonto()+getInteres()+getIvaInteres())*100)/100;
	}
	
	private Double getTotalPagarOtro(){
		return getCuota()*getNumCuota();
	}
	
	public void saveSimulatorCache(){
		Simulation_Cache_PK simulationCachePK = new Simulation_Cache_PK();
		Simulation_Cache simulationCache = new Simulation_Cache();
		
		simulationCachePK.setCompany_id(1);
		
		simulationCache.setPk(simulationCachePK);
		simulationCache.setAmmount(getMonto());
		simulationCache.setTerm_id(getPlazo());
		simulationCache.setPayment(getMontoCuota());
		simulationCache.setFrequency_id(getFrequency());
		simulationCache.setNum_payments(getNumCuota());
		simulationCache.setTotal_payment(getTotalPagar());
		simulationCache.setPeriod_rate(getTasaPeriodo());
		simulationCache.setYearly_rate(getTasaTotal());
		simulationCache.setMx_cat(getCat());
		simulationCache.setCreation_date(new Date());
		
		simulationCacheService.add(simulationCache);

	}
	
	private boolean isConnected(){
		
		if(sesion.getProspectus_id()!=null&&sesion.getProspectus_id()>0)
			return true;
		else
			return false;
		
	}
	
	public String getNameInst() {
		
		if(nameInst==null || nameInst.length()==0)
			nameInst = "Otra institución";
		return nameInst;
	}
	public void setNameInst(String nameInst) {
		this.nameInst = nameInst;
	}
	public String getMontoStr() {
		montoStr = dec.format(getMonto());
		return montoStr;
	}
	public void setMontoStr(String montoStr) {
		this.montoStr = montoStr;
	}
	public String getCuotaStr() {
		cuotaStr = dec.format(getCuota());
		return cuotaStr;
	}
	public void setCuotaStr(String cuotaStr) {
		this.cuotaStr = cuotaStr;
	}
	public String getMontoCuotaStr() {
		montoCuotaStr = dec.format(getMontoCuota());
		return montoCuotaStr;
	}
	public void setMontoCuotaStr(String montoCuotaStr) {
		this.montoCuotaStr = montoCuotaStr;
	}
	public String getTotalPagarStr() {
		totalPagarStr = dec.format(getTotalPagar());
		return totalPagarStr;
	}
	public void setTotalPagarStr(String totalPagarStr) {
		this.totalPagarStr = totalPagarStr;
	}
	public String getTotalPagarOtroStr() {
		totalPagarOtroStr = dec.format(getTotalPagarOtro());
		return totalPagarOtroStr;
	}
	public void setTotalPagarOtroStr(String totalPagarOtroStr) {
		this.totalPagarOtroStr = totalPagarOtroStr;
	}
	public void setDiferenciaStr(String diferenciaStr) {
		this.diferenciaStr = diferenciaStr;
	}
	
	public String getDiferenciaStr() {
		diferenciaStr = dec.format(getDiferencia());
		return diferenciaStr;
	}
	public String getMontoInStr() {
		return montoInStr;
	}
	public void setMontoInStr(String montoInStr) {
		this.montoInStr = montoInStr;
	}
	public String getCuotaInStr() {
		return cuotaInStr;
	}
	public void setCuotaInStr(String cuotaInStr) {
		this.cuotaInStr = cuotaInStr;
	}
	public String getFreqStr() {
		return freqStr;
	}
	public void setFreqStr(String freqStr) {
		this.freqStr = freqStr;
	}
	public ServiceCallingDao getServicecallingRepository() {
		return servicecallingRepository;
	}
	public void setServicecallingRepository(
			ServiceCallingDao servicecallingRepository) {
		this.servicecallingRepository = servicecallingRepository;
	}
	
	public SimulationCacheService getSimulationCacheService() {
		return simulationCacheService;
	}
	public void setSimulationCacheService(
			SimulationCacheService simulationCacheService) {
		this.simulationCacheService = simulationCacheService;
	}
	public ComparisonService getComparisonService() {
		return comparisonService;
	}


	public void setComparisonService(ComparisonService comparisonService) {
		this.comparisonService = comparisonService;
	}


	public Double getCat() {
		return cat;
	}

	public void setCat(Double cat) {
		this.cat = cat;
	}
	
	public String getCatStr() {
		if(getCat() != null)
			catStr = ""+((double)Math.round((getCat())*100)/100);
		return catStr;
	}

	public void setCatStr(String catStr) {
		this.catStr = catStr;
	}
	
	
	public Double getMontoComisionApert() {
		return montoComisionApert;
	}

	public void setMontoComisionApert(Double montoComisionApert) {
		this.montoComisionApert = montoComisionApert;
	}
	
	public Double getCuotaByFormula(boolean flagIva){
		setNumCuota(generaNumCuotas());
		setTasaPeriodo(generaTasaPeriodo());
		Double intper = getTasaPeriodo();
		//System.out.println( getNumCuota()+"  --  "+ intper);
		
		Double num = (Math.pow((1+intper), getNumCuota()))*intper;
		Double den = (Math.pow((1+intper), getNumCuota()))-1;
		Double montoAPagar = getMonto()*(num/den);
//		System.out.println("Monto: "+getAmmount());
//		System.out.println("Monto Cuota1: "+montoAPagar);
		setMontoCuota((double)Math.round((montoAPagar)*100)/100);
//		System.out.println("Monto Cuota2: "+getMontoCuota());
		setTotalPagar((double)Math.round((montoAPagar*getNumCuota())*100)/100);
//		System.out.println("Total a pagar: "+getTotalPagar());
		setInteres(generaInteres());
		
		calculaCat();
		
		return (double)Math.round(montoAPagar*100)/100;
		
	}
	
public void calculaCat(){
		
		String valorCuotas = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();

		ELContext  elContext = facesContext.getELContext();
		ELResolver resolver  = facesContext.getApplication().getELResolver();
		
		TablaAmortizacion amort = (TablaAmortizacion)resolver.getValue(elContext, null, "tablaAmortizacion");;
		
		
		
		amort.setAmmount(getMonto() );
		amort.setTerm_id(	getNumCuota() ) ;
		amort.setRate(getTasaTotal() );
		amort.setdPayment( Double.parseDouble( getMontoCuotaStr().replace("$", "").replace(",", "") ) );
		amort.setTotalPayment( Double.parseDouble(  getTotalPagarStr().replace("$", "").replace(",", "") ) );
		amort.setFreq(	getFrequencyStr() );
		amort.setCat( "" );
		amort.setComision( (getComisionApertura() +"").replace("$", "").replace(",", "")  );
		
		amort.setSafiSimulation(true);
		
		
		
		
		amort.calculaTabla();
		int x = 0;
		for(Amortization a : amort.getTamort()){
			
			if(x != 0)
				valorCuotas += ",";
			
			x++;
			
			Double interes =  Double.parseDouble( a.getInterest().replace("$", "").replace(",", "") );
			
			Double interesSinIva = interes/(1.16);
			
			interesSinIva = Double.parseDouble( (dec.format(interesSinIva)).replace("$", "").replace(",", "") );
			
			Double capital = Double.parseDouble( a.getCapital().replace("$", "").replace(",", "") );
			
			valorCuotas += (capital+interesSinIva);
			
		}
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		Double catRes =  simulatorService.getCatBySafi( ( getMonto() - (getMonto()*.05) ), valorCuotas, getDiasFreq());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		
		long difCns_R_2 = cal2.getTimeInMillis() - cal1.getTimeInMillis();
		 System.out.println("Tiempo en calcular el  cat: "+difCns_R_2+" milisegundos");
		
		System.out.println("Resultado Cat: "+catRes);
		
		setCat(catRes);
		
	}
	
public Double getComisionApertura() {
		
		if( sesion != null && sesion.getOpeningCommission()!=null){
			comisionApertura = sesion.getOpeningCommission();
		}else{
			comisionApertura = 5.0D;
		}
		
		return comisionApertura;
	}


public int getDiasFreq() {
	return diasFreq;
}


public void setDiasFreq(int diasFreq) {
	this.diasFreq = diasFreq;
}


public SimulatorService getSimulatorService() {
	return simulatorService;
}


public void setSimulatorService(SimulatorService simulatorService) {
	this.simulatorService = simulatorService;
}
	
}