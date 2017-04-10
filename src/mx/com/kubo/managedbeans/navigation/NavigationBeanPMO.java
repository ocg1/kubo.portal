package mx.com.kubo.managedbeans.navigation;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Fields;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SimulatorBean;

public abstract class NavigationBeanPMO extends NavigationBeanAMO
{	
	public void cambiaPorcentBasicos(ActionEvent e)
	{
		if( sesion.getArea().equals("L") ){
			if( is_Rechazo() ){
				
				faces     = FacesContext.getCurrentInstance();
				
				elContext = faces.getELContext();
				resolver  = faces.getApplication().getELResolver();
				external  = faces.getExternalContext();
			
				String url = (getPath() + "/Portal/no-posible-prestamo.xhtml?redirecFrom=NavigationBean");
				
				try 
				{
					System.out.println( "Redirigiendo Rechazo automatico desde NavigationBean: " + url);
					external.redirect(url);
				        
				} catch (IOException ex) {						      
					ex.printStackTrace();
				}catch(Exception e1){
					System.out.println("Redirect "+url);
				}
				
				return;
				
			}
			
		}
		
		for(MenuRegBean a:lista_menu_items)
		{
			if(a.getTargetItem().equals(paginaActual))
			{
				String s = porcentBasic + "";
				
				String[] array = s.split("\\.");
				
				int i = 0;
				
				if(array[0] != null && !array[0].equals("NaN"))
				{
					i = (Integer.parseInt(array[0]));	
				}
				
				
				a.setPorcItem(i);
				
				Access access = accessService.loadMaxAccess(getProspectus(), getCompany());
				
				access.setPercentage(i);
				access.setAccess_datetime(new Date());
				
				accessService.update(access);
				
				break;
			}
		}
	}
	
	public void cambiaPaginaAceptado(ActionEvent e)
	{		
		System.out.println("Entra -- cambiaPaginaAceptado");
		
		//Scoring score = scoringService.loadMaxScoringByProspectus(getProspectus(), getCompany());
		
		asignar_BC_score();
			
			if(score != null)
			{
				
				if(!score.getKubo_score_a().equals("N"))
				{
					asignar_menu_items();
					
					for(MenuRegBean a:lista_menu_items)
					{
						a.setIsblocked("none");
						a.setIsConBlocked("block");
						
						if(a.getScreenid() == 6 )
						{
							a.setDisplayBar(false);
						} else {
							
							a.setDisplayBar(true);
						}
					}
					
					SimulatorBean sim = simulatorService.getMaxSimulationProspectWithPurpose(score.getProspectus_id(), getCompany());
					
					simulator.setPurpose_id(sim.getPurpose_id());
					simulator.setAmmount(sim.getAmmount());
					//simulator.setComisionApertura(sim.ge);
					simulator.setMontoCuota(sim.getPayment());
					//comisionApertura
					
					cambiaPagina(e);
				}				
			}
			
			System.out.println("termina -- cambiaPaginaAceptado");
	}
	
	public void cambiaPaginaRechazado(ActionEvent e)
	{		
		System.out.println("Entra -- cambiaPaginaAceptado");
		
		//Scoring score = scoringService.loadMaxScoringByProspectus(getProspectus(), getCompany());
		
		asignar_BC_score();
					
		cambiaPagina(e);
				
		menuSel      = menu_item_selected.split("::")[2];	
		screen_id    = menu_item_selected.split("::")[1];
		paginaActual = menu_item_selected.split("::")[0];
		
		Access access = new Access();
		access.setCompany_id(getCompany());
		access.setProspectus_id(getProspectus());
		access.setScreen_id(Integer.parseInt(screen_id));
		access.setPercentage(0);
		access.setWeb_browser        (sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system          (sesion.getOsbrawser());
		access.setHorizontal_size    (sesion.getBrowser_height());
		access.setVertical_size      (sesion.getBrowser_width());
		access.setIpaddress          (sesion.getIP_address_client());
		access.setProspectus_id_coach(sesion.getCoachProspectus_id());
		access.setUser_agent         (sesion.getUser_agent());
		access.setDevice_info        (sesion.getDevice_info());
		
		access.setUrl_access		  (sesion.getUrl_access());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access, true);
		
		setDisplaySim(false);
		
		System.out.println("termina -- cambiaPaginaAceptado");
	}

	public void cambiaPagina(ActionEvent evento)
	{			
		menu_item_selected = (String) evento.getComponent().getAttributes().get("section").toString();
		
		viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		
		viewMap.clear();
				
		asignar_menu_item_selected(menu_item_selected);		
	}
}
