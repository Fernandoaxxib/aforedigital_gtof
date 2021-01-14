package mx.axxib.aforedigitalgt.dal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.AforeMessage;

@Repository
public class RepoBase {

    protected EntityManager entityManager = null;

    @PersistenceContext
    public void RepoBase(EntityManager em) {
        this.entityManager = em;
    }
    
	@Autowired
	private AforeMessage aforeMessage;

	protected AforeException GenericException(Exception e) {
		AforeException error;
		if (e instanceof AforeException) {
			error = (AforeException) e;
		} else {
			error = new AforeException(aforeMessage.getCode("error.dal.generic", null),
					aforeMessage.getOnlyMessage("error.dal.generic", null), e.getMessage());
		}
		return error;
	}

}
