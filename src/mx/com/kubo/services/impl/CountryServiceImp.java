package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Country;
import mx.com.kubo.model.CountryPK;
import mx.com.kubo.repositories.CountryDao;
import mx.com.kubo.services.CountryService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImp implements CountryService{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private CountryDao countryRepository;

	@Override
	public Country getCountryById(CountryPK id) {
		return countryRepository.loadSelectedCountry(id);
	}

	@Override
	public void add(Country newCountry) {
		countryRepository.saveCountry(newCountry);		
	}

	@Override
	public List<Country> getCountryList() {
		return countryRepository.loadCountryList();
	}
	
	

}
