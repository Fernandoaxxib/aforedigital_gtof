package mx.axxib.aforedigitalgt.eml;

import java.util.List;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.axxib.aforedigitalgt.reca.eml.LoteDesmarcar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class RecaudacionOut {	
	private String mensaje;
	private Integer estatus;
	private List<LoteDesmarcar> listDesmarcar;
	private List<Recaudacion> listRecaudacion;
}
