package mx.com.kubo.registro.verificacion;

public final class CierreDelDiaIMP extends CierreDelDiaDMO
implements CierreDelDiaIMO
{
	public final void init()
	{
		if(system_param != null)
		{
			String system_value = system_param.getValue();
			
			if(system_value != null && system_value.equals("S"))
			{
				cierre_del_dia_ENABLED = true;
			}
		}
	}
}
