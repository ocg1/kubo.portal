package mx.com.kubo.bean;


public class IncomeDetailsBean {

	private int index;
	private Object sellingOrOperCostDetail;
	private Double ammount;
	private String name;
	private String ammount_modified;
	private boolean save;
	private int typeDetail;
	private String typeComponent;// L=Label,I=InputText,C=Combo,R=Radio
	private boolean putBoldStyle;
	private boolean showProcess;
	private String hs_name;
	
	public IncomeDetailsBean(){
		
	}


	public Object getSellingOrOperCostDetail() {
		return sellingOrOperCostDetail;
	}
	public void setSellingOrOperCostDetail(Object sellingOrOperCostDetail) {
		this.sellingOrOperCostDetail = sellingOrOperCostDetail;
	}	
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmmount_modified() {
		return ammount_modified;
	}
	public void setAmmount_modified(String ammount_modified) {
		this.ammount_modified = ammount_modified;
	}	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isSave() {
		return save;
	}
	public void setSave(boolean save) {
		this.save = save;
	}
	public int getTypeDetail() {
		return typeDetail;
	}
	public void setTypeDetail(int typeDetail) {
		this.typeDetail = typeDetail;
	}
	public String getTypeComponent() {
		return typeComponent;
	}
	public void setTypeComponent(String typeComponent) {
		this.typeComponent = typeComponent;
	}


	public boolean isPutBoldStyle() {
		return putBoldStyle;
	}


	public void setPutBoldStyle(boolean putBoldStyle) {
		this.putBoldStyle = putBoldStyle;
	}


	public boolean isShowProcess() {
		return showProcess;
	}


	public void setShowProcess(boolean showProcess) {
		this.showProcess = showProcess;
	}


	public String getHs_name() {
		return hs_name;
	}


	public void setHs_name(String hs_name) {
		this.hs_name = hs_name;
	}	
	
	
}
