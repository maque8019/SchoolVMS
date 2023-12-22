package BLL;

import DAL.TopAdminDAO;
import Entity.Student;
import Entity.TopAdmin;

public class TopAdminService {
    private String message="";
    TopAdminDAO dao;
    public TopAdminService()
    {
        dao=new TopAdminDAO();
    }

    public TopAdmin login(String topAdminID, String topAdminPwd)
    {
        TopAdmin curTopAdmin=dao.getTopAdminbyStudentID(topAdminID);
        if(curTopAdmin==null)
        {
            message="用户名不存在！";
            return null;
        }
        else
        {
            if(!curTopAdmin.getTaPwd().equals(topAdminPwd))
            {
                message="密码不正确！";
                return null;
            }
        }

        return curTopAdmin;
    }
    public boolean register(String stuID,
                            String stuName,
                            String topAdminPwd
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
        if(dao.getTopAdmin(stuName)!=null)
        {
            message="用户名已存在！注册不成功！";
            return  false;
        }
        //(4)一个学生只能注册一次
        if(dao.getTopAdminbyStudentID(stuID)!=null)
        {
            message="该学生已注册！注册不成功！";
            return  false;
        }
        TopAdmin topAdmin=new TopAdmin(topAdminPwd,stu1);

        //(6)写入数据，注册完成
        if(!dao.insertTopAdmin(topAdmin))
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