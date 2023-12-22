package test;

import BLL.ActivityServices;
import DAL.ActivityDAO;
import DAL.AdminActDAO;
import Entity.Activity;
import Entity.AdminAct;
import Entity.Administrator;
import dbUtils.IAccessDB;
import dbUtils.JavaMySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class testUI {

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub
        IAccessDB db=  new JavaMySQL();
        AdminActDAO adminActDao=new AdminActDAO();
        String sqlString="SELECT act_id,act_name,act_instruct,act_num,act_hours,stu_id";
        sqlString=sqlString+" FROM Activity order by act_id";
        ArrayList<Activity> allActivitys=new ArrayList<Activity>();
        ResultSet rs=db.getRsbySQL(sqlString);
        try {
            while(rs.next()) {
                System.out.println(rs.getString(2));
                    int actID=rs.getInt(1);
                    String actName=rs.getString(2);
                    String actInstruct=rs.getString(3);
                    int size=rs.getInt(4);
                    Float actHours= rs.getFloat(5);
                    Administrator administrator=adminActDao.getAdminbyStudentID(rs.getString(6));
                    Activity activity=new Activity(actID,actName,actInstruct,size,actHours,administrator,false);
                    System.out.println(activity);
                    allActivitys.add(activity);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        String [][]content=new String[2][6];
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
        System.out.println(content[0][1]);


        }



    }

