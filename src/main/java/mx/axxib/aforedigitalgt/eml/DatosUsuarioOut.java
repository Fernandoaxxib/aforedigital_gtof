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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "DatosUsuarioOut", classes = {
		@ConstructorResult(targetClass = DatosUsuarioOut.class, columns = {				
				@ColumnResult(name = "ID_USUARIO", type = Integer.class),
				@ColumnResult(name = "ID_PERFIL", type = Integer.class),
				@ColumnResult(name = "DESCRIP_PERFIL", type = String.class),
				@ColumnResult(name = "GPO_SEG_SITIO", type = Integer.class),								
				@ColumnResult(name = "USUARIO", type = String.class),
				@ColumnResult(name = "EMAIL", type = String.class),
				@ColumnResult(name = "NOMBRE", type = String.class),
				@ColumnResult(name = "APELLIDO_PATERNO", type = String.class),			
				@ColumnResult(name = "APELLIDO_MATERNO", type = String.class),
				@ColumnResult(name = "CURP", type = String.class),
				@ColumnResult(name = "RFC", type = String.class),
				@ColumnResult(name = "NCI", type = String.class),
				@ColumnResult(name = "ID_ESTATUS_USER", type = String.class),
				@ColumnResult(name = "FECHA_ALTA", type = Date.class),				
				@ColumnResult(name = "FECHA_ACTUALIZACION", type = Date.class),
				@ColumnResult(name = "TELEFONO", type = Double.class),
				@ColumnResult(name = "USUARIO_ASISTE", type = String.class),
				@ColumnResult(name = "PERFIL_ASISTE", type = Integer.class)				
		}) }) })
public class DatosUsuarioOut {

	private Integer ID_USUARIO;
    private Integer ID_PERFIL;
    private String DESCRIP_PERFIL;
    private Integer GPO_SEG_SITIO;
    private String USUARIO;
    private String EMAIL;
    private String NOMBRE;
    private String APELLIDO_PATERNO;
    private String APELLIDO_MATERNO;
    private String CURP;
    private String RFC;
    private String NCI;
    private String ID_ESTATUS_USER;
    private Date FECHA_ALTA;
    private Date FECHA_ACTUALIZACION;        
    private Double TELEFONO; 
    private String USUARIO_ASISTE;
    private Integer PERFIL_ASISTE; 
}
