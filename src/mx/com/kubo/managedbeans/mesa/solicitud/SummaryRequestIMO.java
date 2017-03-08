package mx.com.kubo.managedbeans.mesa.solicitud;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.bean.IncomeDetailsBean;
import mx.com.kubo.bean.PersonAndPhones;
import mx.com.kubo.bean.PhoneReview;
import mx.com.kubo.mesa.solicitud.resumen.purpose.EditorPurposeIMO;
import mx.com.kubo.mesa.solicitud.resumen.loantype.EditorTipoCreditoIMO;
import mx.com.kubo.mesa.solicitud.perfil.curp.EditorCurpIMO;
import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMO;
import mx.com.kubo.managedbeans.mesa.solicitud.notas.NotasDelCasoIMO;
import mx.com.kubo.mesa.solicitud.ReporteInusualIMO;
import mx.com.kubo.mesa.solicitud.documentacion.DocumentacionIMO;
import mx.com.kubo.mesa.solicitud.perfil.ActividadEconomicaIMO;
import mx.com.kubo.mesa.solicitud.perfil.EditorIdentificationIMO;
import mx.com.kubo.mesa.solicitud.promo.PromocionIMO;
import mx.com.kubo.mesa.solicitud.telefonos.TelefonosIMO;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.catalogos.IdentificationType;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.DomicilioIMO;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.EditorViviendaIMO;
import mx.com.kubo.mesa.solicitud.perfil.nombre.EditorNombreIMO;
import mx.com.kubo.mesa.solicitud.perfil.rfc.EditorRfcIMO;
import mx.com.kubo.services.IncomeTypeService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.MotiveService;
import mx.com.kubo.services.RegistrationReasonService;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.SelectEvent;

interface SummaryRequestIMO 
{
	void init();
	void mapeoDatos(String search);
	
	void cargaConsultas();
	
	
	void loadImgProyect() throws FileNotFoundException;
	void loadProyectList();
	
	void setQuestionsinForum();
	void updatePublicForum(ValueChangeEvent e);
	void goToLogs(ActionEvent e);
	void setNullAmmuntMinus();
	void updateAmmountMinus();
	void changeExpenses(ExpensesBean ex);
	void editLogoProyect(ActionEvent e);
	
	void calculaPorcCliente(String user);
	void cleanPorcCliente();
	

	void saveNewStatusProyect();
	
	void setListChanges(ChangeBean change);
	
	void saveNewBanckDesk();
	void saveNewClabe();
	
	
	void validateBank();
	void aceptaCondiciones();
	void modificaCondiciones();
	void setIniciales();
	void realizaSimulacion();
	void handleSelect        (SelectEvent     event);
	void changeSignatureDate (DateSelectEvent event);
	void changeMeansOfPayment();
	
	void saveChangeIncomDetails(IncomeDetailsBean incomeDetailBean);	
	
	void showOtherIncome();
	void validaRegistrationReason();
	void loadRestructureBean();
	void changeRegistationReason();
	void savePromotorData();
	void saveNewSingDate();
	void generateDate();
	void newConsultation();
		
	Hashtable<String, Integer> getDimImg(File fOrigen);
	
	List<String> autocomplete(String query);
	List <IdentificationType> getLista_identification_type();
	
	ChangeBean      getChangeStatusProyect();
	ClabeAccount    getNewClaveAccount();
	PersonAndPhones existPerson(PhoneReview phRev,int company_id,int prospectus_id);
	
	CasosPospuestosIMO    getCasosPospuestos();
	EditorRfcIMO          getEditor_RFC();
	EditorCurpIMO         getEditor_CURP();
	EditorNombreIMO       getEditor_nombre();
	EditorViviendaIMO     getEditor_domicilio();
	EditorViviendaIMO     getDomicilio_fiscal();
	     DomicilioIMO     getDomicilio_actividad();
	EditorTipoCreditoIMO  getEditor_tipo_credito();
	EditorPurposeIMO      getEditor_purpose();
	NotasDelCasoIMO       getNotas();
	ActividadEconomicaIMO getActividad_economica();
	DocumentacionIMO      getDocumentacion();
	ReporteInusualIMO     getInusual();
	TelefonosIMO          getTelefono();
	PromocionIMO          getCheck();
	EditorIdentificationIMO getEditor_identification();
	
	RegistrationReasonService getReasonsService();
	MembershipService         getService_membership();		
	
	String actionNavLogs();
	
	String getEstatus_SELECTED();		
	
	boolean isDirectory(String other);
	boolean getReport(Integer prospectus_id);
	boolean isValidBank(String name);	
	boolean callSGB(ProyectLoan pl);
	boolean isEditor_domicilio_ENABLED();
	boolean isDomicilio_fiscal_ENABLED();
	boolean isEditor_nombre_ENABLED();	
	boolean isEditor_tipo_credito_ENABLED();
	boolean isReporte_inusual_ENABLED();
	
	void setMotiveservice         (MotiveService     service);
	void setService_income_type   (IncomeTypeService service);

	void notificar(Evento evento, String errormsg);
	
	void listener_estatus_SELECTED(AjaxBehaviorEvent evento);
	
	void setEstatus_SELECTED(String selected);
	
	void cargaInfoCompleta();
}
