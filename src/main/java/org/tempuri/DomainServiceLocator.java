/**
 * DomainServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class DomainServiceLocator extends org.apache.axis.client.Service implements org.tempuri.DomainService {

    public DomainServiceLocator() {
    }


    public DomainServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DomainServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IDomainService
    private java.lang.String BasicHttpBinding_IDomainService_address = "http://172.20.1.72/WSDomainService/DomainService.svc";

    public java.lang.String getBasicHttpBinding_IDomainServiceAddress() {
        return BasicHttpBinding_IDomainService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IDomainServiceWSDDServiceName = "BasicHttpBinding_IDomainService";

    public java.lang.String getBasicHttpBinding_IDomainServiceWSDDServiceName() {
        return BasicHttpBinding_IDomainServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IDomainServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IDomainServiceWSDDServiceName = name;
    }

    public org.tempuri.IDomainService getBasicHttpBinding_IDomainService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IDomainService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IDomainService(endpoint);
    }

    public org.tempuri.IDomainService getBasicHttpBinding_IDomainService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.BasicHttpBinding_IDomainServiceStub _stub = new org.tempuri.BasicHttpBinding_IDomainServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IDomainServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IDomainServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IDomainService_address = address;
    }


    // Use to get a proxy class for BasicHttpsBinding_IDomainService
    private java.lang.String BasicHttpsBinding_IDomainService_address = "https://siga01.axxi.com.mx/WSDomainService/DomainService.svc";

    public java.lang.String getBasicHttpsBinding_IDomainServiceAddress() {
        return BasicHttpsBinding_IDomainService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpsBinding_IDomainServiceWSDDServiceName = "BasicHttpsBinding_IDomainService";

    public java.lang.String getBasicHttpsBinding_IDomainServiceWSDDServiceName() {
        return BasicHttpsBinding_IDomainServiceWSDDServiceName;
    }

    public void setBasicHttpsBinding_IDomainServiceWSDDServiceName(java.lang.String name) {
        BasicHttpsBinding_IDomainServiceWSDDServiceName = name;
    }

    public org.tempuri.IDomainService getBasicHttpsBinding_IDomainService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpsBinding_IDomainService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpsBinding_IDomainService(endpoint);
    }

    public org.tempuri.IDomainService getBasicHttpsBinding_IDomainService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.BasicHttpsBinding_IDomainServiceStub _stub = new org.tempuri.BasicHttpsBinding_IDomainServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpsBinding_IDomainServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpsBinding_IDomainServiceEndpointAddress(java.lang.String address) {
        BasicHttpsBinding_IDomainService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.IDomainService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.BasicHttpBinding_IDomainServiceStub _stub = new org.tempuri.BasicHttpBinding_IDomainServiceStub(new java.net.URL(BasicHttpBinding_IDomainService_address), this);
                _stub.setPortName(getBasicHttpBinding_IDomainServiceWSDDServiceName());
                return _stub;
            }
            if (org.tempuri.IDomainService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.BasicHttpsBinding_IDomainServiceStub _stub = new org.tempuri.BasicHttpsBinding_IDomainServiceStub(new java.net.URL(BasicHttpsBinding_IDomainService_address), this);
                _stub.setPortName(getBasicHttpsBinding_IDomainServiceWSDDServiceName());
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
        if ("BasicHttpBinding_IDomainService".equals(inputPortName)) {
            return getBasicHttpBinding_IDomainService();
        }
        else if ("BasicHttpsBinding_IDomainService".equals(inputPortName)) {
            return getBasicHttpsBinding_IDomainService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "DomainService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IDomainService"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpsBinding_IDomainService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_IDomainService".equals(portName)) {
            setBasicHttpBinding_IDomainServiceEndpointAddress(address);
        }
        else 
if ("BasicHttpsBinding_IDomainService".equals(portName)) {
            setBasicHttpsBinding_IDomainServiceEndpointAddress(address);
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
