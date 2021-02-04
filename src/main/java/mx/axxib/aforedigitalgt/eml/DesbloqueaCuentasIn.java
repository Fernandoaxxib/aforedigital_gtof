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

public class DesbloqueaCuentasIn {
	private String codCuenta;
	private Integer clave;
	private String usuario;
	private Date fechaInicio;
		
}


