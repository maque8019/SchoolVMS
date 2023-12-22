package DAL;

import java.sql.ResultSet;

import Entity.Student;
import Entity.Volunteer;


public class VolunteerDAO extends abstractDAO{
    //构造函数
    public VolunteerDAO()
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
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2)
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            errorMessage=e.getMessage();
        }
        return aStudent;
    }

    public Volunteer getVolunteer(String volName) {
        errorMessage="";
        Student aStudent=null;  //学生对象
        Volunteer aVolunteer=null;  //学生用户名

        String sqlString="SELECT vol_id,vol_name,password,vol_hours,stu_id From Volunteer";
        sqlString=sqlString+" Where vol_name='"+volName+"'";
        ResultSet rs=db.getRsbySQL(sqlString);
        try {
            if(rs.next()) {
                String stuID=String.valueOf(rs.getInt(5));//获得学号
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

    public Volunteer getVolunteerbyStudentID(String stuID) {
        //TODO: Something
        errorMessage="";
        Student aStudent=null;  //学生对象
        Volunteer aVolunteer=null;  //学生用户名

        String sqlString="SELECT vol_id,vol_name,password, vol_hours,stu_id From Volunteer";
        sqlString=sqlString+" Where stu_id='"+stuID+"'";
        ResultSet rs=db.getRsbySQL(sqlString);
        try {
            if(rs.next()) {
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
    public boolean isUserExsit(String volName) {
        //TODO: Something
        if(getVolunteer(volName)!=null )
        {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * 获取用户ID最大号
     * @return
     */
    public int getMaxUserID()
    {
        //TODO: Something
        errorMessage="";
        String sqlString="SELECT MAX(vol_id) AS maxID From Volunteer";
        return db.getMaxID(sqlString);
        //return -1;
    }

    public boolean insertStudent(Student aStudent) {
        //TODO: Something
        errorMessage="";
        String sqlString="INSERT INTO Student(stu_id,stu_name) VALUES('";
        sqlString=sqlString+aStudent.getStuID()+"','";
        sqlString=sqlString+aStudent.getStuName()+"','";
        if (db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }
        else {
            errorMessage="写入学生信息数据不成功";
            return false;
        }
    }



    public boolean insertVolunteer(Volunteer aVol) {
        //TODO: Something
        errorMessage="";
        String sqlString="INSERT INTO Volunteer(vol_id,vol_name,vol_hours,password,stu_id) VALUES(";
        sqlString=sqlString+aVol.getVolID()+",'";
        sqlString=sqlString+aVol.getVolName()+"','";
        sqlString=sqlString+aVol.getVolHours()+"','";
        sqlString=sqlString+aVol.getVolPwd()+"','";
        sqlString=sqlString+aVol.getStuInfo().getStuID()+"')";
        if (db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }
        else {
            errorMessage="写入学生用户信息数据不成功";
            return false;
        }

    }

    //获得类内部发生的错误信息
    public String getErrorMessage() {
        return errorMessage;
    }

}