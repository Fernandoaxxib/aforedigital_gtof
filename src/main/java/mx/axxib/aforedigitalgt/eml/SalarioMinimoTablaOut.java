package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import lombok.Data;

@Data
public class SalarioMinimoTablaOut {
	private String cdZona;
	private Date fechaCalendario;
	private Double montoDiario;
	private Date   fechaUltimo;
	private String userId;
}
