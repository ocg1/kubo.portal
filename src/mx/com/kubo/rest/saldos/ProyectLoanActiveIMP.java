package mx.com.kubo.rest.saldos;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import mx.com.kubo.model.ProyectLoanActiveInSafi;
import mx.com.kubo.rest.model.ResponseProyectLoanActiveInSafi;

public class ProyectLoanActiveIMP extends ProyectLoanActiveDMO
implements ProyectLoanActiveIMO
{	
	public void init(String cuenta, String status, char status_delinquentinv)
	{	
		safiProyecLoanActiveLst = service_proyect_loan.getProyectLoanActiveInSafiByAccount(cuenta, status, status_delinquentinv);
		
		if(safiProyecLoanActiveLst != null && !safiProyecLoanActiveLst.isEmpty())
		{
			active_list = new ArrayList<ResponseProyectLoanActiveInSafi>(safiProyecLoanActiveLst.size());
		}
		
		init_active_list();
		
		responseJSON = Response.ok(active_list).build();
	}
	
	private void init_active_list()
	{
		for(ProyectLoanActiveInSafi proyectloan: safiProyecLoanActiveLst)
		{
			response = new ResponseProyectLoanActiveInSafi();
			response.setConsecutivo (proyectloan.getConsecutivo());
			response.setProyect_purpose_name(proyectloan.getProyect().getPurpose().getName());
			response.setProyect_name(proyectloan.getProyect().getName());
			response.setProyect_id  (proyectloan.getProyect_id() + "");
			response.setProyect_loan_id(proyectloan.getProyect_loan_id() + "");
			response.setProspectus_id  (proyectloan.getProspectus_id() + "");
			response.setCompany_id  (proyectloan.getCompany_id() + "");
			response.setFondeokuboid(proyectloan.getFondeokuboid() + "");
			response.setKubo_score_a(proyectloan.getKubo_score_a());
			response.setKubo_score_b(proyectloan.getKubo_score_b());
			response.setAmmount (proyectloan.getAmmount() + "");
			response.setPlazoStr(proyectloan.getPlazoStr());
			response.setFechaInicioInv(date_format.format(proyectloan.getFechaInicioInv()));
			response.setRate_investor   (proyectloan.getRate_investor() + "");
			response.setPeriod_rate     (proyectloan.getPeriodRate() + "");
			response.setRate_moratoria ((proyectloan.getRate_investor() * 2) + "");
			response.setMontofondeoinv  (proyectloan.getMontofondeoinv() + "");
			response.setPorcentajefondeo(proyectloan.getPorcentajefondeo() + "");
			response.setSaldo_vigente (proyectloan.getSaldosVigAtrGar() + "");
			response.setSaldo_atrasado(proyectloan.getSaldosVigIntGar() + "");
			response.setEstatus(proyectloan.getEstatusStr());
			
			if(proyectloan.getDiasAtraso() == 1)
			{
				response.setDias_atraso(proyectloan.getDiasAtraso() + " Día");
				
			} else {
				
				response.setDias_atraso(proyectloan.getDiasAtraso() + " Días");
			}
			
			if(proyectloan.getNoCuotasAtraso() == 1)
			{
				response.setCuotas_atraso(proyectloan.getNoCuotasAtraso() + " Cuota");
				
			} else {
				
				response.setCuotas_atraso(proyectloan.getNoCuotasAtraso() + " Cuotas");
			}
			
			active_list.add(response);
		}
	}
}
