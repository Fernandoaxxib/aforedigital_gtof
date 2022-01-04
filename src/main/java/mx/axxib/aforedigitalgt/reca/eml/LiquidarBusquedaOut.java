package mx.axxib.aforedigitalgt.reca.eml;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de liquidar movimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 17/Nov/2021
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class LiquidarBusquedaOut {
	private Integer estatus;
	private String mensaje;
	private List<LiquidarLotes> lotes;
	
	private BigDecimal acciones;
	private BigDecimal acciones1;
	private BigDecimal acciones2;
	private BigDecimal acciones3;
	private BigDecimal acciones4;
	
	private BigDecimal monto;
	private BigDecimal monto1;
	private BigDecimal monto2;
	private BigDecimal monto3;
	private BigDecimal monto4;
}

