/**
 * WsSgbRisk.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices;

public interface WsSgbRisk extends java.rmi.Remote {
    public java.lang.String testConnection() throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse newProject(java.lang.String prospectId, java.lang.String projectId, java.lang.String productId, java.lang.String amount, java.lang.String companyId, java.lang.String mxTasa, java.lang.String mxFrec, java.lang.String mxNumPagos, java.lang.String mxComisionApertura, java.lang.String bursolnum, java.lang.String loan_type, java.lang.String is_collection_solution) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse consultaBuro(java.lang.String cadenaConsulta) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.ApplicationLogResponse applicationsLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.ApplicationLogResponse creditsLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.ApplicationLogResponse recoveryLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.ApplicationLogResponse eventsLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.TSafiCuentasAhoMovDep[] getTSafiCuentasAhoMovDep(java.lang.String cuentaAhoID, java.util.Calendar fecha, java.lang.String natMovimiento) throws java.rmi.RemoteException;
    public java.util.Calendar getFechaCorte() throws java.rmi.RemoteException;
    public com.soa.model.businessobject.Vtbur_infoAlertaInc[] getVtbur_infoAlertaInc(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.Vtbur_infoAlertaPrev[] getVtbur_infoAlertaPrev(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.TSafiKiva[] getTSafiKiva(java.lang.String creditoId) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.AllDataBur getAllDataBur(java.lang.String burSolNum) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.KuboRulesResponse[] getKuboRules(java.lang.String prospectId) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse notificaPromesadePago() throws java.rmi.RemoteException;
    public com.soa.webServices.responses.SGBCierreDiarioResponse dispararCierreDiario(com.soa.webServices.request.SGBCierreDiarioRequest cierreDiarioRequest) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.SGBConsultaCierreDiarioResponse consultarCierreDiario(com.soa.webServices.request.SGBConsultaCierreDiarioRequest consultaCierreDiarioRequest) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.ApplicationLogResponse evaluationLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse newProjectDTO(com.mx.kubo.sgbws.models.dto.NewProjectRequestDTO newProjectRequestDTO) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.BurResponse getProspectProspector(java.lang.String user, java.lang.String password, java.lang.String prospectId) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.BurResponse getProspectBC(java.lang.String user, java.lang.String password, java.lang.String prospectId) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse prospectAdmin(java.lang.String user, java.lang.String password, java.lang.String prospectId, java.lang.String firstName, java.lang.String secondName, java.lang.String surName, java.lang.String aditSurName, java.lang.String birthday, java.lang.String rfc, java.lang.String street, java.lang.String mx_manzana, java.lang.String mx_lote, java.lang.String mx_numExterior, java.lang.String mx_numInterior, java.lang.String mx_colonia, java.lang.String mx_municipio, java.lang.String mx_estado, java.lang.String mx_codPostal, java.lang.String mx_lada, java.lang.String phoneNumber, java.lang.String curp, java.lang.String folio, double monto_Solicitado) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.SpScoreKubo getProspectRisk(com.soa.webServices.request.BCRiskRequest request) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse updateCredit(java.lang.String userId, java.lang.String projectLoanId, java.lang.String prospectId, java.lang.String companyId, java.lang.String varId, java.lang.String newValue) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse updateProspect(java.lang.String userId, java.lang.String prospectId, java.lang.String varId, java.lang.String value) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse changePhone(java.lang.String userId, java.lang.String prospectId, java.lang.String phoneTypeId, java.lang.String phoneNumber) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse changeAddress(java.lang.String userId, java.lang.String prospectId, java.lang.String addressTypeId, java.lang.String street, java.lang.String mx_manzana, java.lang.String mx_lote, java.lang.String mx_numExterior, java.lang.String mx_numInterior, java.lang.String mx_colonia, java.lang.String mx_municipio, java.lang.String mx_estado, java.lang.String mx_codPostal, java.lang.String mx_lada) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.TransunionResponse getTransunionDecisionResponse(java.lang.String prospectId, java.lang.String bursolnum) throws java.rmi.RemoteException;
    public java.lang.String CRRTRMSVPWD(java.lang.String usuario, java.lang.String password, java.lang.String consultasMaximas, java.lang.String indTest) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.TSafiCreditosMovs[] getTSafiCreditosMovs(java.lang.String creditoId, java.lang.String amortiCreId, java.util.Calendar fechaOperacion, java.lang.String tipoSaldo, java.lang.String idProspecto) throws java.rmi.RemoteException;
    public void notificationDisbursementInfusion() throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse depositNotification(java.lang.String prospectusId, java.lang.String mail, java.lang.String safiClientId, java.lang.String originAccount, java.lang.String originBank, java.lang.String destinationAccount, java.lang.String despositAmount, java.lang.String depositDate, java.lang.String description, java.lang.String savingsAccountId) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse notificationValidatedSignature(java.lang.String prospectusId, java.lang.String projectLoanId) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse aplicationPublicationInvestor(java.lang.String aplicationPublicationInvestorDTOJSON) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse buroReprocess(com.mx.kubo.sgbws.models.dto.ReprocessBuroDataDTO reprocessBuroDataDTO) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.SpScoreKubo getClientRisk(java.lang.String clientId, java.lang.String bur_solnum, java.lang.String homeType, java.lang.String gender, java.lang.String age, java.lang.String businessType) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.BurResponse getProspectIDProvider(java.lang.String user, java.lang.String password, java.lang.String prospectId, java.lang.String indCreditCard, java.lang.String creditCardTermination, java.lang.String indmortgage, java.lang.String indCarLoan) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.BurResume getBurResume(java.lang.String user, java.lang.String password, java.lang.String burSol) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.BurResponse[] getBurScores(java.lang.String user, java.lang.String password, java.lang.String prospectId) throws java.rmi.RemoteException;
    public boolean getKuboRulesEvaluation(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse phoneUpdate(com.soa.webServices.request.PhoneUpdateRequest phoneUpdateRequest) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse homeUpdate(com.soa.webServices.request.HomeUpdateRequest homeUpdateRequest) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse referencesUpdate(com.soa.webServices.request.ReferencesUpdateRequest req) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse updateIfe(com.soa.webServices.request.VDocumentsIfe doc) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse documentsReview(com.soa.webServices.request.DocumentsReviewRequest docs) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse consultaCirculo(java.lang.String cadenaConsulta) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse consultaCirculoIdProvider(java.lang.String prospectId) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.BurGraphic[] getBurGraphic(java.lang.String burSolNum, java.lang.String[] order) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.Vtbur_infocalkubo getVtbur_infocalkubo(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.Vtbur_infocte getVtbur_infocte(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.Vtbur_infodircte[] getVtbur_infodircte(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.Vtbur_infocredcte_vig[] getVtbur_infocredcte_vig(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.Vtbur_infocredcte_c[] getVtbur_infocredcte_c(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.Vtbur_infocredcte_m[] getVtbur_infocredcte_m(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.ProspectBCRiskResponse getProspectBCRisk(com.soa.webServices.request.BCRiskRequest request) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.Vtbur_infocnsltult[] getVtbur_infocnsltult(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.Vtbur_infocnsltms[] getVtbur_infocnsltms(java.lang.String bursolnum) throws java.rmi.RemoteException;
    public com.soa.webServices.responses.WsSgbResponse prospectPhone(java.lang.String prospectId, java.lang.String phoneNumber, java.lang.String phoneType, java.lang.String operation) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.TSafiPosicionInt[] getTSafiPosicionInt(java.lang.String creditoid) throws java.rmi.RemoteException;
    public com.soa.model.businessobject.TSafiPagosCuota[] getTSafiPagosCuota(java.lang.String creditoid) throws java.rmi.RemoteException;
}
