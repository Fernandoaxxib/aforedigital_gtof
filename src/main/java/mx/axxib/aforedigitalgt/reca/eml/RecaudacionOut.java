package mx.axxib.aforedigitalgt.reca.eml;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class RecaudacionOut {	
	private String mensaje;
	private Integer estatus;
	private BigDecimal totComprar;
	private Integer partComprar;
	private List<LoteDesmarcar> listDesmarcar;
	private List<Recaudacion> listRecaudacion;
}
