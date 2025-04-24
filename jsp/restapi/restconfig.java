package restapi;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class restconfig extends Application {
	public Map<String,Object> getprorerties(){
		Map<String,Object> properties= new HashMap<String, Object>();
		properties.put("jersey.config.server.provider.packages", "restapi");
		return properties;
	}
}
