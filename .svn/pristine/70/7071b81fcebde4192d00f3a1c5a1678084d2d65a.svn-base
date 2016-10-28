package mx.com.kubo.managedbeans.mesa.solicitud;

import static mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan.BORRADOR;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Motive;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.Referred;
import mx.com.kubo.model.ReferredPK;
import mx.com.kubo.model.Tutor;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.model.mesa.solicitud.notas.TipoDeNota;
import mx.com.kubo.services.mesa.solicitud.estatus.EditorEstatusIMP;
import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

public abstract class SummaryRequestPMO extends SummaryRequestAMO
implements SummaryRequestIMO
{			
	public final void listener_estatus_SELECTED(AjaxBehaviorEvent evento)
	{		
		request = RequestContext.getCurrentInstance();
		
		ajax_inputText      = (HtmlInputText) evento.getComponent();		
		ajax_selected_value = (String) ajax_inputText.getValue();
		
		motive_selected = null;
		
		estatus_SELECTED = ajax_selected_value;
		
		if(estatus_SELECTED != null && !estatus_SELECTED.equals(""))
		{			
			//System.out.printf("\nSummaryRequestPMO.listener_estatus_SELECTED(): " + estatus_SELECTED);
			
			lista_motivos_cambio_estatus =  motiveservice.getMotiveStatusListByStatus(Integer.parseInt(estatus_SELECTED));
			
			motive_TOKEN = "";
			
			for(Motive motivo_cambio_estatus : lista_motivos_cambio_estatus)
			{
				motive_id          = motivo_cambio_estatus.getPk().getMotive_id();
				motive_descripcion = motivo_cambio_estatus.getDescription();
				
				motive_TOKEN += "::" + motive_id + ";" + motive_descripcion;
				
				//System.out.printf("\n" + motive_TOKEN);
			}	
			
			estatus_NEW = EstatusProyectLoan.getInstance(new Integer(estatus_SELECTED));	
			
			casosPospuestos.setEstatus_SELECTED(estatus_NEW);
			
			request.addCallbackParam("lista_motivos_de_cambio", motive_TOKEN);											
			
		} else {							
			
			request.addCallbackParam("lista_motivos_de_cambio", "undefined" );			
		}	
	}
	
	public final void saveNewStatusProyect()
	{						
		estatus_ORIGINAL = EstatusProyectLoan.getInstance(actualProyect.getStatusProyect().getStatusPK().getStatus_id());
		estatus_NEW      = EstatusProyectLoan.getInstance(new Integer(statusChangeID));		
		
		descripcion_del_cambio = changeStatusProyect.getWhyChangeData();
		
		if(motive_selected != null)
		{
			motive_id_selected = Integer.toString(motive_selected);
			
		} else {
			
			motive_id_selected = "-1";
		}
				
		if(estatus_ORIGINAL == BORRADOR)
		{
			editor_notas.setMotive_selected(motive_selected);
			editor_notas.setObservaciones(descripcion_del_cambio);
			
			switch(estatus_NEW)
			{
				case CANCELADO:						
					editor_notas.crear_nueva_nota(TipoDeNota.RECHAZO);					
				break;
				
				case DESISTIDO:	
					editor_notas.crear_nueva_nota(TipoDeNota.DESISTIDO);
				break;
				
				case POSPUESTO:
					editor_notas.crear_nueva_nota(TipoDeNota.POSPUESTO);
				break;
				
				case PRE_AUTORIZADO:
					editor_notas.crear_nueva_nota(TipoDeNota.PREAPROBADO);
				break;
				
				default: break;
			}			
		}						
		 
		editor_estatus = new EditorEstatusIMP();
		editor_estatus.setEmisor_prospectus_id(sesion.getProspectus_id());
		editor_estatus.setCaso_pospuesto(casosPospuestos);
		editor_estatus.setEstatus_ORIGINAL(estatus_ORIGINAL);
		editor_estatus.setEstatus_NEW(estatus_NEW);
		editor_estatus.setMotivo_id_selected(motive_id_selected);
		editor_estatus.setDescripcion_motivo(descripcion_del_cambio);
		editor_estatus.init_proyect_loan(actualProyect);
		
		cambio_de_estatus_OK = editor_estatus.guardar_cambio_de_estatus();
		
		if(cambio_de_estatus_OK)
		{
			if(cadenaProyecto != null)
			{
				proyect_loan_id = Integer.parseInt(cadenaProyecto.split("::")[0]);
				proyect_id      = Integer.parseInt(cadenaProyecto.split("::")[1]);
				prospectus_id   = Integer.parseInt(cadenaProyecto.split("::")[2]);
				company_id      = Integer.parseInt(cadenaProyecto.split("::")[3]);
								
				proyect_loan_PK = new ProyectLoanPK(proyect_loan_id, proyect_id ,prospectus_id, company_id);
				
				actualProyect = service_proyect_loan.findProyect(proyect_loan_PK);
			}
			
			setFechaPospuestaValida(true);						
			
			actualProyect = service_proyect_loan.getProyectLoanById(actualProyect.getProyectloanPk());
			
			lista_cambio_estatus = service_estatus.getListaEstatus_by_EstatusConfig(actualProyect.getStatus_id());
			
			asignar_change_control();
		}
	}		

	protected final boolean saveChangeData(String table, String field, String origValue, String newValue, String comment)
	{
		Change_controlPK changeCtrlPK = new Change_controlPK();
		
		changeCtrlPK.setProspectus_id(persona.getNatPerPK().getProspectus_id());
		changeCtrlPK.setCompany_id(persona.getNatPerPK().getCompany_id());
		
		Change_control changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
		changeCtrl.setAfected_table(table);
		changeCtrl.setIp(getIpAddressClient());			
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);
		changeCtrl.setField(field);
		changeCtrl.setComments(comment);
		
		int prospectus_id = persona.getNatPerPK().getProspectus_id();
		int company_id    = persona.getNatPerPK().getCompany_id();
		
		if(service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id))
		{
			return true;	
		} else {
			return false;
		}
	}
	
	public List<ClientViewFullName> completeinfoclient(String strSearch)
	{
		List<ClientViewFullName> suggestions = new ArrayList<ClientViewFullName>();
		//Dependiendo del valor del checkBox sera la busqueda.
		//1:Por nombre
		
			suggestions = clientViewFullNameService.getListAllUserByName(strSearch);
		
		
		return suggestions;		
	}	
	
	public LinkedList<ClientViewFullName> completeinfoclientRefered(String strSearch)
	{
		LinkedList<ClientViewFullName> suggestions = new LinkedList<ClientViewFullName>();
		//Dependiendo del valor del checkBox sera la busqueda.
		//1:Por nombre
		
			suggestions = clientViewFullNameService.getListAllUserForReferred(strSearch);
		
		
		return suggestions;		
	}
	
	public void onItemSelect(SelectEvent event) {
      //System.out.println("Seleccionar elemento: " +  event.getObject().toString());
      setTutor_search(event.getObject().toString());
    }
	
	public void onItemReferredSelect(SelectEvent event) {
	      //System.out.println("Seleccionar Persona que lo recomendo: " +  event.getObject().toString());
	      setRefered_search( event.getObject().toString() );
	    }
	
	public void search_Tutor(ActionEvent e)
	{		
		//System.out.println("Prospecto seleccionado: "+getTutor_search()); 
		
		gnNaturalPersonPK tPK = new gnNaturalPersonPK();
		tPK.setProspectus_id(Integer.parseInt(getTutor_search()));
		tPK.setCompany_id(sesion.getCompany_id());	
		
		tutor = service_natural_person.getNaturalPersonById(tPK);
		
	}
	
	public void asignarTutor(){
		//System.out.println("Asignando tutor .. ");

		Tutor newTutor = new Tutor();
		
		newTutor.setCompany_id(company_id);
		newTutor.setProspectus_id(prospectus_id);
		newTutor.setProspectus_id_tutor(tutor.getNatPerPK().getProspectus_id());
		
		if(tutorservice.addTutor(newTutor))
		{
			init_lista_thisTutor();
		}
		
	}
	
	public void asignarRecomendado(){
		//System.out.println("Asignando quien lo recomendó Recomendacion .. ");
		
		String valorNuevo 		= "";
		String valorAnterior 	= "";
		
		if (getRefered_search() != null) {
		
			if( hasReferred ){
				valorAnterior = referredIni.getMembership().getPerson().NombreCompletoNPM();
				referredservice.removeReferred(referredIni);
				
			}else{
				
				valorAnterior = member.getWho_recommends() != null ? member.getWho_recommends() : "" ;
				
			}
			
			
			ReferredPK refPK = new ReferredPK();
			
			RequestContext rc = RequestContext.getCurrentInstance();
			
			refPK.setProspectus_id_destiny( persona.getProspectus().getProspectusPK().getProspectus_id());
			
			refPK.setProspectus_id_origin (Integer.parseInt( getRefered_search() ) );
			
			refPK.setCompany_id( persona.getProspectus().getProspectusPK().getCompany_id() );
			
			Referred newReferred = new Referred();
			
			newReferred.setPk( refPK );
			newReferred.setDate_referred( new Date() );
			
			if( referredservice.addReferred( newReferred ) ){
				
				rc.addCallbackParam( "accion","1" );//Agregar
				verificaRecomendado();
				validaRegistrationReason();
				
				valorNuevo = referredIni.getMembership().getPerson().NombreCompletoNPM();
				
				member.setWho_recommends(valorNuevo);
				
				service_membership.update(member);
				
				saveChangeData("gn_referred", "prospectus_id_origin", valorAnterior, valorNuevo, "Mesa de control asignó quien recomendó a esta persona");
				
			}
			
		}
		
	}
	
	public void verificaRecomendado(  ){
		
		referredIni = null;
		hasReferred = false;
		
		if(persona != null && persona.getProspectus() != null && persona.getProspectus().getProspectusPK() != null ){
			
			referredIni =   referredservice.getQuienLoRecomendo(persona.getProspectus().getProspectusPK().getProspectus_id(),persona.getProspectus().getProspectusPK().getCompany_id());
			
			if ( referredIni != null ){
				hasReferred = true;
			}
			
		}else{
			
			hasReferred = false;
			
		}
		
		changeReferred = new ChangeBean();
		
		changeReferred.setNameTable("gn_referred");
		changeReferred.setNameField("prospectus_id_origin");
		
		List<Change_control> listChangeReferred = null;
		
		if(actualProyect != null)
		{
			int prospectus_id = actualProyect.getProyectloanPk().getProspectus_id();
			int company_id    = actualProyect.getProyectloanPk().getCompany_id();
			listChangeReferred = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, new String[]{"gn_referred"}, new String[]{"prospectus_id_origin"});
		
		} else {
			int prospectus_id = persona.getNatPerPK().getProspectus_id();
			int company_id    = persona.getNatPerPK().getCompany_id();
			listChangeReferred = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, new String[]{"gn_referred"}, new String[]{"prospectus_id_origin"});
		}
		
		changeReferred.setHasChange(listChangeReferred!=null && listChangeReferred.size()>0?true:false);
		changeReferred.setLstChanges(listChangeReferred!=null && listChangeReferred.size()>0?listChangeReferred:null);	
		
	}
	
	protected void init_lista_thisTutor()
	{
		lstTutor =  tutorservice.getTutorByProspectus(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id());
		
		if(lstTutor != null && lstTutor.size() > 0)
		{
			thisTutor = lstTutor.get(0);
			
			natural_person_PK = new gnNaturalPersonPK();
			natural_person_PK.setProspectus_id(thisTutor.getProspectus_id_tutor());
			natural_person_PK.setCompany_id(thisTutor.getCompany_id());	
			
			personTutor = service_natural_person.getNaturalPersonById(natural_person_PK);
			
		} else {
			
			thisTutor = null;
			personTutor = null;
		}		
	}
	
	protected void init_lista_imTutor()
	{		
		lstTutor =  tutorservice.getProspectusFromTutor(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id());
		
		imTutor = new ArrayList<NaturalPerson>();
		
		if(lstTutor != null && lstTutor.size() > 0)
		{						
			for(Tutor tutor : lstTutor )
			{				
				natural_person_PK = new gnNaturalPersonPK();
				natural_person_PK.setProspectus_id (tutor.getProspectus_id());
				natural_person_PK.setCompany_id    (tutor.getCompany_id());	
				
				natural_person = service_natural_person.getNaturalPersonById(natural_person_PK);
				
				imTutor.add(natural_person);
			}
			
		} else {
			
			imTutor = null;			
		}
	}
}
