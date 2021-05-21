package mx.axxib.aforedigitalgt.ctrll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.SalarioMinOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoInsertTablaOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoMensaje;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoOut;
import mx.axxib.aforedigitalgt.serv.SalarioMinimoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "salarioMinimo")
@ELBeanName(value = "salarioMinimo")
public class SalarioMinimoCtrll extends ControllerBase {
	
	@Autowired
	private SalarioMinimoServ salarioMinService;
	

//	@Setter
//	@Getter
//	private String zona;
	
	@Setter
	@Getter
	private String montoDiario;
	
	@Getter
	@Setter
	private Date fechaUlt;
	
	@Getter
	@Setter
	private Date fechaCalendario;
	
	
	
	@Getter
	@Setter
	private String insertUsuario;
	
	@Setter
	@Getter
	private String insertZona;
	
//	@Getter
//	@Setter
//	private List<SalarioMinimoTablaOut> salarioMinimoTablaOut;
	
	@Getter
	@Setter
	private  SalarioMinOut salarioMinOut;
	
	@Getter
	private List<SalarioMinimoOut> salarioMinimoTablaOut;
	
	@Getter
	private  SalarioMinimoOut seleccionado;
	
	@Getter
	@Setter
	private SalarioMinimoInsertTablaOut salarioMinimoInsertTablaOut;
	
	@Getter
	@Setter
	private SalarioMinimoMensaje salarioMinimoMensaje;
	
	@Getter
	private String mensajeTabla;
	
	@Getter
	@Setter
	private Integer totalIdUsuario;
	
	@Getter
	private String mensaje;
	
	private int modo; //0=no definido, 1=nuevo, 2=edicion 3=eliminar
	
	private ProcessResult prG;
	
	private String valorMonto;
	
	public String getValorMonto() {
		return valorMonto;
	}


	public void setValorMonto(String valorMonto) {
		this.valorMonto = valorMonto;
	}
	
