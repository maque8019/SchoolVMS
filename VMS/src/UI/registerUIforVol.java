package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import BLL.VolunteerService;


public class registerUIforVol extends JFrame{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private loginUIforAll loginui=null;
	JButton btnOK, btnCancel ;
	private JTextField txtStuID;
	private JTextField txtStuName;
	private JTextField txtVolName;
	private JPasswordField pwd1;
	private JPasswordField pwd2;
	public registerUIforVol(loginUIforAll loginui)
	{
		this.loginui =loginui;
		getContentPane().setLayout(null);
		btnOK=new JButton("提交注册");
		btnOK.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnCancel = new JButton("取消注册");
		btnCancel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));

		btnOK.addActionListener(new btnActionListener());
		btnCancel.addActionListener(new btnActionListener());

		btnCancel.setBounds(214, 336, 128, 34);
		getContentPane().add(btnCancel);

		btnOK.setBounds(69, 336, 115, 34);
		this.getContentPane().add(btnOK) ;

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(59, 10, 296, 303);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("学号");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 29, 115, 32);
		panel.add(lblNewLabel);

		txtStuID = new JTextField();
		txtStuID.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		txtStuID.setBounds(133, 29, 138, 31);
		panel.add(txtStuID);
		txtStuID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 71, 101, 38);
		panel.add(lblNewLabel_1);

		txtStuName = new JTextField();
		txtStuName.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		txtStuName.setBounds(133, 70, 138, 32);
		panel.add(txtStuName);
		txtStuName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u5FD7\u613F\u8005\u540D\u79F0");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 132, 99, 32);
		panel.add(lblNewLabel_2);

		txtVolName = new JTextField();
		txtVolName.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		txtVolName.setBounds(133, 132, 138, 35);
		panel.add(txtVolName);
		txtVolName.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("登录密码");
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(10, 190, 101, 32);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("二次密码");
		lblNewLabel_4.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(10, 247, 101, 32);
		panel.add(lblNewLabel_4);

		pwd1 = new JPasswordField();
		pwd1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		pwd1.setBounds(133, 190, 138, 35);
		panel.add(pwd1);

		pwd2 = new JPasswordField();
		pwd2.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		pwd2.setBounds(133, 247, 138, 35);
		panel.add(pwd2);

		this.setTitle("\u5FD7\u613F\u8005\u6CE8\u518C");
		this.setSize(414,429);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
		this.setResizable(true);
	}


	//内部类
	class btnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==btnOK)
			{//如果点击的是登录按钮则
				String stuID=txtStuID.getText();
				String stuName=txtStuName.getText();
				String volName=txtVolName.getText();
				String userPwd1=String.valueOf(pwd1.getPassword());
				String userPwd2=String.valueOf(pwd2.getPassword());


				//开始注册
				register(stuID,stuName,volName,userPwd1,userPwd2);
			}
			else if(e.getSource()==btnCancel)
			{
				loginui.setVisible(true);
				dispose();
			}
		}

		private void register(String stuID,
							  String stuName,
							  String volName,
							  String volPwd1,
							  String volPwd2
		) {
			//(1)数据检查--不能为空，两次密码相同等
			if(checkEmpty(stuID,stuName,volName,volPwd1,volPwd2))
			{
				JOptionPane.showMessageDialog(null,"注册不成功！","提示",1);
				return;
			}
			if(!checktwoPwd(volPwd1,volPwd2))
			{
				JOptionPane.showMessageDialog(null,"注册不成功！","提示",1);
				return;
			}

			VolunteerService op=new VolunteerService();
			if(op.register(stuID, stuName, volName, volPwd1))
			{
				//(7)显示登录窗口，关闭注册窗口
				loginui.setVisible(true);
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
								   String volName,
								   String volPwd1,
								   String volPwd2)
		{
			boolean flag=false;
			if (stuID.isEmpty()) flag=true ;
			if(stuName.isEmpty()) flag=true ;
			if(volName.isEmpty()) flag=true;
			if(volPwd1.isEmpty()) flag=true;
			if(volPwd2.isEmpty()) flag=true;
			if(flag)
			{
				JOptionPane.showMessageDialog(null,"有空字段没有填写","提示",1);
			}
			return flag;
		}

		private boolean checktwoPwd(String volPwd1,
									String volPwd2)
		{
			if(volPwd1.equals(volPwd2))
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