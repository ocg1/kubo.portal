package mx.com.kubo.tools;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import java.security.*;

import javax.xml.bind.DatatypeConverter;


import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import mx.com.kubo.constantes.BeanID;
import mx.com.kubo.controller.InversionAutomatica;
import mx.com.kubo.controller.ThreadVerificacionInicial;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component @Scope("prototype")
public class Utilities 
implements ApplicationContextAware, Serializable
{
	private static ApplicationContext context;
	
	public final static <T> T findBean(BeanID id) 
	{
		return findBean(id.getId());
	}
	
	@SuppressWarnings("unchecked")
	public final static <T> T findBean(String beanName) 
	{
		ApplicationContext context =  getApplicationContext();
		
	    return (T) context.getBean( beanName );
	}
	
	public static ApplicationContext getApplicationContext()
	{
		return context;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException 
	{
		try
		{
			System.out.println("Utilities.setApplicationContext()");
			
			Utilities.context = context;
			
			System.out.println("\n\n++++++++++++++++++++ SI JALO ++++++++++++++++++++\n\n");
			
//			Thread.sleep( 3000 );
//			
//			ThreadVerificacionInicial verficador =  new ThreadVerificacionInicial ();
//			
//			System.out.println("\n\n++++++++++++++++++++ THREAD ++++++++++++++++++++\n\n");
//			
//			verficador.excecuteAction();
//			
			System.out.println("\n\n++++++++++++++++++++ OK ++++++++++++++++++++\n\n");
//			
			
		} catch(Exception e) {
			
			System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxx NO JALO xxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
			
			e.printStackTrace();
		}
		
	}
		
	public static String encrypt(String data)
	{		  		  
		SHA1   s     = new SHA1();
		String ruido = "rwr24t5yt25y543td32ty6";
		  
		try 
		{
		    return s.getHash ( s.getHash( data + ruido ) + ruido );
		    
		} catch(NoSuchAlgorithmException e) {
		    
			e.printStackTrace();
			
		}
		    
		    return "0";
	}
	
	public static String encryptPassTemp(String pass)
	{
		SHA1   s     = new SHA1();
		String ruido = "uy0hk";
		
		try 
		{
            return s.getHash(s.getHash(pass + ruido) + ruido);
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
		
		return "0";	
	}
	
	public static String encodeBase64( String word ){
		
		String s =  new String(DatatypeConverter.parseBase64Binary( word ));
		
		return s;
		
	}
	
	public static String decodeBase64( String wordB64 ){
		
		String s =   DatatypeConverter.printBase64Binary( wordB64.getBytes() );
		return s;
	}
	
/*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String> pdfToImage(String realPath,String files){		
		List<String> listImg= new ArrayList<String>();
		String urlImg;
		PDDocument document = null;
		try {
			File thisFile=new File(files);			
			document=PDDocument.load(thisFile);
			List<PDPage> pages = document.getDocumentCatalog().getAllPages();
			String name=thisFile.getName().substring(0,thisFile.getName().lastIndexOf("."));
			
			Iterator iter = pages.iterator(); 
		    int i =1;
		    while (iter.hasNext()) {
		        PDPage page = (PDPage) iter.next();
		        PDResources resources = page.getResources();
		        Map pageImages = resources.getImages();
		        if (pageImages != null) { 
		            Iterator imageIter = pageImages.keySet().iterator();
		            while (imageIter.hasNext()) {
		                String key = (String) imageIter.next();
		                PDXObjectImage image = (PDXObjectImage) pageImages.get(key);
		                urlImg="/temp/"+name+i;
		                image.setWidth(500);
		                image.setHeight(500);
		                image.write2file(new File(realPath+urlImg+"."+image.getSuffix()));			                
		                listImg.add(urlImg+"."+image.getSuffix());
		                i++;
		            }
		        }
		    }								
			
		} catch (Exception e) {
			throw new RuntimeException("An exception occured in parsing the PDF Document.", e);
		} finally {
			try {
				document.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return listImg;
		
	}*/
	
	public static String getRandomName(){
		int i = (int) (Math.random() * 100);
		Calendar cal2 = Calendar.getInstance();
		String randomName=((cal2.getTimeInMillis() / 100)+"").toString();
		randomName=randomName.substring(randomName.length()-7);		
		return i+randomName;
	}
	
	public static boolean copyFile(String fileName, InputStream in){
		boolean result=false;
		 try {
             // write the inputStream to a FileOutputStream
			 
			 File file = new File(fileName);
			 
             OutputStream out = new FileOutputStream( file );  
             
             int read = 0;
             byte[] bytes = new byte[1024];
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }          
             in.close();
             out.flush(); 
             out.close();
             result= true;
             } catch (IOException e) {
            	 e.printStackTrace();
            	 result= false;
             }
		 return result;
	}
	
	 public static boolean deleteFileDirByEqualName(File file,String delthisFile) 
	 {
		boolean bandera = false;
		
		 try 
		 {
			 if(file.isDirectory())
			 {
				    File[] files = file.listFiles();
				    
				    for(int i = 0; i < files.length; i++) 
				    {
				    		if(!files[i].isDirectory())
				    		{
				    			if(files[i].getName().length()>delthisFile.length())
				    			{
					    			String toEqual=files[i].getName().substring(0, delthisFile.length());
					    			
					    			if(toEqual.equals(delthisFile))
					    			{
					    				files[i].delete();	
					    				bandera=true;
					    			}					    							    			
					    			
				    			}
				    		}
				    	/*
				        if(files[i].isDirectory()) {  
				         this.deleteFileDirByEqualName(files[i]);  
				        } else {  
				         files[i].delete();  
				        }  */
				     }
				  }
			
		} catch (Exception e) {
			bandera= false;
		} 
		 
		 return bandera;
		 }
	 
	 public static boolean createDirectory(String path)
	 {
		 boolean result = false;
		 
		 try 
		 {
			 File file = new File(path);
			 
			 if (!file.isDirectory()) 
			 {
				 file.mkdirs();
				 
				 result = true;
					
			} else {
					
				result = true;
			}
				
		} catch (Exception e) {
			
			e.printStackTrace();
			
			result = false;
		}
		 
		return result;
			
	 }
	 
	 
	 public static boolean deleteThisFile(String path){
		 boolean result=false;
		 try {
			 File file =new File(path);
			 result=file.delete();
		} catch (Exception e) {
			result=false;
		}
		 return result;
		 
	 }
	 
	 public static String getMetadataPDF(File file){
		 String metadata=null;
		 try {
			 PDDocument doc = PDDocument.load(file);
			 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");			 
			 PDDocumentInformation docInfo=doc.getDocumentInformation();
			 metadata="";
			 metadata+="Page Count:"+doc.getNumberOfPages()+"|";
			  if(docInfo.getTitle()!=null)
				 metadata+="Title:"+docInfo.getTitle()+"|";
			 if(docInfo.getAuthor()!=null)
				 metadata+="Author:"+docInfo.getAuthor()+"|";
			 if(docInfo.getSubject()!=null)
				 metadata+="Subject:"+docInfo.getSubject()+"|";
			 if(docInfo.getKeywords()!=null)
				 metadata+="Keywords:"+docInfo.getKeywords()+"|";
			 if(docInfo.getCreator()!=null)
				 metadata+="Creator:"+docInfo.getCreator()+"|";
			 if(docInfo.getProducer()!=null)
				 metadata+="Producer:"+docInfo.getProducer()+"|";
			 if(docInfo.getCreationDate()!=null && docInfo.getCreationDate().getTime()!=null)
				 metadata+="Creation Date:"+sdf.format(docInfo.getCreationDate().getTime())+"|";
			 if(docInfo.getModificationDate()!=null && docInfo.getModificationDate().getTime()!=null)
				 metadata+="Modification Date:"+sdf.format(docInfo.getModificationDate().getTime())+"|";
			 if(docInfo.getTrapped()!=null)
				 metadata+="Trapped:"+docInfo.getTrapped()+"|";	
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		 return metadata;
	 }
	 
	 public static String getAddress_ip(){
		 try {
			 InetAddress ownIP=InetAddress.getLocalHost();
			 return ownIP.getHostAddress().toString();
		} catch (Exception e) {
			return null;
		}
	 }
	 
	 
	 public static boolean fileMove(String sourceFile, String destinationFile) {

		   try {
		    File inFile = new File(sourceFile);
		    File outFile = new File(destinationFile);

		    FileInputStream in = new FileInputStream(inFile);
		    FileOutputStream out = new FileOutputStream(outFile);

		    int c;
		    while ((c = in.read()) != -1)
		     out.write(c);

		    in.close();
		    out.close();

		    File file = new File(sourceFile);
		    if (file.exists()) {
		     file.delete();
		    }
		    return true;
		   } catch (IOException e) {
		    System.err.println("Hubo un error de entrada/salida!!!");
		    return false;
		   }
		   
	}
	 
	 public static boolean validaMail(String mailStr){
			if( mailStr != null ){
				Pattern p = Pattern.compile("^\\w+([\\.\\-*\\w+])*@\\w([\\-\\w])+\\.\\w{2,4}([\\.*\\w{2,3}])*$");
				//([\\w\\-]+\\.)+[A-Z]{2,4}([\\.\\w])?$
				
				Matcher m = p.matcher(mailStr);
				
				if ( m.matches())
					return true;
				else
					return false;
			}else{
				return true;
			}
		}
	 
	 public static Integer calcularEdad(String fecha){
		    Date fechaNac=null;
		        try {
		            /*Se puede cambiar la mascara por el formato de la fecha que se 
		            //quiera recibir, por ejemplo año mes día "yyyy-MM-dd"
		            en este caso es día mes año*/
		            fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
		        } catch (Exception ex) {
		            System.out.println("Error:"+ex);
		        	return 0;
		        }
		        Calendar fechaNacimiento = Calendar.getInstance();
		        //Se crea un objeto con la fecha actual
		        Calendar fechaActual = Calendar.getInstance();
		        //Se asigna la fecha recibida a la fecha de nacimiento.
		        fechaNacimiento.setTime(fechaNac);
		        //Se restan la fecha actual y la fecha de nacimiento
		        int year = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
		        int month =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
		        int day = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
		        //Se ajusta el año dependiendo el mes y el día
		        if(month<0 || (month==0 && day<0)){
		        	year--;
		        }
		        //Regresa la edad en base a la fecha de nacimiento
		        return year;
		    }
	 
	 
	public static int digitoVerificador(String cadena){
			int digitoVer = 0;
			int i= 0;
			int j= cadena.length();

			while( i < cadena.length()) {
				 digitoVer =  digitoVer +
				(Integer.parseInt(cadena.charAt(i)+"") * j);
				
				    j = j - 1;
				    i = i + 1;
			    
			}

			int mod = digitoVer % (cadena.length());

			if (mod == 0){
			     digitoVer = 1;
			}else{
			    if (mod == 1){ 
			        digitoVer = 0;
			    }else{
			    	if(mod >9)
			    		digitoVer = (cadena.length()) - mod;
			    	else
			    		digitoVer = mod;
			    }
			}
			return digitoVer;
		}
	
	public static String getRandomCharacter(){
		String [] cadena= {"ad","2d","d4","49e","34jd","dr","7D","8D","9D","0D" ,"JD","QD","KD", "uj3","qw","BB","4B","7ee","3e","00","8B","9B","0B", "JB","QB","KB", "AC","2C","3C","4C","5C","6C","7C","8C","9C","0C", "JC","QC","kc4","iry","2F","3F","4F","5F","6F","7F", "8F","9F","0F","JF","QF","8u"};
		int numRandom = (int) Math.round(Math.random() * (cadena.length-1) ) ;
		return cadena[numRandom];		
	}
	
	
	public String diasTrans(Date fecInferior, Date fecSuperior){
		
		GregorianCalendar date1 = new GregorianCalendar();
        date1.setTime(fecInferior);
        GregorianCalendar date2 = new GregorianCalendar();
        date2.setTime(fecSuperior);
        int rangoAnyos = 0;
        int rango =0;
        /* COMPROBAMOS SI ESTAMOS EN EL MISMO ANYO */
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
        	
        	rango = (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
        	
        } else {
            /* SI ESTAMOS EN DISTINTO ANYO COMPROBAMOS QUE EL ANYO DEL DATEINI NO SEA BISIESTO
             * SI ES BISIESTO SON 366 DIAS EL ANYO
             * SINO SON 365
             */
            int diasAnyo = date1.isLeapYear(date1.get(Calendar.YEAR)) ? 366 : 365;

            /* CALCULAMOS EL RANGO DE ANYOS */
            rangoAnyos = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);

            /* CALCULAMOS EL RANGO DE DIAS QUE HAY */
            rango = (rangoAnyos * diasAnyo) + (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));

        }
		
		return rango+"";
		
	}
	
	public static java.sql.Date restarFechasDias(Date fecha, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fecha.getTime());
        cal.add(Calendar.DATE, -dias);
        return new java.sql.Date(cal.getTimeInMillis());
      //Retorna este formato: 2011-10-01
    }

	public static String capilizeString(String cadena)
	{
		char[] caracteres = cadena.toCharArray();
		caracteres[0] = Character.toUpperCase(caracteres[0]);
		
	    // el -2 es para evitar una excepción al caernos del arreglo
	    for (int i = 1; i < cadena.length(); i++)
	    {
	    	// Es 'palabra'
	    	if (caracteres[i-1] == ' ' || caracteres[i-1] == '.' || caracteres[i-1] == ',')
	    	{	    		
	    		caracteres[i] = Character.toUpperCase(caracteres[i]);
	    		
	    	} else {	    		
	    		
	    		caracteres[i] = Character.toLowerCase(caracteres[i]);	    		
	    	}
	    }
	    
	    return new String(caracteres);
	}

	public static java.sql.Date restarFechasMeses(Date fecha, int meses) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fecha.getTime());
        cal.add(Calendar.MONTH, -meses);
        return new java.sql.Date(cal.getTimeInMillis());
        //Retorna este formato: 2011-10-01
    }
	
	//Convierte un carpeta completa a un archivo .zip
	public static boolean createZip(String pathSourceFolder, String pathFileToZip){
		 try {
		 	 final int BUFFER = 1024;
			 //Nuestro InputStream
			 BufferedInputStream origin = null;
			 //Declaramos el FileOutputStream para guardar el archivo
			 FileOutputStream dest = new FileOutputStream(pathFileToZip);
			 //Indicamos que será un archivo ZIP
			 ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
			 //Indicamos que el archivo tendrá compresión
			 out.setMethod(ZipOutputStream.DEFLATED);
			 //Indicamos que el archivo NO tendrá compresión
			 //out.setMethod(ZipOutputStream.STORED);
			 byte data[] = new byte[BUFFER];
			 // Creamos la referencia de la carpeta a leer
			 File f = new File(pathSourceFolder);
			 // Guarda los nombres de los archivos de la carpeta a leer
			 String files[] = f.list();
			 // Muestra el listado de archivos de la carpeta a leer
			 for (int i=0; i<files.length; i++) {
				 //Creamos el objeto a guardar para cada uno de los elementos del listado
				 FileInputStream fi = new FileInputStream(pathSourceFolder+files[i]);
				 origin = new BufferedInputStream(fi, BUFFER);
				 ZipEntry entry = new ZipEntry(files[i]);
				 //Guardamos el objeto en el ZIP
				 out.putNextEntry(entry);
				 int count;
				 //Escribimos el objeto en el ZIP
				 while((count = origin.read(data, 0,BUFFER)) != -1) {
					 out.write(data, 0, count);
				 }
				 origin.close();
			 }
			 out.close();
			 return true;
		 } catch(Exception e) {
			 e.printStackTrace();
			 return false;
		 }
	 }
	
	public static boolean deleteDirectory(File directory){
		try{
			 File[] files = directory.listFiles();
	         for (int x=0;x<files.length;x++){
	                 if (files[x].isDirectory()) {
	                	 deleteDirectory(files[x]);
	                 }
	                 files[x].delete();
	         }
	         return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getBCDesc( String score){
		
		String res = "";
			
			if(score != null){
				
				int i = Integer.parseInt(score);
				
				if( i<0 ){
					
					if(i == (-1) ){
						
						res = "<br />Consumidor Fallecido<br />";
						
					}else if(i == (-5) ){
						
						res = "<br />Expediente con todas las cuentas cerradas y por lo menos con una en atraso mayor o igual a 90 días<br />";
						
					}else if(i == (-6) ){
						
						res = "<br />Expediente con todas sus cuentas con antigüedad menor a 6 meses y al menos una tiene MOP >= 03<br />";
						
					}else if(i == (-7) ){
						
						res = "<br />Expediente con todas sus cuentas con antigüedad menor a 6 meses y al menos una tiene MOP >= 02<br />";
						
					}else if(i == (-8) ){
						
						res = "<br />Expediente no tiene al menos una cuenta actualizada en el último año o con antigüedad mínima de 6 " +
								"meses, y/o con cuentas que no se incluyen en el cálculo del BC-Score<br />";
						
					}else if(i == (-9) ){
						
						res = "<br />Expediente sin cuentas para cálculo de BC-Score<br />";
					}
				}
				
			}
		
		return res; 	
	}
	
	public static String getDeploymentPath()
	{
		String deploymentDirectoryPath;
		
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
        deploymentDirectoryPath  = ctx.getRealPath("/");                
        
        return deploymentDirectoryPath;
	}
	
    public static boolean isNumeric(String cadena){
	    try {
	    	
	    	Long.parseLong(cadena);
	    	
	    	return true;
	    } catch (NumberFormatException nfe){
	    	
	    	return false;
	    	
	    }
    }
    
    public static boolean isDouble(String cadena){
	    try {
	    	
	    	Double.parseDouble(cadena);
	    	return true;
	    	
	    } catch (NumberFormatException nfe){
	    	
	    	return false;
	    	
	    }
    }
    
    public static String getTipoCredito( String loan_type ){
    	
    	String loanType = "";
    	
    	if( loan_type == null ){
			
			loanType = "Primera vez";
		}
		else if( loan_type.equals("NVO") ){
				
			loanType = "Primera vez";
			
		}
		else if( loan_type.equals("RST") || ( loan_type.equals("RST") ) ){
			
			loanType = "Reestructurado";
			
		}else if( loan_type.equals("ADD") || ( loan_type.equals("REN") ) || ( loan_type.equals("RDC") ) ){
			
			loanType = "Repetido";
			
		}
    	
    	return loanType;
    	
    }
    
    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
    
    public static String quitaAcentos( String str ){
		
		if( str != null ) {
			
			String original = "áàäéèëíìïóòöúùüuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		    // Cadena de caracteres ASCII que reemplazarán los originales.
		    String ascii = "aaaeeeiiiooouuuunAAAEEEIIIOOOUUUNcC";
		    
		    for (int i=0; i<original.length(); i++) {
		        // Reemplazamos los caracteres especiales.
		    	str = str.replace(original.charAt(i), ascii.charAt(i));
		    }//for i
		    
			str = str.replaceAll("\"","");
			
			str = str.replaceAll("\\\\","");
			
			str = str.replaceAll("/","");
			
			str = str.replaceAll("'","");
			
			str = str.replaceAll("&","");
		
		}
		
		return str;
	}
    
    public static int asignar_dias_transcurridos_a_hoy( Date dFecha ) 
	{
		GregorianCalendar date1 = new GregorianCalendar();
        date1.setTime(dFecha);
        
        GregorianCalendar date2 = new GregorianCalendar();
        date2.setTime(new Date());
        
        int rangoAnyos = 0;
        int rango = 0;
        int diasAnyo = 0;
        /* COMPROBAMOS SI ESTAMOS EN EL MISMO ANYO */
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) 
        {        
        	rango = (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
        	
        } else {
            /* SI ESTAMOS EN DISTINTO ANYO COMPROBAMOS QUE EL ANYO DEL DATEINI NO SEA BISIESTO
             * SI ES BISIESTO SON 366 DIAS EL ANYO
             * SINO SON 365
             */
            diasAnyo = date1.isLeapYear(date1.get(Calendar.YEAR)) ? 366 : 365;

            /* CALCULAMOS EL RANGO DE ANYOS */
            rangoAnyos = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);

            /* CALCULAMOS EL RANGO DE DIAS QUE HAY */
            rango = (rangoAnyos * diasAnyo) + (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
        }
		
		return rango;	
	}
    
    public static String md5_encode( String str ) 
	{
    	
		try {

			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			
			byte[] array = md.digest(str.getBytes());
			
			StringBuffer sb = new StringBuffer();
			
			for (int i = 0; i < array.length; ++i) {
			
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			
			}
			
			return sb.toString();

		} catch (Exception e) {
			return null;
		}
    	
	}

}