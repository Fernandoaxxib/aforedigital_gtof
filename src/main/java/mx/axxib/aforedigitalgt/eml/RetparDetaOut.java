package mx.axxib.aforedigitalgt.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de desempleo parcialidades
//** Interventor Principal: JSAS
//** Fecha Creación: 30/Nov/2020
//** Última Modificación:
//***********************************************//

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class RetparDetaOut {
	private Integer estatus;
	private String mensaje;
	private List<RetparDeta> administrativos;
}



  