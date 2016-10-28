package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.RelationShip;
import mx.com.kubo.model.RelationShipPK;
import mx.com.kubo.repositories.RelationShipDao;
import mx.com.kubo.services.RelationShipService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RelationShipServiceImp implements RelationShipService{
	Logger log = Logger.getLogger(getClass());
	@Autowired
	private RelationShipDao relationRepository;
	
	@Override
	public RelationShip getRelationShipByID(RelationShipPK relationShipPk) {
		// TODO Auto-generated method stub
		return relationRepository.getRelationShipByID(relationShipPk);
	}

	@Override
	public boolean addRelationShip(RelationShip newRelationShip) {
		// TODO Auto-generated method stub
		return relationRepository.addRelationShip(newRelationShip);
	}

	@Override
	public boolean updateRelationShip(RelationShip relationShip) {
		// TODO Auto-generated method stub
		return relationRepository.updateRelationShip(relationShip);
	}

	@Override
	public List<RelationShip> getListRelationShip() {
		// TODO Auto-generated method stub
		return relationRepository.getListRelationShip();
	}

	@Override
	public RelationShip getRelationShipByProspectOriginal(int prospectusID,int companyID) {
		// TODO Auto-generated method stub
		return relationRepository.getRelationShipByProspectOriginal(prospectusID, companyID);
	}

}
