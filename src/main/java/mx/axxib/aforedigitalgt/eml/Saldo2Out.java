package mx.axxib.aforedigitalgt.eml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Saldo2Out {
	private String cod_subproduct;
	private String descripcion;
	private Double saldo_subproduct;
	private Double total_saldos;
	private Boolean disabled;	
	private Boolean disabled2;
}
