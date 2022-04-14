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
public class ResPendientes extends ResponseBase {
    private List<Pendiente> pendientes;

    public ResPendientes(
        List<Pendiente> pendientes,
        Integer estatus,
        String mensaje
    ){
        this.setPendientes(pendientes);
        this.setEstatus(estatus);
        this.setMensaje(mensaje);
    }
}
