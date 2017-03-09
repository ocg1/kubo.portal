package mx.com.kubo.mesa.solicitud.resumen.score;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class EditorScoreIMP extends EditorScoreDMO
implements EditorScoreIMO
{
	public final void init_kubo_score_letter(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		kuboScoreLetter = select_one_menu.getValue().toString();				
		
		request.addCallbackParam("kuboScoreLetter", kuboScoreLetter);
	}
	
	public final void init_kubo_score_number(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		kuboScoreNumber = select_one_menu.getValue().toString();				
		
		request.addCallbackParam("kuboScoreNumber", kuboScoreNumber);
	}
	
	public final void save() 
	{							
		new_value = kuboScoreLetter + kuboScoreNumber;
		
		save_change_control();		
		init_change_control_bean();
		
		if(change_control_OK)
		{
			init_editor();
		}		
	}
	
	public final void init_editor() 
	{
		proyect_loan.setKubo_score_a(kuboScoreLetter);
		proyect_loan.setKubo_score_b(kuboScoreNumber);
		
		update_OK = service_proyect_loan.update(proyect_loan);
		
		if(update_OK)
		{			
			proyect_loan = service_proyect_loan.findProyect(proyect_loan.getProyectloanPk());
			
			kuboScoreLetter = proyect_loan.getKubo_score_a();
			kuboScoreNumber = proyect_loan.getKubo_score_b();
			
			original_value = kuboScoreLetter + kuboScoreNumber;						
		}
	}
}
