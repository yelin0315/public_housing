package com.addrbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AddrDao {
	
	private static AddrDao instance= new AddrDao();
	
	public AddrDao() {}
	
	public static AddrDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() {
		Context context=null;
		DataSource dataSource=null;
		Connection connection=null;
		
		try {
			context=new InitialContext();
			dataSource=(DataSource)context.lookup("java:/comp/env/jdbc/Oracle11g");
			connection=dataSource.getConnection();
			System.out.println("getConncetion성공!");
		}catch(Exception e) {
			System.out.println("getConnection()예외 발생!");
			e.printStackTrace();
		}
		return connection;
	}
	
	public ArrayList<AddrDto> getList(){
		ArrayList<AddrDto> addrList= new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement("SELECT* FROM Addrbook ORDER BY AB_ID DESC");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int id= rs.getInt("ab_id");
				String name= rs.getString("ab_name");
				String email= rs.getString("ab_email");
				String comdept =rs.getString("ab_comdept");
				String birth = rs.getString("ab_birth");
				String tel =rs.getString("ab_tel");
				String memo =rs.getString("ab_memo");
				
				addrList.add(new AddrDto(id, name, email, comdept, birth, tel, memo));
				
			}
		}catch(SQLException e) {
			System.out.println("getList()예외 발생");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return addrList;
	}
	
	public void insertAddr(AddrDto addr) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn= getConnection();
			pstmt=conn.prepareStatement("INSERT INTO Addrbook VALUES(addr_seq.nextval,?,?,?,?,?,?)");
			pstmt.setString(1, addr.getName());
			pstmt.setString(2, addr.getEmail());
			pstmt.setString(3, addr.getComdept());
			pstmt.setString(4, addr.getBirth());
			pstmt.setString(5, addr.getTel());			
			pstmt.setString(6, addr.getMemo());
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("insertAddr()예외발생");
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public AddrDto readById(int id) {
		AddrDto addr= new AddrDto();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement("SELECT* FROM Addrbook WHERE ab_id=?");
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			
			rs.next();
			String name= rs.getString("ab_name");
			String email= rs.getString("ab_email");
			String comdept =rs.getString("ab_comdept");
			String birth = rs.getString("ab_birth");
			String tel =rs.getString("ab_tel");		
			String memo =rs.getString("ab_memo");
			
			addr= new AddrDto(id, name, email, comdept, birth, tel, memo);
		}catch(SQLException e) {
			System.out.println("readByID 예외발생");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return addr;
	}
	
	public void updateAddr(int id, AddrDto addr) {
		Connection conn=null;
		PreparedStatement pstmt= null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement("UPDATE Addrbook SET ab_name=?, ab_email=?, ab_comdept=?, ab_birth=?, ab_tel=?, ab_memo=? WHERE ab_id="+id);
			pstmt.setString(1, addr.getName());
			pstmt.setString(2, addr.getEmail());
			pstmt.setString(3, addr.getComdept());
			pstmt.setString(4, addr.getBirth());
			pstmt.setString(5, addr.getTel());		
			pstmt.setString(6, addr.getMemo());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("updateAddr()예외발생");
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void deleteAddr(int id) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement("DELETE FROM Addrbook WHERE ab_id="+id);
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("deleteAddr() 예외발생");
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
