package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno base para las respuestas que sólo incluyen estatus y mensaje
//** Interventor Principal: JSAS
//** Fecha Creación: 19/Nov/2020
//** Última Modificación:
//***********************************************//
@Data
@NoArgsConstructor
@MappedSuperclass

public class BaseOut {
	private Integer estatus;
	private String mensaje;

}

//P_ESTATUS OUT NUMBER,
//P_MENSAJE OUT VARCHAR2
