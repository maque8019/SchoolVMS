package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.*;

public class ActivityDAO extends abstractDAO {
    //构造函数
    VolunteerDAO volDao=new VolunteerDAO();
    AdministratorDAO adminDao=new AdministratorDAO();
    AdminActDAO adminActDao= new AdminActDAO();
    public ActivityDAO()
    {
        super();  //初始化父类
        errorMessage="";
        volDao=new VolunteerDAO();
        adminDao=new AdministratorDAO();

    }
    public Activity getActivityByID(int  actID)
    {
        errorMessage="";
        Activity aActivity=null;
        Administrator administrator=adminActDao.getAdminbyActivityID(actID);
        String sqlString="SELECT act_id,act_name,act_instruct,act_num,act_hours,stu_id isFinish";
        sqlString=sqlString+" FROM Activity Where act_ID="+actID;
        ResultSet rs=db.getRsbySQL(sqlString);
        try {
            if(rs.next()) {
                aActivity=new Activity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getFloat(5),
                        administrator,
                        false
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            setErrorMessage(e.getMessage());
        }
        return aActivity;
    }

    public ArrayList<Activity> getAllActivity()
    {
        setErrorMessage("");
        String sqlString="SELECT act_id,act_name,act_instruct,act_num,act_hours,stu_id isFinish";
        sqlString=sqlString+" FROM activity WHERE isFinish="+0+" order by act_id";
        ArrayList<Activity> allActivitys=new ArrayList<Activity>();
        ResultSet rs=db.getRsbySQL(sqlString);
        try {
            while(rs.next()) {
                System.out.println(rs.getInt(1));
                int actID=rs.getInt(1);//小组序号
                String actName=rs.getString(2);//组名
                String actInstruct=rs.getString(3);//项目描述
                int size=rs.getInt(4);//小组人数
                Float actHours= rs.getFloat(5);
                Administrator administrator=adminActDao.getAdminbyStudentID(rs.getString(6));
                Activity activity=new Activity(actID,actName,actInstruct,size,actHours,administrator,false);
                allActivitys.add(activity);
            }
        } catch (Exception e) {
            // TODO: handle exception
            setErrorMessage(e.getMessage());
        }
        return allActivitys;
    }
    public ArrayList<AdminAct> getAllAdminAct()
    {
        setErrorMessage("");
        ArrayList<AdminAct>allAdminActs=new ArrayList<AdminAct>();
        String sqlString="SELECT act_id,act_name,act_instruct,act_num,act_hours";
        sqlString=sqlString+" FROM Activity WHERE isFinish= "+0+" order by act_id";
        ResultSet rs=db.getRsbySQL(sqlString);
        try {
            while(rs.next()) {
                int actID=rs.getInt(1);
                Administrator leader=adminActDao.getAdminbyActivityID(actID);
                AdminAct adminAct=new AdminAct(actID,leader);
                allAdminActs.add(adminAct);
            }
        } catch (Exception e) {
            // TODO: handle exception
            setErrorMessage(e.getMessage());
        }
        return allAdminActs;
    }

    public Activity getActivitybyAdminID(String stuID) {
        //TODO: Something
        errorMessage = "";
        Activity aActivity = null;
        Administrator aAdmin = adminActDao.getAdminbyStudentID(stuID);
        AdminActDAO adminActDAO= new AdminActDAO();
        int actID=adminActDAO.getAdminActbyAdminID(stuID).getActID();
        String sqlString = "SELECT act_id,act_name,act_instruct,act_num,act_hours,stu_id,isFinish From Activity";
        sqlString = sqlString + " Where act_id='" + actID + "'";
        ResultSet rs = db.getRsbySQL(sqlString);
        try {
            if (rs.next()) {
                boolean isFinish;
                if (rs.getInt(7)==1) isFinish=true;	else isFinish=false;
                aActivity = new Activity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getFloat(5),
                        aAdmin,
                        isFinish
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            errorMessage = e.getMessage();
        }
        return aActivity;

    }

    public ArrayList<Activity> getActivitysbyAdminID(String stuID){
        errorMessage = "";
        ArrayList<Activity> aActivitys=new ArrayList<Activity>();
        Administrator aAdmin = adminActDao.getAdminbyStudentID(stuID);
        String sqlString = "SELECT act_id,act_name,act_instruct,act_num,act_hours,stu_id,isFinish From Activity";
        sqlString = sqlString + " Where stu_id='" + stuID + "'";
        ResultSet rs = db.getRsbySQL(sqlString);
        try {
            while (rs.next()) {
                boolean isFinish;
                if (rs.getInt(7)==1) isFinish=true;	else isFinish=false;

                Activity aActivity = new Activity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getFloat(5),
                        aAdmin,
                        isFinish
                );
                aActivitys.add(aActivity);
            }
        } catch (Exception e) {
            // TODO: handle exception
            errorMessage = e.getMessage();
        }
        return aActivitys;

    }
    public ArrayList<Allcomers> getAllFinishActivityByVolID(String stuID)  {
        setErrorMessage("");
        ArrayList<Allcomers> allcomers=new ArrayList<Allcomers>();
        String Status;
        Status="接受";

        String sqlString="SELECT Sn,act_id,stu_id,isCancel,checkStatus ";
        sqlString=sqlString+" FROM Allcomer "
                +" Where stu_id="+stuID+" AND checkStatus='"+Status
                + "' Order By act_id,Sn";
        System.out.println(sqlString);
        ResultSet rs=db.getRsbySQL(sqlString);
        //

        try {
            while(rs.next()) {
                int actID=rs.getInt(2);
                Activity theActivity=this.getActivityByID(actID);
                boolean isCancel;//是否取消
                int sn=rs.getInt(1);//序号
                if (rs.getInt(4)==1) isCancel=true;	else isCancel=false;
                String checkStatus=rs.getString(5);//状态
                if (theActivity.isFinish()==true) {
                    Volunteer vol = volDao.getVolunteerbyStudentID(rs.getString(3));
                    Allcomers aAllcomer = new Allcomers(sn, theActivity, vol, isCancel, checkStatus); //成员对象
                    allcomers.add(aAllcomer);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            setErrorMessage(e.getMessage());
        }
        return allcomers;
    }
    public ArrayList<Allcomers> getAllActivityByVolID(String stuID)  {
        setErrorMessage("");
        ArrayList<Allcomers> allcomers=new ArrayList<Allcomers>();
        String Status;
        Status="接受";

        String sqlString="SELECT Sn,act_id,stu_id,isCancel,checkStatus ";
        sqlString=sqlString+" FROM Allcomer "
                +" Where stu_id="+stuID+" AND checkStatus='"+Status
                + "' Order By act_id,Sn";
        System.out.println(sqlString);
        ResultSet rs=db.getRsbySQL(sqlString);
         //

        try {
            while(rs.next()) {
                int actID=rs.getInt(2);
                Activity theActivity=this.getActivityByID(actID);
                boolean isCancel;//是否取消
                int sn=rs.getInt(1);//序号
                if (rs.getInt(4)==1) isCancel=true;	else isCancel=false;
                String checkStatus=rs.getString(5);//状态
                Volunteer vol=volDao.getVolunteerbyStudentID(rs.getString(3));
                Allcomers aAllcomer=new Allcomers(sn,theActivity,vol,isCancel,checkStatus); //成员对象
                allcomers.add(aAllcomer);
            }
        } catch (Exception e) {
            // TODO: handle exception
            setErrorMessage(e.getMessage());
        }
        return allcomers;
    }


    public ArrayList<Allcomers> getAllActivityMembersByActivityID(int actID)
    {
        setErrorMessage("");
        ArrayList<Allcomers> allcomers=new ArrayList<Allcomers>();

        String sqlString="SELECT Sn,act_id,stu_id,isCancel,checkStatus ";
        sqlString=sqlString+" FROM Allcomer "
                +" Where act_id="+actID
                + " Order By act_id,Sn";
        ResultSet rs=db.getRsbySQL(sqlString);
        Activity theActivity=this.getActivityByID(actID); //

        try {
            while(rs.next()) {
                boolean isCancel;//是否取消
                boolean isLeader;
                int sn=rs.getInt(1);//序号
                if (rs.getInt(4)==1) isCancel=true;	else isCancel=false;
                String checkStatus=rs.getString(5);//状态
                Volunteer vol=volDao.getVolunteerbyStudentID(rs.getString(3));
                Allcomers aAllcomer=new Allcomers(sn,theActivity,vol,isCancel,checkStatus); //成员对象
                allcomers.add(aAllcomer);
            }
        } catch (Exception e) {
            // TODO: handle exception
            setErrorMessage(e.getMessage());
        }
        return allcomers;
    }

    public boolean InsertaActivity(Activity aActivity) throws SQLException {
        //TODO: Something
        errorMessage="";
        String []sqlString=new String[3];
        int actID=db.getMaxID("select max(act_id) as maxID FROM Activity")+1;
        sqlString[0]="INSERT INTO Activity(act_ID,act_Name,act_instruct,act_num,act_hours,stu_id) VALUES(";
        sqlString[0]=sqlString[0]+actID+",'";
        sqlString[0]=sqlString[0]+aActivity.getActName()+"','";
        sqlString[0]=sqlString[0]+aActivity.getActInstruct()+"',";
        sqlString[0]=sqlString[0]+aActivity.getActNum()+",";
        sqlString[0]=sqlString[0]+aActivity.getActHours()+",'";
        sqlString[0]=sqlString[0]+aActivity.getAdminInfo().getStuInfo().getStuID()+"')";
        sqlString[1]="INSERT INTO admin_activity(stu_id,stu_name,act_id) VALUES('";
        sqlString[1]=sqlString[1]+aActivity.getAdminInfo().getStuInfo().getStuID()+"','";
        sqlString[1]=sqlString[1]+aActivity.getAdminInfo().getStuInfo().getStuName()+"',";
        sqlString[1]=sqlString[1]+actID+")";
        //由于同时向两个表中插入数据，所有采用了事务
        try {
            if (db.ExecuteTrans(sqlString)==2)
            {
                return true;
            }
            else {
                errorMessage="创建志愿活动不成功！";
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public boolean InsertaAllcomer(Allcomers aAllcomes) {
        //TODO: Something
        errorMessage="";
        String sqlString;

        //获得最大sn
        int sn=db.getMaxID("SELECT MAX(Sn) AS maxSn FROM Allcomer")+1;
        sqlString="INSERT INTO Allcomer(Sn,act_id,stu_id,checkStatus) VALUES(";
        sqlString=sqlString+sn+",";
        sqlString=sqlString+aAllcomes.getActivityInfo().getActID()+",'";
        sqlString=sqlString+aAllcomes.getVolunteerInfo().getStuInfo().getStuID()+"',";
        sqlString=sqlString+"'审核中')";
        //由于同时向两个表中插入数据，所有采用了事务
        if (db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }
        else {
            errorMessage="加入志愿活动不成功！";
            return false;
        }
    }
    public boolean DeleteaAllcomer(Allcomers aAllcomer){
        errorMessage="";
        String sqlString;
        sqlString="DELETE FROM Allcomer WHERE stu_id=";
        sqlString=sqlString+aAllcomer.getVolunteerInfo().getStuInfo().getStuID();
        sqlString=sqlString+" AND act_id=";
        sqlString=sqlString+aAllcomer.getActivityInfo().getActID();
        System.out.println(sqlString);
        if(db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }else {
            errorMessage="退出志愿活动不成功";
            return false;
        }
    }

    public boolean checkAllcomers(int StuID,int actID,String result)
    {
        String sqlString="UPDATE Allcomer SET CheckStatus='"+result+"'" ;
        sqlString=sqlString+" WHERE stu_id="+StuID+" AND act_id='"+actID+"'";
        if(db.ExcuteSQL(sqlString)<1)
        {
            return true;
        }
        else {
            errorMessage="操作不成功！";
            return false;
        }

    }
    public boolean checkAllcomerbySn(int sn,String result)
    {
        String sqlString="UPDATE Allcomer SET checkStatus='"+result+"'" ;
        sqlString=sqlString+" WHERE sn="+sn;
        if(db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }
        else {
            errorMessage="操作不成功！";
            return false;
        }

    }
    public boolean deleteAllcomersbySn(int sn)
    {
        String sqlString="DELETE FROM Allcomer " ;
        sqlString=sqlString+" WHERE sn="+sn;
        if(db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }
        else {
            errorMessage="操作不成功！";
            return false;
        }
    }
    public boolean finishedActivity(int actID){
        errorMessage="";
        String sqlString="UPDATE Activity SET isFinish=" +1
                +" WHERE act_id="+actID;
        if (db.ExcuteSQL(sqlString)==1)
        {
            return true;
        }
        else {
            errorMessage="志愿活动审批不成功！";
            return false;
        }


    }

}
