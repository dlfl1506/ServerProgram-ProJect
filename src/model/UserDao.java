package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import model.User;
import model.dto.LoginReqDto;

import view.DB;


public class UserDao {
	
	public int deleteById(int id) {
		String sql = "DELETE FROM user WHERE id = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public List<User> findAll(){
		String sql = "SELECT id,username,email,role FROM user"; // 0,4   4,4   8,4
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		List<User> users = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			
			// Persistence API
			while(rs.next()) { // 커서를 이동하는 함수
				User user = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.role(rs.getString("role"))
						.build();
				users.add(user);	
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		
		return null;
	}
	public int save(User user) { // 회원가입
		String sql = "INSERT INTO user(username, password, email, role) VALUES(?,?,?,?)";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getRole());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public User findByUsernameAndPassword(LoginReqDto dto) {   //  로그인
		String sql = "SELECT id, username, email, role FROM user WHERE username = ? AND password = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			rs =  pstmt.executeQuery();
			
			// Persistence API
			if(rs.next()) {
				User user = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.role(rs.getString("role"))
						.build();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
}
