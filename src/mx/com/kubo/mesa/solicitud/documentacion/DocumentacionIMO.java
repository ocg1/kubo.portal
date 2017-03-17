package mx.com.kubo.mesa.solicitud.documentacion;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.FilesTypeCategoryBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Reca;

public interface DocumentacionIMO 
{
	void setPersona(NaturalPerson persona);
	void setProyect_loan(ProyectLoan proyect_loan);
	void setSesion(SessionBean sesion);
	void setFile_type_id(Integer file_type_id);
	
	void init();
	void init_file_type_id(AjaxBehaviorEvent event);
	void init_reca_id(AjaxBehaviorEvent      event);
	void handleFileUpload(FileUploadEvent    event);
	
	boolean isReca_ENABLED();
	boolean isLogo_ENABLED();
	
	Integer getFile_type_id();
	Integer getReca_id();
	
	Integer getSize_limit();
	
	ChangeBean getChangedocument();
	
	SelectItem[] getMenuItems();
	
	List<FilesTypeCategoryBean> getListFiles();
	List<Reca> getRecaitems();		
}
