package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.Ticket;
import mx.com.kubo.repositories.TicketDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TicketDaoImp implements Serializable,TicketDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	  /**
	   * Sets the entity manager.
	   */
		
	  @PersistenceContext
	  public void setEntityManager(EntityManager em) {
	      this.em = em;
	  }

	@Transactional
	@Override
	public boolean saveTicket(Ticket ticket, Integer prospectus_id){
		
		boolean flag= false;
		Integer tempId = 0;
		
		while(!flag){
			
			String hql= "select max(ticketpk.ticket_id) from Ticket";
			List list = em.createQuery(hql).getResultList();
			int maxID = (((Integer)list.get(0))==null)?0:((Integer)list.get(0)).intValue();
			maxID++;
			if(tempId != maxID){
				
				tempId = maxID;
				
				ticket.getTicketpk().setTicket_id(maxID);
				
				try{
					
					em.persist(ticket);
					flag = true;
					
				}catch(Exception e){
					
					e.printStackTrace();
					
					ServiceCalling newService = new ServiceCalling();
					
					newService.setAcces_datetime(new Date());
					newService.setCompany_id(ticket.getTicketpk().getCompany_id());
					newService.setInfo("Error al Insertar en Ticket: "+e.getMessage());
					newService.setProspectus_id(prospectus_id);
					newService.setStatus(3);
					
					String query="select MAX(s.service_calling_id) from ServiceCalling s";
					Integer id=0;
					id=(Integer) em.createQuery(query).getSingleResult();
					id= (id==null)?0:id;
					id++;
					
					
					newService.setService_calling_id(id);
					em.persist(newService);
					
				}
				
			}else{
				flag = true;
			}
			
		}
		
		return flag;
	}
	
	@Override
	public Ticket getTiketbyCreditAndAmortId( String safi_credit_id , Integer payment_number ){
		
		try{
			
			String query="from Ticket tk where tk.safi_credit_id = ? and tk.payment_number = ?";
			
			Ticket thisTicket = em.createQuery(query,Ticket.class).setParameter(1, safi_credit_id).setParameter(2,payment_number).getSingleResult();
			
			return thisTicket;
			
		}catch(Exception e){
			return null;
		}
		
	}
	
}
