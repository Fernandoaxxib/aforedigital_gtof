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
public class RecaudacionIsssteArchivoRespuestaOut {	
	private String mensaje;
	private Integer estatus;
	private String seguimiento;
	private List<RecaudacionIsssteArchivoRespuesta> listaDatos;

}
