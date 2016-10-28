package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = IdentificationCollector.class,
			name = "ifeQuery",
			query = "call microfin.IDENTIFICLIENTEALT(:clientID, :tipoIdentID, :oficial, "
					+ ":numIdentif, :fecExIden, :fecVenIden, :empresaID, :audUsuario, "
					+ ":audFechaActual, :audDireccionIP, :audProgramaID, :audSucursal, "
					+ ":audNumTransaccion)",
			hints = {
			   			@QueryHint(name = "org.hibernate.callable", value = "true")
					}
	
	)
})
@Entity
public class IdentificationCollector implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String control;
	@Column
	private String ErrMen;
	@Column
	private String consecutivo;
	@Column
	private String NumErr;
	
	public String getControl() {
		return control;
	}
	public void setControl(String control) {
		this.control = control;
	}
	public String getErrMen() {
		return ErrMen;
	}
	public void setErrMen(String errMen) {
		ErrMen = errMen;
	}
	public String getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
	public String getNumErr() {
		return NumErr;
	}
	public void setNumErr(String numErr) {
		NumErr = numErr;
	}
	
	
}
