package mx.axxib.aforedigitalgt.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class InfoUsuarioOut {	
	List<DatosUsuarioOut> datosUsuario;
	private String P_MENSAJE;
}
