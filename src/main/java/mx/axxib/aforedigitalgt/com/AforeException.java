package mx.axxib.aforedigitalgt.com;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AforeException extends Exception {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String code;
	@Getter
	@Setter
	private String userMessage;
	@Getter
	@Setter
	private String shortMessage;
	
	
	
	

}
