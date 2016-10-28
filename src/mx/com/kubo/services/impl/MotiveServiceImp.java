package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.Motive;
import mx.com.kubo.model.MotivePK;
import mx.com.kubo.repositories.MotiveDao;
import mx.com.kubo.services.MotiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotiveServiceImp implements MotiveService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MotiveDao motivestatusrepository;
	
	@Override
	public List<Motive> getMotiveStatusListByStatus(Integer status_id){
		
		return motivestatusrepository.getMotiveStatusListByStatus(status_id);
		
	}
	
	@Override
	public Motive getMotiveByPK( MotivePK pk ){
		
		return motivestatusrepository.getMotiveByPK( pk );
		
	}
	
	@Override
	public List<Motive> getMotiveStatusListByNoteType(Integer note_type_id){
		
		return motivestatusrepository.getMotiveStatusListByNoteType( note_type_id );
		
	}
	
}
