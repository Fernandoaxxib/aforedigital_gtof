package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;
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
		@SqlResultSetMapping(name = "ConsultaNSS", 
				classes = { @ConstructorResult(targetClass = ConsultaNSS.class, 
					columns = {
						@ColumnResult(name = "PERIODO_PAGO", type = String.class),
						@ColumnResult(name = "ULTIMO_SDI_PERIODO", type = BigDecimal.class),
						@ColumnResult(name = "REGISTRO_PATRONAL_IMSS", type = String.class),
						@ColumnResult(name = "FEC_APLICADO", type = Date.class),
						@ColumnResult(name = "VALOR_TITULO", type = BigDecimal.class),
						@ColumnResult(name = "SIEFORE", type = String.class),
						@ColumnResult(name = "SAR92_MONTO", type = BigDecimal.class),
						@ColumnResult(name = "APORTACION_ESTATAL_MONTO", type = BigDecimal.class),
						@ColumnResult(name = "RETIRO_MONTO", type = BigDecimal.class),
						@ColumnResult(name = "CESANTIA_VEJES_MONTO", type = BigDecimal.class),
						@ColumnResult(name = "CUOTA_SOCIAL_MONTO", type = BigDecimal.class),
						@ColumnResult(name = "APORTACION_ESPECIAL_MONTO", type = BigDecimal.class),
						@ColumnResult(name = "VIVIENDA_97_MONTO", type = BigDecimal.class),
						@ColumnResult(name = "APORTACION_ESTATAL_CUOTA", type = BigDecimal.class),
						@ColumnResult(name = "RETIRO_CUOTA", type = BigDecimal.class),
						@ColumnResult(name = "CESANTIA_VEJES_CUOTA", type = BigDecimal.class),
						@ColumnResult(name = "CUOTA_SOCIAL_CUOTA", type = BigDecimal.class),
						@ColumnResult(name = "APORTACION_ESPECIAL_CUOTA", type = BigDecimal.class),
						@ColumnResult(name = "VIVIENDA_97_CUOTA", type = BigDecimal.class)
						
					})
				})
})

public class ConsultaNSS {
	private String periodo;
	private BigDecimal ultimoSDI;
	private String registroPatronal;
	private Date fechaAplicado;
	private BigDecimal valorTitulo;
	private String siefore;
	private BigDecimal sar92Monto;
	
	private BigDecimal aportacionEstatalMonto;
	private BigDecimal retiroMonto;
	private BigDecimal cesantiaVejezMonto;
	private BigDecimal cuotaSocialMonto;
	private BigDecimal aportacionEspecialMonto;
	private BigDecimal vivienda97Monto;
	
	private BigDecimal aportacionEstatalCuota;
	private BigDecimal retiroCuota;
	private BigDecimal cesantiaVejezCuota;
	private BigDecimal cuotaSocialCuota;
	private BigDecimal aportacionEspecialCuota;
	private BigDecimal vivienda97Cuota;
	
//	private String noMovmimiento;
//	private Date fechaMovimiento;
}

//P_PERIODO_DE_PAGO    	OUT	VARCHAR2
//P_ULTIMO_SDI_PERIODO   	OUT	NUMBER
//P_REGISTRO_PATRONAL_IMSS OUT VARCHAR2,   	OUT	VARCHAR2
//P_FEC_APLICADO             	OUT	DATE
//P_VALOR_TITULO              	OUT	DATE
//P_SIEFORE  OUT                	OUT	VARCHAR2
//P_SAR92_MONTO             	OUT	NUMBER
//P_APORTACION_ESTATAL_MONTO  	OUT	NUMBER
//P_RETIRO_MONTO              	OUT	NUMBER
//P_CESANTIA_VEJES_MONTO      	OUT	NUMBER
//P_CUOTA_SOCIAL_MONTO      	OUT	NUMBER
//P_APORTACION_ESPECIAL_MONTO  	OUT	NUMBER
//P_VIVIENDA_97_MONTO   	OUT	NUMBER
//P_APORTACION_ESTATAL_CUOTA   	OUT	NUMBER
//P_RETIRO_CUOTA               	OUT	NUMBER
//P_CESANTIA_VEJES_CUOTA 	OUT	NUMBER
//P_CUOTA_SOCIAL_CUOTA     	OUT	NUMBER
//P_APORTACION_ESPECIAL_CUOTA  	OUT	NUMBER
//P_VIVIENDA_97_CUOTA 	OUT	NUMBER