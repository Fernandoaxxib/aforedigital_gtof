package mx.axxib.aforedigitalgt.com;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Clase que representa la respuesta del servicio de autenticación rest
//** Interventor Principal: JSAS
//** Fecha Creación: 26/Julio/2021
//** Última Modificación:
//***********************************************//
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthUserResponse {
    
    private	String codRespuesta;
    private	String token;
    private	String codError;
    private	String mensaje;
}
