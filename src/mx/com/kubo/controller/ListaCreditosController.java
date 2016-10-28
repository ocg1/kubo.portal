package mx.com.kubo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import mx.com.kubo.bean.CreditoCaracteristicas;

@ManagedBean
@RequestScoped

public class ListaCreditosController implements Serializable {
	private List listCred;
	private int sizeList;
	
	public ListaCreditosController(){
		llenaArreglo();
	}
	
	private void llenaArreglo(){
		listCred = new ArrayList();
		CreditoCaracteristicas cc = new CreditoCaracteristicas();
		cc.setCategoria("C");
		cc.setGrado(1);
		cc.setTasa(2.7);
		cc.setPlazo(36);
		cc.setCalifBuro("776-779");
		cc.setMontoTotal(25000.00);
		cc.setMontoRestante(15000.00);
		cc.setTitCredito("Credito Personal");
		cc.setProposito("Proposito Personal");
		cc.setTiempoRestante(25);
		
		listCred.add(cc);
		
		 cc = new CreditoCaracteristicas();
		cc.setCategoria("F");
		cc.setGrado(1);
		cc.setTasa(2.7);
		cc.setPlazo(36);
		cc.setCalifBuro("776-779");
		cc.setMontoTotal(25000.00);
		cc.setMontoRestante(15000.00);
		cc.setTitCredito("Credito Personal");
		cc.setProposito("Proposito Personal");
		cc.setTiempoRestante(25);
		
		listCred.add(cc);
		
		cc = new CreditoCaracteristicas();
		cc.setCategoria("A");
		cc.setGrado(3);
		cc.setTasa(4.5);
		cc.setPlazo(12);
		cc.setCalifBuro("776-779");
		cc.setMontoTotal(39000.00);
		cc.setMontoRestante(15000.00);
		cc.setTitCredito("Credito Personal");
		cc.setProposito("Proposito Personal");
		cc.setTiempoRestante(25);
		
		listCred.add(cc);
	}

	public List getListCred() {
		return listCred;
	}

	public void setListCred(List listCred) {
		this.listCred = listCred;
	}

	public int getSizeList() {
		return listCred.size();
	}

	public void setSizeList(int sizeList) {
		this.sizeList = sizeList;
	}
}
