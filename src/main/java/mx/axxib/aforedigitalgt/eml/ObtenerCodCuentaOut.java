package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class ObtenerCodCuentaOut {
	private String nombre;
	private String codCuenta;
	private String codEmpresa;
	private String codEstado;
	private String codCliente;
	private Date fechaCert;
	private String tipoSolic;
	private Integer estatus;
	private String mensaje;
}

//P_NOMBRE OUT VARCHAR2,
//P_COD_CUENTA OUT VARCHAR2,
//P_COD_ESTADO IN OUT VARCHAR2,
//P_COD_CLIENTE OUT VARCHAR2,
//P_FECHA_CERTIF OUT DATE, 
//P_TIP_SOLICITUD OUT VARCHAR2,
//P_ESTATUS  OUT NUMBER,
//P_MENSAJE  OUT VARCHAR2