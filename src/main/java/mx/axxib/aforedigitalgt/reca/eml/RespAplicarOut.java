package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespAplicarOut {

	private String P_MENSAJE;
	private Integer P_ESTATUS;
	private List<LoteIssOut> lotesBono;
	private List<LoteIssOut> lotesReca;
}
