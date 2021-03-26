package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class ObtenerCodCuentaHOut {
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

//NUM_SEGURO_SOCIAL STRING
//COD_ESTADO STRING
//COD_CLIENTE STRING
//COD_CUENTA STRING
//DIAS_ACUMULADOS INTEGER
//EST_CUENTA STRING
//DIAS_PAGADOS INTEGER
//COD_PRODUCTO INTEGER
//DESCRIPCION STRING
//NOMBRE STRING