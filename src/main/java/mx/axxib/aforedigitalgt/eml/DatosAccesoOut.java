package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class DatosAccesoOut {
	private String P_CUENTA;
	private Integer P_ID_USUARIO;
	private Integer P_GPO_SEG_SITIO;
	private String P_MENSAJE;
}
