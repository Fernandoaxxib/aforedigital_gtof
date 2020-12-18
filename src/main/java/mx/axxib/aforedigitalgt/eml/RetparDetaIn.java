package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class RetparDetaIn {
	private String noSolicitud;
	private String noPago;
}

//P_NUM_SOLICITUD    IN VARCHAR2,
//P_NUMERO_PAGO      IN VARCHAR2,