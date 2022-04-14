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
public class ResRedesComerciales extends ResponseBase {
	private String avance;
	
	public ResRedesComerciales(String avance, Integer estatus, String mensaje) {
		this.setAvance(avance);
		this.setEstatus(estatus);
		this.setMensaje(mensaje);
	}
}
