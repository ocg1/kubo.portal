package mx.com.kubo.managedbeans.investor;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.model.ContractSignature;
import mx.com.kubo.model.ContratoCreditoCollector;
import mx.com.kubo.model.ContratoRepCapBenCollector;
import mx.com.kubo.model.ContratoRepCapCollector;
import mx.com.kubo.model.CreCertSegrepCollector;
import mx.com.kubo.model.PagareCollector;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ContractCollectorService;
import mx.com.kubo.services.ContractSignatureService;
import mx.com.kubo.services.ContratoCreditoCollectorService;
import mx.com.kubo.services.CreCertSegrepCollectorService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PagareCollectorService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.SignatureService;
import mx.com.kubo.services.SystemParamService;

public abstract class ContractDataDMO 
{	
	protected    FacesContext faces;
	protected ExternalContext external;
	protected       ELContext elContext;
	
	protected HttpServletRequest http_request;
	protected ELResolver resolver;
	
	protected List<ContratoRepCapBenCollector> lstBen;
	protected List<PagareCollector> pagareamortizacionlst;
	protected ContratoCreditoCollector contratoCredito;
	protected CreCertSegrepCollector segrep;
	
	protected ContratoRepCapCollector data;
	
	protected final int FILE_ID_CONTRATO_MEDIOS_ELECTRONICOS = 84;
	protected final int FILE_ID_CONTRATO_GARANTIAS = 85;
	protected final int FILE_ID_CONTRATO_CAPTACION = 86;
	protected final int FILE_ID_CONTRATO_CREDITO = 44;
	protected final int FILE_ID_PAGARE = 45;
		
	protected final int LEGAL_ID_CONTRATO_MEDIOS_ELECTRONICOS = 1;
	protected final int LEGAL_ID_CONTRATO_GARANTIAS = 3;
	protected final int LEGAL_ID_CONTRATO_CAPTACION = 2;
	protected final int LEGAL_ID_CONTRATO_CREDITO = 4;
	
	protected final int COMPANYID = 1;
	protected final int PROYECT_LOAN_ID_INV = 1;
	protected final int SCREEN_SIGNATURE = 61;
	
	protected final int URL_SERVER_INT = 4;
	
	protected boolean have_contrato_medios_electronicos = false;
	protected boolean have_contrato_garantias = false;
	protected boolean have_contrato_captacion = false;
	protected boolean have_signature_credito = false;
	
	protected boolean have_signature_medios_electronicos = false;
	protected boolean have_signature_garantias = false;
	protected boolean have_signature_captacion = false;
	
	protected boolean signed_contrato_medios_electronicos = false;
	protected boolean signed_contrato_garantias = false;
	protected boolean signed_contrato_captacion = false;
	protected boolean credit = false;
	
	protected boolean flagRenderedContactCapt;
	
	protected String url_contrato_medios_electronicos = "jsf/cap1.xhtml";
	protected String url_contrato_garantias = "jsf/cap1.xhtml";
	protected String url_contrato_captacion = "jsf/cap1.xhtml";
	protected String url_contrato_credito = "jsf/cap1.xhtml";
	
	protected String URL_SERVER;
	protected String actual;
	
	protected String browser_data;
	protected String ip_data;
	
	protected Double sumCapital 	= 0D ;
	protected Double sumInteres 	= 0D;
	protected Double sumIva 		= 0D;
	protected Double sumPagTot	= 0D;
	
	protected String serialize_str;
	protected String data_url_str;
	protected String txtContratoMedios;
	protected String unsignature;
	
	protected String serialize_str_2;
	protected String data_url_str_2;
	protected String txtContratoCaptacion;
	
	protected String serialize_str_3;
	protected String data_url_str_3;
	protected String txtContratoGarantia;
	
	protected String serialize_str_4;
	protected String data_url_str_4;
	protected String txtContratoCredito; 
	
	protected String browser_name;
	protected String browser_version;
	protected String browser_OS;
	protected String browser_width;
	protected String browser_height;
	protected String kubo_version;
	protected String safi_client_id;
	protected String monto_credito;
	protected String nombre_institucion;
	protected String freq_name;
	protected String sucursal = "1";
	protected String fechaVencimientoStr;
	protected String monto_a_pagar_str;
	protected String centavos;
	protected String tasaFija;
	protected String tasaFijaTxt;
	protected String url_access;
	
	protected String comisionAperturaStr;
	protected String pagoTardioStr;

