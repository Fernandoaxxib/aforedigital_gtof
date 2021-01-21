package mx.axxib.aforedigitalgt.eml;

import java.util.List;

import lombok.Data;

@Data
public class SalarioMinOut {
	private String mensaje;
	private List<SalarioMinimoOut> listSalarioMin;
}
