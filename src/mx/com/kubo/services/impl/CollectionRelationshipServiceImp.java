package mx.com.kubo.services.impl;

import java.io.Serializable;

import mx.com.kubo.model.CollectionRelationship;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.repositories.CollectionRelationshipDao;
import mx.com.kubo.services.CollectionRelationshipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionRelationshipServiceImp implements CollectionRelationshipService,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	CollectionRelationshipDao CollectionRelationshipdao;
	
	
	@Override
    public CollectionRelationship getCollectionRelationshipByPLPK( ProyectLoanPK pylnpk ){
		return CollectionRelationshipdao.getCollectionRelationshipByPLPK(pylnpk);
	}
	

}
