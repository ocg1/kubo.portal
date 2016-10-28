package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.repositories.NeighborhoodDao;
import mx.com.kubo.services.NeighborhoodService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service (value = "neighborhoodServiceImp")
public class NeighborhoodServiceImp 
implements NeighborhoodService
{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private NeighborhoodDao neighborhoodRepository;

	@Override
	public NeighborhoodCat getNeighborhoodById(NeighborhoodCatPK id) {
		return neighborhoodRepository.loadSelectedNeighborhood(id);
	}

	@Override
	public void add(NeighborhoodCat newNeighborhoodCat) {
		neighborhoodRepository.saveNeighborhood(newNeighborhoodCat);		
	}

	@Override
	public List<NeighborhoodCat> getNeighborhoodList() {
		return neighborhoodRepository.loadNeighborhoodList();
	}
	
	

}
