package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class VentaTitulosMonitorIn {
	private String seleccion;
	private String indCuotaRend;
	private Integer transacMov;
	private String subtransMov;
	private String idLote;
	private String loteRev;
	private Date fechaI;
	private Date fecha;
	private String usuario;
	private String siafore;
	private BigDecimal retiroAforeMnd;
	private BigDecimal valorCuota;
}

//P_SELECCION IN VARCHAR,
//P_IND_CUOTA_REND IN VARCHAR,
//P_TRANSACMOV  IN NUMBER,
//P_SUBTRANSMOV IN VARCHAR2,
//P_IDLOTE IN VARCHAR2,
//P_LOTE_REV IN VARCHAR2,
//P_FECHAI IN DATE,
//P_FECHA  IN DATE,
//P_USUARIO IN VARCHAR2,
//P_SIAFORE IN VARCHAR2,
//P_RETIRO_AFORE_MND IN NUMBER,
//P_VALOR_CUOTA IN NUMBER