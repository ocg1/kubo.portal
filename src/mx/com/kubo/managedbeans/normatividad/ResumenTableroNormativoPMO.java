package mx.com.kubo.managedbeans.normatividad;

import mx.com.kubo.model.Access;

public class ResumenTableroNormativoPMO extends ResumenTableroNormativoDMO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void accessRegister(){
		
		Access access = new Access();
		access.setScreen_id(81);
		access.setPercentage(0);
		access.setCompany_id          (sesion.getCompany_id());
		access.setProspectus_id       (sesion.getProspectus_id());
		access.setWeb_browser         (sesion.getNamebrawser());
		access.setWeb_browser_version (sesion.getVersionbrawser());
		access.setOp_system           (sesion.getOsbrawser());
		access.setHorizontal_size     (sesion.getBrowser_width());
		access.setVertical_size       (sesion.getBrowser_height());
		access.setUser_agent          (sesion.getUser_agent());
		access.setDevice_info         (sesion.getDevice_info());
		access.setIpaddress           (sesion.getIP_address_client());
		access.setProspectus_id_viewed(null);
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		access.setUrl_access		  (sesion.getUrl_access());
		
		accessService.add(access, true);
		
	}
	
	protected void initResumenTablero(){
		
		tableronormativoresumen = tableronormativoservice.getResumenTableroNormativo();
		
	}
	
}
