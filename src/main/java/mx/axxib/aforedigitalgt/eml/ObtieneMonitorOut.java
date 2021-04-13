package mx.axxib.aforedigitalgt.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno del monitor de procesos
//** Interventor Principal: JSAS
//** Fecha Creación: 19/Nov/2020
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class ObtieneMonitorOut {
	private Integer estatus;
	private String mensaje;
	private List<ObtieneMonitor> monitor;
}
