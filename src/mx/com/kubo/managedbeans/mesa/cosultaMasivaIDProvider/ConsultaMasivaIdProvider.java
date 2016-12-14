package mx.com.kubo.managedbeans.mesa.cosultaMasivaIDProvider;


import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import mx.com.kubo.controller.threads.ConsultaIdProviderMasiva;
import mx.com.kubo.controller.threads.VerificadorInicio;


@ManagedBean
@ViewScoped
public class ConsultaMasivaIdProvider extends ConsultaMasivaIProviderPMO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init( ){
		
		System.out.println( "Iniciando" );
		
	}
	
	public void fileUploadAction(FileUploadEvent event) 
	{	
		
		Date fecha_carga = new Date();
		
		FacesContext faces     = FacesContext.getCurrentInstance();
		
		String code = generaCodigo();
		
		String fileUpload = cargaDocumento( event, fecha_carga , code , faces);
		
		iniciaConsultaMasiva( fileUpload , fecha_carga , code ,  faces);
		
		System.out.println( "Cargando Archivo ... codigo : " + code );
		
		try{
			
			ConsultaIdProviderMasiva ejecutaConsulta = new ConsultaIdProviderMasiva();
			
			ejecutaConsulta.setCodigo_seguimiento(code);
			
			ejecutaConsulta.start();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			
		}
		
	}
	
	
	
}
