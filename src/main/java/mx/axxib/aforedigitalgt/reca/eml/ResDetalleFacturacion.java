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
public class ResDetalleFacturacion extends ResponseBase {
    private List<DetalleFacturacion> detallesFacturacion;

    public ResDetalleFacturacion(
        List<DetalleFacturacion> detallesFacturacion,
        Integer estatus,
        String mensaje
    ){
        this.setDetallesFacturacion(detallesFacturacion);
        this.setEstatus(estatus);
        this.setMensaje(mensaje);
    }
}
