package mx.com.kubo.bean;

public class DetMovsInvestmentsBean {
	private String fecha;
	private String descripcion1;
	private String descripcion2;
	private String descripcion3;
	private String abonosMov;
	private String cargosMov;
	private String saldo;
	private String bancoReceptor;
	
	public String getFecha(){
		return fecha;
	}
	public void setFecha(String fecha){
		this.fecha = fecha;
	}
	public String getDescripcion1() {
		return descripcion1;
	}
	public void setDescripcion1(String descripcion1) {
		this.descripcion1 = descripcion1;
	}
	public String getDescripcion2() {
		return descripcion2;
	}
	public void setDescripcion2(String descripcion2) {
		this.descripcion2 = descripcion2;
	}
	public String getDescripcion3() {
		return descripcion3;
	}
	public void setDescripcion3(String descripcion3) {
		this.descripcion3 = descripcion3;
	}
	public String getAbonosMov(){
		return abonosMov;
	}
	public void setAbonosMov(String abonosMov){
		this.abonosMov = abonosMov;
	}
	public String getCargosMov(){
		return cargosMov;
	}
	public void setCargosMov(String cargosMov){
		this.cargosMov = cargosMov;
	}
	public String getSaldo(){
		return saldo;
	}
	public void setSaldo(String saldo){
		this.saldo = saldo;
	}
	public String getBancoReceptor(){
		return bancoReceptor;
	}
	public void setBancoReceptor(String bancoReceptor){
		this.bancoReceptor = bancoReceptor;
	}
}
