package Entity;



public class Administrator {
    private String contact;
    private String adminPwd;
    private Student stuInfo;
    private boolean isLeader;

    public Administrator() {
        setAdminPwd("");
        setContact("");
        setStuInfo(null);
        setLeader(false);


    }

    public Administrator(String contact, String adminPwd, Student student,boolean isLeader) {
        setAdminPwd(adminPwd);
        setContact(contact);
        setStuInfo(student);
        setLeader(isLeader);


    }

    @Override
    public String toString() {
        String showString = getStuInfo().toString();
        showString = showString + "\n管理员联系方式:" + this.getContact();
        showString = showString + "\n管理员密码:" + this.getAdminPwd();
        return showString;


    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public Student getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(Student stuInfo) {
        this.stuInfo = stuInfo;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
