package Entity;


public class Student {
    private String stuID;
    private String stuName;

    public Student() {
        setStuID("");
        setStuName("");
    }

    public Student(String stuID,String stuName)
    {
        this.setStuID(stuID);
        this.setStuName(stuName);
    }

    @Override
    public String toString()
    {
        String studengInfo;
        studengInfo="学号:"+this.stuID;
        studengInfo=studengInfo+"\n姓名:"+this.stuName;
        return studengInfo;
    }
    public String getStuID() {
        return stuID;
    }
    public void setStuID(String stuID) {
        this.stuID = stuID;
    }
    public String getStuName() {
        return stuName;
    }
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

}