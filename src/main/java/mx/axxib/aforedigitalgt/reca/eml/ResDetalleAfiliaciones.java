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
public class ResDetalleAfiliaciones extends ResponseBase {
    private List<DetalleAfiliacion> detallesAfiliacion;

    public ResDetalleAfiliaciones(
        List<DetalleAfiliacion> detallesAfiliacion,
        Integer estatus,
        String mensaje
    ){
        this.setDetallesAfiliacion(detallesAfiliacion);
        this.setEstatus(estatus);
        this.setMensaje(mensaje);
    }
}