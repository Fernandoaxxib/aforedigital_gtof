package mx.axxib.aforedigitalgt.com;

import java.util.Date;

import lombok.Data;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Clase que representa todos los mensajes en pantalla de la sección "resultado del proceso aplicado"
//** Interventor Principal: JSAS
//** Fecha Creación: 19/Nov/2020
//** Última Modificación:
//***********************************************//
@Data
public class ProcessResult {

	private String descProceso;
	private String status;
	private Date fechaInicial;
	private Date fechaFinal;
}
