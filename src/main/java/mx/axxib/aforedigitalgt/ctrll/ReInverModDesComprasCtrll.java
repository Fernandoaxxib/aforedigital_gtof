package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.LoteOP84Out;
import mx.axxib.aforedigitalgt.serv.ReInverModDesComprasServ;

@Scope(value = "session")
@Component(value = "reinversionModDesCompras")
@ELBeanName(value = "reinversionModDesCompras")
public class ReInverModDesComprasCtrll extends ControllerBase {

	@Autowired
	private ReInverModDesComprasServ service;
	
	@Getter
	@Setter
	private Date fecha;
	
	@Getter
	@Setter
	private String lote;
	
	@Getter
	@Setter
	private List<LoteOP84Out> listLotes;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			
		}
	}
	
	public void getLotes() {}
	
	public void generarReporte() {}
	
	public void comprar() {}
}
