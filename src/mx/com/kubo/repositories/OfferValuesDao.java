package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.OfferValues;

public interface OfferValuesDao {

	public boolean deleteOfferValueByOfferId( Integer offer_id );
	public boolean deleteOfferValue( OfferValues value );
	public boolean saveOfferValues( List<OfferValues> values );
	public boolean saveOfferValue( OfferValues value );
	public  List<OfferValues> getOfferValuesByOfferId( Integer offer_id );
	
}
