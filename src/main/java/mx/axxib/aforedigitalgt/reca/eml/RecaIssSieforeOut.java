package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecaIssSieforeOut {

	private List<IssSieforeOut> listaIssSiefore;
	private Integer on_Estatus;
	private String oc_Mensaje;
	
}
