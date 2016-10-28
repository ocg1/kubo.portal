package mx.com.kubo.test.login;

public abstract class LoginTestAMO extends LoginTestDMO
{	
	protected void asignar_banderas_sesion()
	{
		sesion.setFailedActive(false);
		sesion.setFailedPass(false);
		sesion.setFailedUser(false);
		sesion.setSessionUsed(false);
		sesion.setFailedTracking(false);
		sesion.setCanceled(false);
	}
}
