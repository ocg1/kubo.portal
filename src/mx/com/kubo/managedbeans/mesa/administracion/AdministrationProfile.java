package mx.com.kubo.managedbeans.mesa.administracion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import mx.com.kubo.bean.RequestShortScore;
import mx.com.kubo.bean.ResponseShortScore;
import mx.com.kubo.controller.InversionAutomatica;
import mx.com.kubo.controller.ObtieneConsultaCorta;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.MailLog;
import mx.com.kubo.model.MassiveProspector;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.tools.Utilities;

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
		
		separador = ";";
		
		SystemParamPK spk = new SystemParamPK();
		
		spk.setCompany_id(1);
		spk.setSystem_param_id(90);
		
		SystemParam sp = systemParamService.loadSelectedSystemParam(spk);
		
		if( sp != null  )
		{
			flgStatusBuro = sp.getValue().equals("S");
		}
		
		init_email_date();
	}
					
	public void changePage(ActionEvent e)
	{		
		menu_SELECTED = (String) e.getComponent().getAttributes().get("section").toString();
		
		if(menu_SELECTED != null)
		{			
			if(menu_SELECTED.equals("profile"))
			{			
				actualPage = "asignaPerfil.xhtml";				
			} 
			
			else if(menu_SELECTED.equals("permissionRole"))
			{			
				actualPage = "permisosRole.xhtml";				
			}
			
			else if(menu_SELECTED.equals("screenRole"))
			{				
				actualPage = "screenRole.xhtml";				
			}		

			else if(menu_SELECTED.equals("reinversion_automatica"))
			{
				actualPage = "reinversion_automatica.xhtml";	
			}
			
			else if(menu_SELECTED.equals("alta_masiva"))
			{
				actualPage = "alta_masiva.xhtml";
				 bln_archivos = false;
			}
			
			else if(menu_SELECTED.equals("ping_buro"))
			{
				actualPage = "pingBuro.xhtml";
			}
			
			else if(menu_SELECTED.equals("tablero-normativo"))
			{
				actualPage = "tablero-normativo.xhtml";
			}
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
	
	
	public void handleFileUpload(FileUploadEvent event) 
	{	
		
		Date fecha_carga = new Date();
		
		String fileUpload = cargaDocumento( event, fecha_carga );
		
		if( fileUpload != null ){
			
			procesaInfoArchivo ( fileUpload , fecha_carga, partnerId );
			
			
		}else{
			
			System.out.println( "El Archivo no cargó" );
			
		}
		
	}
	
	private String cargaDocumento( FileUploadEvent event, Date fecha_carga )
	{
	
		String formatFile = "";
		String component_id = event.getComponent().getId();
		String nameFile="alta_masiva";
		String fileName = null;
		
		faces = FacesContext.getCurrentInstance();	
		
		SimpleDateFormat smf = new SimpleDateFormat("dd' de 'MMMM' de 'yyyy' 'HH':'mm':'ss");
		
		
		
		String fecha =  smf.format( fecha_carga ) ;
		
		String pathDocument = "";
		String pathHistoric = "";
		
		String realPath = (faces.getExternalContext().getRealPath("//resources//"));
		
		if(event != null)
		{
			try
			{
				
				formatFile = event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf("."));
				
			} catch (Exception e) {
				
				formatFile = ".txt";
				
			}
			
			pathDocument = "/documents/cia_" + sesion.getCompany_id()+"/alta_masiva/";		
			pathHistoric = "/historic/cia_"  + sesion.getCompany_id()+"/alta_masiva/";
			
			Utilities.createDirectory(realPath + pathDocument );
			Utilities.createDirectory(realPath + pathHistoric );
			
		}
		
		try{
		
			InputStream in = event.getFile().getInputstream();
			
			System.out.println( "component_id: " + component_id + " formatFile: " + formatFile );
			
			nameFile+="_"+partnerId+"_"+fecha+formatFile;
			
			fileName = realPath + pathDocument+nameFile;
			
			OutputStream out = new FileOutputStream(new File(fileName));
            
            int read = 0;
            byte[] bytes = new byte[1024];
         
            while ((read = in.read(bytes)) != -1) 
            {
                out.write(bytes, 0, read);
            }
         
            out.flush(); 
            out.close();
            
            String 	fileNameHist = realPath + pathHistoric+nameFile;
            
            Utilities.copyFile(fileNameHist, in);
            
            in.close();
            
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
		
		return fileName;
		
	}
	
	
	private void procesaInfoArchivo (String pathFile, Date fecha_carga, String partner_id) 
	{				
		
		FileReader 		fr = null;
		BufferedReader 	br = null;
		StringBuilder 	sb = null;
		
		String path_exito = "" ;
		String path_error = "" ;
		
		sb_exito = new StringBuilder(); 
		
		sb_exito.append( 
				"Primer_nombre;" +
				"Segundo_nombre;" +
				"Apellido_materno;" +
				"Apellido_paterno;" + 
				
				"Fecha_nacimiento;" + 
				"RFC;" + 
				"Calle;" + 
				"Numero_exterior;" + 
				"Colonia;" + 
				"Ciudad;" + 
				"Municipio;" + 
				"Estado;" + 
				"Codigo_postal;" + 
				
				"Score Valido;" + 
				"Score;" + 
				"BurSolNum;\n" 
				
				);
		
		sb_error = new StringBuilder(); 
		
		sb_error.append( 
				"Primer_nombre;" +
				"Segundo_nombre;" +
				"Apellido_materno;" +
				"Apellido_paterno;" + 
				
				"Fecha_nacimiento;" + 
				"RFC;" + 
				"Calle;" + 
				"Numero_exterior;" + 
				"Colonia;" + 
				"Ciudad;" + 
				"Municipio;" + 
				"Estado;" + 
				"Codigo_postal;" + 
				
				"ERROR;\n" 
				
				);
        
        System.out.println("Documento a Leer: " + pathFile );
	 
	    try 
	    {
	    	File archivo = new File (pathFile);
	    	fr      = new FileReader (archivo);
	    	br      = new BufferedReader(fr);
	    	sb      = new StringBuilder();
	    	
	    	path_exito = archivo.getPath();
	    	path_error = path_exito;
	    	
	    	String fileUpload = archivo.getName();
	    	
	    	String linea = "";
	    	
		    while((linea = br.readLine()) != null)
		    {		        	 
		    	String registro =  (linea);
		    	
		    	String[] elementos = registro.split(separador);
		    	
		    	insertaRegistro( elementos, fileUpload, fecha_carga, partner_id );
		    	
		    	
		    }
		    
		    procesaProspector( fileUpload );
		    
		    String exito_name = fileUpload.split("\\.")[0];
		    
		    path_exito = path_exito.substring(0,path_exito.indexOf(fileUpload));
		    path_error = path_exito;
		    
		    path_exito += exito_name+"_EXITO.csv";
		    path_error += exito_name+"_ERROR.csv";
		    
		    creaArchivo( sb_exito, path_exito, path_exito );
		    
		    creaArchivo( sb_error, path_error, path_error );
		    
		    archivo_exitoso = path_exito.substring( path_exito.indexOf("/Kubo/resources/") );
		    archivo_error = path_error.substring( path_exito.indexOf("/Kubo/resources/") );
		    
		    System.out.println( sb.toString() );
		    
		    bln_archivos = true;
		    
		    
		    List<MassiveProspector> lst =  massiveprospectorservice.getMassiveLstByFileName(fileUpload);
			
			if( lst != null && lst.size() > 0 ){
				
				for( MassiveProspector elmnt : lst ){
					
					elmnt.setArchivo_error(exito_name+"_ERROR.csv");
					elmnt.setArchivo_exito(exito_name+"_EXITO.csv");
					
					massiveprospectorservice.updateElement(elmnt);
					
				}
				
			}
		    
		    
		    RequestContext requestContext = RequestContext.getCurrentInstance();
		    
		    requestContext.addPartialUpdateTarget("pnlURLFiles");
		    
		    
		    
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("pnlURLFiles");
		         	         		   		    
	    } catch(Exception e) {
	    	
	    	e.printStackTrace();	
	    	
	    } finally {	   
			
	    	try
	    	{
	    		if( null != fr )
	    		{
					 fr.close();	
					 br.close();
				}	
	    		
			 } catch (Exception e) {
				 
				 e.printStackTrace();				 
			 }
		}	      
	}
	
	private void procesaProspector( String fileUpload ){
		
		try{
			
			List<MassiveProspector> lst =  massiveprospectorservice.getMassiveLstByFileName(fileUpload);
			
			if( lst != null && lst.size() > 0 ){
				
				for( MassiveProspector elmnt : lst ){
				
					try{
					
						RequestShortScore shortrequest = new RequestShortScore();
						
						// shortrequest.setClientId(  );
						
						shortrequest.setPrimerNombre( elmnt.getPrimer_nombre() );
						shortrequest.setSegundoNombre( elmnt.getSegundo_nombre() );
						shortrequest.setApellidoMaterno( elmnt.getApellido_materno() );
						shortrequest.setApellidoPaterno( elmnt.getApellido_paterno() );
						shortrequest.setFechaNacimineto(generaFecha( elmnt.getFecha_nacimiento() ));
						shortrequest.setRfc(elmnt.getRfc());
						
						String street = elmnt.getCalle();
						
						if( street != null ){
							
							street = street.replace( "," , "");
							
						}
						
						shortrequest.setCalle( street );
						
						shortrequest.setCiudad( elmnt.getCiudad() );
						
						shortrequest.setCodigoPostal(elmnt.getCodigo_postal()  );
						
						shortrequest.setColonia( elmnt.getColonia() );
						
						
						shortrequest.setEstado( elmnt.getEstado() );
						shortrequest.setMunicipio( elmnt.getMunicipio() );
						shortrequest.setNumeroExterior( elmnt.getNumero_exterior() );
						
						ObtieneConsultaCorta shortScore = new ObtieneConsultaCorta();
						
						ResponseShortScore res =  shortScore.generaConsultaCorta(shortrequest,false);
						
						if(res != null){
							
							elmnt.setValid_prospector( res.getValido()+"" );
							elmnt.setBc_score( res.getScore() );
							elmnt.setMx_solicitud_buro( res.getBurSolNum() );
							
							sb_exito.append( 
									
									elmnt.getPrimer_nombre()  + ";" +
									elmnt.getSegundo_nombre() + ";" +
									elmnt.getApellido_materno()+ ";" +
									elmnt.getApellido_paterno()+ ";" + 
									
									elmnt.getFecha_nacimiento()+";" + 
									elmnt.getRfc()+";" + 
									elmnt.getCalle()+";" + 
									elmnt.getNumero_exterior()+";" + 
									elmnt.getColonia()+";" + 
									elmnt.getCiudad()+";" + 
									elmnt.getMunicipio()+";" + 
									elmnt.getEstado()+";" + 
									elmnt.getCodigo_postal()+";" + 
									
									elmnt.getValid_prospector()+";" +
									res.getScore()+ ";" + 
									res.getBurSolNum()+ ";\n" 
									
									);
							
						}else{
							
							elmnt.setMensaje( "Consulta No Registrada."  );
							
								sb_error.append( 
									
									elmnt.getPrimer_nombre()  + ";" +
									elmnt.getSegundo_nombre() + ";" +
									elmnt.getApellido_materno()+ ";" +
									elmnt.getApellido_paterno()+ ";" + 
									elmnt.getFecha_nacimiento()+";" + 
									elmnt.getRfc()+";" + 
									elmnt.getCalle()+";" + 
									elmnt.getNumero_exterior()+";" + 
									elmnt.getColonia()+";" + 
									elmnt.getCiudad()+";" + 
									elmnt.getMunicipio()+";" + 
									elmnt.getEstado()+";" + 
									elmnt.getCodigo_postal()+";" + 
									"Consulta No Registrada;"
									
									);
							
						}
					
						massiveprospectorservice.updateElement(elmnt);
					
					}catch( Exception e ){
						
						e.printStackTrace();
						
						elmnt.setMensaje( "Consulta No Registrada."  );
						
						sb_error.append( 
							
							elmnt.getPrimer_nombre()  + ";" +
							elmnt.getSegundo_nombre() + ";" +
							elmnt.getApellido_materno()+ ";" +
							elmnt.getApellido_paterno()+ ";" + 
							elmnt.getFecha_nacimiento()+";" + 
							elmnt.getRfc()+";" + 
							elmnt.getCalle()+";" + 
							elmnt.getNumero_exterior()+";" + 
							elmnt.getColonia()+";" + 
							elmnt.getCiudad()+";" + 
							elmnt.getMunicipio()+";" + 
							elmnt.getEstado()+";" + 
							elmnt.getCodigo_postal()+";" + 
							"ERROR: "+ e.getMessage() +";"
							
							);
						
					}
					
				}
				
				
				
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
	private boolean creaArchivo( StringBuilder sb_exito,String ruta, String rutaHist ){
		
		try{
			
			//String ruta = "/home/mario/archivo.txt";
	        
			System.out.println( "Ruta EXITO: " + ruta );
			
			File archivo = new File(ruta);
	        BufferedWriter bw;
	        
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write( sb_exito.toString() );
	       
	        bw.close();
	        
	        return true;
			
		}catch(Exception e){
		
			e.printStackTrace();
			return false;
		}
	}
	
	private void insertaRegistro( String[] elementos, String fileUpload, Date fecha_carga, String partner_id ){
		
		try{
		
			boolean b_error = false;
			String error_str = "";
			
			SimpleDateFormat smf = new SimpleDateFormat( "dd-MM-yyyy" );
			
			MassiveProspector massiveElement = new MassiveProspector();
			
			massiveElement.setFecha_carga				( fecha_carga);
			massiveElement.setPartner_id				( partner_id);
			massiveElement.setPrimer_nombre				( validaCadena( elementos[0] ));
			massiveElement.setSegundo_nombre			( validaCadena( elementos[1] ));
			massiveElement.setApellido_paterno			( validaCadena( elementos[2] ));
			massiveElement.setApellido_materno			( validaCadena( elementos[3] ));
			
			Date fecNac = null;
			
			try{
				
				fecNac = smf.parse   ( elementos[4] );
				massiveElement.setFecha_nacimiento			( fecNac );
			
			}catch(Exception e){
				e.printStackTrace();
				error_str += " Error en el formato de la fecha ";
				b_error = true;
			}
			
			String rfc = elementos[5];
			
			if( rfc != null && rfc.trim().length() > 0){
			
				massiveElement.setRfc						( validaCadena( elementos[5] ));
			
			}else{
				
				rfc = generaRFC( elementos[0], elementos[1], elementos[2], elementos[3],  fecNac );
				if( rfc.length() > 0 ){
					massiveElement.setRfc						( rfc );
				}else{
					error_str += " Error en el formato del RFC";
					b_error = true;
				}
				
			}
			massiveElement.setCalle						( validaCadena( elementos[6] ));
			massiveElement.setNumero_exterior			( validaCadena( elementos[7] ));
			massiveElement.setColonia					( validaCadena( elementos[8] ));
			massiveElement.setCiudad					( validaCadena( elementos[9] ));
			massiveElement.setMunicipio					( validaCadena( elementos[10]));
			
			String estado = validaEstado( elementos[11]);
			
			if(estado.length() == 0){
				error_str += " | Error en el formato del estado";
				b_error = true;
			}else{
			
				massiveElement.setEstado					( estado );
			
			}
			
			massiveElement.setCodigo_postal				( validaCadena( elementos[12]));
			
			massiveElement.setArchivo_carga				( fileUpload );
			
			if( !b_error ){
			
				if ( massiveprospectorservice.addElement(massiveElement) ){
					
					System.out.println( "¡ ¡ ¡ ¡  I N S E R T A D O  ! ! ! !" );
					
				}else{
					System.out.println( "X X X X X  E R R O R  X X X X" );
					
					sb_error.append( 
							elementos[0] + ";" +
							elementos[1] + ";" +
							elementos[2] + ";" +
							elementos[3] + ";" +
							elementos[4] + ";" + 
							elementos[5] + ";" + 
							elementos[6] + ";" + 
							elementos[7] + ";" + 
							elementos[8] + ";" + 
							elementos[9] + ";" + 
							elementos[10] + ";" + 
							elementos[11] + ";" + 
							elementos[12] + ";" + 
							"Error Al Insertar;\n" 
							
							);
					
				}
			
			}else{
				
				sb_error.append( 
						elementos[0] + ";" +
						elementos[1] + ";" +
						elementos[2] + ";" +
						elementos[3] + ";" +
						elementos[4] + ";" + 
						elementos[5] + ";" + 
						elementos[6] + ";" + 
						elementos[7] + ";" + 
						elementos[8] + ";" + 
						elementos[9] + ";" + 
						elementos[10] + ";" + 
						elementos[11] + ";" + 
						elementos[12] + ";" + 
						error_str + ";\n" 
						
						);
				
			}
		
		}catch(Exception e){
			
			e.printStackTrace();
			
			sb_error.append( 
					
					elementos[0] + ";" +
					elementos[1] + ";" +
					elementos[2] + ";" +
					elementos[3] + ";" +
					elementos[4] + ";" + 
					elementos[5] + ";" + 
					elementos[6] + ";" + 
					elementos[7] + ";" + 
					elementos[8] + ";" + 
					elementos[9] + ";" + 
					elementos[10] + ";" + 
					elementos[11] + ";" + 
					elementos[12] + ";" + 
					"Excepcion: " + e.getMessage() + 	";\n" 
					
					);
			
		}
		
	}
	
	private String generaRFC( String primer_nombre, String segundo_nombre, String apellido_paterno, String apellido_materno, Date fecha_nac ){
		
		try{
		
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		
			return naturalpersonservice.getRFC(( primer_nombre + " " + segundo_nombre ), apellido_paterno, apellido_materno, fm.format(fecha_nac));
		
		}catch( Exception e ){
			e.printStackTrace();
			return "";
		}
	}
	
	private String validaEstado ( String estado ){
		
		estado = validaCadena( estado );
		
		String res = "";
		
		estado = quitaAcentos( estado );
		
		estado = estado.toUpperCase();
		
		if(estado.indexOf("AGUASCALIENTES")  !=  (-1) ){ 	
				res = "AGS";
		
			}else if(estado.indexOf("BAJA CALIFORNIA SUR")  !=  (-1) ){ 
				res = "BCS";
			
			}
			else if(estado.indexOf("BAJA CALIFORNIA")  !=  (-1) ){ 	
				res = "BCN";
			
			}
			else if(estado.indexOf("CAMPECHE")  !=  (-1) ){ 	
				res = "CAM";
			
			}
			else if(estado.indexOf("COAHUILA")  !=  (-1) ){ 	
				res = "COA";
			}
			else if(estado.indexOf("COLIMA")  !=  (-1) ){ 	
				res = "COL";
			}
			else if(estado.indexOf("CHIAPAS")  !=  (-1) ){ 	
				res = "CHS";
			}
			else if(estado.indexOf("CHIHUAHUA")  !=  (-1) ){ 	
				res = "CHI";
			}
			else if(estado.indexOf("DISTRITO FEDERAL")  !=  (-1) ){ 	
				res = "DF";
			}
			else if(estado.indexOf("DURANGO")  !=  (-1) ){ 	
				res = "DGO";
			}
			else if(estado.indexOf("GUANAJUATO")  !=  (-1) ){ 	
				res = "GTO";
			}
			else if(estado.indexOf("GUERRERO")  !=  (-1) ){ 	
				res = "GRO";
			}
			else if(estado.indexOf("HIDALGO")  !=  (-1) ){ 	
				res = "HGO";
			}
			else if(estado.indexOf("JALISCO")  !=  (-1) ){ 	
				res = "JAL";
			}
			else if(estado.indexOf("MEXICO")  !=  (-1) ){ 	
				res = "EM";
			}
			else if(estado.indexOf("MICHOACAN")  !=  (-1) ){ 	
				res = "MICH";
			}
			else if(estado.indexOf("MORELOS")  !=  (-1) ){ 	
				res = "MOR";
			}
			else if(estado.indexOf("NAYARIT")  !=  (-1) ){ 	
				res = "NAY";
			}
			else if(estado.indexOf("NUEVO LEON")  !=  (-1) ){ 	
				res = "NL";
			}
			else if(estado.indexOf("OAXACA")  !=  (-1) ){ 	
				res = "OAX";
			}
			else if(estado.indexOf("PUEBLA")  !=  (-1) ){ 	
				res = "PUE";
			}
			else if(estado.indexOf("QUERETARO")  !=  (-1) ){ 	
				res = "QRO";
			}
			else if(estado.indexOf("QUINTANA ROO")  !=  (-1) ){ 	
				res = "QR";
			}
			else if(estado.indexOf("SAN LUIS POTOSI")  !=  (-1) ){ 	
				res = "SLP";
			}
			else if(estado.indexOf("SINALOA")  !=  (-1) ){ 	
				res = "SIN";
			}
			else if(estado.indexOf("SONORA")  !=  (-1) ){ 	
				res = "SON";
			}
			else if(estado.indexOf("TABASCO")  !=  (-1) ){ 	
				res = "TAB";
			}
			else if(estado.indexOf("TAMAULIPAS")  !=  (-1) ){ 	
				res = "TAM";
			}
			else if(estado.indexOf("TLAXCALA")  !=  (-1) ){ 	
				res = "TLA";
			}
			else if(estado.indexOf("VERACRUZ")  !=  (-1) ){ 	
				res = "VER";
			}
			else if(estado.indexOf("YUCATAN")  !=  (-1) ){ 	
				res = "YUC";
			}
			else if(estado.indexOf("ZACATECAS")  !=  (-1) ){ 	
				res = "ZAC";
			}

			return res;
	}
		
	private String quitaAcentos(String str){
		
		if( str != null ) {
			
			String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		    // Cadena de caracteres ASCII que reemplazarán los originales.
		    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		    
		    for (int i=0; i<original.length(); i++) {
		        // Reemplazamos los caracteres especiales.
		    	str = str.replace(original.charAt(i), ascii.charAt(i));
		    }//for i

			
		}
		
		return str;
	}
	
	private String validaCadena( String cdn ){
		
		cdn = cdn.replaceAll("\"", "" );
		return cdn;
		
	}
	
	private String generaFecha( Date nfecha){
	
		if( nfecha != null ){
			
			String res = "";
			
			SimpleDateFormat sd = new SimpleDateFormat( "ddMMyyyy" );
			
			res = sd.format(nfecha);
			return res;
			
		}else{
			
			return "";
			
		}
			
	
	}
	
	
	public void pingProspector(){
		
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
