package mx.com.kubo.controller;

import mx.com.kubo.controller.threads.VerificadorInicio;

public class ThreadVerificacionInicial {

public boolean excecuteAction(){
		
		try{
			
			VerificadorInicio verifica = new VerificadorInicio();
			
			verifica.start();
			
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
	}
	
}
