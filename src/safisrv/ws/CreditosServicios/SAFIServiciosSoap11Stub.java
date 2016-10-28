/**
 * SAFIServiciosSoap11Stub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CreditosServicios;

public class SAFIServiciosSoap11Stub extends org.apache.axis.client.Stub implements safisrv.ws.CreditosServicios.SAFIServicios {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[9];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("creaCredito");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "creaCreditoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">creaCreditoRequest"), safisrv.ws.CreditosServicios.CreaCreditoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">creaCreditoResponse"));
        oper.setReturnClass(safisrv.ws.CreditosServicios.CreaCreditoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "creaCreditoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("segurosVida");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "segurosVidaRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">segurosVidaRequest"), safisrv.ws.CreditosServicios.SegurosVidaRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">segurosVidaResponse"));
        oper.setReturnClass(safisrv.ws.CreditosServicios.SegurosVidaResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "segurosVidaResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultaDetallePagos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "consultaDetallePagosRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaDetallePagosRequest"), safisrv.ws.CreditosServicios.ConsultaDetallePagosRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaDetallePagosResponse"));
        oper.setReturnClass(safisrv.ws.CreditosServicios.ConsultaDetallePagosResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "consultaDetallePagosResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("solicitudCredito");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "solicitudCreditoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">solicitudCreditoRequest"), safisrv.ws.CreditosServicios.SolicitudCreditoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">solicitudCreditoResponse"));
        oper.setReturnClass(safisrv.ws.CreditosServicios.SolicitudCreditoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "solicitudCreditoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("simuladorCuotaCredito");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "simuladorCuotaCreditoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">simuladorCuotaCreditoRequest"), safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">simuladorCuotaCreditoResponse"));
        oper.setReturnClass(safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "simuladorCuotaCreditoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelaCredito");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "cancelaCreditoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">cancelaCreditoRequest"), safisrv.ws.CreditosServicios.CancelaCreditoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">cancelaCreditoResponse"));
        oper.setReturnClass(safisrv.ws.CreditosServicios.CancelaCreditoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "cancelaCreditoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("reestrucCredito");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "reestrucCreditoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">reestrucCreditoRequest"), safisrv.ws.CreditosServicios.ReestrucCreditoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">reestrucCreditoResponse"));
        oper.setReturnClass(safisrv.ws.CreditosServicios.ReestrucCreditoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "reestrucCreditoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultaActividadCredito");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "consultaActividadCreditoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaActividadCreditoRequest"), safisrv.ws.CreditosServicios.ConsultaActividadCreditoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaActividadCreditoResponse"));
        oper.setReturnClass(safisrv.ws.CreditosServicios.ConsultaActividadCreditoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "consultaActividadCreditoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("altaProspecto");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "altaProspectoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">altaProspectoRequest"), safisrv.ws.CreditosServicios.AltaProspectoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">altaProspectoResponse"));
        oper.setReturnClass(safisrv.ws.CreditosServicios.AltaProspectoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "altaProspectoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

    }

    public SAFIServiciosSoap11Stub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SAFIServiciosSoap11Stub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SAFIServiciosSoap11Stub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">altaProspectoRequest");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.AltaProspectoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">altaProspectoResponse");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.AltaProspectoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">cancelaCreditoRequest");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.CancelaCreditoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">cancelaCreditoResponse");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.CancelaCreditoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaActividadCreditoRequest");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.ConsultaActividadCreditoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaActividadCreditoResponse");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.ConsultaActividadCreditoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaDetallePagosRequest");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.ConsultaDetallePagosRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaDetallePagosResponse");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.ConsultaDetallePagosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">creaCreditoRequest");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.CreaCreditoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">creaCreditoResponse");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.CreaCreditoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">reestrucCreditoRequest");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.ReestrucCreditoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">reestrucCreditoResponse");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.ReestrucCreditoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">segurosVidaRequest");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.SegurosVidaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">segurosVidaResponse");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.SegurosVidaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">simuladorCuotaCreditoRequest");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">simuladorCuotaCreditoResponse");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">solicitudCreditoRequest");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.SolicitudCreditoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">solicitudCreditoResponse");
            cachedSerQNames.add(qName);
            cls = safisrv.ws.CreditosServicios.SolicitudCreditoResponse.class;
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

    public safisrv.ws.CreditosServicios.CreaCreditoResponse creaCredito(safisrv.ws.CreditosServicios.CreaCreditoRequest creaCreditoRequest) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "creaCredito"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {creaCreditoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (safisrv.ws.CreditosServicios.CreaCreditoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (safisrv.ws.CreditosServicios.CreaCreditoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, safisrv.ws.CreditosServicios.CreaCreditoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public safisrv.ws.CreditosServicios.SegurosVidaResponse segurosVida(safisrv.ws.CreditosServicios.SegurosVidaRequest segurosVidaRequest) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "segurosVida"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {segurosVidaRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (safisrv.ws.CreditosServicios.SegurosVidaResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (safisrv.ws.CreditosServicios.SegurosVidaResponse) org.apache.axis.utils.JavaUtils.convert(_resp, safisrv.ws.CreditosServicios.SegurosVidaResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public safisrv.ws.CreditosServicios.ConsultaDetallePagosResponse consultaDetallePagos(safisrv.ws.CreditosServicios.ConsultaDetallePagosRequest consultaDetallePagosRequest) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "consultaDetallePagos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaDetallePagosRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (safisrv.ws.CreditosServicios.ConsultaDetallePagosResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (safisrv.ws.CreditosServicios.ConsultaDetallePagosResponse) org.apache.axis.utils.JavaUtils.convert(_resp, safisrv.ws.CreditosServicios.ConsultaDetallePagosResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public safisrv.ws.CreditosServicios.SolicitudCreditoResponse solicitudCredito(safisrv.ws.CreditosServicios.SolicitudCreditoRequest solicitudCreditoRequest) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "solicitudCredito"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {solicitudCreditoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (safisrv.ws.CreditosServicios.SolicitudCreditoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (safisrv.ws.CreditosServicios.SolicitudCreditoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, safisrv.ws.CreditosServicios.SolicitudCreditoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse simuladorCuotaCredito(safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest simuladorCuotaCreditoRequest) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "simuladorCuotaCredito"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {simuladorCuotaCreditoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public safisrv.ws.CreditosServicios.CancelaCreditoResponse cancelaCredito(safisrv.ws.CreditosServicios.CancelaCreditoRequest cancelaCreditoRequest) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "cancelaCredito"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cancelaCreditoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (safisrv.ws.CreditosServicios.CancelaCreditoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (safisrv.ws.CreditosServicios.CancelaCreditoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, safisrv.ws.CreditosServicios.CancelaCreditoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public safisrv.ws.CreditosServicios.ReestrucCreditoResponse reestrucCredito(safisrv.ws.CreditosServicios.ReestrucCreditoRequest reestrucCreditoRequest) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "reestrucCredito"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reestrucCreditoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (safisrv.ws.CreditosServicios.ReestrucCreditoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (safisrv.ws.CreditosServicios.ReestrucCreditoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, safisrv.ws.CreditosServicios.ReestrucCreditoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public safisrv.ws.CreditosServicios.ConsultaActividadCreditoResponse consultaActividadCredito(safisrv.ws.CreditosServicios.ConsultaActividadCreditoRequest consultaActividadCreditoRequest) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "consultaActividadCredito"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaActividadCreditoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (safisrv.ws.CreditosServicios.ConsultaActividadCreditoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (safisrv.ws.CreditosServicios.ConsultaActividadCreditoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, safisrv.ws.CreditosServicios.ConsultaActividadCreditoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public safisrv.ws.CreditosServicios.AltaProspectoResponse altaProspecto(safisrv.ws.CreditosServicios.AltaProspectoRequest altaProspectoRequest) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "altaProspecto"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {altaProspectoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (safisrv.ws.CreditosServicios.AltaProspectoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (safisrv.ws.CreditosServicios.AltaProspectoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, safisrv.ws.CreditosServicios.AltaProspectoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
