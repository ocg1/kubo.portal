package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Term;
import mx.com.kubo.repositories.TermDao;
import mx.com.kubo.services.TermService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermServiceImp implements TermService{

	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private TermDao termRepository;

	@Override
	public Term getTermById(int id) {
		return termRepository.loadSelectedTerm(id);
	}

	@Override
	public void add(Term newTerm) {
		termRepository.saveTerm(newTerm);
		
	}

	@Override
	public List<Term> getTermList() {
		return termRepository.loadTermList();
	}
	
	
	
	
}
