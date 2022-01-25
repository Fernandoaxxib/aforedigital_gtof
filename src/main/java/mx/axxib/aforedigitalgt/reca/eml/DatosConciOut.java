package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de conciliación semanas de cotización IMSS
//** Interventor Principal: JSAS
//** Fecha Creación: 31/Ene/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class DatosConciOut {
	private Integer estatus;
	private String mensaje;
	private List<SemanaDetalle> detalle;
}

