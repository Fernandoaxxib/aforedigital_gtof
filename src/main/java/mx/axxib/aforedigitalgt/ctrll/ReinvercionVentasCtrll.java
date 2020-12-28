package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.ReinversionComprasOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoOut;
import mx.axxib.aforedigitalgt.eml.TotalReinversionCompras;

@Scope(value = "session")
@Component(value = "reinversionVentas")
@ELBeanName(value = "reinversionVentas")
public class ReinvercionVentasCtrll  extends ControllerBase {
	
	@Getter
	@Setter
	private Date fechaAplicacion;
	
	@Getter
	@Setter
	private String lote;
	
	@Getter
	@Setter
	List<ReinversionComprasOut> reinvercionComprasOut;
	
	@Getter
	@Setter
	TotalReinversionCompras totalReinversionCompras;
	
	
	public void getEjecutarReporte() {
		try {
			
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void getEjecutarCompra() {
		try {
			
		}catch (Exception e) {
			GenericException(e);
		}
	}
}
