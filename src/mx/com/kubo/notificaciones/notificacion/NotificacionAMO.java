package mx.com.kubo.notificaciones.notificacion;

import static mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan.CANCELADO;
import static mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan.DESISTIDO;
import static mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan.POSPUESTO;

import java.util.ArrayList;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MotivePK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.StatusProyectCatPK;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;

public abstract class NotificacionAMO extends NotificacionPMO
{					
	protected final void asignarDatosAcreditado(Membership acreditado)
	{
		setProspectName(acreditado.getPerson().NombreCompletoNPM());
		setProspectus_id(acreditado.getMembershipPK().getProspectus_id() + "");						
		setEmailAcred(acreditado.getEmail());
		
		if(acreditado.getPerson().getMx_ife_cveelector() != null && acreditado.getPerson().getMx_ife_cveelector().trim().length() > 0 )
		{
			setClaveElector(acreditado.getPerson().getMx_ife_cveelector());
		} else { 
			setClaveElector("No proporcionada");
		}	
	}
	
	protected final void asignarNegativeBCScore(Membership emisor, Scoring score)
	{					
		if(score != null && score.getBc_score() != null && Integer.parseInt(score.getBc_score()) < 0)
		{						
			setDescNegativeBCScore(getCirculo_INFO(emisor, score));
		}		
	}
	
	protected final void asignarScore(Scoring score) 
	{
		setBcScore  (score.getBc_score());
		setTasa     (score.getRate() + "");
		setKuboScore(score.getKubo_score_a() + score.getKubo_score_b());
		setCalif    (score.getKubo_score_a() + score.getKubo_score_b());
		setComision (score.getOpening_commission() + "");
		setBurSolNum(score.getMx_solicitud_buro());					
	}
	
/*	
	protected void asignarReason(Membership member) 
	{
		String registrationReason = new RegistrationReasonIMP().validaRegistrationReason(member);
		setRegistrationReason(registrationReason);
	}
*/	
	
	protected void asignarLoanType(ProyectLoan proyecto) 
	{
		if(proyecto != null && proyecto.getLoan_type() != null && proyecto.getLoantype() != null)
		{
			setLoanType(proyecto.getLoantype().getLoan_type_name());
		}else{
			setLoanType("No definido");
		}	
	}
	
	protected void asignarBcFirma(Membership emisor)
	{
		setBcFirma(true);	
		setUserControlName(emisor.getPerson().NombreCompletoNPM());
	}
	
	protected void asignarError(String errormsg) 
	{
		setError(true);
		setErrormsg(errormsg);
	}
	
	protected void asignaDatosPublicacion(ProyectLoan proyecto)
	{		
		setFolio(proyecto.getPerson().getProspectus().getTracking_id());
		setMonto(number_format.format(proyecto.getAmmount()));
		setPlazo(getPlazo(proyecto));
		setProposito_del_credito( proyecto.getProyect().getPurpose().getName() );
		publish = true;
	}
	
	protected void asignarProject(ProyectLoan proyect_loan)
	{	
		setStrFecha(date_format.format(proyect_loan.getDay_published()));
		setMonto   (number_format.format(proyect_loan.getAmmount()));	
		
		setBcScore  (proyect_loan.getBc_score() + "");
		setCalif    (proyect_loan.getKubo_score_a() + proyect_loan.getKubo_score_b());
		setKuboScore(proyect_loan.getKubo_score_a() + proyect_loan.getKubo_score_b());
		setTasa     (proyect_loan.getRate() + "");
		setComision (proyect_loan.getOpening_commission() + "");
		setUrlImg   (proyect_loan.getStatusProyect().getUrl_img());
		
		setPlazo(getPlazo(proyect_loan));		
	}
	
	protected void asignarPromotorName(Membership acreditado, boolean tiene_promotor)
	{
		if(tiene_promotor)
		{
			setPromotorName(acreditado.getPromotor().getName());
		} else {
			setPromotorName("Sin Promotor");
		}
	}
	
	protected final void asignarNotasRechazo(ProyectLoan proyect_loan)
	{
		tipo_ultima_nota = service_notas.getTipo_ultima_nota(proyect_loan);
		
		company_id         = proyect_loan.getProyectloanPk().getCompany_id();		
		motivo_id_selected = proyect_loan.getMotive_id();				
		
		titulo_estatus_credito = "Crédito Rechazado";
		estatus_credito        = "RECHAZADO";
		lista_notas            = null;
		fecha_pospuesta        = null;
		
		lista_notas = new ArrayList<Notes>();				

		switch(tipo_ultima_nota)
		{
			case NOTA_RECHAZO: 
				titulo_estatus_credito = "Crédito Rechazado";
				estatus_credito        = "RECHAZADO";
				
				estatus_PK = new StatusProyectCatPK(CANCELADO, company_id);
				estatus    = service_estatus.getStatus_by_PK(estatus_PK);				
				url        = estatus.getUrl_img();
				
				setUrlImg(url);
				
				proyect_loan.setStatus_id(CANCELADO.getId());
				service_proyect_loan.update(proyect_loan);
				
				asignar_motive_catalog_description(motivo_id_selected);
				
				lista_notas.add(service_notas.getUltima_nota(proyect_loan));
			break;
			
			case NOTA_POSPUESTO:
				titulo_estatus_credito = "Crédito Pospuesto";
				estatus_credito        = "POSPUESTO";
				
				estatus_PK = new StatusProyectCatPK(POSPUESTO, company_id);
				estatus    = service_estatus.getStatus_by_PK(estatus_PK);				
				url        = estatus.getUrl_img();
				
				setUrlImg(url);
				
				proyect_loan.setStatus_id(POSPUESTO.getId());
				service_proyect_loan.update(proyect_loan);
				
				asignar_motive_catalog_description(motivo_id_selected);
				
				fecha_pospuesta = date_format.format(proyect_loan.getPosposed_date());
				
				lista_notas.add(service_notas.getUltima_nota(proyect_loan));
			break;
			
			case NOTA_DESISTIDO:
				titulo_estatus_credito = "Crédito Desistido";
				estatus_credito        = "DESISTIDO";				
				
				estatus_PK = new StatusProyectCatPK(DESISTIDO, company_id);
				estatus    = service_estatus.getStatus_by_PK(estatus_PK);				
				url        = estatus.getUrl_img();
				
				setUrlImg(url);
				
				proyect_loan.setStatus_id(DESISTIDO.getId());
				service_proyect_loan.update(proyect_loan);
				
				asignar_motive_catalog_description(motivo_id_selected);
				
				lista_notas.add(service_notas.getUltima_nota(proyect_loan));
			break;
			
			default: break;
		}					
	}
	
	protected void asignar_motive_catalog_description(Integer motivo_id_selected) 
	{
		motive_PK = new MotivePK();
		motive_PK.setCompany_id(company_id);
		motive_PK.setMotive_id(motivo_id_selected);
		
		motive = service_motive.getMotiveByPK(motive_PK);		
		
		motive_catalog_description = motive.getDescription(); 
	}
	
/*	
	private void asignar_label_cambio_de_estatus()
	{			
		afected_table = new String[]{"ln_proyect_loan"};
		afected_field = new String[]{"status_id"};
		
		change_control_LAST = service_change_control.getChangeControlByProspectByAfectedTablesFields(prospectus_id, company_id, afected_table, afected_field);
		
		estatus_original = change_control_LAST.getOriginal_value();
	}
*/	
}
