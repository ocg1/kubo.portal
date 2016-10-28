package mx.com.kubo.listeners.mesa.solicitud;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.model.FullName;
import mx.com.kubo.model.FullNamePK;

public abstract class EditorNombreAMO extends EditorNombreDMO
{
	protected void init_first_name() 
	{
		first_name = (String) input_text.getValue();
		
		first_name = first_name.trim();
		
		if(first_name.length() > 0)
		{			
			change_control_bean = lista_cambios.get(INDEX_NOMBRE);
			
			change_control_bean.setNewValue(first_name);
			change_control_bean.setOrigValue(person.getFirst_name());
			change_control_bean.setWhyChangeData("Se modifica el nombre del inversionista desde mesa de control");
			
			
			lista_cambios.set(INDEX_NOMBRE, change_control_bean);
			
			edicion_ENABLED = true;
			error_first_name_ENABLED = false;
			
			System.out.println("EditorNombreIMP.listener_first_name(): " + first_name);
			
		} else {
			
			edicion_ENABLED = false;
			error_first_name_ENABLED = true;					
			
			System.out.println("EditorNombreIMP.listener_first_name(): ERROR");
		}
	}
	
	protected void init_middle_name() 
	{
		middle_name = (String) input_text.getValue();
		
		middle_name = middle_name.trim();
				
		change_control_bean = lista_cambios.get(INDEX_SEGUNDO_NOMBRE);
		
		change_control_bean.setNewValue(middle_name);
		change_control_bean.setOrigValue(person.getMiddle_name());
		change_control_bean.setWhyChangeData("Se modifica el segundo nombre del inversionista desde mesa de control");
		
		lista_cambios.set(INDEX_SEGUNDO_NOMBRE, change_control_bean);
		
		edicion_ENABLED = true;		
		
		System.out.println("EditorNombreIMP.listener_middle_name(): " + middle_name);
	}
	
	protected void init_father_last_name() 
	{
		father_last_name = (String) input_text.getValue();
		
		father_last_name = father_last_name.trim();
		
		if(father_last_name.length() > 0)
		{			
			change_control_bean = lista_cambios.get(INDEX_APELLIDO_PATERNO);
			
			change_control_bean.setNewValue(father_last_name);
			change_control_bean.setOrigValue(person.getFather_last_name());
			change_control_bean.setWhyChangeData("Se modifica el apellido paterno del inversionista desde mesa de control");			
			
			lista_cambios.set(INDEX_APELLIDO_PATERNO, change_control_bean);
			
			edicion_ENABLED = true;
			error_father_last_name_ENABLED = false;
			
			System.out.println("EditorNombreIMP.listener_father_last_name(): " + father_last_name);
			
		} else {
			
			edicion_ENABLED = false;
			error_father_last_name_ENABLED = true;		
			
			System.out.println("EditorNombreIMP.listener_father_last_name(): ERROR");
		}	
	}
	
	protected void init_mother_last_name() 
	{
		mother_last_name = (String) input_text.getValue();
		
		mother_last_name = mother_last_name.trim();		
				
		change_control_bean = lista_cambios.get(INDEX_APELLIDO_MATERNO);
		
		change_control_bean.setNewValue(mother_last_name);
		change_control_bean.setOrigValue(person.getMother_last_name());
		change_control_bean.setWhyChangeData("Se modifica el apellido materno del inversionista desde mesa de control");
		
		lista_cambios.set(INDEX_APELLIDO_MATERNO, change_control_bean);
		
		edicion_ENABLED = true;
		
		System.out.println("EditorNombreIMP.listener_mother_last_name(): " + mother_last_name);
	}
	
	protected void init_guardar_cambios() 
	{
		if(edicion_ENABLED)
		{			
			person.setFirst_name(first_name);
			person.setMiddle_name(middle_name);
			person.setFather_last_name(father_last_name);
			person.setMother_last_name(mother_last_name);
			
			person = service_natural_person.update(person);
			
			full_name = person.NombreCompletoNPM();
			
			for(ChangeBean bean: lista_cambios)
			{
				change_control_OK = service_change_control.registrar_change_control(bean, company_id, emisor_prospectus_id, prospectus_id);
			}
			
			edicion_ENABLED = false;
			
			init_lista_cambios();
			init_person();
			init_lista_cambios();
			init_bitacora_change_control();	
			
			saveFullName();
			
			System.out.println("EditorNombreIMP.listener_guardar_cambios(): OK");				
		} 
	}
	
private void saveFullName(){
		
		String first_name       = person.getFirst_name()        == null ? "" : person.getFirst_name().trim();	
		String middle_name      = person.getMiddle_name()       == null ? ""  : person.getMiddle_name().trim();
		String father_last_name = person.getFather_last_name()  == null ? "" : person.getFather_last_name().trim();
		String mother_last_name = person.getMother_last_name()  == null ? "" : person.getMother_last_name().trim();
		
		String fullnameStr = first_name; 
		
		if( middle_name.length() > 0 ){
			
			if( fullnameStr.length() > 0 ){
				fullnameStr += " ";
			}
			
			fullnameStr +=  middle_name;
			
		}
		if( father_last_name.length() > 0 ){
			
			if( fullnameStr.length() > 0 ){
				fullnameStr += " ";
			}
			
			fullnameStr += father_last_name;
			
		}
		if( mother_last_name.length() > 0 ){
			
			if( fullnameStr.length() > 0 ){
				fullnameStr += " ";
			}
			
			fullnameStr	+= mother_last_name ;
			
		}
		
		FullNamePK fpk = new FullNamePK();
		
		fpk.setCompany_id(person.getNatPerPK().getCompany_id());
		fpk.setProspectus_id(person.getNatPerPK().getProspectus_id());
		
		FullName fullname = full_name_service .getFullName(fpk);
		
		if( fullname == null ){
			
			fullname = new FullName();
			
			fullname.setPk(fpk);
			fullname.setFull_name(fullnameStr);
			
			full_name_service.saveFullName(fullname);
			
		}else{
			
			fullname.setFull_name(fullnameStr);
			full_name_service.updateFullName(fullname);
			
		}
		
	}
	
}
