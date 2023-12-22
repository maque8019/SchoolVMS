package DAL;

import java.sql.ResultSet;

import Entity.*;


public class AdminActDAO extends abstractDAO{
    //构造函数
    public AdminActDAO()
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

    public Administrator getAdministrator(String adminName){
        errorMessage="";
        Student aStudent=null;
        Administrator aAdmin=null;
        String sqlString="SELECT stu_id,stu_name,password,contact isLeader From Administrator";
        sqlString=sqlString+"Where stu_name='"+adminName+"'";
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

    public AdminAct getAdminActbyAdminID(String stuID) {
        //TODO: Something
        errorMessage = "";
        AdminAct aAdminAct = null;
        Administrator aAdmin = null;
        String sqlString = "SELECT stu_id,stu_name,act_id From Admin_activity";
        sqlString = sqlString + " Where stu_id='" + stuID + "'";
        ResultSet rs = db.getRsbySQL(sqlString);
        try {
            if (rs.next()) {
                aAdmin = this.getAdminbyStudentID(stuID);//继续从学生表中获得学生对象
                //构造用户对象
                aAdminAct = new AdminAct(
                        rs.getInt(3),
                        aAdmin
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            errorMessage = e.getMessage();
        }
        return aAdminAct;

    }
    public AdminAct getAdminActbyActivityID(int actID) {
        //TODO: Something
        errorMessage = "";
        AdminAct aAdminAct = null;
        Administrator aAdmin = null;
        String sqlString = "SELECT stu_id,stu_name,act_id From Admin_activity";
        sqlString = sqlString + " Where act_id='" + actID + "'";
        ResultSet rs = db.getRsbySQL(sqlString);
        try {
            if (rs.next()) {
                String stuID=rs.getString(1);
                aAdmin = this.getAdminbyStudentID(stuID);//继续从学生表中获得学生对象
                //构造用户对象
                aAdminAct = new AdminAct(
                        rs.getInt(3),
                        aAdmin
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            errorMessage = e.getMessage();
        }
        return aAdminAct;

    }
    public Administrator getAdminbyActivityID(int actID) {
        //TODO: Something
        errorMessage = "";
        AdminAct adminAct = null;
        Administrator aAdmin = null;
        Activity activity=null;
        Student aStudent=null;
        String stuID=this.getAdminActbyActivityID(actID).getAdminInfo().getStuInfo().getStuID();
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
    public Activity getActivitybyAdminID(String stuID) {
        //TODO: Something
        errorMessage = "";
        Activity aActivity = null;
        Administrator aAdmin =this.getAdminbyStudentID(stuID);
        int actID=this.getAdminActbyAdminID(stuID).getActID();
        String sqlString = "SELECT act_id,act_name,act_instruct,act_num,act_hours,stu_id From Activity";
        sqlString = sqlString + " Where act_id='" + actID + "'"+" AND isFinish="+0;

        ResultSet rs = db.getRsbySQL(sqlString);
        try {
            if (rs.next()) {
                aActivity = new Activity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getFloat(5),
                        aAdmin,
                        false
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            errorMessage = e.getMessage();
        }
        return aActivity;

    }

    public boolean isUserExsit(String stuID) {
        //TODO: Something
        if(getActivitybyAdminID(stuID)!=null )
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
        String sqlString="SELECT MAX(act_id) AS maxID From Activity";
        return db.getMaxID(sqlString);
        //return -1;
    }

    public boolean insertActivity(Activity activity) {
        //TODO: Something
        errorMessage="";
        String sqlString="INSERT INTO Activity(act_id,act_name,act_instruct,act_num,act_hours) VALUES('";
        sqlString=sqlString+activity.getActID()+"','";
        sqlString=sqlString+activity.getActName()+"','";
        sqlString=sqlString+activity.getActInstruct()+"','";
        sqlString=sqlString+activity.getActNum()+"','";
        sqlString=sqlString+activity.getActHours()+"','";
        if (db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }
        else {
            errorMessage="写入志愿活动数据不成功";
            return false;
        }
    }



    public boolean insertAdminAct(AdminAct adminAct) {
        //TODO: Something
        errorMessage="";
        String sqlString="INSERT INTO Admin_activity(stu_id,stu_name,act_id) VALUES(";
        sqlString=sqlString+adminAct.getAdminInfo().getStuInfo().getStuID()+",'";
        sqlString=sqlString+adminAct.getAdminInfo().getStuInfo().getStuName()+"','";
        sqlString=sqlString+adminAct.getActID()+"','";
        if (db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }
        else {
            errorMessage="写入管理员志愿联系信息数据不成功";
            return false;
        }

    }

    //获得类内部发生的错误信息
    public String getErrorMessage() {
        return errorMessage;
    }

}
