package mx.com.kubo.controller;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped

public class SimulatorController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sliderValue= 18;

	public SimulatorController() {
		super();
	}

	public int getSliderValue() {
		return sliderValue;
	}

	public void setSliderValue(int sliderValue) {
		if(sliderValue==0){
			sliderValue = 1;
		}else{
			sliderValue = (sliderValue*36)/100;
		}
		System.out.println(sliderValue);
		this.sliderValue = sliderValue;
	}
	

}
