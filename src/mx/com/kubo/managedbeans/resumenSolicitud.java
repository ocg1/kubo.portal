package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.sql.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.services.ProyectLoanService;

import org.apache.log4j.Logger;


@ManagedBean
@SessionScoped
public class resumenSolicitud implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
	
	@ManagedProperty("#{proyectFundingServiceImp}")
	private ProyectLoanService servicioProyecto;
	
	public Date datePublication;
	private String cadenaProyecto;
	private ProyectLoan actualProyect;
	
	
	public String mapeoDatos(ActionEvent e){
		cadenaProyecto = (String) e.getComponent().getAttributes().get("proyectAtrr").toString();
		
		//(int proyect_loan_id,int proyect_id,int prospectus_id,int company_id )
		ProyectLoanPK passingProyect = new ProyectLoanPK(Integer.parseInt(cadenaProyecto.split("::")[0]),Integer.parseInt(cadenaProyecto.split("::")[1]),Integer.parseInt(cadenaProyecto.split("::")[2]),Integer.parseInt(cadenaProyecto.split("::")[3]));
		actualProyect = servicioProyecto.findProyect(passingProyect);
		parametroTest();
		return "resumen";
	}
		
	public resumenSolicitud(ProyectLoan actualProyect) {
		super();
		this.actualProyect = actualProyect;
	}

	public void parametroTest(){
		try{
			datePublication = new Date(actualProyect.getDays_online());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Date getDatePublication() {
		return datePublication;
	}
	
	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}
}
