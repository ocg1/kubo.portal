package mx.com.kubo.repositories;

import mx.com.kubo.model.Offer;

public interface OfferDao  {

	public Offer getOfferByBurSolNum( String bursolnum );
	public boolean updateOffer( Offer offer );
	public boolean saveOffer( Offer offer );
	
}
