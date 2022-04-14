package mx.axxib.aforedigitalgt.reca.eml;

import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class ResponseBase {
	private Integer estatus;
	private String mensaje;
}
