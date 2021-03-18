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
public class SalarioMinOut {
	private String mensaje;
	private List<SalarioMinimoOut> listSalarioMin;
	private Integer estatus;
}
