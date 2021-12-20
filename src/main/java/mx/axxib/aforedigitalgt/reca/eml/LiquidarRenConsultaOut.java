package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de liquidar rendimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 20/Dic/2021
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class LiquidarRenConsultaOut {
	private Integer estatus;
	private String mensaje;
	private List<LiquidarRenConsulta> montos;

	
}

