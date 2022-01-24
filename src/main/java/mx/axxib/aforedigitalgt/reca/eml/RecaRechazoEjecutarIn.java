package mx.axxib.aforedigitalgt.reca.eml;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de recaudación imss
//** Interventor Principal: JSAS
//** Fecha Creación: 20/Dic/2021
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class RecaRechazoEjecutarIn {

	private String idOperacion;
	private String fechaLote;
	private String secLote;
	private Integer tipoRegistro;
	private String idServicio;
	private Integer consecutivo;
	private String nss;
}
