package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.OfferValues;

public interface OfferValuesService {

	public boolean deleteOfferValueByOfferId( Integer offer_id );
	public boolean deleteOfferValue( OfferValues value );
	public boolean saveOfferValues( List<OfferValues> values );
	public  List<OfferValues> getOfferValuesByOfferId( Integer offer_id );
	
}
