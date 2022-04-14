package mx.axxib.aforedigitalgt.reca.eml;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class ResCargaArchivo extends ResponseBase {
	private Integer aceptadas;
	private Integer rechazadas;
	
	public ResCargaArchivo(
		Integer aceptadas,
		Integer rechazadas,
		Integer estatus,
        String mensaje
	) {
		this.setAceptadas(aceptadas);
		this.setRechazadas(rechazadas);
		this.setEstatus(estatus);
        this.setMensaje(mensaje);
	}
}
