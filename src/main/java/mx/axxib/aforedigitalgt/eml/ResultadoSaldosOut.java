package mx.axxib.aforedigitalgt.eml;

import java.util.List;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class ResultadoSaldosOut {

	private String P_MENSAJE;
	private Integer P_ESTATUS;
	private String P_TOTALES_SALDOS;
	private List<DetalleSaldoOut> listSaldos;
	private List<DatosClienteOut> datosCliente;
	private List<DatosClienteNSSOut> datosClienteNSS;
	private List<Saldo2Out> saldos;
}
