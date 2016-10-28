package mx.com.kubo.services;

import mx.com.kubo.model.CollectionRelationship;
import mx.com.kubo.model.ProyectLoanPK;

public interface CollectionRelationshipService {

	public abstract CollectionRelationship getCollectionRelationshipByPLPK( ProyectLoanPK pylnpk );
	
}
