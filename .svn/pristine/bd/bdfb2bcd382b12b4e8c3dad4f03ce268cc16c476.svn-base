package mx.com.kubo.managedbeans.mesa.solicitud.notas;

import mx.com.kubo.model.mesa.solicitud.notas.TipoDeNota;

public abstract class NotasDelCasoPMO extends NotasDelCasoAMO
{
	protected void procesar_tipo_nota() 
	{
		tipo_de_nota = TipoDeNota.getInstance(tipo_nota_SELECTED);
		
		switch(tipo_de_nota)
		{
			case RECHAZO:
			case POSPUESTO:
			case DESISTIDO:
			case PREAPROBADO:
				addNote.setNote_type_id(tipo_nota_SELECTED);
				addNote.setMotive_id(motive_id_SELECTED);
				
				lista_motivos = service_motive.getMotiveStatusListByNoteType(tipo_nota_SELECTED);
			break;
			
			case VISITA_DOMICILIARIA:
				addNote.setNote_type_id(tipo_nota_SELECTED);
				
				visita_domiciliaria_ENABLED = true;
				
				request.addCallbackParam("visita_domiciliaria_ENABLED", true);
			break;
			
			default: 
				addNote.setNote_type_id(tipo_nota_SELECTED);
			break;
		}												
	}
}
