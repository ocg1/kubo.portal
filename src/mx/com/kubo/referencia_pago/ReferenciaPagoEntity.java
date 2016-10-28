package mx.com.kubo.referencia_pago;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({	
	@NamedNativeQuery(
			resultClass = ReferenciaPagoEntity.class,
			name        = "getLista_referencias_pago",    
			query 	    = "call GENERA_REFERENCIAS( :safi_credito_id )",
			hints 		= {@QueryHint(name = "org.hibernate.callable", value = "true")}    
	)
})

@Entity
public class ReferenciaPagoEntity implements Serializable
{
	private static final long serialVersionUID = 2530700301666150204L;

	@Id
	@Column (name = "deposit_bank_id")
	private Integer id;
	
	@Column(name = "bank_seq")
	private Integer no_secuencia;
	
	@Column(name = "is_active")
	private Integer enabled;
	
	@Column(name = "comission_bank")
	private Double comision;
	
	@Column private Character deposit_type;
	@Column private Character area;
	
	@Column private String mx_numero_empresa; 
	@Column private String clabe_account;
	@Column private String referencia;		
	@Column private String url_logo;
	
	@Column (name = "bank_account")
	private String no_cuenta;
	
	public final Integer getId() 
	{
		return id;
	}

	public final void setId(Integer id) 
	{
		this.id = id;
	}

	public final Integer getNo_secuencia() 
	{
		return no_secuencia;
	}

	public final void setNo_secuencia(Integer no_secuencia) 
	{
		this.no_secuencia = no_secuencia;
	}
	
	public final Double getComision() 
	{
		return comision;
	}

	public final void setComision(Double comision) 
	{
		this.comision = comision;
	}

	public final String getUrl_logo() 
	{
		return url_logo;
	}

	public final void setUrl_logo(String url_logo) 
	{
		this.url_logo = url_logo;
	}
	
	public String getMx_numero_empresa() {
		return mx_numero_empresa;
	}

	public void setMx_numero_empresa(String mx_numero_empresa) {
		this.mx_numero_empresa = mx_numero_empresa;
	}
	
	public String getClabe_account() {
		return clabe_account;
	}

	public void setClabe_account(String clabe_account) {
		this.clabe_account = clabe_account;
	}

	public final String getNo_cuenta() 
	{
		return no_cuenta;
	}

	public final void setNo_cuenta(String no_cuenta) 
	{
		this.no_cuenta = no_cuenta;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
}
