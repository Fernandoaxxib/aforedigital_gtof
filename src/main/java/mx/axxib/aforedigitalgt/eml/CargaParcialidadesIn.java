package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class CargaParcialidadesIn {
	private String noSolicitud;
	private String codEmpresa;
}

//p_num_solicitud 	IN	VARCHAR2			
//p_cod_empresa   	IN	VARCHAR2