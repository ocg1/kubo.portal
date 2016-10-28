package mx.com.kubo.registro.consulta;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.registro.ChangeControlIMO;

public interface AutorizadorIMO extends ChangeControlIMO
{
	void init_change(AjaxBehaviorEvent evento);
}
