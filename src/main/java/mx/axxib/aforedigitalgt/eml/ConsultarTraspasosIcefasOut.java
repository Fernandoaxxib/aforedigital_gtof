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
public class ConsultarTraspasosIcefasOut {
	
	private List<ConsultarDatosIcefasOut> cpCursor;
	private String mensaje;
	private Integer status;
}
