package mx.com.kubo.managedbeans;

import java.util.ArrayList;
import java.util.Date;

import mx.com.kubo.model.Frequency;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.PurposePK;
import mx.com.kubo.model.SimulationConfig;
import mx.com.kubo.model.Simulation_Cache;
import mx.com.kubo.model.Simulation_Cache_PK;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;

public abstract class SimulatorAMO extends SimulatorDMO 
{
	protected void init_partner_ID() 
	{
		   if(sesion != null && sesion.getProspectus_id() != null && sesion.getCompany_id() != null)
		   {			  
			   if(sesion.getArea() != null && (sesion.getArea()=='M' || sesion.getArea()=='I'))
				{
					
				   area = sesion.getArea().toString();
				   
				} else {
					
					if(sesion.getArea() != null)
					{
						area = sesion.getArea().toString();
					}
					
					proyectLoan = proyectLoanService.getMaxProyectLoanByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
					
					if(proyectLoan != null)
					{						
						setLoanTypeID(proyectLoan.getLoan_type());
						
						if(proyectLoan.getProyect().getPartner_id() != null && partnerID == null)
						{
							partnerID =	proyectLoan.getProyect().getPartner_id().toString();
						}
							
					} else {
						
						membershipPK = new MembershipPK(sesion.getProspectus_id(), sesion.getCompany_id());
						
						membership = membershipservice.getMembershipById(membershipPK);
						
						if(membership.getRegistration_reason() != null)
						{
							partnerID = membership.getRegistration_reason().getPartner_id();
						}					
					}
					
					if(sesion != null && sesion.getPartner() != null && partnerID == null)
					{
						partnerID = "BRD";
					}						
				}
			   
		   	} else if(sesion != null && sesion.getPartner() != null) {
				
		   		
		   		if(sesion.getPartner().equals("10000034") || sesion.getPartner().equals("ALIANZA BARARED")){
		   		
		   			partnerID = "BRD";
		   		
		   		}else{
		   			partnerID = sesion.getPartner();
		   		}
			   
			}
		   
		   System.out.println( "\n\n******* SimulationConfig Partner: "+sesion.getPartner()+" *******\n\n" );
	}
	
	protected void init_simulation_config() 
	{
		simulationConfig = new SimulationConfig();
		
		if(area == null)
		{
			area = "L";
			
			if(partnerID == null)
			{
				if( sesion == null || sesion.getCompany_id() == null)
				{
					simulationConfig = simulationConfigService.getSimulationByArea(area, 1);
				} else {
					simulationConfig = simulationConfigService.getSimulationByArea(area, sesion.getCompany_id());
				}
				
			} else {
				
				if( sesion == null || sesion.getCompany_id() == null)
				{
					simulationConfig = simulationConfigService.getSimulationByPartnerIDandArea(partnerID,area, 1);
				} else {
					simulationConfig = simulationConfigService.getSimulationByPartnerIDandArea(partnerID,area, sesion.getCompany_id());
				}
			}
			
			area = null;
			
		} else if(getLoanTypeID() != null && partnerID != null && area != null){
				
			simulationConfig = simulationConfigService.getSimulationByLoanTypeIDandPartnerIDandArea(getLoanTypeID(), partnerID, area, sesion.getCompany_id());
			
			if( simulationConfig == null )
			{				
				simulationConfig = simulationConfigService.getSimulationByPartnerIDandArea(partnerID, area, sesion.getCompany_id());					
			}
			
			if( simulationConfig == null )
			{				
				simulationConfig = simulationConfigService.getSimulationByLoanTypeIDandArea(getLoanTypeID(), area, sesion.getCompany_id());					
			}
			
		} else if(getLoanTypeID() != null && area != null){
				
			simulationConfig = simulationConfigService.getSimulationByLoanTypeIDandArea(getLoanTypeID(), area, sesion.getCompany_id());
				
		} else if(partnerID != null && area != null){
				
			simulationConfig = simulationConfigService.getSimulationByPartnerIDandArea(partnerID, area, sesion.getCompany_id());
			
		} else if(area != null){
				
			simulationConfig = simulationConfigService.getSimulationByArea(area, sesion.getCompany_id());
			
		} else {
			
			simulationConfig = simulationConfigService.getSimulationByArea("L", 1);
		}
		
		if(simulationConfig == null)
		{			
			area = "L";
			
			if( sesion == null || sesion.getCompany_id() == null)
			{
				simulationConfig = simulationConfigService.getSimulationByArea(area, 1);
				
			} else {
				
				simulationConfig = simulationConfigService.getSimulationByArea(area, sesion.getCompany_id());
			}
			
			area = null;				
		}
	   			
		if(simulationConfig != null)
		{
			setMontoMax(""+simulationConfig.getMax_amount());
			setMontoMin(""+simulationConfig.getMin_amount());
			setTermMax(""+simulationConfig.getMax_term());
			
		} else {
			
			setMontoMax("50000");
			setMontoMin("5000");
			setTermMax("18");
		}	
	}
	
