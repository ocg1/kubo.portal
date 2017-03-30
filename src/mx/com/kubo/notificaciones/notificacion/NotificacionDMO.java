package mx.com.kubo.notificaciones.notificacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.ClabeAccountPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Motive;
import mx.com.kubo.model.MotivePK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.StatusProyectCatPK;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.creditos.CreditosVigentes;
import mx.com.kubo.registro.RegistrationReasonIMO;
import mx.com.kubo.registro.RegistrationReasonIMP;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.ClabeAccountService;
import mx.com.kubo.services.EstatusProyectLoanService;
import mx.com.kubo.services.MotiveService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;
import mx.com.kubo.tools.Utilities;

public abstract class NotificacionDMO
{
	protected ProyectLoanService        service_proyect_loan;
	protected EstatusProyectLoanService service_estatus;
	protected NotesService              service_notas;
	protected MotiveService             service_motive;
	protected Change_controlService     service_change_control;
	protected ClabeAccountService       service_clabe_account;
	protected RegistrationReasonIMO     service_reason;
	
	protected NotificacionEstatusEnvio estatus_envio;	
	protected CreditosVigentes         creditos_vigentes;	
	protected Membership               emisor;
	protected Membership               acreditado;
	
    protected ClabeAccount   clabe_account;
	protected ClabeAccountPK clabe_account_PK;
	
	protected StatusProyectCat   estatus;
	protected StatusProyectCatPK estatus_PK;
	protected Motive             motive;
	protected MotivePK           motive_PK;
	
	protected String url;
	
	protected Integer motivo_id_selected;
	
	protected Change_control change_control_LAST;
	
	protected String [] afected_table, afected_field;
	protected List<String> lista_archivos_adjuntos;
 	
	protected ConexionIMO   conexion_SMTP;
	protected MimeMultipart mimeMultipart;	
	protected MimeBodyPart  body;
	protected MimeMessage   MIME_message;
	
	protected List<Notes>   lista_notas;
	    
    protected Integer prospectusID;
    
    protected StringBuilder sb;
    
	protected String bcScore;
	protected String burSolNum;	    
	protected String calif;
	protected String claveActivacion;
	protected String comision;
	protected String cuentaDesembolso;
	protected String claveElector;
	protected String descNegativeBCScore;
	protected String encriptempass;
	protected String errormsg;	    
	protected String emailAcred;
	protected String fechaDesembolso;
	protected String folio;
	protected String formaDesembolso;
	protected String forwardUser;	    
	protected String kuboScore;
	protected String loanType;
	protected String nameTo;
	protected String monto;
	protected String observacion;
	protected String partner_number;
	protected String plazo;
	protected String promotorName;
	protected String prospectus_id;
	protected String prospectName;
	protected String registrationReason;
	protected String safiClientID;
	protected String strTo;
	protected String strCC;
	protected String strCCO;
	protected String strFecha;	    
	protected String tasa;
	protected String toPartnerName;
	protected String urlImg;
	protected String comportamiento_pago_URL;
	protected String is_automatic_aproved;
	protected String automatic_aproved_TOKEN;
	protected String userControlName;				
    protected String body_text;
    protected String subject;
    protected String password_activacion;
    protected String url_activacion;
    protected String tmp;        
    protected String titulo_estatus_credito;
    protected String estatus_original;
    protected String estatus_credito;
    protected String fecha_pospuesta;
    protected String motive_catalog_description;	
	protected String cliente_SAFI;
	protected String cuenta;
	protected String cuenta_CLABE;
	protected String banco;	 
    protected String proposito_del_credito;
    protected String descripcion;
    protected String fechaDep;
    protected String creation_DATE;
    protected String finish_DATE;
    protected String motivo_disposicion;

	protected NumberFormat     number_format;    
    protected SimpleDateFormat date_format;
    protected SimpleDateFormat date_format_hora;    
    protected SimpleDateFormat date_format_activacion;
    
    protected Integer clabe_account_id;	
	
	protected int company_id;
	protected int tipo_ultima_nota;	
	
