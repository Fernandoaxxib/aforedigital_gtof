package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class DesmarcaCargaConsultaMasivaOut {
	private String p_Mensaje;
	private Integer on_Estatus;
}
