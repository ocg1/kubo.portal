package mx.com.kubo.controller.threads;

import mx.com.kubo.model.Scoring;

public class RespuestaConsultaMasiva {

	private boolean vigente;
	private Scoring score;
	private boolean no_hit;
	private boolean maxIntentos ;
	private String msgErrBur;
	
	
	public boolean isVigente() {
		return vigente;
	}
	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}
	public Scoring getScore() {
		return score;
	}
	public void setScore(Scoring score) {
		this.score = score;
	}
	public boolean isNo_hit() {
		return no_hit;
	}
	public void setNo_hit(boolean no_hit) {
		this.no_hit = no_hit;
	}
	public boolean isMaxIntentos() {
		return maxIntentos;
	}
	public void setMaxIntentos(boolean maxIntentos) {
		this.maxIntentos = maxIntentos;
	}
	public String getMsgErrBur() {
		return msgErrBur;
	}
	public void setMsgErrBur(String msgErrBur) {
		this.msgErrBur = msgErrBur;
	}
	
}
