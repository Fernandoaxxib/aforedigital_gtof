package mx.axxib.aforedigitalgt.reca.serv;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.FocapdomRepo;
import mx.axxib.aforedigitalgt.reca.eml.ResActualizaVersionAppVol;
import mx.axxib.aforedigitalgt.reca.eml.ResBuscar;
import mx.axxib.aforedigitalgt.reca.eml.ResCargaArchivo;
import mx.axxib.aforedigitalgt.reca.eml.ResDetalleAfiliaciones;
import mx.axxib.aforedigitalgt.reca.eml.ResDetalleFacturacion;
import mx.axxib.aforedigitalgt.reca.eml.ResDetalleMovtos;
import mx.axxib.aforedigitalgt.reca.eml.ResGeneraArchivo;
import mx.axxib.aforedigitalgt.reca.eml.ResPendientes;
import mx.axxib.aforedigitalgt.reca.eml.ResSolPorAfiliar;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de FOCAPDOM
//** Interventor Principal: CVMR
//** Fecha Creación: 26/03/2022
//** Última Modificación:
//***********************************************//
@Service
public class FocapdomServ extends ServiceBase{
    @Autowired
    private FocapdomRepo repo;

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
            return repo.detalleAfiliaciones(
                cveRechAfil,
                cveEstSolCa,
                fechaCapturaCa1,
                fechaCapturaCa2,
                cveRechCa,
                fechaGenCa,
                nssCa,
                curpCa
            );
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

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
            return repo.detalleFacturacion(
                nss,
                nssDf,
                curp,
                curpDf,
                cveEstSol,
                cveEstSolFa,
                cveOrigAporta,
                cveOrigAportaDf,
                cveTipoPeriodoDf
            );
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

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
            return repo.detalleMovtos(
                nss,
                nssDm,
                curp,
                curpDm,
                referenciaDm,
                fecMovDmI,
                fecMovDmF
            );
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    public ResActualizaVersionAppVol actualizaVersionAppVol(
        String nuevaVersion
    ) throws AforeException{
        try {
            return repo.actualizaVersionAppVol(nuevaVersion);
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    public ResGeneraArchivo generaArchivo(
        String noReg,
        Integer consec,
        Integer archivo
    ) throws AforeException{
        try {
            return repo.generaArchivo(
                noReg,
                consec,
                archivo
            );
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    public ResSolPorAfiliar solPorAfiliar() throws AforeException{
        try {
            return repo.solPorAfiliar();
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    public ResBuscar buscar() throws AforeException{
        try {
            return repo.buscar();
        } catch (Exception e) {
            throw GenericException(e);
        }
    }

    public ResPendientes pendientes(
        Date fecGenCar,
        String consecCar
    ) throws AforeException{
        try {
            return repo.pendientes(
                fecGenCar,
                consecCar
            );
        } catch (Exception e) {
            throw GenericException(e);
        }
    }
    
    public ResCargaArchivo cargaArchivo(
            String archivoCar,
            Date fecGenCar,
            String consecCar,
            String ruta
        ) throws AforeException{
            try {
                return repo.cargaArchivo(
                    archivoCar,
                    fecGenCar,
                    consecCar,
                    ruta
                );
            } catch (Exception e) {
                throw GenericException(e);
            }
        }
}