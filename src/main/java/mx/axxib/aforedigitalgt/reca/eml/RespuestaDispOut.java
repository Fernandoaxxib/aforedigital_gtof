package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDispOut {

	private List<LoteDispOut> lotes;
	private List<EmpresaOut> listaEmpresas;
	private List<EmpresaOut> lotesEmpresa;	
	private String oc_IND_ACCION;
	private Integer on_Estatus;
	private String oc_Mensaje;
}
