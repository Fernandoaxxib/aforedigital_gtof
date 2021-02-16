package mx.axxib.aforedigitalgt.eml;

import java.util.List;


import lombok.Data;

@Data
public class VerPagoChequeOut {
	private String 	 mensaje;
	private List<VerPagoChequeListOut> VerPagoChequeListOut;
}
