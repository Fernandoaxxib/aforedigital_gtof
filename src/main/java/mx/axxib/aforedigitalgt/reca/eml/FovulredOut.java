package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.BaseOut;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class FovulredOut extends BaseOut{

	@Getter
	@Setter
	private String avance;
	@Getter
	@Setter
	private List<FechaReversa> listFechas;
	@Getter
	@Setter
	private List<LoteReporte> listLotes;
	@Getter
	@Setter
	private List<RedComercial> listRedesComerciales;
}
