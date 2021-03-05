package mx.axxib.aforedigitalgt.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class LlenaMovimientosOut {
	private Integer estatus;
	private String mensaje;
	private List<LlenaMovimiento> movimientos;
}

