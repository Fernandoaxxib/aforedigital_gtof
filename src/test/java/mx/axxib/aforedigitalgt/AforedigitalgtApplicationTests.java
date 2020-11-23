package mx.axxib.aforedigitalgt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.PermisoResult;
import mx.axxib.aforedigitalgt.serv.PermisoService;

@SpringBootTest
class AforedigitalgtApplicationTests {

	@Autowired
	private PermisoService permisoService;

	@Test
	public void getUserInfoTest() throws AforeException {
		PermisoResult rest = permisoService.getPermisosUsuario("1904922");
		System.out.println(rest.getDatos().size());
		assertThat(rest.getDatos()).isNotEmpty();
	}

}
