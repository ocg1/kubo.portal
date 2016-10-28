/**
 * WsSgbRiskServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices;

public class WsSgbRiskServiceLocator extends org.apache.axis.client.Service implements com.soa.webServices.WsSgbRiskService {

    public WsSgbRiskServiceLocator() {
    }


    public WsSgbRiskServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsSgbRiskServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WsSgbRisk
    private java.lang.String WsSgbRisk_address = "http://192.168.11.222:8580/Servicios_SGB/services/WsSgbRisk";

    public java.lang.String getWsSgbRiskAddress() {
        return WsSgbRisk_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WsSgbRiskWSDDServiceName = "WsSgbRisk";

    public java.lang.String getWsSgbRiskWSDDServiceName() {
        return WsSgbRiskWSDDServiceName;
    }

    public void setWsSgbRiskWSDDServiceName(java.lang.String name) {
        WsSgbRiskWSDDServiceName = name;
    }

    public com.soa.webServices.WsSgbRisk getWsSgbRisk() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WsSgbRisk_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWsSgbRisk(endpoint);
    }

    public com.soa.webServices.WsSgbRisk getWsSgbRisk(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.soa.webServices.WsSgbRiskSoapBindingStub _stub = new com.soa.webServices.WsSgbRiskSoapBindingStub(portAddress, this);
            _stub.setPortName(getWsSgbRiskWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWsSgbRiskEndpointAddress(java.lang.String address) {
        WsSgbRisk_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.soa.webServices.WsSgbRisk.class.isAssignableFrom(serviceEndpointInterface)) {
                com.soa.webServices.WsSgbRiskSoapBindingStub _stub = new com.soa.webServices.WsSgbRiskSoapBindingStub(new java.net.URL(WsSgbRisk_address), this);
                _stub.setPortName(getWsSgbRiskWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WsSgbRisk".equals(inputPortName)) {
            return getWsSgbRisk();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webServices.soa.com", "WsSgbRiskService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webServices.soa.com", "WsSgbRisk"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WsSgbRisk".equals(portName)) {
            setWsSgbRiskEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
