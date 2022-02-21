package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Reporte Conciliados
//** Interventor Principal: JSAS
//** Fecha Creación: 14/Feb/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class SaldosIndiOut {
	private Integer estatus;
	private String mensaje;
	private List<SaldosIndi> datos;
}

