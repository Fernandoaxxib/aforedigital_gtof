package mx.axxib.aforedigitalgt.ctrll;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import mx.axxib.aforedigitalgt.com.AforeMessage;

@Component(value = "msg")
public class ResourceBundleBean extends HashMap<Object, Object> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AforeMessage aforeMessage;

    @Override
    public String get(Object key) {
        //ServletRequest request = (ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String message;
        try {
            message = aforeMessage.getMessage(key.toString().replace('_', '.'), null);
        }
        catch (NoSuchMessageException e) {
            message = "???" + key + "???";
        }
        return message;
    }
}
