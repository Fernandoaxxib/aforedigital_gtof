package mx.axxib.aforedigitalgt.com;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Clase que representa una excepción que posteriormente será guardada en un archivo json
//** Interventor Principal: JSAS
//** Fecha Creación: 19/Nov/2020
//** Última Modificación:
//***********************************************//
@AllArgsConstructor
public class AforeException extends Exception {

	private static final long serialVersionUID = 1L; 

	@Getter
	@Setter
	private String code;
	@Getter
	@Setter
	private String userMessage;
	@Getter
	@Setter
	private String shortMessage;


}
