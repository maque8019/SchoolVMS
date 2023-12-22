package BLL;



import DAL.VolunteerDAO;
import Entity.Student;
import Entity.Volunteer;

public class VolunteerService {
    private String message="";
    VolunteerDAO dao;
    public VolunteerService()
    {
        dao=new VolunteerDAO();
    }

    public Volunteer login(String stuID,String volPwd)
    {
        //从数据库中获得学生用户对象
        Volunteer curVolunteer=dao.getVolunteerbyStudentID(stuID);
        if(curVolunteer==null)
        {
            message="用户名不存在！";
            return null;
        }
        else
        {
            if(!curVolunteer.getVolPwd().equals(volPwd))
            {
                message="密码不正确！";
                return null;
            }
        }

        return curVolunteer;
    }
    public boolean register(String stuID,
                            String stuName,
                            String volName,
                            String volPwd
    )
    {
        //(2)学号和姓名一致检查
        Student stu1=dao.getStudentbyID(stuID);
        if(stu1==null)
        {
            message="学生库中没有该学生！注册不成功！";
            return false;
        }
        //(3)用户名不能重名
        if(dao.getVolunteer(volName)!=null)
        {
            message="用户名已存在！注册不成功！";
            return  false;
        }
        //(4)一个学生只能注册一次
        if(dao.getVolunteerbyStudentID(stuID)!=null)
        {
            message="该学生已注册！注册不成功！";
            return  false;
        }
        int volID=dao.getMaxUserID()+1;
        float volHours=0;
        Volunteer vol=new Volunteer(volID,volName,volPwd,volHours,stu1);

        //(6)写入数据，注册完成
        if(!dao.insertVolunteer(vol))
        {
            message="在写入数据时出现问题，注册失败！";
            return  false;
        }
        else
        {
            return true;
        }
    }

    public String getMessage() {
        return message;
    }



}