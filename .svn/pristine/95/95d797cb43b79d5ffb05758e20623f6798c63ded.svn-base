package mx.com.kubo.services.impl;

import java.io.Serializable;

import mx.com.kubo.model.Ticket;
import mx.com.kubo.repositories.TicketDao;
import mx.com.kubo.services.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImp implements Serializable , TicketService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TicketDao ticketdao;
	
	@Override
	public Ticket getTiketbyCreditAndAmortId( String safi_credit_id , Integer payment_number ){
		return ticketdao.getTiketbyCreditAndAmortId(safi_credit_id, payment_number);
	}
	@Override
	public boolean saveTicket(Ticket ticket, Integer prospectus_id){
		return ticketdao.saveTicket(ticket, prospectus_id);
	}
	
}
