package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class MarcasIn {
	private String codCuenta;
}

//P_COD_CUENTA     IN  VARCHAR2