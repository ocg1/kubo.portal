package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.RelationShip;
import mx.com.kubo.model.RelationShipPK;

public interface RelationShipDao {
	public  RelationShip getRelationShipByID(RelationShipPK relationShipPk);
	public  boolean addRelationShip(RelationShip newRelationShip);
	public  boolean updateRelationShip(RelationShip relationShip);
	public  List<RelationShip> getListRelationShip();
	public  RelationShip getRelationShipByProspectOriginal(int prospectusID,int companyID);
}
