package mx.com.kubo.managedbeans;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Offer;
import mx.com.kubo.model.OfferRejectionMotive;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.segment.SegmentProspectus;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.OfferRejectionMotiveService;
import mx.com.kubo.services.OfferService;
import mx.com.kubo.services.OfferValuesService;
import mx.com.kubo.services.PromoParametersService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ScreenService;
import mx.com.kubo.services.SegmentActionService;
import mx.com.kubo.services.SegmentProspectusService;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;

public class CheckScoreProcessedDMO {
	
	protected final int SEGMENT_TYPE_BURO = 1;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringservice;
	
	@ManagedProperty("#{segmentProspectusServiceImp}")
	protected SegmentProspectusService   segmentprospectusservice;
	
	@ManagedProperty("#{segmentActionServiceImp}")
	protected SegmentActionService segmentactionservice;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{screenServiceImp}")
	protected ScreenService screenservice;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{offerServiceImp}")
	protected OfferService offer_service;
	
	@ManagedProperty("#{offerValuesServiceImp}")
	protected OfferValuesService offer_values_service;
	
	@ManagedProperty("#{promoParametersServiceImp}")
	protected PromoParametersService promo_parameters_service;
	
	@ManagedProperty("#{offerRejectionMotiveServiceImp}")
	protected OfferRejectionMotiveService offerrejectionmotiveservice;
	
	@ManagedProperty("#{service_catalogos}")
	protected ServiceCatalogosIMO service_catalogos;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	protected boolean checkEnabled;
	protected SessionBean sesion;
	
	protected Offer offer;
	
	protected ELContext elContext;
	
	protected boolean canCheckScore = false;
	
	protected boolean enabledSegment = false;
	
	protected Scoring score;
	
	protected Screen screen;
	
	protected SegmentProspectus segment;
	
	protected Membership member;
	
	protected String partner_id;
	
	protected List< SegmentProspectus > segmentList;
	
	protected String[] promoValues;
	
	protected boolean flagRepeat = false;
	protected String pageSend = "tmp1";
	protected boolean isvalid = false;
	
	protected String userName;
	
	protected int offerType;
	protected boolean btnOffer = false;
	
	protected String pagePromo = "tmp1";
	protected String pageBtn = "tmp1";
	
	
	protected String titlePromo = "";
	
	protected Integer prospectus_id;
	protected Integer company_id;
	
	protected  Locale locale;
	protected  NumberFormat dec;
	
	protected List<OfferRejectionMotive> lstmotive;
	protected Integer motive_id = 1;
	protected String motiveStr;
	
	
	public ScoringService getScoringservice() {
		return scoringservice;
	}

	public void setScoringservice(ScoringService scoringservice) {
		this.scoringservice = scoringservice;
	}

	public SegmentProspectusService getSegmentprospectusservice() {
		return segmentprospectusservice;
	}

	public void setSegmentprospectusservice(
			SegmentProspectusService segmentprospectusservice) {
		this.segmentprospectusservice = segmentprospectusservice;
	}

	public boolean isCheckEnabled() {
		return checkEnabled;
	}

	public void setCheckEnabled(boolean checkEnabled) {
		this.checkEnabled = checkEnabled;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public ELContext getElContext() {
		return elContext;
	}

	public void setElContext(ELContext elContext) {
		this.elContext = elContext;
	}

	public boolean isCanCheckScore() {
		return canCheckScore;
	}

	public void setCanCheckScore(boolean canCheckScore) {
		this.canCheckScore = canCheckScore;
	}

	public boolean isEnabledSegment() {
		return enabledSegment;
	}

	public void setEnabledSegment(boolean enabledSegment) {
		this.enabledSegment = enabledSegment;
	}

	public Scoring getScore() {
		return score;
	}

	public void setScore(Scoring score) {
		this.score = score;
	}

	public SegmentProspectus getSegment() {
		return segment;
	}

	public void setSegment(SegmentProspectus segment) {
		this.segment = segment;
	}

	public SegmentActionService getSegmentactionservice() {
		return segmentactionservice;
	}

	public void setSegmentactionservice(SegmentActionService segmentactionservice) {
		this.segmentactionservice = segmentactionservice;
	}

	public String getPageSend() {
		return pageSend;
	}

	public void setPageSend(String pageSend) {
		this.pageSend = pageSend;
	}

	public boolean isIsvalid() {
		return isvalid;
	}

	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}

	public ScreenService getScreenservice() {
		return screenservice;
	}

	public void setScreenservice(ScreenService screenservice) {
		this.screenservice = screenservice;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public OfferService getOffer_service() {
		return offer_service;
	}

	public void setOffer_service(OfferService offer_service) {
		this.offer_service = offer_service;
	}

	public OfferValuesService getOffer_values_service() {
		return offer_values_service;
	}

	public void setOffer_values_service(OfferValuesService offer_values_service) {
		this.offer_values_service = offer_values_service;
	}

	public String[] getPromoValues() {
		return promoValues;
	}

	public void setPromoValues(String[] promoValues) {
		this.promoValues = promoValues;
	}

	public int getOfferType() {
		return offerType;
	}

	public void setOfferType(int offerType) {
		this.offerType = offerType;
	}

	public boolean isBtnOffer() {
		return btnOffer;
	}

	public void setBtnOffer(boolean btnOffer) {
		this.btnOffer = btnOffer;
	}

	public String getPagePromo() {
		return pagePromo;
	}

	public void setPagePromo(String pagePromo) {
		this.pagePromo = pagePromo;
	}

	public String getPageBtn() {
		return pageBtn;
	}

	public void setPageBtn(String pageBtn) {
		this.pageBtn = pageBtn;
	}

	public PromoParametersService getPromo_parameters_service() {
		return promo_parameters_service;
	}

	public void setPromo_parameters_service(PromoParametersService promo_parameters_service) {
		this.promo_parameters_service = promo_parameters_service;
	}

	public ServiceCatalogosIMO getService_catalogos() {
		return service_catalogos;
	}

	public void setService_catalogos(ServiceCatalogosIMO service_catalogos) {
		this.service_catalogos = service_catalogos;
	}

	public Integer getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getTitlePromo() {
		return titlePromo;
	}

	public void setTitlePromo(String titlePromo) {
		this.titlePromo = titlePromo;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public List<OfferRejectionMotive> getLstmotive() {
		return lstmotive;
	}

	public void setLstmotive(List<OfferRejectionMotive> lstmotive) {
		this.lstmotive = lstmotive;
	}

	public String getMotiveStr() {
		return motiveStr;
	}

	public void setMotiveStr(String motiveStr) {
		this.motiveStr = motiveStr;
	}

	public Integer getMotive_id() {
		return motive_id;
	}

	public void setMotive_id(Integer motive_id) {
		this.motive_id = motive_id;
	}

	public OfferRejectionMotiveService getOfferrejectionmotiveservice() {
		return offerrejectionmotiveservice;
	}

	public void setOfferrejectionmotiveservice(OfferRejectionMotiveService offerrejectionmotiveservice) {
		this.offerrejectionmotiveservice = offerrejectionmotiveservice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ProyectLoanService getProyectLoanService() {
		return proyectLoanService;
	}

	public void setProyectLoanService(ProyectLoanService proyectLoanService) {
		this.proyectLoanService = proyectLoanService;
	}

	public Membership getMember() {
		return member;
	}

	public void setMember(Membership member) {
		this.member = member;
	}
	

}
