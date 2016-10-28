package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_report_type")
public class ReportType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private ReportTypePK reportTypePK;
	@Column
	private String name;		//varchar (100)
	@Column
	private int sequence;		//int(10)
	@Column
	private char is_visible;	//char(1)
	@Column
	private String short_name;	//varchar (60)	
	
	public ReportType(){
		
	}
	
	public ReportType(ReportTypePK reportTypePK, String name, int sequence,
			char is_visible, String short_name) {
		super();
		this.reportTypePK = reportTypePK;
		this.name = name;
		this.sequence = sequence;
		this.is_visible = is_visible;
		this.short_name = short_name;
	}

	public ReportTypePK getReportTypePK() {
		return reportTypePK;
	}

	public void setReportTypePK(ReportTypePK reportTypePK) {
		this.reportTypePK = reportTypePK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public char getIs_visible() {
		return is_visible;
	}

	public void setIs_visible(char is_visible) {
		this.is_visible = is_visible;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
