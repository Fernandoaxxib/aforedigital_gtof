package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class ObtieneMontoRetiroIn {
	private String tipoRetiro;
	private Date fechaFinal;
	private Date fechaInicial;
	private Integer tipoTransaccion;
	private String subTipoTransaccion;
}

//P_TIP_RETIRO IN VARCHAR2,
//P_FECHA IN DATE,
//P_FECHAI IN DATE,
//P_TIP_TRANSACCION IN NUMBER,
//P_SUBTIP_TRANSAC IN NUMBER,
