package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class LiqEjecutaReporteIn {
	private String tipoReporte;
	private String siefore;
	private String idLote;
	private String tipoRetiro;
	private String estado;
	private String usuario;
	private String descripcion;
	private String fecha;
}

//P_TIPO_REPORTE IN VARCHAR2,
//P_SIEFORE IN VARCHAR2,
//P_ID_LOTE IN VARCHAR2,
//P_TIPO_RETIRO IN VARCHAR2,
//P_ESTADO IN VARCHAR2,
//P_USUARIO IN VARCHAR2,
//P_DESC_SIEFORE IN VARCHAR2,
//P_FECHA_SISTEMA IN DATE

