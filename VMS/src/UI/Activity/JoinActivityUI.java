package UI.Activity;

import javax.swing.JInternalFrame;
import BLL.ActivityServices;
import Entity.Activity;
import Entity.Allcomers;
import UI.mainWin;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinActivityUI extends JInternalFrame{

    private static final long serialVersionUID = 1L;
    mainWin fatherWin;
    private JTextField txtActName;
    private JTextField txtActID;
    private JTextField txtSize;
    private JTextField txtHours;
    private JTextField txtLeaderInfo;
    JTextArea txtProjectDesc;
    private JTable AllcomerTable;
    private JTable tblActivity;
    String [][] tableContent=null;
    DefaultTableModel allActivityModel;
    TableColumn column;
    ActivityServices activityServices=null;
    /**
     * 构造函数
     * @param f  用于与父窗体联系的对象
     */
    public JoinActivityUI(mainWin f) {
        activityServices=new ActivityServices();


        this.setTitle("\u7533\u8BF7\u52A0\u5165\u5FD7\u613F\u6D3B\u52A8");
        this.setResizable(true); //设置是否允许自由调整大小
        this.setClosable(true);  //设置是否提供关闭按钮
        this.setMaximizable(true); //设置是否提供“最大化”按钮
        this.setIconifiable(true); //设置是否提供“最小化”按钮

        try {
            this.setSelected(true);
        } catch (PropertyVetoException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }//最前面

        setBounds(5, 5, 1000, 680); //位置和大小

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE );

        this.setVisible(true);
        this.setResizable(true);
        fatherWin=f;
        getContentPane().setLayout(null);
        JPanel panel_0 = new JPanel();
        panel_0.setBorder(new TitledBorder(null, "可加入志愿活动", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_0.setBounds(28, 13, 924, 228);
        getContentPane().add(panel_0);
        panel_0.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();

        scrollPane_1.setBounds(14, 31, 896, 184);
        panel_0.add(scrollPane_1);

        tblActivity = new JTable();
        this.tblActivity.setRowHeight(24);  //行高
        Font myfont=new Font("宋体", Font.BOLD, 14);
        Font myfont1=new Font("宋体", Font.PLAIN, 14);
        tblActivity.setFont(new Font("宋体", Font.BOLD, 12));
        tblActivity.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SelectaActivitytoShow();

            }
        });
        tblActivity.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblActivity.setFont(new Font("宋体", Font.PLAIN, 14));
        JTableHeader head=tblActivity.getTableHeader();
        head.setPreferredSize(new Dimension(head.getWidth(),30));
        head.setFont(myfont);

        tblActivity.setRowHeight(25);
        tblActivity.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);



        scrollPane_1.setViewportView(tblActivity);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(28, 254, 924, 357);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lbl_0 = new JLabel("\u6D3B\u52A8\u5E8F\u53F7");
        lbl_0.setBounds(41, 42, 86, 18);
        panel_1.add(lbl_0);

        JLabel lbl_1 = new JLabel("\u5FD7\u613F\u6D3B\u52A8\u540D\u79F0");


        lbl_1.setBounds(10, 92, 117, 18);
        panel_1.add(lbl_1);

        JLabel lbl_2 = new JLabel("\u6D3B\u52A8\u4ECB\u7ECD");
        lbl_2.setBounds(41, 152, 86, 18);
        panel_1.add(lbl_2);

        JLabel lbl_3 = new JLabel("\u6D3B\u52A8\u4EBA\u6570");
        lbl_3.setBounds(41, 268, 86, 18);
        panel_1.add(lbl_3);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "已加入志愿者情况", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_2.setBounds(457, 29, 432, 300);
        panel_1.add(panel_2);
        panel_2.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(14, 38, 404, 187);
        panel_2.add(scrollPane);

        AllcomerTable = new JTable();
        AllcomerTable.setFont(new Font("宋体", Font.PLAIN, 13));
        scrollPane.setViewportView(AllcomerTable);

        JButton btnAdd = new JButton("申请加入");
        btnAdd.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ApplyforJoin();
            }
        });
        btnAdd.setBounds(78, 238, 113, 49);
        panel_2.add(btnAdd);

        JButton btnExit = new JButton("退出申请");
        btnExit.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Widthdraw();
            }
        });
        btnExit.setBounds(225, 238, 113, 49);
        panel_2.add(btnExit);

        txtActName = new JTextField();
        txtActName.setBounds(139, 89, 283, 24);
        panel_1.add(txtActName);
        txtActName.setColumns(10);

        txtActID = new JTextField();
        txtActID.setBounds(141, 39, 86, 24);
        panel_1.add(txtActID);
        txtActID.setColumns(10);

        txtSize = new JTextField();
        txtSize.setBounds(141, 271, 86, 24);
        panel_1.add(txtSize);
        txtSize.setColumns(10);

        txtLeaderInfo = new JTextField();
        txtLeaderInfo.setBounds(141, 308, 215, 24);
        panel_1.add(txtLeaderInfo);
        txtLeaderInfo.setColumns(10);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(141, 138, 294, 121);
        panel_1.add(scrollPane_2);

        txtProjectDesc = new JTextArea();
        txtProjectDesc.setLineWrap(true);
        txtProjectDesc.setWrapStyleWord(true);
        scrollPane_2.setViewportView(txtProjectDesc);
        lbl_0.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lbl_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lbl_2.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lbl_3.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        panel_0.setFont(myfont);
        panel_1.setFont(myfont);
        panel_2.setFont(myfont);
        this.txtLeaderInfo.setFont(myfont1);
        this.txtProjectDesc.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        this.txtSize.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        this.txtActID.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        this.txtActName.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        showAllActivity();

        JLabel lbl_5 = new JLabel("\u5FD7\u613F\u65F6\u957F");
        lbl_5.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lbl_5.setBounds(261, 273, 74, 21);
        panel_1.add(lbl_5);

        txtHours = new JTextField();
        txtHours.setBounds(345, 271, 66, 24);
        panel_1.add(txtHours);
        txtHours.setColumns(10);
        this.txtHours.setFont(new Font("微軟正黑體", Font.PLAIN, 18));

        JLabel lbl_4 = new JLabel("\u7EC4\u7EC7\u8005\u4FE1\u606F");
        lbl_4.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lbl_4.setBounds(24, 300, 92, 32);
        panel_1.add(lbl_4);




    }

    private void showAllActivity() {
        String []tableHead=activityServices.getTableHead();
        String [][]tableContent=activityServices.getTableContent();
        allActivityModel=new DefaultTableModel(tableContent,tableHead);
        this.tblActivity.setModel(allActivityModel);  //加载模型数据
        this.tblActivity.validate(); //刷新表格
        return;
    }

    private void SelectaActivitytoShow() {

        int rowIndex=this.tblActivity.getSelectedRows()[0];  //获得当前的行号
        //通过表格选择组号
        int actID=Integer.parseInt(this.tblActivity.getModel().getValueAt(rowIndex, 0).toString());
        Activity activity=activityServices.getActivityById(actID);//通过组号获得小组对象
        //显示当前队的信息
        if (activity!=null) {
            this.txtActID.setText(String.valueOf(activity.getActID()));
            this.txtActName.setText(activity.getActName());
            this.txtSize.setText(String.valueOf(activity.getActNum()));
            this.txtHours.setText(String.valueOf(activity.getActHours()));
            this.txtProjectDesc.setText(activity.getActInstruct());
            this.txtLeaderInfo.setText(activity.getAdminInfo().getStuInfo().getStuName()+activity.getAdminInfo().getContact());
        }
        ShowAllcomerTable(); //通过表格显示小组成员
    }
    //显示小组成员
    private void ShowAllcomerTable() {
        int rowIndex=this.tblActivity.getSelectedRows()[0];  //获得当前的行号
        //通过表格选择组号
        int actID=Integer.parseInt(this.tblActivity.getModel().getValueAt(rowIndex, 0).toString());
        String []tableHead=activityServices.getAllcomersTableHead(); //表头
        String [][]tableContent=activityServices.getAllcomersTableContent(actID);//表内容

        DefaultTableModel allcomersModel=new DefaultTableModel(tableContent,tableHead);  //构建填充表格的模型
        this.AllcomerTable.setModel(allcomersModel);  //加载模型数据
        this.AllcomerTable.validate(); //刷新表格
        return ;
    }
    /**
     * 申请加入小组
     */
    public void ApplyforJoin() {
        int actID=Integer.parseInt(this.txtActID.getText()); //小组编号
        Activity activity=activityServices.getActivityById(actID);//通过组号获得小组对象

        Allcomers allcomers=new Allcomers(-1,activity,fatherWin.getCurVol(),false,"审核中");
        if(activityServices.CreateAllcomers(allcomers)) {
            JOptionPane.showMessageDialog(null, "加入成功!","提示信息",1);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "创建不成功!"+activityServices.getStrMessage(),"提示信息",1);
        }
    }
    public void Widthdraw(){
        int actID=Integer.parseInt(this.txtActID.getText());
        Activity activity=activityServices.getActivityById(actID);//通过组号获得小组对象
        Allcomers allcomers=new Allcomers(-1,activity,fatherWin.getCurVol(),false,"退出");
        if(activityServices.DeleteAllcomers(allcomers)){
            JOptionPane.showMessageDialog(null, "退出成功!","提示信息",1);


        }else {
            JOptionPane.showMessageDialog(null, "退出不成功!"+activityServices.getStrMessage(),"提示信息",1);

        }


    }
}