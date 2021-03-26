package mx.axxib.aforedigitalgt.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ResultadoSaldosOut;
import mx.axxib.aforedigitalgt.serv.SaldosActualesServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "saldosActuales")
@ELBeanName(value = "saldosActuales")
public class SaldosActualesCtrll extends ControllerBase {

	@Autowired
	private SaldosActualesServ service;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {

		}
	}

	public void probar() throws Exception {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());

		try {
			ResultadoSaldosOut res = service.getDatosXNombre("");
			if (res.getP_ESTATUS() == 1) {
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (res.getP_ESTATUS() == 2) {
					GenerarErrorNegocio(res.getP_MENSAJE());
				} else if (res.getP_ESTATUS() == 0) {
					pr.setStatus(res.getP_MENSAJE());
				}
			}
		} catch (AforeException e) {
			pr = GenericException(e);
		}
	}

}
