package UI.Activity;

import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import BLL.ActivityServices;
import Entity.Activity;
import UI.mainWin;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.SQLException;

public class CreateActivityUI extends JInternalFrame{
    private JTextField txtActivityName;
    private JTextField txtNum;
    private JButton btnCreate;
    JTextArea txtProjectDesc;
    ActivityServices actServices;
    mainWin fatherWin;
    private JTextField txtTime;
    public CreateActivityUI(mainWin f)
    {
        actServices=new ActivityServices(); //小组成员列表
        fatherWin=f;
        this.setTitle("\u521B\u5EFA\u5FD7\u613F\u6D3B\u52A8");
        this.setResizable(true); //设置是否允许自由调整大小
        this.setClosable(true);  //设置是否提供关闭按钮
        this.setMaximizable(true); //设置是否提供“最大化”按钮
        this.setIconifiable(true); //设置是否提供“最小化”按钮

        setBounds(5, 5, 758, 536); //位置和大小
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE );

        this.setVisible(true);
        this.setResizable(true);
        getContentPane().setLayout(null);

        JLabel lbl_1 = new JLabel("\u6D3B\u52A8\u540D\u79F0\uFF1A");
        lbl_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(95, 54, 586, 333);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        lbl_1.setBounds(35, 23, 92, 33);
        panel_1.add(lbl_1);

        JLabel lbl_2 = new JLabel("\u6D3B\u52A8\u4ECB\u7ECD\uFF1A");
        lbl_2.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lbl_2.setBounds(35, 90, 92, 33);
        panel_1.add(lbl_2);

        JLabel lbl_3 = new JLabel("\u5C0F\u7EC4\u4EBA\u6570\uFF1A");
        lbl_3.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lbl_3.setBounds(35, 253, 92, 33);
        panel_1.add(lbl_3);
        txtActivityName = new JTextField();
        txtActivityName.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        txtActivityName.setToolTipText("不超过30个汉字");
        txtActivityName.setBounds(141, 23, 358, 36);
        panel_1.add(txtActivityName);
        txtActivityName.setColumns(10);

        txtNum = new JTextField();
        txtNum.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        txtNum.setBounds(137, 253, 86, 33);
        panel_1.add(txtNum);
        txtNum.setColumns(10);
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(141, 97, 356, 121);
        panel_1.add(scrollPane_2);

        txtProjectDesc = new JTextArea();
        txtProjectDesc.setToolTipText("不超过50个汉字");
        txtProjectDesc.setBounds(141, 95, 354, 119);
        scrollPane_2.setViewportView(txtProjectDesc);

        JLabel lblNewLabel = new JLabel("\u5FD7\u613F\u65F6\u957F\uFF1A");
        lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblNewLabel.setBounds(295, 253, 101, 33);
        panel_1.add(lblNewLabel);

        txtTime = new JTextField();
        txtTime.setBounds(406, 253, 93, 37);
        txtTime.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        panel_1.add(txtTime);
        txtTime.setColumns(10);

        btnCreate = new JButton("提交");
        btnCreate.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    CreateActivity();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btnCreate.setBounds(243, 415, 113, 61);
        getContentPane().add(btnCreate);

        JButton btnCancel = new JButton("取消");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });
        btnCancel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        btnCancel.setBounds(418, 415, 113, 61);
        getContentPane().add(btnCancel);

    }
    private void check()
    {
            this.setEnabled(false);
            this.dispose();
        }

    private void CreateActivity() throws SQLException {
        Activity activity=null;
        String actName=this.txtActivityName.getText();
        String actInstruct=this.txtProjectDesc.getText();
        int size=Integer.parseInt(this.txtNum.getText());
        float hours=Float.parseFloat(this.txtTime.getText());
        activity=new Activity(-1,actName,actInstruct,size,hours,fatherWin.getCurAdmin(),false);
        if(actServices.CreateActivity(activity)){
            JOptionPane.showMessageDialog(null, "创建成功!","提示信息",1);
        }
        else {
            JOptionPane.showMessageDialog(null, "发布失败!"+actServices.getStrMessage(),"提示信息",1);
        };

    }
}