package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.DesmarcaCargaConsultaMasivaOut;
import mx.axxib.aforedigitalgt.eml.DesmarcaCargaMasivaClaveOut;

@Repository
@Transactional
public class DesmarcaCargaConsultaMasivaRepo extends RepoBase{
	
	private final EntityManager entityManager;
	
	@Autowired
	public DesmarcaCargaConsultaMasivaRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public DesmarcaCargaConsultaMasivaOut ejecutarArchivoCarga(String ruta, String nombre) throws AforeException {
		try {
	 	String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DETALLE_DESMARCA_CARGA_MASIVA_PACKAGE).concat(".").concat(Constantes.DETALLE_DESMARCA_CARGA_MASIVA_CREAR_ARCHIVO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Archivo", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
		
		query.setParameter("p_Ruta", ruta);
		query.setParameter("p_Archivo", nombre);
		
		DesmarcaCargaConsultaMasivaOut res=new DesmarcaCargaConsultaMasivaOut();
		
//		String res = (String) query.getOutputParameterValue("p_Mensaje");

		res.setP_Mensaje((String) query.getOutputParameterValue("p_Mensaje"));
		res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public DesmarcaCargaConsultaMasivaOut reversaArchivoCarga() throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DETALLE_DESMARCA_CARGA_MASIVA_PACKAGE).concat(".").concat(Constantes.DETALLE_DESMARCA_CARGA_MASIVA_REVERSA_ARCHIVO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
		
		//String res = (String) query.getOutputParameterValue("p_Mensaje");
		DesmarcaCargaConsultaMasivaOut res=new DesmarcaCargaConsultaMasivaOut();
		res.setP_Mensaje((String) query.getOutputParameterValue("p_Mensaje"));
		res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public DesmarcaCargaConsultaMasivaOut desmarcaMasivaCuenta() throws AforeException {
		try {
	    String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DETALLE_DESMARCA_CARGA_MASIVA_PACKAGE).concat(".").concat(Constantes.DETALLE_DESMARCA_CUENTAS_MASIVAS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);		
		
		//String res = (String) query.getOutputParameterValue("p_Mensaje");
		DesmarcaCargaConsultaMasivaOut res=new DesmarcaCargaConsultaMasivaOut();
		res.setP_Mensaje((String) query.getOutputParameterValue("p_Mensaje"));
		res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public DesmarcaCargaMasivaClaveOut desmarcaObtenerProceso (String proceso,String claveProceso, String propClave) throws AforeException {
		try {
	    String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DETALLE_DESMARCA_CARGA_MASIVA_PACKAGE).concat(".").concat(Constantes.DETALLE_DESMARCA_MASIVA_OBTENER_PROCESO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		
		query.registerStoredProcedureParameter("p_Proceso", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CLAVEPROCESO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_PROPERCLAVE", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_DESCPROCESO", String.class, ParameterMode.OUT);	
		query.registerStoredProcedureParameter("p_PROPERDESC", String.class, ParameterMode.OUT);
		
		query.setParameter("p_Proceso", proceso);
		query.setParameter("p_CLAVEPROCESO", claveProceso);
		query.setParameter("p_PROPERCLAVE", propClave);
			
		DesmarcaCargaMasivaClaveOut res= new DesmarcaCargaMasivaClaveOut();
		res.setP_DESCPROCESO((String) query.getOutputParameterValue("p_DESCPROCESO"));
		res.setP_PROPERDESC((String) query.getOutputParameterValue("p_PROPERDESC"));

		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public DesmarcaCargaConsultaMasivaOut desmarcaIndividualCuenta(String nss, String curp, String claveProceso) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DETALLE_DESMARCA_CARGA_MASIVA_PACKAGE).concat(".").concat(Constantes.DETALLE_DESMARCA_MASIVA_INDIVIDUAL_CITA_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Nss", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CURP", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CveProc", String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);		
		
		query.setParameter("p_Nss", nss);
		query.setParameter("p_CURP", curp);
		query.setParameter("p_CveProc", claveProceso);
		
		//String res = (String) query.getOutputParameterValue("p_Mensaje");
		DesmarcaCargaConsultaMasivaOut res=new DesmarcaCargaConsultaMasivaOut();
		res.setP_Mensaje((String) query.getOutputParameterValue("p_Mensaje"));
		res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public DesmarcaCargaConsultaMasivaOut consultaMarcas(String claveProceso,String descripcionProceso) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DETALLE_DESMARCA_CARGA_MASIVA_PACKAGE).concat(".").concat(Constantes.DETALLE_DESMARCA_CONSULTAR_MARCAS_CLAVE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_PerCve", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_PerDesc", String.class, ParameterMode.IN);
		
		
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
		
		query.setParameter("p_PerCve", claveProceso);
		query.setParameter("p_PerDesc", descripcionProceso);
				
		//String res = (String) query.getOutputParameterValue("p_Mensaje");
		DesmarcaCargaConsultaMasivaOut res=new DesmarcaCargaConsultaMasivaOut();
		res.setP_Mensaje((String) query.getOutputParameterValue("p_Mensaje"));
		res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public DesmarcaCargaConsultaMasivaOut consultaMarcasArchivo(String ruta, String nombre) throws AforeException {
		try {
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DETALLE_DESMARCA_CARGA_MASIVA_PACKAGE).concat(".").concat(Constantes.DETALLE_DESMARCA_CONSULTAR_MARCAS_NSS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_NomCons", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
		
		query.setParameter("p_Ruta", ruta);
		query.setParameter("p_NomCons", nombre);
		
		//String res = (String) query.getOutputParameterValue("p_Mensaje");
		DesmarcaCargaConsultaMasivaOut res=new DesmarcaCargaConsultaMasivaOut();
		res.setP_Mensaje((String) query.getOutputParameterValue("p_Mensaje"));
		res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
}
