package mx.com.kubo.repositories.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.CreditHistoryAttempt;
import mx.com.kubo.repositories.CreditHistoryAttemptDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("deprecation")
@Repository
public class CreditHistoryAttemptDaoImp 
implements CreditHistoryAttemptDao
{
	private EntityManager em = null;
	
	private String[] status_DISABLED = {"-1", "-5", "-6", "-7", "-8", "0"};
	
	@PersistenceContext
	public void setEntityManager(EntityManager em)
	{
		this.em = em;
	}
	
	@Transactional	
	public boolean add(CreditHistoryAttempt newCredit) 
	{
		try
		{
			em.persist(newCredit);
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Integer getCreditHistoryAttemptByAll(CreditHistoryAttempt objCredit, Date fechaLimite) 
	{	
		EntityManager entityManager=em.getEntityManagerFactory().createEntityManager();
		Session session = (Session) entityManager.unwrap(Session.class);
		Integer numReg = 0;
		try{
			
			Criteria crit =  session.createCriteria(CreditHistoryAttempt.class);
			
			crit.add(Expression.eq("prospectus_id", objCredit.getProspectus_id()));
			crit.add(Expression.eq("company_id",    objCredit.getCompany_id()));
			
			if(objCredit.getFirst_name()!=null){
				crit.add(Expression.eq("first_name", objCredit.getFirst_name()));
			}else{
				crit.add(Expression.isNull("first_name"));
			}
			
			if(objCredit.getMiddle_name()!=null){
				crit.add(Expression.eq("middle_name", objCredit.getMiddle_name()));
			}else{
				crit.add(Expression.isNull("middle_name"));
			}
			
			if(objCredit.getFather_last_name()!=null){
				crit.add(Expression.eq("father_last_name", objCredit.getFather_last_name()));
			}else{
				crit.add(Expression.isNull("father_last_name"));
			}
			
			if(objCredit.getMother_last_name()!=null){
				crit.add(Expression.eq("mother_last_name", objCredit.getMother_last_name()));
			}else{
				crit.add(Expression.isNull("mother_last_name"));
			}
			if(objCredit.getDate_of_birth()!=null){
				crit.add(Expression.eq("date_of_birth", objCredit.getDate_of_birth()));
				//crit.add(Expression.eq("date_of_birth", objCredit.getDate_of_birth()));
			}else{
				crit.add(Expression.isNull("date_of_birth"));
			}
			
			if(objCredit.getMx_rfc()!=null){
				crit.add(Expression.eq("mx_rfc", objCredit.getMx_rfc()));
			}else{
				crit.add(Expression.isNull("mx_rfc"));
			}
			
			if(objCredit.getStreet()!=null){
				crit.add(Expression.eq("street", objCredit.getStreet()));
			}else{
				crit.add(Expression.isNull("street"));
			}
			
			if(objCredit.getMx_manzana()!=null){
				crit.add(Expression.eq("mx_manzana", objCredit.getMx_manzana()));
			}else{
				crit.add(Expression.isNull("mx_manzana"));
			}
			
			if(objCredit.getMx_lote()!=null){
				crit.add(Expression.eq("mx_lote", objCredit.getMx_lote()));
			}else{
				crit.add(Expression.isNull("mx_lote"));
			}
	
			if( objCredit.getAddress_number()!=null){
				crit.add(Expression.eq("address_number",  objCredit.getAddress_number()));
			}else{
				crit.add(Expression.isNull("address_number"));
			}
			
			if(objCredit.getApt_number()!=null){
				crit.add(Expression.eq("apt_number", objCredit.getApt_number()));
			}else{
				crit.add(Expression.isNull("apt_number"));
			}
			
			if(objCredit.getNeighborhood_name()!=null){
				crit.add(Expression.eq("neighborhood_name", objCredit.getNeighborhood_name()));
			}else{
				crit.add(Expression.isNull("neighborhood_name"));
			}
			
			if(objCredit.getTown_name()!=null){
				crit.add(Expression.eq("town_name", objCredit.getTown_name()));
			}else{
				crit.add(Expression.isNull("town_name"));
			}
			
			if(objCredit.getState_name()!=null){
				crit.add(Expression.eq("state_name", objCredit.getState_name()));
			}else{
				crit.add(Expression.isNull("state_name"));
			}
			
			if(objCredit.getZip_code()!=null){
				crit.add(Expression.eq("zip_code", objCredit.getZip_code()));
			}else{
				crit.add(Expression.isNull("zip_code"));
			}
			
			if(objCredit.getMx_lada()!=null){
				crit.add(Expression.eq("mx_lada", objCredit.getMx_lada()));
			}else{
				crit.add(Expression.isNull("mx_lada"));
			}
			
			if(objCredit.getPhone()!=null){
				crit.add(Expression.eq("phone", objCredit.getPhone()));
			}else{
				crit.add(Expression.isNull("phone"));
			}
			
			if(objCredit.getMx_curp()!=null){
				crit.add(Expression.eq("mx_curp", objCredit.getMx_curp()));
			}else{
				crit.add(Expression.isNull("mx_curp"));
			}
			
			if(objCredit.getCreditcard_is_principal()!=null){
				crit.add(Expression.eq("creditcard_is_principal", objCredit.getCreditcard_is_principal()));
			}else{
				crit.add(Expression.isNull("creditcard_is_principal"));
			}
	
			if(objCredit.getCreditcard_four_digits()!=null){
				crit.add(Expression.eq("creditcard_four_digits", objCredit.getCreditcard_four_digits()));
			}else{
				crit.add(Expression.isNull("creditcard_four_digits"));
			}
			
			if(objCredit.getMortgage_is_principal()!=null){
				crit.add(Expression.eq("mortgage_is_principal", objCredit.getMortgage_is_principal()));
			}else{
				crit.add(Expression.isNull("mortgage_is_principal"));
			}
			
			if(objCredit.getCar_is_principal()!=null){
				crit.add(Expression.eq("car_is_principal", objCredit.getCar_is_principal()));
			}else{
				crit.add(Expression.isNull("car_is_principal"));
			}
			
			ArrayList<String> statusListStr = new ArrayList<String>();
			statusListStr.add("-1");
			statusListStr.add("-5");
			statusListStr.add("-6");
			statusListStr.add("-7");
			statusListStr.add("-8");
			statusListStr.add("0");
			
			crit.add(Expression.not(Expression.in("status_res", statusListStr)));
			crit.add(Expression.ge("consultation_date", fechaLimite));
			@SuppressWarnings("unchecked")
			List<CreditHistoryAttempt> listRes = (List<CreditHistoryAttempt>)crit.list();
			//CreditHistoryAttempt uniqueResult = (CreditHistoryAttempt) crit.uniqueResult();
	
			if(listRes.size()>0)
				numReg = listRes.size();

				
		}catch(Exception e){
			e.printStackTrace();
			numReg = -1;
		}
		session.close();
		return numReg;
		
	}
	
	public Integer getCreditHistoryAttemptByDate(Date fechaLimite, Integer prospectus_id, Integer company_id) 
	{
		//String query = "select count(*) from gn_credit_history_attempt where consultation_date > '"+diasValidos+"'";
		String query = "select count(*) from CreditHistoryAttempt where prospectus_id = ? and "
					+ "company_id =? and consultation_date >= ? "
					+ "and status_res not in(-1, -5, -6, -7, -8)";
		
		Integer numConsultasBuro = 0;
				
		try
		{
			
			numConsultasBuro = Integer.parseInt((em.createQuery(query, Long.class)
					.setParameter(1, prospectus_id)
					.setParameter(2, company_id)
					.setParameter(3, fechaLimite)
					.getSingleResult()).toString());
			
		}catch(Exception e){
			e.printStackTrace();
			numConsultasBuro = 0;
		}
		
		return numConsultasBuro;
	}
		
	public Integer getCreditHistoryAttemptByCheck(Date fechaLimite, Integer prospectus_id, Integer company_id)
	{		
			//String query = "select count(*) from gn_credit_history_attempt where consultation_date > '"+diasValidos+"'";
			String query = "select count(*) from CreditHistoryAttempt where prospectus_id = ? and "
						+ "company_id =? and consultation_date >= ? and is_check = '1' "
						+ "and status_res not in(-1, -5, -6, -7, -8)";
			Integer numConsultasBuro = 0;

			try
			{			
				numConsultasBuro = Integer.parseInt((em.createQuery(query, Long.class)
						.setParameter(1, prospectus_id)
						.setParameter(2, company_id)
						.setParameter(3, fechaLimite)
						.getSingleResult()).toString());
				
			}catch(Exception e){
				e.printStackTrace();
				numConsultasBuro = 0;
			}
			
			return numConsultasBuro;			
	}
		
	public List<CreditHistoryAttempt> getAttemptsByProspectus(int prospectus_id)
	{	
		try
		{			
			String query = "from CreditHistoryAttempt where prospectus_id = ? order by consultation_date desc";
			
			List<CreditHistoryAttempt> lstRes = em.createQuery(query,CreditHistoryAttempt.class).setParameter(1, prospectus_id).getResultList();
			
			return lstRes;
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public final CreditHistoryAttempt getUltima_consulta(int prospectus_id, int company_id)
	{
		CreditHistoryAttempt ultima_consulta = null;
		
		String query = "SELECT MAX(c.attempt_id) FROM CreditHistoryAttempt c WHERE prospectus_id = ? AND c.company_id = ?";
		
		Integer last_attempt_id = (Integer) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2, company_id).getSingleResult();
		
		if(last_attempt_id != null)
		{
			query = "FROM CreditHistoryAttempt c WHERE c.attempt_id = ?";
			
			ultima_consulta = em.createQuery(query, CreditHistoryAttempt.class).setParameter(1, last_attempt_id).getSingleResult();
		}
		
		return ultima_consulta;
	}
	
	@Transactional
	public boolean borrar_consulta(CreditHistoryAttempt consulta) 
	{
		boolean borrar_ultima_consulta = false;
		
		try
		{
			CreditHistoryAttempt ultima_consulta = em.find(CreditHistoryAttempt.class, consulta.getAttempt_id());
			
			if(ultima_consulta != null)
			{				
				em.remove(ultima_consulta);
					
				borrar_ultima_consulta = true;
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return borrar_ultima_consulta;
	}	
	
	public Integer getConsultas_anteriores(CreditHistoryAttempt intento, Date fechaLimite)
	{
		List       <CreditHistoryAttempt> lista_consultas_anteriores;
		TypedQuery <CreditHistoryAttempt> typed;
		
		String query;
		
		Integer consultas_anteriores = 0;
			
		try
		{
			query = init_consultas_anteriores_QUERY(intento, fechaLimite); 
			
			typed = em.createQuery(query, CreditHistoryAttempt.class);
			typed.setParameter("status_DISABLED", Arrays.asList(status_DISABLED));
			
			lista_consultas_anteriores = typed.getResultList();
			
			if(lista_consultas_anteriores != null)
			{
				consultas_anteriores = lista_consultas_anteriores.size();
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			consultas_anteriores = -1;
		}
		
		System.out.println("DAOCreditHistoryAttemptIMP.getCreditHistoryAttemptByAll(): " + consultas_anteriores);
		
		return consultas_anteriores;
	}

	private String init_consultas_anteriores_QUERY(CreditHistoryAttempt intento, Date fechaLimite) 
	{
		String first_name               = intento.getFirst_name();
		String middle_name              = intento.getMiddle_name();
		String father_last_name         = intento.getFather_last_name();
		String mother_last_name         = intento.getMother_last_name();
		String mx_rfc                   = intento.getMx_rfc();
		String mx_curp                  = intento.getMx_curp();
		String street                   = intento.getStreet();
		String mx_manzana               = intento.getMx_manzana();
		String mx_lote                  = intento.getMx_lote();
		String address_number           = intento.getAddress_number();
		String apt_number               = intento.getApt_number();
		String neighborhood_name        = intento.getNeighborhood_name();
		String town_name                = intento.getTown_name();
		String state_name               = intento.getState_name();
		String zip_code                 = intento.getZip_code();
		String phone                    = intento.getPhone();
		String creditcard_four_digits   = intento.getCreditcard_four_digits();		
		Integer mx_lada                 = intento.getMx_lada();
		Integer creditcard_is_principal = intento.getCreditcard_is_principal();
		Integer mortgage_is_principal   = intento.getMortgage_is_principal();
		Integer car_is_principal        = intento.getCar_is_principal();
		Integer prospectus_id           = intento.getProspectus_id();
		int company_id                  = intento.getCompany_id();
		java.sql.Date date_of_birth     = intento.getDate_of_birth();
		
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String fecha_limite = date_format.format(fechaLimite);
		
		StringBuilder sb = new StringBuilder();
		sb.append("FROM CreditHistoryAttempt ");
		sb.append(" WHERE prospectus_id = ").append(prospectus_id);
		sb.append(" AND   company_id    = ").append(company_id);
		sb.append(" AND   consultation_date >= ").append("'").append(fecha_limite).append("'");
		sb.append(" AND   status_res NOT IN (:status_DISABLED) ");
		
		
		if(first_name != null)
		{
			sb.append(" AND first_name = ").append("'").append(first_name).append("'");
			
		} else {
			
			sb.append(" AND first_name IS NULL");
		}
		
		if(middle_name != null)
		{
			sb.append(" AND middle_name = ").append("'").append(middle_name).append("'");
			
		} else {
			
			sb.append(" AND middle_name IS NULL");
		}
		
		if(father_last_name != null)
		{
			sb.append(" AND father_last_name = ").append("'").append(father_last_name).append("'");
			
		} else {
			
			sb.append(" AND father_last_name IS NULL");
		}
		
		if(mother_last_name != null)
		{
			sb.append(" AND mother_last_name = ").append("'").append(mother_last_name).append("'");
			
		} else {
			
			sb.append(" AND mother_last_name IS NULL");
		}
		
		if(date_of_birth != null)
		{
			sb.append(" AND date_of_birth = ").append("'").append(date_of_birth).append("'");
			
		} else {
			
			sb.append(" AND date_of_birth IS NULL");
		}
		
		if(mx_rfc != null)
		{
			sb.append(" AND mx_rfc = ").append("'").append(mx_rfc).append("'");
			
		} else {
			
			sb.append(" AND mx_rfc IS NULL");
		}
		
		if(mx_curp != null)
		{
			sb.append(" AND mx_curp = ").append("'").append(mx_curp).append("'");
			
		} else {
			
			sb.append(" AND mx_curp IS NULL");
		}
		
		if(street != null)
		{
			sb.append(" AND street = ").append("'").append(street).append("'");
			
		} else {
			
			sb.append(" AND street IS NULL");
		}
		
		if(mx_manzana != null)
		{
			sb.append(" AND mx_manzana = ").append("'").append(mx_manzana).append("'");
			
		} else {
			
			sb.append(" AND mx_manzana IS NULL");
		}
		
		if(mx_lote != null)
		{
			sb.append(" AND mx_lote = ").append("'").append(mx_lote).append("'");
			
		} else {
			
			sb.append(" AND mx_lote IS NULL");
		}
		
		if(address_number != null)
		{
			sb.append(" AND address_number = ").append("'").append(address_number).append("'");
			
		} else {
			
			sb.append(" AND address_number IS NULL");
		}
		
		if(apt_number != null)
		{
			sb.append(" AND apt_number = ").append("'").append(apt_number).append("'");
			
		} else {
			
			sb.append(" AND apt_number IS NULL");
		}
		
		if(neighborhood_name != null)
		{
			sb.append(" AND neighborhood_name = ").append("'").append(neighborhood_name).append("'");
			
		} else {
			
			sb.append(" AND neighborhood_name IS NULL");
		}
		
		if(town_name != null)
		{
			sb.append(" AND town_name = ").append("'").append(town_name).append("'");
			
		} else {
			
			sb.append(" AND town_name IS NULL");
		}
		
		if(state_name != null)
		{
			sb.append(" AND state_name = ").append("'").append(state_name).append("'");
			
		} else {
			
			sb.append(" AND state_name IS NULL");
		}
		
		if(zip_code != null)
		{
			sb.append(" AND zip_code = ").append("'").append(zip_code).append("'");
			
		} else {
			
			sb.append(" AND zip_code IS NULL");
		}
		
		if(mx_lada != null)
		{
			sb.append(" AND mx_lada = ").append(mx_lada);
			
		} else {
			
			sb.append(" AND mx_lada IS NULL");
		}
		
		if(phone != null)
		{
			sb.append(" AND phone = ").append("'").append(phone).append("'");
			
		} else {
			
			sb.append(" AND phone IS NULL");
		}
		
		if(creditcard_is_principal != null)
		{
			sb.append(" AND creditcard_is_principal = ").append(creditcard_is_principal);
			
		} else {
			
			sb.append(" AND creditcard_is_principal IS NULL");
		}
		
		if(creditcard_four_digits != null)
		{
			sb.append(" AND creditcard_four_digits = ").append("'").append(creditcard_four_digits).append("'");
			
		} else {
			
			sb.append(" AND creditcard_four_digits IS NULL");
		}
		
		if(mortgage_is_principal != null)
		{
			sb.append(" AND mortgage_is_principal = ").append(mortgage_is_principal);
			
		} else {
			
			sb.append(" AND mortgage_is_principal IS NULL");
		}
		
		if(car_is_principal != null)
		{
			sb.append(" AND car_is_principal = ").append(car_is_principal);
			
		} else {
			
			sb.append(" AND car_is_principal IS NULL");
		}
		
		return sb.toString();
	}
}
