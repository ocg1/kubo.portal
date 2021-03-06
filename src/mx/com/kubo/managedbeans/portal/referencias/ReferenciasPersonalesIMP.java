package mx.com.kubo.managedbeans.portal.referencias;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.References;
import mx.com.kubo.model.ReferencesPK;

@ManagedBean (name = "referencias_personales") @ViewScoped
public final class ReferenciasPersonalesIMP extends ReferenciasPersonalesAMO
implements Serializable
{	
	private static final long serialVersionUID = 8556864085060080848L;
	
	@PostConstruct
	public final void init()
	{
		faces = FacesContext.getCurrentInstance();
		
		init_sesion();
		
		if(access_from == null){
			
			ELContext context   = faces.getELContext();
			ELResolver resolver  = faces.getApplication().getELResolver();
			
			sesion          	= (SessionBean)         resolver.getValue(context, null, "sessionBean");
			SearchSummaySession session_sumary  = (SearchSummaySession) resolver.getValue(context, null, "searchSummaySession");
			
			String cadenaProyecto = session_sumary.getSearchSummary();
		
			if( cadenaProyecto!= null && cadenaProyecto.trim().length() > 2 && (cadenaProyecto.split("::")).length > 2 ){
			
				prospectus_id   = Integer.parseInt(cadenaProyecto.split("::")[2]);
				company_id      = Integer.parseInt(cadenaProyecto.split("::")[3]);
			
			}else{
				sesion = null;
			}
			  
			
		}
		
		if(sesion != null)
		{
			init_referencias();
		}
		
		listReferenceMotive = service_references.loadReferencesMotiveLstEnabled();
		listReferenceScore = service_references.loadReferencesScoreLstEnabled();
		
		referenceEdit=new References();
	
		referenceRemove=new References();
	
		referenceAdd=new References();
	}
	
	public void cargaReferencia( AjaxBehaviorEvent event ){
		
		referenceEdit = null;
		
		String referenceValue = event.getComponent().getAttributes().get("referencia").toString();
		System.out.println( "referenceValue: " + referenceValue );
		
		ReferencesPK refpk = new ReferencesPK() ;
		
		refpk.setCompany_id( Integer.parseInt( referenceValue.split("::")[2] ));
		refpk.setProspectus_id(Integer.parseInt( referenceValue.split("::")[1] ));
		refpk.setReference_id(Integer.parseInt( referenceValue.split("::")[0] ));
		
		referenceEdit = service_references.loadSelectedReferece(refpk);
		
		String name = "";
		
		if (referenceEdit.getFirst_name() != null && referenceEdit.getFirst_name().trim().length() > 0 ){
			
			if( name.trim().length()>0 ){
				name += " ";
			}
			
			name += referenceEdit.getFirst_name().trim();
			
		}
		
		if (referenceEdit.getMiddle_name() != null && referenceEdit.getMiddle_name().trim().length() > 0 ){
			
			if( name.trim().length()>0 ){
				name += " ";
			}
			
			name += referenceEdit.getMiddle_name().trim();
			
			referenceEdit.setMiddle_name(null);
			
		}
		
		if (referenceEdit.getFather_last_name() != null && referenceEdit.getFather_last_name().trim().length() > 0 ){
			
			if( name.trim().length()>0 ){
				name += " ";
			}
			
			name += referenceEdit.getFather_last_name().trim();
			referenceEdit.setFather_last_name(null);
		}

		if (referenceEdit.getMother_last_name() != null && referenceEdit.getMother_last_name().trim().length() > 0 ){
			
			if( name.trim().length()>0 ){
				name += " ";
			}
			
			name += referenceEdit.getMother_last_name().trim();
			referenceEdit.setMother_last_name(null);
		}
		
		referenceEdit.setFirst_name(name);
		
	}
	
	public void cargaReferenciaRem(AjaxBehaviorEvent event ){
		
		referenceRemove = null;
		
		String referenceValue = event.getComponent().getAttributes().get("referencia").toString();
		System.out.println( "referenceValue: " + referenceValue );
		
		ReferencesPK refpk = new ReferencesPK() ;
		
		refpk.setCompany_id( Integer.parseInt( referenceValue.split("::")[2] ));
		refpk.setProspectus_id(Integer.parseInt( referenceValue.split("::")[1] ));
		refpk.setReference_id(Integer.parseInt( referenceValue.split("::")[0] ));
		
		referenceRemove = service_references.loadSelectedReferece(refpk);
		
		String name = "";
		
		if (referenceRemove.getFirst_name() != null && referenceRemove.getFirst_name().trim().length() > 0 ){
			
			if( name.trim().length()>0 ){
				name += " ";
			}
			
			name += referenceRemove.getFirst_name().trim();
			
		}
		
		if (referenceRemove.getMiddle_name() != null && referenceRemove.getMiddle_name().trim().length() > 0 ){
			
			if( name.trim().length()>0 ){
				name += " ";
			}
			
			name += referenceRemove.getMiddle_name().trim();
			
			referenceRemove.setMiddle_name(null);
			
		}
		
		if (referenceRemove.getFather_last_name() != null && referenceRemove.getFather_last_name().trim().length() > 0 ){
			
			if( name.trim().length()>0 ){
				name += " ";
			}
			
			name += referenceRemove.getFather_last_name().trim();
			referenceRemove.setFather_last_name(null);
		}

		if (referenceRemove.getMother_last_name() != null && referenceRemove.getMother_last_name().trim().length() > 0 ){
			
			if( name.trim().length()>0 ){
				name += " ";
			}
			
			name += referenceRemove.getMother_last_name().trim();
			referenceRemove.setMother_last_name(null);
		}
		
		referenceRemove.setFirst_name(name);
		
	}
	
	public void updateReference(){
		
		service_references.updateReference(referenceEdit);
		init_referencias();
		referenceEdit=new References();
	}
	
	public void removeReference(){
		
		service_references.removeReference(referenceRemove);
		init_referencias();
		referenceRemove=new References();
	}
	
	public void saveReference(){
		
		
		int reference_id = getMaxReferenceId();
		
		ReferencesPK refpk = new ReferencesPK();
		
		refpk.setCompany_id(company_id);
		refpk.setProspectus_id(prospectus_id);
		refpk.setReference_id(reference_id);
		
		referenceAdd.setReferencePK(refpk);
		
		service_references.saveReference(referenceAdd);
		init_referencias();
		referenceAdd=new References();
	}
	
	private int getMaxReferenceId(){
		
		int max = 0;
		
		listReference = service_references.loadReferencesListByProspect(prospectus_id, company_id);
		
		if( listReference !=  null && listReference.size() > 0){
		
			for(References r : listReference)
			{
				if(r.getReferencePK().getReference_id() > max){
					max = r.getReferencePK().getReference_id();
				}
			}
		
		}
		
		return max+1; 
		
	}
	
}
