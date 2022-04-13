package org.tempuri;

public class IDomainServiceProxy implements org.tempuri.IDomainService {
  private String _endpoint = null;
  private org.tempuri.IDomainService iDomainService = null;
  
  public IDomainServiceProxy() {
    _initIDomainServiceProxy();
  }
  
  public IDomainServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIDomainServiceProxy();
  }
  
  private void _initIDomainServiceProxy() {
    try {
      iDomainService = (new org.tempuri.DomainServiceLocator()).getBasicHttpBinding_IDomainService();
      if (iDomainService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iDomainService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iDomainService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iDomainService != null)
      ((javax.xml.rpc.Stub)iDomainService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.IDomainService getIDomainService() {
    if (iDomainService == null)
      _initIDomainServiceProxy();
    return iDomainService;
  }
  
  public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseLogin authenticate(org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin request) throws java.rmi.RemoteException{
    if (iDomainService == null)
      _initIDomainServiceProxy();
    return iDomainService.authenticate(request);
  }
  
  public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseBase isLocked(org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin request) throws java.rmi.RemoteException{
    if (iDomainService == null)
      _initIDomainServiceProxy();
    return iDomainService.isLocked(request);
  }
  
  public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseBase unLock(org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin request) throws java.rmi.RemoteException{
    if (iDomainService == null)
      _initIDomainServiceProxy();
    return iDomainService.unLock(request);
  }
  
  public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseBase changePassword(org.datacontract.schemas._2004._07.DomainService_Entities.DTOChangePassword request) throws java.rmi.RemoteException{
    if (iDomainService == null)
      _initIDomainServiceProxy();
    return iDomainService.changePassword(request);
  }
  
  public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseLogin getInfoUser(org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin request) throws java.rmi.RemoteException{
    if (iDomainService == null)
      _initIDomainServiceProxy();
    return iDomainService.getInfoUser(request);
  }
  
  public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseLogin resetPassword(org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin request) throws java.rmi.RemoteException{
    if (iDomainService == null)
      _initIDomainServiceProxy();
    return iDomainService.resetPassword(request);
  }
  
  public org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseBase exists(java.lang.String userName) throws java.rmi.RemoteException{
    if (iDomainService == null)
      _initIDomainServiceProxy();
    return iDomainService.exists(userName);
  }
  
  
}