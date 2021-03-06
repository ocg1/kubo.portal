package mx.com.kubo.managedbeans.portal.referencias;

import java.util.ArrayList;

import mx.com.kubo.model.References;
import mx.com.kubo.portal.AccessIMP;
import mx.com.kubo.portal.reader.ParameterReaderIMP;

public abstract class ReferenciasPersonalesAMO extends ReferenciasPersonalesDMO 
{		
	protected void init_sesion() 
	{
		reader = new ParameterReaderIMP();
		reader.setFaces(faces);
		reader.init_sesion();
		
		access_from = reader.getAccess_from();
		
		if(access_from != null)
		{
		    sesion = reader.getSesion();				
		    page_title = reader.getPage_title();
		
			prospectus_id = sesion.getProspectus_id();
			   company_id = sesion.getCompany_id();  
			   
			auditor = new AccessIMP();
			auditor.setService_access(service_access);
			auditor.setSesion(sesion);
			auditor.setScreen_id(SCREEN_REFERENCIAS_PERSONALES);
			auditor.setAccess_from(access_from);
		}
	}
	
	protected void init_referencias() 
	{		
		listReference = service_references.loadReferencesListByProspect(prospectus_id, company_id);	
		
		ArrayList<References> array = new ArrayList<References>();
		
		for(References r : listReference)
		{
			if(r.getEmail()==null || r.getEmail().indexOf("null")!=-1||r.getEmail().trim().equals(""))
			{
				r.setEmail("No proporcionó su e-mail");
			}
			
			if(r.getPhone()==null || r.getPhone().indexOf("null")!=-1|| r.getPhone().trim().equals(""))
			{
				r.setPhone("No proporcionó su teléfono");
				
				array.add(r);
				
				continue;
				
			} else {
				
				r.setPhone( "044/045" + r.getPhone() );
			}
			
			if(r.getRelationship()==null || r.getPhone().indexOf("null")!=-1|| r.getPhone().trim().equals(""))
			{
				r.setRelationship("No proporcionó su parentesco");
			}
			
		}
		
		for(References x : array)
		{
			listReference.remove(x);
		}
		
		if(array.size() > 0)
		{			
			if(array.size() > 1)
			{			
				noshowRefStr="(*)Además proporcionó "+array.size()+" referencias sin número telefónico.";
				
			} else {
			
				noshowRefStr="(*)Además proporcionó "+array.size()+" referencia sin número telefónico.";				
			}	
			
		} else {
			
			noshowRefStr="";			
		}
	}

}
