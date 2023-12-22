package dbUtils;

import  java.sql.*;

public class JavaMySQL implements IAccessDB {
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    MysqlServer sqlServer=new MysqlServer();

    private Connection conn=null;
    private Statement stmt=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;
    public JavaMySQL(){
        createConnection();
    }

    private void createConnection() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(sqlServer.getURL(),sqlServer.getUsername(),sqlServer.getPassword());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return conn;
    }

    public ResultSet getRsbySQL(String strSQL) {
        ResultSet rs=null;
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(strSQL);
            return rs;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

    }
    public int ExcuteSQL(String strSQL){
        int rowsCount=0;
        try {
            stmt=conn.createStatement();
            rowsCount=stmt.executeUpdate(strSQL);
            return rowsCount;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("");
            return -1;
        }
    }

    public int getaRecordRows(String strSQL) {
        ResultSet rs=null;
        int rows=0;
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(strSQL);
            if (rs.next())
                rows=rs.getInt(1);
            return rows;
        } catch (Exception e) {

            e.printStackTrace();
            return rows;
        }
    }

    public int getMaxID(String strSQL) {
        ResultSet rs=null;
        int maxID=0;
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(strSQL);
            if (rs.next())
                maxID=rs.getInt(1);
            return maxID;
        } catch (Exception e) {

            e.printStackTrace();
            return maxID;
        }
    }

    @Override
    public int ExecuteTrans(String[] sqlTxt) throws SQLException {
        int rows=ExcuteSQL(sqlTxt[0])+ExcuteSQL(sqlTxt[1]);
        return rows;
    }

    public void Close()
    {
        try {
            if(stmt!=null &&!stmt.isClosed() ) {
                stmt.close();
            }
            if(conn!=null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}