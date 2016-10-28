package mx.com.kubo.portal.simulador;

import java.util.Date;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.sun.faces.mgbean.ManagedBeanCreationException;

import mx.com.kubo.managedbeans.TablaAmortizacion;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.managedbeans.registro.documentacion.AddPldDocument;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.Simulation_Cache;
import mx.com.kubo.model.Simulation_Cache_PK;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;

public abstract class CreditSimulatorAMO extends CreditSimulatorDMO 
{
	protected void init_rate() 
	{
		if(isConnected && sesion.getArea() != null && sesion.getArea().toString().equals("L"))
		{
			int prospectus_id = sesion.getProspectus_id();
			int company_id    = sesion.getCompany_id();
			
			proyectLoan = proyectLoanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
		}
		
		if(isConnected && sesion.getArea().toString().equals("L"))
		{
			int prospectus_id = sesion.getProspectus_id();
			int company_id    = sesion.getCompany_id();
			
			proyectLoan = proyectLoanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
			
			Scoring score = null;
			Double rate = null;
			Integer status = null;
			
			if(proyectLoan != null)
			{
				score = proyectLoan.getScoring();
			}			
			
			if(score != null)
			{
				rate   = score.getRate();
				status = score.getStatus();
			}
			
			if(rate != null && status !=null && status == 1)
			{
				sesion.setRate(rate);
			}
		}
	}
	
	protected Integer generaNumCuotas()
	{						
		switch (frequency_id)
		{
			case SEMANAL:
				//num= (int)Double.parseDouble(Math.rint((getTerm_id()*52)/12)+"");
				frequencyAnual = 52;
				frequencyStr   = "semanales";
				frequencyStr2  = "semanas";				
				frequencyName  = frequency.getName();
				//setFrequencyName("Semanal");
				diasFreq = 7;
				freqStr  = "S";
			break;
				
			case CATORCENAL:
				//num= (int)Double.parseDouble(Math.rint((getTerm_id()*26)/12)+"");
				frequencyAnual = 26;
				frequencyStr   = "catorcenales";
				frequencyStr2  = "catorcenas";				
				frequencyName  = frequency.getName();
				//setFrequencyName("Catorcenal");
				diasFreq = 14;
				freqStr  = "C";
			break;
			
			case QUINCENAL:
				//num= getTerm_id()*2;
				frequencyAnual = 24;
				frequencyStr   = "quincenales";
				frequencyStr2  = "quincenas";
				frequencyName  = frequency.getName();
				//setFrequencyName("Quincenal");
				diasFreq = 15;
				freqStr  = "Q";
			break;
			
			case MENSUAL:
				//num= getTerm_id();
				frequencyAnual = 12;
				frequencyStr   = "mensuales";
				frequencyStr2  = "meses";				
				frequencyName  = frequency.getName();
				//setFrequencyName("Mensual");
				diasFreq = 30;
				freqStr  = "M";
			break;
		}
		
		return term_id;
	}
	
	protected Double generaTasaPeriodo(boolean flagIva)
	{		
		double taP = tasaTotal / 100;
		double tp = 0D;
		
		if(flagIva){
			
			tp = (double)Math.round(((taP/360)*(1+iva))*100000000)/100000000;
			
		}else{
			
			tp = (double)Math.round((taP/360)*100000000)/100000000;
			
		}
		
		return tp*diasFreq;
		
	}
	
	protected Double generaInteres()
	{
		Double i = ((montoCuota* term_id) - ammount);
		
		double interes = i / ( 1 + iva);
		
		double iva = (double) Math.round((i - interes) * 100) / 100;
		
		ivaInteres = iva;
		
		return (double)Math.round(interes*100)/100;
	}
	
	protected String formatDec(String valor)
	{
		String res;
		valor = valor.replace("$", "");
		
		String[] arrayValor = valor.split("\\.");
		
		if(arrayValor.length < 2)
		{
			//res=valor+".00";
			res = valor;
			
		} else {
			
			if(arrayValor[1].length() < 2)
			{
				arrayValor[1]= arrayValor[1] + "0";
			}
			
			res = arrayValor[0]+"."+arrayValor[1];
		}
		
		return res;
	}
	
