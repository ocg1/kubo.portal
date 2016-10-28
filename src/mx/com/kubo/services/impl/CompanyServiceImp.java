package mx.com.kubo.services.impl;

import mx.com.kubo.model.Company;
import mx.com.kubo.repositories.CompanyDao;
import mx.com.kubo.services.CompanyService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImp implements CompanyService {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private CompanyDao companyRepository;

	@Override
	public Company getCompanyById(int id) {
		return companyRepository.loadSelectedCompany(id);
	}

	@Transactional
	@Override
	public void add(Company newCompany) {
		log.info("add.CompanyServiceImp");
		companyRepository.saveCompany(newCompany);		
	}

}
