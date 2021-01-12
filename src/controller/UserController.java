package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.CommonRespDto;
import com.google.gson.Gson;

import model.dto.LoginReqDto;

import view.Script;

import model.User;
import model.UserDao;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserDao userdao = new UserDao();
		if(cmd.equals("joinForm")) {
			RequestDispatcher dis = 
					request.getRequestDispatcher("user/join.jsp");
				dis.forward(request, response);
		}else if (cmd.equals("loginForm")) {
			RequestDispatcher dis = 
					request.getRequestDispatcher("user/login.jsp");
				dis.forward(request, response);
		}else if (cmd.equals("join")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String role = request.getParameter("role");
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setRole(role);
			System.out.println("회원가입 : "+user);
			int result = userdao.save(user);
			if(result == 1) {
				response.sendRedirect("index.jsp");
			}else {
				Script.back(response, "회원가입실패");
			}
		}else if (cmd.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
		
			LoginReqDto dto = new LoginReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			User userEntity = userdao.findByUsernameAndPassword(dto);
			if(userEntity != null && userEntity.getRole().equals("user")) {
				HttpSession session = request.getSession();
				session.setAttribute("user", userEntity); // 인증주체
				RequestDispatcher dis = 
						request.getRequestDispatcher("index.jsp");
					dis.forward(request, response);
			}else if (userEntity != null  && userEntity.getRole().equals("admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("admin", userEntity); // 인증주체
				RequestDispatcher dis = 
						request.getRequestDispatcher("index.jsp");
					dis.forward(request, response);
			}
			else {
				Script.back(response, "로그인실패");
			}
		}
		else if(cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		}else if (cmd.equals("userlist")) {
			
			List<User> users = userdao.findAll();
			request.setAttribute("list", users);
			RequestDispatcher dis = 
					request.getRequestDispatcher("user/userList.jsp");
				dis.forward(request, response);
		}else if(cmd.equals("delete")) {
			// 1. 요청 받은 json 데이터를 자바 오브젝트로 파싱
			int id = Integer.parseInt(request.getParameter("id"));

			// 2. DB에서 id값으로 글 삭제
			int result = userdao.deleteById(id);
			
			// 3. 응답할 json 데이터를 생성
			CommonRespDto<String> commonRespDto = new CommonRespDto<>();
			commonRespDto.setStatusCode(result);
			commonRespDto.setData("성공");
			
			Gson gson = new Gson();
			String respData = gson.toJson(commonRespDto);
			System.out.println("respData : "+respData);
			PrintWriter out = response.getWriter();
			out.print(respData);
			out.flush();
		
		}
	}
}