package mx.com.kubo.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean
@SessionScoped
public class HistorialCred implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String hidden1="none";
	String hidden2="none";
	String hidden3="none";
	String hidden4="none";
	String tarjetaVigente="2";
	String tarjetaDep="2";
	String credHip="2";
	String credAuto="2";
	
	public String getHidden1() {
		return hidden1;
	}
	public void setHidden1(String hidden1) {
		this.hidden1 = hidden1;
	}
	public String getHidden2() {
		return hidden2;
	}
	public void setHidden2(String hidden2) {
		this.hidden2 = hidden2;
	}
	public String getHidden3() {
		return hidden3;
	}
	public void setHidden3(String hidden3) {
		this.hidden3 = hidden3;
	}
	public String getHidden4() {
		return hidden4;
	}
	public void setHidden4(String hidden4) {
		this.hidden4 = hidden4;
	}
	public String getTarjetaVigente() {
		return tarjetaVigente;
	}
	public void setTarjetaVigente(String tarjetaVigente) {
		this.tarjetaVigente = tarjetaVigente;
	}
	public String getTarjetaDep() {
		return tarjetaDep;
	}
	public void setTarjetaDep(String tarjetaDep) {
		this.tarjetaDep = tarjetaDep;
	}
	public String getCredHip() {
		return credHip;
	}
	public void setCredHip(String credHip) {
		this.credHip = credHip;
	}
	public String getCredAuto() {
		return credAuto;
	}
	public void setCredAuto(String credAuto) {
		this.credAuto = credAuto;
	}	

	public void titularTarjetaCredVigente (){
		if(tarjetaVigente.equals("2"))
				hidden1="none";
		else
			hidden1="block";
	}
	public void muestraTarjetaDep (){
		if(tarjetaDep.equals("2"))
				hidden2="none";
		else
			hidden2="block";
	}
	public void muestraCredHip (){
		if(credHip.equals("2"))
				hidden3="none";
		else
			hidden3="block";
	}
	public void muestraCredAuto (){
		if(credAuto.equals("2"))
				hidden4="none";
		else
			hidden4="block";
	}
	
	public String callWebService(){
		int result=(int) (Math.random()* 2+1);
		System.out.println("Web Service DICE = "+result);
		if(result ==1){
			return "ofertaInicial";
		}
		else{
			return "infoResult";
		}
		
	}
	
}
