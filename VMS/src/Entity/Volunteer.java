package Entity;

public class Volunteer{
    private int volID;
    private String volName;
    private String volPwd;
    private Student stuInfo;
    private float volHours;
    public Volunteer(){
        setVolID(-1);
        setVolName("");
        setVolPwd("");
        setVolHours(0.F);
        setStuInfo(new Student());

    }public Volunteer(int volID,String volName,String volPwd,float volHours,Student student){
        setVolID(volID);
        setVolName(volName);
        setVolPwd(volPwd);
        setVolHours(volHours);
        setStuInfo(student);

    }
    @Override
    public String toString(){
        String showString=getStuInfo().toString();
        showString=showString+"\n志愿ID:"+this.getVolID();
        showString=showString+"\n志愿者名称:"+this.getVolName();
        showString=showString+"\n志愿者密码:"+this.getVolPwd();
        showString=showString+"\n志愿时长:"+this.getVolHours();
        return showString;


    }

    public Student getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(Student stuInfo) {
        this.stuInfo = stuInfo;
    }

    public float getVolHours() {
        return volHours;
    }

    public void setVolHours(Float volHours) {
        this.volHours = volHours;
    }

    public int getVolID() {
        return volID;
    }

    public void setVolID(int volID) {
        this.volID = volID;
    }

    public String getVolName() {
        return volName;
    }

    public void setVolName(String volName) {
        this.volName = volName;
    }

    public String getVolPwd() {
        return volPwd;
    }

    public void setVolPwd(String volPwd) {
        this.volPwd = volPwd;
    }
}