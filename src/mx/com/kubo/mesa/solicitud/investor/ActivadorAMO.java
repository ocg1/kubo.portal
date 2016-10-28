package mx.com.kubo.mesa.solicitud.investor;

import java.util.Date;

import mx.com.kubo.model.IdentificationCollector;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;

public abstract class ActivadorAMO extends ActivadorDMO
{
	protected void init_proyect_loan() 
	{
		proyect_loan_PK = new ProyectLoanPK();
		proyect_loan_PK.setCompany_id(company_id);
		proyect_loan_PK.setProspectus_id(prospectus_id_inv);
		
		membership_PK = new MembershipPK();
		membership_PK.setCompany_id(company_id);
		membership_PK.setProspectus_id(prospectus_id_inv);
		
		membership = service_membership.getMembershipById(membership_PK);
		
		proyect_loan = new ProyectLoan();		
		proyect_loan.setPerson( membership.getPerson() );
		proyect_loan.setProyectloanPk(proyect_loan_PK);
		
		Integer tipo_identificacion = proyect_loan.getPerson().getIdentification_type_id();
		
		acreditado_IFE = null;
		
		if(tipo_identificacion != null)
		{
			switch(tipo_identificacion)
			{
				case IFE:
					acreditado_IFE = proyect_loan.getPerson().getMx_ife_cveelector();
				break;
				
				case INE:
					acreditado_IFE = proyect_loan.getPerson().getMx_ine_cic();
				break;
			}
		}
	}
	
	public boolean callSPSafiAltaIFE(int prospectus_id_inv,int company_id, ProyectLoan pl) 
	{
		System.out.println("+++++    LLAMANDO SP SAFI    +++++");
			
			try {
				
				
				ServiceCalling srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(pl.getPerson().getNatPerPK().getCompany_id());
				srvCall.setInfo("Invocando el procedimiento almacenado microfin.IDENTIFICLIENTEALT");
				srvCall.setProspectus_id(pl.getPerson().getNatPerPK().getProspectus_id());
				srvCall.setStatus(1);
				
				service_calling.saveServiceCall(srvCall);
				
				IdentificationCollector objIFE;				
				
				if(pl.getPerson().getCitizenship() == EXTRANJERO)
				{
					objIFE = service_natural_person.getIdentificationCollector(Long.parseLong(pl.getPerson().getSafi_client_id()), 
							6, "S", "111111111111111111", null, null, 1, 2, new Date(), "1.1.1.1", "Portal Kubo", 1, 1);
				} else {
					objIFE = service_natural_person.getIdentificationCollector(Long.parseLong(pl.getPerson().getSafi_client_id()), 
							1, "S", acreditado_IFE, null, null, 1, 2, new Date(), "1.1.1.1", "Portal Kubo", 1, 1);
				}
				
				srvCall = new ServiceCalling();
				if(objIFE != null){
					if(objIFE.getNumErr().equals("001") || objIFE.getNumErr().equals("000")){
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id(pl.getPerson().getNatPerPK().getCompany_id());
						srvCall.setInfo("Regresando Satisfactoriamente de invocar el procedimiento almacenado microfin.IDENTIFICLIENTEALT: "+objIFE.getErrMen());
						srvCall.setProspectus_id(pl.getPerson().getNatPerPK().getProspectus_id());
						srvCall.setStatus(2);
						
						service_calling.saveServiceCall(srvCall);
						
						return true;
					}else{
						
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id(pl.getPerson().getNatPerPK().getCompany_id());
						srvCall.setInfo("Regresando satisfactoriamente de invocar el procedimiento almacenado microfin.IDENTIFICLIENTEALT:"+objIFE.getErrMen());
						srvCall.setProspectus_id(pl.getPerson().getNatPerPK().getProspectus_id());
						srvCall.setStatus(2);
						
						service_calling.saveServiceCall(srvCall);
						System.out.println(objIFE.getErrMen());
						return false;
					}
				}else{
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getPerson().getNatPerPK().getCompany_id());
					srvCall.setInfo("Error al invocar el procedimiento almacenado microfin.IDENTIFICLIENTEALT");
					srvCall.setProspectus_id(pl.getPerson().getNatPerPK().getProspectus_id());
					srvCall.setStatus(3);
					srvCall.setException("El objeto es null. Error en el DAO NaturalPerson.getIdentificationCollector()");
					
					service_calling.saveServiceCall(srvCall);
					System.out.println("Error al dar de alta los datos de identificación en Safi.");
					return false;
				}

			} catch (Exception e) {
				System.out.println("****************************************************************************************************");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println("Exception:");
				e.printStackTrace();
				System.out.println("-----Exception-----");
				System.out.println(e.getMessage());
				System.out.println("****************************************************************************************************");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				
				ServiceCalling srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(pl.getPerson().getNatPerPK().getCompany_id());
				srvCall.setInfo("Error al invocar el procedimiento almacenado microfin.IDENTIFICLIENTEALT");
				srvCall.setProspectus_id(pl.getPerson().getNatPerPK().getProspectus_id());
				srvCall.setStatus(3);
				
				if(e.getStackTrace().toString().length()>1999)
					srvCall.setException(e.getStackTrace().toString().substring(0,1999));
				else
					srvCall.setException(e.getStackTrace().toString());
				service_calling.saveServiceCall(srvCall);
				
				System.out.println("Error al dar de alta los datos de identificación en Safi.");
				
				return false;
				
			}
		}
	public boolean notificacion(Evento evento,String errormsg,Membership emisor,  Membership acreditado )
	{
		try 
		{
			notificador.setEmisor(emisor);
			notificador.setAcreditado(acreditado);
			notificador.notificar(evento, null, null, errormsg);
		} catch (NotificacionException e) {			
			e.printStackTrace();
		}	
		
		return true;
	}
}
