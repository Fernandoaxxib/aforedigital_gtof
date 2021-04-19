package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class LlenaInfoOut {
	private List<LlenaInfo> datos;
	private BigDecimal totTitulos;
	private BigDecimal totPesos;
	private String mensaje;
	private Integer estatus;
}
  