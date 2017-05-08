package mx.com.kubo.mesa.solicitud.permisos;

import java.util.List;

import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.tools.Utilities;

public abstract class RoleFunctionDMO implements RoleFunctionIMO
{
	protected AccessService accessService;
	
	protected RoleFunctionController role_function;
	
	protected SessionBean sesion;
	
	protected ProyectLoan proyect_loan;
	protected NaturalPerson person;
	protected Scoring score;
	
	protected String area;
	protected String safi_client_id;
	
	protected Integer status_id;
	protected Integer role_id;
	protected Integer company_id;
	
	protected List<RoleFunction> lista_funciones;
	protected List<AccessCollector> menuAccess;
		
	protected int function_id;
	protected int screen_id;
	
	protected boolean addDocument;	
	protected boolean additionalCredit;
	protected boolean asignaCartera;
	protected boolean autorizar_contratos_ENABLED;
	protected boolean autorizar_personas_relacionadas_ENABLED;
	protected boolean cancel_prospectus;
	protected boolean copiar_documentos_ENABLED;
	protected boolean displayLogCob;	
	protected boolean displayAddTutor;
	protected boolean displayAlerts;
	protected boolean displayNotes;	
	protected boolean displayTransUnion;	
	protected boolean displayReferredChange;	
	protected boolean edit_Form_help_Coach;
	protected boolean editDocument;
	protected boolean editor_domicilio_ENABLED;
	protected boolean editor_nombre_ENABLED;
	protected boolean editor_tipo_credito_ENABLED;
	protected boolean makeNewConsultation;
	protected boolean ren4c;
	protected boolean renovacionAct;
	protected boolean changeTelephone;
	protected boolean changeStatus;
	protected boolean changeDispersion;
	protected boolean changeActions;
	protected boolean changeInstitutionalInvestor;
	protected boolean changeCreditConditionsFunction;
	protected boolean ver_pestana_tablero_normativo_ENABLED;
	protected boolean ver_notas_comportamiento_inusual_ENABLED;
	protected boolean modificar_tasa_acreditado;
	protected boolean modificar_tasa_inversionista;
	protected boolean modificar_comision_apertura;
	protected boolean modificar_destino_credito;
	protected boolean modificar_calificacion;
	protected boolean reprocesar_buro_credito;
	
	protected final int EN_COMITE = 1;
	protected final int SCREEN_CONTROLTABLE_LOGS = 16;
	
	protected final int  MODIFICAR_CONDICIONES_CREDITO = 2;
	protected final int          EDICION_CURP_TEL_DOCS = 4;
	protected final int               RENOVAR_CREDITOS = 5;
	protected final int               ASIGNAR_CARTERA  = 6;
	protected final int        CREAR_CREDITO_ADICIONAL = 7;
	protected final int              EDITAR_DOCUMENTOS = 8;
	protected final int            ADJUNTAR_DOCUMENTOS = 9;
	protected final int                CAMBIAR_ESTATUS = 10;
	protected final int CAMBIAR_CONDICIONES_DISPERSION = 11;
	protected final int             MODIFICAR_TELEFONO = 12;
	protected final int                CONSULTA_MANUAL = 13;
	protected final int   VER_CALIFICACION_TRANSUINION = 14;
	protected final int  ACTIVAR_SOLICITUDES_INVERSION = 15;
	protected final int        AGREGAR_NOTAS = 16;
	protected final int      CANCELAR_CUENTA = 17;
	protected final int      AGREGAR_ALARMAS = 18;
	protected final int        ASIGNAR_TUTOR = 20;
	protected final int     EDICION_VIVIENDA = 21;
	protected final int ASIGNAR_RECOMENDADOS = 22;
	protected final int    EDITAR_FORMULARIO = 25;
	protected final int       EDICION_NOMBRE = 26;
	protected final int EDICION_TIPO_CREDITO = 27;	
	protected final int  AUTORIZAR_CONTRATOS = 29;	
	protected final int    COPIAR_DOCUMENTOS = 30;
	protected final int VER_PESTANA_TABLERO_NORMATIVO    = 31;
	protected final int AUTORIZAR_PERSONAS_RELACIONADAS  = 32;	
	protected final int VER_NOTAS_COMPORTAMIENTO_INUSUAL = 33;
	protected final int REN_4_C = 34;
	protected final int       MODIFICAR_TASA_ACREDITADO = 36;
	protected final int    MODIFICAR_TASA_INVERSIONISTA = 37;
	protected final int MODIFICAR_COMISION_POR_APERTURA = 38;
	protected final int       MODIFICAR_DESTINO_CREDITO = 39;
	protected final int         REPROCESAR_BURO_CREDITO = 40;
	protected final int          MODIFICAR_CALIFICACION = 41;
	