	public void generaMontoCuota2()
	{	
		SAFI_cuota_credito = new SAFICuotaCreditoIMP(ammount, term_id, frequency_id, freqStr, tasaPeriodo);
		SAFI_cuota_credito.setSesion(sesion);
		SAFI_cuota_credito.init();
		
		cat        = SAFI_cuota_credito.getCat();
		montoCuota = SAFI_cuota_credito.getMontoCuota();
		totalPagar = SAFI_cuota_credito.getTotalPagar();
		numCuota   = SAFI_cuota_credito.getNumCuota();
		
		flagSaveSimulationCache = SAFI_cuota_credito.isFlagSaveSimulationCache();
	}
	
	protected Double getCuotaByFormula(boolean flagIva, boolean isSafiSimulation)
	{
		numCuota      = generaNumCuotas();
		tasaPeriodo   = generaTasaPeriodo(flagIva);
		Double intper = tasaPeriodo;
		//System.out.println( getNumCuota()+"  --  "+ intper);
		
		Double num = (Math.pow((1+intper), numCuota)) * intper;
		Double den = (Math.pow((1+intper), numCuota)) - 1;
		Double montoAPagar = ammount * (num / den);
		
		Double payment = Math.ceil(montoAPagar);
		
		montoCuota = payment;
		
		totalPagar = (double) Math.round((montoAPagar * numCuota) * 100) / 100;
		
		interes = generaInteres();
				
		calculaCat(isSafiSimulation );
		
		return (double)Math.round(montoAPagar*100)/100;
		
	}
	
	private void calculaCat( boolean isSafiSimulation )
	{	
		faces = FacesContext.getCurrentInstance();
	
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		amortization = (TablaAmortizacion) resolver.getValue(elContext, null, "tablaAmortizacion");
		
		CAT_calculator = new CATCalculatorIMP(ammount, term_id, diasFreq, montoCuota, tasaTotal, comisionApertura, frequencyStr, totalRecibir);
		CAT_calculator.setTotalPagar(totalPagar);
		CAT_calculator.setAmortization(amortization);
		CAT_calculator.setSafiSimulation(isSafiSimulation);
		CAT_calculator.setSesion(sesion);
		CAT_calculator.init();
	
		cat = CAT_calculator.getCat();		
	}	
	
	protected void saveSimulatorCache()
	{
		try
		{			
			simulationCachePK = new Simulation_Cache_PK();
			simulationCache   = new Simulation_Cache();
			
			simulationCachePK.setCompany_id(1);
			
			simulationCache.setPk(simulationCachePK);
			simulationCache.setAmmount(ammount);
			simulationCache.setTerm_id(term_id);
			simulationCache.setPayment(montoCuota);
			simulationCache.setFrequency_id(frequency_id);
			simulationCache.setNum_payments(numCuota);
			simulationCache.setTotal_payment(totalPagar);
			simulationCache.setPeriod_rate(tasaPeriodo);
			simulationCache.setYearly_rate(tasaTotal);
			simulationCache.setMx_cat(cat);
			simulationCache.setCreation_date(new Date());
			
			simulationCacheService.add(simulationCache);
			
		} catch(Exception e){
			
			e.printStackTrace();
		}						
	}
	
