package UI.Admin;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import javax.swing.*;

import BLL.ActivityServices;
import DAL.ActivityDAO;
import DAL.AdminActDAO;
import DAL.AdministratorDAO;
import Entity.Activity;
import Entity.Administrator;
import Entity.Allcomers;
import UI.mainWin;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;

public class TopAdminCheckUI extends JInternalFrame {
    mainWin fatherWin;
    private JTextField txtActivityName;
    private JTextField txtAdminName;
    private JButton btnAccept;
    private JButton btnReject;
    DefaultTableModel allActivityModel;
    DefaultTableModel applyAdminModel;
    ActivityServices activityServices;

    AdminActDAO adminActDAO=new AdminActDAO();
    ActivityDAO activityDAO=new ActivityDAO();
    AdministratorDAO administratorDAO=new AdministratorDAO();
    private JTable ActEnd;
    private JTextField txtContact;
    private JTable AdminApply;

    public TopAdminCheckUI(mainWin f) {
        fatherWin=f;
        activityServices=new ActivityServices();
        this.setTitle("审核小组成员");
        this.setResizable(true);
        this.setClosable(true);  //设置是否提供关闭按钮
        this.setMaximizable(true); //设置是否提供“最大化”按钮
        this.setIconifiable(true); //设置是否提供“最小化”按钮

        try {
            this.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }

        setBounds(5, 5, 906, 648);
        JFrame frame = new JFrame("Close ContentPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        frame.setContentPane(contentPane);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE );

        this.setVisible(true);
        this.setResizable(true);
        getContentPane().setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setFont(new Font("宋体", Font.BOLD, 14));
        panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u5BA1\u6279", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(33, 29, 840, 357);
        getContentPane().add(panel_1);


        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u5FD7\u613F\u6D3B\u52A8\u60C5\u51B5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(33, 409, 840, 174);

        getContentPane().add(panel);
        panel.setLayout(null);

        btnAccept = new JButton("审核通过");
        btnAccept.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                acceptActivity();
            }
        });
        btnAccept.setBounds(459, 103, 113, 37);
        panel.add(btnAccept);

        btnReject = new JButton("\u62D2\u7EDD\u901A\u8FC7");
        btnReject.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        btnReject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                refuseFinish();
            }
        });
        btnReject.setBounds(149, 102, 113, 38);
        panel.add(btnReject);

        JLabel actName = new JLabel("\u6D3B\u52A8\u540D\u79F0");
        actName.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        actName.setBounds(28, 53, 74, 21);
        panel.add(actName);

        JLabel lblNewLabel = new JLabel("\u7BA1\u7406\u5458");
        lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblNewLabel.setBounds(335, 54, 58, 19);
        panel.add(lblNewLabel);

        txtActivityName = new JTextField();
        txtActivityName.setBounds(112, 52, 190, 24);
        panel.add(txtActivityName);
        txtActivityName.setColumns(10);

        txtAdminName = new JTextField();
        txtAdminName.setBounds(403, 52, 119, 24);
        panel.add(txtAdminName);
        txtAdminName.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u8054\u7CFB\u65B9\u5F0F");
        lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(547, 53, 74, 21);
        panel.add(lblNewLabel_1);

        txtContact = new JTextField();
        txtContact.setBounds(644, 51, 153, 25);
        panel.add(txtContact);
        txtContact.setColumns(10);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u5B8C\u7ED3\u5FD7\u613F\u6D3B\u52A8\u5BA1\u6279", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_3.setBounds(43, 32, 392, 300);
        panel_1.add(panel_3);
        panel_3.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(52, 42, 273, 214);
        panel_3.add(scrollPane_1);

        ActEnd = new JTable();
        ActEnd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ActEnd.setRowHeight(24);
        ActEnd.setFont(new Font("宋体", Font.PLAIN, 12));
        ActEnd.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        ActEnd.setBounds(0, 0, 1, 1);
        scrollPane_1.setViewportView(ActEnd);

        JPanel panel_3_1 = new JPanel();
        panel_3_1.setLayout(null);
        panel_3_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u7BA1\u7406\u5458\u7533\u8BF7\u5BA1\u6279", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_3_1.setBounds(445, 32, 392, 300);
        panel_1.add(panel_3_1);

        JScrollPane scrollPane_1_1 = new JScrollPane();
        scrollPane_1_1.setBounds(53, 35, 273, 165);
        panel_3_1.add(scrollPane_1_1);

        AdminApply = new JTable();
        scrollPane_1_1.setViewportView(AdminApply);

        JButton btnAcceptAdmin = new JButton("\u901A\u8FC7");
        btnAcceptAdmin.setFont(new Font("宋体", Font.PLAIN, 14));
        btnAcceptAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acceptAdmin();
            }
        });
        btnAcceptAdmin.setBounds(229, 230, 97, 34);
        panel_3_1.add(btnAcceptAdmin);

        JButton btnRejectAdmin = new JButton("\u62D2\u7EDD");
        btnRejectAdmin.setFont(new Font("宋体", Font.PLAIN, 14));
        btnRejectAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                rejectAdmin();
            }
        });
        btnRejectAdmin.setBounds(59, 230, 97, 34);
        panel_3_1.add(btnRejectAdmin);
        showFinishedActivity();
        ActEnd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SelectaActivitytoShow();

            }
        });




    }

    private void showFinishedActivity() {
        String []tableHead=activityServices.getActEndTableHead();
        String [][]tableContent=activityServices.getActEndTableContent();
        allActivityModel=new DefaultTableModel(tableContent,tableHead);
        this.ActEnd.setModel(allActivityModel);  //加载模型数据
        this.ActEnd.validate();
        return;
    }
    private void SelectaActivitytoShow() {

        int rowIndex=this.ActEnd.getSelectedRows()[0];  //获得当前的行号
        int actID=Integer.parseInt(this.ActEnd.getModel().getValueAt(rowIndex, 0).toString());
        Activity activity=activityServices.getActivityById(actID);
        Administrator administrator= adminActDAO.getAdminbyActivityID(actID);
        if (activity!=null) {
            this.txtActivityName.setText(activity.getActName());
            this.txtAdminName.setText(administrator.getStuInfo().getStuName());
            this.txtContact.setText(administrator.getContact());
        }

    }
    private void showApplyAdmin() {
        String []tableHead=activityServices.getAdminApplyTableHead();
        String [][]tableContent=activityServices.getApplyAdminTableContent();
        applyAdminModel=new DefaultTableModel(tableContent,tableHead);
        this.AdminApply.setModel(applyAdminModel);
        this.AdminApply.validate();
        return;
    }


    private void acceptAdmin() {
        int rowIndex=this.AdminApply.getSelectedRows()[0];
        String stuID=this.AdminApply.getModel().getValueAt(rowIndex, 0).toString();
        if(administratorDAO.setAdministrator(stuID)){
            JOptionPane.showMessageDialog(null,"已完成审核！");
        }else
        {
            JOptionPane.showMessageDialog(null,"操作有问题！");
        }
        return;
    }
    private void rejectAdmin() {
        int rowIndex=this.AdminApply.getSelectedRows()[0];
        String stuID=this.AdminApply.getModel().getValueAt(rowIndex, 0).toString();
        if(administratorDAO.deleteAdministrator(stuID)){
            JOptionPane.showMessageDialog(null,"已完成审核！");
        }else
        {
            JOptionPane.showMessageDialog(null,"操作有问题！");
        }
        return;
    }
    private void acceptActivity() {
        int rowIndex=this.ActEnd.getSelectedRows()[0];
        int actID= Integer.parseInt(this.ActEnd.getModel().getValueAt(rowIndex, 0).toString());
        if(activityDAO.finishedActivity(actID)){
            JOptionPane.showMessageDialog(null,"已完成审核！");
        }else
        {
            JOptionPane.showMessageDialog(null,"操作有问题！");
        }
        return;
    }
    private void refuseFinish(){
        JOptionPane.showMessageDialog(null,"拒绝通过");
        return;

    }



}





