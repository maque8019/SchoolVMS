package UI.Volunteer;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import BLL.AdministratorService;
import UI.mainWin;


import java.awt.Color;
import java.awt.Font;

public class JoinAdminUI extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    mainWin fatherWin;
    JButton btnOK, btnCancel ;
    private JTextField txtStuID;  //
    private JTextField txtStuName;//瀛︾敓濮撳悕
    private JTextField txtContact;//
    private JPasswordField pwd1;
    private JPasswordField pwd2;
    public JoinAdminUI(mainWin f)
    {
        getContentPane().setLayout(null);
        btnOK=new JButton("\u63D0\u4EA4\u7533\u8BF7");
        btnOK.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        btnCancel = new JButton("\u9000\u51FA");
        btnCancel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));

        btnOK.addActionListener(new btnActionListener());
        btnCancel.addActionListener(new btnActionListener());

        btnCancel.setBounds(214, 336, 107, 34);
        getContentPane().add(btnCancel);

        btnOK.setBounds(68, 336, 116, 34);
        this.getContentPane().add(btnOK) ;

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panel.setBounds(46, 10, 296, 303);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("\u5B66\u53F7:");
        lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        // lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 12));
        lblNewLabel.setBounds(48, 33, 48, 27);
        panel.add(lblNewLabel);

        txtStuID = new JTextField();
        txtStuID.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        txtStuID.setBounds(110, 33, 161, 27);
        panel.add(txtStuID);
        txtStuID.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D:");
        lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(48, 83, 48, 30);
        panel.add(lblNewLabel_1);

        txtStuName = new JTextField();
        txtStuName.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        txtStuName.setBounds(110, 85, 161, 27);
        panel.add(txtStuName);
        txtStuName.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("\u8054\u7CFB\u65B9\u5F0F:");
        lblNewLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(10, 142, 90, 21);
        panel.add(lblNewLabel_2);

        txtContact = new JTextField();
        txtContact.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        txtContact.setBounds(110, 139, 161, 27);
        panel.add(txtContact);
        txtContact.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("\u767B\u5F55\u5BC6\u7801:");
        lblNewLabel_3.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(10, 195, 86, 27);
        panel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("\u4E8C\u6B21\u5BC6\u7801:");
        lblNewLabel_4.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(10, 255, 86, 27);
        panel.add(lblNewLabel_4);

        pwd1 = new JPasswordField();
        pwd1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        pwd1.setBounds(110, 195, 161, 27);
        panel.add(pwd1);

        pwd2 = new JPasswordField();
        pwd2.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        pwd2.setBounds(110, 255, 161, 27);
        panel.add(pwd2);
        setInit() ;
        this.setTitle("\u7BA1\u7406\u5458\u7533\u8BF7");
        this.setSize(414,429);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        fatherWin=f;
    }
    /**
     * 璁剧疆鍒濆鍊硷紝渚夸簬璋冭瘯
     */
    private void setInit() {
    }
    //鍐呴儴绫?
    class btnActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==btnOK)
            {
                String stuID=txtStuID.getText();
                String stuName=txtStuName.getText();
                String contact=txtContact.getText();
                String userPwd1=String.valueOf(pwd1.getPassword());
                String userPwd2=String.valueOf(pwd2.getPassword());


                //寮?濮嬫敞鍐?
                register(stuID,stuName,contact,userPwd1,userPwd2);
            }
            else if(e.getSource()==btnCancel)
            {
                fatherWin.setVisible(true);
                dispose();
            }
        }
        private void register(String stuID,
                              String stuName,
                              String contact,
                              String userPwd1,
                              String userPwd2
        ) {
            //(1)鏁版嵁妫?鏌?--涓嶈兘涓虹┖锛屼袱娆″瘑鐮佺浉鍚岀瓑
            if(checkEmpty(stuID,stuName,contact,userPwd1,userPwd2))
            {
                JOptionPane.showMessageDialog(null,"申请不成功！","提示",1);
                return;
            }
            if(!checktwoPwd(userPwd1,userPwd2))
            {
                JOptionPane.showMessageDialog(null,"申请不成功！","提示",1);
                return;
            }
            AdministratorService op=new AdministratorService();
            if(op.register(stuID, stuName, contact, userPwd1))
            {
                fatherWin.setVisible(true);
                dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null,op.getMessage(),"提示",1);
                return;
            }


        }

        private boolean checkEmpty(String stuID,
                                   String stuName,
                                   String contact,
                                   String userPwd1,
                                   String userPwd2)
        {
            boolean flag=false;
            if (stuID.isEmpty()) flag=true ;
            if(stuName.isEmpty()) flag=true ;
            if(contact.isEmpty()) flag=true;
            if(userPwd1.isEmpty()) flag=true;
            if(userPwd2.isEmpty()) flag=true;
            if(flag)
            {
                JOptionPane.showMessageDialog(null,"有空字段没有填写","提示",1);
            }
            return flag;
        }

        private boolean checktwoPwd(String userPwd1,
                                    String userPwd2)
        {
            if(userPwd1.equals(userPwd2))
            {
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"两次密码不相同","提示",1);
                return false;

            }
        }
    }

}