	protected ContractSignature contract_sign_medios;
	protected ContractSignature contract_sign_captacion;
	protected ContractSignature contract_sign_garantia;
	protected ContractSignature contract_sign_credito;
	
	protected String signature_medios_location 		= "resources/img/img3.PNG";
	protected String signature_captacion_location 	= "resources/img/img3.PNG";
	protected String signature_garantia_location 		= "resources/img/img3.PNG";
	protected String signature_credito_location 		= "resources/img/img3.PNG";
	
	protected String signature_medios_location_complete;
	protected String signature_captacion_location_complete;
	protected String signature_garantia_location_complete;
	protected String signature_credito_location_complete;
	
	protected boolean have_contrato_credito = false;
	protected boolean signed_contrato_credito = false;
	protected boolean todosFirmados = false; 
	
	protected Integer signature_medios_id;
	protected Integer signature_captacion_id;
	protected Integer signature_garantia_id;
	protected Integer signature_credito_id;
	
	protected Integer proyect_loan_id =  0;
	
	protected List<String> acceptedMedios;
	protected List<String> acceptedCaptacion;
	protected List<String> acceptedGarantia;
	protected List<String> acceptedCredito;
	
	protected SavingAccount saving;
	
	@ManagedProperty("#{contractCollectorServiceImp}")
	protected ContractCollectorService service;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService savingaccountservice;
	
	@ManagedProperty("#{fileTypeServiceImp}")
	protected FileTypeService filetypeservice;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{filesServiceImp}")
	protected FilesService fileService;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService access_service;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{contractSignatureServiceImp}")
	protected ContractSignatureService contractsignatureservice;
	
	@ManagedProperty("#{signatureServiceImp}")
	protected SignatureService signatureservice;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanservice;
	
	@ManagedProperty("#{contratoCreditoCollectorServiceImp}")
	protected ContratoCreditoCollectorService contratocreditocollectorservice;
	
	@ManagedProperty("#{pagareCollectorServiceImp}")
	protected PagareCollectorService pagarecollectorserviceimp;
	
	@ManagedProperty("#{creCertSegrepCollectorServiceImp}")
	protected 	CreCertSegrepCollectorService  crecertsegrepcollectorserviceimp;
	
	public ContratoRepCapCollector getData() {
		
		return data;
		
	}

	public void setData(ContratoRepCapCollector data) {
		
		this.data = data;
		
	}

	public ContractCollectorService getService() {
		
		return service;
		
	}

	public void setService(ContractCollectorService service) {
		
		this.service = service;
		
	}

	public List<ContratoRepCapBenCollector> getLstBen() {
		
		return lstBen;
		
	}

	public void setLstBen(List<ContratoRepCapBenCollector> lstBen) {
		
		this.lstBen = lstBen;
		
	}

	public String getSerialize_str() {
		return serialize_str;
	}

	public void setSerialize_str(String serialize_str) {
		this.serialize_str = serialize_str;
	}

	public String getData_url_str() {
		return data_url_str;
	}

	public void setData_url_str(String data_url_str) {
		this.data_url_str = data_url_str;
	}

	public String getSerialize_str_2() {
		return serialize_str_2;
	}

	public void setSerialize_str_2(String serialize_str_2) {
		this.serialize_str_2 = serialize_str_2;
	}

	public String getData_url_str_2() {
		return data_url_str_2;
	}

	public void setData_url_str_2(String data_url_str_2) {
		this.data_url_str_2 = data_url_str_2;
	}

	public String getSerialize_str_3() {
		return serialize_str_3;
	}

	public void setSerialize_str_3(String serialize_str_3) {
		this.serialize_str_3 = serialize_str_3;
	}

	public String getData_url_str_3() {
		return data_url_str_3;
	}

	public void setData_url_str_3(String data_url_str_3) {
		this.data_url_str_3 = data_url_str_3;
	}

	public String getTxtContratoMedios() {
		return txtContratoMedios;
	}

	public void setTxtContratoMedios(String txtContratoMedios) {
		this.txtContratoMedios = txtContratoMedios;
	}

	public String getTxtContratoCaptacion() {
		return txtContratoCaptacion;
	}

	public void setTxtContratoCaptacion(String txtContratoCaptacion) {
		this.txtContratoCaptacion = txtContratoCaptacion;
	}

	public String getTxtContratoGarantia() {
		return txtContratoGarantia;
	}

	public void setTxtContratoGarantia(String txtContratoGarantia) {
		this.txtContratoGarantia = txtContratoGarantia;
	}

	public SavingAccount getSaving() {
		return saving;
	}

