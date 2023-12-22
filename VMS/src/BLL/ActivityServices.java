package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.ActivityDAO;
import DAL.AdminActDAO;
import DAL.AdministratorDAO;
import DAL.VolunteerDAO;
import Entity.*;

/**
 * 学习小组的各种操作业务逻辑
 * @author hjh
 *
 */
public class ActivityServices {
    ActivityDAO activityDAO;   //DAL层访问对象，用访问数据库
    VolunteerDAO volunteerDAO;
    AdministratorDAO administratorDAO;
    private String strMessage;
    /**
     * 构造函数
     */
    public ActivityServices() {
        activityDAO=new ActivityDAO();
        administratorDAO=new AdministratorDAO();
        volunteerDAO=new VolunteerDAO();

        setStrMessage("");
    }
    /**
     * 获得所有的小组对象
     * @return
     */
    public ArrayList<Activity> getAllActivity(){
        ArrayList<Activity> allActivity=activityDAO.getAllActivity();
        return allActivity;
    }
    /**
     * 获得小组表的表头
     * @return 一个表头数组
     */
    public String[] getTableHead() {
        //表头，标题
        String []tableHead=new String[] {"志愿序号", "志愿名称", "志愿简介", "人数","志愿时长" ,"组长"};
        return tableHead;
    }
    /**
     * 获得小组的表中内容
     * @return 一个二维字符数组
     */
    public String[][] getTableContent(){
        ArrayList<Activity> allActivitys=activityDAO.getAllActivity();
        int n=allActivitys.size();
        String [][]content=new String[n][6];
        int i=0;
        for(Activity aActivity:allActivitys) {
            content[i][0]=String.valueOf(aActivity.getActID());
            content[i][1]=aActivity.getActName();
            content[i][2]=aActivity.getActInstruct();
            content[i][3]=String.valueOf(aActivity.getActNum());
            content[i][4]=String.valueOf(aActivity.getActHours());
            content[i][5]=aActivity.getAdminInfo().getStuInfo().getStuName();
            i++;
        }
        return content;
    }

    /**
     * 通过小组编号获得小组对象
     * @param id
     * @return
     */
    public Activity getActivityById(int id)
    {
        Activity aActivity=activityDAO.getActivityByID(id);
        return aActivity;
    }
    public String [] getAdminActTableHead(){
        String []tableHead=new String[] {"志愿序号", "志愿名称", "人数","是否结束"};
        return tableHead;

    }
    public String[] getAdminApplyTableHead(){
        String []tableHead=new String[] {"学号", "姓名", "联系方式"};
        return tableHead;

    }
    public String[][] getApplyAdminTableContent(){
        ArrayList<Administrator> administrators=administratorDAO.getApplyAdministrator();
        int n=administrators.size();
        String [][]content=new String[n][3];
        int i=0;
        for(Administrator administrator:administrators) {
            content[i][0]=administrator.getStuInfo().getStuID();
            content[i][1]=administrator.getStuInfo().getStuName();
            content[i][2]=administrator.getContact();
            i++;
        }
        return content;
    }
    public String[] getVolActTableHead(){
        String []tableHead=new String[] {"序号", "志愿名称", "组长","联系方式","活动状态"};
        return tableHead;
    }
    public float getVolAllTime(String stuID){
        ArrayList<Allcomers> allcomerActivity=activityDAO.getAllFinishActivityByVolID(stuID);
        int n=allcomerActivity.size();
        float time=0;
        for(Allcomers allcomers:allcomerActivity) {
            time=time+allcomers.getActivityInfo().getActHours();
        }
        return time;



    }
    public String[][] getVolActTableContent(String stuID)  {
        ArrayList<Allcomers> allcomerActivity=activityDAO.getAllActivityByVolID(stuID);
        int n=allcomerActivity.size();
        String [][]content=new String[n][5];
        String isFinish;
        int i=0;
        for(Allcomers allcomers:allcomerActivity) {
            AdminActDAO adminActDAO=new AdminActDAO();
            Administrator administrator=adminActDAO.getAdminbyActivityID(allcomers.getActivityInfo().getActID());
            content[i][0]=String.valueOf(allcomers.getActivityInfo().getActID());
            content[i][1]=allcomers.getActivityInfo().getActName();
            content[i][2]=String.valueOf(administrator.getStuInfo().getStuName());
            content[i][3]=String.valueOf(administrator.getContact());
            if (allcomers.getActivityInfo().isFinish()==true){
                isFinish="已结束";
            }else {
                isFinish="进行中";
            }
            content[i][4]=isFinish;
            i++;
        }
        return content;

    }
    public String[][] getAdminActTableContent(String stuID){
        ArrayList<Activity> allActivitys=activityDAO.getActivitysbyAdminID(stuID);
        int n=allActivitys.size();
        System.out.println(n);
        String [][]content=new String[n][4];
        String isFinish;
        int i=0;
        for(Activity aActivity:allActivitys) {
            content[i][0]=String.valueOf(aActivity.getActID());
            content[i][1]=aActivity.getActName();
            content[i][2]=String.valueOf(aActivity.getActNum());
            if (aActivity.isFinish()==true){
                isFinish="已结束";
            }else {
                isFinish="进行中";
            }
            content[i][3]=isFinish;
            i++;
        }
        return content;

    }
    public String[] getActEndTableHead(){
        String []tableHead=new String[] {"序号", "志愿名称", "状态"};
        return tableHead;

    }
    public String[][] getActEndTableContent(){
        ArrayList<Activity> allActivitys=activityDAO.getAllActivity();
        int n=allActivitys.size();
        String [][]content=new String[n][3];
        int i=0;
        String isFinish;
        for(Activity aActivity:allActivitys) {
            content[i][0]=String.valueOf(aActivity.getActID());
            content[i][1]=aActivity.getActName();
            if (aActivity.isFinish()==true){
                isFinish="已结束";
            }else {
                isFinish="进行中";
            }
            content[i][2]=isFinish;
            i++;
        }
        return content;
    }
    public String[] getAllcomersTableHead() {
        //表头，标题
        String []tableHead=new String[] {"学号", "姓名", "状态"};
        return tableHead;
    }
    public String[][] getAllcomersTableContent(int actID){
        ArrayList<Allcomers> allcomers=activityDAO.getAllActivityMembersByActivityID(actID);
        int n=allcomers.size();
        String [][]content=new String[n][3];
        int i=0;
        for(Allcomers allcomer:allcomers) {
            content[i][0]=String.valueOf(allcomer.getVolunteerInfo().getStuInfo().getStuID());
            content[i][1]=allcomer.getVolunteerInfo().getStuInfo().getStuName();
            content[i][2]=allcomer.getCheckStatus();
            i++;
        }
        return content;
    }

