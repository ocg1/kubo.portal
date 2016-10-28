package mx.com.kubo.controller;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

 
public class selectListener implements ActionListener{

	@Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		
		//access country bean directly
		MenuController menu = (MenuController) FacesContext.getCurrentInstance().
			getExternalContext().getSessionMap().get("menu");

		//country.setPagina(event.getNewValue().toString());
		System.out.println(" --- "+event.getComponent().getAttributes().get("capitulo").toString());
		
	}
	
	
}