package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Residence;
import mx.com.kubo.model.ResidencePK;

public interface ResidenceService {
	
	public abstract Residence getResidenceById(ResidencePK pk);
	public abstract void add(Residence newResidence);
	public abstract List<Residence> getResidenceList();

}