	protected RoleFunctionDMO()
	{
		accessService = Utilities.findBean("accessServiceImp");
		
		changeInstitutionalInvestor = true;
	}
	
	public void setSesion(SessionBean sesion)
	{
		this.sesion = sesion;
		
		if(sesion != null)
		{
			   role_id = sesion.getRole_id();
			company_id = sesion.getCompany_id();
		}
	}
	
	public void setProyectLoan(ProyectLoan proyect_loan)
	{
		this.proyect_loan = proyect_loan;
		
		if(proyect_loan != null)
		{	
			status_id = proyect_loan.getStatus_id();
			   person = proyect_loan.getPerson();						
			
			          area = person.getProspectus().getArea().toString();
			safi_client_id = person.getSafi_client_id();						          
		}
	}
	
	public void setScore(Scoring score)
	{
		this.score = score;
	}
	
	public void setEditor_nombre_ENABLED(boolean flag) 
	{
		editor_nombre_ENABLED = flag;
	}

	public void setRole_function(RoleFunctionController role_function) 
	{
		this.role_function = role_function;
	}
	
	public boolean isEditor_domicilio_ENABLED() 
	{
		return editor_domicilio_ENABLED;
	}
	
	public boolean isEditor_nombre_ENABLED() 
	{
		return editor_nombre_ENABLED;
	}
	
	public boolean isEditor_tipo_credito_ENABLED() 
	{
		return editor_tipo_credito_ENABLED;
	}
	
	public boolean isAutorizar_contratos_ENABLED() 
	{
		return autorizar_contratos_ENABLED;
	}
	
	public boolean isCopiar_documentos_ENABLED() 
	{
		return copiar_documentos_ENABLED;
	}
	
	public boolean isChangeInstitutionalInvestor() 
	{
		return changeInstitutionalInvestor;
	}
	
	public boolean isDisplayNotes()
	{
		return displayNotes;
	}
	
	public boolean isDisplayAddTutor() 
	{
		return displayAddTutor;
	}
	
	public boolean isVer_notas_comportamiento_inusual_ENABLED()
	{
		return ver_notas_comportamiento_inusual_ENABLED;
	}
	
	public boolean isAsignaCartera() 
	{
		return asignaCartera;
	}
	
	public boolean isRenovacionAct() {
		return renovacionAct;
	}

	public boolean isEditDocument() {
		return editDocument;
	}

	public void setEditDocument(boolean editDocument) {
		this.editDocument = editDocument;
	}

	public boolean isAddDocument() 
	{
		return addDocument;
	}

	public boolean isAdditionalCredit() {
		return additionalCredit;
	}

	public boolean isChangeStatus() {
		return changeStatus;
	}

	public boolean isChangeDispersion() {
		return changeDispersion;
	}

	public boolean isChangeTelephone() {
		return changeTelephone;
	}

	public boolean isMakeNewConsultation() {
		return makeNewConsultation;
	}
	
	public boolean isDisplayTransUnion() {
		return displayTransUnion;
	}
	
	public boolean isChangeCreditConditionsFunction() {
		return changeCreditConditionsFunction;
	}

	public boolean isChangeActions() 
	{
		return changeActions;
	}

	public boolean isCancel_prospectus() 
	{
		return cancel_prospectus;
	}
	
	public boolean isDisplayAlerts() 
	{
		return displayAlerts;
	}
	
	public boolean isDisplayReferredChange() 
	{
		return displayReferredChange;
	}
	
	public boolean isDisplayLogCob()
	{
		return displayLogCob;
	}
	
	public boolean getEdit_Form_help_Coach() 
	{
		return edit_Form_help_Coach;
	}
	
	public boolean isRen4c() 
	{
		return ren4c;
	}
	
	public boolean isVer_pestana_tablero_normativo_ENABLED() 
	{
		return ver_pestana_tablero_normativo_ENABLED;
	}
	
	public boolean isAutorizar_personas_relacionadas_ENABLED() 
	{
		return autorizar_personas_relacionadas_ENABLED;
	}
	
	public boolean isModificar_tasa_acreditado() 
	{
		return modificar_tasa_acreditado;
	}
	
	public boolean isModificar_tasa_inversionista() 
	{
		return modificar_tasa_inversionista;
	}
	
	public boolean isModificar_comision_apertura() 
	{
		return modificar_comision_apertura;
	}
	
	public boolean isModificar_destino_credito() 
	{
		return modificar_destino_credito;
	}
	
	public boolean isReprocesar_buro_credito()
	{
		return reprocesar_buro_credito;
	}
	
	public boolean isModificar_calificacion()
	{
		return modificar_calificacion;
	}
}
