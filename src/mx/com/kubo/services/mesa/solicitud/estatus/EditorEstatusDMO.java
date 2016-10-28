
package mx.com.kubo.services.mesa.solicitud.estatus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.kubows.EditorEstatusRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMO;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.EstatusChangeLog;
import mx.com.kubo.model.Motive;
import mx.com.kubo.model.MotivePK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.StatusProyectCatPK;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.MotiveService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.StatusProyectCatService;
import mx.com.kubo.services.mesa.solicitud.ServiceEstatusChangeLogIMO;
import mx.com.kubo.tools.Utilities;

public abstract class EditorEstatusDMO 
implements EditorEstatusIMO
{	
	protected Change_controlService      service_change_control;
	protected ProyectLoanService         service_proyect_loan;
	protected MotiveService              service_motive;
	protected MembershipService          service_membership;
	protected NaturalPersonService       service_natural_person;
	protected ServiceEstatusChangeLogIMO service_estatus_change_log;
	protected StatusProyectCatService    service_estatus;
	
	protected PublicProyectServiceLocator loc;
	protected PublicProyect publicproyect;
	protected EditorEstatusRequest request;
	
	protected CasosPospuestosIMO  caso_pospuesto;
	
	protected ProyectLoan         actualProyect;
	protected EstatusProyectLoan  estatus_ORIGINAL;
	protected EstatusProyectLoan  estatus_NEW;
	protected EstatusChangeLog    estatus_change_log;
	protected StatusProyectCat    estatus_name_NEW;
	protected StatusProyectCat    estatus_name_ORIGINAL;
	protected StatusProyectCatPK  estatus_PK;
	
	protected SystemParamPK       system_param_PK;
	protected MotivePK            motive_PK;
	protected Motive              motive;
	protected SearchSummaySession session_sumary;	
	protected Change_control      change_control_LAST;
	protected ChangeBean          change_control;		
	
	protected Calendar today, fecha_leida;	
	
	protected Date fecha_pospuesta;
	protected Date fecha_pre_autorizacion;
		
	protected SimpleDateFormat formatter_date;
	
	protected List<Change_control> lista_change_control, bitacora_change_control;

	protected String cadenaProyecto;
	protected String system_param;
	protected String motive_catalog_description;
	protected String motivo_del_cambio;
	protected String descripcion_del_cambio;
	
	protected String [] afected_table, afected_field;
	
	protected Integer motivo_id;
	
	protected int proyect_loan_id;
	protected int proyect_id;
	protected int prospectus_id;
	protected int company_id;
	protected int emisor_prospectus_id;
	protected int acreditado_prospectus_id;	
	
	protected final int COMPANY = 1;
	
	protected boolean has_change;
	protected boolean change_control_OK;
	protected boolean cambiar_estatus_OK;
	
	protected EditorEstatusDMO()
	{
		service_change_control     = Utilities.findBean("change_controlServiceImp");
		service_proyect_loan       = Utilities.findBean("proyectLoanServiceImp");
		service_motive             = Utilities.findBean("motiveServiceImp");				
		service_membership         = Utilities.findBean("membershipServiceImp");
		service_natural_person     = Utilities.findBean("naturalPersonServiceImp");
		service_estatus_change_log = Utilities.findBean("service_estatus_change_log");
		service_estatus            = Utilities.findBean("statusProyectCatServiceImp");		
	}
	
	public final void setEmisor_prospectus_id(int emisor_prospectus_id)
	{
		this.emisor_prospectus_id = emisor_prospectus_id;
	}
	
	public final void setCaso_pospuesto(CasosPospuestosIMO caso_pospuesto)
	{
		this.caso_pospuesto = caso_pospuesto;
	}
	
	public final void setEstatus_ORIGINAL(EstatusProyectLoan estatus_ORIGINAL)
	{
		this.estatus_ORIGINAL = estatus_ORIGINAL;
	}
	
	public final void setEstatus_NEW(EstatusProyectLoan estatus_NEW)
	{
		this.estatus_NEW = estatus_NEW;
	}
	
	public final void setMotivo_id_selected(String motivo_id_selected)
	{
		motivo_id = new Integer(motivo_id_selected);
	}
	
	public final void setDescripcion_motivo(String descripcion_motivo)
	{
		descripcion_del_cambio = descripcion_motivo;
	}
}
