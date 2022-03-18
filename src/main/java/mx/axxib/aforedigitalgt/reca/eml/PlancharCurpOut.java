package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlancharCurpOut {
	private List<LoteRecaudacionIssste> listLotes;
	private List<DatosTrabajador> listDatosTrabajadores;
	private String mensaje;
	private Integer estatus;
}
