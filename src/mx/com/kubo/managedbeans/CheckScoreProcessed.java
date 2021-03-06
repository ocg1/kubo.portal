package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Offer;
import mx.com.kubo.model.OfferValues;
import mx.com.kubo.model.Promo;
import mx.com.kubo.model.PromoPK;
import mx.com.kubo.model.PromoParameters;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.model.SegmentAction;
import mx.com.kubo.tools.Utilities;


@ManagedBean
@SessionScoped
public class CheckScoreProcessed extends CheckScoreProcessedDMO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init(){
		
		//System.out.println( "CheckScoreProcessed.init() OK" );
		
		elContext = FacesContext.getCurrentInstance().getELContext();
		sesion    = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");
		
		locale = new Locale("es","mx");
		dec = NumberFormat.getCurrencyInstance(locale);
		
		lstmotive = offerrejectionmotiveservice.getOfferRejectionMotiveList();
		
		pageSend  = "tmp1";
		
		if(sesion != null && sesion.getProspectus_id() != null && sesion.getCompany_id() != null ){
		
			prospectus_id = sesion.getProspectus_id();
			company_id =  sesion.getCompany_id();
			
			MembershipPK mpk = new MembershipPK();
			
			mpk.setCompany_id( company_id );
			mpk.setProspectus_id( prospectus_id );
			
			member = membershipService.getMembershipById(mpk);
			
			if( member!= null && member.getRegistration_reason() != null && member.getRegistration_reason().getPartner() != null && member.getRegistration_reason().getPartner().getPartnerPK() != null  ){
				
				partner_id = member.getRegistration_reason().getPartner().getPartnerPK().getPartner_id();
				
			}else{
				
				partner_id = "SIN_PARTNER";
			
			}
			
			userName = sesion.getNombre();
			
		}
		//System.out.println( "CheckScoreProcessed.init() Finalizado" );
		
	}
	
	
	public void validaMsg(){
	
		//System.out.println( "Validando Respuesta Segmento ....." );
		
		promoValues = null;
		
		RequestContext request   = RequestContext.getCurrentInstance();
		
		checkEnabledScore();
		
		if(score != null){
			
			checkEnabledSegment();
			
			if( (score.getRisk_processed()  == null || score.getRisk_processed().intValue() == 0)  ){
				flagRepeat = true;
				pageSend = "tmp1";
				isvalid = false;
				
				if( request != null ){
					request.addCallbackParam("flagRepeat", flagRepeat);
					request.addCallbackParam("pageSend", pageSend);
					request.addCallbackParam("isvalid", isvalid);
				}
				
				return;
			}
			
			if( canCheckScore  ){
			
				if( enabledSegment ){
					
					if( score.getScreen_viewed().intValue() == 0 ){
					
						flagRepeat = false;
						
						screen = null;
						
						SegmentAction segment_action =  generateSegmentActionScreen();
						
						if( screen != null ){
							pageSend = screen.getName();
						}
						
						if( segment_action != null ){
							isvalid = segment_action.getEnabled() == 1;
						}else{
							
						}
					
						//TODO  ACTUALIZAR SIMULADOR
						try{
						
							Thread.sleep(3000);
							
						}catch(Exception e){
							
							e.printStackTrace();
							
						}
						
						elContext = FacesContext.getCurrentInstance().getELContext();
						
						
						score = scoringservice.loadMaxScoringByProspectus(getProspectus_id(), getCompany_id());
						
						if( !sesion.getArea().toString().equals("M") ){
							
							Simulator sim    = (Simulator) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "simulator");
							
							sim.setTasaTotal(score.getRate());
							sim.setComisionApertura(score.getOpening_commission());
							
							sim.getSesion().setOpeningCommission(score.getOpening_commission());
							sim.getSesion().setRate(score.getRate());
						
							sim.simulaCred(false);
						}
						
						
						
						if( sesion.getArea().toString().equals("L") ){
						
							request.addPartialUpdateTarget("pnlSimuladorLienzo");
						
						}
						
						
					}else{
						flagRepeat = false;
						pageSend = "tmp1";
						isvalid = false;
					}
					
				}else{
					
					flagRepeat = false;
					pageSend = "tmp1";
					isvalid = false;
				
				}
			
			}else{
				
				flagRepeat = false;
				pageSend = "tmp1";
				isvalid = false;
				
			}
			
			checkUserPromo( score );
			
		}else{
			
			flagRepeat = false;
			pageSend = "tmp1";
			isvalid = false;
			
		}
		
		
		
		if( request != null ){
			request.addCallbackParam("flagRepeat", flagRepeat);
			request.addCallbackParam("pageSend", pageSend);
			request.addCallbackParam("isvalid", isvalid);
		}
		
	}
	
	
	private void checkEnabledScore(){
		
		if( getProspectus_id() != null && getCompany_id() != null ){
		
			score = scoringservice.loadMaxScoringByProspectus(getProspectus_id(), getCompany_id());
			
			if( score != null && score.getRisk_processed() != null && score.getRisk_processed().intValue() == 1 && score.getScreen_viewed() != null && score.getScreen_viewed().intValue() == 0 ){
				
				canCheckScore = true;
				
			}else{
				canCheckScore = false;
			}
		
		}else{
			
			canCheckScore = false;
			score = null;
		}
		
	}
	
	private void checkEnabledSegment(){
		
		if( getProspectus_id() != null && getCompany_id() != null ){
			
			segmentList = segmentprospectusservice.loadSegmentProspectListByType(getProspectus_id(), getCompany_id(), SEGMENT_TYPE_BURO );
			
			if( segmentList != null && segmentList.size() > 0 ){
				//nos traemos el primer segmento, ya que para este procedimiento solo se trae un registro por segment_type = 1
				
				segment = segmentList.get(0);
				
				if(segment.getSegment().getEnabled() == 1){
					
					enabledSegment = true;
					
				}else{
					enabledSegment = false;
				}
				
				sesion.setSegment_name( segment.getSegment().getName() );
				sesion.setKubo_score( score.getKubo_score_a() + score.getKubo_score_b() );
				
				
			}else{
				enabledSegment = true;
			}
		
		}else{
			enabledSegment = false;
		}
		
	}
	
	private SegmentAction generateSegmentActionScreen(){
		
		SegmentAction sa = segmentactionservice.getSegmentActionBySegment( segment.getPk().getSegment_id(), getCompany_id(), 1 );
		
		if ( sa != null ) {
			
			ScreenPK spk = new  ScreenPK();
			
			spk.setCompany_id(getCompany_id());
			spk.setScreen_id(Integer.parseInt(sa.getSegment_action()));
			
			screen = screenservice.getScreenById(spk);
			
		}
		
		
		return sa;
		
	}
	
	public void actualizaViewScreen(){
		
		score = scoringservice.loadMaxScoringByProspectus(getProspectus_id(), getCompany_id());
		score.setScreen_viewed(1);
		scoringservice.updateScoring(score);
		
		if( screen != null ){
		
			Access accessTmp = accessService.getMaxAccess(getProspectus_id(), getCompany_id() );
			
			Access access = new Access();
			
			access.setCompany_id(sesion.getCompany_id());
			access.setProspectus_id(sesion.getProspectus_id());
			access.setScreen_id(screen.getScreenPK().getScreen_id());
			access.setPercentage(0);
			access.setWeb_browser(sesion.getNamebrawser());
			access.setWeb_browser_version(sesion.getVersionbrawser());
			access.setOp_system(sesion.getOsbrawser());
			access.setHorizontal_size(sesion.getBrowser_width());
			access.setVertical_size(sesion.getBrowser_height());
			access.setIpaddress(sesion.getIP_address_client());
			access.setUser_agent(sesion.getUser_agent());
			access.setDevice_info(sesion.getDevice_info());
			access.setUrl_access( sesion.getUrl_access() );
			access.setProspectus_id_coach(sesion.getCoachProspectus_id());
			
			access.setIpaddress(sesion.getIP_address_client());
			
			access.setAccess_datetime(new Date());
			
			access.setProspectus_id_coach (sesion.getCoachProspectus_id());
			access.setAccess_from		  (sesion.getAccess_from());
			access.setVersion_description (sesion.getVersion_description());
			
			accessService.add(access, true);
			
			Access accessTmp2 = getCloneAccess( accessTmp );
			
			accessService.add( accessTmp2, true );
			
			if( btnOffer ){
				
				updateShowed1OfferDate();
				
			}
		
		}
		
		//Registra en PRACCESS
	}
	
	private Access getCloneAccess( Access accessTmp2 ){
		
		Access access = new Access();
		
		access.setCompany_id(accessTmp2.getCompany_id());
		access.setProspectus_id(accessTmp2.getProspectus_id());
		access.setScreen_id(accessTmp2.getScreen_id());
		access.setPercentage(accessTmp2.getPercentage());
		access.setWeb_browser(accessTmp2.getWeb_browser());
		access.setWeb_browser_version(accessTmp2.getWeb_browser_version());
		access.setOp_system(accessTmp2.getOp_system());
		access.setHorizontal_size(accessTmp2.getHorizontal_size());
		access.setVertical_size(accessTmp2.getVertical_size());
		access.setIpaddress(accessTmp2.getIpaddress());
		access.setUser_agent(accessTmp2.getUser_agent());
		access.setDevice_info(accessTmp2.getDevice_info());
		access.setUrl_access( sesion.getUrl_access() );
		access.setProspectus_id_coach(accessTmp2.getProspectus_id_coach());
		
		access.setIpaddress(accessTmp2.getIpaddress());
		
		access.setAccess_datetime(new Date());
		
		access.setProspectus_id_coach (accessTmp2.getProspectus_id_coach());
		access.setAccess_from		  (accessTmp2.getAccess_from());
		access.setVersion_description (accessTmp2.getVersion_description());
		
		return access;
		
	}
	
	private void checkUserPromo( Scoring score ){
		
		pagePromo =  "tmp1.xhtml";
		pageBtn =  "tmp1.xhtml";
		offerType = 0;
		titlePromo ="";
		
		offer = offer_service.getOfferByBurSolNum(score.getMx_solicitud_buro());
		
		if( offer != null ){
			
			List<OfferValues> values =  offer_values_service.getOfferValuesByOfferId( offer.getOffer_id() );
			
			PromoPK ppk = new PromoPK();
			
			ppk.setCompany_id(score.getCompany_id());
			ppk.setPromo_id(offer.getPromo_id());
			
			Promo promo = service_catalogos.getPromoByPK( ppk );
			
			if( ( sesion.getArea() != null && sesion.getArea().toString().equals("M") ) || ( promo.getIs_enabled() != null && promo.getIs_enabled().equals("1") )  ){
			
			titlePromo = promo.getDescription();
			
			List<PromoParameters> parameters = promo_parameters_service.getPromoParametersByCode( promo.getCode() );
			
			if( values != null && values.size() > 0 ){
				
				promoValues = new String[values.size()];
				
				int i = 0;
				
				for( OfferValues val : values ){
					
					if(parameters.get(i).getType() != null && parameters.get(i).getType().equals("moneda")){
					
						String str = dec.format( ( Double.parseDouble( val.getValue().replaceAll(",", "") ) )  );
						
						promoValues[i] = str;
						
					}else if(parameters.get(i).getType() != null && parameters.get(i).getType().equals("porcentaje")){
					
						Double str = Utilities.redondearDecimales( ( Double.parseDouble( val.getValue().replaceAll(",", "")  ) * 100 ), 2) ;
						
						promoValues[i] = str+"";
						
					}else{
					
						promoValues[i] = val.getValue();
					
					}
					
					
					
					if( ( i == 4 || i == 11 ) && promo.getPk().getPromo_id() != 5 && promo.getPk().getPromo_id() != 9 &&  promo.getPk().getPromo_id() != 10 && promo.getPk().getPromo_id() != 18 && promo.getPk().getPromo_id() != 14 && promo.getPk().getPromo_id() != 13 && promo.getPk().getPromo_id() != 15 && promo.getPk().getPromo_id() != 19 ){
						
						promoValues[i] =( Double.parseDouble( val.getValue() ) * score.getOpening_commission() ) +"";
					}
					
					i++;
				}
				
				offerType = offer.getPromo_id();
				btnOffer = true;
				getPage(offerType);
				
			}else{
				btnOffer = false;
				offerType = 0;
				getPage(offerType);
			}
			
			}else{
				
				btnOffer = false;
				offerType = 0;
				getPage(offerType);
				
			}
				
		}else{
			btnOffer = false;
			offerType = 0;
			getPage(offerType);
		}
		
	}
	
	private void getPage( int offerType){
		
		
		switch( offerType ){
		
			case 5: // KUBO TE PREMIA Monto
				
				pagePromo 	=  "nuevosProductos/2_KuboTePremia.xhtml";
				pageBtn 	=  "tmp1.xhtml";
				
				break;
		
			case 9: // KUBO TE PREMIA Monto
				
				pagePromo 	=  "nuevosProductos/2_premiaMontoDetalle.xhtml";
				pageBtn 	=  "nuevosProductos/1_PremioSaldoMonto.xhtml";
				
				break;
				
			case 10: // KUBO TE PREMIA Saldo
				
				pagePromo 	=  "nuevosProductos/2_premiaSaldoDetalle.xhtml";
				pageBtn 	=  "nuevosProductos/1_PremioSaldoMonto.xhtml";
				
				break;
				
			case 11: // Con Kubo paga menos - Sin comision
				
				pagePromo 	=  "nuevosProductos/2_detalleComision.xhtml";
				pageBtn 	=  "nuevosProductos/1_Comision.xhtml"; //botones
				
				break;
				
			case 12: // Con Kubo paga menos - Con comision
				
				pagePromo 	=  "nuevosProductos/2_detalleComision.xhtml";
				pageBtn 	=  "nuevosProductos/1_Comision.xhtml";
				
				break;
				
			case 13: // KUBO TE PREMIA Monto
				
				pagePromo 	=  "nuevosProductos/2_KuboTePremia.xhtml";
				pageBtn 	=  "tmp1.xhtml";
				
				break;
		
			case 14: // KUBO TE PREMIA Monto
				
				pagePromo 	=  "nuevosProductos/2_premiaMontoDetalle.xhtml";
				pageBtn 	=  "nuevosProductos/1_PremioSaldoMonto.xhtml";
				
				break;
				
			case 15: // KUBO TE PREMIA Saldo
				
				pagePromo 	=  "nuevosProductos/2_premiaSaldoDetalle.xhtml";
				pageBtn 	=  "nuevosProductos/1_PremioSaldoMonto.xhtml";
				
				break;
				
			case 16: // Con Kubo paga menos - Sin comision
				
				pagePromo 	=  "nuevosProductos/2_detalleComision.xhtml";
				pageBtn 	=  "nuevosProductos/1_Comision.xhtml"; //botones
				
				break;
				
			case 17: // Con Kubo paga menos - Con comision
				
				pagePromo 	=  "nuevosProductos/2_detalleComision.xhtml";
				pageBtn 	=  "nuevosProductos/1_Comision.xhtml";
				
				break;
				
			case 18: // KUBO TE PREMIA Monto
				
				pagePromo 	=  "nuevosProductos/2_premiaMontoDetalle.xhtml";
				pageBtn 	=  "nuevosProductos/1_PremioSaldoMonto.xhtml";
				
				break;
				
			case 19: // KUBO TE PREMIA Saldo
				
				pagePromo 	=  "nuevosProductos/2_premiaSaldoDetalle.xhtml";
				pageBtn 	=  "nuevosProductos/1_PremioSaldoMonto.xhtml";
				
				break;
				
			case 20: // Con Kubo paga menos - Sin comision
				
				pagePromo 	=  "nuevosProductos/2_detalleComision.xhtml";
				pageBtn 	=  "nuevosProductos/1_Comision.xhtml"; //botones
				
				break;
				
			case 21: // Con Kubo paga menos - Con comision
				
				pagePromo 	=  "nuevosProductos/2_detalleComision.xhtml";
				pageBtn 	=  "nuevosProductos/1_Comision.xhtml";
				
				break;
				
			default :
				
				pagePromo =  "tmp1.xhtml";
				pageBtn =  "tmp1.xhtml";
				
				break;
		
		}
		
		
	}
	
	public void updateShowed1OfferDate(){
		
		Offer offer = offer_service.getOfferByBurSolNum(score.getMx_solicitud_buro());
		
		offer.setShowed1_date(new Date());
		
		offer_service.updateOffer(offer);
	}
	
	public void updateShowed2OfferDate(){
		
		Offer offer = offer_service.getOfferByBurSolNum(score.getMx_solicitud_buro());
		
		offer.setShowed2_date(new Date());
		
		offer_service.updateOffer(offer);
	}
	
	public void updateRejection1OfferDate(){
		
		Offer offer = offer_service.getOfferByBurSolNum(score.getMx_solicitud_buro());
		
		offer.setRejection1_date(new Date());
		
		offer_service.updateOffer(offer);
	}
	
	public void updateRejection2OfferDate(){
		
		Offer offer = offer_service.getOfferByBurSolNum(score.getMx_solicitud_buro());
		
		offer.setRejection2_date(new Date());
		
		offer_service.updateOffer(offer);
	}
	
	public void updateAccepted1OfferDate(){
		
		Offer offer = offer_service.getOfferByBurSolNum(score.getMx_solicitud_buro());
		
		offer.setAccepted1_date(new Date());
		
		offer_service.updateOffer(offer);
	}
	
	public void updateAcceptedOffer_1(){
		
		Offer offer = offer_service.getOfferByBurSolNum(score.getMx_solicitud_buro());
		
		offer.setAccepted2_date(new Date());
		offer.setOption_selected("1");
		
		offer_service.updateOffer(offer);
		
	}
	
	public void updateAcceptedOffer_2(){
		
		Offer offer = offer_service.getOfferByBurSolNum(score.getMx_solicitud_buro());
		
		offer.setAccepted2_date(new Date());
		offer.setOption_selected("2");
		
		offer_service.updateOffer(offer);
		
	}
	
	public void updateMotiveRejection(){
		
		Offer offer = offer_service.getOfferByBurSolNum(score.getMx_solicitud_buro());
		
		
		if(motive_id == 0){
			
			offer.setOffer_rejection_motive_id(null);
			offer.setOther_rejection_motive(null);
			
		}else if(motive_id == 1){
			
			offer.setOffer_rejection_motive_id(motive_id);
			offer.setOther_rejection_motive(motiveStr);
			
		}else{
			
			offer.setOffer_rejection_motive_id(motive_id);
			offer.setOther_rejection_motive(null);
			
		}
		
		
		offer_service.updateOffer(offer);
		
	}
	
	
	public String getEmailEncodemd5(){
		
		if( member != null && member.getEmail() != null ){
		
			return Utilities.md5_encode(member.getEmail());
		
		}else{
			return "";
		}
				
	}
	
	
}
