package mx.axxib.aforedigitalgt.eml;

import java.util.List;
import lombok.Data;


@Data
public class VerSolicitudChequeOut {
	private String   mensaje;
	private List<VerSolicitudChequeListOut> verSolicitudChequeListOut;
	
}
