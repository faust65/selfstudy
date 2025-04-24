package news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class newsdao {
	
	public Connection open() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("깡!!!!");
		}
        try {
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tr?allowPublicKeyRetrieval=true&useSSL=false","user","1234");
		} catch (SQLException e) {
			System.out.println("깡1!!!!");
		}
		return conn;
	}
	
	public List<news> getall() throws ClassNotFoundException, SQLException{
		Connection conn=open();
		List<news> newslist=new ArrayList<>();
		String sql="select aid,title,STR_TO_DATE(date,'yyyy-mm-dd hh:mm:ss') as cdate from news";
		PreparedStatement pstmt =conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while (rs.next()) {
			news n=new news();
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setDate(rs.getString("cdate"));
			newslist.add(n);
		}
		return newslist;
	}
	
	public news getnews(int aid) throws ClassNotFoundException, SQLException {
		Connection conn=open();
		news n=new news();
		String sql="select aid,title,img,STR_TO_DATE(date,'yyyy-mm-dd hh:mm:ss') as cdate,content from news where aid=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		n.setAid(rs.getInt("aid"));
		n.setTitle(rs.getString("title"));
		n.setImg(rs.getString("img"));
		n.setDate(rs.getString("cdate"));
		n.setContent(rs.getString("content"));
		pstmt.executeQuery();
		return n;
	}
	
	public void addnews(news n) throws SQLException, ClassNotFoundException {
		Connection conn=open();
		System.out.println("1");
		String sql="insert into news(title,img,date,content) valuse(?,?,CURRENT_TIMESTAMP(),?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, n.getTitle());
		pstmt.setString(2, n.getImg());
		pstmt.setString(3, n.getContent());
		pstmt.executeUpdate();
	}
	
	public void delnews(int aid) throws SQLException, ClassNotFoundException {
		Connection conn=open();
		String sql="delete from news where aid=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		if(pstmt.executeUpdate()==0) {
			throw new SQLException("DBe");
		}
	}
}

