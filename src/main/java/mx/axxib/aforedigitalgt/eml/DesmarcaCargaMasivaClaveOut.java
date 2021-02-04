package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class DesmarcaCargaMasivaClaveOut {

	private String p_DESCPROCESO;
	private String p_PROPERDESC;
}
