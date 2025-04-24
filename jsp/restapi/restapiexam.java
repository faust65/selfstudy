package restapi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class restapiexam {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String shello() {
		return "hapi";
	}
	@POST
	public String shello(@QueryParam("msg") String msg) {
		return msg+"api";
	}
}