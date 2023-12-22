package UI.Admin;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.*;
import BLL.ActivityServices;
import Entity.Activity;
import Entity.Allcomers;
import UI.mainWin;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckAllcomerUI extends JInternalFrame {
    mainWin fatherWin;  //设置父窗口
    private JTextField txtStuID;
    private JTextField txtStudentName;
    private JButton btnAccept;
    private JButton btnReject;
    JList <Allcomers> Allcomerslist;
    DefaultListModel <Allcomers> listModel;
    DefaultTableModel allActivityModel;
    ActivityServices activityServices;
    Allcomers allcomers=null;
    Activity activity=null;
    private JTable allActTable;
    public CheckAllcomerUI(mainWin f) {
        fatherWin=f;
        activityServices=new ActivityServices();
        this.setTitle("审核小组成员");
        this.setResizable(true); //设置是否允许自由调整大小
        this.setClosable(true);  //设置是否提供关闭按钮
        this.setMaximizable(true); //设置是否提供“最大化”按钮
        this.setIconifiable(true); //设置是否提供“最小化”按钮

        try {
            this.setSelected(true);
        } catch (PropertyVetoException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        setBounds(5, 5, 906, 648); //位置和大小

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE );

        this.setVisible(true);
        this.setResizable(true);
        getContentPane().setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setFont(new Font("宋体", Font.BOLD, 14));
        panel_1.setBorder(new TitledBorder(null, "本人发起的志愿活动", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(33, 29, 840, 357);
        getContentPane().add(panel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setFont(new Font("宋体", Font.BOLD, 14));
        panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "已加入志愿者情况", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_2.setBounds(458, 29, 356, 300);
        panel_1.add(panel_2);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(42, 46, 273, 214);
        panel_2.add(scrollPane);

        Allcomerslist = new JList<Allcomers>();
        Allcomerslist.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {

                allcomers=Allcomerslist.getSelectedValue();
                txtStuID.setText(allcomers.getVolunteerInfo().getStuInfo().getStuID());
                txtStudentName.setText(allcomers.getVolunteerInfo().getStuInfo().getStuName());
            }
        });
        Allcomerslist.setBorder(new LineBorder(new Color(0, 0, 0)));
        Allcomerslist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(Allcomerslist);


        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "志愿者情况", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(33, 409, 840, 174);

        getContentPane().add(panel);
        panel.setLayout(null);

        btnAccept = new JButton("审核通过");
        btnAccept.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                acceptAllcomer();
            }
        });
        btnAccept.setBounds(459, 103, 113, 37);
        panel.add(btnAccept);

        btnReject = new JButton("拒绝加入");
        btnReject.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        btnReject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                refuseAllcomer();
            }
        });
        btnReject.setBounds(149, 102, 113, 38);
        panel.add(btnReject);

        JLabel label = new JLabel("学号/考试号");
        label.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        label.setBounds(30, 49, 100, 21);
        panel.add(label);

        JLabel lblNewLabel = new JLabel("姓名");
        lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblNewLabel.setBounds(377, 49, 51, 19);
        panel.add(lblNewLabel);

        txtStuID = new JTextField();
        txtStuID.setBounds(149, 49, 119, 24);
        panel.add(txtStuID);
        txtStuID.setColumns(10);

        txtStudentName = new JTextField();
        txtStudentName.setBounds(459, 49, 119, 24);
        panel.add(txtStudentName);
        txtStudentName.setColumns(10);
        Allcomerslist.setBounds(10, 10, 271, 325);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(null, "\u4F60\u521B\u5EFA\u7684\u5FD7\u613F\u6D3B\u52A8", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.setBounds(34, 29, 392, 300);
        panel_1.add(panel_3);
        panel_3.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(51, 41, 273, 214);
        panel_3.add(scrollPane_1);

        allActTable = new JTable();
        allActTable.setFont(new Font("宋体", Font.PLAIN, 12));

        allActTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                InitList();

            }
        });
        allActTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_1.setViewportView(allActTable);
        showAllActivity();


    }
    private void showAllActivity() {
        String []tableHead=activityServices.getAdminActTableHead();
        System.out.println(tableHead[1]);
        String [][]tableContent=activityServices.getAdminActTableContent(fatherWin.getCurAdmin().getStuInfo().getStuID());
        allActivityModel=new DefaultTableModel(tableContent,tableHead);
        this.allActTable.setModel(allActivityModel);  //加载模型数据
        this.allActTable.validate(); //刷新表格
        return;
    }
    private void InitList() {
        listModel=new DefaultListModel<Allcomers>();
        int row=allActTable.getSelectedRows()[0];
        Object actID= allActTable.getValueAt(row,0);
        int actid=Integer.parseInt((String) actID);
        ArrayList<Allcomers> allcomers=activityServices.getAllcomersByActID(actid);
        if(allcomers!=null)
        {
            for(Allcomers allcomer:allcomers) {
                listModel.addElement(allcomer);
            }
        }
        try {
            this.Allcomerslist.setModel(listModel);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        this.Allcomerslist.validate();

    }

    private void acceptAllcomer() {
        checkAllcomer("接受");
    }


    private void checkAllcomer(String judge) {
        if (allcomers==null)
        {
            JOptionPane.showMessageDialog(null,"没有选择组员！");
            return;
        }
        if(activityServices.checkAllcomers(fatherWin.getCurAdmin().getStuInfo(),allcomers, judge))
        {
            JOptionPane.showMessageDialog(null,"已完成审核！");
            InitList();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"操作有问题！"+activityServices.getStrMessage());
        }
        return;
    }

    private void refuseAllcomer()
    {
        if (allcomers==null)
        {
            JOptionPane.showMessageDialog(null,"没有选择组员！");
            return;
        }
        if(JOptionPane.showConfirmDialog(this,"你确定要拒绝"+allcomers.getVolunteerInfo().getStuInfo().getStuName()+"的申请加入吗？")==JOptionPane.YES_OPTION)
        {
            if(activityServices.refuseAllcomers(fatherWin.getCurAdmin().getStuInfo(), allcomers))
            {
                JOptionPane.showMessageDialog(null,"已完成审核！");
                InitList();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"操作有问题！"+activityServices.getStrMessage());
            }
        }
        return;
    }
}
