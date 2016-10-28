package mx.com.kubo.services;

import java.util.Date;
import java.util.List;

import mx.com.kubo.model.PipelineHistory;

public interface PipelineHistoryService {
	public abstract List<PipelineHistory> getPipelineHistory();
	public abstract List<PipelineHistory> getPipelineHistoryByPeriodo(Date periodo);
	public abstract List<Date> getPeriodoList();
}
