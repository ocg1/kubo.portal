package com.soa.webServices;

public class WsSgbRiskProxy implements com.soa.webServices.WsSgbRisk {
  private String _endpoint = null;
  private com.soa.webServices.WsSgbRisk wsSgbRisk = null;
  
  public WsSgbRiskProxy() {
    _initWsSgbRiskProxy();
  }
  
  public WsSgbRiskProxy(String endpoint) {
    _endpoint = endpoint;
    _initWsSgbRiskProxy();
  }
  
  private void _initWsSgbRiskProxy() {
    try {
      wsSgbRisk = (new com.soa.webServices.WsSgbRiskServiceLocator()).getWsSgbRisk();
      if (wsSgbRisk != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wsSgbRisk)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wsSgbRisk)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wsSgbRisk != null)
      ((javax.xml.rpc.Stub)wsSgbRisk)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.soa.webServices.WsSgbRisk getWsSgbRisk() {
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk;
  }
  
  public java.lang.String testConnection() throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.testConnection();
  }
  
  public com.soa.webServices.responses.WsSgbResponse newProject(java.lang.String prospectId, java.lang.String projectId, java.lang.String productId, java.lang.String amount, java.lang.String companyId, java.lang.String mxTasa, java.lang.String mxFrec, java.lang.String mxNumPagos, java.lang.String mxComisionApertura, java.lang.String bursolnum, java.lang.String loan_type, java.lang.String is_collection_solution) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.newProject(prospectId, projectId, productId, amount, companyId, mxTasa, mxFrec, mxNumPagos, mxComisionApertura, bursolnum, loan_type, is_collection_solution);
  }
  
  public com.soa.model.businessobject.Vtbur_infoAlertaPrev[] getVtbur_infoAlertaPrev(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getVtbur_infoAlertaPrev(bursolnum);
  }
  
  public com.soa.model.businessobject.TSafiKiva[] getTSafiKiva(java.lang.String creditoId) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getTSafiKiva(creditoId);
  }
  
  public com.soa.webServices.responses.AllDataBur getAllDataBur(java.lang.String burSolNum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getAllDataBur(burSolNum);
  }
  
  public com.soa.webServices.responses.KuboRulesResponse[] getKuboRules(java.lang.String prospectId) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getKuboRules(prospectId);
  }
  
  public com.soa.webServices.responses.WsSgbResponse notificaPromesadePago() throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.notificaPromesadePago();
  }
  
  public com.soa.model.businessobject.SpScoreKubo getClientRisk(java.lang.String clientId, java.lang.String bur_solnum, java.lang.String homeType, java.lang.String gender, java.lang.String age, java.lang.String businessType) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getClientRisk(clientId, bur_solnum, homeType, gender, age, businessType);
  }
  
  public com.soa.model.businessobject.BurResponse getProspectIDProvider(java.lang.String user, java.lang.String password, java.lang.String prospectId, java.lang.String indCreditCard, java.lang.String creditCardTermination, java.lang.String indmortgage, java.lang.String indCarLoan) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getProspectIDProvider(user, password, prospectId, indCreditCard, creditCardTermination, indmortgage, indCarLoan);
  }
  
  public boolean getKuboRulesEvaluation(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getKuboRulesEvaluation(bursolnum);
  }
  
  public com.soa.webServices.responses.WsSgbResponse newProjectDTO(com.mx.kubo.sgbws.models.dto.NewProjectRequestDTO newProjectRequestDTO) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.newProjectDTO(newProjectRequestDTO);
  }
  
  public com.soa.webServices.responses.WsSgbResponse phoneUpdate(com.soa.webServices.request.PhoneUpdateRequest phoneUpdateRequest) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.phoneUpdate(phoneUpdateRequest);
  }
  
  public com.soa.webServices.responses.WsSgbResponse homeUpdate(com.soa.webServices.request.HomeUpdateRequest homeUpdateRequest) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.homeUpdate(homeUpdateRequest);
  }
  
  public com.soa.webServices.responses.WsSgbResponse referencesUpdate(com.soa.webServices.request.ReferencesUpdateRequest req) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.referencesUpdate(req);
  }
  
  public com.soa.webServices.responses.WsSgbResponse updateIfe(com.soa.webServices.request.VDocumentsIfe doc) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.updateIfe(doc);
  }
  
  public com.soa.webServices.responses.WsSgbResponse consultaBuro(java.lang.String cadenaConsulta) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.consultaBuro(cadenaConsulta);
  }
  
  public com.soa.model.businessobject.BurResponse getProspectProspector(java.lang.String user, java.lang.String password, java.lang.String prospectId) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getProspectProspector(user, password, prospectId);
  }
  
  public com.soa.model.businessobject.BurResponse getProspectBC(java.lang.String user, java.lang.String password, java.lang.String prospectId) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getProspectBC(user, password, prospectId);
  }
  
  public com.soa.webServices.responses.WsSgbResponse prospectAdmin(java.lang.String user, java.lang.String password, java.lang.String prospectId, java.lang.String firstName, java.lang.String secondName, java.lang.String surName, java.lang.String aditSurName, java.lang.String birthday, java.lang.String rfc, java.lang.String street, java.lang.String mx_manzana, java.lang.String mx_lote, java.lang.String mx_numExterior, java.lang.String mx_numInterior, java.lang.String mx_colonia, java.lang.String mx_municipio, java.lang.String mx_estado, java.lang.String mx_codPostal, java.lang.String mx_lada, java.lang.String phoneNumber, java.lang.String curp, java.lang.String folio, double monto_Solicitado) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.prospectAdmin(user, password, prospectId, firstName, secondName, surName, aditSurName, birthday, rfc, street, mx_manzana, mx_lote, mx_numExterior, mx_numInterior, mx_colonia, mx_municipio, mx_estado, mx_codPostal, mx_lada, phoneNumber, curp, folio, monto_Solicitado);
  }
  
  public com.soa.webServices.responses.WsSgbResponse consultaCirculo(java.lang.String cadenaConsulta) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.consultaCirculo(cadenaConsulta);
  }
  
  public com.soa.webServices.responses.WsSgbResponse consultaCirculoIdProvider(java.lang.String prospectId) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.consultaCirculoIdProvider(prospectId);
  }
  
  public com.soa.model.businessobject.BurGraphic[] getBurGraphic(java.lang.String burSolNum, java.lang.String[] order) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getBurGraphic(burSolNum, order);
  }
  
  public com.soa.model.businessobject.Vtbur_infocalkubo getVtbur_infocalkubo(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getVtbur_infocalkubo(bursolnum);
  }
  
  public com.soa.model.businessobject.Vtbur_infocte getVtbur_infocte(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getVtbur_infocte(bursolnum);
  }
  
  public com.soa.model.businessobject.Vtbur_infodircte[] getVtbur_infodircte(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getVtbur_infodircte(bursolnum);
  }
  
  public com.soa.model.businessobject.Vtbur_infocredcte_vig[] getVtbur_infocredcte_vig(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getVtbur_infocredcte_vig(bursolnum);
  }
  
  public com.soa.model.businessobject.Vtbur_infocredcte_c[] getVtbur_infocredcte_c(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getVtbur_infocredcte_c(bursolnum);
  }
  
  public com.soa.model.businessobject.Vtbur_infocredcte_m[] getVtbur_infocredcte_m(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getVtbur_infocredcte_m(bursolnum);
  }
  
  public com.soa.webServices.responses.ProspectBCRiskResponse getProspectBCRisk(com.soa.webServices.request.BCRiskRequest request) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getProspectBCRisk(request);
  }
  
  public com.soa.webServices.responses.WsSgbResponse updateProspect(java.lang.String userId, java.lang.String prospectId, java.lang.String varId, java.lang.String value) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.updateProspect(userId, prospectId, varId, value);
  }
  
  public com.soa.webServices.responses.WsSgbResponse changePhone(java.lang.String userId, java.lang.String prospectId, java.lang.String phoneTypeId, java.lang.String phoneNumber) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.changePhone(userId, prospectId, phoneTypeId, phoneNumber);
  }
  
  public com.soa.webServices.responses.WsSgbResponse changeAddress(java.lang.String userId, java.lang.String prospectId, java.lang.String addressTypeId, java.lang.String street, java.lang.String mx_manzana, java.lang.String mx_lote, java.lang.String mx_numExterior, java.lang.String mx_numInterior, java.lang.String mx_colonia, java.lang.String mx_municipio, java.lang.String mx_estado, java.lang.String mx_codPostal, java.lang.String mx_lada) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.changeAddress(userId, prospectId, addressTypeId, street, mx_manzana, mx_lote, mx_numExterior, mx_numInterior, mx_colonia, mx_municipio, mx_estado, mx_codPostal, mx_lada);
  }
  
  public com.soa.webServices.responses.TransunionResponse getTransunionDecisionResponse(java.lang.String prospectId, java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getTransunionDecisionResponse(prospectId, bursolnum);
  }
  
  public com.soa.model.businessobject.Vtbur_infocnsltult[] getVtbur_infocnsltult(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getVtbur_infocnsltult(bursolnum);
  }
  
  public com.soa.model.businessobject.Vtbur_infocnsltms[] getVtbur_infocnsltms(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getVtbur_infocnsltms(bursolnum);
  }
  
  public com.soa.webServices.responses.WsSgbResponse prospectPhone(java.lang.String prospectId, java.lang.String phoneNumber, java.lang.String phoneType, java.lang.String operation) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.prospectPhone(prospectId, phoneNumber, phoneType, operation);
  }
  
  public com.soa.model.businessobject.TSafiPosicionInt[] getTSafiPosicionInt(java.lang.String creditoid) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getTSafiPosicionInt(creditoid);
  }
  
  public com.soa.model.businessobject.TSafiPagosCuota[] getTSafiPagosCuota(java.lang.String creditoid) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getTSafiPagosCuota(creditoid);
  }
  
  public com.soa.webServices.responses.WsSgbResponse updateCredit(java.lang.String userId, java.lang.String projectLoanId, java.lang.String prospectId, java.lang.String companyId, java.lang.String varId, java.lang.String newValue) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.updateCredit(userId, projectLoanId, prospectId, companyId, varId, newValue);
  }
  
  public com.soa.webServices.responses.WsSgbResponse notificationValidatedSignature(java.lang.String prospectusId, java.lang.String projectLoanId) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.notificationValidatedSignature(prospectusId, projectLoanId);
  }
  
  public com.soa.webServices.responses.WsSgbResponse aplicationPublicationInvestor(java.lang.String aplicationPublicationInvestorDTOJSON) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.aplicationPublicationInvestor(aplicationPublicationInvestorDTOJSON);
  }
  
  public com.soa.webServices.responses.WsSgbResponse buroReprocess(com.mx.kubo.sgbws.models.dto.ReprocessBuroDataDTO reprocessBuroDataDTO) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.buroReprocess(reprocessBuroDataDTO);
  }
  
  public java.lang.String[] getPromotorData(java.lang.String[] prospectusArray, java.lang.String activityTypeId, java.lang.String statusId) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getPromotorData(prospectusArray, activityTypeId, statusId);
  }
  
  public com.soa.webServices.responses.ApplicationLogResponse applicationsLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.applicationsLogs(cliProId, indCliPro);
  }
  
  public com.soa.webServices.responses.ApplicationLogResponse creditsLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.creditsLogs(cliProId, indCliPro);
  }
  
  public com.soa.webServices.responses.ApplicationLogResponse recoveryLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.recoveryLogs(cliProId, indCliPro);
  }
  
  public com.soa.webServices.responses.ApplicationLogResponse eventsLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.eventsLogs(cliProId, indCliPro);
  }
  
  public com.soa.model.businessobject.SpScoreKubo getProspectRisk(com.soa.webServices.request.BCRiskRequest request) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getProspectRisk(request);
  }
  
  public com.soa.webServices.responses.SGBCierreDiarioResponse dispararCierreDiario(com.soa.webServices.request.SGBCierreDiarioRequest cierreDiarioRequest) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.dispararCierreDiario(cierreDiarioRequest);
  }
  
  public com.soa.webServices.responses.SGBConsultaCierreDiarioResponse consultarCierreDiario(com.soa.webServices.request.SGBConsultaCierreDiarioRequest consultaCierreDiarioRequest) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.consultarCierreDiario(consultaCierreDiarioRequest);
  }
  
  public void notificationDisbursementInfusion() throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    wsSgbRisk.notificationDisbursementInfusion();
  }
  
  public com.soa.webServices.responses.WsSgbResponse depositNotification(java.lang.String prospectusId, java.lang.String mail, java.lang.String safiClientId, java.lang.String originAccount, java.lang.String originBank, java.lang.String destinationAccount, java.lang.String despositAmount, java.lang.String depositDate, java.lang.String description, java.lang.String savingsAccountId) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.depositNotification(prospectusId, mail, safiClientId, originAccount, originBank, destinationAccount, despositAmount, depositDate, description, savingsAccountId);
  }
  
  public java.lang.String CRRTRMSVPWD(java.lang.String usuario, java.lang.String password, java.lang.String consultasMaximas, java.lang.String indTest) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.CRRTRMSVPWD(usuario, password, consultasMaximas, indTest);
  }
  
  public com.soa.model.businessobject.TSafiCreditosMovs[] getTSafiCreditosMovs(java.lang.String creditoId, java.lang.String amortiCreId, java.util.Calendar fechaOperacion, java.lang.String tipoSaldo, java.lang.String idProspecto) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getTSafiCreditosMovs(creditoId, amortiCreId, fechaOperacion, tipoSaldo, idProspecto);
  }
  
  public com.soa.model.businessobject.TSafiCuentasAhoMovDep[] getTSafiCuentasAhoMovDep(java.lang.String cuentaAhoID, java.util.Calendar fecha, java.lang.String natMovimiento) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getTSafiCuentasAhoMovDep(cuentaAhoID, fecha, natMovimiento);
  }
  
  public java.util.Calendar getFechaCorte() throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getFechaCorte();
  }
  
  public com.soa.model.businessobject.Vtbur_infoAlertaInc[] getVtbur_infoAlertaInc(java.lang.String bursolnum) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getVtbur_infoAlertaInc(bursolnum);
  }
  
  public com.soa.webServices.responses.ApplicationLogResponse evaluationLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.evaluationLogs(cliProId, indCliPro);
  }
  
  public com.soa.model.businessobject.BurResume getBurResume(java.lang.String user, java.lang.String password, java.lang.String burSol) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getBurResume(user, password, burSol);
  }
  
  public com.soa.model.businessobject.BurResponse[] getBurScores(java.lang.String user, java.lang.String password, java.lang.String prospectId) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.getBurScores(user, password, prospectId);
  }
  
  public com.soa.webServices.responses.WsSgbResponse documentsReview(com.soa.webServices.request.DocumentsReviewRequest docs) throws java.rmi.RemoteException{
    if (wsSgbRisk == null)
      _initWsSgbRiskProxy();
    return wsSgbRisk.documentsReview(docs);
  }
  
  
}