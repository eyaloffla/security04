package com.offla.secured;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Test/30ee80fa-f254-4f7c-9d25-9a1811cf295f/devices")
@ServletSecurity(@HttpConstraint(rolesAllowed={"TAIUserRole"}))
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<br><br>" + "<XX><XX> " + "===doGet===");
		System.out.println("<XX><XX> " + "===doGet===");
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("<XX><XX> " + "===doPost===");
		String greeting = request.getParameter("greeting");
		System.out.println("greeting : " + greeting );
        response.getWriter().write("POST response : " + greeting);
}
}
