package mx.com.kubo.managedbeans.perfil;

public abstract class PerfilControllerPMO extends PerfilControllerAMO
{
	protected boolean is_cambio_pregunta_OK()
	{
		cambio_pregunta_OK = false;
		
		switch(pregunta_selected)
		{
			case 1:
				if((question_id == question_id_1) || (question_id != question_id_2 && question_id != question_id_3))
				{
					cambio_pregunta_OK = true;
					
				} 
			break;
			
			case 2:
				if((question_id == question_id_2) || (question_id != question_id_1 && question_id != question_id_3))
				{
					cambio_pregunta_OK = true;
					
				} 
			break;
			
			case 3:
				if((question_id == question_id_3) || (question_id != question_id_1 && question_id != question_id_2))
				{
					cambio_pregunta_OK = true;
					
				} 
			break;
		}
		
		return cambio_pregunta_OK;
	}
	
	protected void procesar_cambio_pregunta() 
	{
		switch(pregunta_selected)
		{
			case 1:
				if(question_id == question_id_1)
				{
					actulizar_question_pool();
					
				} else if(question_id != question_id_2 && question_id != question_id_3) {
					
					guardar_question_pool(question_id_1);
					
				} else {
					
					is_saved_OK = false;
				}
			break;
			
			case 2:
				if(question_id == question_id_2)
				{
					actulizar_question_pool();
					
				} else if(question_id != question_id_1 && question_id != question_id_3) {
					
					guardar_question_pool(question_id_2 );
					
				} else {
					
					is_saved_OK = false;
				}
			break;
			
			case 3:
				if(question_id == question_id_3)
				{
					actulizar_question_pool();
					
				} else if(question_id != question_id_1 && question_id != question_id_2) {
					
					guardar_question_pool(question_id_3);
					
				} else {
					
					is_saved_OK = false;
				}
			break;
		}
	}
		
	protected void procesar_respuesta() 
	{
		is_answer_OK = false;
		
		switch(pregunta_selected)
		{
			case 1:
				if(answer_1.equals(respuesta))
				{
					is_answer_OK = true;
				}
			break;
			
			case 2:
				if(answer_2.equals(respuesta))
				{
					is_answer_OK = true;
				}
			break;
			
			case 3:
				if(answer_3.equals(respuesta))
				{
					is_answer_OK = true;
				}
			break;
		}
	}
}
