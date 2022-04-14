package mx.axxib.aforedigitalgt.reca.dal;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.ResActualizaVersionAppVol;
import mx.axxib.aforedigitalgt.reca.eml.ResBuscar;
import mx.axxib.aforedigitalgt.reca.eml.ResDetalleAfiliaciones;
import mx.axxib.aforedigitalgt.reca.eml.ResDetalleFacturacion;
import mx.axxib.aforedigitalgt.reca.eml.ResDetalleMovtos;
import mx.axxib.aforedigitalgt.reca.eml.ResGeneraArchivo;
import mx.axxib.aforedigitalgt.reca.eml.ResSolPorAfiliar;
import mx.axxib.aforedigitalgt.reca.eml.ResCargaArchivo;
import mx.axxib.aforedigitalgt.reca.eml.ResPendientes;

//***********************************************//
//** Funcionalidad: Repositorio - FOCAPDOM
//** Desarrollador: CVMR
//** Fecha de creación: 24/Mar/2022
//** Última modificación: 26/03/2022
//***********************************************//

@Repository
@Transactional
public class FocapdomRepo extends RepoBase {

    @SuppressWarnings("unchecked")
    public ResDetalleAfiliaciones detalleAfiliaciones(
        Integer cveRechAfil,
        Integer cveEstSolCa,
        Date fechaCapturaCa1,
        Date fechaCapturaCa2,
        Integer cveRechCa,
        Date fechaGenCa,
        String nssCa,
        String curpCa
    ) throws AforeException{
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
					Constantes.USUARIO_PENSION.concat(".")
						.concat(Constantes.PKG_RECA_FOCAPDOM).concat(".")
						.concat(Constantes.PRC_CAPTURA_B_DETA_AFILIA),
                    "DetalleAfiliacion"
			);

