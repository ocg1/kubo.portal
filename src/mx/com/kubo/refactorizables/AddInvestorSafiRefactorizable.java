package mx.com.kubo.refactorizables;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.notificables.Evento;

public interface AddInvestorSafiRefactorizable {
	public void     init();
	public void     setSuccessFullOn();
	public void     cargaInfo ();
	
	public boolean finalizaSolicitud();
	public boolean notificacion(Evento evento ,String errormsg, Membership membership );
}
