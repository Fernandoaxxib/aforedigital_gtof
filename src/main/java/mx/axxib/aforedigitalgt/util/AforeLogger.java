package mx.axxib.aforedigitalgt.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import mx.axxib.aforedigitalgt.com.AforeException;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Se encarga de persistir a un archivo json las excepciones
//** Interventor Principal: JSAS
//** Fecha Creación: 19/Nov/2020
//** Última Modificación:
//***********************************************//
@Configuration
public class AforeLogger {
	
	@Value("${log.path}")
	private String logPath;

	public void saveException(AforeException ex) {
		
		JsonObject object = new JsonObject();
		object.addProperty("date", DateUtil.getNowStringDate("dd-MM-yyyy HH:mm:ss"));
		object.addProperty("code", ex.getCode());
		object.addProperty("userMessage", ex.getUserMessage());
		object.addProperty("shortMessage", ex.getShortMessage());
		object.addProperty("trace", Arrays.toString(ex.getStackTrace()));
		
		String now = DateUtil.getNowStringDate("ddMMyyyy");
	    String file = "log_".concat(now).concat(".json");
	    Path path = Paths.get(logPath, file);
		
		try (Writer writer = new FileWriter(path.toString(), true)) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(object, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
