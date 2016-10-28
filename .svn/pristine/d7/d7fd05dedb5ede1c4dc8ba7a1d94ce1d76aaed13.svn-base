package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.ProfileCategoryInv;
import mx.com.kubo.model.ProfileFormValue;
import mx.com.kubo.model.ProfileFormValuePK;
import mx.com.kubo.model.ProfileInv;
import mx.com.kubo.model.ProfileInvPK;
import mx.com.kubo.model.ProfileInvestForm;
import mx.com.kubo.model.investor.OtherinvestmentCat;
import mx.com.kubo.services.ProfileCategoryService;
import mx.com.kubo.services.ProfileFormValueService;
import mx.com.kubo.services.ProfileInvService;
import mx.com.kubo.services.ProfileInvestFormService;


@ManagedBean
@ViewScoped
public class ProfileInvestment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{profileCategoryServiceImp}")
	private ProfileCategoryService profilecategoryservice;
	
	@ManagedProperty("#{profileFormValueServiceImp}")
	private ProfileFormValueService profileformvalueservice;
	
	@ManagedProperty("#{profileInvestFormServiceImp}")
	private ProfileInvestFormService profileformservice;
	
	@ManagedProperty("#{profileInvServiceImp}")
	private ProfileInvService profileinvservice;
	
	private List<ProfileCategoryInv> lstprofilecategory;
	
	private SessionBean sesion;
	
	private List<ProfileFormValue> profileformvaluelist;
	
	private List<ProfileInvestForm> profileformlist;
	
	private List<OtherinvestmentCat> otherInvestmentlist;
	
	private String otherInvestmentDetail;
	
	private String valueClick;
	
	private List<Integer[]> groups;
	
	private Integer totalPoints;
	
	private String profileCalc;
	
	private String scriptChecks;
	
	private ProfileInv profile;
	
	private String valueClickInvestProfile;
	
	@PostConstruct
	public void init(){
		
		initGroups();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		sesion = (SessionBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "sessionBean");
		
		lstprofilecategory = profilecategoryservice.getListProfileCategory();
		
		profileformvaluelist = profileformvalueservice.getProfileFormValueListByProspectus( sesion.getProspectus_id(), sesion.getCompany_id() );
		
		profileformlist = profileformservice.getProfileInvestFormList();
		
		profile =  profileinvservice.getProfileInvByProspectus(sesion.getProspectus_id(), sesion.getCompany_id());
		
		otherInvestmentlist = profileinvservice.getOtherinvestmentCat();
		
		if( profile == null ){
			saveProfile();
		}
		
		
		if( profileformvaluelist == null || profileformvaluelist.size()==0 ){
			insertForm();
		}
		
		calculaTotalPoints();
		calculaPerfil();
		initchecks();
		
	}
	
	private void calculaTotalPoints(){
		totalPoints = 0;
		for( ProfileFormValue formValue : profileformvaluelist ){
			
			if( formValue.getValue().equals("1") ){
				
				totalPoints += formValue.getProfile_form().getPoints();
				
			}
			
		}
		
		profile.setTotal_points(totalPoints);
		profileinvservice.updateProfileInv(profile);
		
	}
	
	public Integer getPoints(Integer form_id){
		
		Integer points = 0;
		
		for( ProfileInvestForm tmp : profileformlist ){
		
			if(tmp.getPk().getProfile_form_id() == form_id){
			
				points = tmp.getPoints();
				break;
			
			}
		
		}
		
		return points;
	
	}
	
	public void clickEvent(){
		
		System.out.println( " ---- "+valueClick+" ---- " );
		
		Integer form_id = Integer.parseInt( valueClick.split("::")[0] );
		boolean flag = ( valueClick.split("::")[1] ).equals( "true" );
		
		if( form_id == 16){
			resetField1(flag);
			return;
		}
		
		
		Integer[] group = null;
		
		
		for(Integer[] tmp : groups ){
			
			for(Integer t : tmp){
				if( t==form_id ){
					group = tmp;
					break;
				}
			}
			
			if(group!=null)
				break;
			
		}
		
		
		if(form_id < 31){
		
			if(group != null)
				resetValues(group,form_id,flag);
		
		}else{
			Integer[] array = {};
			resetValues(array,form_id,flag);
		}
		actualizaValues();
		
	}

	public ProfileCategoryService getProfilecategoryservice() {
		return profilecategoryservice;
	}

	public void setProfilecategoryservice(
			ProfileCategoryService profilecategoryservice) {
		this.profilecategoryservice = profilecategoryservice;
	}

	public List<ProfileCategoryInv> getLstprofilecategory() {
		return lstprofilecategory;
	}

	public void setLstprofilecategory(List<ProfileCategoryInv> lstprofilecategory) {
		this.lstprofilecategory = lstprofilecategory;
	}

	public ProfileFormValueService getProfileformvalueservice() {
		return profileformvalueservice;
	}

	public void setProfileformvalueservice(
			ProfileFormValueService profileformvalueservice) {
		this.profileformvalueservice = profileformvalueservice;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public List<ProfileFormValue> getProfileformvaluelist() {
		return profileformvaluelist;
	}

	public void setProfileformvaluelist(List<ProfileFormValue> profileformvaluelist) {
		this.profileformvaluelist = profileformvaluelist;
	}

	public ProfileInvestFormService getProfileformservice() {
		return profileformservice;
	}

	public void setProfileformservice(ProfileInvestFormService profileformservice) {
		this.profileformservice = profileformservice;
	}

	public List<ProfileInvestForm> getProfileformlist() {
		return profileformlist;
	}

	public void setProfileformlist(List<ProfileInvestForm> profileformlist) {
		this.profileformlist = profileformlist;
	}

	public String getValueClick() {
		return valueClick;
	}

	public void setValueClick(String valueClick) {
		this.valueClick = valueClick;
	}
	
	public void insertForm(){
		
	 	for ( ProfileInvestForm form : profileformlist ){
	 		ProfileFormValue values = new ProfileFormValue();
	 		
	 		values.setLast_update( new Date() );
	 		
	 		if( form.getPk().getProfile_form_id() > 30 ){
	 			values.setValue("1");
	 		}else{
	 			values.setValue("0");
	 		}
	 		
	 		ProfileFormValuePK fPk = new ProfileFormValuePK();
	 		
	 		fPk.setCompany_id(sesion.getCompany_id());
	 		fPk.setProspectus_id(sesion.getProspectus_id());
	 		fPk.setProfile_form_id(form.getPk().getProfile_form_id());
	 		
	 		values.setPk(fPk);
	 		
	 		profileformvalueservice.saveProfileFormValue(values);
	 		
	 		
	 		
	 	}
	 	
	 	profileformvaluelist = profileformvalueservice.getProfileFormValueListByProspectus( sesion.getProspectus_id(), sesion.getCompany_id() );
		
	}
	
	private boolean resetValues(Integer[] group,Integer form_id,boolean value){
		boolean flag = false;
		
			for(Integer id : group ){
				
				ProfileFormValue values = new ProfileFormValue();
		 		
		 		values.setLast_update( new Date() );
		 		values.setValue("0");
		 		
		 		ProfileFormValuePK fPk = new ProfileFormValuePK();
		 		
		 		fPk.setCompany_id(sesion.getCompany_id());
		 		fPk.setProspectus_id(sesion.getProspectus_id());
		 		fPk.setProfile_form_id( id );
		 		
		 		values.setPk(fPk);
				
				profileformvalueservice.updateProfileFormValue( values  );
			}
			
			if(form_id < 16 && value){
				ProfileFormValue values = new ProfileFormValue();
		 		
		 		values.setLast_update( new Date() );
		 		values.setValue("0");
		 		
		 		ProfileFormValuePK fPk = new ProfileFormValuePK();
		 		
		 		fPk.setCompany_id(sesion.getCompany_id());
		 		fPk.setProspectus_id(sesion.getProspectus_id());
		 		fPk.setProfile_form_id( 16 );
		 		
		 		values.setPk(fPk);
				
				profileformvalueservice.updateProfileFormValue( values  );
			}
			
			if(form_id > 30 ){
				ProfileFormValue values = new ProfileFormValue();
		 		
		 		values.setLast_update( new Date() );
		 		values.setValue("0");
		 		
		 		ProfileFormValuePK fPk = new ProfileFormValuePK();
		 		
		 		fPk.setCompany_id(sesion.getCompany_id());
		 		fPk.setProspectus_id(sesion.getProspectus_id());
		 		fPk.setProfile_form_id( form_id );
		 		
		 		values.setPk(fPk);
				
				profileformvalueservice.updateProfileFormValue( values  );
			}
			
			if( value ){
				
				ProfileFormValue values = new ProfileFormValue();
		 		
		 		values.setLast_update( new Date() );
		 		values.setValue("1");
		 		
		 		ProfileFormValuePK fPk = new ProfileFormValuePK();
		 		
		 		fPk.setCompany_id(sesion.getCompany_id());
		 		fPk.setProspectus_id(sesion.getProspectus_id());
		 		fPk.setProfile_form_id( form_id );
		 		
		 		values.setPk(fPk);
				
				profileformvalueservice.updateProfileFormValue( values );
				
			}
		
		return flag;
	}
	
	private void actualizaValues(){
		profileformvaluelist = profileformvalueservice.getProfileFormValueListByProspectus( sesion.getProspectus_id(), sesion.getCompany_id() );
		calculaTotalPoints();
		calculaPerfil();
	}
	
	private void calculaPerfil(){
		
		int calc = 0;
		
		for( ProfileCategoryInv cat : lstprofilecategory){
		
			if( cat.getMax_points()>=totalPoints && cat.getMin_points()<=totalPoints  ){
			
				profileCalc = cat.getProfile_category();
				calc = cat.getPk().getProfile_category_id();
				break;
			
			}
		
		}
		
		profile.setProfile_category_id(calc);
		profileinvservice.updateProfileInv(profile);
		
	}
	
	private void initchecks(){
		scriptChecks="<script>";
		boolean isHidden = false;
		for( ProfileFormValue formValue : profileformvaluelist){
			
			if( formValue.getPk().getProfile_form_id() > 30 ){
				
				if( formValue.getValue().equals("1") ){
					scriptChecks +=" $('#checkForm_"+formValue.getPk().getProfile_form_id()+"').trigger('click');"
							+ "$('#checkForm_"+formValue.getPk().getProfile_form_id()+"').closest('span').addClass('active');";
				}
				
			}else{
			
				if( formValue.getValue().equals("1") ){
					scriptChecks +=" $('.form_id_"+formValue.getPk().getProfile_form_id()+"')[0].checked=true;";
				}
			
			}
			
		}
		
		if(profile.getNo_risk() != null && profile.getNo_risk().equals("1") ){
			scriptChecks +=" $('.noRisk_yes')[0].checked=true;";
			scriptChecks +=" $('#dvResKuboImpulso').css('display','block');";
			isHidden = true;
		}
		if(profile.getNo_risk() != null && profile.getNo_risk().equals("0") ){
			scriptChecks +=" $('.noRisk_no')[0].checked=true;";
			scriptChecks +=" $('#dvResKuboImpulso').css('display','none');";
			isHidden = false;
		}
		/*
		if( isHidden ){
			if(profile.getAgree_no_risk() != null && profile.getAgree_no_risk().equals("1") ){
				scriptChecks +=" $('.Agree_no_risk')[0].checked=true;";
			}
			if(profile.getAgree_no_risk() != null && profile.getAgree_no_risk().equals("0") ){
				scriptChecks +=" $('.No_Agree_no_risk')[0].checked=true;";
			}
		}else{
			scriptChecks +=" $('.No_Agree_no_risk')[0].checked=false;";
			scriptChecks +=" $('.Agree_no_risk')[0].checked=false;";
		}
		*/
		scriptChecks+="</script>";
	}
	
	private void resetField1(boolean flag){
		Integer[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		resetValues(array,16,flag);
		actualizaValues();
	}

	private void initGroups(){
		
		groups = new ArrayList<Integer[]>();
		
		Integer[] array = {1,2,3};
		groups.add(array);
		
		Integer[] array1 = {4,5,6};
		groups.add(array1);
		
		Integer[] array2 = {7,8,9};
		groups.add(array2);
		
		Integer[] array3 = {10,11,12};
		groups.add(array3);
		
		Integer[] array4 = {13,14,15};
		groups.add(array4);
		
		Integer[] array5 = {16};
		groups.add(array5);
		
		Integer[] array6 = {17,18,19};
		groups.add(array6);
		
		Integer[] array7 = {20,21,22,23};
		groups.add(array7);
		
		Integer[] array8 = {24,25,26,27};
		groups.add(array8);
		
		Integer[] array9 = {28,29,30};
		groups.add(array9);
		
		
	}
	
	public void clickProfileInve(){
		System.out.println("  -----    "+valueClickInvestProfile+"   ----   ");
		
		String element = valueClickInvestProfile.split("::")[0];
		boolean valor = (valueClickInvestProfile.split("::")[1]).equals("true");
		
		boolean flag = false;
		
		if(element.equals("noRisk_yes") && valor){
			profile.setNo_risk("1");
		}
		if(element.equals("noRisk_yes") && !valor){
			profile.setNo_risk("0");
			flag = true;
		}
		if(element.equals("noRisk_no")){
			profile.setNo_risk("0");
			flag = true;
		}
		if(!flag){
			if(element.equals("Agree_no_risk") && valor){
				profile.setAgree_no_risk("1");
			}
			if(element.equals("Agree_no_risk") && !valor){
				profile.setAgree_no_risk("0");
			}
			if(element.equals("No_Agree_no_risk")){
				profile.setAgree_no_risk("0");
			}
		}else{
			profile.setAgree_no_risk("0");
		}
		updateProfile();
		
	}
	
	public void updateProfile(){
		
		if( profile.getOther_investment_id() != null && profile.getOther_investment_id() != 4 ){
			profile.setOther_investment_description( null) ;
		}
		
		profileinvservice.updateProfileInv(profile);
	}
	
	private void saveProfile(){
		
		profile = new ProfileInv();
		
		profile.setCreation_date(new Date());
		
		ProfileInvPK pk = new ProfileInvPK();
		
		pk.setProspectus_id(sesion.getProspectus_id());
		pk.setCompany_id(sesion.getCompany_id());
		
		profile.setPk(pk);
		profile.setNo_risk("0");
		profile = profileinvservice.saveProfileInv(profile);
		
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	public String getProfileCalc() {
		return profileCalc;
	}

	public void setProfileCalc(String profileCalc) {
		this.profileCalc = profileCalc;
	}

	public String getScriptChecks() {
		return scriptChecks;
	}

	public void setScriptChecks(String scriptChecks) {
		this.scriptChecks = scriptChecks;
	}

	public ProfileInvService getProfileinvservice() {
		return profileinvservice;
	}

	public void setProfileinvservice(ProfileInvService profileinvservice) {
		this.profileinvservice = profileinvservice;
	}

	public ProfileInv getProfile() {
		return profile;
	}

	public void setProfile(ProfileInv profile) {
		this.profile = profile;
	}

	public String getValueClickInvestProfile() {
		return valueClickInvestProfile;
	}

	public void setValueClickInvestProfile(String valueClickInvestProfile) {
		this.valueClickInvestProfile = valueClickInvestProfile;
	}

	public List<OtherinvestmentCat> getOtherInvestmentlist() {
		return otherInvestmentlist;
	}

	public void setOtherInvestmentlist(List<OtherinvestmentCat> otherInvestmentlist) {
		this.otherInvestmentlist = otherInvestmentlist;
	}

	public String getOtherInvestmentDetail() {
		return otherInvestmentDetail;
	}

	public void setOtherInvestmentDetail(String otherInvestmentDetail) {
		this.otherInvestmentDetail = otherInvestmentDetail;
	}
	

}
