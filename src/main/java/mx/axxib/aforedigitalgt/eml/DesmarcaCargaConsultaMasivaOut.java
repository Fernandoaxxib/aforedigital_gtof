package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class DesmarcaCargaConsultaMasivaOut {
	private String mensaje;
	private Integer estatus;
	private Integer regresa;
}
