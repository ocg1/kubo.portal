package mx.com.kubo.managedbeans;

import java.util.Date;

import javax.faces.context.FacesContext;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.tools.Utilities;

public abstract class HeaderBeanAMO extends HeaderBeanDMO
{
	protected boolean isPassValid( String pwd )
	{
		Integer days = 0;
		boolean flag = false;
		
		pwd = Utilities.encrypt(pwd);
		
		faces = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		if( sesion.getArea() == 'L' )
		{			
			sysPK = new SystemParamPK();
			
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(19); //dias de validez de password para Acreditado
			
			sysParam = service_system_param.loadSelectedSystemParam(sysPK) ;
			
			days = Integer.parseInt(sysParam.getValue());
		}
		
		if( sesion.getArea() == 'I' )
		{
			
			sysPK = new SystemParamPK();
			
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(20); //dias de validez de password para Acreditado
			
			sysParam = service_system_param.loadSelectedSystemParam(sysPK) ;
			
			days = Integer.parseInt(sysParam.getValue());
		}
		
		if( sesion.getArea() == 'M' )
		{			
			sysPK = new SystemParamPK();
			
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(21); //dias de validez de password para Acreditado
			
			sysParam = service_system_param.loadSelectedSystemParam(sysPK) ;
			
			//TODO parte que valida que el password del coach no expire
			
			MembershipPK mpk = new MembershipPK();
			
			mpk.setCompany_id(sesion.getCompany_id());
			mpk.setProspectus_id(sesion.getProspectus_id());
			
		    Membership member = membershipService.getMembershipById(mpk);
		    
		    if ( member.getPerson().getProspectus().getRole_id() != null ){
		    	
		    	if( member.getPerson().getProspectus().getRole_id() == 15 )
		    	{
		    		days = (-1);
		    	
		    	}else{
		    		days = Integer.parseInt(sysParam.getValue());
		    	}
		    	
		    }else{
			
		    	days = Integer.parseInt(sysParam.getValue());
			
		    }
		    //TODO fin de parte que valida que el password del coach no expire
		}
			
			if(days != (-1)){
		
					PasswordHistory pwdH =  passwordhistoryservice.getPasswordHistorySelec(pwd, sesion.getProspectus_id(), sesion.getCompany_id());
					
					if( pwdH != null ){
						
						Date now = new Date();
						
						Long diferencia= ( now.getTime() - pwdH.getDate_changed().getTime() )/ MILLSECS_PER_DAY;
						
						if(diferencia > days){
							
							flag = false;
							
						}else{
							
							flag = true;
							
						}
						
					}else{
						
						PasswordHistoryPK pwdhpk = new PasswordHistoryPK();
						pwdhpk.setCompany_id(sesion.getCompany_id());
						pwdhpk.setProspectus_id(sesion.getProspectus_id());
						
						pwdH = new PasswordHistory();
						pwdH.setPassword(pwd);
						pwdH.setDate_changed(new Date());
						pwdH.setPwdhpk(pwdhpk);
						
						if( passwordhistoryservice.savePasswordHistory(pwdH) ){
							flag = true;
						}
						else{
							flag = false;
						}
					}
			}else{
				flag = true;
			}
			
			return flag;
	}
	
	protected void inicializaSimulador(Integer company_id,Integer prospectus_id, boolean  isSafi)
	{
		faces     = FacesContext.getCurrentInstance();
		external  = faces.getExternalContext();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
				
		simulator = (Simulator) resolver.getValue(elContext, null, "simulator");
		sesion    = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		sim = simulatorService.getMaxSimulationProspect(prospectus_id, company_id) ;
		
		if(sim != null)
		{
			simulator.setAmmount(sim.getAmmount());
			simulator.setFrequency_id(sim.getFrequency_id());
			
			if(sesion.getRate()!=null)
			{
				simulator.setTasaTotal(sesion.getRate());
				
			} else {
				
				simulator.setTasaTotal(52.6D);
			}
			
			simulator.setTerm_id(sim.getTerm_id());
			simulator.setPurpose_id(sim.getPurpose_id()==null?0:sim.getPurpose_id());
		}else{
			simulator.setPurpose_id(0);
		}
		
		simulator.simulaCred( isSafi );		
	}
}
