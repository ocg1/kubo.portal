package mx.com.kubo.managedbeans;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

import javax.faces.context.FacesContext;

import sun.misc.BASE64Decoder;


public class SignatureManaged implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String urlFirma;
	
	private String serialize_str;
	private String data_url_str;
	
	private String prospectusIdStr;
	private String companyIdStr;
	
	private String proyect_loan_idStr;
	
	public SignatureManaged( String companyIdStr, String prospectusIdStr, String serialize_str, String data_url_str, String proyect_loan_id){
		
		this.serialize_str = serialize_str;
		this.data_url_str = data_url_str;
		
		this.prospectusIdStr = prospectusIdStr;
		this.companyIdStr = companyIdStr;
		this.proyect_loan_idStr = proyect_loan_id;
		
	}
	
//	
//	
//	public void init_external_params(){
//		
//		prospectusIdStr = (String) external.getRequestParameterMap().get("prospecto");
//		companyIdStr = (String) external.getRequestParameterMap().get("cia");
//	}
	
//	public void getData(){
//		generaImg();
//	}
	
	public boolean generaImg( String tipo_contrato ){
		
		boolean flag = false;
		
		try{
			
			String fileNameFile = "firma.png";
			String fileNameFile_hist ="";
			
			String fileName = "";
			
			Date d = new Date();
			
			fileNameFile = "firma_"+tipo_contrato+"_"+companyIdStr+"_"+prospectusIdStr+"_"+proyect_loan_idStr+"_"+d.getTime()+".png";
			
			fileNameFile_hist = "firma_"+tipo_contrato+"_"+companyIdStr+"_"+prospectusIdStr+"_"+proyect_loan_idStr+"_"+"_hist_"+d.getTime()+".png";
			
			fileName = "cia_"+companyIdStr+"/pros_"+prospectusIdStr+"/Firma/";
			
			String destination = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/resources/documents/");
			String path = destination + "/" + fileName;
			
			String destination_hist = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/resources/historic/");
			String path_hist = destination_hist + "/" + fileName;
			
			System.out.println( "path1: " + path );
			System.out.println( "path1_hist: " + path_hist );
			
			validateDir( path );
			
			validateDir( path_hist );
			
			path += fileNameFile;
			path_hist += fileNameFile_hist;
			
			System.out.println( "path2: " + path );
			
			//System.out.println( data_url_str );
			
			//path = "/opt/" + fileName;
			
			String imageDataBytes = data_url_str.substring(data_url_str.indexOf(",")+1);
			
			if( path != null  ){
			
				BASE64Decoder decoder = new BASE64Decoder();
		        byte[] decodedBytes = decoder.decodeBuffer(imageDataBytes);
	
				InputStream stream = new ByteArrayInputStream(decodedBytes);
	
				OutputStream out = new FileOutputStream(new File(path));
	
				byte[] bytes = new byte[stream.available()];
				
				int read = 0;
				
				urlFirma = path;
				
				while ((read = stream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			
			}
			
			if( path_hist != null ){
				
				BASE64Decoder decoder = new BASE64Decoder();
		        byte[] decodedBytes = decoder.decodeBuffer(imageDataBytes);
	
				InputStream stream = new ByteArrayInputStream(decodedBytes);
	
				OutputStream out = new FileOutputStream(new File(path_hist));
	
				byte[] bytes = new byte[stream.available()];
				
				int read = 0;
				
				while ((read = stream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
				
			}
			
			flag = true;
		
		}catch( Exception e ){
			e.printStackTrace();
			flag = false;
		
		}
		
		return flag;
		
	}
	
	private boolean validateDir(String path) 
	{
		File file = new File(path);
		
		boolean isDirectory = file.isDirectory();
		
		if (!isDirectory) 
		{
			System.out.println("·#·#·#·#· Creando la ruta: " + path);
			file.mkdirs();
		} else {
			System.out.println("·#·#·#·#· Ya existe la ruta: " + path);
		}
		
		return isDirectory;
	}

	public String getSerialize_str() {
		return serialize_str;
	}

	public void setSerialize_str(String serialize_str) {
		this.serialize_str = serialize_str;
	}

	public String getData_url_str() {
		return data_url_str;
	}

	public void setData_url_str(String data_url_str) {
		this.data_url_str = data_url_str;
	}

	public String getUrlFirma() {
		return urlFirma;
	}
	
}
