package mx.com.kubo.mesa.solicitud.resumen.purpose;

import java.util.List;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlIMO;
import mx.com.kubo.model.Purpose;

public interface EditorPurposeIMO extends ChangeControlIMO
{	
	void init();
	void save(); 
	
	List<Purpose> getPurpose_list();
}