            query
                .registerStoredProcedureParameter("in_CVE_RECH_AFIL", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_CVE_EST_SOL_CA", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("id_FECHA_CAPTURA_CA_1", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("id_FECHA_CAPTURA_CA_2", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_CVE_RECH_CA", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("id_FECHA_GEN_CA", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ic_NSS_CA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ic_CURP_CA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT)

                .setParameter("in_CVE_RECH_AFIL", cveRechAfil)
                .setParameter("in_CVE_EST_SOL_CA", cveEstSolCa)
                .setParameter("id_FECHA_CAPTURA_CA_1", fechaCapturaCa1)
                .setParameter("id_FECHA_CAPTURA_CA_2", fechaCapturaCa2)
                .setParameter("in_CVE_RECH_CA", cveRechCa)
                .setParameter("id_FECHA_GEN_CA", fechaGenCa)
                .setParameter("ic_NSS_CA", nssCa)
                .setParameter("ic_CURP_CA", curpCa);

                ResDetalleAfiliaciones result = new ResDetalleAfiliaciones(
                    new ArrayList<>(),
                    (Integer)query.getOutputParameterValue("on_Estatus"),
                    (String)query.getOutputParameterValue("oc_Mensaje")
                );
                if(result.getEstatus().equals(1)){
                    result.setDetallesAfiliacion(query.getResultList());
                }

                return result;
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public ResDetalleFacturacion detalleFacturacion(
        String nss,
        String nssDf,
        String curp,
        String curpDf,
        String cveEstSol,
        String cveEstSolFa,
        String cveOrigAporta,
        String cveOrigAportaDf,
        String cveTipoPeriodoDf
    ) throws AforeException{
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
					Constantes.USUARIO_PENSION.concat(".")
						.concat(Constantes.PKG_RECA_FOCAPDOM).concat(".")
						.concat(Constantes.PRC_CAPTURA_B_DETA_FACTURA),
                    "DetalleFacturacion"
			);

            query
                .registerStoredProcedureParameter("ic_NSS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ic_NSS_DF", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ic_CURP", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ic_CURP_DF", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_CVE_EST_SOL", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_CVE_EST_SOL_FA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_CVE_ORIG_APORTA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_CVE_ORIG_APORTA_DF", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_CVE_TIPO_PERIODO_DF", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT)

                .setParameter("ic_NSS", nss)
                .setParameter("ic_NSS_DF", nssDf)
                .setParameter("ic_CURP", curp)
                .setParameter("ic_CURP_DF", curpDf)
                .setParameter("in_CVE_EST_SOL", cveEstSol)
                .setParameter("in_CVE_EST_SOL_FA", cveEstSolFa)
                .setParameter("in_CVE_ORIG_APORTA", cveOrigAporta)
                .setParameter("in_CVE_ORIG_APORTA_DF", cveOrigAportaDf)
                .setParameter("in_CVE_TIPO_PERIODO_DF", cveTipoPeriodoDf);
            
                ResDetalleFacturacion result = new ResDetalleFacturacion(
                    new ArrayList<>(),
                    (Integer)query.getOutputParameterValue("on_Estatus"),
                    (String)query.getOutputParameterValue("oc_Mensaje")
                );
                if(result.getEstatus().equals(1)){
                    result.setDetallesFacturacion(query.getResultList());
                }

                return result;
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public ResDetalleMovtos detalleMovtos(
        String nss,
        String nssDm,
        String curp,
        String curpDm,
        Integer referenciaDm,
        Date fecMovDmI,
        Date fecMovDmF
    ) throws AforeException{
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
					Constantes.USUARIO_PENSION.concat(".")
						.concat(Constantes.PKG_RECA_FOCAPDOM).concat(".")
						.concat(Constantes.PRC_CAPTURA_B_DETA_MOVTOS),
                    "DetalleMovtos"
			);

            query
                .registerStoredProcedureParameter("ic_NSS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ic_NSS_DM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ic_CURP", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ic_CURP_DM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_REFERENCIA_DM", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("id_FECMOV_DM_I", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("id_FECMOV_DM_F", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT)

                .setParameter("ic_NSS", nss)
                .setParameter("ic_NSS_DM", nssDm)
                .setParameter("ic_CURP", curp)
                .setParameter("ic_CURP_DM", curpDm)
                .setParameter("in_REFERENCIA_DM", referenciaDm)
                .setParameter("id_FECMOV_DM_I", fecMovDmI)
                .setParameter("id_FECMOV_DM_F", fecMovDmF);

                ResDetalleMovtos result = new ResDetalleMovtos(
                    new ArrayList<>(),
                    (Integer)query.getOutputParameterValue("on_Estatus"),
                    (String)query.getOutputParameterValue("oc_Mensaje")
                );
                if(result.getEstatus().equals(1)){
                    result.setDetallesMovtos(query.getResultList());
                }

                return result;
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    public ResActualizaVersionAppVol actualizaVersionAppVol(
        String nuevaVersion
    ) throws AforeException{
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
					Constantes.USUARIO_PENSION.concat(".")
						.concat(Constantes.PKG_RECA_FOCAPDOM).concat(".")
						.concat(Constantes.PRC_ACTUALIZA_VERSION_APP_VOL)
			);

            query
                .registerStoredProcedureParameter("ic_NUEVA_VERSION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("oc_Aviso", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT)

                .setParameter("ic_NUEVA_VERSION", nuevaVersion);

                return new ResActualizaVersionAppVol(
                    (String)query.getOutputParameterValue("oc_Aviso"),
                    (Integer)query.getOutputParameterValue("on_Estatus"),
                    (String)query.getOutputParameterValue("oc_Mensaje")
                );
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    public ResGeneraArchivo generaArchivo(
        String noReg,
        Integer consec,
        Integer archivo
    ) throws AforeException {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
					Constantes.USUARIO_PENSION.concat(".")
						.concat(Constantes.PKG_RECA_FOCAPDOM).concat(".")
						.concat(Constantes.PRC_B_GENERAR_ARCHIVO)
			);

            query
            .registerStoredProcedureParameter("in_NO_REG", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("ic_CONSEC", Integer.class, ParameterMode.IN)
            .registerStoredProcedureParameter("ic_ARCHIVO", Integer.class, ParameterMode.IN)
            .registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT)
            .registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT)

            .setParameter("in_NO_REG", noReg)
            .setParameter("ic_CONSEC", consec)
            .setParameter("ic_ARCHIVO", archivo);

            return new ResGeneraArchivo(
                (Integer)query.getOutputParameterValue("on_Estatus"),
                (String)query.getOutputParameterValue("oc_Mensaje")
            );

        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public ResSolPorAfiliar solPorAfiliar() throws AforeException{
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
					Constantes.USUARIO_PENSION.concat(".")
						.concat(Constantes.PKG_RECA_FOCAPDOM).concat(".")
						.concat(Constantes.PRC_B_SOL_POR_AFILIAR),
                    "SolPorAfiliar"
			);

            query
                .registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

                ResSolPorAfiliar result = new ResSolPorAfiliar(
                    new ArrayList<>(),
                    (Integer)query.getOutputParameterValue("on_Estatus"),
                    (String)query.getOutputParameterValue("oc_Mensaje")
                );
                if(result.getEstatus().equals(1)){
                    result.setSolsPorAfiliar(query.getResultList());
                }

                return result;
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public ResBuscar buscar() throws AforeException{
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
					Constantes.USUARIO_PENSION.concat(".")
						.concat(Constantes.PKG_RECA_FOCAPDOM).concat(".")
						.concat(Constantes.PRC_B_BUSCAR),
                    "Busqueda"
			);

            query
                .registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

                ResBuscar result = new ResBuscar(
                    new ArrayList<>(),
                    (Integer)query.getOutputParameterValue("on_Estatus"),
                    (String)query.getOutputParameterValue("oc_Mensaje")
                );
                if(result.getEstatus().equals(1)){
                    result.setBusquedas(query.getResultList());
                }

                return result;
        } catch (Exception e) {
            throw GenericException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public ResPendientes pendientes(
            Date fecGenCar,
            String consecCar
        ) throws AforeException{
            try {
            	StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
    					Constantes.USUARIO_PENSION.concat(".")
    						.concat(Constantes.PKG_RECA_FOCAPDOM).concat(".")
    						.concat(Constantes.PRC_B_PENDIENTES),
                        "Pendiente"
    			);
            	
            	query
            		.registerStoredProcedureParameter("id_FEC_GEN_CAR", String.class, ParameterMode.IN)
            		.registerStoredProcedureParameter("ic_CONSEC_CAR", String.class, ParameterMode.IN)
            		.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT)
                    
                    .setParameter("id_FEC_GEN_CAR", fecGenCar)
                    .setParameter("ic_CONSEC_CAR", consecCar);
            	
            	ResPendientes result = new ResPendientes(
            			new ArrayList<>(),
                        (Integer)query.getOutputParameterValue("on_Estatus"),
                        (String)query.getOutputParameterValue("oc_Mensaje")
                    );
            	if(result.getEstatus().equals(1)){
                    result.setPendientes(query.getResultList());
                }
            	
            	return result;
            	
            } catch (Exception e) {
                throw GenericException(e);
            }
        }

	public ResCargaArchivo cargaArchivo(
		String archivoCar,
		Date fecGenCar,
		String consecCar,
		String ruta
	) throws AforeException {
		try {
        	StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
					Constantes.USUARIO_PENSION.concat(".")
						.concat(Constantes.PKG_RECA_FOCAPDOM).concat(".")
						.concat(Constantes.PRC_B_CARGA_ARCHIVO)
			);
        	
        	query
	    		.registerStoredProcedureParameter("ic_ARCHIVO_CAR", String.class, ParameterMode.IN)
	    		.registerStoredProcedureParameter("id_FEC_GEN_CAR", Date.class, ParameterMode.IN)
	    		.registerStoredProcedureParameter("ic_CONSEC_CAR", String.class, ParameterMode.IN)
	    		.registerStoredProcedureParameter("ic_RUTA", String.class, ParameterMode.IN)
        		.registerStoredProcedureParameter("on_ACEPTADAS", Integer.class, ParameterMode.OUT)
        		.registerStoredProcedureParameter("on_RECHAZADAS", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT)
                
                .setParameter("ic_ARCHIVO_CAR", archivoCar)
                .setParameter("id_FEC_GEN_CAR", fecGenCar)
                .setParameter("ic_CONSEC_CAR", consecCar)
                .setParameter("ic_RUTA", ruta);
        	
        	return new ResCargaArchivo(
        			(Integer)query.getOutputParameterValue("on_ACEPTADAS"),
        			(Integer)query.getOutputParameterValue("on_RECHAZADAS"),
                    (Integer)query.getOutputParameterValue("on_Estatus"),
                    (String)query.getOutputParameterValue("oc_Mensaje")
                );
        } catch (Exception e) {
            throw GenericException(e);
        }
	}
}
