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
public class ReporteOut {

	private String p_Ruta;
	private String p_Archivo;
	private String oc_Mensaje;
	private Integer on_Estatus;
	private String p_Avance;
}
