package DAL;

import java.sql.ResultSet;
import Entity.Student;
import Entity.TopAdmin;
import Entity.Volunteer;


public class TopAdminDAO extends abstractDAO{
    //构造函数
    public TopAdminDAO()
    {
        super();
    }
    public Student getStudentbyID(String stuID) {
        errorMessage="";
        Student aStudent=null;
        String sqlString="SELECT stu_id,stu_name From Student";
        sqlString=sqlString+" Where stu_id='"+stuID+"'";
        ResultSet rs=db.getRsbySQL(sqlString);
        try {
            if(rs.next()) {
                aStudent=new Student(
                        rs.getString(1),
                        rs.getString(2)
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            errorMessage=e.getMessage();
        }
        return aStudent;
    }
    public TopAdmin getTopAdmin(String topAdminName){
        errorMessage="";
        Student aStudent=null;
        TopAdmin aTopAdmin=null;
        String sqlString="SELECT stu_id,stu_name,password From Top_admin";
        sqlString=sqlString+"Where stu_name='"+topAdminName+"'";
        ResultSet rs= db.getRsbySQL(sqlString);
        try{
            if(rs.next()){
                String stuID=rs.getString(1);
                aStudent=this.getStudentbyID(stuID);
                aTopAdmin=new TopAdmin(
                        rs.getString(3),
                        aStudent
                );
            }
        }catch (Exception e){
            errorMessage=e.getMessage();

        }
        return aTopAdmin;
    }

    public Volunteer getVolunteer(String volName) {
        errorMessage="";
        Student aStudent=null;  //学生对象
        Volunteer aVolunteer=null;  //学生用户名

        String sqlString="SELECT vol_id,vol_name,password,stu_id From Volunteer";
        sqlString=sqlString+" Where vol_name='"+volName+"'";
        ResultSet rs=db.getRsbySQL(sqlString);
        try {
            if(rs.next()) {
                String stuID=rs.getString(4);//获得学号
                aStudent=this.getStudentbyID(stuID);//继续从学生表中获得学生对象
                //构造用户对象
                aVolunteer=new Volunteer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        aStudent
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            errorMessage=e.getMessage();
        }
        return aVolunteer;

    }
    public TopAdmin getTopAdminbyStudentID(String stuID) {
        //TODO: Something
        errorMessage = "";
        Student aStudent = null;
        TopAdmin aTopAdmin = null;
        String sqlString="SELECT stu_id,stu_name,password From Top_admin";
        sqlString=sqlString+" Where stu_id='"+stuID+"'";
        ResultSet rs = db.getRsbySQL(sqlString);
        try {
            if (rs.next()) {
                aStudent = this.getStudentbyID(stuID);//继续从学生表中获得学生对象
                aTopAdmin = new TopAdmin(
                        rs.getString(3),
                        aStudent
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            errorMessage = e.getMessage();
        }
        return aTopAdmin;
    }


    public boolean isUserExsit(String topAdminName) {
        //TODO: Something
        if(getTopAdmin(topAdminName)!=null )
        {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean insertTopAdmin(TopAdmin aTopAdmin){
        errorMessage="";
        String sqlString="INSERT INTO Top_admin(stu_id,password,stu_name) VALUES(";
        sqlString=sqlString+aTopAdmin.getStuInfo().getStuID()+"','";
        sqlString=sqlString+aTopAdmin.getTaPwd()+"','";
        sqlString=sqlString+aTopAdmin.getStuInfo().getStuName()+"','";
        if(db.ExcuteSQL(sqlString)==1){
            return true;
        }else {
            errorMessage="写入最高管理员信息数据不成功";
            return false;
        }

    }


    //获得类内部发生的错误信息
    public String getErrorMessage() {
        return errorMessage;
    }

}