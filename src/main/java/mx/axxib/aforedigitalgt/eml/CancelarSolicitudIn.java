package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class CancelarSolicitudIn {
	private String nss;
	private String noSolicitud;
	private Integer cveProcesoOpe;
}

//P_NSS IN VARCHAR2,
//P_NUM_SOLICITUD IN VARCHAR2,
//P_CVE_PROCESO_OPE IN NUMBER