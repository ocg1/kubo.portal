package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "sessionBean") @SessionScoped
public class SessionBean extends SessionBeanDMO
implements Serializable
{	
	private static final long serialVersionUID = 3501325771178091844L;

	@PostConstruct
	public void init()
	{		
		System.out.println("SessionBean.init(): OK");		
	}
			
	public SessionBean() 
	{
		super();
	}
	
	
	public final void sessionOut()
	{
		setOpeningCommission (null);
		setProspectus_id     (null);
		setCompany_id        (null);
		setNickname          (null);
		setLastPage          (null);
		setEmail             (null);
		setNombre            (null);
		setRate              (null);
		setUsrImg            (null);
		setArea              (null);
		setTrackId           (null);
		setNamebrawser       (null);
		setVersionbrawser    (null);
		setOsbrawser         (null);
		setBrowser_width     (null);
		setBrowser_height    (null);
		setIP_address_client (null);
		setRole_id           (null);		
		setOrigen_campaign   (null);
		setSm_email          (null);
		setSm_first_name     (null);
		setFecha_acceso_ACTUAL(null);
		setFecha_acceso_LAST(null);
		setMaxTimeInactiveSegundos     (null);
		
		setBlochHeader(false);
		setMobile     (false);
		setConnected  (false);
		setCoachProspectus_id(null);
		setPromotor_id(null);
		setUser_graphic_temp(null);
		setVersion_description(null);
		setSegment_name( "" );
		setKubo_score( "" );
		setReferred_client( "" );
		setReferred_not_client( "" );
		setHaveUsrImg( false );
		setIsnew("N");
		setUrl_access(null);
		setFb_New( false );
		setMedium("");
		
		setNew_consulting( false );
		setEnabled_Ren_4_Click(false);
		
	}
	
	
	public final String setAreaReg(String areaChar)
	{
		if(areaChar != null)
		{			
			setArea(areaChar.charAt(0));
			return "preregistro";
			
		} else {
			return null;
		}		
	}	
	
	public void notificationLoginSucces() {
		
		setFlagLoginEnabled(false);
	}
}