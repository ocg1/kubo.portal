package mx.com.kubo.services;

import mx.com.kubo.model.Offer;

public interface OfferService {

	public Offer getOfferByBurSolNum( String bursolnum );
	public boolean updateOffer( Offer offer );
	public boolean saveOffer( Offer offer );
	
}
