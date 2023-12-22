package Entity;
public class AdminAct {
    private int actID;
    private Administrator adminInfo;

    public AdminAct() {
        setActID(-1);
        setAdminInfo(null);


    }

    public AdminAct(int actID, Administrator administrator) {
        setAdminInfo(administrator);
        setActID(actID);

    }

    @Override
    public String toString() {
        String showString="管理员学号:"+getAdminInfo().getStuInfo().getStuID();
        showString = showString + "\n管理员名称:" + getAdminInfo().getStuInfo().getStuName();
        showString = showString+"\n活动编号:"+this.getActID();
        return showString;


    }

    public int getActID() {
        return actID;
    }

    public void setActID(int actID) {
        this.actID = actID;
    }

    public Administrator getAdminInfo() {
        return adminInfo;
    }

    public void setAdminInfo(Administrator adminInfo) {
        this.adminInfo = adminInfo;
    }
}
