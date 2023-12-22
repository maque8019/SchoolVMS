package Entity;

import java.text.SimpleDateFormat;

public class Allcomers {
    private int sn;
    private Activity activityInfo;
    private  Volunteer volunteerInfo;
    private boolean isCancel;
    private String checkStatus;
    /**
     * 小组成员的构造函数
     */
    public Allcomers()
    {
        setSn(-1);
        setActivityInfo(null);
        setVolunteerInfo(null);
        setCancel(false);
        setCheckStatus("审核中");
    }
    /**
     * 小组成员的有参构造函数
     */
    public Allcomers(int sn,
                  Activity activityInfo,
                  Volunteer volunteerInfo,
                  boolean isCancel,
                  String checkStatus) {

        this.setSn(sn);
        this.setActivityInfo(activityInfo);
        this.setVolunteerInfo(volunteerInfo);
        this.setCancel(isCancel);
        this.setCheckStatus(checkStatus);
    }


    @Override
    public String toString()
    {
        String AllcomerInfo;
        if(this.volunteerInfo ==null)
        {
            return "none";
        }
        AllcomerInfo="["+volunteerInfo.getStuInfo().getStuID()+
                "]"+volunteerInfo.getStuInfo().getStuName()+"--"+this.checkStatus+"(sn="+sn+")";
        return AllcomerInfo;
    }
    public int getSn() {
        return sn;
    }
    public void setSn(int sn) {
        this.sn = sn;
    }

    public Activity getActivityInfo() {
        return activityInfo;
    }


    public void setActivityInfo(Activity activityInfo) {
        this.activityInfo = activityInfo;
    }



    public void setVolunteerInfo(Volunteer volunteerInfo) {
        this.volunteerInfo = volunteerInfo;
    }

    public Volunteer getVolunteerInfo() {
        return volunteerInfo;
    }



    public boolean isCancel() {
        return isCancel;
    }
    public void setCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }
    public String getCheckStatus() {
        return checkStatus;
    }
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }


}