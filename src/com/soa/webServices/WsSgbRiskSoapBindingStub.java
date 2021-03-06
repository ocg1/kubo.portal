/**
 * WsSgbRiskSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices;

public class WsSgbRiskSoapBindingStub extends org.apache.axis.client.Stub implements com.soa.webServices.WsSgbRisk {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[61];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
        _initOperationDesc7();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("testConnection");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "testConnectionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("newProject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "projectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "productId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "amount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "companyId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mxTasa"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mxFrec"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mxNumPagos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mxComisionApertura"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "loan_type"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "is_collection_solution"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "newProjectReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVtbur_infoAlertaPrev");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infoAlertaPrev"));
        oper.setReturnClass(com.soa.model.businessobject.Vtbur_infoAlertaPrev[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infoAlertaPrevReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTSafiKiva");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "creditoId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "TSafiKiva"));
        oper.setReturnClass(com.soa.model.businessobject.TSafiKiva[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTSafiKivaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllDataBur");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "burSolNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "AllDataBur"));
        oper.setReturnClass(com.soa.webServices.responses.AllDataBur.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getAllDataBurReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getKuboRules");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "KuboRulesResponse"));
        oper.setReturnClass(com.soa.webServices.responses.KuboRulesResponse[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getKuboRulesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notificaPromesadePago");
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "notificaPromesadePagoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getClientRisk");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "clientId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bur_solnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "homeType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "gender"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "age"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "businessType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "SpScoreKubo"));
        oper.setReturnClass(com.soa.model.businessobject.SpScoreKubo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getClientRiskReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProspectIDProvider");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "indCreditCard"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "creditCardTermination"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "indmortgage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "indCarLoan"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurResponse"));
        oper.setReturnClass(com.soa.model.businessobject.BurResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getProspectIDProviderReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getKuboRulesEvaluation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getKuboRulesEvaluationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("newProjectDTO");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "newProjectRequestDTO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "NewProjectRequestDTO"), com.mx.kubo.sgbws.models.dto.NewProjectRequestDTO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "newProjectDTOReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("phoneUpdate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "phoneUpdateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.webServices.soa.com", "PhoneUpdateRequest"), com.soa.webServices.request.PhoneUpdateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "phoneUpdateReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("homeUpdate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "homeUpdateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.webServices.soa.com", "HomeUpdateRequest"), com.soa.webServices.request.HomeUpdateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "homeUpdateReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("referencesUpdate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.webServices.soa.com", "ReferencesUpdateRequest"), com.soa.webServices.request.ReferencesUpdateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "referencesUpdateReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateIfe");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "doc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.webServices.soa.com", "VDocumentsIfe"), com.soa.webServices.request.VDocumentsIfe.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "updateIfeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultaBuro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "cadenaConsulta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "consultaBuroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProspectProspector");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurResponse"));
        oper.setReturnClass(com.soa.model.businessobject.BurResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getProspectProspectorReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProspectBC");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurResponse"));
        oper.setReturnClass(com.soa.model.businessobject.BurResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getProspectBCReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("prospectAdmin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "firstName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "secondName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "surName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "aditSurName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "birthday"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "rfc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "street"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_manzana"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_lote"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_numExterior"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_numInterior"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_colonia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_municipio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_estado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_codPostal"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_lada"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "phoneNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "curp"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "folio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "monto_Solicitado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectAdminReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultaCirculo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "cadenaConsulta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "consultaCirculoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultaCirculoIdProvider");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "consultaCirculoIdProviderReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBurGraphic");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "burSolNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "order"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurGraphic"));
        oper.setReturnClass(com.soa.model.businessobject.BurGraphic[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getBurGraphicReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVtbur_infocalkubo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocalkubo"));
        oper.setReturnClass(com.soa.model.businessobject.Vtbur_infocalkubo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocalkuboReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVtbur_infocte");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocte"));
        oper.setReturnClass(com.soa.model.businessobject.Vtbur_infocte.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocteReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVtbur_infodircte");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infodircte"));
        oper.setReturnClass(com.soa.model.businessobject.Vtbur_infodircte[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infodircteReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVtbur_infocredcte_vig");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocredcte_vig"));
        oper.setReturnClass(com.soa.model.businessobject.Vtbur_infocredcte_vig[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocredcte_vigReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVtbur_infocredcte_c");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocredcte_c"));
        oper.setReturnClass(com.soa.model.businessobject.Vtbur_infocredcte_c[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocredcte_cReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVtbur_infocredcte_m");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocredcte_m"));
        oper.setReturnClass(com.soa.model.businessobject.Vtbur_infocredcte_m[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocredcte_mReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProspectBCRisk");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.webServices.soa.com", "BCRiskRequest"), com.soa.webServices.request.BCRiskRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ProspectBCRiskResponse"));
        oper.setReturnClass(com.soa.webServices.responses.ProspectBCRiskResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getProspectBCRiskReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateProspect");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "varId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "value"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "updateProspectReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("changePhone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "phoneTypeId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "phoneNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "changePhoneReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("changeAddress");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "addressTypeId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "street"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_manzana"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_lote"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_numExterior"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_numInterior"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_colonia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_municipio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_estado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_codPostal"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mx_lada"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "changeAddressReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTransunionDecisionResponse");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "TransunionResponse"));
        oper.setReturnClass(com.soa.webServices.responses.TransunionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTransunionDecisionResponseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVtbur_infocnsltult");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocnsltult"));
        oper.setReturnClass(com.soa.model.businessobject.Vtbur_infocnsltult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocnsltultReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVtbur_infocnsltms");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocnsltms"));
        oper.setReturnClass(com.soa.model.businessobject.Vtbur_infocnsltms[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocnsltmsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("prospectPhone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "phoneNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "phoneType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "operation"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectPhoneReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTSafiPosicionInt");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "creditoid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "TSafiPosicionInt"));
        oper.setReturnClass(com.soa.model.businessobject.TSafiPosicionInt[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTSafiPosicionIntReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTSafiPagosCuota");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "creditoid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "TSafiPagosCuota"));
        oper.setReturnClass(com.soa.model.businessobject.TSafiPagosCuota[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTSafiPagosCuotaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateCredit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "projectLoanId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "companyId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "varId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "newValue"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "updateCreditReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notificationValidatedSignature");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectusId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "projectLoanId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "notificationValidatedSignatureReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("aplicationPublicationInvestor");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "aplicationPublicationInvestorDTOJSON"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "aplicationPublicationInvestorReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buroReprocess");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "reprocessBuroDataDTO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "ReprocessBuroDataDTO"), com.mx.kubo.sgbws.models.dto.ReprocessBuroDataDTO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "buroReprocessReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPromotorData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectusArray"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "activityTypeId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "statusId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getPromotorDataReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("applicationsLogs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "cliProId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "indCliPro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ApplicationLogResponse"));
        oper.setReturnClass(com.soa.webServices.responses.ApplicationLogResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "applicationsLogsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("creditsLogs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "cliProId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "indCliPro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ApplicationLogResponse"));
        oper.setReturnClass(com.soa.webServices.responses.ApplicationLogResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "creditsLogsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("recoveryLogs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "cliProId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "indCliPro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ApplicationLogResponse"));
        oper.setReturnClass(com.soa.webServices.responses.ApplicationLogResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "recoveryLogsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eventsLogs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "cliProId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "indCliPro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ApplicationLogResponse"));
        oper.setReturnClass(com.soa.webServices.responses.ApplicationLogResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "eventsLogsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProspectRisk");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.webServices.soa.com", "BCRiskRequest"), com.soa.webServices.request.BCRiskRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "SpScoreKubo"));
        oper.setReturnClass(com.soa.model.businessobject.SpScoreKubo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getProspectRiskReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("dispararCierreDiario");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "cierreDiarioRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.webServices.soa.com", "SGBCierreDiarioRequest"), com.soa.webServices.request.SGBCierreDiarioRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "SGBCierreDiarioResponse"));
        oper.setReturnClass(com.soa.webServices.responses.SGBCierreDiarioResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "dispararCierreDiarioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultarCierreDiario");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "consultaCierreDiarioRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.webServices.soa.com", "SGBConsultaCierreDiarioRequest"), com.soa.webServices.request.SGBConsultaCierreDiarioRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "SGBConsultaCierreDiarioResponse"));
        oper.setReturnClass(com.soa.webServices.responses.SGBConsultaCierreDiarioResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "consultarCierreDiarioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notificationDisbursementInfusion");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("depositNotification");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectusId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "mail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "safiClientId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "originAccount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "originBank"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "destinationAccount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "despositAmount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "depositDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "description"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "savingsAccountId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "depositNotificationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CRRTRMSVPWD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "usuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "consultasMaximas"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "indTest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "CRRTRMSVPWDReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTSafiCreditosMovs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "creditoId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "amortiCreId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "fechaOperacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "tipoSaldo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "idProspecto"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "TSafiCreditosMovs"));
        oper.setReturnClass(com.soa.model.businessobject.TSafiCreditosMovs[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTSafiCreditosMovsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTSafiCuentasAhoMovDep");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "cuentaAhoID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "fecha"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "natMovimiento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "TSafiCuentasAhoMovDep"));
        oper.setReturnClass(com.soa.model.businessobject.TSafiCuentasAhoMovDep[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTSafiCuentasAhoMovDepReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getFechaCorte");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        oper.setReturnClass(java.util.Calendar.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getFechaCorteReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[55] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVtbur_infoAlertaInc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "bursolnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infoAlertaInc"));
        oper.setReturnClass(com.soa.model.businessobject.Vtbur_infoAlertaInc[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infoAlertaIncReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[56] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("evaluationLogs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "cliProId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "indCliPro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ApplicationLogResponse"));
        oper.setReturnClass(com.soa.webServices.responses.ApplicationLogResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "evaluationLogsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[57] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBurResume");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "burSol"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurResume"));
        oper.setReturnClass(com.soa.model.businessobject.BurResume.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getBurResumeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[58] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBurScores");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurResponse"));
        oper.setReturnClass(com.soa.model.businessobject.BurResponse[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "getBurScoresReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[59] = oper;

    }

    private static void _initOperationDesc7(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("documentsReview");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://webServices.soa.com", "docs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.webServices.soa.com", "DocumentsReviewRequest"), com.soa.webServices.request.DocumentsReviewRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse"));
        oper.setReturnClass(com.soa.webServices.responses.WsSgbResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://webServices.soa.com", "documentsReviewReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[60] = oper;

    }

    public WsSgbRiskSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public WsSgbRiskSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public WsSgbRiskSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurGraphic");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.BurGraphic.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurResponse");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.BurResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurResume");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.BurResume.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "EstatusCierre");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.EstatusCierre.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Logs");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Logs.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "SpScoreKubo");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.SpScoreKubo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "TSafiCreditosMovs");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.TSafiCreditosMovs.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "TSafiCuentasAhoMovDep");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.TSafiCuentasAhoMovDep.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "TSafiKiva");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.TSafiKiva.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "TSafiPagosCuota");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.TSafiPagosCuota.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "TSafiPosicionInt");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.TSafiPosicionInt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "VApplicationLog");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.VApplicationLog.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vbur_maxoto");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vbur_maxoto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infoAlertaInc");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infoAlertaInc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infoAlertaPrev");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infoAlertaPrev.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocalkubo");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocalkubo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocnsltms");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocnsltms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocnsltult");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocnsltult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocredcte_c");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocredcte_c.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocredcte_m");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocredcte_m.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocredcte_vig");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocredcte_vig.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocte");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocte.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infodircte");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infodircte.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_scaa");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_scaa.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_scag");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_scag.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_scapm");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_scapm.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_sccc");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_sccc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_sccpm");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_sccpm.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_spca");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_spca.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "NewProjectRequestDTO");
            cachedSerQNames.add(qName);
            cls = com.mx.kubo.sgbws.models.dto.NewProjectRequestDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "ReprocessBuroDataDTO");
            cachedSerQNames.add(qName);
            cls = com.mx.kubo.sgbws.models.dto.ReprocessBuroDataDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.webServices.soa.com", "BCRiskRequest");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.request.BCRiskRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.webServices.soa.com", "DocumentsReviewRequest");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.request.DocumentsReviewRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.webServices.soa.com", "HomeUpdateRequest");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.request.HomeUpdateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.webServices.soa.com", "PhoneUpdateRequest");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.request.PhoneUpdateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.webServices.soa.com", "ReferencesUpdateRequest");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.request.ReferencesUpdateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.webServices.soa.com", "SGBCierreDiarioRequest");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.request.SGBCierreDiarioRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.webServices.soa.com", "SGBConsultaCierreDiarioRequest");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.request.SGBConsultaCierreDiarioRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.webServices.soa.com", "VDocumentsIfe");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.request.VDocumentsIfe.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://responses.webServices.soa.com", "AllDataBur");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.responses.AllDataBur.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ApplicationLogResponse");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.responses.ApplicationLogResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://responses.webServices.soa.com", "KuboRulesResponse");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.responses.KuboRulesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ProspectBCRiskResponse");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.responses.ProspectBCRiskResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://responses.webServices.soa.com", "SGBCierreDiarioResponse");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.responses.SGBCierreDiarioResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://responses.webServices.soa.com", "SGBConsultaCierreDiarioResponse");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.responses.SGBConsultaCierreDiarioResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://responses.webServices.soa.com", "TransunionResponse");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.responses.TransunionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://responses.webServices.soa.com", "WsSgbResponse");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.responses.WsSgbResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://util.webServices.soa.com", "Files");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.util.Files.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://util.webServices.soa.com", "InputParam");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.util.InputParam.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_BurGraphic");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.BurGraphic[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurGraphic");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_EstatusCierre");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.EstatusCierre[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "EstatusCierre");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Logs");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Logs[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Logs");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vbur_maxoto");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vbur_maxoto[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vbur_maxoto");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_infoAlertaInc");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infoAlertaInc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infoAlertaInc");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_infoAlertaPrev");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infoAlertaPrev[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infoAlertaPrev");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_infocnsltms");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocnsltms[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocnsltms");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_infocnsltult");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocnsltult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocnsltult");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_infocredcte_c");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocredcte_c[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocredcte_c");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_infocredcte_m");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocredcte_m[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocredcte_m");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_infocredcte_vig");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infocredcte_vig[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infocredcte_vig");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_infodircte");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_infodircte[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_infodircte");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_scaa");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_scaa[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_scaa");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_scag");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_scag[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_scag");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_scapm");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_scapm[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_scapm");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_sccc");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_sccc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_sccc");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_sccpm");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_sccpm[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_sccpm");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns2_Vtbur_spca");
            cachedSerQNames.add(qName);
            cls = com.soa.model.businessobject.Vtbur_spca[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_spca");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns5_Files");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.util.Files[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://util.webServices.soa.com", "Files");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webServices.soa.com", "ArrayOf_tns5_InputParam");
            cachedSerQNames.add(qName);
            cls = com.soa.webServices.util.InputParam[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://util.webServices.soa.com", "InputParam");
            qName2 = new javax.xml.namespace.QName("http://webServices.soa.com", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public java.lang.String testConnection() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "testConnection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse newProject(java.lang.String prospectId, java.lang.String projectId, java.lang.String productId, java.lang.String amount, java.lang.String companyId, java.lang.String mxTasa, java.lang.String mxFrec, java.lang.String mxNumPagos, java.lang.String mxComisionApertura, java.lang.String bursolnum, java.lang.String loan_type, java.lang.String is_collection_solution) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "newProject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectId, projectId, productId, amount, companyId, mxTasa, mxFrec, mxNumPagos, mxComisionApertura, bursolnum, loan_type, is_collection_solution});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.Vtbur_infoAlertaPrev[] getVtbur_infoAlertaPrev(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infoAlertaPrev"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.Vtbur_infoAlertaPrev[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.Vtbur_infoAlertaPrev[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.Vtbur_infoAlertaPrev[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.TSafiKiva[] getTSafiKiva(java.lang.String creditoId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTSafiKiva"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {creditoId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.TSafiKiva[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.TSafiKiva[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.TSafiKiva[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.AllDataBur getAllDataBur(java.lang.String burSolNum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getAllDataBur"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {burSolNum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.AllDataBur) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.AllDataBur) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.AllDataBur.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.KuboRulesResponse[] getKuboRules(java.lang.String prospectId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getKuboRules"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.KuboRulesResponse[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.KuboRulesResponse[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.KuboRulesResponse[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse notificaPromesadePago() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "notificaPromesadePago"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.SpScoreKubo getClientRisk(java.lang.String clientId, java.lang.String bur_solnum, java.lang.String homeType, java.lang.String gender, java.lang.String age, java.lang.String businessType) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getClientRisk"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {clientId, bur_solnum, homeType, gender, age, businessType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.SpScoreKubo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.SpScoreKubo) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.SpScoreKubo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.BurResponse getProspectIDProvider(java.lang.String user, java.lang.String password, java.lang.String prospectId, java.lang.String indCreditCard, java.lang.String creditCardTermination, java.lang.String indmortgage, java.lang.String indCarLoan) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getProspectIDProvider"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, password, prospectId, indCreditCard, creditCardTermination, indmortgage, indCarLoan});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.BurResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.BurResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.BurResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public boolean getKuboRulesEvaluation(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getKuboRulesEvaluation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse newProjectDTO(com.mx.kubo.sgbws.models.dto.NewProjectRequestDTO newProjectRequestDTO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "newProjectDTO"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {newProjectRequestDTO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse phoneUpdate(com.soa.webServices.request.PhoneUpdateRequest phoneUpdateRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "phoneUpdate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {phoneUpdateRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse homeUpdate(com.soa.webServices.request.HomeUpdateRequest homeUpdateRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "homeUpdate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {homeUpdateRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse referencesUpdate(com.soa.webServices.request.ReferencesUpdateRequest req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "referencesUpdate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse updateIfe(com.soa.webServices.request.VDocumentsIfe doc) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "updateIfe"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {doc});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse consultaBuro(java.lang.String cadenaConsulta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "consultaBuro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cadenaConsulta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.BurResponse getProspectProspector(java.lang.String user, java.lang.String password, java.lang.String prospectId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getProspectProspector"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, password, prospectId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.BurResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.BurResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.BurResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.BurResponse getProspectBC(java.lang.String user, java.lang.String password, java.lang.String prospectId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getProspectBC"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, password, prospectId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.BurResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.BurResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.BurResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse prospectAdmin(java.lang.String user, java.lang.String password, java.lang.String prospectId, java.lang.String firstName, java.lang.String secondName, java.lang.String surName, java.lang.String aditSurName, java.lang.String birthday, java.lang.String rfc, java.lang.String street, java.lang.String mx_manzana, java.lang.String mx_lote, java.lang.String mx_numExterior, java.lang.String mx_numInterior, java.lang.String mx_colonia, java.lang.String mx_municipio, java.lang.String mx_estado, java.lang.String mx_codPostal, java.lang.String mx_lada, java.lang.String phoneNumber, java.lang.String curp, java.lang.String folio, double monto_Solicitado) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectAdmin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, password, prospectId, firstName, secondName, surName, aditSurName, birthday, rfc, street, mx_manzana, mx_lote, mx_numExterior, mx_numInterior, mx_colonia, mx_municipio, mx_estado, mx_codPostal, mx_lada, phoneNumber, curp, folio, new java.lang.Double(monto_Solicitado)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse consultaCirculo(java.lang.String cadenaConsulta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "consultaCirculo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cadenaConsulta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse consultaCirculoIdProvider(java.lang.String prospectId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "consultaCirculoIdProvider"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.BurGraphic[] getBurGraphic(java.lang.String burSolNum, java.lang.String[] order) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getBurGraphic"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {burSolNum, order});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.BurGraphic[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.BurGraphic[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.BurGraphic[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.Vtbur_infocalkubo getVtbur_infocalkubo(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocalkubo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.Vtbur_infocalkubo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.Vtbur_infocalkubo) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.Vtbur_infocalkubo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.Vtbur_infocte getVtbur_infocte(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocte"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.Vtbur_infocte) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.Vtbur_infocte) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.Vtbur_infocte.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.Vtbur_infodircte[] getVtbur_infodircte(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infodircte"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.Vtbur_infodircte[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.Vtbur_infodircte[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.Vtbur_infodircte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.Vtbur_infocredcte_vig[] getVtbur_infocredcte_vig(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocredcte_vig"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.Vtbur_infocredcte_vig[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.Vtbur_infocredcte_vig[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.Vtbur_infocredcte_vig[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.Vtbur_infocredcte_c[] getVtbur_infocredcte_c(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocredcte_c"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.Vtbur_infocredcte_c[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.Vtbur_infocredcte_c[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.Vtbur_infocredcte_c[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.Vtbur_infocredcte_m[] getVtbur_infocredcte_m(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocredcte_m"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.Vtbur_infocredcte_m[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.Vtbur_infocredcte_m[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.Vtbur_infocredcte_m[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.ProspectBCRiskResponse getProspectBCRisk(com.soa.webServices.request.BCRiskRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getProspectBCRisk"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.ProspectBCRiskResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.ProspectBCRiskResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.ProspectBCRiskResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse updateProspect(java.lang.String userId, java.lang.String prospectId, java.lang.String varId, java.lang.String value) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "updateProspect"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userId, prospectId, varId, value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse changePhone(java.lang.String userId, java.lang.String prospectId, java.lang.String phoneTypeId, java.lang.String phoneNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "changePhone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userId, prospectId, phoneTypeId, phoneNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse changeAddress(java.lang.String userId, java.lang.String prospectId, java.lang.String addressTypeId, java.lang.String street, java.lang.String mx_manzana, java.lang.String mx_lote, java.lang.String mx_numExterior, java.lang.String mx_numInterior, java.lang.String mx_colonia, java.lang.String mx_municipio, java.lang.String mx_estado, java.lang.String mx_codPostal, java.lang.String mx_lada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "changeAddress"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userId, prospectId, addressTypeId, street, mx_manzana, mx_lote, mx_numExterior, mx_numInterior, mx_colonia, mx_municipio, mx_estado, mx_codPostal, mx_lada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.TransunionResponse getTransunionDecisionResponse(java.lang.String prospectId, java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTransunionDecisionResponse"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectId, bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.TransunionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.TransunionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.TransunionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.Vtbur_infocnsltult[] getVtbur_infocnsltult(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocnsltult"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.Vtbur_infocnsltult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.Vtbur_infocnsltult[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.Vtbur_infocnsltult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.Vtbur_infocnsltms[] getVtbur_infocnsltms(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infocnsltms"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.Vtbur_infocnsltms[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.Vtbur_infocnsltms[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.Vtbur_infocnsltms[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse prospectPhone(java.lang.String prospectId, java.lang.String phoneNumber, java.lang.String phoneType, java.lang.String operation) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "prospectPhone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectId, phoneNumber, phoneType, operation});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.TSafiPosicionInt[] getTSafiPosicionInt(java.lang.String creditoid) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTSafiPosicionInt"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {creditoid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.TSafiPosicionInt[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.TSafiPosicionInt[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.TSafiPosicionInt[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.TSafiPagosCuota[] getTSafiPagosCuota(java.lang.String creditoid) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTSafiPagosCuota"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {creditoid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.TSafiPagosCuota[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.TSafiPagosCuota[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.TSafiPagosCuota[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse updateCredit(java.lang.String userId, java.lang.String projectLoanId, java.lang.String prospectId, java.lang.String companyId, java.lang.String varId, java.lang.String newValue) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "updateCredit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userId, projectLoanId, prospectId, companyId, varId, newValue});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse notificationValidatedSignature(java.lang.String prospectusId, java.lang.String projectLoanId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "notificationValidatedSignature"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectusId, projectLoanId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse aplicationPublicationInvestor(java.lang.String aplicationPublicationInvestorDTOJSON) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "aplicationPublicationInvestor"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {aplicationPublicationInvestorDTOJSON});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse buroReprocess(com.mx.kubo.sgbws.models.dto.ReprocessBuroDataDTO reprocessBuroDataDTO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "buroReprocess"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reprocessBuroDataDTO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String[] getPromotorData(java.lang.String[] prospectusArray, java.lang.String activityTypeId, java.lang.String statusId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getPromotorData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectusArray, activityTypeId, statusId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.ApplicationLogResponse applicationsLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "applicationsLogs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cliProId, indCliPro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.ApplicationLogResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.ApplicationLogResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.ApplicationLogResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.ApplicationLogResponse creditsLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "creditsLogs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cliProId, indCliPro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.ApplicationLogResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.ApplicationLogResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.ApplicationLogResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.ApplicationLogResponse recoveryLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "recoveryLogs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cliProId, indCliPro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.ApplicationLogResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.ApplicationLogResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.ApplicationLogResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.ApplicationLogResponse eventsLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "eventsLogs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cliProId, indCliPro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.ApplicationLogResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.ApplicationLogResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.ApplicationLogResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.SpScoreKubo getProspectRisk(com.soa.webServices.request.BCRiskRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getProspectRisk"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.SpScoreKubo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.SpScoreKubo) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.SpScoreKubo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.SGBCierreDiarioResponse dispararCierreDiario(com.soa.webServices.request.SGBCierreDiarioRequest cierreDiarioRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "dispararCierreDiario"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cierreDiarioRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.SGBCierreDiarioResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.SGBCierreDiarioResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.SGBCierreDiarioResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.SGBConsultaCierreDiarioResponse consultarCierreDiario(com.soa.webServices.request.SGBConsultaCierreDiarioRequest consultaCierreDiarioRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "consultarCierreDiario"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaCierreDiarioRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.SGBConsultaCierreDiarioResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.SGBConsultaCierreDiarioResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.SGBConsultaCierreDiarioResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void notificationDisbursementInfusion() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "notificationDisbursementInfusion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse depositNotification(java.lang.String prospectusId, java.lang.String mail, java.lang.String safiClientId, java.lang.String originAccount, java.lang.String originBank, java.lang.String destinationAccount, java.lang.String despositAmount, java.lang.String depositDate, java.lang.String description, java.lang.String savingsAccountId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "depositNotification"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectusId, mail, safiClientId, originAccount, originBank, destinationAccount, despositAmount, depositDate, description, savingsAccountId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String CRRTRMSVPWD(java.lang.String usuario, java.lang.String password, java.lang.String consultasMaximas, java.lang.String indTest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "CRRTRMSVPWD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {usuario, password, consultasMaximas, indTest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.TSafiCreditosMovs[] getTSafiCreditosMovs(java.lang.String creditoId, java.lang.String amortiCreId, java.util.Calendar fechaOperacion, java.lang.String tipoSaldo, java.lang.String idProspecto) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTSafiCreditosMovs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {creditoId, amortiCreId, fechaOperacion, tipoSaldo, idProspecto});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.TSafiCreditosMovs[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.TSafiCreditosMovs[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.TSafiCreditosMovs[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.TSafiCuentasAhoMovDep[] getTSafiCuentasAhoMovDep(java.lang.String cuentaAhoID, java.util.Calendar fecha, java.lang.String natMovimiento) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getTSafiCuentasAhoMovDep"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cuentaAhoID, fecha, natMovimiento});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.TSafiCuentasAhoMovDep[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.TSafiCuentasAhoMovDep[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.TSafiCuentasAhoMovDep[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.util.Calendar getFechaCorte() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getFechaCorte"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.util.Calendar) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.util.Calendar) org.apache.axis.utils.JavaUtils.convert(_resp, java.util.Calendar.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.Vtbur_infoAlertaInc[] getVtbur_infoAlertaInc(java.lang.String bursolnum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[56]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getVtbur_infoAlertaInc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bursolnum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.Vtbur_infoAlertaInc[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.Vtbur_infoAlertaInc[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.Vtbur_infoAlertaInc[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.ApplicationLogResponse evaluationLogs(java.lang.String cliProId, java.lang.String indCliPro) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[57]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "evaluationLogs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cliProId, indCliPro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.ApplicationLogResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.ApplicationLogResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.ApplicationLogResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.BurResume getBurResume(java.lang.String user, java.lang.String password, java.lang.String burSol) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[58]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getBurResume"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, password, burSol});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.BurResume) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.BurResume) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.BurResume.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.model.businessobject.BurResponse[] getBurScores(java.lang.String user, java.lang.String password, java.lang.String prospectId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[59]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "getBurScores"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, password, prospectId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.model.businessobject.BurResponse[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.model.businessobject.BurResponse[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.model.businessobject.BurResponse[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.soa.webServices.responses.WsSgbResponse documentsReview(com.soa.webServices.request.DocumentsReviewRequest docs) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[60]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webServices.soa.com", "documentsReview"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {docs});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.soa.webServices.responses.WsSgbResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.soa.webServices.responses.WsSgbResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.soa.webServices.responses.WsSgbResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
