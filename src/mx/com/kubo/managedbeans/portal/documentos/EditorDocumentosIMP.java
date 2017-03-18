package mx.com.kubo.managedbeans.portal.documentos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean (name = "editor_documentos") @ViewScoped
public class EditorDocumentosIMP extends EditorDocumentosAMO
implements Serializable
{
	private static final long serialVersionUID = -6105142354559756692L;

	@PostConstruct
	public final void init()
	{
		faces = FacesContext.getCurrentInstance();
		
		real_path = faces.getExternalContext().getRealPath("//resources//");
				
		init_sesion();
		init_gestor();					
	}
}
