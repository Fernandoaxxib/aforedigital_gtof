package mx.axxib.aforedigitalgt.dal;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.Constantes;

@Repository
public class CargaMasivaDAO {
	
	private final EntityManager entityManager;
	
	@Autowired
	public CargaMasivaDAO(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public String ejecutarArchivoCarga(String ruta, String nombre) {
		
		String storedFullName =  Constantes.DETALLE_CARGA_MASIVA_PACKAGE.concat(".").concat(Constantes.DETALLE_CARGA_MASIVA_CREAR_ARCHIVO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Archivo", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		
		query.setParameter("p_Ruta", ruta);
		query.setParameter("p_Archivo", nombre);
		
		
		
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		
	}
	
	@SuppressWarnings("unchecked")
	public String reversaArchivoCarga() {
		
		String storedFullName =  Constantes.DETALLE_CARGA_MASIVA_PACKAGE.concat(".").concat(Constantes.DETALLE_CARGA_MASIVA_REVERSA_ARCHIVO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		
	}
	
	@SuppressWarnings("unchecked")
	public String desmarcaMasivaCuenta() {
		
		String storedFullName =  Constantes.DETALLE_DESMARCA_MASIVA_PACKAGE.concat(".").concat(Constantes.DETALLE_DESMARCA_CUENTAS_MASIVAS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		
	}
	
	@SuppressWarnings("unchecked")
	public String desmarcaIndividualCuenta(String nss, String curp, String claveProceso) {
		
		String storedFullName =  Constantes.DETALLE_DESMARCA_MASIVA_PACKAGE.concat(".").concat(Constantes.DETALLE_DESMARCA_MASIVA_INDIVIDUAL_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Nss", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CURP", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CveProc", String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		
		query.setParameter("p_Nss", nss);
		query.setParameter("p_CURP", curp);
		query.setParameter("p_CveProc", claveProceso);
		
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		
	}
	
	@SuppressWarnings("unchecked")
	public String consultaMarcas(String claveProceso,String descripcionProceso) {
		
		String storedFullName =  Constantes.DETALLE_DESMARCA_MASIVA_PACKAGE.concat(".").concat(Constantes.DETALLE_CONSULTAR_MARCAS_CLAVE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_PerCve", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_PerDesc", String.class, ParameterMode.IN);
		
		
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		
		query.setParameter("p_PerCve", claveProceso);
		query.setParameter("p_PerDesc", descripcionProceso);
				
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		
	}
	
	@SuppressWarnings("unchecked")
	public String consultaMarcasArchivo(String ruta, String nombre) {
		
		String storedFullName =  Constantes.DETALLE_CONSULTAR_MARCAS_PACKAGE.concat(".").concat(Constantes.DETALLE_CONSULTAR_MARCAS_ARCHIVO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_NomCons", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);		
		
		query.setParameter("p_Ruta", ruta);
		query.setParameter("p_NomCons", nombre);
		
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		
	}
}
