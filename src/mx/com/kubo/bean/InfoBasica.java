package mx.com.kubo.bean;

import java.io.Serializable;

public class InfoBasica implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String last;
	private String second;
	private String gender;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
