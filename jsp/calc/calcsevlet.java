package calc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcs")
public class calcsevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public calcsevlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int n1=Integer.parseInt(request.getParameter("n1"));
		int n2=Integer.parseInt(request.getParameter("n2"));
		String op=request.getParameter("op");
		long result=0;
		switch (request.getParameter("op")) {
			case "+": result=n1+n2; break;
			case "-": result=n1-n2; break;
			case "*": result=n1*n2; break;
			case "/": result=n1/n2; break;
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter o=response.getWriter();
		o.append("<html><body><h2>계</h2><hr>").append("계산 결과:"+result+"</body><html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
