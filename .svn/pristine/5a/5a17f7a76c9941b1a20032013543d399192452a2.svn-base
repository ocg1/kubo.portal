package mx.com.kubo.services.impl;

import java.util.Date;
import java.util.List;

import mx.com.kubo.model.PipelineHistory;
import mx.com.kubo.repositories.PipelineHistoryDao;
import mx.com.kubo.services.PipelineHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PipelineHistoryServiceImp implements PipelineHistoryService {

	@Autowired
	PipelineHistoryDao pipelineHistoryDao;

	@Override
	public List<PipelineHistory> getPipelineHistory() {
		// TODO Auto-generated method stub
		return pipelineHistoryDao.getPipelineHistory();
	}

	@Override
	public List<PipelineHistory> getPipelineHistoryByPeriodo(Date periodo) {
		// TODO Auto-generated method stub
		return pipelineHistoryDao.getPipelineHistoryByPeriodo(periodo);
	}

	@Override
	public List<Date> getPeriodoList() {
		// TODO Auto-generated method stub
		return pipelineHistoryDao.getPeriodoList();
	}
	
	
	
}
