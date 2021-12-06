package mx.axxib.aforedigitalgt.reca.eml;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de centro patronal
//** Interventor Principal: JSAS
//** Fecha Creación: 23/Nov/2021
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class AplicarCuentaIn {
	private String codCuenta;
	private String lote;
	private String indAccion;
	private String accion;
	private String subTrans;
	
}

