package mx.com.kubo.managedbeans.portal.ofertas;

import java.util.Date;

import mx.com.kubo.controller.threads.RespuestaConsultaMasiva;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.managedbeans.registro.consulta.Preaprobacion;
import mx.com.kubo.model.ConsultingManual;
import mx.com.kubo.model.ConsultingManualPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.portal.ofertas.ParserRenovacionAutomaticaIMP;

public abstract class ConsultaRenovacionAMO extends ConsultaRenovacionDMO 
{
	protected void init_score() 
	{
		if(score != null)
		{
			String renovation    = score.getIs_consulting_for_renovation();
			Date consulting_date = score.getResult_datetime();			
			is_risk_processed    = score.getRisk_processed() > 0 ? true : false;
			bur_sol_num          = score.getMx_solicitud_buro();
			
			Date today = new Date();
											
			long diff = today.getTime() - consulting_date.getTime();
			
		    long days = diff / 1000 / 60 / 60 / 24;
		    		    
		    boolean is_consulting_for_renovation = renovation != null && renovation.equals("S");
		    
		    if(!is_consulting_for_renovation)
		    {
		    	ProyectLoan lastProyectloan = service_proyect_loan.getMaxProyectLoanByProspectAndStatus(prospectus_id, company_id, BORRADOR);
		    	
		    	is_consulting_for_renovation = lastProyectloan != null ? true : false;
		    }		    		    		    		    		    		    
		    
		    consulta_vigente_OK = is_consulting_for_renovation && days < 31;		
		    
		    if(consulta_vigente_OK)
		    {
		    	if(is_risk_processed)
		    	{
			    	parser = new ParserRenovacionAutomaticaIMP();		
			    	parser.setProspectus_id(prospectus_id);
			    	parser.setCompany_id(company_id);
			    	parser.setScore(score);			
			    			    	
			    	ofert_ENABLED = parser.is_ofert_ENABLED();
	    		
			    	redirect_to_registro_ENABLED = !ofert_ENABLED;		
			    	
			    	if( redirect_to_registro_ENABLED && sesion.getArea() != null && sesion.getArea().toString().equals("L") ){
			    		navigation = (NavigationBeanIMP) resolver.getValue(elContext, null, "navigationBean");
						navigation.setFlagBCScore(true);
			    	}
			    	
		    	} else {
		    		
		    		redirect_to_wait_ENABLED = true;
		    	}
		    	
			} else {
				if( sesion.getArea() != null && sesion.getArea().toString().equals("L") ){
					
					navigation = (NavigationBeanIMP) resolver.getValue(elContext, null, "navigationBean");
					navigation.setFlagBCScore(false);
					
				}
			}
		    
		    redirect_to_ofert_ENABLED = consulta_vigente_OK && ofert_ENABLED;
		}
	}
	
	protected void init_consulting_manual()
	{				
		consulting    = new ConsultingManual();
		consulting_PK = new ConsultingManualPK();
					
		consulting_PK.setCompany_id(company_id);
		consulting_PK.setProspectus_id(prospectus_id);
		
		consulting.setPk(consulting_PK);						
		consulting.setConsulting_prospectus_id(prospectus_id);			
		consulting.setConsulting_date(new Date());				
		consulting.setMx_solicitud_buro(bur_sol_num);
		consulting.setIs_consulting_for_renovation("S");
		
		service_consulting_manual.saveConsultingManual(consulting);
	}
	
	
	protected RespuestaConsultaMasiva ejecutaConsulta( int prospectus_id , int company_id ){
		
		boolean isVigente = false;
		boolean no_hit = false;
		boolean maxIntentos = false;
		String msgErrBur = "";
		
		RespuestaConsultaMasiva respuesta = new RespuestaConsultaMasiva();
		
		 Scoring score = service_score.loadMaxScoringByProspectus(prospectus_id, company_id);
		
		Scoring scoreRes = null;
		
		if( score != null  ){
		
			if( score.getResult_datetime() != null && !esConsultaVigente( score.getResult_datetime() ) ){
			
				service_score.removeScoring(prospectus_id, company_id);
			
			}else{
				isVigente = true;
			}
		
		}
		
		if( !isVigente ){
		
			ProyectLoan lastProyectloan = service_proyect_loan.getMaxProyectLoanByProspectAndStatus(prospectus_id, company_id, 3) ;
			
			Preaprobacion preaprobacion = initPreaprobacion();
			
			preaprobacion.setProspectus_id(prospectus_id);
			preaprobacion.setCompany_id(company_id);
			
			preaprobacion.setMonto_solicitado( lastProyectloan.getAmmount() );
			
			preaprobacion.setExcecuteJSF(false);
			
			//preaprobacion.setIs_call_to_client("S");
			
			preaprobacion.setLoan_type("REN");
			
			// preaprobacion.setlo
			
			preaprobacion.init_variables();
			
			preaprobacion.callWSSGB();
			
			no_hit =preaprobacion.isNoHit();
			
			maxIntentos =preaprobacion.isDisplayErrBurConsult();
			
			msgErrBur =preaprobacion.getMsgWarningBurConsult();
			
			scoreRes =  preaprobacion.getScore();
			
			/****************************/
			
			if( !no_hit && !maxIntentos ){
			
				score = service_score.loadMaxScoringByProspectus(prospectus_id, company_id);
				
				score.setIs_consulting_for_renovation("S");
				
				service_score.updateScoring(score);
				
				bur_sol_num = score.getMx_solicitud_buro();
				
				/*Preaprobacion preaprobacion = initPreaprobacion();
				
				preaprobacion.setProspectus_id(prospectus_id);
				preaprobacion.setCompany_id(company_id);
				
				preaprobacion.init_variables(); */
				
				preaprobacion.setScore(score);
				
				preaprobacion.setExcecuteJSF(false);
				
				preaprobacion.setConsultaBuro(true);
				
				preaprobacion.asignar_score();
			
			}
		
			/*****************************/
			
			respuesta.setVigente(isVigente);
			respuesta.setScore(scoreRes);
			respuesta.setNo_hit(no_hit);
			respuesta.setMaxIntentos( maxIntentos );
			respuesta.setMsgErrBur( msgErrBur ); 
			
		
		}else{
			
			respuesta.setVigente(isVigente);
			respuesta.setScore(score);
			respuesta.setNo_hit(no_hit);
			respuesta.setMaxIntentos( maxIntentos );
			respuesta.setMsgErrBur( msgErrBur );
			
		}
		
		return respuesta;
		
	}
	
	private Boolean esConsultaVigente( Date dia ){
		
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
		
		try{
			
			Date hoy = new Date();
			
			Long dias = (hoy.getTime() - dia.getTime() ) / MILLSECS_PER_DAY;
			
			return ( 30 > dias.intValue() );
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public Preaprobacion initPreaprobacion(){
		
		Preaprobacion preaprobacion = (Preaprobacion) resolver.getValue(elContext, null, "preaprobacion");	
		
		return preaprobacion;
	}
	
}
