package mx.axxib.aforedigitalgt.reca.eml;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Consulta bono pensión
//** Interventor Principal: JSAS
//** Fecha Creación: 11/Mar/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class FechaCorteOut {
	private Integer estatus;
	private String mensaje;
	
	private BigDecimal valorUDI;
	private BigDecimal pagoAnt;
	private BigDecimal compVal;
	private BigDecimal valorPesos;
	private BigDecimal valorPesosCom;
	
}

//   P_VALOR_UDI_DIA OUT NUMBER,
//   P_PAGO_ANT_UDIS  OUT NUMBER,
//   P_COMP_VAL_NOM_UDIS OUT NUMBER,
//   P_VAL_PESOS_PAG_ANT  OUT NUMBER,
//   P_VAL_PESOS_COM_VALOR_NOM OUT NUMBER,
