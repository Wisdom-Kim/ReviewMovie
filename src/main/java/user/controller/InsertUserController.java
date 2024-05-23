package user.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.dao.UserDAO;
import user.dto.UserDTO;

@WebServlet("/insertUser.do")
public class InsertUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorUrl = "views/errors/error.jsp";
		
		String accountId = request.getParameter("accountId");
		String passwd = request.getParameter("passwd");
		String username = request.getParameter("username");
		String birthday = request.getParameter("birthday");
		boolean type = false;	// 일반 회원(관리자X)
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		UserDTO newUser = null;
		
		if(accountId == null || accountId == "" || passwd == null || passwd == ""
				|| username == null || username == "" || birthday == null || birthday == "") {
			response.sendRedirect(errorUrl);
		} else {
			try {	
				Date birthday_to_date = df.parse(birthday);

				newUser = new UserDTO(accountId, passwd, username, birthday_to_date, type);
				
				UserDAO.insertUser(newUser);
				
			} catch (ParseException e) {
				request.setAttribute("error", "회원가입 실패: 생일을 바르게 입력하세요.");
				request.getRequestDispatcher(errorUrl).forward(request, response);
			} 
//			catch (SQLException e) {
//				request.setAttribute("error", "회원가입 실패: 서버 에러");
//				request.getRequestDispatcher(errorUrl).forward(request, response);
//			}
		}
		
	}

}
