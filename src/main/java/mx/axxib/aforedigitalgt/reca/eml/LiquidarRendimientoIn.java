package mx.axxib.aforedigitalgt.reca.eml;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de liquidar rendimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 30/Nov/2021
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class LiquidarRendimientoIn {
	private String idLote;
	private BigDecimal total;
	private String transaccion;
	private String subTrans;
	private String transComp;
	private String tipo;
	
}

