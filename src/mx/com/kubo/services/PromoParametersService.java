package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.PromoParameters;

public interface PromoParametersService {

	public List<PromoParameters> getPromoParametersByCode( String code );
	
}
