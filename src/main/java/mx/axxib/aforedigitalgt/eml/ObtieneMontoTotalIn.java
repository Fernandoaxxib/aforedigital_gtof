package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class ObtieneMontoTotalIn {
	private Date fechaInicial;
	private Date fechaFinal;
}

//P_FECHA IN DATE,
//P_FECHAI IN DATE,