    protected boolean promotor_id_ENABLED;
    protected boolean altaInversion;
    protected boolean bcFirma;   
    protected boolean bcSeguimiento;
    protected boolean confmail;
    protected boolean consultaBC;
    protected boolean forwad;
    protected boolean error;
    protected boolean isAvisoFinSolicitudInv;
    protected boolean isPartner01;
    protected boolean isRechazado;
    protected boolean isAutorizado;
    protected boolean isPreautorizado;	
    //protected boolean isPubliInversion;
    protected boolean publish;
    protected boolean pruebaCorreo;
    protected boolean temppass;  
	
    protected final String JSF_CONSULTA_BURO;
    
    protected static final int NOTA_RECHAZO   = 7;
    protected static final int NOTA_POSPUESTO = 10;
    protected static final int NOTA_DESISTIDO = 11;
    
    protected NotificacionDMO()
    {    	
    	tmp = "<tr><td> Tipo de Crédito: </td>"	
    		+ "<td><span style='font-weight: bold;'>###LOANTYPE###</span></td></tr>";
    	
    	JSF_CONSULTA_BURO = "https://www.kubofinanciero.com/Kubo/jsf/consultaBuro.jsf";
    	
        altaInversion = false;
	    bcSeguimiento = false;
	    confmail      = false;
	    forwad        = false;
		publish       = false;
		temppass      = false;
		pruebaCorreo  = false;
		isAvisoFinSolicitudInv = false;
		
		date_format            = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
		date_format_hora       = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy HH:mm:ss", new Locale("ES"));
		date_format_activacion = new SimpleDateFormat("ddMMyyyy", new Locale("ES"));
        mimeMultipart		   = new MimeMultipart();
        body                   = new MimeBodyPart();   
		
		number_format = NumberFormat.getNumberInstance(new Locale("es","mx"));
		
		service_notas          = Utilities.findBean("notesServiceImp");
		service_proyect_loan   = Utilities.findBean("proyectLoanServiceImp");
		service_estatus        = Utilities.findBean("estatusProyectLoanServiceIMP");
		service_motive         = Utilities.findBean("motiveServiceImp");
		service_change_control = Utilities.findBean("change_controlServiceImp");
		service_clabe_account  = Utilities.findBean("clabeAccountServiceImp");
		
		service_reason = new RegistrationReasonIMP();
    }
    
	public final ConexionIMO getConexion_SMTP()
	{
		return conexion_SMTP;
	}
    
	protected final String getCirculo_INFO(Membership emisor, Scoring score)
	{
		creditos_vigentes = Utilities.findBean("creditosVigentesNotificacionImpl");				
		creditos_vigentes.setCompany_id(emisor.getMembershipPK().getCompany_id());
		creditos_vigentes.setProspectus_id(emisor.getMembershipPK().getProspectus_id());	
		
		return creditos_vigentes.getINFO(score.getMx_solicitud_buro());
	}

	public String getBcScore() {
		return bcScore;
	}

	public final void setBcScore(String score) 
	{
		bcScore = score;
	}

	public String getBurSolNum() {
		return burSolNum;
	}

	public final void setBurSolNum(String buro_solicitud_numero) 
	{
		burSolNum = buro_solicitud_numero;
	}

	public String getCalif() {
		return calif;
	}

	public final void setCalif(String calif) 
	{
		this.calif = calif;
	}

	public String getClaveActivacion() {
		return claveActivacion;
	}

	public void setClaveActivacion(String claveActivacion) {
		this.claveActivacion = claveActivacion;
	}

	public String getComision() {
		return comision;
	}

	public final void setComision(String comision) 
	{
		this.comision = comision;
	}

	public String getCuentaDesembolso() {
		return cuentaDesembolso;
	}

	public void setCuentaDesembolso(String cuentaDesembolso) {
		this.cuentaDesembolso = cuentaDesembolso;
	}

	public String getClaveElector() {
		return claveElector;
	}

	public final void setClaveElector(String clave) 
	{
		claveElector = clave;
	}

	public String getDescNegativeBCScore() {
		return descNegativeBCScore;
	}

