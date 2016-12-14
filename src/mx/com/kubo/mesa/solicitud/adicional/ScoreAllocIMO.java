package mx.com.kubo.mesa.solicitud.adicional;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SimulatorBean;

public interface ScoreAllocIMO 
{	
	void setProyect_loan(ProyectLoan proyect_loan);
	void setScore(Scoring score);
	void setSimulation(SimulatorBean simulation);
	
	void init();
	
	ProyectLoan getProyect_loan();
}
