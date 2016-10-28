package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.ProyectQuestion;
import mx.com.kubo.model.ProyectQuestionPK;

public interface ProyectQuestionService {
	public abstract ProyectQuestion getProyectQuestionById(ProyectQuestionPK id);
	public abstract void add(ProyectQuestion newProyectQuestion);
	public abstract List<ProyectQuestion> getProyectQuestionList();
}
