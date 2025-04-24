package dbb;

import java.sql.DriverManager;
import java.sql.SQLException;

public class bd {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tr?allowPublicKeyRetrieval=true&useSSL=false","user","1234");
    }
}