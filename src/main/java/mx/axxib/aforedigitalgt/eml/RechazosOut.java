package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class RechazosOut {
	private String nombre;
	private String fax;
	private String tipoPrestacion;
	private String descPrestacion;
	private String tipoPension;
	private String descPension;
	private Date fechaInclusion;
	private String noEmpleado;
	private String nombreEmpleado;
	private String plaza;
	private String causaRechazo;
	private String causaRechazo2;
	private String causaRechazo3;
	private String descCausa;
	private String descCausa2;
	private String descCausa3;
	private String mensaje;
}
  