	public void saveSimulator()
	{		
		int prospectus_id = sesion.getProspectus_id();
		
		try
		{			
			prevencionld = null;
			
			if(sesion.getProspectus_id() != null )
			{
				prevencionld = prevencionldservice.getPrevencionLDByProspectus(sesion.getProspectus_id() , sesion.getCompany_id() );
			}
			
			if(prevencionld != null)
			{			
				hasPLD = true;
				
			} else {
				
				hasPLD = false;				
			}
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
			hasPLD = false;
		}
		
		listChangeByProspect = changeControlService.getListByChangeByProspectus(prospectus_id);
		
		//Para cuestiones de rendimiento me traigo la lista de cambios de ese prospectus.
		// Se determina que campos tuvieron cambios y no se actualizan en la tablade PLD
		
		
		//Consulta el registro PLD actual del prospecto.
		 if(listChangeByProspect!=null && listChangeByProspect.size()>0)
		 {			 
			 if(sesion.getArea()!='I')
			 {				 
			   	PrevencionLDPK prevPK = new PrevencionLDPK();				
				prevPK.setCompany_id(sesion.getCompany_id());
				prevPK.setProspectus_id(sesion.getProspectus_id());		
				
				prevencionld = prevencionldservice.getSelectedPrevencionLDById(prevPK);
				
			 }
			 
		   }
		
		if(hasPLD)
		{
			 if(!sesion.getArea().toString().equals("I"))
			 {
				if(listChangeByProspect!=null && listChangeByProspect.size()>0)
				{	
					boolean hasMovementFre=false; // bandera que determina si el campo movement_frequency sufrio cambios por parte del usuario. y esta ya no se actualiza.
					boolean hasMaxDeposit=false;// bandera que determina si el campo maximum_deposit sufrio cambios por parte del usuario. y esta ya no se actualiza.
					boolean hasMaxWithdraw=false;// bandera que determina si el campo maximum_withdraw sufrio cambios por parte del usuario. y esta ya no se actualiza.
					boolean hasMaxBalance=false;// bandera que determina si el campo maximum_balance sufrio cambios por parte del usuario. y esta ya no se actualiza.
					
					for (Change_control change_iter : listChangeByProspect) 
					{
						if(change_iter.getAfected_table().trim().equals("gn_mx_pld") && change_iter.getField().trim().equals("movement_frequency"))
							hasMovementFre=true;
						if(change_iter.getAfected_table().trim().equals("gn_mx_pld") && change_iter.getField().trim().equals("maximum_deposit"))
							hasMaxDeposit=true;
						if(change_iter.getAfected_table().trim().equals("gn_mx_pld") && change_iter.getField().trim().equals("maximum_withdraw"))
							hasMaxWithdraw=true;
						if(change_iter.getAfected_table().trim().equals("gn_mx_pld") && change_iter.getField().trim().equals("maximum_balance"))
							hasMaxBalance=true;
					}
					
					if(!hasMovementFre)
						prevencionld.setMovement_frequency(frequencyName);
					if(!hasMaxDeposit)
						prevencionld.setMaximum_deposit(montoCuota);
					if(!hasMaxWithdraw)
						prevencionld.setMaximum_withdraw(ammount);
					if(!hasMaxBalance)
						prevencionld.setMaximum_balance(montoCuota);
					
					
				}else{
					
					if(prevencionld==null){
						PrevencionLDPK prevPK = new PrevencionLDPK();				
						prevPK.setCompany_id(sesion.getCompany_id());
						prevPK.setProspectus_id(sesion.getProspectus_id());
						
						prevencionld = new PrevencionLD();
						prevencionld.setPk(prevPK);
					}
					
					prevencionld.setMovement_frequency(frequencyName);
					prevencionld.setMaximum_deposit(montoCuota);
					prevencionld.setMaximum_withdraw(ammount);
					prevencionld.setMaximum_balance(montoCuota);
				}
				
				prevencionld.setResource_origin("Del préstamo de Kubo.Financiero");
				
				prevencionldservice.updatePrevencionLD(prevencionld);
				
				//Para determinar si esta en la seccion de documentacion y PLD entonces hace el update sobre la seccion.
				//Si no esta en la sección de documentacion y PLD ho hace nada ya que al llegar a esta sección se actualizaran los datos.
				ELContext elContext = FacesContext.getCurrentInstance().getELContext();			
				NavigationBeanIMP navigation = (NavigationBeanIMP) FacesContext.getCurrentInstance().getApplication()
				        .getELResolver().getValue(elContext, null, "navigationBean");
				 
				 if(navigation!=null && navigation.getPaginaActual().equals("registro/documentationAndPld")){
					 try{
						 
						 AddPldDocument pldAndDoc = (AddPldDocument) FacesContext.getCurrentInstance().getApplication()
							        .getELResolver().getValue(elContext, null, "addPldDocument");
						 pldAndDoc.setPrevencionld(prevencionld);
						 RequestContext requestContext = RequestContext.getCurrentInstance();
						 
						 if( requestContext != null ){
							 requestContext.addPartialUpdateTarget("frm_questPLD");
						 }
					 
					 }catch(ManagedBeanCreationException me){
					
						 System.out.println( "No se pudo crear el managedBean AddPldDocument" );  
					 
					 }
					 
				 }
			 }
			
		}else{
			
			 if(sesion.getArea()!='I')
			 {				 
				if(prevencionld==null)
				{
					
					PrevencionLDPK prevPK = new PrevencionLDPK();				
					prevPK.setCompany_id(sesion.getCompany_id());
					prevPK.setProspectus_id(sesion.getProspectus_id());
					
					prevencionld = new PrevencionLD();
					prevencionld.setPk(prevPK);
					
				}
					
					prevencionld.setMovement_frequency(frequencyName);
					prevencionld.setMaximum_deposit(montoCuota);
					prevencionld.setMaximum_withdraw(ammount);
					prevencionld.setMaximum_balance(montoCuota);
					
					try
					{
						prevencionldservice.savePrevencionLD(prevencionld);
						
					}catch(Exception e){
						
					}
					
					hasPLD = true;
			 }
		}
	}
	
