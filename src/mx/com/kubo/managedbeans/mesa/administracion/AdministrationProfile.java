package mx.com.kubo.managedbeans.mesa.administracion;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.com.kubo.bean.RequestShortScore;
import mx.com.kubo.bean.ResponseShortScore;
import mx.com.kubo.controller.InversionAutomatica;
import mx.com.kubo.controller.ObtieneConsultaCorta;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.mesa.administracion.DocumentUploaderIMP;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;

@ManagedBean(name = "administrationProfile") @ViewScoped
public class AdministrationProfile extends AdministrationProfileAMO
implements Serializable 
{
	private static final long serialVersionUID = 5297949001206986731L;
	
	@PostConstruct
	public final void init()
	{
		faces = FacesContext.getCurrentInstance();	
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		partnerLst =  partnerserviceimp.getPartnerList();
		
		SystemParamPK spk = new SystemParamPK();
		
		spk.setCompany_id(1);
		spk.setSystem_param_id(90);
		
		SystemParam sp = systemParamService.loadSelectedSystemParam(spk);
		
		if( sp != null  )
		{
			flgStatusBuro = sp.getValue().equals("S");
		}
		
		init_email_date();
		
		lista_blocked_person = service_blocked_person.getBlockedPerson();
	}
					
	public void changePage(ActionEvent e)
	{		
		menu_SELECTED = (String) e.getComponent().getAttributes().get("section").toString();
		
		if(menu_SELECTED != null)
		{			
			init_actualPage();
		}				
	}
	
	public void cargaListInversionesPorRealizar(){
		
		inversionAutomatica = new InversionAutomatica();
		
		fechaInversion = new Date();
		
		inversionAutomatica.cargaListaInversionistas( fechaInversion );
		
		listaInversionistas = inversionAutomatica.getListaInversionistas();
		
		System.out.println( " cargaListInversionesPorRealizar ... " );
		
	}
	
	public void ejecutaInversion(){
		
		try{
			
			inversionAutomatica.ejecutaInversionAutomatica( fechaInversion );
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
	public void cargaListInversionesRealizadas(){
		
		//InversionAutomatica inversionAutomatica = new InversionAutomatica();
		
		Calendar c = Calendar.getInstance();
		c.setTime( fechaInversion );
		c.set( c.get(Calendar.YEAR),c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
		//c.set( 2015,11, 31, 0, 0, 0);
		listaInversionesRealizadas = inversionAutomatica.cargaProgressLst( c.getTime());
		
		if(listaInversionesRealizadas != null)
		{
			System.out.println( "Inversiones Realizadas: "+listaInversionesRealizadas.size() );
		}else
		{
			System.out.println("listaInversionesRealizadas == null");
		}
		
	}
	
	public void handleFileCredFmDos(FileUploadEvent event)
	{
		
		handleFileUpload(event);
	}
	
	public void handleFileBlockedPerson(FileUploadEvent event)
	{
		request = RequestContext.getCurrentInstance();		
		faces   = FacesContext.getCurrentInstance();
		
		external = faces.getExternalContext();
		
		String realPath = external.getRealPath("//resources//");
		
		UploadedFile file = event.getFile();
		
		loader = new DocumentUploaderIMP();
		loader.setSesion(sesion);
		loader.setFile(file);
		loader.setRealPath(realPath);
		loader.init();
		
		String file_uploaded_path = loader.getFile_uploaded_path();
		
		request.addCallbackParam("file_uploaded_path", file_uploaded_path);
		
		manager = new BlockedPersonIMP();
		manager.setFile_uploaded_path(file_uploaded_path);
		manager.init();
		
		String path_file_LOG = manager.getPath_file_LOG();		
		
		System.out.println("AdministrationProfile.handleFileBlockedPerson()");
		System.out.println("> path_file_LOG = " + path_file_LOG);

		request.addCallbackParam("path_file_LOG", path_file_LOG);		
	}	
	
	public void handleFileUpload(FileUploadEvent event) 
	{	
		
		Date fecha_carga = new Date();
		
		String fileUpload = cargaDocumento( event, fecha_carga );
		
		if( fileUpload != null )
		{			
			procesaInfoArchivo ( fileUpload , fecha_carga, partnerId );
						
		} else {
			
			System.out.println( "El Archivo no cargó" );			
		}		
	}
	
	public void pingProspector()
	{
		
		consultaSatisfactoria = false;
		
		try{
			
			RequestShortScore shortrequest = new RequestShortScore();
			
			// shortrequest.setClientId(  );
			
			/*
			
			 { 
			 	"apellidoPaterno": "Lopez", 
			 	"apellidoMaterno":"Lopez", 
			 	"primerNombre": "Kitaro", 
			 	"segundoNombre":"", 
			 	"fechaNacimineto": "17061959", 
			 	"rfc":"LOLK590617PT3", 
			 	"calle": "Ajusco", 
			 	"numeroExterior": "28", 
			 	"numeroInterior": "", 
			 	"colonia":"Los Alpes", 
			 	"municipio": "Alvaro Obregon", 
			 	"ciudad": "Alvaro Obregon", 
			 	"estado": "DF", 
			 	"codigoPostal":"01010", 
			 	"clientId": 87572}

			 
			*/
			
			shortrequest.setPrimerNombre( "Kitaro" );
			shortrequest.setSegundoNombre( "" );
			shortrequest.setApellidoMaterno( "Lopez" );
			shortrequest.setApellidoPaterno( "Lopez" );
			shortrequest.setFechaNacimineto("17061959");
			shortrequest.setRfc("LOLK590617PT3");
			shortrequest.setCalle( "Ajusco" );
			shortrequest.setCiudad( "Alvaro Obregon" );
			shortrequest.setCodigoPostal( "01010" );
			shortrequest.setColonia( "Los Alpes" );
			shortrequest.setEstado( "DF" );
			shortrequest.setMunicipio( "Alvaro Obregon" );
			shortrequest.setNumeroExterior( "28" );
			
			ObtieneConsultaCorta shortScore = new ObtieneConsultaCorta();
			
			dateInicio = new Date();
			
			ResponseShortScore res =  shortScore.generaConsultaCorta(shortrequest,false);
			
			strSendConsultaProspector = shortScore.getCadenaEnviada();
			
			System.out.println( "Respuesta: " + shortScore.getStrRes() );
			
			strResConsultaProspector =  shortScore.getStrRes();
			
			if(res != null){
				
				if( res.getScore() != null && !res.getScore().equals( "null" ) ){
					consultaSatisfactoria = true;
				}else{
					consultaSatisfactoria = false;
				}
				
				
			}else{
				
				consultaSatisfactoria = false;
				
			}
		
			
		
		}catch( Exception e ){
			
			e.printStackTrace();
			
			
			
		}
		
		dateFin = new Date();;
	
		
	}
		
	public void activarConsultas(){
		
		
		SystemParamPK spk = new SystemParamPK();
		
		spk.setCompany_id(1);
		spk.setSystem_param_id(90);
		
		SystemParam sp = systemParamService.loadSelectedSystemParam(spk);
		
		sp.setValue("S");
		
		systemParamService. updateSelectedSystemParam( sp );
		
		spk = new SystemParamPK();
		
		spk.setCompany_id(1);
		spk.setSystem_param_id(90);
		
		sp = systemParamService.loadSelectedSystemParam(spk);
		
		if( sp != null  ){
			flgStatusBuro = sp.getValue().equals("S");
		}
		
	}
	
	public void desactivarConsultas(){
		
		SystemParamPK spk = new SystemParamPK();
		
		spk.setCompany_id(1);
		spk.setSystem_param_id(90);
		
		SystemParam sp = systemParamService.loadSelectedSystemParam(spk);
		
		sp.setValue("N");
		
		systemParamService. updateSelectedSystemParam( sp );
		
		spk = new SystemParamPK();
		
		spk.setCompany_id(1);
		spk.setSystem_param_id(90);
		
		sp = systemParamService.loadSelectedSystemParam(spk);
		
		if( sp != null  ){
			flgStatusBuro = sp.getValue().equals("S");
		}
		
	}
	
	public void notificar()
	{
		request = RequestContext.getCurrentInstance();
		
		init_notificar_evento();
		
		if(notificar_OK)
		{
			init_email_date();
		}
		
		String response = sb_exito.toString();
		
		System.out.println(response);
		
		request.addCallbackParam("notificar_OK", notificar_OK);
		request.addCallbackParam("email_date_ENABLED", email_date_ENABLED);
		request.addCallbackParam("response", response);
	}
}
