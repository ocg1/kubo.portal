package mx.com.kubo.managedbeans.portal.documentos;

import mx.com.kubo.mesa.solicitud.documentacion.DocumentacionIMP;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.portal.AccessIMP;
import mx.com.kubo.portal.reader.ParameterReaderIMP;

public abstract class EditorDocumentosAMO extends EditorDocumentosDMO 
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
		    file_type_id    = reader.getFile_type_id();	
			proyect_loan_id = reader.getProyect_loan_id();
			   			
			auditor = new AccessIMP();
			auditor.setService_access(service_access);
			auditor.setSesion(sesion);
			auditor.setScreen_id(SCREEN_EDITAR_DOCUMENTOS);
			auditor.setAccess_from(access_from);
		}		
	}
	
	protected void init_gestor()
	{
		if(sesion != null)
		{
	                    company_id = sesion.getCompany_id();
					 prospectus_id = sesion.getCoachProspectus_id();
	          prospectus_id_viewed = sesion.getProspectus_id();
	          
	  		membership_PK = new MembershipPK(prospectus_id, company_id);
			
			mesa = service_membership.getMembershipById(membership_PK);
			
			init_role_function();
			
			membership_PK = new MembershipPK(prospectus_id_viewed, company_id);
			
			cliente = service_membership.getMembershipById(membership_PK);
			
			proyect_loan = service_proyect_loan.getProyectLoanByProyectLoanID(proyect_loan_id, prospectus_id_viewed, company_id);
			
			gestor = new DocumentacionIMP();
			gestor.setReal_path(real_path);
			gestor.setSesion(sesion);
			gestor.setProyect_loan (proyect_loan);		
			gestor.setPersona(cliente.getPerson());
			
			if(file_type_id != null)
			{
				gestor.setFile_type_id(file_type_id);
				
				editar_file_ENABLED = false;
			}
			
			gestor.init();
		}
	}
	
	protected void init_role_function() 
	{		
		if(mesa != null)
		{		
			role_id = mesa.getPerson().getProspectus().getRole_id();
			
			lista_role_function = service_role_function.getLstFunctionByRole(role_id, company_id);
			
			for(RoleFunction rf : lista_role_function)
			{
				int function_id = rf.getPk().getFunction_id();
				
				if(function_id == EDITAR_DOCUMENTOS)
				{ 			
					editar_file_ENABLED = true;				
				}
				
				if(function_id == AUTORIZAR_CONTRATOS)
				{
					autorizar_contratos_ENABLED = true;
				}
			}
		}
	}
}
