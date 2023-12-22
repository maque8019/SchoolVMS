package UI;

import Entity.Administrator;
import Entity.TopAdmin;
import Entity.Volunteer;
import UI.Comm.CJMenuBar;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import java.awt.Container;



public class mainWin extends JFrame {

    private static final long serialVersionUID = 1L;
    private CJMenuBar menuBar = new CJMenuBar(this);

    Container contentPane=null;
    public JDesktopPane desktopPane;
    private Volunteer curVol=null;
    private Administrator curAdmin=null;
    private TopAdmin curTopAdmin=null;

    public mainWin(Volunteer curVol) {
        setCurVol(curVol);
        this.setTitle("志愿者管理系统--"+curVol.getVolName()+"同学！");
        initWin();
    }

    public mainWin(Administrator curAdmin) {
        setCurAdmin(curAdmin);
        this.setTitle("志愿者管理系统--"+curAdmin.getStuInfo().getStuName()+"同学！");
        initWin();
    }
    public mainWin(TopAdmin curTopAdmin) {
        setCurTopAdmin(curTopAdmin);
        this.setTitle("志愿者管理系统--"+curTopAdmin.getStuInfo().getStuName()+"同学！");
        initWin();
    }
    /**
     * 初始化主界面
     */
    private void initWin()
    {

        this.setJMenuBar(menuBar);
        contentPane=this.getContentPane();
        // 创建桌面面板
        desktopPane = new JDesktopPane();
        // 将桌面面板添加到主窗口的内容面板中
        getContentPane().add(desktopPane);
        this.setSize(1024,768);         //设置窗体大小
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //最大化
        //设置当关闭窗口时，保证JVM也退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //显示窗体
        this.setVisible(true);
        this.setResizable(true);
    }

    public Administrator getCurAdmin() {
        return curAdmin;
    }

    public TopAdmin getCurTopAdmin() {
        return curTopAdmin;
    }

    public void setCurAdmin(Administrator curAdmin) {
        this.curAdmin = curAdmin;
    }

    public void setCurTopAdmin(TopAdmin curTopAdmin) {
        this.curTopAdmin = curTopAdmin;
    }

    public Volunteer getCurVol() {
        return curVol;
    }

    public void setCurVol(Volunteer curVol) {
        this.curVol = curVol;
    }

}