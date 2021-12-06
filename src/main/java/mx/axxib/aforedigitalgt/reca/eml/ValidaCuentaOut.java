package mx.axxib.aforedigitalgt.reca.eml;

import java.math.BigDecimal;
import java.util.List;

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

public class ValidaCuentaOut {
	private Integer estatus;
	private String mensaje;
	private String descCuenta;
	
}

