package mx.com.kubo.services.fondeo.cuenta;

import java.text.SimpleDateFormat;

import safisrv.ws.CuentasServicios.AltaRelacionadoCtaRequest;
import safisrv.ws.CuentasServicios.AltaRelacionadoCtaResponse;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.AddressTokenIMO;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.services.fondeo.ServiceFondeoIMO;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Residence;
import mx.com.kubo.model.ResidencePK;
import mx.com.kubo.model.ViewClientInfo;
import mx.com.kubo.constantes.ActividadEconomica;
import mx.com.kubo.tools.Utilities;

public abstract class AltaRelacionadosCuentaDMO extends ServiceFondeoDMO
implements ServiceFondeoIMO
{	
	protected ServiceAltaRelacionadosIMP service_relacionados_cuenta;
	
	protected AltaRelacionadoCtaRequest   request;
	protected AltaRelacionadoCtaResponse  response;		
	
	protected ViewClientInfo view_client_info;
	protected ActividadEconomica actividad;
	
	protected Residence   tipo_vivienda;
	protected ResidencePK tipo_vivienda_PK;
	
	protected AddressTokenIMO token;
	
	protected String fecha_nacimiento;
	protected String address_TOKEN;	
	protected String antiguedad;
	
	protected Integer actividad_economica_id;
	protected Integer tipo_vivienda_id;
	protected Integer antiguedad_vivienda;
	protected Integer tipo_identificacion;
	
	protected int ocupacion_id;
		
	protected AltaRelacionadosCuentaDMO()
	{		
		service_client_INFO    = Utilities.findBean("viewClientInfoServiceImp");
		service_address        = Utilities.findBean("addressServiceImp");
		service_residencia     = Utilities.findBean("residenceServiceImp");
		service_saving_account = Utilities.findBean("savingAccountServiceImp");
		
		service_relacionados_cuenta = new ServiceAltaRelacionadosIMP();
		
		date_format = new SimpleDateFormat("yyyy-MM-dd");
		
		fecha_nacimiento = "";
	}
	
	public void setAcreditado(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		if(proyect_loan != null)
		{		
			acreditado = proyect_loan.getPerson();
			
			company_id          = acreditado.getNatPerPK().getCompany_id();
			prospectus_id       = acreditado.getNatPerPK().getProspectus_id(); 
			antiguedad_vivienda = acreditado.getLength_of_residence();
			tipo_vivienda_id    = acreditado.getResidence_id();			
			SAFI_client_id      = acreditado.getSafi_client_id();
						
			init_antiguedad();
			init_address_TOKEN();
			init_saving_account();
			
			tipo_identificacion = acreditado.getIdentification_type_id();
			
			if(tipo_identificacion != null)
			{
				switch(tipo_identificacion)
				{
					case IFE:
						acreditado_ID = acreditado.getMx_ife_cveelector();
					break;
					
					case INE:
						acreditado_ID = acreditado.getMx_ine_cic();
					break;
				}
			}
			
			SAFI_related_person_NEW = saving_account != null && SAFI_account_id != null && SAFI_related_person == null;
		}
	}
	
	protected abstract void init_antiguedad();
	protected abstract void init_address_TOKEN();
	protected abstract void init_saving_account();
}
