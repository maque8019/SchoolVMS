package test;

import java.sql.*;

public class q {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/vms";
        String user="root";
        String pwd="wm1234567890";
        String strSQl="SELECT * FROM Volunteer";
        Connection conn= DriverManager.getConnection(url,user,pwd);
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(strSQl);
        if(rs.next()){
            System.out.println(rs.getString(3));
            rs.getInt(1);
            rs.getInt(2);
            rs.getString(3);
            rs.getString(4);
            rs.getFloat(5);
        }

    }
}
