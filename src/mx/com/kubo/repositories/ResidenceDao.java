package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Residence;
import mx.com.kubo.model.ResidencePK;


public interface ResidenceDao {

	public Residence loadSelectedResidence(ResidencePK pk);
	public void saveResidence(Residence newResidence);
	public List<Residence> loadResidenceList();
}
