/**
 * DomainService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface DomainService extends javax.xml.rpc.Service {
    public java.lang.String getBasicHttpBinding_IDomainServiceAddress();

    public org.tempuri.IDomainService getBasicHttpBinding_IDomainService() throws javax.xml.rpc.ServiceException;

    public org.tempuri.IDomainService getBasicHttpBinding_IDomainService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getBasicHttpsBinding_IDomainServiceAddress();

    public org.tempuri.IDomainService getBasicHttpsBinding_IDomainService() throws javax.xml.rpc.ServiceException;

    public org.tempuri.IDomainService getBasicHttpsBinding_IDomainService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