	@Getter
	@Setter
	private Date fechaCalendar;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
		insertUsuario=null;
		insertZona=null;
		fechaCalendario=null;
		montoDiario=null;
		fechaUlt=null;
		modo=0;
		salarioMinimoTablaOut= new ArrayList<SalarioMinimoOut>();
		botonGenerarTabla();
		
		
		// Cancelar inicialización sobre la misma pantalla
		init = false;
		
	}
	}
	
	public void botonGenerarTabla() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Obtener Salario Minimo");
		try {
			
			//SalarioMinOut salarioMinOut =new SalarioMinOut();
			SalarioMinOut salarioMinOut = salarioMinService.getSalarioMinimo();
		   
		    if (salarioMinOut.getEstatus() == 1 && salarioMinOut.getListSalarioMin() != null && salarioMinOut.getListSalarioMin().size() > 0) {
				totalIdUsuario=salarioMinOut.getListSalarioMin().size();
				salarioMinimoTablaOut=salarioMinOut.getListSalarioMin();
				System.out.println("VALOR DE salarioMinimoTablaOut: "+totalIdUsuario);
		    	pr.setStatus("Consulta Exitosa");//"Consulta Exitosa"
			}else {
				if (salarioMinOut.getEstatus() == 2) {
					GenerarErrorNegocio(salarioMinimoMensaje.getMensaje());
				} else if (salarioMinOut.getEstatus() == 0) {
					prG.setStatus(salarioMinimoMensaje.getMensaje());
				}
//				pr.setStatus("No se encontraron resultados por Id Usuario");
//				mensajeTabla = "Sin información";
			}
		    			
			
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	public void nuevo() {
		seleccionado = null;
		insertUsuario=null;
		insertZona=null;
		fechaCalendario=null;
		montoDiario=null;
		fechaUlt=null;
		mensaje = "Nuevo Salario Minimo";
		modo = 1;
		prG = new ProcessResult();
		prG.setFechaInicial(DateUtil.getNowDate());
		prG.setDescProceso("Nuevo Salario Minimo");
		
	}
	
	public void editar(SalarioMinimoOut valor) {
		seleccionado = valor;
		mensaje = "Editar Salario Minimo";
		modo = 2;
		prG = new ProcessResult();
		prG.setFechaInicial(DateUtil.getNowDate());
		prG.setDescProceso("Editar Salario Minimo");
		montoDiario = valor.getMontoDiario().toString();
		//fechaCalendar = valor.getFechaCalendario();
	}
	
	public void borrar(SalarioMinimoOut valor) {
		seleccionado = valor;
		modo = 3;
		prG = new ProcessResult();
		prG.setFechaInicial(DateUtil.getNowDate());
		prG.setDescProceso("Borrar Salario Minimo");
	}
	
	
	public void guardar() {
		if(modo == 1 || modo == 2) {
			
			if(!ValidateUtil.isDouble(montoDiario)) {//valorMonto
				UIInput fini = (UIInput) findComponent("montoGuardar");
				fini.setValid(false);
				prG.setStatus("Monto no válido");
				resultados.add(prG);
				return;
			}
			
//			if (fechaCalendar == null) {
//				UIInput fini = (UIInput) findComponent("fechaGuardar");
//				fini.setValid(false);
//				prG.setStatus("Fecha UMA es requerida");
//				resultados.add(prG);
//				return;
//			}			
		}
		
		if(modo==1) {
			insertar();
		} else if(modo==2) {
			actualizar();
		} else if(modo==3) {
			eliminar();
		}
	}
	
	
	private void insertar() {
		try {
			SalarioMinimoOut parametros = new SalarioMinimoOut();
			
			//ValorUMA parametros = new ValorUMA();
			parametros.setCdZona(insertZona);
			parametros.setFechaCalendario(fechaCalendario);
			//parametros.setFechaUltimo(fechaUltimo);
			parametros.setMontoDiario(Double.parseDouble(montoDiario));
			parametros.setUserId("FO");
			
//			parametros.setFecha(fechaUMA);
//			parametros.setMonto(new BigDecimal (valorUMA));
//			parametros.setUser(dataSource.getUsername());
			//BaseOut res = valorUMAService.insertarUMA(parametros );
			salarioMinimoMensaje=salarioMinService.save("FO", insertZona, fechaCalendario, Double.parseDouble(montoDiario));//Double.parseDouble(montoDiario)
			System.out.println("MENSAJE: "+salarioMinimoMensaje.getMensaje());
			if (salarioMinimoMensaje.getEstatus() == 1) {
				prG.setStatus("Se guardo el nuevo Usuario");//"Consulta Exitosa"
				SalarioMinOut salarioMinOut =salarioMinService.getSalarioMinimo();
				if(salarioMinOut.getEstatus()==1) {
					salarioMinimoTablaOut=salarioMinOut.getListSalarioMin();	
				}
				
			}else {
				if (salarioMinimoMensaje.getEstatus() == 2) {
					GenerarErrorNegocio(salarioMinimoMensaje.getMensaje());
				} else if (salarioMinimoMensaje.getEstatus() == 0) {
					prG.setStatus(salarioMinimoMensaje.getMensaje());
				}
				
				//prG.setStatus("Error al guardar nuevo Usuario");
			}	
		} catch (Exception e) {
			prG = GenericException(e);
		} finally {
			prG.setFechaFinal(DateUtil.getNowDate());
			resultados.add(prG);
		}

	}	


	private void actualizar() {
		try {
			
			 //salarioMinimoMensaje=salarioMinService.update(dataSource.getUsername(), insertZona, fechaCalendario,fechaUlt, Double.parseDouble(montoDiario));
			seleccionado.setMontoDiario( Double.parseDouble(montoDiario));
			salarioMinimoMensaje=salarioMinService.update(seleccionado);
			System.out.println("VALOR DE UPDATE: "+salarioMinimoMensaje);
			if (salarioMinimoMensaje.getEstatus() == 1) {
					prG.setStatus("Se Edito el Usuario");//"Consulta Exitosa"
					SalarioMinOut salarioMinOut =salarioMinService.getSalarioMinimo();
					if(salarioMinOut.getEstatus()==1) {
						salarioMinimoTablaOut=salarioMinOut.getListSalarioMin();	
					}
					
				}else {
					if (salarioMinimoMensaje.getEstatus() == 2) {
						GenerarErrorNegocio(salarioMinimoMensaje.getMensaje());
					} else if (salarioMinimoMensaje.getEstatus() == 0) {
						prG.setStatus(salarioMinimoMensaje.getMensaje());
					}
					//prG.setStatus("Error al Editar  Usuario");
				}	
		} catch (Exception e) {
			prG = GenericException(e);
		} finally {
			prG.setFechaFinal(DateUtil.getNowDate());
			resultados.add(prG);
		}
	}
	
	private void eliminar() {
		try {
			System.out.println("VALOR DE SELECCIONADO: "+seleccionado);
			SalarioMinimoMensaje salarioMinimoMensaje = salarioMinService.delete(seleccionado);
			if (salarioMinimoMensaje.getEstatus() == 1) {
				prG.setStatus("Se borro el Usuario");//"Consulta Exitosa"
				SalarioMinOut salarioMinOut =salarioMinService.getSalarioMinimo();
				if(salarioMinOut.getEstatus()==1) {
					salarioMinimoTablaOut=salarioMinOut.getListSalarioMin();	
				}
				
			}else {
				if (salarioMinimoMensaje.getEstatus() == 2) {
					GenerarErrorNegocio(salarioMinimoMensaje.getMensaje());
				} else if (salarioMinimoMensaje.getEstatus() == 0) {
					prG.setStatus(salarioMinimoMensaje.getMensaje());
				}
				//prG.setStatus("Error al Editar  Usuario");
			}	
		} catch (Exception e) {
			prG = GenericException(e);
		} finally {
			prG.setFechaFinal(DateUtil.getNowDate());
			resultados.add(prG);
		}
	}
	
	
	
	
	
	public void onRowEdit(RowEditEvent<SalarioMinimoOut> event) { 
	//@SuppressWarnings("rawtypes")
	//public void onRowEdit(RowEditEvent event) {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Editar columna Usuario");
		
    	 
    	 //SalarioMinimoTablaOut salarioMinimoTabla=  (SalarioMinimoTablaOut) event.getObject();
    	 SalarioMinimoOut salarioMinimoTabla=  (SalarioMinimoOut) event.getObject();
    	
        //salarioMinimoTablaOut
        try {
        	//salarioMinimoMensaje=salarioMinService.update(salarioMinimoTabla.getUserId(), salarioMinimoTabla.getCdZona(), salarioMinimoTabla.getFechaCalendario(), salarioMinimoTabla.getMontoDiario());
			
			if (salarioMinimoMensaje.getEstatus() == 1) {
				
		    	pr.setStatus("Se edito columna Usuario");//"Consulta Exitosa"
			}else {
				pr.setStatus("Error al  editar columna Usuario");
				
			}

        }  catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
        
    }
     
    public void onRowCancel(RowEditEvent<SalarioMinimoOut> event) {
    	
    	ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Actualizacion de Usuario Cancelada");
		
        try {
        event.getObject().getUserId();
        pr.setStatus("Se cancelo el update columna Monto");
        }catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
    }
   
   
    
    public void onAddNew() {
    	ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Agregar Nuevo Usuario");
        //se insertaron  correctamente los datos
    									//String usuario, Date calendario, Double monto
    	
    	try {
    		//String msg=salarioMinService.save(salarioMinimoInsertTablaOut.getUserId(), salarioMinimoInsertTablaOut.getFechaCalendario(), salarioMinimoInsertTablaOut.getMontoDiario());
    		System.out.println("insertUsuario: "+insertUsuario+"   fechaCalendario: "+fechaCalendario+"  montoDiario: "+montoDiario);
    		if((insertUsuario == null || insertUsuario == "") || fechaCalendario ==null || montoDiario ==null) {
    			
    			boolean bandera=false;
    			
    			if((insertUsuario == null || insertUsuario=="") && fechaCalendario ==null && montoDiario ==null && insertZona==null){
    			UIInput inputUsuario = (UIInput) findComponent("usuarioGuardar");
    			inputUsuario.setValid(false);
    			UIInput inputZona = (UIInput) findComponent("zonaGuardar");
    			inputZona.setValid(false);
				UIInput inputFecha = (UIInput) findComponent("fechaGuardar");
				inputFecha.setValid(false);
				UIInput inputMonto = (UIInput) findComponent("montoGuardar");
				inputMonto.setValid(false);
				pr.setStatus("Ingresar Usuario,Zona,Fecha y Monto");
				bandera=true;
    			}
    			
    			
    			if(fechaCalendario ==null && montoDiario ==null && bandera==false){
        			UIInput inputFecha = (UIInput) findComponent("fechaGuardar");
    				inputFecha.setValid(false);
    				UIInput inputMonto = (UIInput) findComponent("montoGuardar");
    				inputMonto.setValid(false);
    				pr.setStatus("Ingresar Fecha y Monto");
    				bandera=true;
        		}
    			
    			if((insertUsuario == null || insertUsuario=="") && montoDiario ==null && bandera==false){
        			UIInput inputFecha = (UIInput) findComponent("usuarioGuardar");
    				inputFecha.setValid(false);
    				UIInput inputMonto = (UIInput) findComponent("montoGuardar");
    				inputMonto.setValid(false);
    				pr.setStatus("Ingresar Usuario y Monto");
    				bandera=true;
        		}
    			
    			if((insertUsuario == null || insertUsuario=="") && fechaCalendario ==null && bandera==false){
        			UIInput inputFecha = (UIInput) findComponent("usuarioGuardar");
    				inputFecha.setValid(false);
    				UIInput inputMonto = (UIInput) findComponent("fechaGuardar");
    				inputMonto.setValid(false);
    				pr.setStatus("Ingresar Usuario y Fecha");
    				bandera=true;
        		}
    			
    			if((insertUsuario == null || insertUsuario=="") && bandera==false){
    				UIInput inputUsuario = (UIInput) findComponent("usuarioGuardar");
        			inputUsuario.setValid(false);
    				pr.setStatus("Ingresar Usuario");
    				bandera=true;
        		}
    			
    			if(montoDiario ==null && bandera==false){
    				UIInput inputMonto = (UIInput) findComponent("montoGuardar");
    				inputMonto.setValid(false);
    				pr.setStatus("Ingresar Monto");
    				bandera=true;
        		}
    			
    			if(fechaCalendario ==null && bandera==false){
    				UIInput inputFecha = (UIInput) findComponent("fechaGuardar");
    				inputFecha.setValid(false);
    				pr.setStatus("Ingresar Fecha");
    				bandera=true;
        		}
    			if(esDecimal(montoDiario ) ==false && bandera==false){
    				UIInput inputMonto = (UIInput) findComponent("montoGuardar");
    				inputMonto.setValid(false);
    				pr.setStatus("Ingresar Digitos para Monto");
    				bandera=true;
        		}
    			if(insertZona  ==null && bandera==false){
    				UIInput inputMonto = (UIInput) findComponent("zonaGuardar");
    				inputMonto.setValid(false);
    				pr.setStatus("Ingresar Zona");
    				bandera=true;
        		}
    			
    		}else {
    			
    			
				salarioMinimoMensaje=salarioMinService.save(insertUsuario, insertZona, fechaCalendario, Double.parseDouble(montoDiario));//Double.parseDouble(montoDiario)
				System.out.println("MENSAJE: "+salarioMinimoMensaje.getMensaje());
				if (salarioMinimoMensaje.getEstatus() == 1) {
					
			    	pr.setStatus("Se guardo el nuevo Usuario");//"Consulta Exitosa"
				}else {
					pr.setStatus("Error al guardar nuevo Usuario");
				}	
				
				
    		}
			
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
    }
     
    public boolean esDecimal(String cad)
    {
    try
    {
      Double.parseDouble(cad);
      return true;
    }
    catch(NumberFormatException nfe)
    {
      return false;
    }
    }


	
}
