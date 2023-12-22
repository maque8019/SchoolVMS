package dbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IAccessDB {
    ResultSet getRsbySQL(String sqlTxt);//根据SQL语言获得一个结果集
    int ExcuteSQL(String sqlTxt);//根据SQL语言执行数据库的增删改;
    public int getaRecordRows(String strSQL) ;  //获得符合条件记集行数
    public int getMaxID(String strSQL);   //获得最大的ID号
    public int ExecuteTrans(String[] sqlTxt) throws SQLException;//事务
}