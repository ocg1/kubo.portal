package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.repositories.TownDao;
import mx.com.kubo.services.TownService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImp implements TownService
{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private TownDao dao;

	public TownCat getTownById(TownCatPK id) 
	{
		return dao.loadSelectedTown(id);
	}

	public void add(TownCat newTownCat) 
	{
		dao.saveTown(newTownCat);		
	}
	
	public List<TownCat> getTownList() 
	{
		return dao.loadTownList();
	}
	
	public List<TownCat> loadTownList(int state_id)
	{
		return dao.loadTownList(state_id);
	}
}
