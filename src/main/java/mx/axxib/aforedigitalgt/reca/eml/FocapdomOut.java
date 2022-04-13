package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.BaseOut;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class FocapdomOut extends BaseOut {
	@Getter
	@Setter
	private String salida;
	@Getter
	@Setter
	private DatosInicialesFocapdom datosIniciales;
	@Getter
	@Setter
	private List<OrigenAporta> listOrigen;
	@Getter
	@Setter
	private List<Periodicidad> listPeriodicidad;
	@Getter
	@Setter
	private List<BeneficiarioFis> listBenefeciario;
	@Getter
	@Setter
	private List<OrigenCaptura> listOrigenCaptura;
	@Getter
	@Setter
	private List<OrigenRecursos> listOrigenRecursos;
	@Getter
	@Setter
	private List<TipoMovimiento> listTipoMovimiento;
	@Getter
	@Setter
	private List<TipoPeriodo> listTipoPeriodo;
	@Getter
	@Setter
	private List<CompaniaCelular> listCompaniaCelular;
	@Getter
	@Setter
	private List<TipoPersona> listTipoPersona;
	@Getter
	@Setter
	private List<TipoIdentificacion> listTipoIdentificacion;
	@Getter
	@Setter
	private List<Banco> listBancos;
	@Getter
	@Setter
	private List<TipoCuenta> listTipoCuenta;
	@Getter
	@Setter
	private List<Asesor> listAsesor;
}
