package mx.axxib.aforedigitalgt.com;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthUserResponse {
    
    private	String codRespuesta;
    private	String token;
    private	String codError;
    private	String mensaje;
}
