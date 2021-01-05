package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class VentaTitulosMonitorCTIn {
	private String indCuotaRend;
	private String loteCorte;
	private String usuario;
	private String siafore;
	private BigDecimal retiroAforeMnd;
	private BigDecimal valorCuota;
}

//P_IND_CUOTA_REND IN VARCHAR,
//P_LOTE_CORTE IN VARCHAR2,
//P_USUARIO IN VARCHAR2,
//P_SIAFORE IN VARCHAR2,
//P_RETIRO_AFORE_MND IN NUMBER,
//P_VALOR_CUOTA IN NUMBER