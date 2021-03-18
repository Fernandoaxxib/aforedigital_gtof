package mx.axxib.aforedigitalgt.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class SalarioMinOut {
	private String mensaje;
	private List<SalarioMinimoOut> listSalarioMin;
	private Integer estatus;
}
