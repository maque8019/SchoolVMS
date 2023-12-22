package DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import Entity.Administrator;
import Entity.Student;
import Entity.Volunteer;


public class AdministratorDAO extends abstractDAO{
    //构造函数
    public AdministratorDAO()
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
    public ArrayList<Administrator> getApplyAdministrator(){
        errorMessage="";
        Student aStudent=null;
        Administrator aAdmin=null;
        String sqlString="SELECT stu_id,stu_name,password,contact,isLeader From Administrator";
        sqlString=sqlString+" Where isLeader= "+0;
        System.out.println(sqlString);
        ArrayList<Administrator> administrators=new ArrayList<Administrator>();
        ResultSet rs= db.getRsbySQL(sqlString);
        try{
            if(rs.next()){
                boolean isLeader;
                if (rs.getInt(5)==1) isLeader=true;	else isLeader=false;
                String stuID=rs.getString(1);
                aStudent=this.getStudentbyID(stuID);
                aAdmin=new Administrator(
                        rs.getString(4),
                        rs.getString(3),
                        aStudent,
                        isLeader

                );
                administrators.add(aAdmin);
            }

        }catch (Exception e){
            errorMessage=e.getMessage();
        }
        return administrators;

    };
    public boolean setAdministrator(String stuID){
        errorMessage="";
        String sqlString="UPDATE Administrator SET isLeader=" +1
                +" WHERE stu_id="+stuID;
        if (db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }
        else {
            errorMessage="管理员审批不成功！";
            return false;
        }


    }
    public boolean deleteAdministrator(String stuID){
        errorMessage="";
        String sqlString="UDELETE FROM Administrator "
                +" WHERE stu_id="+stuID;
        if (db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }
        else {
            errorMessage="管理员审批不成功！";
            return false;
        }


    }
    public Administrator getAdministrator(String adminName){
        errorMessage="";
        Student aStudent=null;
        Administrator aAdmin=null;
        String sqlString="SELECT stu_id,stu_name,password,contact,isLeader From Administrator";
        sqlString=sqlString+" Where stu_name='"+adminName+"'";
        ResultSet rs= db.getRsbySQL(sqlString);
        try{
            if(rs.next()){
                boolean isLeader;
                if (rs.getInt(5)==1) isLeader=true;	else isLeader=false;
                String stuID=rs.getString(1);
                aStudent=this.getStudentbyID(stuID);
                aAdmin=new Administrator(
                        rs.getString(4),
                        rs.getString(3),
                        aStudent,
                        isLeader

                );
            }

        }catch (Exception e){
            errorMessage=e.getMessage();
        }
        return aAdmin;

    };

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
    public Administrator getAdminbyStudentID(String stuID) {
        //TODO: Something
        errorMessage = "";
        Student aStudent = null;
        Administrator aAdmin = null;
        String sqlString = "SELECT stu_id,stu_name,contact,password,isLeader From Administrator";
        sqlString = sqlString + " Where stu_id='" + stuID + "'";
        ResultSet rs = db.getRsbySQL(sqlString);
        try {
            if (rs.next()) {
                boolean isLeader;
                if (rs.getInt(5)==1) isLeader=true;	else isLeader=false;
                aStudent = this.getStudentbyID(stuID);//继续从学生表中获得学生对象
                //构造用户对象
                aAdmin = new Administrator(
                        rs.getString(3),
                        rs.getString(4),
                        aStudent,
                        isLeader

                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            errorMessage = e.getMessage();
        }
        return aAdmin;
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

    public boolean insertAdministrator(Administrator aAdmin){
        errorMessage="";
        String sqlString="INSERT INTO Administrator(stu_id,stu_name,contact,password) VALUES('";
        sqlString=sqlString+aAdmin.getStuInfo().getStuID()+"','";
        sqlString=sqlString+aAdmin.getStuInfo().getStuName()+"','";
        sqlString=sqlString+aAdmin.getContact()+"','";
        sqlString=sqlString+aAdmin.getAdminPwd()+"')";
        if(db.ExcuteSQL(sqlString)==1){
            return true;
        }else {
            errorMessage="写入管理员信息数据不成功";
            return false;
        }

    }



    public boolean insertVolunteer(Volunteer aVol) {
        //TODO: Something
        errorMessage="";
        String sqlString="INSERT INTO Volunteer(vol_id,vol_name,vol_hours,password,stu_id) VALUES(";
        sqlString=sqlString+aVol.getVolID()+"','";
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