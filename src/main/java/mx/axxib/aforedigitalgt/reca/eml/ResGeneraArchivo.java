package mx.axxib.aforedigitalgt.reca.eml;

import javax.persistence.MappedSuperclass;

//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
//@AllArgsConstructor
//@NoArgsConstructor
@MappedSuperclass
public class ResGeneraArchivo extends ResponseBase {
    public ResGeneraArchivo(
        Integer estatus,
        String mensaje
    ){
        this.setEstatus(estatus);
        this.setMensaje(mensaje);
    }
}
