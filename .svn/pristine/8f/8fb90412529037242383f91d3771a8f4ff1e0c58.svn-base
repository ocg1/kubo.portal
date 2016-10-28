package mx.com.kubo.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.map.MapModel;


@ManagedBean
@SessionScoped
public class InfoBasicaController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String second_name;
	private String father_last_name;
	private String mother_last_name;
	private String gender;
	private String short_name;
	private String about_me;
	private MapModel emptyModel;
	
	private boolean activeTab=true;
	int actTab;  
	
	public InfoBasicaController(){
		activeTab=true;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecond_name() {
		return second_name;
	}
	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}
	public String getFather_last_name() {
		return father_last_name;
	}
	public void setFather_last_name(String father_last_name) {
		this.father_last_name = father_last_name;
	}
	public String getMother_last_name() {
		return mother_last_name;
	}
	public void setMother_last_name(String mother_last_name) {
		this.mother_last_name = mother_last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getAbout_me() {
		if(about_me!=null)
		return about_me.replaceAll("\n", "");
		else
			return about_me;
	}
	public void setAbout_me(String about_me) {
		this.about_me = about_me.replaceAll("\n", "");
	}
	
	
	public boolean isActiveTab() {
		return activeTab;
	}
	public void setActiveTab(boolean activeTab) {
		this.activeTab = activeTab;
	}
	
	
	public int getActTab() {
		return actTab;
	}
	public void setActTab(int actTab) {
		this.actTab = actTab;
	}
	public String callWebService(){
		int result=(int) (Math.random()* 2+1);
		System.out.println("Web Service DICE= "+result);
		if(result ==1){
			setActiveTab(false);
			setActTab(2);
			return null;
		}
		else{
			return "infoResult";
		}
		
	}
	public void changaIndexTab(){
		actTab=3;
	}

	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}
	
	
}
