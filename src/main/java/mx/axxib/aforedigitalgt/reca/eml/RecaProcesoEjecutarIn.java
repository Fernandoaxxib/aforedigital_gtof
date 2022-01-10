package mx.axxib.aforedigitalgt.reca.eml;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de recaudación imss
//** Interventor Principal: JSAS
//** Fecha Creación: 13/Dic/2021
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class RecaProcesoEjecutarIn {
	private String opcion;
	private String idOperacion;
	private String fechaLote;
	private String secLote;
	private Date fecha;
	private String archivo;
	private String directorio;
	

}

