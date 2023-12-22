package Entity;

public class Activity {
    private int actID;
    private String actName;
    private String actInstruct;
    private int actNum;
    private float actHours;
    private int stu_id;
    private Student stuInfo;
    private Administrator adminInfo;
    private boolean isFinish;

    public Activity() {
        setActHours(0);
        setActID(-1);
        setActInstruct("");
        setActNum(0);
        setActName("");
        setAdminInfo(null);
        setFinish(false);


    }

    public Activity(int actID, String actName, String actInstruct, int actNum,float actHours,Administrator adminInfo,boolean isFinish) {
        setActHours(actHours);
        setActID(actID);
        setActInstruct(actInstruct);
        setActNum(actNum);
        setActName(actName);
        setAdminInfo(adminInfo);
        setFinish(isFinish);


    }

    @Override
    public String toString() {
//        String showString = "\n活动ID:" + this.getActID();
//        showString = showString + "\n志愿活动名称:" + this.getActName();
//        showString = showString + "\n志愿活动介绍:" + this.getActInstruct();
//        showString = showString + "\n志愿活动时长:" + this.getActHours();
//        showString = showString + "\n志愿活动人数:" + this.getActNum();
//        showString = showString + "\n志愿活动人数:" + this.getActNum();
        return "["+this.getActID()+"]"+this.actName+"("+this.getAdminInfo().getStuInfo().getStuName()+")";


    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public void setStuInfo(Student stuInfo) {
        this.stuInfo = stuInfo;
    }

    public Student getStuInfo() {
        return stuInfo;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }


    public float getActHours() {
        return actHours;
    }

    public void setActHours(float actHours) {
        this.actHours = actHours;
    }

    public int getActID() {
        return actID;
    }

    public void setActID(int actID) {
        this.actID = actID;
    }

    public int getActNum() {
        return actNum;
    }

    public void setActNum(int actNum) {
        this.actNum = actNum;
    }

    public String getActInstruct() {
        return actInstruct;
    }

    public void setActInstruct(String actInstruct) {
        this.actInstruct = actInstruct;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public void setAdminInfo(Administrator adminInfo) {
        this.adminInfo = adminInfo;
    }

    public Administrator getAdminInfo() {
        return adminInfo;
    }
}

