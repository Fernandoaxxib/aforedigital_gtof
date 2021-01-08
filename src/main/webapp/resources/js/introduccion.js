function operaciones(){
	
	
	
	var valor=PF('test').getSelectedValue();

	if(valor==2){
		$("#admonCuentas").css("display", "none");
		$("#operacionGestion").css("display", "block");
		
	}
	if(valor==1){
		$("#admonCuentas").css("display", "block");
		$("#operacionGestion").css("display", "none");
	}
	
}