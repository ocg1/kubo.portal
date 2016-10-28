package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.RelationShip;
import mx.com.kubo.model.RelationShipPK;

public interface RelationShipService {
	public abstract RelationShip getRelationShipByID(RelationShipPK relationShipPk);
	public abstract boolean addRelationShip(RelationShip newRelationShip);
	public abstract boolean updateRelationShip(RelationShip relationShip);
	public abstract List<RelationShip> getListRelationShip();
	public abstract RelationShip getRelationShipByProspectOriginal(int prospectusID,int companyID);

}