	public void setSaving(SavingAccount saving) {
		this.saving = saving;
	}

	public SavingAccountService getSavingaccountservice() {
		return savingaccountservice;
	}

	public void setSavingaccountservice(SavingAccountService savingaccountservice) {
		this.savingaccountservice = savingaccountservice;
	}

	public FileTypeService getFiletypeservice() {
		return filetypeservice;
	}

	public void setFiletypeservice(FileTypeService filetypeservice) {
		this.filetypeservice = filetypeservice;
	}

	public String getUnsignature() {
		return unsignature;
	}

	public void setUnsignature(String unsignature) {
		this.unsignature = unsignature;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}

	public FilesService getFileService() {
		return fileService;
	}

	public void setFileService(FilesService fileService) {
		this.fileService = fileService;
	}

	public boolean isHave_contrato_medios_electronicos() {
		return have_contrato_medios_electronicos;
	}

	public void setHave_contrato_medios_electronicos(boolean have_contrato_medios_electronicos) {
		this.have_contrato_medios_electronicos = have_contrato_medios_electronicos;
	}

	public boolean isHave_contrato_garantias() {
		return have_contrato_garantias;
	}

	public void setHave_contrato_garantias(boolean have_contrato_garantias) {
		this.have_contrato_garantias = have_contrato_garantias;
	}

	public boolean isHave_contrato_captacion() {
		return have_contrato_captacion;
	}

	public void setHave_contrato_captacion(boolean have_contrato_captacion) {
		this.have_contrato_captacion = have_contrato_captacion;
	}

	public String getUrl_contrato_medios_electronicos() {
		return url_contrato_medios_electronicos;
	}

	public void setUrl_contrato_medios_electronicos(String url_contrato_medios_electronicos) {
		this.url_contrato_medios_electronicos = url_contrato_medios_electronicos;
	}

	public String getUrl_contrato_garantias() {
		return url_contrato_garantias;
	}

	public void setUrl_contrato_garantias(String url_contrato_garantias) {
		this.url_contrato_garantias = url_contrato_garantias;
	}

	public String getUrl_contrato_captacion() {
		return url_contrato_captacion;
	}

	public void setUrl_contrato_captacion(String url_contrato_captacion) {
		this.url_contrato_captacion = url_contrato_captacion;
	}

	public String getBrowser_data() {
		return browser_data;
	}

	public void setBrowser_data(String browser_data) {
		this.browser_data = browser_data;
	}

	public String getIp_data() {
		return ip_data;
	}

	public void setIp_data(String ip_data) {
		this.ip_data = ip_data;
	}

	public AccessService getAccess_service() {
		return access_service;
	}

	public void setAccess_service(AccessService access_service) {
		this.access_service = access_service;
	}

	

	public String getBrowser_name() {
		return browser_name;
	}

	public void setBrowser_name(String browser_name) {
		this.browser_name = browser_name;
	}

	public String getBrowser_version() {
		return browser_version;
	}

	public void setBrowser_version(String browser_version) {
		this.browser_version = browser_version;
	}

	public String getBrowser_OS() {
		return browser_OS;
	}

	public void setBrowser_OS(String browser_OS) {
		this.browser_OS = browser_OS;
	}

	public String getBrowser_width() {
		return browser_width;
	}

	public void setBrowser_width(String browser_width) {
		this.browser_width = browser_width;
	}

	public String getBrowser_height() {
		return browser_height;
	}

	public void setBrowser_height(String browser_height) {
		this.browser_height = browser_height;
	}

	public ContractSignatureService getContractsignatureservice() {
		return contractsignatureservice;
	}

	public void setContractsignatureservice(ContractSignatureService contractsignatureservice) {
		this.contractsignatureservice = contractsignatureservice;
	}

	public SignatureService getSignatureservice() {
		return signatureservice;
	}

	public void setSignatureservice(SignatureService signatureservice) {
		this.signatureservice = signatureservice;
	}

	public ContractSignature getContract_sign_medios() {
		return contract_sign_medios;
	}

	public void setContract_sign_medios(ContractSignature contract_sign_medios) {
		this.contract_sign_medios = contract_sign_medios;
	}

	public ContractSignature getContract_sign_captacion() {
		return contract_sign_captacion;
	}

	public void setContract_sign_captacion(ContractSignature contract_sign_captacion) {
		this.contract_sign_captacion = contract_sign_captacion;
	}

	public ContractSignature getContract_sign_garantia() {
		return contract_sign_garantia;
	}

	public void setContract_sign_garantia(ContractSignature contract_sign_garantia) {
		this.contract_sign_garantia = contract_sign_garantia;
	}

