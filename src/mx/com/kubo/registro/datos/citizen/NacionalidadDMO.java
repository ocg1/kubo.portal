package mx.com.kubo.registro.datos.citizen;

import mx.com.kubo.registro.datos.DatosPersonalesDMO;

public abstract class NacionalidadDMO extends DatosPersonalesDMO
implements NacionalidadIMO
{	
	protected Integer citizenship;	
	protected Integer country_id;
	
	protected static final int EXTRANJERO = 0;
	protected static final int MEXICANA   = 1;
}
