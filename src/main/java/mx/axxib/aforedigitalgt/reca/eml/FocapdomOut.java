package mx.axxib.aforedigitalgt.reca.eml;

import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.BaseOut;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class FocapdomOut extends BaseOut {
	@Getter
	@Setter
	private String salida;
	@Getter
	@Setter
	private DatosInicialesFocapdom datosIniciales;
}
