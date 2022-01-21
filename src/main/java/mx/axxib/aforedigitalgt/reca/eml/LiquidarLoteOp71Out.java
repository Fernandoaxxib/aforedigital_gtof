package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de liquidar lote op71
//** Interventor Principal: JJSC
//** Fecha Creación: 29/Dic/2021
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class LiquidarLoteOp71Out {

	private Integer on_Estatus;
	private String oc_Mensaje;
	private List<LoteOp71Out> listaLotes;
	private List<SieforeOut> listaSiefore;
	private List<SectorOut> listaSectores;
	private List<OperacionOut> listaOperaciones;
}
