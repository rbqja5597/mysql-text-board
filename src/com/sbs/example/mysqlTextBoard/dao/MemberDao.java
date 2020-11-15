package com.sbs.example.mysqlTextBoard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sbs.example.mysqlTextBoard.dto.Member;

public class MemberDao {
	private List<Member> members;
	private int lastMemberId;
	
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000&socketTimeout=60000";

	private final String USER_NAME = "sbsst";
	private final String PASSWORD = "sbs123414";
	
	Connection conn = null;
	Statement state = null;
	PreparedStatement pstmt;
	ResultSet rs;
	

	public MemberDao() {
		lastMemberId = 0;
		members = new ArrayList<>();
		
	}
	
	public int join(String loginId, String loginPw, String name) {
		int id = 0;
		Connection con = null;

		try {
			String dbmsJdbcUrl = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000&socketTimeout=60000";
			String dbmsLoginId = "sbsst";
			String dbmsLoginPw = "sbs123414";

			// MySQL 드라이버 등록
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			// 연결 생성
			try {
				con = DriverManager.getConnection(dbmsJdbcUrl, dbmsLoginId, dbmsLoginPw);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String sql = "INSERT INTO members";
			sql += " SET loginId = ?";
			sql += ", loginPw = ?";
			sql += ", name = ?";

			try {
				PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, loginId);
				pstmt.setString(2, loginPw);
				pstmt.setString(3, name);
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				id = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}

		return null;
	}

	public boolean isJoinAvailabelLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}

		return true;
	}

	

}
