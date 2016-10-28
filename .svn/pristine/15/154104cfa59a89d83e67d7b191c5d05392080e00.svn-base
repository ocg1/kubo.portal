package mx.com.kubo.services.impl;

import mx.com.kubo.model.TransunionResp;
import mx.com.kubo.repositories.TransunionRespDao;
import mx.com.kubo.services.TransunionRespService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransunionRespServiceImp implements TransunionRespService {

	@Autowired
	private TransunionRespDao transunionrespdao;
	
	@Override
	public TransunionResp getTransunionByBurSolNum( String bursolnum ){
		return transunionrespdao.getTransunionByBurSolNum(bursolnum);
	}
	
}
