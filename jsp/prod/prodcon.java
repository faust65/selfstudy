package prod;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/prodcon")
public class prodcon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	prodsv sv;
	
	public void init(ServletConfig cf) throws ServletException {
		super.init(cf);
    	sv=new prodsv();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act=request.getParameter("act");
		String view="";
		
		if(request.getParameter("act")==null) {
			getServletContext().getRequestDispatcher("/prodcon?act=list").forward(request, response);
		}else {
			switch (act) {
			case "list":view= list(request,response); break;
			case "info":view= info(request,response); break;
			}
			getServletContext().getRequestDispatcher("/prod/"+view).forward(request, response);
		}
	}
	
	
	private String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("p", sv.find(request.getParameter("id")));
		return "prodinfo.jsp";
	}

	private String info(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("prods", sv.findall());
		return "prodlist.jsp";
	}

}
