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
public class DatosGraficasTotalesOut {
	private String P_PERIODO_FEC;
	private List<GraficasTotalesOut> graficasTotales;
	private String P_MENSAJE;
	private Integer P_ESTATUS; 
}