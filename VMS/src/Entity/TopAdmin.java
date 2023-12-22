package Entity;

public class TopAdmin {
    private String taPwd;
    private Student stuInfo;
    public TopAdmin(){
        setTaPwd("");
        setStuInfo(new Student());

    }
    public TopAdmin(String taPwd,Student student){
        setStuInfo(student);
        setTaPwd(taPwd);
    }
    @Override
    public String toString(){
        String showString=getStuInfo().toString();
        showString=showString+"\n最高管理员密码:"+this.getTaPwd();
        return showString;


    }


    public Student getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(Student stuInfo) {
        this.stuInfo = stuInfo;
    }

    public String getTaPwd() {
        return taPwd;
    }

    public void setTaPwd(String taPwd) {
        this.taPwd = taPwd;
    }
}