	protected void init_list_frequency() 
	{
		listPurpose = purposeService.getPurposeList();
		
		listFrequencyTmp = frequencyService.getFrequencyList();
		
		listFrequency = new ArrayList<Frequency>();
		
		for(Frequency ls :listFrequencyTmp )
		{
			switch (ls.getFrequencyPK().getFrequency_id())
			{
				
				case 1://Semanal
					ls.setName("Semanas");
					break;
					
				case 2: //Catorcenal
					ls.setName("Catorcenas");
					break;
					
				case 3: //Quincenal
					ls.setName("Quincenas");
					break;
					
				case 4: //Mensual
					ls.setName("Meses");
					break;
					
			}
			
			listFrequency.add(ls);
		}
	}
	
	protected void init_PLD() 
	{
		try
		{			
			prevencionld = null;
			
			if(sesion.getProspectus_id() != null )
				prevencionld = prevencionldservice.getPrevencionLDByProspectus(sesion.getProspectus_id() , sesion.getCompany_id() );
			
			if(prevencionld != null){
				
				setHasPLD(true);
				
			}else{
				
				setHasPLD(false);
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
			setHasPLD(false);
		}	
	}
	
	protected void initSimulador_()
	{		
		if( sesion.getProspectus_id() != null && sesion.getArea() != null && sesion.getArea().toString().equals("L") )
		{			
			SimulatorBean sim = simulatorService.getMaxSimulationProspect(sesion.getProspectus_id(), sesion.getCompany_id());
			
			if( sim != null )
			{			
				if(  sim.getAmmount() > Double.parseDouble( getMontoMax() )  )
				{
					ammount = Double.parseDouble( getMontoMax() );
				}
				
				if(  sim.getAmmount() > Double.parseDouble( getMontoMin() )  )
				{
					ammount = Double.parseDouble( getMontoMin() );
				}
				
				if(membership == null)
				{					
					MembershipPK mpk = new MembershipPK(sesion.getProspectus_id() , sesion.getCompany_id());
					
					membership = membershipservice.getMembershipById( mpk );
				}
				
				if( membership.getRegistration_reason() != null && membership.getRegistration_reason().getPartner() != null && membership.getRegistration_reason().getPartner().getPartnerPK().getPartner_id().equals("VMK") )
				{				
					if( frequency_id != 1 )
					{						
						frequency_id = 1;						
					}
					
					if( getTerm_id(  ) > Integer.parseInt( getTermMax() ) )
					{						
						setTerm_id( Integer.parseInt( getTermMax() ) );						
					}
					
					int mint = simulationConfig.getMin_term() * 4;
					
					if( getTerm_id(  ) <  mint  )
					{
						setTerm_id( mint );
					}					
				}
			
			} else {
				
				ammount = Double.parseDouble( getMontoMax() );
				
				if( membership.getRegistration_reason() != null && membership.getRegistration_reason().getPartner() != null && membership.getRegistration_reason().getPartner().equals("VMK") )
				{					
					frequency_id = 1;
					setTerm_id( 16 );					
				}				
			}									
		}		
	}
	
	protected boolean isConnected()
	{		
		if(sesion.getProspectus_id()!=null&&sesion.getProspectus_id()>0)
		{
			return true;
			
		} else {
			
			return false;
		}		
	}
	
	protected void setActualRate()
	{
		if(isConnected() && sesion.getArea().toString().equals("L"))
		{
			ProyectLoan proyectLoan=proyectLoanService.getMaxProyectLoanByProspect(sesion.getProspectus_id(),sesion.getCompany_id());
			
			if(proyectLoan!=null && proyectLoan.getScoring()!=null && proyectLoan.getScoring().getRate()!=null && proyectLoan.getScoring().getStatus()!=null && proyectLoan.getScoring().getStatus()==1  )
			{
				sesion.setRate(proyectLoan.getScoring().getRate());
			}
		}
	}
	
	protected Double generaInteres()
	{
		Double i = ((getMontoCuota()*getNumCuota())-getAmmount());
		double interes = i/(1+getIva());
		double iva = (double)Math.round((i - interes)*100)/100;
		setIvaInteres(iva);
		return (double)Math.round(interes*100)/100;
	}
	
	protected Double generaTasaPeriodo(boolean flagIva)
	{
		/*
		double ta = getTasaTotal();
		double n = Double.parseDouble(getFrequencyAnual()+"");
		//return (double)Math.round((ta/n)*100)/100;
		double tp = 0D;
		if(flagIva){
			tp = (double)Math.round((((ta/100)/n)*(1+getIva()))*10000)/10000;
		}else{
			tp = (double)Math.round((((ta/100)/n))*10000)/10000;
		}
		setTasaPeriodoPorc((double)Math.round((tp*100)*100)/100);
		return tp;
		*/
		
		double taP = getTasaTotal()/100;
		double tp = 0D;
		
		if(flagIva){
			
			tp = (double)Math.round(((taP/360)*(1+getIva()))*100000000)/100000000;
			
		}else{
			
			tp = (double)Math.round((taP/360)*100000000)/100000000;
			
		}
		
		return tp*getDiasFreq();		
	}
	
	protected void saveSimulatorCache()
	{
		try
		{
			
			Simulation_Cache_PK simulationCachePK = new Simulation_Cache_PK();
			Simulation_Cache simulationCache = new Simulation_Cache();
			
			simulationCachePK.setCompany_id(1);
			
			simulationCache.setPk(simulationCachePK);
			simulationCache.setAmmount(getAmmount());
			simulationCache.setTerm_id(getTerm_id());
			simulationCache.setPayment(getMontoCuota());
			simulationCache.setFrequency_id(getFrequency_id());
			simulationCache.setNum_payments(getNumCuota());
			simulationCache.setTotal_payment(getTotalPagar());
			simulationCache.setPeriod_rate(getTasaPeriodo());
			simulationCache.setYearly_rate(getTasaTotal());
			simulationCache.setMx_cat(getCat());
			simulationCache.setCreation_date(new Date());
			
			simulationCacheService.add(simulationCache);
			
		} catch(Exception e){
			
			e.printStackTrace();
		}					
	}
	
	protected void addSimulatorAnonimo(boolean isSafi) 
	{
		SimulatorPK pk    = new SimulatorPK(0, 0, 1);			
		SimulatorBean sim = new SimulatorBean();
		
		sim.setTitle(null);
		sim.setBc_score(null);
		sim.setAmmount(getAmmount());
		sim.setDate(new Date());
		sim.setFrequency_id(getFrequency_id());
		sim.setNum_payments(getNumCuota());
		sim.setPayment(getMontoCuota());
		sim.setPeriod_rate(getTasaPeriodo());
		sim.setPurpose_id(getPurpose_id());
		sim.setSimulatorPK(pk);
		sim.setTerm_id(getTerm_id());
		sim.setTotal_interest(getInteres());
		sim.setTotal_payment(getTotalPagar());
		sim.setType('M');
		sim.setType_id(null);
		sim.setYearly_rate(getTasaTotal());
		sim.setYearly_interest(null);
		sim.setMx_cat(getCat());
		
		if(isSafi)
		{
			sim.setOrigin("S");
			
		} else {
			
			sim.setOrigin("K");
		}
		
		try
		{
			simulatorService.add(sim);
			
		} catch (Exception e) {
			
				e.printStackTrace();
		}
	}
	
	protected void addSimulator(boolean isSafi) 
	{
		SimulatorPK pk    = new SimulatorPK(0, sesion.getProspectus_id(), sesion.getCompany_id());			
		SimulatorBean sim = new SimulatorBean();
		
		sim.setTitle(null);
		sim.setBc_score(null);
		sim.setAmmount(getAmmount());
		sim.setDate(new Date());
		sim.setFrequency_id(getFrequency_id());
		sim.setNum_payments(getNumCuota());
		sim.setPayment(getMontoCuota());
		sim.setPeriod_rate(getTasaPeriodo());
		sim.setPurpose_id(getPurpose_id());
		sim.setSimulatorPK(pk);
		sim.setTerm_id(getTerm_id());
		sim.setTotal_interest(getInteres());
		sim.setTotal_payment(getTotalPagar());
		sim.setType('M');
		sim.setType_id(null);
		sim.setYearly_rate(getTasaTotal());
		sim.setYearly_interest(null);
		sim.setMx_cat(getCat());
		
		if(isSafi)
		{
			sim.setOrigin("S");
		}else{
			sim.setOrigin("K");
		}
		
		try{
			simulatorService.add(sim);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void updateProyectLoan() 
	{
		if (proyectLoan != null && proyectLoan.getStatus_id() == 0)
		{
			if(proyectLoan.getStatus_id() != null && proyectLoan.getStatus_id() == 0)
			{
				if(proyectLoan.getOpening_commission() != null)
				{
					double cuota_simulador    = getMontoCuota();
					double cuota_proyect_loan = proyectLoan.getPayment();
					double cuota_apertura     = getAmmount() * proyectLoan.getOpening_commission() / 100;
				
					if(cuota_simulador != cuota_proyect_loan)
					{
						proyectLoan.setAmmount(getAmmount());
						proyectLoan.setMin_ammount(getAmmount());
						proyectLoan.setMx_cat(getCat());
						proyectLoan.setTerm_id(getTerm_id());
						proyectLoan.setFrequency_id(getFrequency_id());
						proyectLoan.setPayment(getMontoCuota());
						
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
	
	public Integer generaNumCuotas()
	{
		int freq = getFrequency_id();
		int num = getTerm_id();
		
		switch (freq)
		{
			case 1://Semanal
				//num= (int)Double.parseDouble(Math.rint((getTerm_id()*52)/12)+"");
				setFrequencyAnual(52);
				setFrequencyStr("semanales");
				setFrequencyStr2("semanas");
				setFrequencyName("Semanal");
				setDiasFreq(7);
				setFreqStr("S");
				break;
			case 2: //Catorcenal
				//num= (int)Double.parseDouble(Math.rint((getTerm_id()*26)/12)+"");
				setFrequencyAnual(26);
				setFrequencyStr("catorcenales");
				setFrequencyStr2("catorcenas");
				setFrequencyName("Catorcenal");
				setDiasFreq(14);
				setFreqStr("C");
				break;
			case 3: //Quincenal
				//num= getTerm_id()*2;
				setFrequencyAnual(24);
				setFrequencyStr("quincenales");
				setFrequencyStr2("quincenas");
				setFrequencyName("Quincenal");
				setDiasFreq(15);
				setFreqStr("Q");
				break;
			case 4: //Mensual
				//num= getTerm_id();
				setFrequencyAnual(12);
				setFrequencyStr("mensuales");
				setFrequencyStr2("meses");
				setFrequencyName("Mensual");
				setDiasFreq(30);
				setFreqStr("M");
				break;
		}
		return num;

	}
	
	public void setFrequency_id(int frequency_id) {
		this.frequency_id = frequency_id;
		if(frequency_id!=0)
			initFrequency(frequency_id);
		setFrequencyName("");
	}
	
	public String getTotalRecibir() 
	{		
		Double d =  getAmmount() - (getAmmount() * (getComisionApertura()/100));
		
		d = (double) Math.round(d*100)/100;
		
		totalRecibir = dec.format(d);
		
		return totalRecibir;
	}
	
	public Double getComisionApertura() 
	{	
		if(sesion.getOpeningCommission()!=null)
		{
			comisionApertura = sesion.getOpeningCommission();
		}
		
		return comisionApertura;
	}
	
	public Double getTasaTotal() 
	{	
		if(sesion.getRate() != null && !sesion.getArea().toString().equals("M"))
		{
			tasaTotal = sesion.getRate();
		}
		
		return tasaTotal;
	}

	public String getCatStr() 
	{
		if(getCat() != null)
		{
			catStr = "" + ((double) Math.round((getCat()) * 100) / 100);
		}
		
		return catStr;
	}
	
	public String getMailAction() 
	{
		if(isConnected())
		{
			mailAction = "<a id='send' href='#formRegistro'>Enviamos tu simulación por mail (reg).</a>";
			
		} else {
			
			mailAction = "<a id='inlineMail' href='#formRegistro'>Enviamos tu simulación por mail.</a>";
		}
		
		return mailAction;
	}
	
	public String getGuardarAction() 
	{
		
		if(isConnected())
			guardarAction = "<a id='save' href='#'>Guarda tu simulación para próximas visitas (reg).</a>";
		else{
			guardarAction = "<a id='inlineSave' href='#formRegistro'>Guarda tu simulación para próximas visitas.</a>";
		}
		return guardarAction;
	}
	
	public boolean isClientePriceShoes()
	{		
		MembershipPK mpk = new MembershipPK();
		
		mpk.setCompany_id(sesion.getCompany_id());
		mpk.setProspectus_id(sesion.getProspectus_id());
		
		Membership member =  membershipservice.getMembershipById(mpk);
		
		if( member.getRegistration_reason_id() != null && member.getRegistration_reason_id() == 8 )
		{
			
			return true;
			
		}else{
			
			return false;			
		}		
	}
	
	public void setPurpose_id(int purpose_id) 
	{
		this.purpose_id = purpose_id;
		
		if(purpose_id != 0)
		{
			initPurpose(purpose_id);
		}else{
			setPurpose( new Purpose() );
		}
		
		setPurposeName("");
	}


	public String getFrequencyName() {
		
		int freq = getFrequency_id();
		switch (freq){
			case 1://Semanal
				frequencyName="Semanal";
				break;
			case 2: //Catorcenal
				frequencyName="Catorcenal";
				break;
			case 3: //Quincenal
				frequencyName = "Quincenal";
				break;
			case 4: //Mensual
				frequencyName = "Mensual";
				break;
		}
		
		
		return frequencyName;
	}

	public String getAmmountStr() 
	{		
		ammountStr = formatDec(dec.format(this.ammount));
		ammountStr = ammountStr.split("\\.")[0];
		return ammountStr;
	}

	public void setAmmountStr(String ammountStr) {
		this.ammountStr = ammountStr;
		setAmmount(Float.parseFloat(ammountStr.replaceAll(",", "")));
	}
	
	public String getTotalPagarStr() 
	{
		if(getTotalPagar() != null && getTotalPagar() > 0)
		{
			return formatDec(dec.format(getTotalPagar()));
			
		} else {
			
			return "No disponible";
		}
	}
	
	public String getMontoCuotaStr() 
	{
		if(getMontoCuota() != null && getMontoCuota() > 0)
		{
			return formatDec(dec.format(getMontoCuota()));
		} else {
			return "No disponible";
		}
	}
	
	public String getIvaInteresStr() 
	{
		return formatDec(dec.format(getIvaInteres()));
	}
	
	public String getInteresStr() 
	{
		return formatDec(dec.format(getInteres()));
	}
	
	private void initFrequency(int frequency_id)
	{
		setFrequency(frequencyService.getFrequencyById(frequency_id));
	}
	
	private void initPurpose(int purpose_id){
		
		setPurpose(purposeService.getPurposeById(new PurposePK(purpose_id, 1)));
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
	
	/*
	 private Double generaTEA(){
		Double t = getTasaTotal();
		double n = Double.parseDouble(getFrequencyAnual()+"");
		Double t1 = (double)Math.round((t/(n))*100)/100;
		Double t2 = Math.pow((1+t1), (1/n));
		
		Double t3 = (t2-1)*100;
		
		Double t4 = (double)Math.round(t3*100)/100;
		return t4;
	}
	*/
	
//	private Double generaTotal(){
//	return (double)Math.round((getAmmount()+getInteres()+getIvaInteres())*100)/100;
//}
}
