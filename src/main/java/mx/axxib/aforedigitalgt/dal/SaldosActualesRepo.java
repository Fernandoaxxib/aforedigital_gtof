package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.DatosClienteNSSOut;
import mx.axxib.aforedigitalgt.eml.DatosClienteOut;
import mx.axxib.aforedigitalgt.eml.DetalleSaldoOut;
import mx.axxib.aforedigitalgt.eml.ResultadoSaldosOut;
import mx.axxib.aforedigitalgt.eml.SaldoOut;


@Repository
@Transactional
public class SaldosActualesRepo extends RepoBase{

	private final EntityManager entityManager;

	@Autowired
	public SaldosActualesRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public ResultadoSaldosOut getSaldosBloque(String P_CUENTA,String P_COD_EMPRESA,Date P_FECHA,String P_FECHA_FILTRO,String P_SELEC_VIVIENDA) throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALDOS_ACTUALES_CLIENTE_PACKAGE).concat(".").concat(Constantes.SALDOS_ACTUALES_CLIENTE_LLENA_SALDOS_BLOQUE);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "DetalleSaldoOut");		

		query.registerStoredProcedureParameter("P_CUENTA", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_FECHA_FILTRO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_SELEC_VIVIENDA", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("CP_LLENA_SALDOS", void.class, ParameterMode.REF_CURSOR);
		
		query.setParameter("P_CUENTA", P_CUENTA);
		query.setParameter("P_COD_EMPRESA", P_COD_EMPRESA);
		query.setParameter("P_FECHA", P_FECHA);
		query.setParameter("P_FECHA_FILTRO", P_FECHA_FILTRO);
		query.setParameter("P_SELEC_VIVIENDA", P_SELEC_VIVIENDA);
		
		
		ResultadoSaldosOut res= new ResultadoSaldosOut();
		List<DetalleSaldoOut> lista = query.getResultList();
		res.setListSaldos(lista);
		res.setP_MENSAJE((String)query.getOutputParameterValue("P_MENSAJE"));
		res.setP_ESTATUS((Integer)query.getOutputParameterValue("P_ESTATUS"));
		
		return res;
	  }catch(Exception e) {
		 throw GenericException(e); 
	  }
	}
	
	@SuppressWarnings("unchecked")
	public ResultadoSaldosOut getDatosXNombre(String P_NOMBRE) throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALDOS_ACTUALES_CLIENTE_PACKAGE).concat(".").concat(Constantes.SALDOS_ACTUALES_CLIENTE_OBTIENEDATOS_NOMBRE);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "DatosClienteOut");		

		query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
				
		query.setParameter("P_NOMBRE", P_NOMBRE);		
		
		ResultadoSaldosOut res= new ResultadoSaldosOut();
		List<DatosClienteOut> lista = query.getResultList();
		res.setDatosCliente(lista);
		res.setP_MENSAJE((String)query.getOutputParameterValue("P_MENSAJE"));
		res.setP_ESTATUS((Integer)query.getOutputParameterValue("P_ESTATUS"));
		
		return res;
	  }catch(Exception e) {
		 throw GenericException(e); 
	  }
	}
	
	@SuppressWarnings("unchecked")
	public ResultadoSaldosOut getDatosXNSS(String P_NSS) throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALDOS_ACTUALES_CLIENTE_PACKAGE).concat(".").concat(Constantes.SALDOS_ACTUALES_CLIENTE_OBTIENEDATOS_NSS);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "DatosClienteNSSOut");		

		query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
				
		query.setParameter("P_NSS", P_NSS);		
		
		ResultadoSaldosOut res= new ResultadoSaldosOut();
		List<DatosClienteNSSOut> lista = query.getResultList();
		res.setDatosClienteNSS(lista);
		res.setP_MENSAJE((String)query.getOutputParameterValue("P_MENSAJE"));
		res.setP_ESTATUS((Integer)query.getOutputParameterValue("P_ESTATUS"));
		
		return res;
	  }catch(Exception e) {
		 throw GenericException(e); 
	  }
	}
	
	@SuppressWarnings("unchecked")
	public ResultadoSaldosOut getCargaSaldos(String P_COD_PRODUCTO,String P_COD_CUENTA) throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALDOS_ACTUALES_CLIENTE_PACKAGE).concat(".").concat(Constantes.SALDOS_ACTUALES_CLIENTE_CARGA_SALDOS);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SaldoOut");		

		query.registerStoredProcedureParameter("P_COD_PRODUCTO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
				
		query.setParameter("P_COD_PRODUCTO", P_COD_PRODUCTO);	
		query.setParameter("P_COD_CUENTA", P_COD_CUENTA);	
		
		ResultadoSaldosOut res= new ResultadoSaldosOut();
		List<SaldoOut> lista = query.getResultList();
		res.setSaldos(lista);
		res.setP_MENSAJE((String)query.getOutputParameterValue("P_MENSAJE"));
		res.setP_ESTATUS((Integer)query.getOutputParameterValue("P_ESTATUS"));			
		
		return res;
	  }catch(Exception e) {
		 throw GenericException(e); 
	  }
	}
	
	@SuppressWarnings("unchecked")
	public ResultadoSaldosOut getCargaNuevoNivel(String P_COD_SUBPRODUCTO,String p_cod_cuenta) throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALDOS_ACTUALES_CLIENTE_PACKAGE).concat(".").concat(Constantes.SALDOS_ACTUALES_CLIENTE_CARGA_NUEVO_NIVEL);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SaldoOut");		

		query.registerStoredProcedureParameter("P_COD_SUBPRODUCTO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_cod_cuenta", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
				
		query.setParameter("P_COD_SUBPRODUCTO", P_COD_SUBPRODUCTO);	
		query.setParameter("p_cod_cuenta", p_cod_cuenta);	
		
		ResultadoSaldosOut res= new ResultadoSaldosOut();
		List<SaldoOut> lista = query.getResultList();
		
		res.setSaldos(lista);
		res.setP_MENSAJE((String)query.getOutputParameterValue("P_MENSAJE"));
		res.setP_ESTATUS((Integer)query.getOutputParameterValue("P_ESTATUS"));			
		
		return res;
	  }catch(Exception e) {
		 throw GenericException(e); 
	  }
	}

}
