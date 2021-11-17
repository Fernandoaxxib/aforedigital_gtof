package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientosOut {

	private Integer P_ESTATUS;
	private String P_MENSAJE;
	private List<MovimientoImssOut> listaMovimientos;
	
}
