/**
 * PublicProyectServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public class PublicProyectServiceLocator extends org.apache.axis.client.Service implements mx.com.kubo.kubows.PublicProyectService {

    public PublicProyectServiceLocator() {
    }


    public PublicProyectServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PublicProyectServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PublicProyect
    private java.lang.String PublicProyect_address = "http://192.168.11.222:8280/KuboServices/services/PublicProyect";

    public java.lang.String getPublicProyectAddress() {
        return PublicProyect_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PublicProyectWSDDServiceName = "PublicProyect";

    public java.lang.String getPublicProyectWSDDServiceName() {
        return PublicProyectWSDDServiceName;
    }

    public void setPublicProyectWSDDServiceName(java.lang.String name) {
        PublicProyectWSDDServiceName = name;
    }

    public mx.com.kubo.kubows.PublicProyect getPublicProyect() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PublicProyect_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPublicProyect(endpoint);
    }

    public mx.com.kubo.kubows.PublicProyect getPublicProyect(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mx.com.kubo.kubows.PublicProyectSoapBindingStub _stub = new mx.com.kubo.kubows.PublicProyectSoapBindingStub(portAddress, this);
            _stub.setPortName(getPublicProyectWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPublicProyectEndpointAddress(java.lang.String address) {
        PublicProyect_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (mx.com.kubo.kubows.PublicProyect.class.isAssignableFrom(serviceEndpointInterface)) {
                mx.com.kubo.kubows.PublicProyectSoapBindingStub _stub = new mx.com.kubo.kubows.PublicProyectSoapBindingStub(new java.net.URL(PublicProyect_address), this);
                _stub.setPortName(getPublicProyectWSDDServiceName());
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
        if ("PublicProyect".equals(inputPortName)) {
            return getPublicProyect();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "PublicProyectService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "PublicProyect"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PublicProyect".equals(portName)) {
            setPublicProyectEndpointAddress(address);
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
