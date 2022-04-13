/**
 * IDomainService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface IDomainService extends java.rmi.Remote {
    public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseLogin authenticate(org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin request) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseBase isLocked(org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin request) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseBase unLock(org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin request) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseBase changePassword(org.datacontract.schemas._2004._07.DomainService_Entities.DTOChangePassword request) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseLogin getInfoUser(org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin request) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseLogin resetPassword(org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin request) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseBase exists(java.lang.String userName) throws java.rmi.RemoteException;
}
