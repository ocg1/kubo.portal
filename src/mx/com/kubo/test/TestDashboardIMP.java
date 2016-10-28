package mx.com.kubo.test;

import java.io.File;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.registro.verificacion.PersonaBloqueadaIMP;

@ManagedBean(name = "TestDashboard") @ViewScoped
public class TestDashboardIMP extends TestDashboardAMO
implements Serializable, TestDashboardIMO
{
	private static final long serialVersionUID = -5736093180189669743L;
	
	public final void init_busqueda_clientes()
	{
		request = RequestContext.getCurrentInstance();
		
		blocked_person = new PersonaBloqueadaIMP();
		blocked_person.init_busqueda_clientes();
		
		blocked_person_TOTAL = blocked_person.getBlocked_person_TOTAL();
		
		request.addCallbackParam("blocked_person_TOTAL", blocked_person_TOTAL);
	}
	
	public final void init_folder_name(AjaxBehaviorEvent event)
	{
		request  = RequestContext.getCurrentInstance();
		faces    = FacesContext.getCurrentInstance();
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
        sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		external = faces.getExternalContext();
		
		real_PATH = external.getRealPath("//resources//");
		
		//real_PATH = "/home/damian/Documentos/contratos-copiados";
		
		input_text = (HtmlInputText) event.getComponent();
		
		folder_name = input_text.getValue().toString();
		
		folder_path = folder_name;
		
		init_lista_RECA();
		init_document_loader();
		
		request.addCallbackParam("folder_name", folder_name);
	}
	
	public final void init_lista_creditos_SAFI(AjaxBehaviorEvent event)
	{
		request  = RequestContext.getCurrentInstance();
		faces    = FacesContext.getCurrentInstance();
		
		folder = new File(folder_path);
		
		lista_files = folder.list();
		
		for(int i = 0; i < lista_files.length; i++)
		{
			file_name = lista_files[i];			
			
			init_safi_credit();	
			
			//System.out.println("('" + i + "', '" + safi_credit_id + "');");
			System.out.print("'" + safi_credit_id + "',");
		}
		
		request.addCallbackParam("loaded_files", lista_files.length);
	}
	

	public final void init_load(AjaxBehaviorEvent event)
	{
		request  = RequestContext.getCurrentInstance();
		faces    = FacesContext.getCurrentInstance();		
		
		folder = new File(folder_path);
		
		lista_files = folder.list();
										
		System.out.println("TestDashboardIMP.init_carga_contratos(): " + lista_files.length);
		
		for(int i = 0; i < lista_files.length; i++)
		{
			file_name = lista_files[i];			
			
			init_safi_credit();
			init_proyect_loan();
			init_contrato();			
			
			if(proyect_loan != null)
			{
				loader.fileUpload();
			}
			
			upload_OK = loader.isUpload_OK();
			
			if(upload_OK)
			{
				System.out.println("TestDashboardIMP.init_load(): " + lista_files[i] + " " + safi_credit_id);	
			}														
		}		
						
		request.addCallbackParam("loaded_files", lista_files.length);
	}
}
