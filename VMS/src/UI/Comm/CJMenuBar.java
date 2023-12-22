package UI.Comm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import UI.Admin.CheckAllcomerUI;
import UI.Activity.CreateActivityUI;
import UI.Activity.JoinActivityUI;
import UI.Admin.TopAdminCheckUI;
import UI.Volunteer.JoinAdminUI;
import UI.Volunteer.VolDetailsUI;
import UI.mainWin;


//主窗体的菜单条
public class CJMenuBar extends JMenuBar  {

    private static final long serialVersionUID = 1L;

    JMenu menuAdmin=new JMenu("管理员(Q)");;
    JMenu menuVol=new JMenu("志愿者(T)");;
    JMenu menuHelp=new JMenu("帮助(H)");
    JMenuItem menuItemCreate=new JMenuItem("创建志愿活动");
    JMenuItem menuItemJoin=new JMenuItem("加入志愿活动");
    JMenuItem menuItemCheck=new JMenuItem("活动成员审核");
    JMenuItem menuItemDetails=new JMenuItem("个人详情");
    JMenuItem menuItemApply=new JMenuItem("申请成为管理员");
    JMenuItem menuItemCheckAdmin=new JMenuItem("申请审查");
    JMenuItem menuItemH=new JMenuItem("操作帮助");
    JMenuItem menuItemX=new JMenuItem("退出");

    mainWin mainFrame;  //关联的窗体 保证可以实现互动
    public CJMenuBar(mainWin mw) {
        this.mainFrame=mw;
        init();
    }
    private void init()
    {
        //2.加入菜单项，并给出响应事件
        menuAdmin.setMnemonic('C');// 设置快捷键 alt+ C 才会有效果
        menuVol.setMnemonic('T');
        menuHelp.setMnemonic('H');
        // 菜单项加入到菜单
        menuVol.add(menuItemApply);
        menuItemApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrame.getCurVol()!=null){
                    JoinAdminUI aFrm=new JoinAdminUI(mainFrame);
                    mainFrame.desktopPane.add(aFrm);
                    mainFrame.desktopPane.add(aFrm,0);
                }
                else {
                    JOptionPane.showMessageDialog(null,"不是志愿者！","提示",1);

                }
            }
        });
        menuVol.add(menuItemDetails);
        menuItemDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrame.getCurVol()!=null){
                    VolDetailsUI aFrm=new VolDetailsUI(mainFrame);
                    mainFrame.desktopPane.add(aFrm);
                    mainFrame.desktopPane.add(aFrm,0);
                }
                else {
                    JOptionPane.showMessageDialog(null,"不是志愿者！","提示",1);


                }
            }
        });






        menuAdmin.add(menuItemCreate);
        menuItemCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainFrame.getCurAdmin()!=null) {
                    CreateActivityUI aFrm=new CreateActivityUI(mainFrame);
                    mainFrame.desktopPane.add(aFrm,0);

                }
                else
                {
                    JOptionPane.showMessageDialog(null,"不是管理员！","提示",1);
                }
            }
        });
        menuAdmin.add(menuItemCheckAdmin);
        menuItemCheckAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainFrame.getCurTopAdmin()!=null){
                    TopAdminCheckUI aFrm=new TopAdminCheckUI(mainFrame);
                    mainFrame.desktopPane.add(aFrm,0);

                }else{
                    JOptionPane.showMessageDialog(null,"不是最高管理员！","提示",1);

                }
            }
        });


        menuVol.add(menuItemJoin);
        menuItemJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(mainFrame.getCurVol()!=null) {
                    JoinActivityUI aFrm=new JoinActivityUI(mainFrame);
                    mainFrame.desktopPane.add(aFrm);
                    mainFrame.desktopPane.add(aFrm,0);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"不是志愿者！","提示",1);
                }
            }
        });

        menuAdmin.add(menuItemCheck);
        menuItemCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(mainFrame.getCurAdmin()!=null) {
                    CheckAllcomerUI aFrm=new CheckAllcomerUI(mainFrame);
                    mainFrame.desktopPane.add(aFrm,0);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"不是管理员！","提示",1);
                }
            }
        });


        menuHelp.add(menuItemH);
        menuItemH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"加Q2889423387","提示",1);
            }
        });
        menuHelp.addSeparator();
        menuHelp.add(menuItemX);
        menuItemX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //3. 菜单加入到菜单条
        this.add(menuAdmin);
        this.add(menuVol);
        this.add(menuHelp);
    }

}