	public final void setDescNegativeBCScore(String Score) 
	{
		descNegativeBCScore = Score;
	}

	public String getEncriptempass() {
		return encriptempass;
	}

	public void setEncriptempass(String encriptempass) {
		this.encriptempass = encriptempass;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getEmailAcred() {
		return emailAcred;
	}

	public final void setEmailAcred(String email) 
	{
		emailAcred = email;
	}

	public String getFechaDesembolso() {
		return fechaDesembolso;
	}

	public void setFechaDesembolso(String fechaDesembolso) {
		this.fechaDesembolso = fechaDesembolso;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getFormaDesembolso() {
		return formaDesembolso;
	}

	public void setFormaDesembolso(String formaDesembolso) {
		this.formaDesembolso = formaDesembolso;
	}

	public String getForwardUser() {
		return forwardUser;
	}

	public void setForwardUser(String forwardUser) {
		this.forwardUser = forwardUser;
	}

	public String getKuboScore() {
		return kuboScore;
	}

	public final void setKuboScore(String score) 
	{
		kuboScore = score;
	}

	public String getLoanType() {
		return loanType;
	}

	public final void setLoanType(String type) 
	{
		loanType = type;
	}

	public String getNameTo() {
		return nameTo;
	}

	public void setNameTo(String nameTo) {
		this.nameTo = nameTo;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getPartner_number() {
		return partner_number;
	}

	public void setPartner_number(String partner_number) {
		this.partner_number = partner_number;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public String getPromotorName() {
		return promotorName;
	}

	public void setPromotorName(String promotorName) {
		this.promotorName = promotorName;
	}

	public String getProspectus_id() {
		return prospectus_id;
	}

	public final void setProspectus_id(String id) 
	{
		prospectus_id = id;
	}

	public String getProspectName() {
		return prospectName;
	}

	public final void setProspectName(String name) 
	{
		prospectName = name;
	}

	public String getRegistrationReason() {
		return registrationReason;
	}

	public final void setRegistrationReason(String reason) 
	{
		registrationReason = reason;
	}

	public String getSafiClientID() {
		return safiClientID;
	}

	public void setSafiClientID(String safiClientID) {
		this.safiClientID = safiClientID;
	}

	public String getStrTo() {
		return strTo;
	}

	public final void setStrTo(String strTo) 
	{
		this.strTo = strTo;
	}

	public String getStrCC() {
		return strCC;
	}

	public void setStrCC(String strCC) {
		this.strCC = strCC;
	}

	public String getStrCCO() {
		return strCCO;
	}

	public void setStrCCO(String strCCO) {
		this.strCCO = strCCO;
	}

	public String getStrFecha() {
		return strFecha;
	}

	public final void setStrFecha(String strFecha) 
	{
		this.strFecha = strFecha;
	}

	public String getTasa() {
		return tasa;
	}

	public final void setTasa(String tasa) 
	{
		this.tasa = tasa;
	}

	public String getToPartnerName() {
		return toPartnerName;
	}

	public void setToPartnerName(String toPartnerName) {
		this.toPartnerName = toPartnerName;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public String getUserControlName() {
		return userControlName;
	}

	public final void setUserControlName(String userControlName) 
	{
		this.userControlName = userControlName;
	}

	public Integer getProspectusID() {
		return prospectusID;
	}

	public final void setProspectusID(Integer prospectusID) 
	{
		this.prospectusID = prospectusID;
	}

    public boolean isBcSeguimiento()
    {
    	return bcSeguimiento;
    }
    
    public void setBcSeguimiento(boolean bandera)
    {
    	bcSeguimiento = bandera;
    }

	public boolean isAltaInversion() {
		return altaInversion;
	}

	public void setAltaInversion(boolean altaInversion) {
		this.altaInversion = altaInversion;
	}

	public boolean isConfmail() {
		return confmail;
	}

	public void setConfmail(boolean confmail) {
		this.confmail = confmail;
	}

	public boolean isForwad() {
		return forwad;
	}

	public void setForwad(boolean forwad) {
		this.forwad = forwad;
	}
	
	public boolean isTemppass() {
		return temppass;
	}

	public void setTemppass(boolean temppass) {
		this.temppass = temppass;
	}
	
	public boolean isBcFirma() {
		return bcFirma;
	}

	public final void setBcFirma(boolean bcFirma) 
	{
		this.bcFirma = bcFirma;
	}

	public boolean isConsultaBC() {
		return consultaBC;
	}

	public final void setConsultaBC(boolean bandera) 
	{
		consultaBC = bandera;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isPartner01() {
		return isPartner01;
	}

	public void setPartner01(boolean isPartner01) {
		this.isPartner01 = isPartner01;
	}

	public boolean isRechazado() {
		return isRechazado;
	}

	public void setRechazado(boolean isRechazado) {
		this.isRechazado = isRechazado;
	}

	public boolean isAutorizado() {
		return isAutorizado;
	}

	public void setAutorizado(boolean isAutorizado) {
		this.isAutorizado = isAutorizado;
	}

	public boolean isPreautorizado() {
		return isPreautorizado;
	}

	public void setPreautorizado(boolean isPreautorizado) {
		this.isPreautorizado = isPreautorizado;
	}

/*	
	public boolean isPubliInversion() {
		return isPubliInversion;
	}

	public void setPubliInversion(boolean isPubliInversion) {
		this.isPubliInversion = isPubliInversion;
	}
*/	

	public boolean isPruebaCorreo() {
		return pruebaCorreo;
	}

	public void setPruebaCorreo(boolean pruebaCorreo) {
		this.pruebaCorreo = pruebaCorreo;
	}

	public boolean isAvisoFinSolicitudInv() {
		return isAvisoFinSolicitudInv;
	}

	public void setAvisoFinSolicitudInv(boolean isAvisoFinSolicitudInv) {
		this.isAvisoFinSolicitudInv = isAvisoFinSolicitudInv;
	}
    
	protected String getPlazo(ProyectLoan pyln)
	{
		String  plazo = pyln.getTerm_id() + "";
		int      freq = pyln.getFrequency_id();
		
		switch (freq)
		{
			case 1://Semanal
				plazo += " Semanas";
			break;
			
			case 2: //Catorcenal
				plazo += " Catorcenas";
			break;
			
			case 3: //Quincenal
				plazo += " Quincenas";
			break;
				
			case 4: //Mensual
				plazo += " Meses";
			break;
		}
		
		return plazo;
		
	}
	
	protected String leer_notificacion_HTML(String file_path) 
	{		
		File archivo      = null;
	    FileReader fr     = null;
	    BufferedReader br = null;
	    StringBuilder sb  = new StringBuilder();
	      
	    String tmpCuerpoStr = null;
	    
		String deploymentDirectoryPath;
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
        deploymentDirectoryPath  = ctx.getRealPath("/") + file_path;
	 
	    try 
	    {
	    	System.out.println(deploymentDirectoryPath);
	    	  
	    	archivo = new File (deploymentDirectoryPath);
	    	fr      = new FileReader (archivo);
	    	br      = new BufferedReader(fr);
		    String linea;
		         
		    while((linea = br.readLine()) != null)
		    {		        	 
		    	sb.append(linea);		        	
		    }
		         
		    tmpCuerpoStr = sb.toString();
		    
	    } catch(Exception e) {
	    	
	    	e.printStackTrace();
	    	
	    } finally {	        
	    	try
	    	{
	    		if( null != fr )
	    		{
					 fr.close();					 
				}				 	
			 } catch (Exception e2) {
				 e2.printStackTrace();				 
			 }
	    }
	      
	    return tmpCuerpoStr;	      
	 }

	public String getProposito_del_credito() {
		return proposito_del_credito;
	}

	public void setProposito_del_credito(String proposito_del_credito) {
		this.proposito_del_credito = proposito_del_credito;
	}

	public List<String> getLista_archivos_adjuntos() {
		return lista_archivos_adjuntos;
	}

	public void setLista_archivos_adjuntos(List<String> lista_archivos_adjuntos) {
		this.lista_archivos_adjuntos = lista_archivos_adjuntos;
	}
}
