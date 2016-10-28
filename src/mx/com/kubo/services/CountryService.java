package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Country;
import mx.com.kubo.model.CountryPK;

public interface CountryService 
{
	Country getCountryById(CountryPK id);
	
	void add(Country newCountry);
	
	List<Country> getCountryList();

}
