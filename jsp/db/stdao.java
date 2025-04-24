package dbb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class stdao {
	Connection con=null;
	PreparedStatement psmt;
	
	public void open() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tr?allowPublicKeyRetrieval=true&useSSL=false","user","1234");
	}
	
	public void close() throws SQLException {
		psmt.close();
		con.close();
	}
	
	public void insert(st s) throws ClassNotFoundException, SQLException {
		open();
		String sql="insert into st(un,univ,birth,email) values(?,?,?,?)";
		psmt=con.prepareStatement(sql);
		psmt.setString(1, s.getUn());
		psmt.setString(1, s.getUniv());
		psmt.setDate(1, s.getBirth());
		psmt.setString(1, s.getEmail());
		psmt.executeUpdate();
		close();
	}
	
	public List<st> getAll() throws ClassNotFoundException, SQLException {
		open();
		List<st> st=new ArrayList<>();
		psmt=con.prepareStatement("select * from st");
		ResultSet rs=psmt.executeQuery();
		while (rs.next()) {
			st s=new st();
			s.setId(rs.getInt("id"));
			s.setUn(rs.getString("un"));
			s.setUniv(rs.getString("univ"));
			s.setBirth(rs.getDate("birth"));
			s.setEmail(rs.getString("email"));
			st.add(s);
		}
		return st;
	}
}


