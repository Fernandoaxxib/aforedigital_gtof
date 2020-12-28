package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class ReinversionComprasOut {
	
	private Integer lote;
	private Integer subcuenta;
	private String  siafore;
	private Integer monto;
	private Integer acciones;
}
