package mx.com.kubo.repositories.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.CreditHistoryAttempt;
import mx.com.kubo.repositories.DAOCreditHistoryAttemptIMO;

import org.springframework.stereotype.Repository;

@Repository
public class DAOCreditHistoryAttemptIMP 
implements DAOCreditHistoryAttemptIMO 
{
	private EntityManager em = null;
	
	private String[] status_DISABLED = {"-1", "-5", "-6", "-7", "-8", "0"};
	
	@PersistenceContext
	public void setEntityManager(EntityManager em)
	{
		this.em = em;
	}
	
	public Integer getCreditHistoryAttemptByAll(CreditHistoryAttempt intento, Date fechaLimite)
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
