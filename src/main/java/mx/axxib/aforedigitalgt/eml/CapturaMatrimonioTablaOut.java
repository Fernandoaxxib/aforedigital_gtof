package mx.axxib.aforedigitalgt.eml;

import lombok.Data;

@Data
public class CapturaMatrimonioTablaOut {
	
	private String inversion;
	private String tipoSaldo;
	private String descripcion;
	private String saldoActual;
	private String montoRetirar;
	private String fechaAprovacion;
	private String estatus;

}
