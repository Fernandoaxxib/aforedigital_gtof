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
public class ResBuscar extends ResponseBase{
    private List<Busqueda> busquedas;

    public ResBuscar(
        List<Busqueda> busquedas,
        Integer estatus,
        String mensaje
    ){
        this.setBusquedas(busquedas);
        this.setEstatus(estatus);
        this.setMensaje(mensaje);
    }
}
