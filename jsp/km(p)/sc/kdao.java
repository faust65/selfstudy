package kgide;
//   view jsp 343p

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class kdao {
	public Connection open() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kmdb?allowPublicKeyRetrieval=true&useSSL=false","user","user");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<km> kmshow() throws Exception {
		Connection conn=open();
		List<km> kmlist=new ArrayList<>();
		String sql="select cid, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate, cont from km";
		PreparedStatement psmt=conn.prepareStatement(sql);
		ResultSet rs=psmt.executeQuery();
		
		try(conn; psmt; rs){
			while(rs.next()) {
				km k=new km();
				k.setCid(rs.getInt("cid"));
				k.setDate(rs.getString("cdate"));
				k.setCont(rs.getString("cont"));
				kmlist.add(k);
			}
		}
		return kmlist;
	}
	
	public void addkmu(km k) throws Exception {
		Connection conn=open();
		String sql="insert into km(date,cont) values(CURRENT_TIMESTAMP(),?)";
		PreparedStatement psmt=conn.prepareStatement(sql);
		
		try (conn; psmt){
			psmt.setString(1, k.getCont());
			psmt.executeUpdate();
		}
	}
	
	public void delkmu(int cid) throws Exception {
		Connection conn=open();
		String sql="delete from km where cid=?";
		PreparedStatement psmt=conn.prepareStatement(sql);
		
		try(conn; psmt){
			psmt.setInt(1,cid);
			if(psmt.executeUpdate()==0) {
				throw new SQLException("DB e");
			}
		}
	}
	
	public boolean login(km k) throws Exception{
		Connection conn=open();
		String sql="select uid,pw from kmlogin where uid=? && pw=?";
		PreparedStatement psmt=conn.prepareStatement(sql);
		ResultSet rs=null;
		
		try(conn; psmt){
			psmt.setString(1, k.getUid());
			psmt.setString(2, k.getPw());
			rs=psmt.executeQuery();
		}
		
		if(rs.next()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void newac(km k) throws Exception {
		Connection conn=open();
		String sql="insert into kmlogin(uid,pw) values(?,?)";
		PreparedStatement psmt=conn.prepareStatement(sql);
		
		try(conn; psmt){
			psmt.setString(1, k.getUid());
			psmt.setString(2, k.getPw());
			psmt.executeUpdate();
		}
	}
}
