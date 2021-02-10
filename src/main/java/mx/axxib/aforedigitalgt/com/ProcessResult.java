package mx.axxib.aforedigitalgt.com;

import java.util.Date;

import lombok.Data;

@Data
public class ProcessResult {

	private String descProceso;
	private String status;
	private Date fechaInicial;
	private Date fechaFinal;
}
