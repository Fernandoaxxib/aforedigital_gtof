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

public class ReporteSolcChequeRetiroIn {
	private Integer idTransaccion;
	private String idSubtransaccion;
	private Date fechaInicial;
	private Date fechaFinal;
	private Integer noSolicInicial;
	private Integer noSolicFinal;
	private String estado;
	
}

//P_TIP_TRANSAC IN NUMBER,  
//P_SUBTIP_TRANSAC IN VARCHAR2,
//P_FCH_INI IN DATE,
//P_FCH_FIN IN DATE,
//P_NO_SOLIC_INI IN NUMBER,
//P_NO_SOLIC_FIN IN NUMBER,
//P_ESTADO IN VARCHAR2,
