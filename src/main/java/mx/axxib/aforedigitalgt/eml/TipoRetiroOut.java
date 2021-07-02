package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class TipoRetiroOut {
	private Integer cancelados;
	private Integer capturada;
	private Integer prevLiquida;
	private Integer liquidados;
	private Integer liquidaMens;
}
