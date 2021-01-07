package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "AprobSelecOut", 
				classes = { @ConstructorResult(targetClass = AprobSelecOut.class, 
					columns = {
						@ColumnResult(name = "OC_INDACCION", type = Integer.class),
						@ColumnResult(name = "OC_GLOBALERROR", type = String.class),						
						@ColumnResult(name = "OC_GLOBALEXITO", type = String.class),						
						@ColumnResult(name = "OC_GLOBALSISTPROC", type = String.class),												
						@ColumnResult(name = "OC_GLOBALABREVPROC", type = Date.class),												
						@ColumnResult(name = "OC_NOMBREAPLICACION", type = String.class),						
						@ColumnResult(name = "OC_MENSAJE", type = String.class)												
					})
				})
})
public class AprobSelecOut {    
    private String ocIndAccion;
    private String ocGlobalError;
    private String ocGlobalExito;
    private String ocGlobalSistProc;
    private String oc_GlobalAbrevProc;
    private String oc_NombreAplicacion;
    private String oc_Mensaje;
}