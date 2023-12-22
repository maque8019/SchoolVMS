package BLL;



import DAL.AdministratorDAO;
import Entity.Administrator;
import Entity.Student;
import Entity.Volunteer;

public class AdministratorService {
    private String message="";
    AdministratorDAO dao;
    public AdministratorService()
    {
        dao=new AdministratorDAO();
    }

    public Administrator login(String adminID, String adminPwd)
    {
        Administrator curAdmin=dao.getAdminbyStudentID(adminID);
        if(curAdmin==null)
        {
            message="用户名不存在！";
            return null;
        } else if (curAdmin.isLeader()==false) {
            message="管理员审批还未通过";
            return null;

        } else
        {
            if(!curAdmin.getAdminPwd().equals(adminPwd))
            {
                message="密码不正确！";
                return null;
            }
        }

        return curAdmin;
    }
    public boolean register(String stuID,
                            String stuName,
                            String contact,
                            String adminPwd
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
        if(dao.getAdministrator(stuName)!=null)
        {
            message="用户名已存在！注册不成功！";
            return  false;
        }
        //(4)一个学生只能注册一次
        if(dao.getAdminbyStudentID(stuID)!=null)
        {
            message="该学生已注册！注册不成功！";
            return  false;
        }
        Administrator admin=new Administrator(contact,adminPwd,stu1,false);

        //(6)写入数据，注册完成
        if(!dao.insertAdministrator(admin))
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