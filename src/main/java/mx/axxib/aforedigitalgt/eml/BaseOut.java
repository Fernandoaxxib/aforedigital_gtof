package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class BaseOut {
	private Integer estatus;
	private String mensaje;

}

//P_ESTATUS OUT NUMBER,
//P_MENSAJE OUT VARCHAR2
