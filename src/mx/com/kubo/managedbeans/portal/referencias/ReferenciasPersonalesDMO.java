package mx.com.kubo.managedbeans.portal.referencias;

import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.portal.reader.ParameterReaderIMO;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.References;
import mx.com.kubo.model.ReferencesMotive;
import mx.com.kubo.model.ReferencesScore;
import mx.com.kubo.portal.AccessIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ReferencesService;

public abstract class ReferenciasPersonalesDMO
{
	@ManagedProperty("#{referencesServiceImp}")
	protected ReferencesService service_references;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access;
	
	protected   FacesContext faces;	
	protected RequestContext request;
	
	protected SessionBean sesion;
	
	protected          AccessIMO auditor;
	protected ParameterReaderIMO reader;
	
	protected List<References> listReference;
	
	protected List<ReferencesMotive> listReferenceMotive;
	
	protected List<ReferencesScore> listReferenceScore;
	
	protected References referenceEdit;
	
	protected References referenceRemove;
	
	protected References referenceAdd;
	
	protected String noshowRefStr;
	protected String page_title;
	protected String access_from;
	
	protected Integer company_id;
	protected Integer prospectus_id;	
	
	protected final int SCREEN_REFERENCIAS_PERSONALES = 70;
	
	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public void setService_references(ReferencesService service) 
	{
		service_references = service;
	}
	
	public final AccessIMO getAuditor() 
	{
		return auditor;
	}

	public final String getNoshowRefStr()
	{
		return noshowRefStr;
	}
	
	public final List<References> getListReference()
	{
		return listReference;
	}

	public final String getPage_title() 
	{
		return page_title;
	}

	public References getReferenceEdit() {
		return referenceEdit;
	}

	public void setReferenceEdit(References referenceEdit) {
		this.referenceEdit = referenceEdit;
	}

	public References getReferenceRemove() {
		return referenceRemove;
	}

	public void setReferenceRemove(References referenceRemove) {
		this.referenceRemove = referenceRemove;
	}

	public References getReferenceAdd() {
		return referenceAdd;
	}

	public void setReferenceAdd(References referenceAdd) {
		this.referenceAdd = referenceAdd;
	}

	public List<ReferencesMotive> getListReferenceMotive() {
		return listReferenceMotive;
	}

	public void setListReferenceMotive(List<ReferencesMotive> listReferenceMotive) {
		this.listReferenceMotive = listReferenceMotive;
	}

	public List<ReferencesScore> getListReferenceScore() {
		return listReferenceScore;
	}

	public void setListReferenceScore(List<ReferencesScore> listReferenceScore) {
		this.listReferenceScore = listReferenceScore;
	}
}
