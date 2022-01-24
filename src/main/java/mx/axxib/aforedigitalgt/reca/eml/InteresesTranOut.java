package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class InteresesTranOut {
	private List<FechaOut> listFechas;
	private List<LoteCargaOut> listLotes;
	private List<LoteRevOut> listatLotesRev;
	private String P_MENSAJE;
	private Integer P_ESTATUS;
}
