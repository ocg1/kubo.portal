package mx.com.kubo.managedbeans.mesa.administracion;

import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.FileUploadEvent;

public interface AdministrationProfileIMO 
{
	void    handleFileBlockedPerson(FileUploadEvent event);
	void init_blocked_person_list(AjaxBehaviorEvent event);
	
    void         init_citizenship(AjaxBehaviorEvent event);
	void    delete_blocked_person(AjaxBehaviorEvent event);
}
