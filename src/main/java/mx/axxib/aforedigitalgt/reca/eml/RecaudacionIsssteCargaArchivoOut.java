package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class RecaudacionIsssteCargaArchivoOut {	
	private String mensaje;
	private Integer estatus;
	private List<RecaudacionIsssteCargaArchivo> listaDatos;

}
