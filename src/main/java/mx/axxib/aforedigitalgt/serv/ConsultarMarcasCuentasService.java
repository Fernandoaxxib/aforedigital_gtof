package mx.axxib.aforedigitalgt.serv;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ConsultarMarcasCuentasRepo;
import mx.axxib.aforedigitalgt.eml.ConsultarDatosIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarTraspasosIcefasOut;
import mx.axxib.aforedigitalgt.eml.CpDatosIcefasOut;

@Service
public class ConsultarMarcasCuentasService extends ServiceBase {
	
	@Autowired
	private ConsultarMarcasCuentasRepo consultarTraspasos;

	@SuppressWarnings("null")
	public ConsultarTraspasosIcefasOut getConsultarTraspasos(String codCuenta,String codTipoSaldo,String claveProceso, String estado) throws AforeException {
			try {
				return consultarTraspasos.getConsultarTraspasos(codCuenta,codTipoSaldo,claveProceso,estado);

			} catch (Exception e) {
				throw GenericException(e);
			}
		}
	
	@SuppressWarnings("null")
	public ConsultarNombreCuentaIcefasOut getConsultarCurp(String curp) throws AforeException {
		try {
			return consultarTraspasos.getConsultarCurp(curp);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("null")
	public ConsultarNombreCuentaIcefasOut getConsultarNss(String nss) throws AforeException {
		try {
			return consultarTraspasos.getConsultarNss(nss);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
}
