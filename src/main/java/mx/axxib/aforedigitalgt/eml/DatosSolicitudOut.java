package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class DatosSolicitudOut {
	private String noSolicitud;
	private String curp;
	private String rfc;
	private String primerApellido;
	private String segundoApellido;
	private String nombre;
	private String monTotalPrestacion;
	private Integer sbcTipoA;
	private String codEmpresa;
	private String clasificacionPago;
	private String estatusSolicitud;
	private String numResolucion;
	private String fondo;
	private String codCuenta;
	private Date fechaAccion;
	private String fondoDescripcion;
	private String pagoDescripcion;
	private String remanente;
	
}

//P_NUM_SOLICITUD      	OUT	 VARCHAR2
//P_CURP  	OUT	VARCHAR2
//P_RFC    	OUT	VARCHAR2
// P_PRIMER_APELLIDO   	OUT	VARCHAR2
//P_SEGUNDO_APELLIDO   	OUT	VARCHAR2
//P_NOMBRE             	OUT	VARCHAR2
//P_MON_TOTAL_PRESTACION 	OUT	VARCHAR2
//P_SBC_TIPO_A    	OUT	NUMBER
//P_COD_EMPRESA     	OUT	VARCHAR2
//P_CLASIFICACION_PAGO   	OUT	VARCHAR2
//P_ESTATUS_SOLICITUD 	OUT	VARCHAR2
// P_NUM_RESOLUCION  	OUT	VARCHAR2
//P_FONDO           	OUT	VARCHAR2
//P_COD_CUENTA        	OUT	VARCHAR2
//P_FECHA_ACCION        	OUT	DATE
//P_FONDO_DESCRIPCION  	OUT	VARCHAR2
//P_PAGO_DESC   	OUT	OUT
//P_REMANENTE  OUT VARCHAR2	OUT	VARCHAR2