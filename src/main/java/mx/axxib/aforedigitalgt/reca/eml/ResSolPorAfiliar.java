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
public class ResSolPorAfiliar extends ResponseBase {
    private List<SolPorAfiliar> solsPorAfiliar;

    public ResSolPorAfiliar(
        List<SolPorAfiliar> solsPorAfiliar,
        Integer estatus,
        String mensaje
    ){
        this.setSolsPorAfiliar(solsPorAfiliar);
        this.setEstatus(estatus);
        this.setMensaje(mensaje);
    }
}