    public boolean CreateActivity(Activity aNewActivity) throws SQLException {
        //应该有一个业务逻辑核查工作
        //例如，如果一个人已创建一个小组，则不可以再创建，即一个人只能创建一个小组
        //

        if(activityDAO.InsertaActivity(aNewActivity)) {
            return true;
        }
        else
        {
            strMessage=activityDAO.getErrorMessage();
            return false;
        }
    }


    public boolean CreateAllcomers(Allcomers aNewAllcomers)
    {
        //这里也有很多逻辑处理内容，例如一个人只能参加一个小组，等等
        if(activityDAO.InsertaAllcomer(aNewAllcomers)) {
            return true;
        }
        else
        {
            strMessage=activityDAO.getErrorMessage();
            return false;
        }
    }
    public boolean DeleteAllcomers(Allcomers aNewAllcomers) {
        if (activityDAO.DeleteaAllcomer(aNewAllcomers)) {
            return true;
        } else {
            strMessage = activityDAO.getErrorMessage();
            return false;
        }
    }


    public ArrayList <Allcomers> getAllcomersByActID(int actID)
    {
        return activityDAO.getAllActivityMembersByActivityID(actID);
    }

    public Activity getAcrivrityByLeader(String StuID) {
        return activityDAO.getActivitybyAdminID(StuID);
    }
    /**
     * 通过学号获得该学生所创建的小组中的成员
     * @param stuID  学号
     * @return  小组成员列表
     */
    public ArrayList <Allcomers> getAllcomersByStuID(String stuID)
    {
        Activity activity=getAcrivrityByLeader(stuID);  //所获得的学习小组
        if(activity!=null) {
            return getAllcomersByActID((activity.getActID()));
        }
        else
        {
            return null;
        }
    }

    public boolean checkAllcomers(Student leader,Allcomers allcomers,String Status)
    {
        //审核小组成员，接受或拒绝
        if(Status.equals("接受") || Status.equals("拒绝"))
        {
            System.out.println(Status);
            //return teamDao.checkTeamer(aTeamer.getStudentInfo().getStuID(), aTeamer.getTeamInfo().getTeamID(),Status);
            return activityDAO.checkAllcomerbySn(allcomers.getSn(),Status);
        }
        else
        {
            strMessage="只能是接受或拒绝！";
            return false;
        }
    }

    public boolean refuseAllcomers(Student leader,Allcomers allcomers)
    {
        //审核小组成员，接受或拒绝
        if(!allcomers.getActivityInfo().getAdminInfo().getStuInfo().getStuID().equals(leader.getStuID()))
        {
            //不是小组组长，无权审核！
            strMessage="不是小组组长，无权审核！";
            return false;
        }
        //return teamDao.checkTeamer(aTeamer.getStudentInfo().getStuID(), aTeamer.getTeamInfo().getTeamID(),Status);
        return activityDAO.deleteAllcomersbySn(allcomers.getSn());
    }
    /**
     * 获得类内消息
     * @return
     */
    public String getStrMessage() {
        return strMessage;
    }

    public void setStrMessage(String strMessage) {
        this.strMessage = strMessage;
    }
}