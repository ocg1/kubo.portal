package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ShowPicture implements Serializable{
	
	private String urlImg;
	private boolean formatIspdfFile;
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String url = (String) facesContext.getExternalContext().getRequestParameterMap().get("url");
		urlImg=url;
		
		if(urlImg!=null && urlImg!=""){
			String format=urlImg.substring(urlImg.lastIndexOf(".")+1);
			if(format.toLowerCase().equals("pdf"))
				setFormatIspdfFile(true);
			else
				setFormatIspdfFile(false);
		}
	}
	public String getUrlImg() {
		return urlImg;
	}
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	public boolean isFormatIspdfFile() {
		return formatIspdfFile;
	}
	public void setFormatIspdfFile(boolean formatIspdfFile) {
		this.formatIspdfFile = formatIspdfFile;
	}	
}
