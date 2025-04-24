package news;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(urlPatterns = "/newscon")
@MultipartConfig(maxFileSize = 1024*1024*2,location = "c:/Temp/img")
public class newscon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private newsdao dao;
	private ServletContext ctx;
	
	private final String start_page="newss/newslist.jsp";
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao=new newsdao();
		ctx=getServletContext();
	}
	
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		dao= new newsdao();
		Method m;
		String view=null;
		if(action==null) {
			action="listnews";
		}try {
		m=this.getClass().getMethod(action, HttpServletRequest.class);
		view=(String)m.invoke(this, request);
		}catch(Exception e) {
			System.out.println("e");
		}
		if(view.startsWith("redirect:/")) {
			String rview=view.substring("redirect:/".length());
			response.sendRedirect(rview);
		}else {
			RequestDispatcher dispacher=request.getRequestDispatcher(view);
			dispacher.forward(request, response);
		}
	}
	
	public String addnews(HttpServletRequest request){
		news n=new news();
		try {
			Part part = request.getPart("file");
			String filename=getfilename(part);
			if(filename!=null&&!filename.isEmpty()) {
				part.write(filename);
			}
		
		BeanUtils.populate(n, request.getParameterMap());
		n.setImg("/img/"+filename);
		dao.addnews(n);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return "redirect:/newscon?action=listnews";
	}
	
	public String deletenews(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		int aid=Integer.parseInt(request.getParameter("aid"));
		dao.delnews(aid);
		return "redirect:/newscon?action=listnews";
	}
	
	public String listnews(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		List<news> list;
		list=dao.getall();
		request.setAttribute("newslist", list);
		return "newss/newslist.jsp";
	}
	
	public String getnews(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		int aid=Integer.parseInt(request.getParameter("aid"));
		news n=dao.getnews(aid);
		request.setAttribute("news", n);
		return "newss/newsview.jsp";
	}
	
	private String getfilename(Part part) {
		String filename=null;
		String header=part.getHeader("content-disposition");
		System.out.println("header=>"+header);
		int start=header.indexOf("filename=");
		filename=header.substring(start+10,header.length()-1);
		ctx.log("파일명"+filename);
		return filename;
	}
}
