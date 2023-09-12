package com.addrbook.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.addrbook.model.AddrDto;
import com.addrbook.service.DeleteService;
import com.addrbook.service.DeleteServiceImpl;
import com.addrbook.service.InsertService;
import com.addrbook.service.InsertServiceImpl;
import com.addrbook.service.ListService;
import com.addrbook.service.ListServiceImpl;
import com.addrbook.service.UpdateService;
import com.addrbook.service.UpdateServiceImpl;

/**
 * Servlet implementation class FrontControllerr
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		actionDo(request,response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8"); 
		
		if(command.equals("/insert.do")) {
			System.out.println("insert.do입니다.");
			
			AddrDto insAddr=new AddrDto();
			insAddr.setName(request.getParameter("name"));
			insAddr.setEmail(request.getParameter("email"));
			insAddr.setComdept(request.getParameter("comdept"));
			insAddr.setBirth(request.getParameter("birth"));
			insAddr.setTel(request.getParameter("tel"));
			insAddr.setMemo(request.getParameter("memo"));
			
			request.setAttribute("insAddr",insAddr);
			
			InsertService insertService = new InsertServiceImpl();
			insertService.execute(request, response);
			response.sendRedirect("/addrBook/list.do");
			
		}
		
		if(command.equals("/list.do")) {
			System.out.println("list.do 입니다.");
			
			ListService listService = new ListServiceImpl();
			ArrayList<AddrDto> addrList= listService.execute(request, response);
			
			request.setAttribute("addrList", addrList);
			
			RequestDispatcher requestDispatcher= request.getRequestDispatcher("/addrbook_list.jsp");
			requestDispatcher.forward(request, response);
		}
		
		if(command.equals("/update.do")) {
			System.out.println("update.do 입니다.");
			HttpSession session = request.getSession();
			
			int updId=(int)session.getAttribute("updDelId");
			
			AddrDto updAddr= new AddrDto();
			updAddr.setName(request.getParameter("name"));
			updAddr.setEmail(request.getParameter("email"));
			updAddr.setComdept(request.getParameter("comdept"));
			updAddr.setBirth(request.getParameter("birth"));
			updAddr.setTel(request.getParameter("tel"));
			updAddr.setMemo(request.getParameter("memo"));
			
			request.setAttribute("updId", updId);
			request.setAttribute("updAddr", updAddr);
			
			UpdateService updateService = new UpdateServiceImpl();
			updateService.execute(request, response);
			response.sendRedirect("/addrBook/list.do");
		}
		
		if(command.equals("/delete.do")) {
			System.out.println("delete.do입니다.");
			HttpSession session = request.getSession();
			
			int delId =(int)session.getAttribute("updDelId");
			request.setAttribute("delId", delId);
			
			DeleteService deleteService = new DeleteServiceImpl();
			deleteService.execute(request, response);
			response.sendRedirect("/addrBook/list.do");
		}
		
	}
	
}
