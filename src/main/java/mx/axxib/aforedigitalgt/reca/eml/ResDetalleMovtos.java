package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

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
public class ResDetalleMovtos extends ResponseBase {
    private List<DetalleMovto> detallesMovtos;

    public ResDetalleMovtos(
        List<DetalleMovto> detallesMovtos,
        Integer estatus,
        String mensaje
    ){
        this.setDetallesMovtos(detallesMovtos);
        this.setEstatus(estatus);
        this.setMensaje(mensaje);
    }
}
