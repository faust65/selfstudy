package dbb;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

// sql 테이블이 없어서 그런 거 아님?
// ㄴ그렇더라
@WebServlet("/stcon")
public class stcon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	stdao dao;
    public void init(ServletConfig cf) throws ServletException {
        super.init(cf);
        dao=new stdao();
    }
    
    protected void svs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, IllegalAccessException, InvocationTargetException {
    	request.setCharacterEncoding("utf-8");
    	String action=request.getParameter("action");
    	String view="";
    	if(action==null) {
    		getServletContext().getRequestDispatcher("/stcon?action=list").forward(request, response);
    	}else {
    		switch (action) {
			case "list": view=list(request,response); break;
			case "insert": view= insert(request,response); break;
		 }
    		getServletContext().getRequestDispatcher("/db/"+view).forward(request, response);
    	}
    }

	public String list(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		request.setAttribute("st", dao.getAll());
		return "stinfo.jsp";
	}

	public String insert(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, SQLException {
		st s=new st();
		BeanUtils.populate(s,request.getParameterMap());
		dao.insert(s);
		return list(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    try {
	        svs(request, response);
	    } catch (ClassNotFoundException | SQLException | IllegalAccessException | InvocationTargetException e) {
	        e.printStackTrace();
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    try {
	        svs(request, response);
	    } catch (ClassNotFoundException | SQLException | IllegalAccessException | InvocationTargetException e) {
	        e.printStackTrace();
	    }
	}

	
}
