package restapi;

import java.sql.SQLException;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import news.news;
import news.newsdao;

@Path("/news")
public class newsapiservice {
	newsdao dao;
	public newsapiservice() {
		dao= new newsdao();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addnews(news news) {
		try {
			dao.addnews(news);
		} catch (Exception e) {
			e.printStackTrace();
			return "newsadde";
		}
			return "newsadd";
	}
	
	@DELETE
	@Path("{aid}")
	public String delnews(@PathParam("aid") int aid) {
		try {
			dao.delnews(aid);
		} catch (Exception e) {
			e.printStackTrace();
			return "newsdele"+aid;
	}
		return "newsdel"+aid;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<news> getnewslist(){
		List<news> newslist=null;
		try {
			newslist=dao.getall();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newslist;
	}
	
	@GET
	@Path("{aid}")
	public news getnews(@PathParam("aid") int aid) {
		news news=null;
		try {
			news=dao.getnews(aid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}
}
