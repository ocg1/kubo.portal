package mx.com.kubo.controller.contract.investor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class CreaContrato {
	
	private String css="";
	private String cuerpoContrato;
	private String fyle_type ;
	private String companyIdStr;
	private String prospectusIdStr;
	private String category_str;
	private String url_firma;
	private String location;
	private String locationPagare;
	private boolean iscopy;
	
	//public static final String DEST = "//opt//contrato77CSS.pdf";
	public static final String CSS2 = "  " ;
	
	public CreaContrato( String cuerpoContrato,String fyle_type,String companyIdStr,String prospectusIdStr,String category_str ){
		
		this.cuerpoContrato = cuerpoContrato;
		this.fyle_type = fyle_type ;
		this.companyIdStr = companyIdStr;
		this.prospectusIdStr = prospectusIdStr;
		this.category_str = category_str;
		this.iscopy = false;
		
	}
	 
    public boolean generaContrato( String servidor ) {
    	
    	try{
    	
    	    //servidor = "http://localhost:8080/Kubo/";
    		
	    	String fileNameFile = "";
	    	
	    	String fileNameFile_hist = "";
			
			String fileName = "";			
			
			Date d = new Date();
			
			fileNameFile = fyle_type +"_"+companyIdStr+"_"+prospectusIdStr+"_"+d.getTime()+".pdf";
			
			fileNameFile_hist = fyle_type +"_"+companyIdStr+"_"+prospectusIdStr+"_hist_"+d.getTime()+".pdf";
			
			fileName = "cia_"+companyIdStr+"/pros_"+prospectusIdStr+"/"+category_str+"/";
			
			String destination = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/resources/documents/");
			
			String destination_hist = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/resources/historic/");
			
			String path = destination + "/" + fileName;
			
			String path_hist = destination_hist + "/" + fileName;
			
			System.out.println( "path1: " + path );
			
			validateDir( path );
			
			validateDir( path_hist );
			
			path += fileNameFile;
			
			path_hist += fileNameFile_hist;
			
			location = "/documents/"+fileName+fileNameFile;
	    	
			//createPdf( path_hist, servidor,fileNameFile_hist);
	        
	        return createPdf( path, servidor,fileNameFile);
	        
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return false;
    		
    	}
    }
    
    public boolean generaContratoCopia( String servidor ) {
    	
    	try{
    	
    	    //servidor = "http://localhost:8080/Kubo/";
    		
	    	String fileNameFile = "";
			
			String fileName = "";
			
			Date d = new Date();
			
			fileNameFile = fyle_type +"_"+companyIdStr+"_"+prospectusIdStr+"_"+d.getTime()+".pdf";
			
			fileName = "cia_"+companyIdStr+"/pros_"+prospectusIdStr+"/"+category_str+"/Copia/";
			
			String destination = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/resources/documents/");
			
			String path = destination + "/" + fileName;
			
			System.out.println( "path1: " + path );
			
			validateDir( path );
			
			path += fileNameFile;
			
			location = "/documents/"+fileName+fileNameFile;
	    	
			iscopy = true;
			boolean flag =  createPdf( path, servidor, fileNameFile);
			iscopy = true;
	        return flag;
	        
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return false;
    		
    	}
    }
	
    
    public boolean generaContratoCredito( String servidor, String py_ln_id ) {
    	
    	try{
    	
    	    //servidor = "http://localhost:8080/Kubo/";
    		
    		String inicioPagare = "<span class=\"pagareInicio\"></span>";
    		
    		String finPagare = "<span class=\"pagareFin\"></span>";
    		
    		String saltaPag = "<div class=\"pagebreak\"></div>";
    		
	    	String fileNameFile = "";
			
			String fileName = "";
			
			Date d = new Date();
			
			if( iscopy ){
				
				fileNameFile = fyle_type+ "_" + py_ln_id +"_"+companyIdStr+"_"+prospectusIdStr+"_copia.pdf";
				
			}else{
			
				fileNameFile = fyle_type+ "_" + py_ln_id +"_"+companyIdStr+"_"+prospectusIdStr+"_"+d.getTime()+".pdf";
				
			}
			
			fileName = "cia_"+companyIdStr+"/pros_"+prospectusIdStr+"/"+category_str+"/";
			
			String destination = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/resources/documents/");
			
			String path = destination + "/" + fileName;
			
			System.out.println( "path1: " + path );
			
			validateDir( path );
			
			path += fileNameFile;
			
			location = "/documents/"+fileName+fileNameFile;
	    	
	    	StringBuilder body_html1 = new StringBuilder( getCuerpoContrato() );
	    	
	    	if( !iscopy ){
	    	
			    	// ---
			    	
			    	StringBuilder body_inicio = new StringBuilder( body_html1.substring(0,  body_html1.indexOf(inicioPagare)  ) );
			    	
			    	StringBuilder htmlInicio =  procesaHTML( getHTML(servidor, body_inicio),  servidor  );
			    	
			    	body_inicio = null;
			    	
			    	// ---
			    	
			    	StringBuilder body_pagare = new StringBuilder( body_html1.substring( (body_html1.indexOf(inicioPagare) + inicioPagare.length() ) ,  body_html1.indexOf(finPagare) ) );
			    	
			    	StringBuilder htmlPagare =  procesaHTML( getHTML(servidor, body_pagare),  servidor  );
			    	
			    	body_pagare = null;
			    	
			    	// ---
			    	
			    	StringBuilder body_fin = new StringBuilder( body_html1.substring( (body_html1.indexOf(finPagare) + finPagare.length() )  ) );
			    	
			    	body_fin = new StringBuilder( body_fin.substring( (body_fin.indexOf(saltaPag) + saltaPag.length() )  ) );
			    	
			    	StringBuilder htmlfin =  procesaHTML( getHTML(servidor, body_fin),  servidor  );
			    	
			    	body_fin = null;
			    	
			    	// --- INICIO
			    	
			    	body_html1 = null;
			    	
			    	String path_html_inicio = path.substring( 0, (path.indexOf("pdf") -1 ));
			    	path_html_inicio=path_html_inicio+"_inicio.pdf";
			    	System.out.println( path_html_inicio );
			    	
			    	createFilePDF( path_html_inicio, servidor, htmlInicio ) ;
			    	 
			    	// --- PAGARE MESA
			    	
			    	String path_html_pagare = destination + "/" + fileName + "Pagare1_"+companyIdStr+"_"+prospectusIdStr+"_"+d.getTime()+".pdf";
			    	//path_html_pagare_copia=path_html_pagare_copia+"_pagare.pdf";
			    	System.out.println( path_html_pagare );
			    	
			    	createFilePDF( path_html_pagare, servidor, htmlPagare ) ;
			    	
			    	locationPagare =  "/documents/" + fileName + "Pagare1_"+companyIdStr+"_"+prospectusIdStr+"_"+d.getTime()+".pdf";
			    	
			    	// --- PAGARE COPIA
			    	
			    	String path_html_pagare_copia_TMP = path_html_pagare;
			    	path_html_pagare_copia_TMP=path_html_pagare_copia_TMP.replace(".pdf", "_pagare_TMP.pdf");
			    	System.out.println( path_html_pagare_copia_TMP );
			    	
			    	
			    	createFilePDF( path_html_pagare_copia_TMP, servidor, htmlPagare ) ;
			    	
			    	String path_html_pagare_copia = path_html_pagare_copia_TMP.replace("_TMP", "");
			    	
			    	iscopy = true;
			    	createCopyPdf(path_html_pagare_copia_TMP, path_html_pagare_copia, servidor, false,false,"");
			    	iscopy = false;
			    	
			    	// --- FIN
			    	
			    	String path_html_fin = path.substring( 0, (path.indexOf("pdf") -1 ));
			    	path_html_fin=path_html_fin+"_fin.pdf";
			    	System.out.println( path_html_fin );
			    	
			    	createFilePDF( path_html_fin, servidor, htmlfin ) ;
			    	 
			    	// --- MERGED
					
			    	List<InputStream> list = new ArrayList<InputStream>();
			       
		            // Source pdfs
			    	
			    	File f1 = new File( path_html_inicio );
			    	File f2 = new File( path_html_pagare_copia );
		            File f3 =new File( path_html_fin );
			    	
		            list.add(new FileInputStream(f1));
		            list.add(new FileInputStream(f2));
		            list.add(new FileInputStream(f3));
		
		            // Resulting pdf
		            
		            String path_html_merge = path.substring( 0, (path.indexOf("pdf") -1 ));
		            path_html_merge=path_html_merge+"_merged.pdf";
		            
		            OutputStream out = new FileOutputStream(new File(path_html_merge));
		            
		            doMerge(list, out);
			    	
		            f1.delete();
		            f2.delete();
		            f3.delete();
		            
			    	// -- 
		            
		            createCopyPdf(path_html_merge, path, servidor, true, true, fileNameFile );
		            
	    	}else{
	    		
	    		StringBuilder body_inicio = new StringBuilder( body_html1 );
		    	
		    	StringBuilder htmlcopy =  procesaHTML( getHTML(servidor, body_inicio),  servidor  );
		    	
		    	File f = new File( path );
		    	
		    	if( f.exists() ){
		    		f.delete();
		    	}
	    		
		    	
		    	String path_html_tmp = path.substring( 0, (path.indexOf("pdf") -1 ));
		    	path_html_tmp=path_html_tmp+"_TMP.pdf";
		            
		    	createFilePDF( path_html_tmp, servidor, htmlcopy ) ;
		    	createCopyPdf(path_html_tmp, path, servidor, true, false, "");
	    		
	    	}
	    	
            
	    	
            return true;
	        
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return false;
    		
    	}
    }
    
    
	private boolean createPdf(String path, String servidor , String fileName ) {
    	
		boolean flag = false;
		
    	try{
    	
	    	System.out.println( "Comenzando" );
	    	
	    	String path2 = path.substring( 0, (path.indexOf("pdf") -1 ));
	    	path2=path2+"_TMP.pdf";
	    	System.out.println( path2 );
	    	
	    	StringBuilder body_html = new StringBuilder( getCuerpoContrato() );
	    	
	    	StringBuilder htmlTXT =  procesaHTML( getHTML(servidor, body_html),  servidor  );
	    	
	    	body_html = null;
	    	
	    	if(createFilePDF( path2, servidor, htmlTXT ) ){
	    	       
		        createCopyPdf( path2 , path, servidor, true, true, fileName);
		        
		        flag = true;
		        
		        System.out.println( "Finalizado" );
	    	}else{
	    		flag = false;
	    		System.out.println( "Finalizado El FilePDF no se creó ..." );
	    	}
        
    	}catch(Exception e){
    		
    		flag = true;
    		e.printStackTrace();
    	       
    	}
        
    	return flag;
    	
    }
	
	private boolean createFilePDF( String path, String servidor, StringBuilder htmlTXT ){
		
		boolean flag = false;
		
		try{
		
				File file_temp = new File(path);
		        file_temp.getParentFile().mkdirs();
		    	
		        // step 1
		        Document document = new Document();
		        // step 2
		        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
		        // step 3
		        document.open();
		        // step 4
		        
		        CSSResolver cssResolver = new StyleAttrCSSResolver();
		        CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(css.getBytes()));
		        cssResolver.addCss(cssFile);
		 
		        // HTML
		        HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
		        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
		 
		        // Pipelines
		        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
		        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
		        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
		 
		        // XML Worker
		        XMLWorker worker = new XMLWorker(css, true);
		        XMLParser p = new XMLParser(worker);
		        
		        
		        
		        //System.out.println( htmlTXT );
		        p.parse(new ByteArrayInputStream(htmlTXT.toString().getBytes()));
		 
		        htmlTXT = null;
		        
		        // step 5
		        document.close();
		        
		        flag = true;
		        
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
 
		return flag;
		
	}
	
	private StringBuilder procesaHTML( String html, String servidor ){
		
		   StringBuilder htmlTXT = new StringBuilder();
        
	       htmlTXT.append( html );
		
	       htmlTXT = new StringBuilder( htmlTXT.toString().replaceAll("<span class=\"firmaCliente\"></span>","<img  src='"+url_firma+"' />" )  ); 

	       htmlTXT = new StringBuilder( htmlTXT.toString().replaceAll("<hr>", "<hr />" ) );
	       
	       htmlTXT = new StringBuilder( htmlTXT.toString().replaceAll("<br>", "<br />" ) );
	        
//			<img src="/Kubo/javax.faces.resource/firmaRodrigoMg.png.xhtml?ln=img">
	        
	        String pattern1 = "/Kubo/javax.faces.resource";
	        String pattern2 = "xhtml?ln=";
	        String pattern3 = "\">";
	        String pattern4 = ";jsessionid=";
	        String RUTA 	= servidor + "resources/";
	        
	        
	        //System.out.println( "htmlTXT: " + htmlTXT.length() );
	        
	        while( htmlTXT.toString().indexOf( pattern4 ) != (-1) ){
	        	
	        	int i = htmlTXT.toString().indexOf( pattern4 );
	        	
	        	System.out.println( "';jsessionid=' index of:"+i );
	        	
	        	StringBuilder  txt1 = new StringBuilder (htmlTXT.toString().substring(0 , i ) );
	        	
	        	StringBuilder  txt1_1 = new StringBuilder ( htmlTXT.toString().substring( i + pattern4.length() )  );
	        	
	        	StringBuilder txt2 = new StringBuilder( txt1_1.toString().substring(txt1_1.toString().indexOf("?ln="))  );
	        	
	        	htmlTXT = new StringBuilder( txt1.toString()+txt2.toString() );
	        }
	        
	        while( htmlTXT.toString().indexOf(pattern1) != (-1)){
	        	
	        	int p1 = htmlTXT.toString().indexOf(pattern1);
	        	
	        	//System.out.println( " index: " + p1 );
	        	
	        	StringBuilder txt1 = new StringBuilder( htmlTXT.toString().substring(0 , p1 ) );
	        	
	        	StringBuilder txt2 = new StringBuilder( htmlTXT.toString().substring( p1 ) );
	        	
	        	//System.out.println( " substring 01 : " + txt2.substring(0,100) );
	        	
	        	StringBuilder txt3 = new StringBuilder( "\" /" +  txt2.toString().substring( (txt2.toString().indexOf(pattern3) + (pattern3).length() ) -1 ) );
	        	
	        	//txt3 = "\" /" + txt3;
	        	
	        	// System.out.println( " substring 1 : " + txt3.substring(0,100) );
	        	
	        	// System.out.println( " pattern1.length: " + pattern1.length() );
	        	
	        	int p2 = txt2.toString().indexOf(pattern2);
	        	
	        	//System.out.println( " pattern2.indexOf: " + p2 );
	        	
	        	StringBuilder imgName = new StringBuilder( txt2.toString().substring(pattern1.length(), p2-1) );
	        	
	        	//String txt2_1 = txt2;
	        	
	        	//System.out.println( " substring txt2+100: " + txt2.substring(0, 100) );
	        	
	        	StringBuilder pathImg = new StringBuilder( txt2.toString().substring(( txt2.toString().indexOf(pattern2) +  pattern2.toString().length()),txt2.toString().indexOf(pattern3) ) );
	        	
	        	
	        	
	        	StringBuilder imgSource = new StringBuilder(   RUTA + pathImg.toString() + imgName.toString() );
	        	
	        	// System.out.println( " IMG RESOURCE: " + imgSource );
	        	
	        	htmlTXT = new StringBuilder(  txt1.toString() + imgSource.toString() + txt3.toString() );
	        }
		
	        return htmlTXT;
	        
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
	
	private  void createCopyPdf(String src, String dest_or, String servidor , boolean withNumber, boolean withHist, String nameFile ) throws IOException, DocumentException {
    	
    	try{
    	
    	System.out.println( "Comenzando Copia" );
    	
    	String RUTA_IMG 	= servidor + "resources/img/copia.png";
    	
    	String dest_hist = dest_or.replace("documents", "historic" );
    	
    	if( withHist ){
    		
    		validateDir( dest_hist.substring(0, dest_hist.indexOf( nameFile ) )   );
    		File f1 = new File( dest_hist );
    	}
    	
    	System.out.println( "origen: " + src );
    	System.out.println( "destino: " + dest_or );
    	System.out.println( "destino_hist: " + dest_hist );
    	
    	for( int iX = 0 ; iX < 2 ; iX++ ){
    		
    		String dest = "";
    		
    		if( iX == 0  ){
    			dest = dest_or;
    		}else if( iX == 1){
    			
    			if( !withHist ){
    				continue;
    			}
    			
    			dest = dest_hist;
    		}
    		
    	  PdfReader reader_tmp = new PdfReader(src);
          int n = reader_tmp.getNumberOfPages();
          PdfStamper stamper_tmp = new PdfStamper(reader_tmp, new FileOutputStream(dest));
          stamper_tmp.setRotateContents(false);
          // image watermark
          Image img = Image.getInstance( RUTA_IMG );
          float w = img.getScaledWidth();
          float h = img.getScaledHeight();
          // transparency
          PdfGState gs1 = new PdfGState();
          gs1.setFillOpacity(0.18f);
          // properties
          PdfContentByte over;
          Rectangle pagesize;
          float x, y;
          // loop over every page
          for (int i = 1; i <= n; i++) {
        	  
              pagesize = reader_tmp.getPageSize(i);
              x = (pagesize.getLeft() + pagesize.getRight()) / 2;
              y = (pagesize.getTop() + pagesize.getBottom()) / 2;
              
              over = stamper_tmp.getOverContent(i);
              over.saveState();
              over.setGState(gs1);
              
              if( iscopy ){
	              over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
              }
	          
              over.restoreState();
             
	          if( withNumber ){
	          
	        	  ColumnText.showTextAligned(over, Element.ALIGN_RIGHT,
                      new Phrase(String.format("%s de %s", i, n)), 559, 10, 0);
              
	          }
          }
          stamper_tmp.close();
          reader_tmp.close();
          
    	}
          
    	    File f = new File( src );
    	    f.delete();
        	
    	
    	}catch( Exception e ){
    		e.printStackTrace();
    	}
        
    }

	private String getHTML(String servidor, StringBuilder cuerpo){
		
		String str = 
	    		  "<html><head>"
	    		  + ""
	    		  + "<link rel='stylesheet' type='text/css' href='"+servidor+"jsf/docs/contratos/css/customPDF.css' />"
//	    		  + "<link rel='stylesheet' type='text/css' href='https://www.kubofinanciero.com/Kubo/resources/css/kubo_styles.css' />"
//	    		  + "<link rel='stylesheet' type='text/css' href='https://www.kubofinanciero.com/Kubo/jsf/docs/contratos/css/normalize.css'/>"
	    		  + ""	    		 
	    		  + "</head><body>"
	        + ""
	       
	        + cuerpo.toString()
	        
	        + "" +
	        "</body></html>";
		
		return str;
		
	}
	
	
	public static void doMerge(List<InputStream> list, OutputStream outputStream)
            throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        
        for (InputStream in : list) {
            PdfReader reader = new PdfReader(in);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                document.newPage();
                //import the page from source pdf
                PdfImportedPage page = writer.getImportedPage(reader, i);
                //add the page to the destination pdf
                cb.addTemplate(page, 0, 0);
            }
        }
        
        outputStream.flush();
        document.close();
        outputStream.close();
    }
	

public String getCuerpoContrato() {
	return cuerpoContrato;
}


public void setCuerpoContrato(String cuerpoContrato) {
	this.cuerpoContrato = cuerpoContrato;
}

public String getUrl_firma() {
	return url_firma;
}

public void setUrl_firma(String url_firma) {
	this.url_firma = url_firma;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public boolean isIscopy() {
	return iscopy;
}

public void setIscopy(boolean iscopy) {
	this.iscopy = iscopy;
}

public String getLocationPagare() {
	return locationPagare;
}

public void setLocationPagare(String locationPagare) {
	this.locationPagare = locationPagare;
}
	
}









