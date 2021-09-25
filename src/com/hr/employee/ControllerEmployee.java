package com.hr.employee;

import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/ControllerEmployee")
public class ControllerEmployee extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/database")
	private DataSource dataSource;	
	private DBUtilEmployee dbUtil;

	@Override
	public void init() throws ServletException {
		try {
			this.dbUtil = new DBUtilEmployee(dataSource);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			ResultSet rSet = dbUtil.get_EmpInfo();
			request.setAttribute("emp_Info", rSet);
			RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
			dispatch.forward(request, response);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

	}
}
