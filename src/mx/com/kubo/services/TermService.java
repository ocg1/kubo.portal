package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Term;

public interface TermService {

	public abstract Term getTermById(int id);
	public abstract void add(Term newTerm);
	public abstract List<Term> getTermList();
}
