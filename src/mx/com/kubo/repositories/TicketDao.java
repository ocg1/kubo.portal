package mx.com.kubo.repositories;

import mx.com.kubo.model.Ticket;

public interface TicketDao  {
	
	public Ticket getTiketbyCreditAndAmortId( String safi_credit_id , Integer payment_number );
	public boolean saveTicket(Ticket ticket, Integer prospectus_id);
	
}
