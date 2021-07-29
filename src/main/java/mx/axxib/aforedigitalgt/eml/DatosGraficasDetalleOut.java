package mx.axxib.aforedigitalgt.eml;

import java.util.List;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class DatosGraficasDetalleOut {
	private List<GraficasDetalleOut> graficasDetalle;
	private String P_MENSAJE;
	private Integer P_ESTATUS;
}
