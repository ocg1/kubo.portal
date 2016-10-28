package mx.com.kubo.registro.consulta;

import mx.com.kubo.registro.ChangeControlDMO;

public abstract class AutorizadorDMO extends ChangeControlDMO 
implements AutorizadorIMO
{
	protected AutorizadorDMO()
	{
		comments = "Autorización de Buro de Crédito";
	}
}
