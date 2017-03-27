/**
 * PublicProyectSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public class PublicProyectSoapBindingStub extends org.apache.axis.client.Stub implements mx.com.kubo.kubows.PublicProyect {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[24];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("publishProyect");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "EditorEstatusRequest"), mx.com.kubo.kubows.EditorEstatusRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "publishProyectReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLista_publicaciones_pendientes");
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getLista_publicaciones_pendientesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLista_publicaciones_pendientes_inversion");
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getLista_publicaciones_pendientes_inversionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLista_telefonos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "prospectus_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "company_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "PhoneResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.PhoneResponse[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getLista_telefonosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDepositos_no_identificados");
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getDepositos_no_identificadosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDocumentsByProspect");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "prospectus_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "proyect_loan"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "safi_credit_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "FileResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.FileResponse[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getDocumentsByProspectReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setProspectRisk");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ProspectRiskRequest"), mx.com.kubo.kubows.ProspectRiskRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "setProspectRiskReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notificar");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "NotificadorConfigRequest"), mx.com.kubo.kubows.NotificadorConfigRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "notificarReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReferencesByProspect");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "prospectus_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "company_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ReferencesResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.ReferencesResponse[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getReferencesByProspectReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDatosModelo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "prospectusId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "bur_sol_num"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getDatosModeloReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("changeInvStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "inv_request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ChangeInvStatusRequest"), mx.com.kubo.kubows.ChangeInvStatusRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "changeInvStatusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificationStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "prospectus_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "company_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "proyect_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "status"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "verificationStatusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setTransunionCalif");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "transunion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "TransUnionCalifRequest"), mx.com.kubo.kubows.TransUnionCalifRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "setTransunionCalifReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("acceptedFile");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "acceptedfile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "AcceptedFileRequest"), mx.com.kubo.kubows.AcceptedFileRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "acceptedFileReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notificaRetiros");
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "notificaRetirosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notificaDepositos");
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "notificaDepositosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("enviaSMS");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "SMSRequestService"), mx.com.kubo.kubows.SMSRequestService.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "enviaSMSReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notificaSMSSinPublicar");
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "notificaSMSSinPublicarReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setEstatus_tienda");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "flag"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "setEstatus_tiendaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getEstatus_tienda");
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getEstatus_tiendaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getEjecucion_cierre_del_dia");
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getEjecucion_cierre_del_diaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setEjecucion_cierre_del_dia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "flag"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "setEjecucion_cierre_del_diaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addTagInfusion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "taginfusionreq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "TagInfusionRequest"), mx.com.kubo.kubows.TagInfusionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "addTagInfusionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("clientNotification");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ClientNotificationRequest"), mx.com.kubo.kubows.ClientNotificationRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        oper.setReturnClass(mx.com.kubo.kubows.WsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "clientNotificationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

    }

    public PublicProyectSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public PublicProyectSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public PublicProyectSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "AcceptedFileRequest");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.AcceptedFileRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ArrayOf_xsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ChangeInvStatusRequest");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.ChangeInvStatusRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ClientNotificationRequest");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.ClientNotificationRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "EditorEstatusRequest");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.EditorEstatusRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "FileResponse");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.FileResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "NotificadorConfigRequest");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.NotificadorConfigRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "PhoneResponse");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.PhoneResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ProspectRiskRequest");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.ProspectRiskRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ReferencesResponse");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.ReferencesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "SMSRequestService");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.SMSRequestService.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "TagInfusionRequest");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.TagInfusionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "TransUnionCalifRequest");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.TransUnionCalifRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse");
            cachedSerQNames.add(qName);
            cls = mx.com.kubo.kubows.WsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

    public mx.com.kubo.kubows.WsResponse publishProyect(mx.com.kubo.kubows.EditorEstatusRequest request) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "publishProyect"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse getLista_publicaciones_pendientes() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getLista_publicaciones_pendientes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse getLista_publicaciones_pendientes_inversion() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getLista_publicaciones_pendientes_inversion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.PhoneResponse[] getLista_telefonos(java.lang.String prospectus_id, java.lang.String company_id) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getLista_telefonos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectus_id, company_id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.PhoneResponse[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.PhoneResponse[]) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.PhoneResponse[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse getDepositos_no_identificados() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getDepositos_no_identificados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.FileResponse[] getDocumentsByProspect(java.lang.String prospectus_id, java.lang.String proyect_loan, java.lang.String safi_credit_id) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getDocumentsByProspect"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectus_id, proyect_loan, safi_credit_id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.FileResponse[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.FileResponse[]) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.FileResponse[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse setProspectRisk(mx.com.kubo.kubows.ProspectRiskRequest request) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "setProspectRisk"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse notificar(mx.com.kubo.kubows.NotificadorConfigRequest request) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "notificar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.ReferencesResponse[] getReferencesByProspect(java.lang.String prospectus_id, java.lang.String company_id) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getReferencesByProspect"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectus_id, company_id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.ReferencesResponse[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.ReferencesResponse[]) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.ReferencesResponse[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse getDatosModelo(java.lang.String prospectusId, java.lang.String bur_sol_num) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getDatosModelo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectusId, bur_sol_num});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse changeInvStatus(mx.com.kubo.kubows.ChangeInvStatusRequest inv_request) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "changeInvStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inv_request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse verificationStatus(java.lang.String prospectus_id, java.lang.String company_id, java.lang.String proyect_id, java.lang.String status) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "verificationStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {prospectus_id, company_id, proyect_id, status});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse setTransunionCalif(mx.com.kubo.kubows.TransUnionCalifRequest transunion) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "setTransunionCalif"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {transunion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse acceptedFile(mx.com.kubo.kubows.AcceptedFileRequest acceptedfile) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "acceptedFile"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {acceptedfile});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse notificaRetiros() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "notificaRetiros"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse notificaDepositos() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "notificaDepositos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse enviaSMS(mx.com.kubo.kubows.SMSRequestService request) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "enviaSMS"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse notificaSMSSinPublicar() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "notificaSMSSinPublicar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse setEstatus_tienda(java.lang.String flag) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "setEstatus_tienda"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {flag});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse getEstatus_tienda() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getEstatus_tienda"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse getEjecucion_cierre_del_dia() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "getEjecucion_cierre_del_dia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse setEjecucion_cierre_del_dia(java.lang.String flag) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "setEjecucion_cierre_del_dia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {flag});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse addTagInfusion(mx.com.kubo.kubows.TagInfusionRequest taginfusionreq) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "addTagInfusion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {taginfusionreq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public mx.com.kubo.kubows.WsResponse clientNotification(mx.com.kubo.kubows.ClientNotificationRequest request) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "clientNotification"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (mx.com.kubo.kubows.WsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (mx.com.kubo.kubows.WsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, mx.com.kubo.kubows.WsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
