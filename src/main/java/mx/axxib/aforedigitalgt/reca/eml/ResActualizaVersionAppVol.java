package mx.axxib.aforedigitalgt.reca.eml;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class ResActualizaVersionAppVol extends ResponseBase {
    private String aviso;

    public ResActualizaVersionAppVol(
        String aviso,
        Integer estatus,
        String mensaje
    ){
        this.setAviso(aviso);
        this.setEstatus(estatus);
        this.setMensaje(mensaje);
    }
}