	public List<String> getAcceptedMedios() {
		return acceptedMedios;
	}

	public void setAcceptedMedios(List<String> acceptedMedios) {
		this.acceptedMedios = acceptedMedios;
	}

	public List<String> getAcceptedCaptacion() {
		return acceptedCaptacion;
	}

	public void setAcceptedCaptacion(List<String> acceptedCaptacion) {
		this.acceptedCaptacion = acceptedCaptacion;
	}

	public List<String> getAcceptedGarantia() {
		return acceptedGarantia;
	}

	public void setAcceptedGarantia(List<String> acceptedGarantia) {
		this.acceptedGarantia = acceptedGarantia;
	}

	public ProyectLoanService getProyectloanservice() {
		return proyectloanservice;
	}

	public void setProyectloanservice(ProyectLoanService proyectloanservice) {
		this.proyectloanservice = proyectloanservice;
	}

	public ContratoCreditoCollectorService getContratocreditocollectorservice() {
		return contratocreditocollectorservice;
	}

	public void setContratocreditocollectorservice(ContratoCreditoCollectorService contratocreditocollectorservice) {
		this.contratocreditocollectorservice = contratocreditocollectorservice;
	}

	public PagareCollectorService getPagarecollectorserviceimp() {
		return pagarecollectorserviceimp;
	}

	public void setPagarecollectorserviceimp(PagareCollectorService pagarecollectorserviceimp) {
		this.pagarecollectorserviceimp = pagarecollectorserviceimp;
	}

	public List<PagareCollector> getPagareamortizacionlst() {
		return pagareamortizacionlst;
	}

	public void setPagareamortizacionlst(List<PagareCollector> pagareamortizacionlst) {
		this.pagareamortizacionlst = pagareamortizacionlst;
	}
	public ContratoCreditoCollector getContratoCredito() {
		return contratoCredito;
	}

	public void setContratoCredito(ContratoCreditoCollector contratoCredito) {
		this.contratoCredito = contratoCredito;
	}

	public String getSafi_client_id() {
		return safi_client_id;
	}

	public void setSafi_client_id(String safi_client_id) {
		this.safi_client_id = safi_client_id;
	}

	public String getMonto_credito() {
		return monto_credito;
	}

	public void setMonto_credito(String monto_credito) {
		this.monto_credito = monto_credito;
	}

	public String getNombre_institucion() {
		return nombre_institucion;
	}

	public void setNombre_institucion(String nombre_institucion) {
		this.nombre_institucion = nombre_institucion;
	}

	public CreCertSegrepCollectorService getCrecertsegrepcollectorserviceimp() {
		return crecertsegrepcollectorserviceimp;
	}

	public void setCrecertsegrepcollectorserviceimp(CreCertSegrepCollectorService crecertsegrepcollectorserviceimp) {
		this.crecertsegrepcollectorserviceimp = crecertsegrepcollectorserviceimp;
	}

	public CreCertSegrepCollector getSegrep() {
		return segrep;
	}

	public void setSegrep(CreCertSegrepCollector segrep) {
		this.segrep = segrep;
	}

	public String getFreq_name() {
		return freq_name;
	}

