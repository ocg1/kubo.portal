package mx.com.kubo.services.impl;

import java.util.Date;
import java.util.List;

import mx.com.kubo.model.CreditHistoryAttempt;
import mx.com.kubo.repositories.CreditHistoryAttemptDao;
import mx.com.kubo.services.CreditHistoryAttemptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditHistoryAttemptServiceImp 
implements CreditHistoryAttemptService
{	
	@Autowired
	private CreditHistoryAttemptDao dao;
	
/*	
	@Autowired
	private DAOCreditHistoryAttemptIMO dao_credit_history;
*/	
		
	public boolean add(CreditHistoryAttempt newCredit) 
	{
		return dao.add(newCredit);
	}
	
	public boolean borrar_consulta(CreditHistoryAttempt consulta)
	{
		return dao.borrar_consulta(consulta);
	}

	public Integer getConsultas_anteriores(CreditHistoryAttempt objConsultation, Date fechaLimite) 
	{
		return dao.getConsultas_anteriores(objConsultation, fechaLimite);
		
		//return dao_credit_history.getCreditHistoryAttemptByAll(objConsultation, fechaLimite);
	}
	
	public Integer getCreditHistoryAttemptByDate(Date fechaLimite, Integer prospectus_id, Integer company_id) 
	{		
		return dao.getCreditHistoryAttemptByDate(fechaLimite, prospectus_id, company_id);
	}
		
	public Integer getCreditHistoryAttemptByCheck(Date fechaLimite, Integer prospectus_id, Integer company_id) 
	{
		return dao.getCreditHistoryAttemptByCheck(fechaLimite, prospectus_id, company_id);
	}
	
	public final CreditHistoryAttempt getUltima_consulta(int prospectus_id, int company_id)
	{
		return dao.getUltima_consulta(prospectus_id, company_id);
	}
	
	public List<CreditHistoryAttempt> getAttemptsByProspectus(int prospectus_id)
	{
		return dao.getAttemptsByProspectus(prospectus_id);
	}
}
