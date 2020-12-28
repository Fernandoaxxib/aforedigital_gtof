package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "ConsultarTraspasosOut", classes = { @ConstructorResult(targetClass = ConsultarTraspasosOut.class, columns = {

				@ColumnResult(name ="P_CLAVE_PROCESO", type = String.class),
				@ColumnResult(name ="P_DESC_PROCESO", type = String.class),
				@ColumnResult(name ="P_COD_TIPSALDO", type = String.class),
				@ColumnResult(name ="P_DESCRIPCION", type = String.class),
				@ColumnResult(name ="P_ESTADO", type = String.class),
				@ColumnResult(name ="P_DESC_ESTADO", type = String.class),
				@ColumnResult(name ="P_FECHA_INICIO", type = Date.class),
				@ColumnResult(name ="P_FECHA_FIN", type = Date.class),
				@ColumnResult(name ="P_INCLUIDO_POR", type = String.class),
				@ColumnResult(name ="P_MODIFICADO_POR", type = String.class),
				@ColumnResult(name ="P_FEC_INCLUSION", type = Date.class),
				@ColumnResult(name ="P_FEC_MODIFICACION", type = Date.class)
		}) })
		})

public class ConsultarTraspasosOut {
	
	private String claveProceso;
	private String descProceso;
	private String codTipSaldo;
	private String descSaldo;
	private String estado;
	private String descestado;
	private Date   fechaInicio;
	private Date   fechaFin;
	private String incluidoPor;
	private String modificadoPor;
	private Date   fechaInclusion;
	private Date   fechaModificacion;	
}