	public void setFreq_name(String freq_name) {
		this.freq_name = freq_name;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public Double getSumCapital() {
		return sumCapital;
	}

	public void setSumCapital(Double sumCapital) {
		this.sumCapital = sumCapital;
	}

	public Double getSumInteres() {
		return sumInteres;
	}

	public void setSumInteres(Double sumInteres) {
		this.sumInteres = sumInteres;
	}

	public Double getSumIva() {
		return sumIva;
	}

	public void setSumIva(Double sumIva) {
		this.sumIva = sumIva;
	}

	public Double getSumPagTot() {
		return sumPagTot;
	}

	public void setSumPagTot(Double sumPagTot) {
		this.sumPagTot = sumPagTot;
	}

	public String getFechaVencimientoStr() {
		return fechaVencimientoStr;
	}

	public void setFechaVencimientoStr(String fechaVencimientoStr) {
		this.fechaVencimientoStr = fechaVencimientoStr;
	}

	public String getMonto_a_pagar_str() {
		return monto_a_pagar_str;
	}

	public void setMonto_a_pagar_str(String monto_a_pagar_str) {
		this.monto_a_pagar_str = monto_a_pagar_str;
	}

	public String getCentavos() {
		return centavos;
	}

	public void setCentavos(String centavos) {
		this.centavos = centavos;
	}

	public String getTasaFija() {
		return tasaFija;
	}

	public void setTasaFija(String tasaFija) {
		this.tasaFija = tasaFija;
	}

	public String getTasaFijaTxt() {
		return tasaFijaTxt;
	}

	public void setTasaFijaTxt(String tasaFijaTxt) {
		this.tasaFijaTxt = tasaFijaTxt;
	}

	public boolean isHave_signature_medios_electronicos() {
		return have_signature_medios_electronicos;
	}

	public void setHave_signature_medios_electronicos(boolean have_signature_medios_electronicos) {
		this.have_signature_medios_electronicos = have_signature_medios_electronicos;
	}

	public boolean isHave_signature_garantias() {
		return have_signature_garantias;
	}

	public void setHave_signature_garantias(boolean have_signature_garantias) {
		this.have_signature_garantias = have_signature_garantias;
	}

	public boolean isHave_signature_captacion() {
		return have_signature_captacion;
	}

	public void setHave_signature_captacion(boolean have_signature_captacion) {
		this.have_signature_captacion = have_signature_captacion;
	}

	public boolean isCredit() {
		return credit;
	}

	public void setCredit(boolean credit) {
		this.credit = credit;
	}

	public String getSignature_medios_location() {
		return signature_medios_location;
	}

	public void setSignature_medios_location(String signature_medios_location) {
		this.signature_medios_location = signature_medios_location;
	}

	public String getSignature_captacion_location() {
		return signature_captacion_location;
	}

	public void setSignature_captacion_location(String signature_captacion_location) {
		this.signature_captacion_location = signature_captacion_location;
	}

	public String getSignature_garantia_location() {
		return signature_garantia_location;
	}

	public void setSignature_garantia_location(String signature_garantia_location) {
		this.signature_garantia_location = signature_garantia_location;
	}

	public boolean isHave_signature_credito() {
		return have_signature_credito;
	}

	public void setHave_signature_credito(boolean have_signature_credito) {
		this.have_signature_credito = have_signature_credito;
	}

	public String getSignature_credito_location() {
		return signature_credito_location;
	}

	public void setSignature_credito_location(String signature_credito_location) {
		this.signature_credito_location = signature_credito_location;
	}

	public String getSerialize_str_4() {
		return serialize_str_4;
	}

	public void setSerialize_str_4(String serialize_str_4) {
		this.serialize_str_4 = serialize_str_4;
	}

	public String getData_url_str_4() {
		return data_url_str_4;
	}

	public void setData_url_str_4(String data_url_str_4) {
		this.data_url_str_4 = data_url_str_4;
	}

	public String getTxtContratoCredito() {
		return txtContratoCredito;
	}

	public void setTxtContratoCredito(String txtContratoCredito) {
		this.txtContratoCredito = txtContratoCredito;
	}

	public boolean isHave_contrato_credito() {
		return have_contrato_credito;
	}

	public void setHave_contrato_credito(boolean have_contrato_credito) {
		this.have_contrato_credito = have_contrato_credito;
	}

	public String getUrl_contrato_credito() {
		return url_contrato_credito;
	}

	public void setUrl_contrato_credito(String url_contrato_credito) {
		this.url_contrato_credito = url_contrato_credito;
	}

	public List<String> getAcceptedCredito() {
		return acceptedCredito;
	}

	public void setAcceptedCredito(List<String> acceptedCredito) {
		this.acceptedCredito = acceptedCredito;
	}

	public ContractSignature getContract_sign_credito() {
		return contract_sign_credito;
	}

	public void setContract_sign_credito(ContractSignature contract_sign_credito) {
		this.contract_sign_credito = contract_sign_credito;
	}

	public boolean isTodosFirmados() {
		return todosFirmados;
	}

	public void setTodosFirmados(boolean todosFirmados) {
		this.todosFirmados = todosFirmados;
	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	public boolean isFlagRenderedContactCapt() {
		return flagRenderedContactCapt;
	}

	public void setFlagRenderedContactCapt(boolean flagRenderedContactCapt) {
		this.flagRenderedContactCapt = flagRenderedContactCapt;
	}

	public String getComisionAperturaStr() {
		return comisionAperturaStr;
	}

	public void setComisionAperturaStr(String comisionAperturaStr) {
		this.comisionAperturaStr = comisionAperturaStr;
	}

	public String getPagoTardioStr() {
		return pagoTardioStr;
	}

	public void setPagoTardioStr(String pagoTardioStr) {
		this.pagoTardioStr = pagoTardioStr;
	}
	
}