	protected void add_simulation() 
	{
		pk  = new SimulatorPK(0, sesion.getProspectus_id(), sesion.getCompany_id());			
		sim = new SimulatorBean();
		
		sim.setTitle(null);
		sim.setBc_score(null);
		sim.setAmmount(ammount);
		sim.setDate(new Date());
		sim.setFrequency_id(frequency_id);
		sim.setNum_payments(numCuota);
		sim.setPayment(montoCuota);
		sim.setPeriod_rate(tasaPeriodo);
		sim.setPurpose_id(purpose_id);
		sim.setSimulatorPK(pk);
		sim.setTerm_id(term_id);
		sim.setTotal_interest(interes);
		sim.setTotal_payment(totalPagar);
		sim.setType('M');
		sim.setType_id(null);
		sim.setYearly_rate(tasaTotal);
		sim.setYearly_interest(null);
		sim.setMx_cat(cat);
		
		if(isSafi)
		{
			sim.setOrigin("S");
			
		} else {
			
			sim.setOrigin("K");
		}
		
		try
		{
			simulatorService.add(sim);
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	protected void update_proyect_loan() 
	{
		if (proyectLoan != null && proyectLoan.getStatus_id() == 0)
		{
			if(proyectLoan.getStatus_id() != null && proyectLoan.getStatus_id() == 0)
			{
				if(proyectLoan.getOpening_commission() != null)
				{
					double cuota_simulador    = montoCuota;
					double cuota_proyect_loan = proyectLoan.getPayment();
					double cuota_apertura     = ammount * proyectLoan.getOpening_commission() / 100;
				
					if(cuota_simulador != cuota_proyect_loan)
					{
						proyectLoan.setAmmount(ammount);
						proyectLoan.setMin_ammount(ammount);
						proyectLoan.setMx_cat(cat);
						proyectLoan.setTerm_id(term_id);
						proyectLoan.setFrequency_id(frequency_id);
						proyectLoan.setPayment(montoCuota);
						
						proyectLoan.setOpening_commission_amount(cuota_apertura);
						
						if( proyectLoanService.update(proyectLoan) )
						{
							System.out.println("Simulator.simulaCred(): OK");
							
						} else {
							
							System.out.println("Simulator.simulaCred(): ERROR al actualizar proyect_loan");
						}
						
						if(purpose_id != 0){
							
							Proyect proyect = proyectLoan.getProyect();
							
							proyect.setPurpose_id(purpose_id);
							
							proyectService.update(proyect);
							
						}
						
						proyectLoan = proyectLoanService.getProyectLoanByProyectLoanID( proyectLoan.getProyectloanPk().getProyect_loan_id(), proyectLoan.getProyectloanPk().getProspectus_id(), proyectLoan.getProyectloanPk().getCompany_id());
						
					}
				}
			
			}			
		}
	}
	
	protected void add_simulation_anonimo() 
	{
		pk    = new SimulatorPK(0, 0, 1);			
		sim = new SimulatorBean();
		
		sim.setTitle(null);
		sim.setBc_score(null);
		sim.setAmmount(ammount);
		sim.setDate(new Date());
		sim.setFrequency_id(frequency_id);
		sim.setNum_payments(numCuota);
		sim.setPayment(montoCuota);
		sim.setPeriod_rate(tasaPeriodo);
		sim.setPurpose_id(purpose_id);
		sim.setSimulatorPK(pk);
		sim.setTerm_id(term_id);
		sim.setTotal_interest(interes);
		sim.setTotal_payment(totalPagar);
		sim.setType('M');
		sim.setType_id(null);
		sim.setYearly_rate(tasaTotal);
		sim.setYearly_interest(null);
		sim.setMx_cat(cat);
		
		if(isSafi)
		{
			sim.setOrigin("S");
			
		} else {
			sim.setOrigin("K");
		}
		
		try
		{
			simulatorService.add(sim);
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
}
